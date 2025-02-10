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
        Car[] result = new Car[2];
        
        result[0] = new Car ("Toyota", "Prius", 2015,
            "ED9876", "Silver", 28998.99, 220, 180, Car.Handling.FAIR);
        result[1] = new Car ("Subaru", "Outback", 2015,
            "PV9228", "Green", 25998.99, 250, 260, Car.Handling.GOOD);
        
        return result;
    }
}
