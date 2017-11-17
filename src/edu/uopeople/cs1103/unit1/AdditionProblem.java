package edu.uopeople.cs1103.unit1;

/**
 * Generate a random 2 value addition problem includes getter methods for the problem and answer.
 * @author cs1103
 * @author Mark Bensch
 */
public class AdditionProblem extends MathProblem{

	/**
	 * constructor generates the random math problem along with the answer
	 */
	public AdditionProblem() {
		x = (int)(10 + 40 * Math.random());
		y = (int)(30 * Math.random());
		answer = x + y;
	}

	/**
	 * get the math question as a String
	 * @return a String question
	 */
	public String getProblem() {
		return "Compute the sum: " + x + " + " + y;
	}

}
