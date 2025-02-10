/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars.sales;

import cc.cars.Car;
import cc.cars.SaleHandler;
import cc.cars.Seller;

/**
Base for common seller behaviors.

@author Will Provost
*/
public abstract class AbstractSeller
    implements Seller
{
    private SaleHandler handler;
    protected Car car;

    /**
    Tell the seller what car he's selling.
    */
    public AbstractSeller (Car car)
    {
        this.car = car;
    }

    /**
    Register the given handler for possible callback.
    */
    public void onSale (SaleHandler handler)
    {
        this.handler = handler;
    }

    /**
    Mark the car sold, and notify any registered handler of the sale.
    */
    protected void closeSale ()
    {
        car.setSold (true);
        if (handler != null)
            handler.carSold (car);
    }
}
