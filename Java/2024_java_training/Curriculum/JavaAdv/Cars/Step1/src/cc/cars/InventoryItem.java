/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
This abstract class models the behavior of an inventory item for the car
dealership.  It defines three abstract methods, and one concrete one that
calls these three for details.

@author Will Provost
*/
public abstract class InventoryItem
{
    /**
    @return The name of the item.
    */
    public abstract String getName ();

    /**
    @return The quantity of the item in stock
    */
    public abstract int getQuantity ();

    /**
    @return The total value of the inventory in this item
    */
    public abstract double getPrice ();

    /**
    The one concrete method on the class, this calls the three abstract
    methods to shape up a short report string.
    */
    public String report ()
    {
        return String.format ("%-28s%8d      %,10.2f      %,10.2f%n",
            getName (), getQuantity (), getPrice (),
                getQuantity () * getPrice ());
    }

    /**
    Get the standard header for an inventory listing.
    */
    public static String getHeader ()
    {
        return
            String.format ("%-28s%-12s%12s%16s%n",
                "Item", "Quantity", "Price", "Value") +
            String.format ("%-28s%-12s%12s%16s%n",
                "------------------------", "--------",
                "------------", "-------------");
    }
}
