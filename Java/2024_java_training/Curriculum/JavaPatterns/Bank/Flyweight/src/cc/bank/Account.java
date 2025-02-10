/*
Copyright 2007-2009 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.bank;

import cc.bank.Transaction.Type;

/**
This class implements a bank account.  The account holds a balance
and offers methods to process a given {@link Transaction} or post periodic
interest.

@author Will Provost
*/
public class Account
{
    /**
    Classifies accounts based on account balance.
    Account standing affects what transactions are allowed and what fees
    may be assessed per transaction.
    */
    public enum Standing
    {
        /** In this status, no fees are assessed. */      NO_FEES (1000.0),
        /** In this status, normal fees are assessed. */  GOOD (0.0),
        /** In this status, extra fees are assessed. */   OVERDRAWN (-100.0),
        /** In this status, only deposits are allowed. */ FROZEN (null);

        private Double threshold;

        /**
        Build by setting the threshold for this classification.
        */
        Standing (Double threshold)
        {
            this.threshold = threshold;
        }

        /**
        Calculate the correct transaction fee, using the
        {@link Transaction.Type#getFee base fee} and the account standing.
        */
        public Transaction getFee (Type xaType)
        {
            if (this == FROZEN && xaType != Type.DEPOSIT)
                throw new IllegalStateException ("Account frozen.");

            if (this == NO_FEES || xaType == Type.ASSESS_FEE)
                return null;

            double fee = xaType.getFee ();
            if (this == OVERDRAWN && xaType != Type.DEPOSIT)
                fee *= 2.5;

            return new Transaction (Type.ASSESS_FEE, fee);
        }

        /**
        Derive the appropriate standing for the given account,
        based on its balance.
        */
        public static Standing determineStanding (Account account)
        {
            double balance = account.getBalance ();
            for (Standing candidate : values ())
                if (candidate.getThreshold () != null &&
                        balance >= candidate.getThreshold ())
                    return candidate;

            return FROZEN;
        }

        /**
        Accessor for threshold property.
        */
        public Double getThreshold ()
        {
            return threshold;
        }
    };

    /**
    Build an account instance by providing the ID and starting balance.
    Standing is calculated automatically at this point.
    */
    public Account (int ID, double balance)
    {
        this.ID = ID;
        this.balance = balance;
        standing = Standing.determineStanding (this);
    }

    /**
    Adjusts the account balance appropriately, given the type and amount
    of the transaction.  Assesses any {@link Standing#getFee transaction fees}
    and updates the account standing.

    @throws TransactionRefused
        Refuses to process the transaction if it would leave the account
        with a negative balance.the account is
        {@link Standing#FROZEN frozen}, unless the transaction is a deposit.
    */
    public void executeTransaction (Transaction xa)
        throws TransactionRefused
    {
        Type xaType = xa.getType ();
        if (standing == Standing.FROZEN && xaType != Type.DEPOSIT)
            throw new TransactionRefused ("Account frozen.", this, xa);

        Transaction feeTransaction = standing.getFee (xaType);

        if (xaType == Type.DEPOSIT || xaType == Type.POST_INTEREST)
            balance += xa.getAmount ();
        else
        {
            balance -= xa.getAmount ();
        }

        if (feeTransaction != null)
            executeTransaction (feeTransaction);

        standing = Standing.determineStanding (this);
    }

    /**
    For accounts in {@link Standing#NO_FEES no-fee} or
    {@link Standing#GOOD good} standing, interest is credited at a rate of
    1/4 of a percent each period.
    */
    public void postInterest ()
    {
        // Questionable practice, but interesting to see:
        if (standing.ordinal () <= Standing.GOOD.ordinal ())
            try
            {
                executeTransaction (new Transaction
                    (Type.POST_INTEREST, balance * 0.0025));
            }
            catch (TransactionRefused ex)
            {
                System.out.println
                    ("BUG! Shouldn't be possible to refuse to post interest.");
            }
    }

    /**
    Accessor for the ID property.
    */
    public int getID ()
    {
        return ID;
    }

    /**
    Accessor for the balance property.
    */
    public double getBalance ()
    {
        return balance;
    }

    /**
    Accessor for the standing property.
    */
    public Standing getStanding ()
    {
        return standing;
    }

    private int ID;
    private double balance;
    private Standing standing;
}
