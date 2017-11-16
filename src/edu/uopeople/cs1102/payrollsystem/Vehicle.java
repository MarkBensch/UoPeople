package edu.uopeople.cs1102.payrollsystem;

/**
 * Created by MarkTurnTo on 2/23/17.
 */
public class Vehicle {
    private String plateNumber;
    private String color;

    public Vehicle(String plateNumber, String color) {
        this.plateNumber = plateNumber;
        this.color = color;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
