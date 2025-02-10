package com.amica;

public class Utility {
	public static void checkNotNull(Object ref, String label) {
		if (ref == null) {
			throw new IllegalArgumentException("The " + label + "must be provided.");
		}
	}
}
