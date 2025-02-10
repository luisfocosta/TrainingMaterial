/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This class encapsulates a few fields relevant to a financial holding.
It can calculate its current value and report its state as an string.

@author Will Provost
*/
public class Asset
{
    /**
    The application method parses the command line for the three parameters
    name, number and price and reports on them.

    @param args Must contain security name, number of shares and price as integers
    */
    public static void main (String[] args)
    {
        String name = args[0];
        int numberOfShares = Integer.parseInt (args[1]);
        double sharePrice = Double.parseDouble (args[2]);

        System.out.format 
            ("%d shares of %s at $%,1.2f -- asset value = $%,1.2f%n",
            numberOfShares, name, sharePrice, numberOfShares * sharePrice);
        
        System.out.println 
            ("Name is MMEnterprises? " + (name == "MMEnterprises"));
                
        System.out.println 
            ("Name is MMEnterprises? " + (name.equals ("MMEnterprises")));
    }
}
