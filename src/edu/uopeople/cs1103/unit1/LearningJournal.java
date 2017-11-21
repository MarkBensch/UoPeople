package edu.uopeople.cs1103.unit1;

import edu.uopeople.util.TextIO;

/**
 * Learning Journal Exercise Unit 1
 * @author Mark Bensch
 */

public class LearningJournal {

	public static void main( String args[] ) {
		SolveQuadratic solve = new SolveQuadratic();
		solve.run();
	}

	//TODO: parse equation ~ not happening - just asking for A,B,C

	private static class SolveQuadratic {
		/**
		 * execute the logic for finding the roots of a quadratic.
		 */
		public void run() {
			double answers[];
			double values[] = getUserValues();
			try {
				answers = roots( values[ 0 ], values[ 1 ], values[ 2 ] );
				displayAnswer( answers );
				runAgain();
			} catch ( IllegalArgumentException e ) {
				System.out.println( "\nInvalid parameter while evaluating the quadratic formula:" );
				System.out.println( e.getMessage() );

				run();
			}
		}

		/**
		 * outputs the values of the quadratic formula
		 *
		 * @param answers
		 */
		private void displayAnswer( double[] answers ) {
			System.out.println( "The Roots of the given quadratic are: " );
			if ( answers[ 0 ] == answers[ 1 ] ) {
				System.out.println( "x = " + answers[ 0 ] );
			} else {
				System.out.println( "x = " + answers[ 0 ] + " & x = " + answers[ 1 ] );
			}
		}

		/**
		 * should the program continue to run or exit out
		 */
		private void runAgain() {
			System.out.print( "Would you like to solve for another Quadratic? (yes/no) " );
			String again = TextIO.getWord();
			if ( again.equalsIgnoreCase( "yes" ) ) {
				run();
			} else if ( again.equalsIgnoreCase( "no" ) ) {
				System.exit( 0 );
			} else {
				System.out.println( "please enter a valid response" );
				runAgain();
			}

		}

		/**
		 * Returns array of the two roots of the quadratic equation
		 * A*x*x + B*x + C = 0, provided it has any roots.  If A == 0 or
		 * if the discriminant, B*B - 4*A*C, is negative, then an exception
		 * of type IllegalArgumentException is thrown.
		 *
		 * @return an array of length 1 or 2
		 * @throws IllegalArgumentException
		 */
		private double[] roots( double A, double B, double C )
				throws IllegalArgumentException {
			if ( A == 0 ) {
				throw new IllegalArgumentException( "'A' can't be zero." );
			} else {
				double disc = B * B - 4 * A * C;
				if ( disc < 0 )
					throw new IllegalArgumentException( "Discriminant < zero." );
				return new double[] { ( -B + Math.sqrt( disc ) ) / ( 2 * A ), ( -B - Math.sqrt( disc ) ) / ( 2 * A ) };
			}
		}

		/**
		 * get user values for a quadratic function A, B, C (A*x*x + B*x + C = 0)
		 *
		 * @return double array of length 3
		 */
		private double[] getUserValues() {
			double a, b, c;
			System.out.println( "Please enter below the numeric values of a quadratic equation." );
			System.out.println( "A*x*x + B*x + C = 0, provide the values A, B, C" );
			System.out.print( "A = " ); // this was odd as the other print statements had a ? but A did not on the first call
			a = TextIO.getDouble();
			System.out.print( "B = " );
			b = TextIO.getDouble();
			System.out.print( "C = " );
			c = TextIO.getDouble();
			return new double[] { a, b, c };
		}
	}
}
