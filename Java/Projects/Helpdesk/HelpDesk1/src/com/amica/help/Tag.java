package com.amica.help;

/**
 * Class representing a keyword tag that can be applied to a {@link Ticket}.
 *
 * @author Will Provost
 */
public class Tag implements Comparable<Tag> {

	private String value;

	Tag(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof Tag && 
				value.equalsIgnoreCase(((Tag) other).getValue());
	}

	@Override
	public int hashCode() {
		return value.toLowerCase().hashCode();
	}

	public int compareTo(Tag other) {
		return value.compareToIgnoreCase(other.getValue());
	}
}
