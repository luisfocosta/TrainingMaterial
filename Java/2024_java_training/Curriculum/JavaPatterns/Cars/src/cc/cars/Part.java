/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
This JavaBean encapsulates a car part for the car dealership.

@author Will Provost
*/
public class Part
    extends InventoryItem
{
    private String name;
    private int inStock;
    private double price;

    /**
    Build a part by providing name, quantity in stock, and price per unit.

    @param name Name of the part
    @param inStock Quantity of this part in stock
    @param price Price per unit in dollars
    */
    public Part (String name, int inStock, double price)
    {
        this.name = name;
        this.inStock = inStock;
        this.price = price;
    }

    /**
    Accessor for the name property.  Double-duty for InventoryItem interface.
    */
    public String getName ()
    {
        return name;
    }

    /**
    Accessor for the inStock property.
    */
    public int getInStock ()
    {
        return inStock;
    }

    /**
    Accessor for the price property.
    */
    public double getPrice ()
    {
        return price;
    }

    /**
    InventoryItem's quantity is just our in-stock amount.
    */
    public int getQuantity ()
    {
        return inStock;
    }
}
