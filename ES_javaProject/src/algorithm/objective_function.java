package algorithm;

import java.util.ArrayList;

public class objective_function {
	int bestSolution = 1000000000;
	private static int TIME  = 48;
	
	public objective_function() {
		
	}

	public boolean checkMin(ArrayList<equipment> decisionVar) {
		int aux = this.calcFuncObj(decisionVar);
		return aux < this.bestSolution;
	}
	
	public int calcFuncObj(ArrayList<equipment> decisionVar) {
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
