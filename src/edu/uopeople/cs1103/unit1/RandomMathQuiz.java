package edu.uopeople.cs1103.unit1;


import edu.uopeople.util.TextIO;

import java.util.Arrays;

/**
 * the main class for the simple math quiz
 * this class houses all quiz logic.
 * @author Mark Bensch
 */
public class RandomMathQuiz {

	private int questionChances, numQuestions, fullCredit;

	Double score[];

	/**
	 * constructor setting values to variables
	 */
	public RandomMathQuiz() {
		numQuestions = 10;
		questionChances = 2;
		fullCredit = 10;
		score = new Double[ numQuestions ];
	}

	/**
	 * start the quiz run
	 */
	public void start() {
		MathProblem question;
		for ( int questionCount = 0; questionCount < numQuestions; questionCount++ ) {
			System.out.println( "Question " + ( questionCount + 1 ) + ":" );
			question = randomMathProblem();
			System.out.println( question.getProblem() );
			for ( int chanceCount = 0; chanceCount < questionChances; chanceCount++ ) {
				if ( score[ questionCount ] == null ) {
					score[ questionCount ] = answerQuestion( chanceCount, question );
				} else {
					break;
				}
			}
			System.out.println( "=============================" );
		}
		displayResults();
	}

	/**
	 * print the scores and parameters of the quiz
	 */
	private void displayResults() {
		System.out.println( "Number of Questions: " + numQuestions );
		System.out.println( "Number of Chances per Question: " + questionChances );
		System.out.println( "Full Credit per Question: " + fullCredit );
		System.out.println( "Over All Grade: " + totalScore() + " out of " + ( numQuestions * fullCredit ) );
		System.out.println( "Score Breakdown: " );
		scoreBreakdown();

	}

	/**
	 * create a print out of the unique values and occurrences of the values in score
	 */
	private void scoreBreakdown() {
		double prim[] = new double[ score.length ];
		for ( int index = 0; index < score.length; index++ ) {
			if ( score[ index ] == null ) {
				prim[ index ] = 0;
			} else {
				prim[ index ] = score[ index ].doubleValue();
			}
		}
		double unique[] = Arrays.stream( prim ).distinct().toArray();
		for ( double value : unique ) {
			System.out.println( value + ": " + scoreCount( value ) );
		}
	}

	/**
	 * count the occurrences of the param
	 * @param value
	 * @return count of value
	 */
	private int scoreCount( double value ) {
		int count = 0;
		for ( Double i : score ) {
			if ( i != null ) {
				if ( value == i.doubleValue() ) {
					count++;
				}
			} else {
				if ( value == 0 ) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * generate the total score for the quiz
	 * @return total of score
	 */
	private double totalScore() {
		double total = 0;
		for ( Double i : score ) {
			if ( i != null ) {
				total += i.doubleValue();
			} else {
				total += 0;
			}
		}
		return ( total );
	}

	/**
	 * gather user's answer to the math problem presented to them.
	 * @param chanceCount
	 * @param question
	 * @return score after chances also returns null if answer is not correct
	 */
	private Double answerQuestion( double chanceCount, MathProblem question ) {
		double readAnswer;

		try {
			readAnswer = TextIO.getlnDouble();
			if ( readAnswer == question.getAnswer() ) {
				System.out.println( "Correct!" );
				return new Double( ( ( questionChances - chanceCount ) / questionChances ) * fullCredit );
			} else if ( chanceCount + 1 == questionChances ) {
				System.out.println( "That was Incorrect." );
				System.out.println( "The correct answer was " + question.getAnswer() );
				return null;
			} else {
				System.out.println( "That was Incorrect.\nPlease try again." );
				return null;
			}

		} catch ( Exception e ) {
			System.out.println( e );
			System.out.println( "Answer is not a number.\nPlease try again." );
			return null;
		}
	}

	/**
	 * Randomly select a math problem type from a list of objects that extend MathProblem
	 * @return random MathProblem
	 */
	private MathProblem randomMathProblem() {
		MathProblem mathType[] = { new AdditionProblem(), new SubtractionProblem(), new MultiplicationProblem(), new DivisionProblem() };
		int randomIndex = (int) ( ( mathType.length ) * Math.random() );
		return mathType[ randomIndex ];
	}

}
