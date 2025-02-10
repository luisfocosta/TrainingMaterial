/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.bank;

/**
This application class tests the rest of the package.

@author Will Provost
*/
public class Test
{
    /**
    Exercises an account object, without knowing what account type
    it's dealing with.  Makes a deposit of $50, then a withdrawal of $50,
    reporting the balance after each operation.
    Reports any InsufficientFunds exception and quits.

    @param account Target account instance upon which to operate
    */
    public static void exercise (Account account)
    {
        account.deposit (50);
        System.out.format ("After deposit, balance is $%,1.2f%n", 
            account.getBalance ());
        account.withdraw (50);
        System.out.format ("After withdrawal, balance is $%,1.2f%n",
            account.getBalance ());
    }

    /**
    The application method calls {@link exercise exercise} on each of two
    objects, one an {@link Account} and one a {@link CheckingAccount}.
    */
    public static void main (String[] args)
    {
        System.out.println ("Creating new account with balance of $90.00");
        Account account = new Account (90);
        exercise (account);

        System.out.println ();
        System.out.println 
            ("Creating a new checking account on old account balance.");
        account = new CheckingAccount (account.getBalance ());
        exercise (account);
    }
}
