/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.inherit;

import cc.generics.Validator;

/**
This application exercises the {@link cc.generics.ValidCollection} as applied
to various levels of the interhitance hierarchy from {@link A} to {@link B}
to {@link C}.

@author Will Provost
*/
public class TestValidation
{
    public static void main (String[] args)
    {
        Validator<A> aValidator = new AValidator ();
        Validator<B> bValidator = new BValidator ();
        Validator<C> cValidator = new CValidator ();
        
        /*
        List<B> listBC = new ArrayList<B> ();
        listBC.add (new B (1, 2));
        listBC.add (new C (2, 3, 4));
        
        ValidCollection<B> validBCasB = new ValidCollection<B> (listBC);
        System.out.println (validBCasB.validate (aValidator)
            ? "listBC is valid using AValidator."
            : "listBC is invalid using AValidator.");
        System.out.println (validBCasB.validate (bValidator)
            ? "listBC is valid using BValidator."
            : "listBC is invalid using BValidator.");
        System.out.println ();
        
        List<C> listC = new ArrayList<C> ();
        listC.add (new C (10, 20, 30));
        
        ValidCollection<B> validCasB = new ValidCollection<B> (listC);
        System.out.println (validCasB.validate (bValidator)
            ? "listC is valid using BValidator."
            : "listC is invalid using BValidator.");
        
        ValidCollection<C> validCasC = new ValidCollection<C> (listC);
        System.out.println (validCasC.validate (cValidator)
            ? "listC is valid using CValidator."
            : "listC is invalid using CValidator.");
        */
    }
}

