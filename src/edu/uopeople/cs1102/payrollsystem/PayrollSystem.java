package edu.uopeople.cs1102.payrollsystem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by MarkTurnTo on 2/23/17.
 */
public class PayrollSystem {
    public static void main(String[] arguments) {
        ArrayList<Employee> arrEmp = new ArrayList<Employee>();
        String varCont = "N";
        byte menuOption = 0;
        do{
            menuOption = showMenu();
            switch(menuOption){
                case 1:
                    FullTime ft;
                    ft = readNewFullTime();
                    addEmployee(arrEmp, ft);
                    break;
                case 2:
                    PartTime pt;
                    pt = readNewPartTime();
                    addEmployee(arrEmp,pt);
                    break;
                case 3:
                    calcPayroll(arrEmp);
                    break;
                default:
                    break;
            }
        } while(menuOption != 4);

    }

    public static FullTime readNewFullTime(){

        int id = 0;
        String name = null;
        double sal = 0.0;
        double hourAndHalf = 0.0;
        Scanner kbd = new Scanner(System.in);

        System.out.print("Enter Id: ");
        id = kbd.nextInt();
        System.out.print("\nEnter Name: ");
        name = kbd.next();
        System.out.print("\nEnter Salary: ");
        sal = kbd.nextDouble();
        System.out.print("\nEnter Bonus: ");
        hourAndHalf = kbd.nextDouble();

        FullTime ft1 = null;
        ft1 = new FullTime(id,name,sal, hourAndHalf,getVehicle());

        return ft1;
    }

    public static PartTime readNewPartTime(){
        int id = 0;
        String name = null;
        double rate = 0.0;
        double hoursWorked = 0.0;
        Scanner kbd = new Scanner(System.in);

        System.out.print("Enter Id: ");
        id = kbd.nextInt();
        System.out.print("\nEnter Name: ");
        name = kbd.next();
        System.out.print("\nEnter Hourly Rate: ");
        rate = kbd.nextDouble();
        System.out.print("\nEnter Number of Hours Worked: ");
        hoursWorked = kbd.nextDouble();

        Vehicle v1 = getVehicle();
        PartTime pt1 = null;
        pt1 = new PartTime(id, name, rate, hoursWorked, v1);

        return pt1;
    }

    public static void addEmployee(ArrayList<Employee> pArrEmp, Employee pEmp ){
        pArrEmp.add(pEmp);
    }

    public static void calcPayroll(ArrayList<Employee> pArrEmp){
        double totalCompanyPay = 0.0;
        double individualPay;

        for(int i=0; i < pArrEmp.size(); i++){  //this should really be a for-each
            System.out.println("\n***************************************\n");
            individualPay = pArrEmp.get(i).calculatePay();
            Vehicle v = pArrEmp.get(i).getVehicle();
            String hasVehicle;

            if(v == null)
                hasVehicle = "No";
            else
                hasVehicle = "Yes";

            System.out.println("Employee Name: " + pArrEmp.get(i).getName());
            System.out.println("has Vehicle: "+ hasVehicle);

            if(v != null) {
                System.out.println("Plate Number: " + v.getPlateNumber());
                System.out.println("Color: " + v.getColor());
            }

            System.out.println("Take Home Pay: " + individualPay);

            totalCompanyPay = totalCompanyPay + individualPay;
        }
        System.out.println("\n------------------------------\nTotal Payroll of the company: "
            + totalCompanyPay + "\n-------------------------------");

    }

    public static byte showMenu(){
        byte menuOption = 0;
        Scanner kbd = new Scanner(System.in);

        System.out.println(""
            + "/* **************************************/"
            + "\n/* 1. Add FullTime                      */"
            + "\n/* 2. Add PartTime                      */"
            + "\n/* 3. Calculate Payroll                 */"
            + "\n/* 4. Exit                              */"
            + "\n/* **************************************/");
        System.out.print("Input: ");
        menuOption = kbd.nextByte();

        return menuOption;
    }

    public static Vehicle getVehicle(){
        Scanner kbd = new Scanner(System.in);
        String hasVehicle = "N";

        System.out.print("\nDoes this employee hav a vehicle? Y/N: ");
        hasVehicle = kbd.next();

        if(hasVehicle.equalsIgnoreCase("Y")){
            System.out.print("\nEnter plate number: ");
            String auxPlate = kbd.next();
            System.out.print("\nEnter vehicle color: ");
            String auxColor = kbd.next();
            return new Vehicle(auxPlate, auxColor);
        } else {
            return null;
        }

    }
}

