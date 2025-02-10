/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars.sales;

import java.util.logging.Logger;

import cc.cars.Car;
import cc.cars.UsedCar;

/**
A hard-nosed seller who will negotiate down towards your offer,
but only after you've offered something he could afford to sell at.
Until then, he stands on his asking price.

@author Will Provost
*/
public class HardNosed
    extends AbstractSeller
{
    private static final Logger LOG =
        Logger.getLogger (HardNosed.class.getName());

    /**
    This seller will come down by 25% of the gap between offers.
    */
    private double comeDown = .20;

    /**
    Remembers latest offer so we can negotiate from there.
    */
    private double latestOffer;

    private static final double INITIAL_DISCOUNT = .10;
    private static final double BEST_DISCOUNT = .15;

    /**
    Tell the seller what car he's selling.
    */
    public HardNosed (Car car)
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
    paid for a used car, whichever is higher.  This seller will stand on
    his previous price until the offer is at least as good as his best price
    -- then he will start bargaining down by a small increment.
    */
    public double willSellAt (double offerPrice)
    {
        if (offerPrice >= latestOffer)
        {
            LOG.fine("Buyer's offer is acceptable -- sell the car.");
            closeSale ();
            return offerPrice;
        }

        if (offerPrice < bestPrice ())
        {
            LOG.fine("Buyer's offer is below my best price; not moving.");
            return latestOffer;
        }

        double down = comeDown * (latestOffer - offerPrice);
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
