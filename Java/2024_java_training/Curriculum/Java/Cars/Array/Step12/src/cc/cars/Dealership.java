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
    UsedCar[] usedCars;

    /**
    The dealership relies on a separate class {@link Persistence} to 
    initialize its inventory.
    */
    public Dealership ()
    {
        Persistence persistence = new Persistence ();
            
        cars = persistence.loadCars ();
        usedCars = persistence.loadUsedCars ();
    }
    
    /**
    List all the cars on the lot, in a standard format that can be
    printed to the console, saved to a file, displayed in a window, etc.
    Work through separate collections for cars and used cars, and flag
    the latter as used.
    */
    public String listCars ()
    {
        StringBuffer result = new StringBuffer ();

        for (Car car : cars)
            result.append (String.format ("%s: %s $%,1.2f%n", 
                car.getVIN (), car.getShortName (), car.getStickerPrice()));

        for (Car car : usedCars)
            result.append (String.format ("%s: %s $%,1.2f -- USED%n", 
                car.getVIN (), car.getShortName (), car.getStickerPrice()));
        
        return result.toString ();
    }
    
    /**
    Look up a car by its VIN.
    
    @return Found car, or null if not found
    */
    public Car findCar (String VIN)
    {
        for (Car car : getAllCars ())
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
        for (Car car : getAllCars ())
            if (car.getMake ().equals (make) &&
                    (model == null || car.getModel ().equals (model)))
                return car;
        
        return null;
    }

    /**
    Activate a seller to sell the requested car.
    Sales process is conducted by the seller after this call completes.
    */
    public Seller sellCar (Car car)
    {
        return new Seller (car, .1, .15);
    }

    /**
    Get an array of all the cars -- combines new and used cars into one
    collection.
    */
    protected Car[] getAllCars ()
    {
        Car[] result = new Car[cars.length + usedCars.length];
        int r = 0;
        for (int c = 0; c < cars.length; ++c, ++r)
            result[r] = cars[c];
        for (int u = 0; u < usedCars.length; ++u, ++r)
            result[r] = usedCars[u];
        
        return result;
    }
    
}

