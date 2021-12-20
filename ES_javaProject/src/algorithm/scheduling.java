package algorithm;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


//
//algorithm that does the scheduling procedure
//

public class scheduling {
	
//    TODO: Heuristic algorithm notes
//    		
//    		- Randomize the equipment about to be scheduled; (DONE)
//    		- Check time constrains for the said equipment; (DONE)
//    		- Randomize the time and equipment is scheduled -> start to finish has to be inside the time constrain; (DONE)
//    				(do this 1º with full time executions)
//    		- Check power constrains and if not met, reset the whole process; if met save the result; (DONE, have to confirm if this is correct)
//    		
//    	  To implement after:
//
//			- Do multiple integrations (EASY, DONE)
//    		- Search for the cheapest time
//    		- Divide equipments in smaller ones to have the equipment turn on multiple times on the day;
//    	  	- Solar panel inclusion

	private ArrayList<equipment> equipList = new ArrayList<equipment>();
	
	
	//***PAULO HEURISTIC METHOD***//
	
	//main function
	public void heuristicScheduling() {
		
		int counter = 0;
		int iPosition = -1;
		double[] totalconsumption = new double[48];
		
		while(counter < 10) {
			
			
			//random num for an ID equipment
			int randomEquip = ThreadLocalRandom.current().nextInt(1, 10 + 1);
			
			equipment selectedEquip = new equipment();
			selectedEquip.setID(-1);
			
			for(int i = 0; i < equipList.size();i++) {
				
				if(equipList.get(i).getID() == randomEquip && !equipList.get(i).isAlreadySchedulled()) { //check if there is a equipment with the id generated + equipment is not already scheduled
					selectedEquip = equipList.get(i);
					iPosition = i;   //save position of the equipment in the array (safety measure, may be not necessary bc the existance of the ID)
				}
			}
			
			//System.out.println("ID: " + selectedEquip.getID());
			if(selectedEquip.getID() != -1) {
				
				int randomTime = 10000;
				boolean flagScheduled = false;
				
				if(restrictions.restrictionWorkInTheDaytime(selectedEquip)) {  //equipment works only on the day hours
	
					
					//System.out.println("rt + execTime: " + randomTime + selectedEquip.getExecTime());
					
					while(randomTime + selectedEquip.getExecTime() > 47) {   //generate a valid starting time
						randomTime = ThreadLocalRandom.current().nextInt(17, 47 + 1);
						//System.out.println("Day time generated: " + randomTime);
					}
					
					flagScheduled = true;
				}
				else if(selectedEquip.getEquipment() == "WateringMotor") {
					
					int[] wateringHours = restrictions.restrictionWateringHours();
					
					while(randomTime + selectedEquip.getExecTime() > 47)   //generate a valid starting time
						randomTime = ThreadLocalRandom.current().nextInt(wateringHours[0], wateringHours[1] + 1);
					
					flagScheduled = true;
					
				}
				
				else {
					randomTime = ThreadLocalRandom.current().nextInt(0, 47 + 1);
					flagScheduled = true;
				}
				
				if(flagScheduled && randomTime < 48) {
					
					equipList.get(iPosition).turnOnInTimeline(randomTime);
					equipList.get(iPosition).setScheduled();
					
					for(int i = randomTime; i < (randomTime + equipList.get(iPosition).getExecTime());i++) {
						
						int pos;
						
						//correction to above 47 -> 48 = 0; 49 = 1; etc...
						if(i > 47) {
							pos = (i%48);
							
						}
						else
							pos = i;
						
						totalconsumption[pos] += equipList.get(iPosition).getPower()/2;  //total consumption accumulated each 30 mins
					}
						
					
					
					counter++;
				}
			}
			
			
			//checking the consumption restrictions
			if(counter == 10) {
				
				for(int i = 0; i < totalconsumption.length; i++) {
					
					//System.out.println("CONSUMPTION No" + i + ": " +totalconsumption[i]);
					
					if(i < 7) {
						if(!restrictions.restrictionMaxConsumptionNight(totalconsumption[i])) {						
							counter = 0;	
							for(int j = 0; j < totalconsumption.length; j++) 							
								totalconsumption[j] = 0;
							
							for(int j = 0; j < equipList.size(); j++)
								equipList.get(j).resetEquipment();
							break;
						}					
					}
					
					else {
						if(!restrictions.restrictionMaxConsumptionDay(totalconsumption[i])) {				
							counter = 0;
							for(int j = 0; j < totalconsumption.length; j++) 						
								totalconsumption[j] = 0;
							
							for(int j = 0; j < equipList.size(); j++)
								equipList.get(j).resetEquipment();
							
							break;
						}				
					}
					
					
				}
				
				//System.out.println("-----------------------------------------------------------------------");
	
			}
			
		}
		
	}
	
	
	
	
	
	//TODO: Pick a solution (for now can be a manual solution) 
	public void randomSols(ArrayList<equipment> equip) {
		
		//int counter = 0;
		
		for(equipment e: equip) {
			
			//while(counter < e.getExecTime()) {
				
				int randomNum = ThreadLocalRandom.current().nextInt(0, 47 + 1);
				
				if(!e.getXtPosition(randomNum)) {
					
					e.turnOnInTimeline(randomNum);
					//counter++;
				}
					
			//}
			
			e.setScheduled();
			//counter = 0;
			
		}
		
		
	}
	
	//TODO: Verify restrictions
	public boolean checkRest(ArrayList<equipment> equipList) {
		double totalConsumption = objective_function.calcFuncObj(equipList);
		if(!restrictions.restrictionMaxConsumptionDay(totalConsumption)) {
			return false;
		}
		if(!restrictions.restrictionMaxConsumptionNight(totalConsumption)) {
			return false;
		}
		return true;
	}
	
	//TODO: Calculate objective function and acumCons and check best solution
	public void calcAcumCons(ArrayList<equipment> equipList, tariff cost) {
		for(equipment e: equipList) {
			for(int i = 0; i < objective_function.TIME; i++) {
				if(e.getTimeline()[i]) {
					e.calcCumulatedPower(i, e.getExecTime(), cost.timelineTariff);
				}
			}
		}
	}
	
	public void resetAcumCons(ArrayList<equipment> equipList) {
		for(equipment e: equipList) {
			for(int i = 0; i < objective_function.TIME; i++) {
				if(e.getTimeline()[i]) {
					e.acumCons[i] = 0;
				}
			}
		}
	}
	
	public boolean checkSolution(ArrayList<equipment> equipList) {
//		int totalConsumption = objective_function.calcFuncObj(equipList);
//		if(objective_function.bestSolution > totalConsumption) {
//			objective_function.bestSolution = totalConsumption;
//		}
		return objective_function.bestSolution > objective_function.calcFuncObj(equipList);
	}
	
	public void addEquipment(equipment e) {
		this.equipList.add(e);
	}
	
	public ArrayList<equipment> getEquipList() {
		return equipList;
	}

	
	//TODO: ToString
	@Override
	public String toString() {
		return null;
	}
	
}
