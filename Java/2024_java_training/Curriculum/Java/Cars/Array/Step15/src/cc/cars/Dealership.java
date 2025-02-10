/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

import cc.cars.sales.HardNosed;
import cc.cars.sales.Simple;
import cc.cars.sales.Standard;

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
    Part[] parts;

    /**
    The dealership relies on a separate class {@link Persistence} to 
    initialize its inventory.
    */
    public Dealership ()
    {
        Persistence persistence = new Persistence ();
            
        cars = persistence.loadCars ();
        usedCars = persistence.loadUsedCars ();
        parts = persistence.loadParts ();
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
        String type = System.getProperty("cc.cars.Seller.type");
        
        if (type == null)
            type = (System.currentTimeMillis () / 10) % 2 == 0
                ? "Standard" : "HardNosed";
        
        switch(type)
        {
        case "Simple": return new Simple(car);
        case "Standard": return new Standard(car);
        case "HardNosed": return new HardNosed(car);
        }

        return null;
    }

    /**
    Produce a formatted list of total inventory, including valuation.
    */
    public String listInventory ()
    {
        StringBuffer result = new StringBuffer (InventoryItem.getHeader ());
        for (InventoryItem item : parts)
            result.append ("report() NYI\n");
        
        return result.toString ();
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
    
    /**
    Get an array of the full inventory -- combines cars, used cars, and
    parts into one collection.
    */
    /*protected InventoryItem[] getFullInventory ()
    {
        InventoryItem[] result = 
            new InventoryItem[cars.length + usedCars.length + parts.length];
        int r = 0;
        for (int c = 0; c < cars.length; ++c, ++r)
            result[r] = cars[c];
        for (int u = 0; u < usedCars.length; ++u, ++r)
            result[r] = usedCars[u];
        for (int p = 0; p < parts.length; ++p, ++r)
            result[r] = parts[p];
        
        return result;
    }*/
}

