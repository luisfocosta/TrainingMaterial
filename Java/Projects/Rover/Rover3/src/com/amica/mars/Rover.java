package com.amica.mars;

/**
 * In this version, all state and behavior is defined in static members,
 * as static functions manipulate static variables. 
 * Direction is encoded as an integer, with 0 meaning north, 1 east, etc.
 * 
 * @author Will Provost
 */
public class Rover {

	public enum Direction { NORTH, EAST, SOUTH, WEST }
	
	public static int x;
	public static int y;
	public static Direction direction = Direction.NORTH;
	
	public static void turnLeft() {
		direction = Direction.values()[(direction.ordinal() + 3) % 4];
	}
	
	public static void turnRight() {
		direction = Direction.values()[(direction.ordinal() + 1) % 4];
	}
	
	public static void move() {
		if (direction == Direction.NORTH) {
			++y;
		} else if (direction == Direction.EAST) {
			++x;
		} else if (direction == Direction.SOUTH) {
			--y;
		} else if (direction == Direction.WEST) {
			--x;
		}  
	}

	public static void move(int distance) {
		for (int i = 0; i < distance; ++i) {
			move();
		}
	}
	
	public static String getStatus() {

		String dirString = direction.toString().toLowerCase();

		return String.format("The rover is now at (%d,%d), and facing %s.",
				x, y, dirString);
	}
	
	public static void main(String[] args) {
		
		String commands = "4R2R1L2";
		//String commands = "LL4R2R1L2";
		//String commands = "R4LL2R17";
		
		for (int i = 0; i < commands.length(); ++i) {
			char command = commands.charAt(i);
			if (command == 'L') {
				turnLeft();
			} else if (command == 'R') {
				turnRight();
			} else if (Character.isDigit(command)) {
				move(command - '0');
			} else {
				System.out.println("Unrecognized command: " + command);
				break;
			}
		}
		
		System.out.println(getStatus());
	}
}
