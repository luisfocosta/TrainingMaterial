/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
This class encapsulates a Seller for the car dealership.
A seller is called into action on the user's request, is told what car to 
sell, and then asks for a price related to the sticker price of the car.
Then the user can haggle, by offering to buy at various prices.

@author Will Provost
*/
public class Seller
{
    private Car car;
    private double initialDiscount;
    private double bestDiscount;
    
    private static final double DEFAULT_INITIAL_DISCOUNT = .10;
    private static final double DEFAULT_BEST_DISCOUNT = .20;

    /**
    Tell the seller what car he's selling.  Use default discount values.
    */
    public Seller (Car car)
    {
        this (car, DEFAULT_INITIAL_DISCOUNT, DEFAULT_BEST_DISCOUNT);
    }
    
    /**
    Tell the seller what car he's selling.  
    Set specific values for initial discount and best discount.
    */
    public Seller (Car car, double initialDiscount, double bestDiscount)
    {
        this.car = car;
        this.initialDiscount = initialDiscount;
        this.bestDiscount = bestDiscount;
    }
    
    /**
    Asking price is sticker price minus initial discount, or the price
    paid for a used car, whichever is higher.
    */
    public double getAskingPrice ()
    {
        double result = car.getStickerPrice () * (1.0 - initialDiscount);
        if (car instanceof UsedCar used && used.getPricePaid () > result)
            result = ((UsedCar) car).getPricePaid ();
            
        return result;
    }
    
    /**
    Best price is sticker price minus best discount, or the price
    paid for a used car, whichever is higher.  This method will accept
    the offer price if it's at or above the best price -- no haggling.
    */
    public double willSellAt (double offerPrice)
    {
        return offerPrice >= car.getStickerPrice () * (1.0 - bestDiscount) &&
            (!(car instanceof UsedCar) || 
                offerPrice >= ((UsedCar) car).getPricePaid ())
            ? offerPrice
            : getAskingPrice ();
    }
}
