package edu.uopeople.cs1103.unit1.grading;

import java.util.Arrays;

public class ArraySort {
	private static final int ARRAY_SIZE_T = 1000; // a thousand
	private static final int ARRAY_SIZE_TT = 10000; // ten thousand
	private static final int ARRAY_SIZE_HT = 100000; // a hundred thousand
	/**
	 * This method only calls sortingArrays() three times, each with
	 * different array sizes.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		sortingArrays(ARRAY_SIZE_T);
		sortingArrays(ARRAY_SIZE_TT);
		sortingArrays(ARRAY_SIZE_HT);
	} // end of main()

	/**
	 * This method creates two arrays of integers with equal content, then
	 * it sorts one applying the Selection Sort algorithm and the other with
	 * the built-in Arrays.sort algorithm, and finally prints out the running time of
	 * each of the sorting algorithms.
	 *
	 * @param arraySize the size of both arrays to be created.
	 */
	private static void sortingArrays(int arraySize) {
		IntArray baseArray = new IntArray(arraySize);
		int[] firstArray = baseArray.getArray();
		int[] secondArray = baseArray.getArrayCopy();
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		selectionSort(firstArray);
		endTime = System.currentTimeMillis();
		System.out.printf("Time to sort an array of %,d integers", arraySize);
		System.out.printf(" using the Selection Sort algoritm: %,d milliseconds.\n", (endTime - startTime));
		startTime = System.currentTimeMillis();
		Arrays.sort(secondArray);
		endTime = System.currentTimeMillis();
		System.out.printf("Time to sort an array of %,d integers", arraySize);
		System.out.printf(" using the built-in Arrays.sort algoritm: %,d miliseconds.\n\n", (endTime - startTime));
	} // end of sortingArrays()

	/**
	 * This method sorts an array into increasing order, using selection sort, i.e
	 * moving the greatest elements to the end of of it.
	 *
	 * @param arr the array to be sorted.
	 */

	private static void selectionSort(int[] arr) {
		for (int lastPlace = arr.length-1; lastPlace > 0; lastPlace--) {
			// Find the largest item among arr[0], arr[1], ...,
			// arr[lastPlace], and move it into position lastPlace
			// by swapping it with the number that is currently
			// in position lastPlace.
			int maxLoc = 0; // Location of largest item seen so far.
			for (int j = 1; j <= lastPlace; j++) {
				if (arr[j] > arr[maxLoc]) {
					// Since arr[j] is bigger than the maximum we’ve seen
					// so far, j is the new location of the maximum value
					// we’ve seen so far.
					maxLoc = j;
				}
			}
			int temp = arr[maxLoc]; // Swap largest item with arr[lastPlace].
			arr[maxLoc] = arr[lastPlace];
			arr[lastPlace] = temp;
		} // end of for loop
	} // end of selectionSort()
} // end of class ArraySort (main class)
