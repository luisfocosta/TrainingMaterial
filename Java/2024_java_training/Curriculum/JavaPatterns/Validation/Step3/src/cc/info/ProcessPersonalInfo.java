/*
Copyright 2008-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.info;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;

/**
Processes personal information.

@author Will Provost
*/
public class ProcessPersonalInfo
{
    private static Validator validator =
        Validation.buildDefaultValidatorFactory ().getValidator ();

    /**
    Utility method that validates a given object and produces error messages.
    */
    public static void printReport (String label, PersonalInfo info)
    {
        System.out.println (label + ":");
        
        Set<ConstraintViolation<Object>> violations =
            validator.validate (info);
        if (violations.size () == 0)
            System.out.println ("  Validation succeeded.");
        else
            for (ConstraintViolation<Object> violation : violations)
                System.out.println ("  " + violation.getMessage () + ".");

        System.out.println ();
    }

    /**
    We build two instances of {@link cc.web.Personalinfo}, and validate each.
    */
    public static void main (String[] args)
    {
        PersonalInfo goodInfo = new PersonalInfo ();
        goodInfo.setName ("William Provost");
        goodInfo.setAge (52);
        goodInfo.setEMail ("xxx@yyy.com");
        goodInfo.setSSN ("021304561");
        goodInfo.setReference ("Friend of a friend");
        printReport ("Good PersonalInfo", goodInfo);

        PersonalInfo badInfo = new PersonalInfo ();
        badInfo.setName ("William W. Provost, III");
        badInfo.setAge (12);
        badInfo.setEMail ("xxx@yyy");
        badInfo.setSSN ("02130");
        badInfo.setReference
            ("Friend of a friend of a friend of a friend of a friend");
        printReport ("Bad PersonalInfo", badInfo);
    }
}
