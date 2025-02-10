/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.html;

/**
Abstract factory interface for creating HTML producer components.

@author Will Provost
*/
public abstract class Factory
{
    /**
    Create a page producer.
    */
    public abstract Block createPageProducer ();

    /**
    Create a section producer.
    */
    public abstract Block createSectionProducer ();

    /**
    Create an item producer.
    */
    public abstract Item createItemProducer ();

    /**
    Factory method for the factory.
    Asserts control over which factory is created.
    */
    public static Factory createFactory ()
    {
        return new cc.html.list.ListFactory ();
    }
}

