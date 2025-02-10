/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This application illustrates various procedural techniques to control the
flow of processing.

@author Will Provost
*/
public class FlowControl
{
    public static void main (String[] args)
    {
        System.out.println ("Always executed.");
        
        int x = 5;
        int y = 6;
        
        // Simple conditional, single statement:
        if (x == 5)
            System.out.println ("x = 5!");
        
        // Chain of conditions, single statements 
        //    and a subordinate conditional block at the end:
        if (x < 5)
            System.out.println ("x < 5.");
        else if (x > 5)
            System.out.println ("x > 5.");
        else
        {
            System.out.println ("x must equal 5.");
            if (y == 7)
                System.out.println ("y = 7.");
            else
                System.out.println ("y <> 7.");
        }
        
        // Carelessly strung single statements yield incorrect results here:
        if (x == 5)
            if (y == 5)
                System.out.println ("y = 5.");
        else
            System.out.println ("x does not equal 5?!?  Check that logic!");

        // A code block fixes the problem -- the correct result is no result:
        if (x == 5)
        {
            if (y == 5)
                System.out.println ("y = 5.");
        }
        else
            System.out.println ("x does not equal 5.");
            
        // Storing a boolean result in a variable for later use:
        boolean xEquals3 = x == 3;
        if (!xEquals3)
        {
            System.out.println ("Setting x to 3.");
            x = 3;
        }
        
        // Assigning a variable and testing it all at once:
        if ((y = x + 1) == 4)
            System.out.println ("y set to x + 1, which is 4.");
        else
            System.out.println ("y set to x + 1, which is not 4.");
            
        // Using the ternary logic operator instead of if:
        System.out.println (x == 5
            ? "x equals 5."
            : "x does not equal 5.");

        // Nesting the ternary logic operator instead of if/else:
        System.out.println (x > 5
            ? "x greater than 5."
            : (x < 5
                ? "x less than 5."
                : "x must equal 5."));

        // Switch-cases, tested via a for loop:
        System.out.println ();
        for (int howMuch = 0; howMuch < 4; ++howMuch)
        {
            String result = "";
            switch (howMuch)
            {
            case 0:
                result = "I'm shy. ";
                break;

            case 3:
                result = "Let me tell you something. ";

            case 2:
                result += "I can talk a streak. ";

            case 1:
                result += "I'm not so shy. ";
            }

            switch (howMuch)
            {
            case 2:
            case 3:
                result += "Sorry, am I blabbering?";
            }
            System.out.println ("For " + howMuch + " result is:");
            System.out.println ("  " + result);
        }
        System.out.println ();
        
        // Switching using strings
        String[] input = { "ALL", "1", "2", "UNKNOWN" };
        for (String test : input)
        {
            System.out.format ("%-8s", test);
            switch (test)
            {
            case "ALL":
                System.out.println ("All of the above");
                break;

            case "UNKNOWN":
                System.out.println ("Value is unknown");
                break;
                
            default:
                System.out.println 
                    ("100 * value is " + 100 * Integer.parseInt (test));
            }
        }
        System.out.println ();
        
        // Switching using enumerated type:
        Color co = Color.green;
        switch (co)
        {
        case red:
        case green:
        case blue:
            System.out.println ("How colorful!");
            break;
            
        default:
            System.out.println ("How drab.");
        }

        // Simple for loop:
        System.out.println ();
        for (int i = 0; i < 5; ++i)
            System.out.println ("  i = " + i);
        
        // Allocating index before the loop so it can be used afterwards:
        System.out.println ();
        int i = 0;
        for (; i < 5; ++i)
            System.out.println ("  i = " + i);
        System.out.println ("At end of loop i = " + i);
        
        // Multiple indices moving towards each other:
        System.out.println ();
        i = 0;
        int j = 5;
        for (; i < j; ++i, --j)
            System.out.println ("  i = " + i + "; j = " + j);
            
        // Bad code ahead! This would loop forever:
        //for (i = 0; i < 5; ++j)
        //    System.out.println ("Jane, stop this crazy thing!");
        
        // Simple while loop counting down from 5, executes 5 times:
        System.out.println ();
        i = 5;
        while (i-- != 0)
            System.out.println ("  i = " + i);
        System.out.println ("At end of loop i = " + i);
        
        // Simple while loop counting down from 5, executes 4 times:
        System.out.println ();
        i = 5;
        while (--i != 0)
            System.out.println ("  i = " + i);
        System.out.println ("At end of loop i = " + i);

        // Three loops, only one of which will execute at all:
        System.out.println ();
        for (i = 0; i > 1; ++i) 
            System.out.println ("for loop executed.");
        
        while (i > 1)
        {
            ++i;
            System.out.println ("while loop executed.");
        }
        
        do
        {
            ++i;
            System.out.println ("do-while loop executed.");
        }
        while (i > 1); 
            // Note that if the condition were i >= 1 we'd lose control
            
        // Nested loops:
        System.out.println ();
        for (i = 0; i < 5; ++i)
            for (j = 0; j < 5; ++j)
                System.out.println ("  i = " + i + "; j = " + j);
                
        // Multiple boundary conditions, some tested inside the loop code:
        System.out.println ();
        j = 1;
        for (i = 1; i <= 10; ++i)
        {
            System.out.println ("  i = " + i);
            if ((j *= i) > 100)
                break;
        }
        System.out.println ("At end of loop j = " + j);
        
        // Skipping part of the processing in a loop:
        System.out.println ();
        while (j != 0)
        {
            j -= 1;
            if (j % 25 != 0)
                continue;
                
            System.out.println ("  j = " + j);
        }
        
        // Labeling loops to control break/continue behaviors:
        System.out.println ();
        iLoop: for (i = 0; i < 10; ++i)
        {
            jLoop: for (j = 0; j < 10; ++j)
            {
                int k = i * j;
                System.out.println 
                    ("  i = " + i + "; j = " + j + "; k = " + k);
                
                if (k > 20)
                {
                    System.out.println ("Breaking inner loop.");
                    break jLoop; // but continue with iLoop
                }
                else if (k == 20)
                {
                    System.out.println ("Breaking outer loop.");
                    break iLoop; // done with everything
                }
            }
            System.out.println ("Completed inner loop.");
        }
        
        // Looping over an array:
        System.out.print ("Fibonacci sequence:");
        int[] fibonacci = { 0, 1, 1, 2, 3, 5, 8 };
        for (int fib : fibonacci)
            System.out.print (" " + fib);
        System.out.println ();
        
        // Looping over an array when we need the index:
        int[] copy = new int[fibonacci.length];
        for (int index = 0; index < fibonacci.length; ++index)
            copy[index] = fibonacci[index];
        
        // Looping over all values in an enumeration:
        System.out.print ("Colors are ");
        for (Color color : Color.values ())
            System.out.print (color.name () + " ");
    }
}

enum Color { red, green, blue, black, white };

