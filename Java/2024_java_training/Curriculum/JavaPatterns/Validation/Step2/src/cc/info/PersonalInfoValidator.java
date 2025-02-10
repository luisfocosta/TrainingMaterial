/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
Validator for the {@link PersonalInfo} class.

@author Will Provost
*/
public class PersonalInfoValidator
{
    /**
    Provide a formatted string representing all the error messages.
    (Note that this probably goes in a base class Validator,
     when we're ready to expand the system to other target types.)
    */
    public static String formatErrorReport (Map<String,List<String>> errors)
    {
        StringBuilder builder = new StringBuilder ();
        for (String field : errors.keySet ())
        {
            builder.append (String.format ("%s:%n", field));
            for (String message : errors.get (field))
                builder.append (String.format ("  %s%n", message));
        }

        return builder.toString ();
    }

    /**
    Validate the given object and return a data structure with error
    message or messages per field name.
    */
    public static Map<String,List<String>> validate (PersonalInfo info)
    {
        class Errors
            extends TreeMap<String,List<String>>
        {
            public void add (String key, String message)
            {
                if (!containsKey (key))
                    put (key, new ArrayList<> ());

                get (key).add (message);
            }
        }
        Errors errors = new Errors ();

        if (info.getName () == null)
            errors.add ("name", "Must set a name");
        else if (!info.getName ().matches ("([A-Za-z\\'\\-]+)( [A-Za-z\\'\\-]+)+"))
            errors.add ("name", "Must include at least first and last name");

        if (info.getAge () < 18)
            errors.add ("age", "Age must be at least 18");
        if (info.getAge () > 120)
            errors.add ("age", "Age must be at most 120");

        if (info.getEMail () == null)
            errors.add ("eMail", "Must set an e-mail address");
        else if (!info.getEMail ().matches ("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.(\\w{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))"))
            errors.add ("eMail", "Invalid e-mail address");

        if (info.getSSN () == null)
            errors.add ("SSN", "Must set a SSN");
        else if (!info.getSSN ().matches ("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.(\\w{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))"))
            errors.add ("SSN", "Invalid SSN");

        if (info.getReference () != null &&
                info.getReference () .length () > 40)
            errors.add ("reference",
                "Please keep reference to 40 characters or less");

        return errors;
    }
}
