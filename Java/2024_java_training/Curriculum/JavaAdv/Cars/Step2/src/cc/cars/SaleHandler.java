/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
Handler for sales events.

@author Will Provost
*/
@FunctionalInterface
public interface SaleHandler
{
    /**
    Called when a car has been sold.
    */
    public void carSold (Car car);
}
