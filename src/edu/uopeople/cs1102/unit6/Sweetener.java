package edu.uopeople.cs1102.unit6;

/**
 * Created by MarkTurnTo on 3/2/17.
 */
public abstract class Sweetener {  // think of the abstract class as a blueprint to the subclasses
    /*
    this way we force all classes that extend
    Sweetener to have this method. Also
    each subclass will have a different
    implementation of how aftertaste works.
     */
    public abstract void afterTaste();
}
