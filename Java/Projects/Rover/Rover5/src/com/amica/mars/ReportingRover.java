package com.amica.mars;

/**
 * Base class for Rovers that can report observations via a {@link Telemetry}
 * interface. A helper method prefixes reports with the rover's current 
 * position and direction.
 * 
 * @author Will Provost
 */
public abstract class ReportingRover extends Rover {

	private Telemetry subscriber;
	
	public Telemetry getSubscriber() {
		return subscriber;
	}
	
	public void setSubscriber(Telemetry subscriber) {
		this.subscriber = subscriber;
	}
	
	protected void report(String finding) {
		if (subscriber != null) {
			subscriber.sendMessage(String.format("(%2d,%2d,%-5s) %s", 
					getX(), getY(), getDirectionString(), finding));
		}
	}
}
