/*
Copyright 2005, 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.user;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
A group of users and other groups.

@author Will Provost
*/
public class Group
    extends Principal
{
    private List<Principal> members = new LinkedList<> ();  

    /**
    Create with just the group name; add members individually with
    {@link #addMember addMember}.
    */
    public Group (String name)
    {
        super (name);
    }
    
    /**
    Returns an unmodifiable list of the current membership.
    */
    public List<Principal> getMembers ()
    {
        return Collections.unmodifiableList (members);
    }
    
    /**
    Add a member to the group.  If the name is duplicated by a current
    member, the method will throw an IllegalArgumentException.
    */
    public void addMember (Principal member)
    {
        for (Principal existingMember : members)
            if (member.getName ().equals (existingMember.getName ()))
                throw new IllegalArgumentException 
                    ("Member already exists in group.");
                    
        members.add (member);
        member.setParent (this);
    }
    
    /**
    Remove a member from the group.
    */
    public void removeMember (Principal member)
    {
        members.remove (member);
        member.setParent (null);
    }
    
    /**
    Override to find ourselves or any descendants.
    This implies either that node names are globally unique, or that 
    a parent name will "hide" a child name.
    */
    @Override
    public Principal find (String name)
    {
        Principal result = super.find (name);
        Iterator<Principal> iterator = members.iterator ();
        while (result == null && iterator.hasNext ())
            result = iterator.next ().find (name);

        return result;
    }
}

