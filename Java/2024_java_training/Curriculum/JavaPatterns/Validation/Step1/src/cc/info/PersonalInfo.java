/*
Copyright 2008-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.info;

/**
Simple JavaBean to capture user input.

@author Will Provost
*/
public class PersonalInfo
{
    private String name;
    private int age;
    private String eMail;
    private String SSN;
    private String reference;

    /**
    Accessor for the name property.
    */
    public String getName ()
    {
        return name;
    }

    /**
    Mutator for the name property.
    */
    public void setName (String newValue)
    {
        name = newValue;
    }

    /**
    Accessor for the age property.
    */
    public int getAge ()
    {
        return age;
    }

    /**
    Mutator for the age property.
    */
    public void setAge (int newValue)
    {
        age = newValue;
    }

    /**
    Accessor for the eMail property.
    */
    public String getEMail ()
    {
        return eMail;
    }

    /**
    Mutator for the eMail property.
    */
    public void setEMail (String newValue)
    {
        eMail = newValue;
    }

    /**
    Accessor for the SSN property.
    */
    public String getSSN ()
    {
        return SSN;
    }

    /**
    Mutator for the SSN property.
    */
    public void setSSN (String newValue)
    {
        SSN = newValue;
    }

    /**
    Accessor for the reference property.
    */
    public String getReference ()
    {
        return reference;
    }

    /**
    Mutator for the reference property.
    */
    public void setReference (String newValue)
    {
        reference = newValue;
    }

    /**
    Reports whether the object state is valid.
    */
    public boolean isValid ()
    {
        boolean result = true;

        if (name == null)
        {
            System.out.println ("Must set a name");
            result = false;
        }
        else if (!name.matches ("([A-Za-z\\'\\-]+)( [A-Za-z\\'\\-]+)+"))
        {
            System.out.println ("Must include at least first and last name");
            result = false;
        }

        if (age < 18)
        {
            System.out.println ("Age must be at least 18");
            result = false;
        }
        if (age > 120)
        {
            System.out.println ("Age must be at most 120");
            result = false;
        }

        if (eMail == null)
        {
            System.out.println ("Must set an e-mail address");
            result = false;
        }
        else if (!eMail.matches ("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.(\\w{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))"))
        {
            System.out.println ("Invalid e-mail address");
            result = false;
        }

        if (SSN == null)
        {
            System.out.println ("Must set a SSN");
            result = false;
        }
        else if (!SSN.matches ("[0-9]{3}-?[0-9]{2}-?[0-9]{4}"))
        {
            System.out.println ("Invalid SSN");
            result = false;
        }

        if (reference != null && reference.length () > 40)
        {
            System.out.println ("Please keep reference to 40 characters or less");
            result = false;
        }

        return result;
    }
}
