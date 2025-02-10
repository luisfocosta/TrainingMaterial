package com.amica.race;

/**
 * Information for one runner in a race.
 * 
 * @author Will Provost
 */
public class Result {

	private String name;
	private int age;
	private int minutes;
	private int seconds;
	
	public Result(String name, int age, int minutes, int seconds) {
		this.name = name;
		this.age = age;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public int getTime() {
		return minutes * 60 + seconds;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Result &&
				((Result) other).getName().equals(name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		return name;
	}
}
