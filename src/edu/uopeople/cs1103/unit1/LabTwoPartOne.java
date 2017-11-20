package edu.uopeople.cs1103.unit1;

import java.util.Arrays;

/**
 * This program runs benchmarks on 2 types of sort using 3 array sizes.
 * The Array.sort() method is the base line being used.
 * then a custom sort method is used called a selection sort
 * output:
 * Using a basic Arrays.sort() with array of size 1000 took 1 milliseconds to run.
 * Using a custom selection Sort with array of size 1000 took 4 milliseconds to run.
 * Using a basic Arrays.sort() with array of size 10000 took 3 milliseconds to run.
 * Using a custom selection Sort with array of size 10000 took 45 milliseconds to run.
 * Using a basic Arrays.sort() with array of size 100000 took 12 milliseconds to run.
 * Using a custom selection Sort with array of size 100000 took 2953 milliseconds to run.
 */
public class LabTwoPartOne {
	public static void main( String[] args ) {
		int small = 1000;
		int medium = 10000;
		int big = 100000;

		builtInSort( small );
		customSort( small );
		builtInSort( medium );
		customSort( medium );
		builtInSort( big );
		customSort( big );
	}

	/**
	 * does a benchmark on the built in array sort
	 * displays the results as a println
	 * @param size
	 */
	public static void builtInSort( int size ) {
		int data[] = generateIntArray( size );
		long startTime = System.currentTimeMillis();
		Arrays.sort( data );
		long runTime = System.currentTimeMillis() - startTime;
		System.out.println( "Using a basic Arrays.sort() with array of size " + size + " took " + runTime + " milliseconds to run." );
	}

	/**
	 * does a benchmark on a custom selection sort
	 * and displays the results as a println
	 * @param size
	 */
	public static void customSort( int size ) {
		int data[] = generateIntArray( size );
		long startTime = System.currentTimeMillis();
		selectionSort( data );
		long runTime = System.currentTimeMillis() - startTime;
		System.out.println( "Using a custom selection Sort with array of size " + size + " took " + runTime + " milliseconds to run." );
	}

	/**
	 * does a selection sort on the array passed into it.
	 * Code is from Page 348 of the textbook
	 * Introduction to Programming Using Java Version 5.0, December 2006
	 *
	 * @param A
	 */
	static void selectionSort( int[] A ) {
		for ( int lastPlace = A.length - 1; lastPlace > 0; lastPlace-- ) {
			int maxLoc = 0;
			for ( int j = 1; j <= lastPlace; j++ ) {
				if ( A[ j ] > A[ maxLoc ] ) {
					maxLoc = j;
				}
			}
			int temp = A[ maxLoc ];
			A[ maxLoc ] = A[ lastPlace ];
			A[ lastPlace ] = temp;
		}
	}

	/**
	 * Build a random value primitive integer array of the size passed in
	 * @param size
	 * @return int[]
	 */
	public static int[] generateIntArray( int size ) {
		int array[] = new int[ size ];
		for ( int index = 0; index < size; index++ ) {
			array[ index ] = (int) ( Integer.MAX_VALUE * Math.random() );
		}
		return array;
	}

}
