/*
Copyright 2008-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.info;

/**
Processes personal information.

@author Will Provost
*/
public class ProcessPersonalInfo
{
    /**
    Utility method that validates a given object and produces error messages.
    */
    public static void printReport (String label, PersonalInfo info)
    {
        System.out.println (label + ":");
        
        System.out.println ("Object is " +
            (info.isValid () ? "valid." : "not valid."));

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
