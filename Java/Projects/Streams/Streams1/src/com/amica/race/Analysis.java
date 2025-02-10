package com.amica.race;

/**
 * Application that reads a file of results from a race,
 * and runs a few queries over the data.
 * 
 * @author Will Provost
 */
public class Analysis {

	/**
	 * Helper method that formats total seconds as MM:SS.
	 */
	public static String formatTime(int time) {
		return String.format("%d:%02d", time / 60, time % 60);
	}
	
	/**
	 * Helper method that formats total seconds as MM:SS.hh.
	 */
	public static String formatTime(double time) {
		return String.format("%d:%02.2f", ((int) time) / 60, time % 60);
	}
	
	/**
	 * Loads a prepared CSV with race results, and runs queries on the data.
	 */
	public static void main(String[] args) {
	
		try {
			//TODO Load Times.csv into a List<Result>
			
			//TODO Total runners

			//TODO Runners 40 or older

			//TODO Mean finishing time

			//TODO mean time for 40+ runners

			//TODO Winner's name and finishing time

			//TODO By age groups e.g. 20-29, 30-39, etc.:
			//     how many in the group, winner's name and finishing time

			//TODO Finishing times for 40+ runners, in ascending order
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
