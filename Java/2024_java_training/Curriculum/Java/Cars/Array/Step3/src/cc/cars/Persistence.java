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
}
