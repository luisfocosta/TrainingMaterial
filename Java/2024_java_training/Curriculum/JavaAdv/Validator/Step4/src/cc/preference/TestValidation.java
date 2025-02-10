/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.preference;

import java.awt.Color;
import java.util.*;
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
        Validator<ColorPreferences> colorValidator =
            new ColorPreferencesValidator ();
        
        List<ColorPreferences> list1 = new ArrayList<ColorPreferences> ();
        list1.add (new ColorPreferences 
            (Color.black, Color.white, Color.red));
        list1.add (new ColorPreferences 
            (Color.white, Color.black, Color.red));
        
        ValidCollection<ColorPreferences> validList1 =
            new ValidCollection<ColorPreferences> (list1);
        System.out.println (validList1.validate ()
            ? "List 1 is valid using default validator."
            : "List 1 is invalid using default validator.");
        System.out.println (validList1.validate (colorValidator)
            ? "List 1 is valid using color preferences validator."
            : "List 1 is invalid using color preferences validator.");        

        List<ColorPreferences> list2 = new ArrayList<ColorPreferences> ();
        list2.add (new ColorPreferences (Color.black, Color.white, null));

        System.out.println (ValidCollection.validate (list2)
            ? "List 2 is valid using default validator."
            : "List 2 is invalid using default validator.");
        System.out.println (ValidCollection.validate (list2, colorValidator)
            ? "List 2 is valid using color preferences validator."
            : "List 2 is invalid using color preferences validator.");
    }
}

