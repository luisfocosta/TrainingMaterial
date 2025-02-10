/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
This class encapsulates a used car for the car dealership.
It extends and modifies behaviors on the base class {@link Car}.

@author Will Provost
*/
public class UsedCar
    extends Car
{
    private int odometer;
    private Condition condition;
    private double pricePaid;

    public enum Condition { EXCELLENT, GOOD, FAIR, POOR, WRECK };
    
    /**
    Build a used car by providing all of the parameters
    for a car, plus the odometer reading, condition, and price paid by
    the dealership for it.
    Like objects of the base type, used cars are immutable objects.
    */
    public UsedCar
    (
      String make,
      String model,
      int year,
      String VIN,
      String color,
      double stickerPrice,
      int horsepower,
      int torque,
      Handling handling,
      int odometer,
      Condition condition,
      double pricePaid
    )
    {
        super (make, model, year, VIN, color, stickerPrice, horsepower,
            torque, handling);
        
        this.odometer = odometer;
        this.condition = condition;
        this.pricePaid = pricePaid;
    }

    /**
    Accessor for the <code>odometer</code> property.
    */
    public int getOdometer ()
    {
        return odometer;
    }

    /**
    Accessor for the <code>condition</code> property.
    */
    public Condition getCondition ()
    {
        return condition;
    }

    /**
    Accessor for the <code>pricePaid</code> property.
    */
    public double getPricePaid ()
    {
        return pricePaid;
    }

    /**
    Used cars introduce a range of new outcomes to the test drive, based
    on their condition.  We first call the superclass method, and then
    amend the results object, by either adding or replacing the feedback
    and possibly modifying perceived value.
    */
    @Override
    public TestDriveResults testDrive ()
    {
        TestDriveResults results = super.testDrive ();

        if (condition.equals (Condition.EXCELLENT))
        {
            results.addFeedback ("Seems to be in great shape.");
            results.setPerceivedValue (results.getPerceivedValue () * 1.25);
        }
        else if (condition.equals (Condition.GOOD))
        {
            results.addFeedback ("It's been well kept.");
        }
        else if (condition.equals (Condition.FAIR))
        {
            results.addFeedback ("Shows its age.");
            results.setPerceivedValue (results.getPerceivedValue () * .75);
        }
        else if (condition.equals (Condition.POOR))
        {
            results.addFeedback ("What a mess!");
            results.setPerceivedValue (results.getPerceivedValue () / 2);
        }
        else
        {
            results.setFeedback ("It's a wreck! Couldn't get it to move.");
            results.setPerceivedValue (0);
        }

        return results;
    }
}

