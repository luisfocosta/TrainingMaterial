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
}
