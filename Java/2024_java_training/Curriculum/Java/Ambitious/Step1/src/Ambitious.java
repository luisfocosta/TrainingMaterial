/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This class echoes a greeting to the console output stream,
after checking for command-line arguments to dictate language
and mode.

@author Will Provost
*/
public class Ambitious
{
    static final String[][] greetings =
    {
        { "English", "Hello", "Hi, how the heck are you?" },
        { "French", "Bonjour", "Comment allez-vous?" },
        { "Germen", "Guten tag", "I don't actually know German." },
        { "American", "Yo", "'Sup?" }
    };
    
    static final String VERBOSE_SWITCH = "-v";

    static final int LANGUAGE_INDEX = 0;
    static final int GREETING_INDEX = 1;
    static final int VERBOSE_GREETING_INDEX = 2;

    public void main (String[])
    {
        String language = "English";
        String mode = "";

        if (args.length > 0)
        {
            language = args[args.length - 1];
            if (args.length > 1)
                mode = args[0];
        }

        for (int found = 0; found < greetings.length; ++found)
            if (greetings[found][LANGUAGE_INDEX].equals (language))
                System.out.println 
                    (greetings[found][mode.equals (VERBOSE_SWITCH)
                        ? VERBOSE_GREETING_INDEX
                        : GREETING_INDEX]);
    }
}
