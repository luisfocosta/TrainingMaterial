/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
This class is responsible for initializing the inventory for a dealership.
It returns hard-coded data on each run of the application.

@author Will Provost
*/
public class Persistence
{
    /**
    Primes the inventory with cars, used cars, and parts.
    */
    public Car[] loadCars()
    {
        Car[] result = new Car[10];
        
        result[0] = new Car ("Toyota", "Prius", 2015,
            "ED9876", "Silver", 28998.99, 220, 180, Car.Handling.FAIR);
        result[1] = new Car ("Subaru", "Outback", 2015,
            "PV9228", "Green", 25998.99, 250, 260, Car.Handling.GOOD);
        result[2] = new Car ("Ford", "Taurus", 2014,
            "BA0091", "Black", 32499.99, 250, 320, Car.Handling.GOOD);
        result[3] = new Car ("Saab", "9000", 2015,
            "HJ5599", "Pearl", 34498.99, 300, 320, Car.Handling.EXCELLENT);
        result[4] = new Car ("Honda", "Accord", 2014,
            "ME3278", "Red", 29999.99, 250, 200, Car.Handling.GOOD);
        result[5] = new Car ("Volkswagen", "Jetta TDI", 2014,
            "UI4456", "Green", 23899.99, 260, 200, Car.Handling.GOOD);
        result[6] = new Car ("Kia", "Sonata", 2015,
            "WE9394", "White", 21999.99, 210, 160, Car.Handling.POOR);
        result[7] = new Car ("Ford", "F-150", 2015,
            "XY1234", "Black", 28999.99, 360, 280, Car.Handling.FAIR);
        result[8] = new Car ("Ford", "Escape Hybrid", 2014,
            "IM3110", "Silver", 23999.99, 220, 180, Car.Handling.FAIR);
        result[9] = new Car ("Audi", "A3", 2015,
            "PU4128", "Blue", 39999.99, 280, 230, Car.Handling.EXCELLENT);
        
        return result;
    }

    public UsedCar[] loadUsedCars()
    {
        UsedCar[] result = new UsedCar[10];
        
        result[0] = new UsedCar ("AMC", "Pacer", 1977,
            "CZ7821", "Blue", 1998.99, 250, 200, Car.Handling.GOOD, 
            105000, UsedCar.Condition.POOR, 1000);
        result[1] = new UsedCar ("Ford", "Pinto", 1974,
            "AR7993", "Dust", .99, 200, 180, Car.Handling.FAIR, 
            2500, UsedCar.Condition.WRECK, 0);
        result[2] = new UsedCar ("Renault", "Le Car", 1978,
            "RP5191", "Yellow", 1499.99, 220, 180, Car.Handling.GOOD, 
            90000, UsedCar.Condition.FAIR, 1350);
        result[3] = new UsedCar ("Geo", "Metro", 1991,
            "RG0504", "Midnight", 1098.99, 120, 120, Car.Handling.POOR, 
            83000, UsedCar.Condition.GOOD, 500);
        result[4] = new UsedCar ("Ford", "El Camino", 1972,
            "WQ0227", "Blue and tan", 2098.99, 300, 250, Car.Handling.FAIR, 
            140000, UsedCar.Condition.EXCELLENT, 1500);
        result[5] = new UsedCar ("Toyota", "Prius", 2004,
            "AE5150", "Black", 8998.99, 220, 180, Car.Handling.FAIR, 
            90000, UsedCar.Condition.EXCELLENT, 7000);
        result[6] = new UsedCar ("Subaru", "Outback", 2004,
            "BN7089", "Green", 6998.99, 250, 260, Car.Handling.GOOD, 
            95000, UsedCar.Condition.EXCELLENT, 5500);
        result[7] = new UsedCar ("Ford", "Taurus", 2003,
            "QL3314", "Gold", 9499.99, 250, 320, Car.Handling.GOOD, 
            70000, UsedCar.Condition.EXCELLENT, 7200);
        result[8] = new UsedCar ("Saab", "9000", 2004,
            "KM0962", "Silver", 11498.99, 300, 320, Car.Handling.EXCELLENT, 
            85000, UsedCar.Condition.EXCELLENT, 9500);
        result[9] = new UsedCar ("Saturn", "Ion", 2003,
            "HL4735", "Plum", 4598.99, 250, 200, Car.Handling.FAIR, 
            45000, UsedCar.Condition.EXCELLENT, 3300);
        
        return result;
    }

    public Part[] loadParts()
    {
        Part[] result = new Part[6];
        
        result[0] = new Part ("Brake hose", 8, 30.49);
        result[1] = new Part ("Rearview mirror", 1, 54.99);
        result[2] = new Part ("Distributor cap", 3, 14.99);
        result[3] = new Part ("Headlight", 4, 49.99);
        result[4] = new Part ("Fuse", 16, 2.99);
        result[5] = new Part ("Spark plug", 16, 4.99);
        
        return result;
    }
}
