package algorithm;

import java.util.ArrayList;

abstract public class objective_function {
	public static int bestSolution = 1000000000;
	public static int TIME  = 48;

	public boolean checkMin(ArrayList<equipment> decisionVar) {
		double objectiveFunc = this.calcFuncObj(decisionVar);
		return objectiveFunc < this.bestSolution;
	}
	
	public static double calcFuncObj(ArrayList<equipment> decisionVar) {
		double objectiveFunc = 0;
		double consSolar = 0; 
		for (equipment equip : decisionVar) {
			for(int j = 0; j < TIME; j++) {
				if (equip.getTimeline()[j]) {
					objectiveFunc += equip.acumCons[j];
				}
			}
		}
		objectiveFunc = objectiveFunc - consSolar;
		return objectiveFunc;
	}
	
}
