package com.amica.mars;

/**
 * A Rover that can use ground-penetrating radar to develop an image of
 * the subsurface. Because this is an energy-intensive operation,
 * unlike other subclasses, this one does not make its observations
 * automatically when it moves. Instead it processes a special command. 
 * 
 * @author Will Provost
 */
public class GroundPounder extends ReportingRover {
	
	@Override
	protected void execute(char command) {
		if (command == 'P') {
			report("Pounded some ground!");
		} else {
			super.execute(command);
		}
	}
}
