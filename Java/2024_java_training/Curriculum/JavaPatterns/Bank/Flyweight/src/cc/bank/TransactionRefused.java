/*
Copyright 2007-2009 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.bank;

/**
Exception that indicates that a given transaction could not be processed
according to the bank's rules.

@author Will Provost
*/
public class TransactionRefused
    extends Exception
{
    /**
    Indicate the reason, which becomes the ordinary exception message string,
    and also the account on which the transaction was refused, and the
    transaction object itself.
    */
    public TransactionRefused (String reason, Account account, Transaction xa)
    {
        super (reason);

        this.account = account;
        this.xa = xa;
    }

    /**
    Accessor for account property.
    */
    public Account getAccount ()
    {
        return account;
    }

    /**
    Accessor for transaction property.
    */
    public Transaction getTransaction ()
    {
        return xa;
    }

    private Account account;
    private Transaction xa;
}
