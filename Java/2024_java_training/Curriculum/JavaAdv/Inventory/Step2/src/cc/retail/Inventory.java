/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.retail;

import java.util.List;

/**
Interface to inventory data.

@author Will Provost
*/
public interface Inventory
{
    /**
    Return the quantity in inventory for the given part number.
    */
    public int getQuantity (String partNumber);

    /**
    Batch version of {@link #getQuantity(long) getQuantity}.
    */
    public List<Integer> getQuantities (List<String> partNumbers);

    /**
    Get a list of all part numbers with quantities below the
    given threshold.
    */
    public List<String> getPartsInShortSupply (int min);
}
