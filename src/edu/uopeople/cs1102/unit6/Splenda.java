package edu.uopeople.cs1102.unit6;

/**
 * Created by MarkTurnTo on 3/2/17.
 */
public class Splenda extends Sweetener {
    public void afterTaste() { //extending Sweetener means we must make a afterTaste method
        System.out.println("metallic ick with a hint of bitter.");
    }
}
