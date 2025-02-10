/*
Copyright 2007-2009 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.bank;

import cc.bank.Transaction.Type;

/**
This application class tests the rest of the package.

@author Will Provost
*/
public class Test
{
    /**
    Accessor for this bank's accounts.
    */
    public Account[] getAccounts ()
    {
        return accounts;
    }

    /**
    Utility method to carry out one transaction, handle exceptions,
    and write a line to the console showing the results.
    */
    public static void process (Account account, Transaction... xaList)
    {
        for (Transaction xa : xaList)
            try
            {
                account.executeTransaction (xa);
                System.out.format ("After %-14s balance is $%,9.2f%n",
                    xa.getType () + ",", account.getBalance ());
            }
            catch (TransactionRefused ex)
            {
                System.out.println ("REFUSED " + ex.getTransaction ().getType ()
                    + "; reason is " + ex.getMessage ());
            }
    }

    /**
    Exercises an account object by executing a series of transactions,
    with the help of the {@link #process process} method; and then
    {@link Account#postInterest posting interest}.
    */
    public void exercise (Account account)
    {
        process (account, new Transaction (Type.DEPOSIT, 500),
                          new Transaction (Type.CHECK, 800),
                          new Transaction (Type.WITHDRAW, 500));

        account.postInterest ();
        System.out.format ("After %-14s balance is $%,9.2f%n",
            "post interest,", account.getBalance ());
    }

    /**
    The application method calls {@link #exercise exercise} on each of
    a few accounts.
    */
    public static void main (String[] args)
    {
        Test test = new Test ();

        System.out.println ();
        System.out.println ("Testing account 1 ...");
        test.exercise (test.getAccounts()[0]);

        System.out.println ();
        System.out.println ("Testing account 2 ...");
        test.exercise (test.getAccounts()[1]);

        System.out.println ();
        System.out.println ("Testing account 3 ...");
        test.exercise (test.getAccounts()[2]);

        System.out.println ();
        System.out.println ("Testing account 4 ...");
        test.exercise (test.getAccounts()[3]);

        System.out.println ();
        for (Account account : test.getAccounts ())
            System.out.format
                ("Account %d balance is $%,9.2f; standing is %s%n",
                    account.getID (), account.getBalance (),
                    account.getStanding ());
    }

    private Account[] accounts =
    {
        new Account (1, 1200),
        new Account (2, 500),
        new Account (3, 2400),
        new Account (4, 100)
    };
}
