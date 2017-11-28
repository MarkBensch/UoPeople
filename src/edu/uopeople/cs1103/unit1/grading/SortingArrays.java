package edu.uopeople.cs1103.unit1.grading;


import java.util.*;

public class SortingArrays {
	
	int maxArraySize=1000; //The maximum array size that can be computed
	
	int[]firstArray= new int[maxArraySize]; // declaring the first array can have a max capcity of 10000
	int[]secondArray= new int[maxArraySize];// repeating same step for the second array
	
  public SortingArrays() {
  	//Constuctor 
  	for (int i=0; i < firstArray.length; i++) {
  		//creates two arrays with same numbers using the the random algorithm that was given to us. 
  		firstArray[i]=(int)(Integer.MAX_VALUE*Math.random());
  		secondArray[i]=firstArray[i];		
  	}
  	
  	long startTimefirstArray=System.currentTimeMillis(); //Computes time for sorting
  	selectionSort(firstArray); 
  	long runTimefirstArray= System.currentTimeMillis()-startTimefirstArray;
  	
  	long startTimesecondArray=System.currentTimeMillis();
  	selectionSort(secondArray);
  	long runtimesecondArray=System.currentTimeMillis()-startTimesecondArray;
  	
  	System.out.println("selectionSort(sec):"+runTimefirstArray/1000.0);//Print results for the 
  }
  
  public void selectionSort(int[] array){
    // selection sort algorithm
    for(int i = 0; i < array.length; i++){
      for(int j = i+1; j < array.length; j++){
        if(array[i] > array[j]){
          int temp = array[i];
          array[i] = array[j];
          array[j] = temp;
        }
      }
    }
  }
  
  public void insertionSort(int[] array){
    for(int i = 0; i < array.length; i++){
      int key = array[i];
      int j = i - 1;
      while(j >= 0 && array[j] > key){
        array[j+1] = array[j];
        j--;
        // shifting every value to the left until array[i] is greater than or equal to array[j]
      }
      array[j+1] = key;
    }
  }
}

