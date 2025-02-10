/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.preference;

import java.awt.Color;
import cc.generics.*;

/**
This application exercises the {@link cc.generics.ValidCollection} as applied
to {@link ColorPreferences}.

@author Will Provost
*/
public class TestValidation
{
    public static void main (String[] args)
    {
        ValidCollection<ColorPreferences> list = 
            new ValidCollection<ColorPreferences> ();
        list.add (new ColorPreferences (Color.black, Color.white, Color.red));
        list.add (new ColorPreferences (Color.white, Color.black, Color.red));
        
        System.out.println (list.validate ()
            ? "List is valid using default validator."
            : "List is invalid using default validator.");
        System.out.println 
            (list.validate (new ColorPreferencesValidator ())
                ? "List is valid using color preferences validator."
                : "List is invalid using color preferences validator."); 
    }
}

