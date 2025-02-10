/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars.sales;

import java.util.logging.Logger;

import cc.cars.Car;
import cc.cars.UsedCar;

/**
A standard seller who will negotiate down towards your offer,
but who runs out of patience after three such counter-offers.

@author Will Provost
*/
public class Standard
    extends AbstractSeller
{
    private static final Logger LOG =
        Logger.getLogger (Standard.class.getName());

    /**
    This seller will come down by 30% of the gap between offers.
    */
    private double comeDown = .30;

    /**
    Remembers latest offer so we can negotiate from there.
    */
    private double latestOffer;

    /**
    Patience factor is 3 -- after this many calls to
    {@link #willSellAt willSellAt}, the seller will stop negotiating.
    */
    private int patience = 3;

    private static final double INITIAL_DISCOUNT = .10;
    private static final double BEST_DISCOUNT = .20;

    /**
    Tell the seller what car he's selling.
    */
    public Standard (Car car)
    {
        super (car);
        latestOffer = car.getStickerPrice ();
    }

    /**
    Asking price is sticker price minus initial discount, or the price
    paid for a used car, whichever is higher.
    */
    public double getAskingPrice ()
    {
        double best = bestPrice();
        double discounted = car.getStickerPrice () * (1.0 - INITIAL_DISCOUNT);
        LOG.fine("Asking the higher of best price (" + best +
            ") and discounted price (" + discounted + ")");
        return latestOffer = Math.max (best, discounted);
    }

    /**
    Best price is sticker price minus best discount, or the price
    paid for a used car, whichever is higher.  This seller will start
    negotiating once the offer is no farther below best price than the
    seller's own latest offer is above it.  Then he'll work down by a
    percentage of the gap.  The seller will not haggle forever --
    the patience count goes down with each call to this method, and
    when the patience is exhausted, the seller will stick to his price.
    */
    public double willSellAt (double offerPrice)
    {
        if (offerPrice >= latestOffer)
        {
            LOG.fine("Buyer's offer is acceptable -- sell the car.");
            closeSale ();
            return offerPrice;
        }

        // Check and decrement patience and stick at latest offer if
        //   we're tired of negotiating:
        if (patience == 0)
        {
            LOG.fine("I'm out of patience!");
            return latestOffer;
        }
        else
            --patience;

        // Fix a target price that's either the offer or a number that's
        //   exactly as far below my best price as my latest offer is above
        //   my best price:
        double lower = Math.max (offerPrice, 2 * bestPrice () - latestOffer);

        // Come down by a percentage of the gap:
        double down = comeDown * (latestOffer - lower);
        latestOffer -= down;
        LOG.fine("Coming down by " + down + " to " + latestOffer);
        return latestOffer;
    }

    /**
    This helper method captures best price that can be given, which is
    the sticker price minus the best discount, or the price paid for a
    used car, whichever is greater.
    */
    protected double bestPrice ()
    {
        double result = car.getStickerPrice () * (1.0 - BEST_DISCOUNT);

        return (car instanceof UsedCar used && used.getPricePaid () > result)
            ? ((UsedCar) car).getPricePaid ()
            : result;
    }
}
