package edu.uopeople.cs1103.unit1;

public class LearningJournal {
	public static void main( String args[] ) {
		SolveQuadratic solve = new SolveQuadratic();
		solve.run();
	}

	//TODO: parse equation
//TODO: read equation
//TODO: print error ask a question again
//TODO: repeat "enter equation" until "no" is entered
//TODO:
//TODO:
//TODO:
//TODO:
	private static class SolveQuadratic {
		public void run() {

		}

		/**
		 * Returns the larger of the two roots of the quadratic equation
		 * A*x*x + B*x + C = 0, provided it has any roots.  If A == 0 or
		 * if the discriminant, B*B - 4*A*C, is negative, then an exception
		 * of type IllegalArgumentException is thrown.
		 */
		public double[] roots( double A, double B, double C )
				throws IllegalArgumentException {
			if ( A == 0 ) {
				throw new IllegalArgumentException( "A can't be zero." );
			} else {
				double disc = B * B - 4 * A * C;
				if ( disc < 0 )
					throw new IllegalArgumentException( "Discriminant < zero." );
				return new double[]{( -B + Math.sqrt( disc ) ) / ( 2 * A ), ( -B - Math.sqrt( disc ) ) / ( 2 * A )};
			}
		}
	}
}
