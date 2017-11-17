package edu.uopeople.cs1103.unit1;

public abstract class MathProblem {
	protected int x,y,answer;

	/**
	 *  get the answer to the problem as int
	 * @return an int answer
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * implementation may very by the class that extends MathProblem.
	 * @return should return a string of the problem
	 */
	abstract String getProblem();
}
