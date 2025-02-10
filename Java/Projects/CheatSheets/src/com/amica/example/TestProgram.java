package com.amica.example;

public class TestProgram {

    public static void main(String[] args){
        Employee andreCIS = new Employee("Andre", "Dvorak", 1, "CIS");
        //andreCIS.setDepartment("Accounting");
        System.out.println(andreCIS.toString());

        Employee andreAccounting = new Employee("Andre", "Dvorak", 1, "Accounting");
        System.out.println("Andre CIS equals Andre Accounting? " + andreAccounting.equals(andreCIS));

        Employee emptyEmployee = new Employee();
        try {
            System.out.println(emptyEmployee.getFullName());
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
