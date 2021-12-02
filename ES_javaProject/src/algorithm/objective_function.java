package algorithm;

import java.util.ArrayList;

public class objective_function {
	int bestSolution = 1000000000;
	private static int TIME  = 48;
	
	public objective_function() {
		
	}

	public boolean checkMin(ArrayList<equipment> decisionVar) {
		double objectiveFunc = this.calcFuncObj(decisionVar);
		return objectiveFunc < this.bestSolution;
	}
	
	public double calcFuncObj(ArrayList<equipment> decisionVar) {
		double objectiveFunc = 0;
		double consSolar = 0; //TODO: Create func to calc consSolar
		for (equipment equip : decisionVar) {
			for(int j = 0; j < TIME; j++) {
				if (equip.getTimeline()[j]) {
					System.out.println(j);
					System.out.println(equip);
					objectiveFunc += equip.acumCons[j];
				}
			}
		}
		objectiveFunc = objectiveFunc - consSolar;
		System.out.println(objectiveFunc);
		return objectiveFunc;
	}
	
}
