/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.preference;

import java.awt.Color;
import cc.generics.Validator;

/**
A {@link cc.generics.Validator} dedicated to {@link ColorPreferences}
objects.

@author Will Provost
*/
public class ColorPreferencesValidator
    implements Validator<ColorPreferences>
{
    /**
    A preferences object is valid if (a) its foreground and background
    colors are non-null, and (b) its foreground is markedly darker than its
    background.
    */
    public boolean isValid (ColorPreferences candidate)
    {
        if (candidate == null ||
                candidate.getForeground () == null || 
                candidate.getBackground () == null)
            return false;
        
        int foregroundBrightness = 
            candidate.getForeground ().getRed () +
            candidate.getForeground ().getGreen () +
            candidate.getForeground ().getBlue ();

        int backgroundBrightness = 
            candidate.getBackground ().getRed () +
            candidate.getBackground ().getGreen () +
            candidate.getBackground ().getBlue ();
            
        return foregroundBrightness < backgroundBrightness - 128;
    }

    /**
    Correct color preferences as follows: replace a null object or any
    null color references with foreground black and background white, but
    allow a null for legend color.  Then adjust background to white, and 
    then foreground to black, as necessary until the choices are found
    valid.
    */
    public ColorPreferences correct (ColorPreferences candidate)
    {
        if (isValid (candidate))
            return candidate;
        
        ColorPreferences result = new ColorPreferences 
            (
                candidate == null || candidate.getForeground () == null 
                    ? Color.black 
                    : candidate.getForeground (),
                candidate == null || candidate.getBackground () == null 
                    ? Color.white 
                    : candidate.getBackground (),
                candidate == null ? null : candidate.getLegend ()
            );
            
        if (!isValid (result))
            result.setBackground (Color.white);
        if (!isValid (result))
            result.setForeground (Color.black);
            
        return result;
    }
}
