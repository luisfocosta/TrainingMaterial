package com.amica.mars;

/**
 * A Rover that takes soil samples automatically on each movement.
 * 
 * @author Will Provost
 */
public class SoilSampler extends ReportingRover {

	@Override
	public void move() {
		super.move();
		report("Sampled some soil!");
	}
}
