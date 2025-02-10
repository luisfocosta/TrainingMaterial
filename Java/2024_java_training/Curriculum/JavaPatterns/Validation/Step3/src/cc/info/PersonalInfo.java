/*
Copyright 2008-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.info;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
Simple JavaBean to capture user input.

@author Will Provost
*/
public class PersonalInfo
{
    @NotNull
    @Pattern
    (
      regexp="([A-Za-z\\'\\-]+)( [A-Za-z\\'\\-]+)+",
      message="Must include at least first and last name"
    )
    private String name;

    @Min
    (
      value=18,
      message="Age must be at least 18"
    )
    @Max
    (
      value=120,
      message="Age must be no greater than 120"
    )
    private int age;

    @NotNull
    @Pattern
    (
      regexp="\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.(\\w{2}|(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum))",
      message="Invalid e-mail address"
    )
    private String eMail;

    @NotNull
    @Pattern
    (
      regexp="[0-9]{3}-?[0-9]{2}-?[0-9]{4}",
      message="Invalid SSN"
    )
    private String SSN;
    
    @Size
    (
      max=40,
      message="Please keep reference to 40 characters or less"
    )
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
