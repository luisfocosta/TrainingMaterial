/*
Copyright 2005, 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.primes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
Graphical user interface to the primes finder.

@author Will Provost
*/
public class GUI
    extends JFrame
{
    private FindPrimes worker;
    
    private JTextField txPrime = new JTextField (8);
    private JToggleButton bnStart = new JToggleButton ("Start");
    
    private Consumer<Long> updateHandler;
    private Consumer<Long> consoleHandler;

    private Thread finder;
    
    /**
    Calls {@link FindPrimes#find FindPrimes.find}.
    It catches exceptions to show a message box and reset the button to "Start".
    */
    public void find ()
    {
        try
        {
            worker.find ();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog (GUI.this, 
                "Exception: " + ex.getClass ().getName () + 
                " saying \"" + ex.getMessage () + "\"");
            finder = null;
            bnStart.setText ("Start");
            bnStart.setSelected (false);
        }
    }

    /**
    Button handler either starts a new 
    {@link #Finder} thread, or cancels an existing one.
    Toggles the button text from "Start" to "Cancel" and back.
    */
    public void toggle (ActionEvent ev)
    {
        if (finder != null)
        {
            worker.cancel ();
            finder = null;
            bnStart.setText ("Start");
        }
        else
        {
            finder = new Thread (this::find);
            finder.start ();
            bnStart.setText ("Cancel");
        }
    }
    
    /**
    Creates and lays out GUI and wires up event handlers.
    */
    public GUI (FindPrimes worker)
    {
        super ("Find Primes");
        
        this.worker = worker;
        
        JPanel panel = new JPanel (new GridLayout (2, 1));
        panel.setBorder (BorderFactory.createEmptyBorder (18, 18, 18, 18));
        
        JPanel pnPrime = new JPanel (new FlowLayout (FlowLayout.CENTER));
        pnPrime.setBorder (BorderFactory.createEmptyBorder (8, 8, 8, 8));
        pnPrime.add (txPrime);
        panel.add (pnPrime);
        
        JPanel pnStart = new JPanel (new FlowLayout (FlowLayout.CENTER));
        pnStart.setBorder (BorderFactory.createEmptyBorder (8, 8, 8, 8));
        pnStart.add (bnStart);
        panel.add (pnStart);
        
        getContentPane ().add (BorderLayout.CENTER, panel);
        pack ();
        
        addWindowListener (new WindowAdapter ()
            {
                public void windowClosing (WindowEvent ev)
                {
                    worker.removePrimesListener (updateHandler);
                    worker.removePrimesListener (consoleHandler);
                    System.exit (0);
                }
            } );
            
        worker.addPrimesListener (updateHandler = 
            prime -> txPrime.setText (prime.toString ()));
        worker.addPrimesListener (consoleHandler = 
            prime -> System.out.print (" " + prime));

        bnStart.addActionListener (this::toggle);
    }
    
    /**
    Creates a {@link FindPrimes} engine, sets it to find primes in the range
    1-100000, and passes it to the GUI.
    */
    public static void main (String[] args)
    {
        FindPrimes worker = new FindPrimes (100000);
        //new StressTest (worker).start ();
        new GUI (worker).setVisible (true);
    }
}

