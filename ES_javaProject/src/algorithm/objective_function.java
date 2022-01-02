package algorithm;

import java.util.ArrayList;

abstract public class objective_function {
	public static int bestSolution = 1000000000;
	public static int TIME  = 48;
	public static double SAVED  = 0;
	
	public boolean checkMin(ArrayList<equipment> decisionVar1, ArrayList<photovoltaicPanel> decisionVar2) {
		double objectiveFunc = this.calcFuncObj(decisionVar1, decisionVar2);
		return objectiveFunc < this.bestSolution;
	}
	
	public static double calcFuncObj(ArrayList<equipment> decisionVar1, ArrayList<photovoltaicPanel> decisionVar2) {
		double objectiveFunc = 0;
		double consSolar = 0;
		
		for(photovoltaicPanel panel: decisionVar2) {
			for(int j = 0; j < TIME; j++) {
				
				consSolar += panel.acumCons[j];
				
			}
			
		}
		
		
		for (equipment equip : decisionVar1) {
			for(int j = 0; j < TIME; j++) {
				if (equip.getTimeline()[j]) {
					objectiveFunc += equip.acumCons[j];
				}
			}
		}
		SAVED = consSolar;
		objectiveFunc = objectiveFunc - consSolar;
		return objectiveFunc;
	}
	
}
