package edu.uopeople.cs1102.payrollsystem;

/**
 * Created by MarkTurnTo on 2/23/17.
 */
public class FullTime extends Employee {
    private double salary;
    private double overtime;

    public FullTime(int id, String name, double salary, double overtime, Vehicle vehicle) {
        super(id, name, vehicle);
        this.salary = salary;
        this.overtime = overtime;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public double calculatePay() {
        System.out.println("Full time employee");
        return this.salary + this.overtime;
    }
}
