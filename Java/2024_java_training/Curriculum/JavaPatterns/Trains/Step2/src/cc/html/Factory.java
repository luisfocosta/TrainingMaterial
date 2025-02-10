/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.html;

/**
Abstract factory interface for creating HTML producer components.

@author Will Provost
*/
public interface Factory
{
    /**
    Create a page producer.
    */
    public Block createPageProducer ();

    /**
    Create a section producer.
    */
    public Block createSectionProducer ();

    /**
    Create an item producer.
    */
    public Item createItemProducer ();
}

