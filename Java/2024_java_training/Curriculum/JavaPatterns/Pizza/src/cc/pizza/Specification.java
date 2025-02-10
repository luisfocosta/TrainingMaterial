/*
Copyright 2012 Max Rahder.
Used by permission.
*/

package cc.pizza;

import java.util.HashSet;
import java.util.Set;

/**
Flyweight for pizza specifications.

@author Max Rahder
*/
public class Specification
{
    private static Set<Specification> specifications =
        new HashSet<> ();

    private Set<Ingredient> ingredients = new HashSet<> ();

    /**
    Create a new specification as a set of ingredients.
    */
    private Specification (Ingredient... ingredients)
    {
        super ();
        for (Ingredient i : ingredients)
        {
            this.ingredients.add (i);
        }
    }

    /**
    Create a new specification as a set of ingredients -- or
    return a previously-created, equivalent specification, if one exists.
    */
    public static Specification get (Ingredient... ingredients)
    {
        Specification newSpec = new Specification (ingredients);
        for (Specification spec : specifications)
            if (spec.equals (newSpec))
                return spec;

        specifications.add (newSpec);
        return newSpec;
    }

    /**
    Prints a description of total specifications created to date.
    */
    public static void report ()
    {
        System.out.println ("There are " + Specification.specifications.size ()
                + " types of pizza for which we're storing data.");
    }

    /**
    Provides a sum of the ingredients' caloric content.
    */
    public int getCalories ()
    {
        int result = 0;
        for (Ingredient i : ingredients)
        {
            result += i.calories;
        }
        return result;
    }

    /**
    Provides a sum of the ingredients' weight.
    */
    public int getWeight ()
    {
        int result = 0;
        for (Ingredient i : ingredients)
        {
            result += i.weight;
        }
        return result;
    }

    /**
    Delegate to the set of ingredients to determine the hash code.
    */
    @Override
    public int hashCode ()
    {
        return ingredients.hashCode ();
    }

    /**
    Delegate to the set of ingredients to determine equivalence.
    */
    @Override
    public boolean equals (Object that)
    {
        return ingredients.equals
            (((Specification) that).ingredients);
    }

    /**
    Delegate to the set of ingredients for a simple string representation.
    */
    @Override
    public String toString ()
    {
        return this.ingredients.toString ();
    }
}
