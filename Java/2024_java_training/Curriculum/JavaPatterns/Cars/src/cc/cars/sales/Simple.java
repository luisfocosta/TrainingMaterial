/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars.sales;

import java.util.logging.Logger;

import cc.cars.Car;
import cc.cars.UsedCar;

/**
Simple seller who offers an initial discount and then will accept
an offer that's over a concealed, best price.

@author Will Provost
*/
public class Simple
    extends AbstractSeller
{
    private static final Logger LOG =
        Logger.getLogger (Simple.class.getName());

    private double initialDiscount;
    private double bestDiscount;

    private static final double DEFAULT_INITIAL_DISCOUNT = .10;
    private static final double DEFAULT_BEST_DISCOUNT = .20;

    /**
    Tell the seller what car he's selling.  Use default discount values.
    */
    public Simple (Car car)
    {
        this (car, DEFAULT_INITIAL_DISCOUNT, DEFAULT_BEST_DISCOUNT);
    }

    /**
    Tell the seller what car he's selling.
    Set specific values for initial discount and best discount.
    */
    public Simple (Car car, double initialDiscount, double bestDiscount)
    {
        super (car);
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
        LOG.fine ("Giving initial discount of " + initialDiscount + "%.");

        if (car instanceof UsedCar used && used.getPricePaid () > result)
        {
            result = ((UsedCar) car).getPricePaid ();
            LOG.fine ("Adjusting to the price paid for the (used) car.");
        }

        return result;
    }

    /**
    Best price is sticker price minus best discount, or the price
    paid for a used car, whichever is higher.  This method will accept
    the offer price if it's at or above the best price -- no haggling.
    */
    public double willSellAt (double offerPrice)
    {
        boolean acceptable =
            offerPrice >= car.getStickerPrice () * (1.0 - bestDiscount);
        boolean lowerThanPaid = (car instanceof UsedCar used) &&
            offerPrice < used.getPricePaid ();

        if (acceptable)
            LOG.fine ("Offer is above best discount of " + bestDiscount + "%.");
        if (lowerThanPaid)
            LOG.fine ("Offer is below price paid for (used) car.");

        if (acceptable && !lowerThanPaid)
            closeSale ();

        return acceptable && !lowerThanPaid
            ? offerPrice
            : getAskingPrice ();
    }
}
