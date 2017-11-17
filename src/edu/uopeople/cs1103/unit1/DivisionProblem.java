package edu.uopeople.cs1103.unit1;


/**
 * Generate a random 2 value division problem includes getter methods for the problem and answer.
 * @author Mark Bensch
 */
public class DivisionProblem extends MathProblem {

	/**
	 * constructor generates the random math problem along with the answer
	 */
	public DivisionProblem() {
		x = (int)(10 + 40 * Math.random());
		answer = (int)(30 * Math.random());
		y = x * answer;
	}

	/**
	 * get the math question as a String
	 * @return a String question
	 */
	public String getProblem() {
		return "Compute the quotient: " + y + " / " + x;
	}

}
