/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.functional;

import java.time.LocalDateTime;

/**
Experiments with functional interfaces, method references,
and lambda expressions.

@author Will Provost
*/
public class Functional
{
    /**
    A custom functional interface for event notification.
    */
    @FunctionalInterface
    public static interface Callback
    {
        /**
        Call to notify of a given event.

        @param event Name or string representation of the event
        */
        public void notifyMe (String event);

        /**
        Decorates the {@link #notifyMe main notification method}
        by prepending a timestamp to the event string.
        */
        public default void notifyWithTimestamp (String event)
        {
            notifyMe (LocalDateTime.now() + ": " + event);
        }
    }

    /**
    Primary method that (hypothetically) does some long-running work and
    (actually) calls the notification method on the given callback object.

    @param thingToDo The name of the event that will be provided in the notification call
    @param callback A callback implementation to receive the notification
    */
    public static void doSomethingAndNotifyMe
        (String thingToDo, Callback callback)
    {
        callback.notifyMe (thingToDo);
    }

    /**
    Enhanced method that provides notification with a timestamp,
    taking advantage of the new
    {@link #Callback.notifyWithTimestamp default method} on the
    callback interface.
    */
    public static void doSomethingAndNotifyMeWithATimestamp
        (String thingToDo, Callback callback)
    {
        callback.notifyWithTimestamp (thingToDo);
    }

    /**
    A static method that can act as an implementation of the callback interface
    via a method reference. Writes a report to the console.
    */
    public static void notificationSink (String event)
    {
        System.out.println ("Static method received: " + event);
    }

    /**
    Here we exercise the notification/callback system in various ways.
    */
    public static void main (String[] args)
    {
        // Anonymous inner class:
        doSomethingAndNotifyMe ("Hop", new Functional.Callback ()
            {
                public void notifyMe (String ev)
                {
                    System.out.println
                        ("Anonymous class received: " + ev);
                }
            });

        // Lambda expressions:
        doSomethingAndNotifyMe ("Skip",
            ev -> System.out.println ("Lambda 1 received: " + ev));

        Callback impl1 = ev -> System.out.println ("Lambda 2 received: " + ev);
        doSomethingAndNotifyMe ("Jump", impl1);

        // Method references:
        doSomethingAndNotifyMe ("Skate", Functional::notificationSink);

        Callback impl2 = Functional::notificationSink;
        doSomethingAndNotifyMe ("Pass", impl2);

        // Triggering use of a default method:
        doSomethingAndNotifyMeWithATimestamp ("Shoot",
            ev -> System.out.println ("Lambda 3 received: " + ev));
        doSomethingAndNotifyMeWithATimestamp ("Shoot", impl2);
    }
}
