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
    private static final int LENGTH = 10;
    private static final int PAGES = 5;
    private static int offset = 1;

    /**
    We create a {@link Browser} and supply it with callback logic
    for navigation commands (next, previous, first, and last), as well as
    for producing the page of information and for getting a label for
    the current page. Then we set the browser running, and it controls
    user interaction until the user chooses to quit.
    */
    public static void main (String[] args)
    {
        Browser browser = new Browser
            (
                () -> offset += LENGTH,
                () -> offset -= LENGTH,
                () -> offset = 1,
                () -> { offset = 1 + (PAGES - 1) * LENGTH; },
                () -> 
                    {
                        for (int i = offset; i < offset + LENGTH; ++i)
                            System.out.print ((i == offset ? "" : ", ") + i);
                        System.out.println ();
                        System.out.println ();
                    }, 
                () -> "Numbers between " + offset + " and " + (offset + LENGTH - 1)
            );

        browser.browse (PAGES);
    }
}
