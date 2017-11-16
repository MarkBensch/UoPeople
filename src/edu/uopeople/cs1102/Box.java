package edu.uopeople.cs1102;

/**
 * Assignment: Programming Assignment Unit 6
 * Class: programming 1 - Group D
 * Created by MarkTurnTo on 3/2/17.
 */
public class Box {
    double width;
    double height;
    double depth;

    public Box(){}
    public Box(double width, double height, double depth ){
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public void getVolume(){
        System.out.println("volume is: " + (width * height * depth));
    }
}
