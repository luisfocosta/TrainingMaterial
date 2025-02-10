/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.reflection;

import java.util.*;

/**
Exercises {@link UITree} by building one on the 
{@link cc.preference.ViewPreferences} class.

@author Will Provost
*/
public class TestUITree
{
    /**
    Recursive method to display the UI tree.
    */
    public static void showTree (Map<String,Object> node, String indent)
    {
        for (Map.Entry<String,Object> entry : node.entrySet ())
        {
            System.out.print (indent);
        
            if (entry.getValue () instanceof Class)
            {
                Class<?> type = (Class<?>) entry.getValue ();
                System.out.println (entry.getKey () + " : " + type.getName ());
            }
            else
            {
                UITree type = (UITree) entry.getValue ();
                System.out.println (entry.getKey ());
                showTree (type, indent + "  ");
            }
        }
    }
    
    /**
    Builds a new {@link UITree} on the {@link cc.preference.ViewPreferences}
    class, and passes it to {@link #showTree showTree}.
    */
    public static void main (String[] args)
    {
        UITree tree = new UITree (cc.preference.ViewPreferences.class);
        showTree (tree, "");
    }
}

