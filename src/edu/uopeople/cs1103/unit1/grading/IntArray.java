package edu.uopeople.cs1103.unit1.grading;

public class IntArray {
	private int arraySize;
	private int[] tmpArray;

	/**
	 * Class constructor. Creates an array of random integers.
	 *
	 * @param arrayLength the number of integers in the array.
	 */
	public IntArray (int arrayLength) {
		arraySize = arrayLength;
		tmpArray = new int[arraySize];

		for(int i = 0; i < arraySize; i++)
			tmpArray[i] = (int)(Integer.MAX_VALUE * Math.random());
	} // end of constructor

	/**
	 * This method returns the array of random integers,
	 * create in the class constructor.
	 *
	 * @return an array of integers.
	 */
	public int[] getArray() {
		return tmpArray;
	}

	/**
	 * This method returns a copy of the array of random integers,
	 * create in the class constructor.
	 *
	 * @return an array of integers.
	 */
	public int[] getArrayCopy() {
		int[] arrayCopy = new int[arraySize];

		for(int i = 0; i < arraySize; i++)
			arrayCopy[i] = tmpArray[i];

		return arrayCopy;
	} // end of getArrayCopy()
} // end of class IntArray (complementing class)
