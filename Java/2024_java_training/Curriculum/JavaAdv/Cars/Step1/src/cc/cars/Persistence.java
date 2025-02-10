/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
This class is responsible for initializing the inventory for a dealership.
It returns hard-coded data on each run of the application.

@author Will Provost
*/
public class Persistence
{
    private static final Logger LOG =
        Logger.getLogger (Persistence.class.getName ());

    /**
    Helper method to create a car and add it to a collection.
    Catches the {@link BadDataException} and avoids adding the car
    if its data is inconsistent.
    */
    private static void addCar (List<Car> result, String make, String model,
        int year, String VIN, String color, double price,
        int horsepower, int torque, Car.Handling handling)
    {
        try
        {
            result.add (new Car (make, model, year, VIN, color, price,
                horsepower, torque, handling));
        }
        catch (BadDataException ex)
        {
            LOG.log (Level.WARNING,
                "Excluding " + VIN + " from inventory.", ex);
        }
    }

    /**
    Helper method to create a car and add it to a collection.
    Catches the {@link BadDataException} and avoids adding the car
    if its data is inconsistent.
    */
    private static void addUsedCar (List<UsedCar> result, String make,
        String model, int year, String VIN, String color, double price,
        int horsepower, int torque, Car.Handling handling,
        int odometer, UsedCar.Condition condition, double pricePaid)
    {
        try
        {
            result.add (new UsedCar (make, model, year, VIN, color, price,
                horsepower, torque, handling, odometer, condition, pricePaid));
        }
        catch (BadDataException ex)
        {
            LOG.log (Level.WARNING,
                "Excluding " + VIN + " from inventory.", ex);
        }
    }

    /**
    Load cars from persistent storage, or fall back to a hard-coded list.
    */
    public List<Car> loadCars()
    {
        List<Car> result = new ArrayList<>();

        addCar (result, "Toyota", "Prius", 2015,
            "ED9876", "Silver", 28998.99, 220, 180, Car.Handling.FAIR);
        addCar (result, "Subaru", "Outback", 2015,
            "PV9228", "Green", 25998.99, 250, 260, Car.Handling.GOOD);
        addCar (result, "Ford", "Taurus", 2014,
            "BA0091", "Black", 32499.99, 250, 320, Car.Handling.GOOD);
        addCar (result, "Saab", "9000", 2015,
            "HJ5599", "Pearl", 34498.99, 300, 320, Car.Handling.EXCELLENT);
        addCar (result, "Honda", "Accord", 2014,
            "ME3278", "Red", 29999.99, 250, 200, Car.Handling.GOOD);
        addCar (result, "Volkswagen", "Jetta TDI", 2014,
            "UI4456", "Green", 23899.99, 260, 200, Car.Handling.GOOD);
        addCar (result, "Kia", "Sonata", 2015,
            "WE9394", "White", 21999.99, 210, 160, Car.Handling.POOR);
        addCar (result, "Ford", "F-150", 2015,
            "XY1234", "Black", 28999.99, 360, 280, Car.Handling.FAIR);
        addCar (result, "Ford", "Escape Hybrid", 2014,
            "IM3110", "Silver", 23999.99, 220, 180, Car.Handling.FAIR);
        addCar (result, "Audi", "A3", 2015,
            "PU4128", "Blue", 39999.99, 280, 230, Car.Handling.EXCELLENT);

        result.get (6).setSold (true);
        return result;
    }

    /**
    Load used cars from persistent storage, or fall back to a hard-coded list.
    */
    public List<UsedCar> loadUsedCars()
    {
        List<UsedCar> result = new ArrayList<>();

        addUsedCar (result, "AMC", "Pacer", 1977,
            "CZ7821", "Blue", 1998.99, 250, 200, Car.Handling.GOOD,
            105000, UsedCar.Condition.POOR, 3000);
        addUsedCar (result, "Ford", "Pinto", 1974,
            "AR7993", "Dust", .99, 200, 180, Car.Handling.FAIR,
            2500, UsedCar.Condition.WRECK, 0);
        addUsedCar (result, "Renault", "Le Car", 1978,
            "RP5191", "Yellow", 1499.99, 220, 180, Car.Handling.GOOD,
            90000, UsedCar.Condition.FAIR, 2200);
        addUsedCar (result, "Geo", "Metro", 1991,
            "RG0504", "Midnight", 1098.99, 120, 120, Car.Handling.POOR,
            83000, UsedCar.Condition.GOOD, 3000);
        addUsedCar (result, "Ford", "El Camino", 1972,
            "WQ0227", "Blue and tan", 2098.99, 300, 250, Car.Handling.FAIR,
            140000, UsedCar.Condition.EXCELLENT, 2000);
        addUsedCar (result, "Toyota", "Prius", 2004,
            "AE5150", "Black", 8998.99, 220, 180, Car.Handling.FAIR,
            90000, UsedCar.Condition.EXCELLENT, 7000);
        addUsedCar (result, "Subaru", "Outback", 2004,
            "BN7089", "Green", 6998.99, 250, 260, Car.Handling.GOOD,
            95000, UsedCar.Condition.GOOD, 5500);
        addUsedCar (result, "Ford", "Taurus", 2003,
            "QL3314", "Gold", 9499.99, 250, 320, Car.Handling.GOOD,
            70000, UsedCar.Condition.FAIR, 7200);
        addUsedCar (result, "Saab", "9000", 2004,
            "KM0962", "Silver", 11498.99, 300, 320, Car.Handling.EXCELLENT,
            85000, UsedCar.Condition.EXCELLENT, 9500);
        addUsedCar (result, "Saturn", "Ion", 2003,
            "HL4735", "Plum", 4598.99, 250, 200, Car.Handling.FAIR,
            45000, UsedCar.Condition.FAIR, 3300);

        return result;
    }

    /**
    Load parts from persistent storage, or fall back to a hard-coded list.
    */
    public List<Part> loadParts()
    {
        List<Part> result = new ArrayList<>();

        result.add (new Part ("Brake hose", 8, 30.49));
        result.add (new Part ("Rearview mirror", 1, 54.99));
        result.add (new Part ("Distributor cap", 3, 14.99));
        result.add (new Part ("Headlight", 4, 49.99));
        result.add (new Part ("Fuse", 16, 2.99));
        result.add (new Part ("Spark plug", 16, 4.99));

        return result;
    }
}
