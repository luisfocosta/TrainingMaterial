/*
Copyright 2012 Max Rahder.
Used by permission.
*/

package cc.pizza;

import java.util.ArrayList;
import java.util.List;

/**
Test for the pizza-and-ingredients system.

@author Max Rahder
*/
public class OrderSomePizza
{
    private static List<Order> orders = new ArrayList<Order>();

    /**
    Creates a series of {@link Order}s, and then reports on the orders,
    their calculated weight and caloric content; and then on the distinct
    {@link Specification}s that were created.
    */
    public static void main(String[] args)
    {
        orders.add (new Order
            ("Smith", Ingredient.mushroom));
        orders.add (new Order
            ("Jones", Ingredient.pepper, Ingredient.sausage));
        orders.add (new Order
            ("Johnson", Ingredient.pepperoni, Ingredient.sausage));
        orders.add (new Order
            ("Olson", Ingredient.pepperoni, Ingredient.sausage));
        orders.add (new Order
            ("O'Leary", Ingredient.pepperoni, Ingredient.onion));
        orders.add (new Order
            ("Baker", Ingredient.anchovies));
        orders.add (new Order
            ("Harrison", Ingredient.onion, Ingredient.pepper));
        orders.add (new Order
            ("Tucker", Ingredient.onion, Ingredient.pepperoni));
        orders.add (new Order
            ("Carter", Ingredient.onion, Ingredient.pepper));
        orders.add (new Order
            ("Gardner", Ingredient.mushroom));

        System.out.println (orders.size () + " orders were taken:");
        System.out.println ();
        System.out.println ("Weight(g) Calories  Ingredients");
        System.out.println ("--------- --------  --------------------------");

        for (Order order : orders)
            System.out.format ("   %6d   %6d  %s%n",
                order.specification.getWeight (),
                order.specification.getCalories (),
                order.specification.toString ().replaceAll ("[\\[\\]]", ""));
        System.out.println ();

        Specification.report ();
    }
}
