/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
This class represents the dealership as a whole.
It includes collections of cars, used cars, and parts as its inventory.
It can provide formatted lists of cars or total inventory, find a car by
make and model, and can initiate a sales process by activating a
seller.

@author Will Provost
*/
public class Dealership
{
    Car[] cars;

    /**
    The dealership relies on a separate class {@link Persistence} to 
    initialize its inventory.
    */
    public Dealership ()
    {
        Persistence persistence = new Persistence ();
            
        cars = persistence.loadCars ();
    }
    
    /**
    List all the cars on the lot, in a standard format that can be
    printed to the console, saved to a file, displayed in a window, etc.
    Work through separate collections for cars and used cars, and flag
    the latter as used.
    */
    public String listCars ()
    {
        String result = "";

        for (Car car : cars)
            result += String.format 
                ("%s: %s%n", car.getVIN (), car.getShortName ());

        return result;
    }
    
    /**
    Look up a car by its VIN.
    
    @return Found car, or null if not found
    */
    public Car findCar (String VIN)
    {
        for (Car car : cars)
            if (car.getVIN ().equals (VIN))
                return car;
        
        return null;
    }
    
    /**
    Find a car by make and model.
    
    @return Found car, or null if not found
    */
    public Car findCar (String make, String model)
    {
        for (Car car : cars)
            if (car.getMake ().equals (make) &&
                    (model == null || car.getModel ().equals (model)))
                return car;
        
        return null;
    }

}

