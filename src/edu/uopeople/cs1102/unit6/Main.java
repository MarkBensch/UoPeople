package edu.uopeople.cs1102.unit6;

/**
 * Created by MarkTurnTo on 3/2/17.
 */
public class Main {
    public static void main(String[] args) {
        //with polymorphism since everything extends off sweetener
        //we can use sweetner to represent sweetNLow AND splenda
        //this allows us to preform an action that is in all object
        //we can be sure that afterTaste is in all objects because
        //we made afterTaste an abstract method though we could change
        //an attribute in sweetener to all subclasses
        Sweetener[] sweets = {new SweetNLow(), new Splenda(), new SweetNLow(), new SweetNLow(), new Splenda()};
        for(Sweetener sweet : sweets){
            sweet.afterTaste();
        }

        System.out.println();

        //if we didn't have polymorphism we would need
        //to do something like this... to achieve the same effect
        SweetNLow pink = new SweetNLow();
        Splenda yellow = new Splenda();

        //that is assign each object to it's specific data type (Splenda or SweetNLow)
        //then call the the afterTaste() method on each of these objects
        pink.afterTaste();
        yellow.afterTaste();

    }
}