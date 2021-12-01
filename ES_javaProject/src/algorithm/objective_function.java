package algorithm;

import java.util.ArrayList;

abstract public class objective_function {
	public static int bestSolution = 1000000000;
	public static int TIME  = 48;


	public static boolean checkMin(ArrayList<equipment> decisionVar) {
		int aux = objective_function.calcFuncObj(decisionVar);
		return aux < objective_function.bestSolution;
	}
	
	public static int calcFuncObj(ArrayList<equipment> decisionVar) {
		int aux = 0;
		int consSolar = 0; //TODO: Create func to calc consSolar
		for (equipment i : decisionVar) {
			for(int j = 0; j < TIME; j++) {
				//TODO: sum according to objetive function		
			}
		}
		aux = aux - consSolar;
		return aux;
	}
	
}
