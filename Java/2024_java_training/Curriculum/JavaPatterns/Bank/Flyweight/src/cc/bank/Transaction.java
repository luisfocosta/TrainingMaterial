/*
Copyright 2007-2009 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.bank;

/**
Encapsulates a transaction on an account.

@author Will Provost
*/
public class Transaction
{
    /**
    Classifies transactions; every transaction has a clearly-defined type.
    */
    public enum Type
    {
        /** A withdrawal. */                WITHDRAW      (2.5),
        /** A deposit. */                   DEPOSIT       (1.5),
        /** A check. */                     CHECK          (.75),
        /** Posting interest. */            POST_INTEREST (0),
        /** Assessing a transaction fee. */ ASSESS_FEE    (0);

        private double fee;

        /**
        Build with associated {@link #getFee fee}.
        */
        Type (double fee)
        {
            this.fee = fee;
        }

        /**
        The fee, if any, associated with this transaction type.
        */
        public double getFee ()
        {
            return fee;
        }
    };

    /**
    Build by providing transaction type and amount.
    Amounts are always positive; the transaction type dictates the flow
    of cash (positive or negative impact on balance).
    */
    public Transaction (Type type, double amount)
    {
        this.type = type;
        this.amount = amount;
    }

    /**
    Accessor for the type property.
    */
    public Type getType ()
    {
        return type;
    }

    /**
    Accessor for the amount property.
    */
    public double getAmount ()
    {
        return amount;
    }

    private Type type;
    private double amount;
}
