package edu.uopeople.cs1102.payrollsystem;

/**
 * Created by MarkTurnTo on 2/23/17.
 */
public class PartTime extends Employee {
    private double rate;
    private double hoursWorked;

    public PartTime(int empId, String name, double rate, double hoursWorked, Vehicle vehicle) {
        super(empId, name, vehicle);
        this.rate = rate;
        this.hoursWorked = hoursWorked;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double calculatePay() {
        System.out.println("Part time employee");
        return this.hoursWorked * this.rate;
    }
}
