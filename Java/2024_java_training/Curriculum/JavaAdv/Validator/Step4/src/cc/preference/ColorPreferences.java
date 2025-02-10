/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.preference;

import java.awt.Color;

/**
JavaBean that captures user preferences for colors in a graph.

@author Will Provost
*/
public class ColorPreferences
{
    private Color foreground;
    private Color background;
    private Color legend;

    public ColorPreferences 
        (Color foreground, Color background, Color legend)
    {
        this.foreground = foreground;
        this.background = background;
        this.legend = legend;
    }
    
    public Color getForeground ()
    {
        return foreground;
    }
    
    public void setForeground (Color newValue)
    {
        foreground = newValue;
    }
    
    public Color getBackground ()
    {
        return background;
    }
    
    public void setBackground (Color newValue)
    {
        background = newValue;
    }
    
    public Color getLegend ()
    {
        return legend;
    }
    
    public void setLegend (Color newValue)
    {
        legend = newValue;
    }
}
