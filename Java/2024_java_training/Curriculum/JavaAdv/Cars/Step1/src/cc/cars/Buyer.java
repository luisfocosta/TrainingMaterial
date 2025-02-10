/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

import java.util.function.DoubleBinaryOperator;

/**
Acts as a buying agent for the user.  Give the buyer a budget,
and the class will automate negotiation with the seller
and report on the process.

@author Will Provost
*/
public class Buyer
{
    private String make;
    private String model;
    private double budget;
    private int rounds;

    private static final double CLOSE_ENOUGH = 50;

    /**
    Functional interface to allow callers to provide logic for
    calculating a counter-offer during a negotiation.
    */
    @FunctionalInterface
    public interface CounterOfferCalculator
    {
        /**
        Return the <strong>increment</strong> by which our offer should
        be improved, if any.

        @param offer The buyer's latest offer
        @param response The seller's response
        @param ceiling The highest we could possibly pay --
            this is generally the lower of the sticker price and
            the buyer's budget
        @param round What round of negotiations we are in now
        @param rounds How many rounds we're authorized to attempt in total
        */
        public double sweetenBy(double offer, double response,
            double ceiling, int round, int rounds);
    }

    /**
    Build by indicating budget and how many rounds to try.
    */
    public Buyer (String make, String model, double budget, int rounds)
    {
        this.make = make;
        this.model = model;
        this.budget = budget;
        this.rounds = rounds;
    }

    /**
    Kick off the negotiation by pointing the buyer at a dealership.

    @return Price paid, or Double.NaN if no deal was reached
    */
    public double buy (Dealership dealership,
        DoubleBinaryOperator offerCalculator,
        CounterOfferCalculator counterOfferCalculator)
    {
        Car car = dealership.findCar (make, model);
        if (car == null)
        {
            System.out.println ("They don't have a " + make + " " + model +
                " on the lot right now.");
            return Double.NaN;
        }
        else if (car.isSold ())
        {
            System.out.println ("They have one, but it's sold.");
            return Double.NaN;
        }

        Seller seller = dealership.sellCar (car);
        System.out.println ("Got a seller of class " +
            seller.getClass ().getName ());

        double askingPrice = seller.getAskingPrice ();
        System.out.format ("Asking $%,1.2f%n", askingPrice);

        double offer = Math.min
            (offerCalculator.applyAsDouble (askingPrice, budget), budget);

        for (int r = 0; r < rounds; ++r)
        {
            System.out.format ("  Round %d: offering $%,1.2f ... ", r, offer);
            double response = seller.willSellAt (offer);
            if (response == offer)
            {
                System.out.println ("We have a deal!");
                return offer;
            }

            System.out.format ("Counter-offer of $%,1.2f%n", response);
            if (response - offer <= CLOSE_ENOUGH)
            {
                System.out.println ("Close enough!");
                return response;
            }

            // r + 1, because we'll make the actual offer next time:
            offer = Math.min (Math.min (offer +
                counterOfferCalculator.sweetenBy (offer, response,
                    Math.min(askingPrice, budget), r + 1, rounds),
                response), budget);
        }

        System.out.println ("Couldn't close the deal -- sorry.");
        return Double.NaN;
    }

    /**
    Instantiates a buyer based on command-line arguments for car, budget and
    number of rounds, and kicks it off.
    */
    public static void main (String[] args)
    {
        Dealership dealership = new Dealership ();

        if (args.length < 4)
        {
            System.out.println
              ("Usage: java cc.cars.Buyer <make> <model> <budget> <rounds>");

            final String sellerType = "Standard";
            final String make = "Toyota";
            final String model = "Prius";
            final double budget = 26400;

            System.setProperty ("cc.cars.Seller.type", sellerType);

            System.out.println();
            System.out.println("Tests, using " + sellerType + " seller:");
            System.out.format
                ("Negotiating to buy a %s %s with a budget of $%,1.2f:",
                    make, model, budget);

            System.out.println();
            System.out.println("With standard strategy:");
            new Buyer (make, model, budget, 4).buy
                (dealership,
                 (a,b) -> Math.min(a, b) -
                    Math.min (Math.abs(a - b), Math.min(a, b) / 2),
                 (o, rsp, c, rnd, rnds) -> (c - o) * .7);

            System.out.println();
            System.out.println("Optimized for Standard seller:");
            new Buyer (make, model, budget, 4).buy
                (dealership, (a,b) -> a * .7,
                    (o, rsp, c, rnd, rnds) ->  rnd == 3 ? rsp - o : 0);

            System.out.println();
            System.out.println("Optimized for HardNosed seller:");
            new Buyer (make, model, budget, 20).buy
                (dealership, (a,b) -> a * .95, (o, rsp, c, rnd, rnds) ->  0);

            System.exit (0);
        }

        new Buyer (args[0], args[1], Double.parseDouble (args[2]),
            Integer.parseInt (args[3])).buy
                (dealership,
                 (a,b) -> Math.min(a, b) -
                    Math.min (Math.abs(a - b), Math.min(a, b) / 2),
                 (o, rsp, c, rnd, rnds) -> (c - o) * .7);
    }
}
