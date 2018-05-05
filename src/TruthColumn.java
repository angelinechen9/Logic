
public class TruthColumn {
	public boolean[] constants1;
	public boolean[] constants2;
	public boolean[] constants3;
	
	public TruthColumn(int constants) {
		if (constants == 1) {
			boolean[] constants1 = {true, false};
			this.constants1 = constants1;
		}
		else if (constants == 2) {
			boolean[] constants1 = {true, true, false, false};
			boolean[] constants2 = {true, false, true, false};
			this.constants1 = constants1;
			this.constants2 = constants2;
		}
		else if (constants == 3) {
			boolean[] constants1 = {true, true, true, false, false, false};
			boolean[] constants2 = {true, true, false, true, true, false};
			boolean[] constants3 = {true, false, false, true, false, false};
			this.constants1 = constants1;
			this.constants2 = constants2;
			this.constants3 = constants3;
		}
		
	}
	
	/**
	 * evaluates the conjunction of the constants
	 * @param constants2 the constants that are evaluated
	 * @return the conjunction of the constants
	 */
	public boolean[] and(boolean[] constants1, boolean[] constants2) {
		boolean[] evaluate = new boolean[constants1.length];
		for (int i = 0; i < constants1.length; i++) {
			evaluate[i] = constants1[i] && constants2[i];
		}
		return evaluate;
	}
	
	/**
	 * evaluates the disjunction of the constants
	 * @param constants2 the constants that are evaluated
	 * @return the disjunction of the constants
	 */
	public boolean[] or(boolean[] constants1, boolean[] constants2) {
		boolean[] evaluate = new boolean[constants1.length];
		for (int i = 0; i < constants1.length; i++) {
			evaluate[i] = constants1[i] || constants2[i];
		}
		return evaluate;
	}
	
	/**
	 * evaluates the negation of the constants
	 * @return the negation of the constants
	 */
	public boolean[] negate(boolean[] constants1) {
		boolean[] evaluate = new boolean[constants1.length];
		for (int i = 0; i < constants1.length; i++) {
			evaluate[i] = !constants1[i];
		}
		return evaluate;
	}
	
	/**
	 * evaluates the implication of the constants
	 * @param constants2 the constants that are evaluated
	 * @return the implication of the constants
	 */
	public boolean[] imply(boolean[] constants1, boolean[] constants2) {
		boolean[] evaluate = new boolean[constants1.length];
		for (int i = 0; i < constants1.length; i++) {
			if ((constants1[i] == true) && (constants2[i] == false)) {
				evaluate[i] = false;
			}
			else {
				evaluate[i] = true;
			}
		}
		return evaluate;
	}
	
	/**
	 * evaluates the biconditional of the constants
	 * @param constants2 the constants that are evaluated
	 * @return the biconditional of the constants
	 */
	public boolean[] biconditional(boolean[] constants1, boolean[] constants2) {
		boolean[] evaluate = new boolean[constants1.length];
		for (int i = 0; i < constants1.length; i++) {
			if (constants1[i] == constants2[i]) {
				evaluate[i] = true;
			}
			else {
				evaluate[i] = false;
			}
		}
		return evaluate;
	}
}
