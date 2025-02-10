/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.bank;

/**
This class implements a checking account, which is a specialization
of an ordinary Account that imposes per-transaction fees if a
minimum balance is not kept.

@author Will Provost
*/
public class CheckingAccount
    extends Account
{
    private static final double MINIMUM_BALANCE = 100.0;
    private static double TRANSACTION_FEE = 1.50;

    /**
    Build a checking account instance by providing the balance.
    This just calls the superclass constructor.
    */
    public CheckingAccount (double balance)
    {
        super (balance);
    }

    /**
    Helper method to impose the standard transaction fee.
    */
    protected void imposeTransactionFee ()
    {
        if (getBalance () < MINIMUM_BALANCE)
            super.withdraw (TRANSACTION_FEE);
    }

    /**
    Adds the passed amount to the account balance, after imposing
    transaction fee.
    */
    @Override
    public void deposit (double amount)
    {
        imposeTransactionFee ();
        super.deposit (amount);
    }

    /**
    Subtracts the passed amount from the account balance, and imposes the
    transaction fee.
    */
    @Override
    public void withdraw (double amount)
    {
        super.withdraw (amount);
        imposeTransactionFee ();
    }
}

