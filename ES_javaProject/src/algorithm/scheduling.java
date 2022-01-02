package algorithm;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import main.main;

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
//			- Do multiple integrations (DONE)
//    		- Search for the cheapest time (DONE)
//    		- Divide equipments in smaller ones to have the equipment turn on multiple times on the day; (or just initiate different ones referring to the same equipment)
//    	  	- Solar panel inclusion

	private ArrayList<equipment> equipList = new ArrayList<equipment>();
	private ArrayList<photovoltaicPanel> panelList = new ArrayList<photovoltaicPanel>();
	
	//***PAULO HEURISTIC METHOD***//
	
	//main function
	public void heuristicScheduling(tariff tar) {
		
		int counter = 0;
		int iPosition = -1;
		double[] totalconsumption = new double[48];
		
		while(counter < equipList.size()) {
			
			
			//random num for an ID equipment
			int randomEquip = ThreadLocalRandom.current().nextInt(1, equipList.size() + 1);
			
			equipment selectedEquip = new equipment();
			selectedEquip.setID(-1);
			
			for(int i = 0; i < equipList.size();i++) {
				
				if(equipList.get(i).getID() == randomEquip && !equipList.get(i).isAlreadySchedulled()) { //check if there is a equipment with the id generated + equipment is not already scheduled
					selectedEquip = equipList.get(i);
					iPosition = i;   //save position of the equipment in the array (safety measure, may be not necessary bc the existence of the ID)
				}
			}
			
			//System.out.println("ID: " + selectedEquip.getID());
			if(selectedEquip.getID() != -1) {
				
				int randomTime = 10000;
				boolean flagScheduled = false;
				
				if(restrictions.restrictionWorkInTheDaytime(selectedEquip)) {  //equipment works only on the day hours
	
					//greedy algorithm for the scheduling
					
					if(tar.getTariffTypeByName() == "Simple") {
						//while(randomTime + selectedEquip.getExecTime() > 47) {   //generate a valid starting time
						randomTime = ThreadLocalRandom.current().nextInt(17, (47-selectedEquip.getExecTime()) + 1);
						
						//System.out.println("Day time generated: " + randomTime);
						
						
					}
					
					else if(tar.getTariffTypeByName() == "Bi-hourly") {
						
						
						if(44 < (47-selectedEquip.getExecTime())) 
							randomTime = ThreadLocalRandom.current().nextInt(44, (47-selectedEquip.getExecTime()) + 1);
						
						else
							randomTime = ThreadLocalRandom.current().nextInt(17, (47-selectedEquip.getExecTime()) + 1);
						
					}
					
					else if(tar.getTariffTypeByName() == "Summer Tri-hourly") {
						
						if(44 < (47-selectedEquip.getExecTime())) 
							randomTime = ThreadLocalRandom.current().nextInt(44, (47-selectedEquip.getExecTime()) + 1);
						
						else {
							int greedyTries = 10;
							boolean flagGreedy = false;
							
							ArrayList<List<Integer>> rangeList = new ArrayList<List<Integer>>();
							List<Integer> range1 = new ArrayList<>();
							List<Integer> range2 = new ArrayList<>();
							List<Integer> range3 = new ArrayList<>();
							
							for(int i = 16; i < 21; i++)
								range1.add(i);
							
							for(int i = 26; i < 39; i++)
								range2.add(i);
							
							for(int i = 42; i < 44; i++)
								range3.add(i);
							
							rangeList.add(range1);
							rangeList.add(range2);
							rangeList.add(range3);
							
							for(int i = 0; i < greedyTries; i++) {
								
								List<Integer> randomRange = rangeList.get(new Random().nextInt(rangeList.size()));
								
								int min = randomRange.get(0);
								int max = randomRange.get(randomRange.size()-1);
								
								if(min < (max-selectedEquip.getExecTime())) {
									randomTime = ThreadLocalRandom.current().nextInt(min, (max-selectedEquip.getExecTime()) + 1);
									flagGreedy = true;
									break;
								}
								
							}
							
							if(!flagGreedy)
								randomTime = ThreadLocalRandom.current().nextInt(17, (47-selectedEquip.getExecTime()) + 1);
						}
					
					}
					
					else if(tar.getTariffTypeByName() == "Winter Tri-hourly") {
						
						if(44 < (47-selectedEquip.getExecTime())) 
							randomTime = ThreadLocalRandom.current().nextInt(44, (47-selectedEquip.getExecTime()) + 1);
						
						else {
							int greedyTries = 10;
							boolean flagGreedy = false;
							
							ArrayList<List<Integer>> rangeList = new ArrayList<List<Integer>>();
							List<Integer> range1 = new ArrayList<>();
							List<Integer> range2 = new ArrayList<>();
							List<Integer> range3 = new ArrayList<>();
							
							for(int i = 16; i < 18; i++)
								range1.add(i);
							
							for(int i = 21; i < 36; i++)
								range2.add(i);
							
							for(int i = 41; i < 44; i++)
								range3.add(i);
							
							rangeList.add(range1);
							rangeList.add(range2);
							rangeList.add(range3);
							
							for(int i = 0; i < greedyTries; i++) {
								
								List<Integer> randomRange = rangeList.get(new Random().nextInt(rangeList.size()));
								
								int min = randomRange.get(0);
								int max = randomRange.get(randomRange.size()-1);
								
								if(min < (max-selectedEquip.getExecTime())) {
									randomTime = ThreadLocalRandom.current().nextInt(min, (max-selectedEquip.getExecTime()) + 1);
									flagGreedy = true;
									break;
								}
								
							}
							
							if(!flagGreedy)
								randomTime = ThreadLocalRandom.current().nextInt(17, (47-selectedEquip.getExecTime()) + 1);
						}
					
					}
					
					
					flagScheduled = true;
				}
				else if(selectedEquip.getEquipment() == "WateringMotor") {
					
					int[] wateringHours = restrictions.restrictionWateringHours();
					
					//while(randomTime + selectedEquip.getExecTime() > 47)   //generate a valid starting time
						randomTime = ThreadLocalRandom.current().nextInt(wateringHours[0], (wateringHours[1] - selectedEquip.getExecTime()) + 1);
					
					flagScheduled = true;
					
				}
				
				else {
					randomTime = ThreadLocalRandom.current().nextInt(0, (15-selectedEquip.getExecTime()) + 1);
					flagScheduled = true;
				}
				
				if(flagScheduled && randomTime < 48) {
					
					equipList.get(iPosition).turnOnInTimeline(randomTime);
					equipList.get(iPosition).setScheduled();
					
					
					for(int i = 0; i < panelList.size(); i++) {
						for(int j = randomTime; j < (randomTime+selectedEquip.getExecTime());j++) {
							
							if((selectedEquip.getPower()*1000) < (panelList.get(i).getPowerTimeline()[j] - panelList.get(i).getEquipsTimeline()[j])) {
								
								panelList.get(i).getEquipsTimeline()[j] += selectedEquip.getPower()*1000;
								//System.out.println("ID ADDED: " + selectedEquip.getID());
							}
							
							else if((selectedEquip.getPower()*1000) < panelList.get(i).getPowerTimeline()[j] && (selectedEquip.getPower()*1000) > panelList.get(i).getEquipsTimeline()[j]){
								
								panelList.get(i).getEquipsTimeline()[j] = selectedEquip.getPower()*1000;
								//System.out.println("ID UPDATED: " + selectedEquip.getID());
							}
						}
					}
					
					
					for(int i = randomTime; i < (randomTime + equipList.get(iPosition).getExecTime());i++) {
						
						
						int pos;
						
						//correction to above 47 -> 48 = 0; 49 = 1; etc...
						if(i > 47) {
							pos = (i%48);
							
						}
						else
							pos = i;
						
						totalconsumption[pos] += equipList.get(iPosition).getPower();  //total consumption accumulated each 30 mins
					}
						
					
					
					counter++;
				}
			}
			
			
			//checking the consumption restrictions
			if(counter == 10) {
				
				for(int i = 0; i < totalconsumption.length; i++) {
					
					//System.out.println("CONSUMPTION No" + i + ": " +totalconsumption[i]);
					
					if(i < 17) {
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
		double totalConsumption = objective_function.calcFuncObj(equipList,panelList);
		if(!restrictions.restrictionMaxConsumptionDay(totalConsumption)) {
			return false;
		}
		if(!restrictions.restrictionMaxConsumptionNight(totalConsumption)) {
			return false;
		}
		return true;
	}
	
	//TODO: Calculate objective function and acumCons and check best solution
	public void calcAcumCons(ArrayList<equipment> equipList,ArrayList<photovoltaicPanel> panelList, tariff cost) {
		for(equipment e: equipList) {
			for(int i = 0; i < objective_function.TIME; i++) {
				if(e.getTimeline()[i]) {
					e.calcCumulatedPower(i, e.getExecTime(), cost.timelineTariff);
				}
			}
		}
		
		for(photovoltaicPanel p: panelList) {
			for(int i = 0; i < objective_function.TIME; i++) {
				
				p.calcCumulatedPower(cost.timelineTariff);
			}
		}
	}
	
	public void resetAcumCons(ArrayList<equipment> equipList,ArrayList<photovoltaicPanel> panelList) {
		for(equipment e: equipList) {
			e.resetEquipment();
		}
		for(photovoltaicPanel p: panelList) {
			for(int i = 0; i < objective_function.TIME; i++) {
				p.sumPowerTimeline[i] = 0;
			}
		}
	}
	
	public boolean checkSolution(ArrayList<equipment> equipList) {
//		int totalConsumption = objective_function.calcFuncObj(equipList);
//		if(objective_function.bestSolution > totalConsumption) {
//			objective_function.bestSolution = totalConsumption;
//		}
		return objective_function.bestSolution > objective_function.calcFuncObj(equipList,panelList);
	}
	
	public void addEquipment(equipment e) {
		this.equipList.add(e);
	}
	
	public ArrayList<equipment> getEquipList() {
		return this.equipList;
	}
	
	public void addPanel(photovoltaicPanel pc) {
		
		this.panelList.add(pc);
	}

	public ArrayList<photovoltaicPanel> getPanelList() {
		
		return this.panelList;
	}
	
	//TODO: ToString
	@Override
	public String toString() {
		return null;
	}
	
}
