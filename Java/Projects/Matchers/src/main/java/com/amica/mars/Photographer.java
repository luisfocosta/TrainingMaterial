package com.amica.mars;

/**
 * A Rover that takes photographs automatically on each movement.
 * 
 * @author Will Provost
 */
public class Photographer extends ReportingRover {

	@Override
	public void move() {
		super.move();
		report("Took some pictures!");
	}
}
