package algorithm;

public class objective_function {
	int bestSolution = 1000000000;
	public objective_function() {
		
	}

	public boolean checkMin(equipment[] decisionVar) {
		int aux = this.calcFuncObj(decisionVar);
		return aux < this.bestSolution;
	}
	
	private int calcFuncObj(equipment[] decisionVar) {
		int aux = 0;
		for (equipment i : decisionVar) {
			//TODO: sum according to objetive fumction
		}
		return aux;
	}
	
}
