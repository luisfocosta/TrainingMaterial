package com.amica.mars;

/**
 * In this version, all state and behavior is defined in static members,
 * as static functions manipulate static variables. 
 * 
 * @author Will Provost
 */
public class Rover {

	public static int x;
	public static int y;
	public static int direction;
	
	public static void turnLeft() {
		direction = (direction + 3) % 4;
	}
	
	public static void turnRight() {
		direction = (direction + 1) % 4;
	}
	
	public static void move() {
		if (direction == 0) {
			++y;
		} else if (direction == 1) {
			++x;
		} else if (direction == 2) {
			--y;
		} else if (direction == 3) {
			--x;
		}  
	}

	public static void move(int distance) {
		for (int i = 0; i < distance; ++i) {
			move();
		}
	}
	
	public static String getStatus() {

		String dirString = "";
		if (direction == 0) {
			dirString = "north";
		} else if (direction == 1) {
			dirString = "east";
		} else if (direction == 2) {
			dirString = "south";
		} else if (direction == 3) {
			dirString = "west";
		}
		
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
