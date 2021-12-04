package algorithm;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


//
//algorithm that does the scheduling procedure
//

public class scheduling {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//                                                                                                                       //  
	//  TODO: make algorithms with different approaches that make the scheduling results different to decide what to choose  //
	//					- Heuristic: the order of the equipments being scheduled is randomized, if the equipment is already  //
	//                               scheduled then randomize again and then verify the restrictions that equipment has and  //
	//                               Finally follow a way/condition to make the algorithm schedule the equipments            //
	//                           Ways to do the scheduling (suggestions):                                                    //
	//								1. Schedule on the lowest peaks                                                          //
	//								2. Schedule closest to mid day or at night                                               //
	//								3. Decide optimal initial starts and implement a greedy scheduling		                 //
	//								4. Combination of 1 and 2                                                                //
	//                              5. Combination of 1 and 3                                                                //
	//                                                                                                                       //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private ArrayList<equipment> equipList = new ArrayList<equipment>();
	
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
