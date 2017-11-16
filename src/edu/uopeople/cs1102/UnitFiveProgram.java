package edu.uopeople.cs1102;

/**
 * Assignment: Programming Assignment Unit 5
 * Class: programming 1 - Group E
 * Created by MarkTurnTo on 2/23/17.
 */
public class UnitFiveProgram {
    public static void main(String[] args) {
        double[] numberSet = {5, 7, 12, 23, 3, 2, 8, 14, 10, 5, 9, 13};
        StatCalc myStatCalc = new StatCalc();

        for(double number : numberSet){
            myStatCalc.enter(number);
        }
        System.out.println("The count of numbers in the data set is " + myStatCalc.getCount());
        System.out.println("The Mean of all the numbers is " + myStatCalc.getMean());
        System.out.println("The data set has a standard deviation of " + myStatCalc.getStandardDeviation());
    }
}
