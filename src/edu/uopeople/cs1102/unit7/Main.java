package edu.uopeople.cs1102.unit7;

/**
 * Assignment: Programming Assignment Unit 7
 * Class: programming 1 - Group D
 * Created by MarkTurnTo on 3/9/17.
 */

public class Main {
    public static void main(String[] args) {
        final int numberOfRows = 3;
        final int numberOfColumns = 3;
        boolean isSquare = numberOfRows == numberOfColumns;
        int[] dataSet = {10,12,11,9,8,31,2,16,24};
        int[][] square = createMatrix(dataSet, numberOfRows, numberOfColumns);
        printMatrix(square);
        if(isSquare){
            System.out.println("Secondary Diagonal of the Square Matrix: " + calculateSecondaryDiagonal(square));

        }else{
            System.out.println("This is not a square matrix.");
        }

    }

    private static int calculateSecondaryDiagonal(int[][] square) {
        int sum = 0;
        for(int i = 0; i < square.length; i++){
            sum += square[i][square[i].length-1-i];
        }
        return sum;
    }

    /**
     * Holy crap this method is messy
     * if you have some thoughts on how to clean it up
     * please post it in the overview comments
     */
    public static int[][] createMatrix(int[] list, int rows, int columns){
        int[][] result = new int[rows][columns];
        int x = 0;
        int y = 0;
        for(int i =0; i < list.length; i++){
           if(y < columns){
               try {
                   result[x][y] = list[i];
               } catch (ArrayIndexOutOfBoundsException e){
                   result[x][y] = 0;
               }
               y++;
           } else {
               i--;
               x++;
               y=0;
           }
        }
        return result;
    }

    public static void printMatrix(int[][] matrix){
        for(int r = 0; r < matrix.length; r++) {
            for(int c= 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

}
