package com.amica.games;

import java.util.concurrent.ThreadLocalRandom;

public class OregonTrail {

	public static final int TRAVEL_DAYS_MIN = 3;
	public static final int TRAVEL_DAYS_MAX = 7;
	public static final int TRAVEL_MILES_MIN = 30;
	public static final int TRAVEL_MILES_MAX = 60;

	public static final int HUNTING_DAYS_MIN = 2;
	public static final int HUNTING_DAYS_MAX = 5;
	public static final int HUNTING_FOOD = 100;

	public static final int RESTING_DAYS_MIN = 2;
	public static final int RESTING_DAYS_MAX = 5;

	public static final int FOOD_PER_DAY = 5;

	public static int month = 3;
	public static int day = 1;
	public static int health = 5;
	public static int food = 500;
	public static int milesToGo = 2000;

	public static int randomNumber(int from, int to) {
		return ThreadLocalRandom.current().nextInt(from,to + 1);
	}

	public static boolean gotSickToday() {
		return randomNumber(1, 15) == 1;
	}

	public static int daysInMonth(int month) {
		return (month == 4 || month == 6 || month == 9 || month == 11)
				? 30 : 31;
	}

	public static void passOneDay() {
		++day;
		if (day > daysInMonth(month)) {
			day = 1;
			++month;
		}

		food -= FOOD_PER_DAY;
		if (gotSickToday()) {
			--health;
			System.out.println("Got sick.");
		}
	}

	public static void passSeveralDays(int from, int to) {
		int numberOfDays = randomNumber(from, to);
		for (int d = 0; d < numberOfDays; ++d) {
			passOneDay();
		}
	}

	public static void travel() {
		System.out.println("Traveling ...");
		passSeveralDays(TRAVEL_DAYS_MIN, TRAVEL_DAYS_MAX);
		milesToGo -= randomNumber(TRAVEL_MILES_MIN, TRAVEL_MILES_MAX);
	}

	public static void hunt() {
		System.out.println("Hunting ...");
		passSeveralDays(HUNTING_DAYS_MIN, HUNTING_DAYS_MAX);
		food += HUNTING_FOOD;
	}

	public static void rest() {
		System.out.println("Resting ...");
		passSeveralDays(RESTING_DAYS_MIN, RESTING_DAYS_MAX);
		++health;
	}

	public static void playOneTurn() {
		if (health < 3) {
			rest();
		} else if (food < 100) {
			hunt();
		} else {
			travel();
		}
	}

	public static void showStatus() {
		System.out.format("On %2d/%02d, %4d miles to go, food %3d, health %d.%n",
				(month - 1) % 12 + 1, day, milesToGo, food, health);
	}

	public static void main(String[] args) {
		showStatus();
		while (health > 0 && food > 0 && milesToGo > 0 && month < 13) {
			playOneTurn();
			showStatus();
		}

		if (health <= 0) {
			System.out.println("Sorry, you got too sick, and you died.");
		} else if (food <= 0) {
			System.out.println("Sorry, you ran out of food and starved.");
		} else if (month > 12){
			System.out.println("Sorry, winter came, and you froze to death.");
		} else {
			System.out.println("You made it!");
		}
	}
}
