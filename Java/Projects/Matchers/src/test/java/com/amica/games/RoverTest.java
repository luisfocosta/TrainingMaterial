package com.amica.games;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import com.amica.mars.Rover;
import com.amica.mars.Rover.Direction;

/**
 * An illustration of a custom matcher that exposes a fluent API.
 * 
 * @author Will Provost
 */
public class RoverTest {

	public static class IsAt {
		private int x;
		private int y;
		
		public IsAt(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Matcher<Rover> facing(Direction direction) {
			return allOf(instanceOf(Rover.class),
					hasProperty("x", equalTo(x)),
					hasProperty("y", equalTo(y)),
					hasProperty("direction", equalTo(direction)));
		}
	}
	public static IsAt isAt(int x, int y) {
		return new IsAt(x, y);
	}
	
	private Rover rover = new Rover();
	
	public void takeSteps(int number) {
		for (int i = 0; i < number; ++i) {
			rover.takeNextStep();
		}
	}
	
	public void takeAllSteps() {
		while(rover.isBusy()) {
			rover.takeNextStep();
		}
	}
	
	@Test
	public void testMovement() {
		rover.receiveCommands("2R1");
		rover.takeNextStep();
		assertThat(rover, isAt(0, 1).facing(Direction.NORTH));
		takeAllSteps();
		assertThat(rover, isAt(1, 2).facing(Direction.EAST));
		
	}
}
