/*
Copyright 2005-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.time.LocalDate;
import java.time.Month;
import static java.time.Month.*;

/**
This application directs the user as to appropriate attire for the season,
basing its choices on the month of the year.

@author Will Provost
*/
public class WhatShouldIWear
{
    public static void main (String[] args)
    {
        Month month = LocalDate.now ().getMonth ();

        String what = "";
        switch (month)
        {
        case JANUARY:
        case FEBRUARY:
            what = "Black and brown, perhaps white accessories for skiing.";
            break;

        case MARCH:
        case APRIL:
            what = "You can start to flirt with spring colors.";
            break;

        case MAY:
            what = "Bring out your whites! but only after Memorial Day.";
            break;

        case JUNE:
        case JULY:
        case AUGUST:
            what = "Bright colors, whites and yellows to reflect the sun.";
            break;

        case SEPTEMBER:
        case OCTOBER:
            what = "Earth tones, and this is a " +
                   "nice time for plaids and tweeds.";
            break;

        case NOVEMBER:
            what = "Break out the dark wools again!";
            break;

        case DECEMBER:
            what = "Something festive for the holidays.";
            break;
        }

        System.out.println (what);

        if (month == JULY || month == AUGUST)
            System.out.println ("And don't forget the sunscreen!");
    }
}
