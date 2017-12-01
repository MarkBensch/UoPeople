package edu.uopeople.cs1103.unit2;


/**
 * In many textbooks, the first examples of recursion are the mathematical functions factorial and fibonacci. These functions are defined for non-negative integers using the following recursive formulas:
 *
 * factorial(0) = 1
 * factorial(N) = N*factorial(N-1) for N > 0
 *
 * fibonacci(0) = 1 - needs to be 0 not 1
 * fibonacci(1) = 1
 * fibonacci(N) = fibonacci(N-1) + fibonacci(N-2) for N > 1
 *
 * Write recursive functions to compute factorial(N) and fibonacci(N) for a given non-negative integer N, and write a main() routine to test your functions.
 *
 * <b>Output:</b>
 * Fibonacci( 0 ) equals 0: true
 * Fibonacci( 1 ) equals 1: true
 * Fibonacci( 15 ) equals 610: true
 * Fibonacci( -5 ) is not valid
 *
 * ====================================
 *
 * factorial( 0 ) equals 1: true
 * factorial( 1 ) equals 1: true
 * factorial( 15 ) equals 2004310016: true
 * factorial( -5 ) is not validÒ
 */
public class LearningJournal {
	public static void main( String[] args ) {
		int zero = 0;
		int one = 1;
		int number = 15;
		int neg = -5;

		System.out.println( "Fibonacci( 0 ) equals 0: " + ( fibonacci( zero ) == 0 ) );
		System.out.println( "Fibonacci( 1 ) equals 1: " + ( fibonacci( one ) == 1 ) );
		System.out.println( "Fibonacci( 15 ) equals 610: " + ( fibonacci( number ) == 610 ) );
		try {
			fibonacci( neg );
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() );
		}
		System.out.println( "\n====================================\n" );
		System.out.println( "factorial( 0 ) equals 1: " + ( factorial( zero ) == 1 ) );
		System.out.println( "factorial( 1 ) equals 1: " + ( factorial( one ) == 1 ) );
		System.out.println( "factorial( 15 ) equals 2004310016: " + ( factorial( number ) == 2004310016 ) );
		try {
			factorial( neg );
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() );
		}
	}

	/**
	 * fibonacci(0) = 0
	 * fibonacci(1) = 1
	 * fibonacci(N) = fibonacci(N-1) + fibonacci(N-2) for N > 1
	 *
	 * @param value
	 * @return int
	 * @throws IllegalArgumentException
	 */
	private static int fibonacci( int value ) throws IllegalArgumentException {
		if ( value == 0 ) {
			return 0;
		} else if ( value == 1 ) {
			return 1;
		} else if ( value < 0 ) {
			throw new IllegalArgumentException( "Fibonacci( " + value + " ) is not valid" );
		} else {
			return fibonacci( value - 1 ) + fibonacci( value - 2 );
		}
	}

	/**
	 * factorial(0) = 1
	 * factorial(N) = N*factorial(N-1) for N > 0
	 *
	 * @param value
	 * @return int
	 * @throws IllegalArgumentException
	 */
	private static int factorial( int value ) throws IllegalArgumentException {
		if ( value == 0 ) {
			return 1;
		} else if ( value < 0 ) {
			throw new IllegalArgumentException( "factorial( " + value + " ) is not valid" );
		} else {
			return value * factorial( value - 1 );
		}
	}
}
