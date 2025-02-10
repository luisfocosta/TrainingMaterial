/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.reflection;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
This class represents a tree of named attributes, each of which can be of
simple type or complex type.  Complex types are represented by nested
UITree nodes.

@author Will Provost
*/
public class UITree
    extends TreeMap<String,Object>
{
    /**
    Builds out the entire tree working from the given node.
    Uses ordinary reflection to find fields, but then filters out fields
    that are not flagged with the {@link cc.preference.Show} annotation.
    Recurses to create additional UIType nodes if the field type is not
    either a primitive or a class found in {@link #SIMPLE_TYPES}.
    */
    public UITree (Class<?> rootType)
    {
        try
        {
            for (Field field : rootType.getDeclaredFields ())
                put (field.getName (), field.getType ());
        }
        catch (SecurityException ex)
        {
            System.out.println 
                ("Failed to perform reflection to build UI tree.");
        }
    }
    
    /**
    A set of classes that can be treated as single values for GUI purposes:
    for instance a String is a class but we don't want to recurse through
    its members for purposes of a preferences dialog.
    */
    static Set<Class<?>> SIMPLE_TYPES = new HashSet<Class<?>> ();
    
    /**
    Initializes {@link #SIMPLE_TYPES} to include String, Date, Calendar,
    Color, and {@link cc.preference.ViewPreference.Compass Compass}.
    */
    static
    {
        SIMPLE_TYPES.add (String.class);
        SIMPLE_TYPES.add (Date.class);
        SIMPLE_TYPES.add (Calendar.class);
        SIMPLE_TYPES.add (java.awt.Color.class);
        SIMPLE_TYPES.add (cc.preference.ViewPreferences.Compass.class);
    }
}

