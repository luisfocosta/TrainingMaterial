/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.tools;

/**
Application that exercises the {@link Browser} by showing
ranges or "pages" of numbers.

@author Will Provost
*/
public class Pages
{
    /**
    We create a {@link Browser} and supply it with callback logic
    for navigation commands (next, previous, first, and last), as well as
    for producing the page of information and for getting a label for
    the current page. Then we set the browser running, and it controls
    user interaction until the user chooses to quit.
    */
    public static void main (String[] args)
    {
        final int LENGTH = 10;
        final int PAGES = 5;

        class Holder
        {
            public int value;
        }
        Holder holder = new Holder ();
        holder.value = 1;

        Browser browser = new Browser
            (
                () -> holder.value += LENGTH,
                () -> holder.value -= LENGTH,
                () -> holder.value = 1,
                () -> { holder.value = 1 + (PAGES - 1) * LENGTH; },
                () -> 
                    {
                        for (int i = holder.value; i < holder.value + LENGTH; ++i)
                            System.out.print ((i == holder.value ? "" : ", ") + i);
                        System.out.println ();
                        System.out.println ();
                    }, 
                () -> "Numbers between " + holder.value + " and " + (holder.value + LENGTH - 1)
            );

        browser.browse (PAGES);
    }
}
