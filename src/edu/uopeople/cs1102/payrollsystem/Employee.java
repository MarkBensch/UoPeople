package edu.uopeople.cs1102.payrollsystem;

/**
 * Created by MarkTurnTo on 2/23/17.
 */
public abstract class Employee {
    private int empId;
    private String name;
    private Vehicle vehicle;

    public Employee() {
        System.out.println();
        empId = 0;
        name = "";
    }

    public Employee(int empId, String name, Vehicle vehicle) {
        System.out.println("");
        this.empId = empId;
        this.name = name;
        this.vehicle = vehicle;
    }

    public Employee(int empId, String name, String pPlate, String pColor) {
        this.empId = empId;
        this.name = name;
        this.vehicle = new Vehicle(pPlate,pColor);
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract double calculatePay();
}
