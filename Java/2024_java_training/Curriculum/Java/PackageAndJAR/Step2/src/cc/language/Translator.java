package cc.language;

/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.io.*;
import java.util.*;

/**
This class translates English into Pig Latin.
It operates as a filtering output stream, and can be chained to
any other OutputStream implementation.

@author Will Provost
*/
public class Translator
    extends OutputStream
{
    /**
    Delegate output stream.
    */
    private java.io.PrintStream delegate;

    /**
    Holds the characters up to the first vowel in each word encountered.
    */
    private String beginning = "";

    /**
    Holds the characters starting with the first vowel in each word encountered.
    */
    private String middle = "";

    /**
    Flag as to whether first vowel has been encountered yet.
    */
    private boolean foundMiddle = false;

    /**
    Standard ending for Pig Latin words.
    */
    private static final String ENDING = "ay";

    /**
    Enumeration of vowels that offers a static set of its own values
    as characters, to make it easy to test that a given character is a vowel.
    */
    private static enum Vowel
    {
        a, e, i, o, u;

        public static Set<Character> valueSet = new TreeSet<Character> ();
        static
        {
            for (Vowel v : values ())
                valueSet.add (v.name ().charAt (0));
        }
    };

    /**
    @param delegate A target stream to which to write the translated output.
    */
    public Translator (PrintStream delegate)
    {
        this.delegate = delegate;
    }

    /**
    Override of base-class method to translate character output into Pig Latin.
    Identifies first vowel of each word, stores a beginning part up to that
    character and a middle part following, and on whitespace (actually only
    checks for space character in this implementation), writes the translated
    word as middle-beginning-ending, where ending is the static string "ay".

    @param output Next byte to write to the stream
    */
    @Override
    public void write (int output)
    {
        if (delegate == null)
            return;

        char character = (char) output;
        if (character == '\r'); // consume
        else if (!Character.isLetter (character))
        {
            if ("quit".equalsIgnoreCase (beginning + middle))
            {
                delegate.println ("uttingshay ownday ...");
                System.exit (0);
            }

            delegate.print (middle);
            delegate.print (beginning);
            delegate.print (ENDING);

            delegate.print (character);

            beginning = "";
            middle = "";
            foundMiddle = false;
        }
        else
        {
            if (!foundMiddle && Vowel.valueSet.contains (character))
                foundMiddle = true;

            if (foundMiddle)
                middle += character;
            else
                beginning += character;
        }
    }

    /**
    Application method creates an instance of this class and hooks it to
    {@link java.lang.System#out}.  Enters an infinite loop, accepting lines
    of input from {@link java.lang.System.in} and passing the characters to
    the translator, which then writes the translated string out.

    @param args No command-line arguments are checked.
    */
    public static void main (String[] args)
    {
        try
        (
            OutputStream filter = new Translator (System.out);
        )
        {
            while (true)
                filter.write (System.in.read ());
        }
        catch (IOException ex)
        {
            System.out.println ("IOExceptionay aughtcay.");
        }
    }
}
