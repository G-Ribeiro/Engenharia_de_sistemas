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
	
	//TODO: Calculate objetive function and acumCons and check best solution
	
	//TODO: ToString
	@Override
	public String toString() {
		return null;
	}
	
}
