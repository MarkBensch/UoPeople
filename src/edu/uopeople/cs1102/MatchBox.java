package edu.uopeople.cs1102;

/**
 * Assignment: Programming Assignment Unit 6
 * Class: programming 1 - Group D
 * Created by MarkTurnTo on 3/2/17.
 */
public class MatchBox extends Box {
    private double weight;

    public MatchBox(double w, double h, double d){
        super(w,h,d);
    }

    @Override
    public void getVolume() {
        System.out.println("width of edu.uopeople.cs1102.MatchBox is " + width);
        System.out.println("height of edu.uopeople.cs1102.MatchBox is " + height);
        System.out.println("depth of edu.uopeople.cs1102.MatchBox is " + depth);
        calculateWeight();
        super.getVolume();
    }

    private void calculateWeight() {
        System.out.println("weight of edu.uopeople.cs1102.MatchBox is " + (width * height * depth) * .03611 +" lbs/cubic inch");
    }
}
