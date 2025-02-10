/*
Copyright 2004-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
This interface encapsulates a Seller for the car dealership.
A seller is called into action on the user's request, is told what car to
sell, and then asks for a price related to the sticker price of the car.
Then the user can haggle, by offering to buy at various prices.
The initial asking price and how the seller negotiates are determined
per implementation.

@author Will Provost
*/
public interface Seller
{
    /**
    Call this method first, to get the asking price on the car.
    */
    public double getAskingPrice ();

    /**
    Call this as many times as you like, offering your own prices.
    The seller will respond with a counter-offer, which may be his
    previous offer, a lower offer, or a number matching your offer -- in
    that last case, you have a deal!
    */
    public double willSellAt (double offer);
}
