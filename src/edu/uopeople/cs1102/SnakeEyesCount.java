package edu.uopeople.cs1102;

/**
 * Assignment: Programming Assignment Unit 2
 * Class: programming 1 - Group E
 * Created by MarkTurnTo on 2/2/17.
 */
public class SnakeEyesCount {
    public static void main(String[] arguments){

        int count = 0;
        int die1, die2;

        while(true){
            die1 = (int) (Math.random()*6) + 1;
            die2 = (int) (Math.random()*6) + 1;
            count++;

            if (die1 == 1 && die2 == 1) {
                break;

            }
        }
        System.out.println("It took " + count + " roll(s) to get Snake Eyes");

        /**
         * Challenge Portion of the game
         * using a for loop to simulate running your program 1000 times
         * and calculate the average number of rolls required to get snake eyes …
         * what is the average number of rolls?
         */
        int rolls = 1000;
        int sum = 0;
        for(int rollCounter = 1; rollCounter <= rolls; rollCounter++){
            int chalCount = 0;
            int chalDie1, chalDie2;

            while(true){
                chalDie1 = (int) (Math.random()*6) + 1;
                chalDie2 = (int) (Math.random()*6) + 1;
                chalCount++;

                if (chalDie1 == 1 && chalDie2 == 1) {
                    break;

                }
            }
            sum += chalCount;
        }
        System.out.println("The average number of rolls before rolling Snake Eyes is " + sum/rolls);


    }
}
