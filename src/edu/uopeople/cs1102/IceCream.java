package edu.uopeople.cs1102;

/**
 * Assignment: Programming Assignment Unit 3
 * Class: programming 1 - Group D
 * Created by MarkTurnTo on 2/10/17.
 */

public class IceCream {
    public static void main(String[] arguments){
        String datFile = "icecream.dat"; // initial location of data file
        float strawberry = 0;
        float total = 0;
        float percent;
        String flavor = "";

        while(true){ // loop to get a valid file
            try{ //try and open file using path in datFile
                TextIO.readFile(datFile);
                break; //if file is readable break out of loop
            } catch (IllegalArgumentException e) { //file not found
                System.out.println(datFile + " does not exist");
                System.out.println("Please enter proper file location of ice cream data file");
                datFile = TextIO.getln(); // have the user manually enter the file location
            }
        }

        while(!TextIO.eof()){ //loop until end of file
            flavor = TextIO.getln(); // read a line from the file
            if(flavor.equals("Strawberry")){ // check is line Strawberry?
                strawberry++;
            }
            total++;
        }

        percent = (strawberry/total)*100; //do math

        System.out.println("A total of " + (int)total + " ice creams were sold.");
        System.out.println( (int)strawberry + " of which were strawberry.");
        System.out.println("This was " + percent + "% of the total ice creams sold");
    }
}
