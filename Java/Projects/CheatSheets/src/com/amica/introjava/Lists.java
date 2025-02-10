package com.amica.introjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple examples of using List and ArrayList types.
 * 
 * @author Will Provost
 */
public class Lists {
	public static void main(String[] args) {
		
		// Create a new array-list, and add a few things to it:
		List<String> names = new ArrayList<>();
		names.add("A");
		names.add("B");
		names.add(0, "C"); // insert before index 0
		names.add("D");
		
		// These would do the same thing, since "A" is at index 1
		//names.remove("A");
		//names.remove(1);
		
		System.out.println(names);
		
		// Traverse the list and print each item:
		for (String name : names) {
			System.out.println(name);
		}
		
		// Get the second item; get the last item
		System.out.println("2nd item is " + names.get(1));
		System.out.println("Last item is " + names.get(names.size() - 1));
		
		// Safely remove elements from the list based on a test:
		List<String> doomed = new ArrayList<>();
		for (String name : names) {
			if (name.equals("A")) {
				doomed.add(name);
			}
		}
		names.removeAll(doomed);
		System.out.println(names);
	}
}
