package com.amica.mars;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Base class for Rovers that can report observations to a Consumer<String>.
 * A helper method prefixes reports with the rover's current 
 * position and direction.
 * 
 * @author Will Provost
 */
public abstract class ReportingRover extends Rover {

	private Optional<Consumer<String>> subscriber = Optional.empty();
	
	public Consumer<String> getSubscriber() {
		return subscriber.isPresent() ? subscriber.get() : null;
	}
	
	public void subscribe(Consumer<String> subscriber) {
		this.subscriber = Optional.of(subscriber);
	}
	
	public void unsubscribe() {
		subscriber = Optional.empty();
	}
	
	protected void report(String finding) {
		subscriber.ifPresent(s -> s.accept(String.format("(%2d,%2d,%-5s) %s", 
				getX(), getY(), getDirectionString(), finding)));
	}
}
