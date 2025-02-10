/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private static final Logger LOG =
        Logger.getLogger(Dealership.class.getName());

    Persistence persistence = new Persistence ();
    List<Car> cars;
    List<UsedCar> usedCars;
    List<Part> parts;

    /**
    The dealership relies on a separate class {@link Persistence} to
    initialize its inventory.
    */
    public Dealership ()
    {
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
        return getAllCars().collect(Collectors.mapping
            (car -> String.format ("%s: %s%s %s%n",
                car.getVIN (), car.getShortName (),
                car instanceof UsedCar ? " -- USED --" : "",
                car.isSold ()
                    ? "SOLD"
                    : String.format ("$%,1.2f", car.getStickerPrice())),
             Collectors.joining()));
    }

    /**
    Look up a car by its VIN.

    @return Found car, or null if not found
    */
    public Car findCar (String VIN)
    {
        Optional<? extends Car> found = getAllCars ()
            .filter (car -> car.getVIN().equals(VIN)).findFirst ();
        return found.isPresent () ? found.get() : null;
    }

    /**
    Find a car by make and model.

    @return Found car, or null if not found
    */
    public Car findCar (String make, String model)
    {
        Optional<? extends Car> found = getAllCars ()
            .filter (car -> car.getMake ().equals (make) &&
                (model == null || car.getModel ().equals (model)))
            .findFirst ();
        return found.isPresent () ? found.get() : null;
    }

    /**
    Activate a seller to sell the requested car.
    Sales process is conducted by the seller after this call completes.
    */
    public Seller sellCar (Car car)
    {
        Seller seller = null;
        String type = System.getProperty("cc.cars.Seller.type");

        if (type == null)
        {
            type = "cc.cars.sales." +
                ((System.currentTimeMillis () / 10) % 2 == 0
                    ? "Standard" : "HardNosed");
            LOG.config ("No seller type configured; randomly chose " + type);
        }
        else
            LOG.config ("Configuration calls for seller of type " + type);

        try
        {
            Class<?> sellerClass = Class.forName (type);
            Class<?>[] params = { Car.class };
            Constructor<?> ctor = sellerClass.getConstructor (params);
            Object[] args = { car };
            Object obj = ctor.newInstance (args);
            seller = (Seller) obj;
        }
        catch (ReflectiveOperationException ex)
        {
            LOG.log (Level.SEVERE,
                "Couldn't instantiate configured seller type: " + type, ex);
        }

        seller.onSale (soldCar ->
            {
                if (soldCar instanceof UsedCar)
                    persistence.saveUsedCars (usedCars);
                else
                    persistence.saveCars (cars);
            });

        return seller;
    }

    /**
    Produce a formatted list of total inventory, including valuation.
    */
    public String listInventory ()
    {
        return InventoryItem.getHeader () +
            getFullInventory().collect (Collectors.mapping
                (item -> item.report (), Collectors.joining ()));
    }

    /**
    Get an stream of all the cars -- combines new and used cars into one
    collection.
    */
    protected Stream<Car> getAllCars ()
    {
        return Stream.concat (cars.stream (), usedCars.stream ());
    }

    /**
    Get an stream of the full inventory -- combines cars, used cars, and
    parts into one collection.
    */
    protected Stream<InventoryItem> getFullInventory ()
    {
        return Stream.concat (getAllCars(), parts.stream ());
    }
}
