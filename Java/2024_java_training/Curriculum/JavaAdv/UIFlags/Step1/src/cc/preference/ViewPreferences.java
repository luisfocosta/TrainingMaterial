/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.preference;

/**
JavaBean that captures user preferences for a hypothetical application's view.

@author Will Provost
*/
public class ViewPreferences
{
    public enum Compass { North, South, East, West };

    private int height;
    private int width;
    private ColorPreferences colors;
    private Compass toolbarOrientation;
    private int lineThickness;
    
    public int getHeight ()
    {
        return height;
    }
    
    public void setHeight (int newValue)
    {
        height = newValue;
    }
    
    public int getWidth ()
    {
        return width;
    }
    
    public void setWidth (int newValue)
    {
        width = newValue;
    }
    
    public ColorPreferences getColors ()
    {
        return colors;
    }
    
    public void setColors (ColorPreferences newValue)
    {
        colors = newValue;
    }
    
    public Compass getToolbarOrientation ()
    {
        return toolbarOrientation;
    }
    
    public void setToolbarOrientation (Compass newValue)
    {
        toolbarOrientation = newValue;
    }
    
    public int getLineThickness ()
    {
        return lineThickness;
    }
    
    public void setLineThickness (int newValue)
    {
        lineThickness = newValue;
    }
}
