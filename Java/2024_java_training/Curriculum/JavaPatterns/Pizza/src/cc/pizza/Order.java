/*
Copyright 2012 Max Rahder.
Used by permission.
*/

package cc.pizza;

/**
An order for one or more pizzas, with a customer name and a
{@link Specification}.

@author Max Rahder
*/
public class Order
{

    public final Specification specification;
    public final String name;

    /**
    Provide the customer name and a series of ingredients;
    we create the {@link Specification}.
    */
    public Order (String name, Ingredient... ingredients)
    {
        this (name, Specification.get (ingredients));
    }

    /**
    Provide the customer name and a {@link Specification}.
    */
    public Order (String name, Specification specification)
    {
        super ();

        this.specification = specification;
        this.name = name;
    }
}
