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

    public enum Condition 
    { 
        EXCELLENT (1.25, "Seems to be in great shape."), 
        GOOD (1.0, "It's been well kept."), 
        FAIR (.75, "Shows its age."), 
        POOR (.5, "What a mess!"), 
        WRECK (0.0, "It's a wreck! Couldn't get it to move.");
        
        private Condition (double multiplier, String feedback)
        {
            this.multiplier = multiplier;
            this.feedback = feedback;
        }
        
        public void adjustFeedback (TestDriveResults results)
        {
            results.setPerceivedValue 
                (results.getPerceivedValue () * multiplier);
                
            if (this == WRECK)
                results.setFeedback (feedback);
            else
                results.addFeedback (feedback);
        }
        
        private double multiplier;
        private String feedback;
    };
    
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
        throws BadDataException
    {
        super (make, model, year, VIN, color, stickerPrice, horsepower,
            torque, handling);
        
        if (condition == null)
            throw new IllegalArgumentException("Condition can't be null");
        
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
        condition.adjustFeedback (results);
        return results;
    }
}

