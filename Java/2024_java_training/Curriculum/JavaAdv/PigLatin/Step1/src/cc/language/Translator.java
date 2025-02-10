/*
Copyright 1999-2011 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/


package cc.language;

import java.util.Set;
import java.util.TreeSet;

/**
This class translates English into Pig Latin.
It operates as a filtering output stream, and can be chained to
any other OutputStream implementation.

@author Will Provost
*/
public class Translator
{
    /**
    Enumeration of vowels that offers a static set of its own values
    as characters, to make it easy to test that a given character is a vowel.
    */
    private static enum Vowel 
    { 
        a, e, i, o, u; 
      
        public static Set<Character> valueSet = new TreeSet<> ();
        static
        {
            for (Vowel v : values ())
                valueSet.add (v.name ().charAt (0));
        }
    };

}
