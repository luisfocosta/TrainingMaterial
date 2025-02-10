/*
Copyright 2012 Max Rahder.
Used by permission.
*/

package cc.pizza;

/**
Enumeration of distinct ingredients available on our pizzas.
Each has an assigned weight and caloric content.

@author Max Rahder
*/
public enum Ingredient
{
    onion (60, 80),
    pepper (80, 100),
    tomato (120, 200),
    broccoli (100, 200),
    mushroom (80, 100),
    pepperoni (120, 700),
    anchovies (80, 400),
    sausage (120, 800);

    public final int weight;
    public final int calories;

    private Ingredient (int weight, int calories)
    {
        this.weight = weight;
        this.calories = calories;
    }
}
