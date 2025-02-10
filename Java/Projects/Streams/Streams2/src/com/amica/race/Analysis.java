package com.amica.race;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.amica.streams.Correlation;

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
	
		try ( Stream<String> lines = Files.lines(Paths.get("Times.csv")); ) {

			List<Result> results = lines
					.map(line -> line.split(","))
					.map(fields -> new Result(fields[0], 
							Integer.parseInt(fields[1]),
							Integer.parseInt(fields[2]), 
							Integer.parseInt(fields[3])))
					.collect(Collectors.toList());
			
			System.out.format("There were %d runners.%n", results.size());
			System.out.format("There were %d runners age 40 or older.%n", 
					results.stream().filter(r -> r.getAge() > 39).count());
			
			System.out.format("The mean time was %s.%n", 
					formatTime(results.stream().mapToInt(Result::getTime)
						.average().getAsDouble()));
			System.out.format("The mean time among 40+ runners was %s.%n", 
					formatTime(results.stream().filter(r -> r.getAge() > 39)
						.mapToInt(Result::getTime)
						.average().getAsDouble()));
			
			Comparator<Result> byTime = Comparator.comparing(Result::getTime); 
			Result winner = results.stream().min(byTime).get();
			System.out.format("The winner is %s with a time of %s.%n",
					winner.getName(), formatTime(winner.getTime()));
			
			System.out.println("Results grouped by age:");
			Map<Integer,List<Result>> groups = results.stream()
					.collect(Collectors.groupingBy(r -> r.getAge() / 10));
			for (int age : groups.keySet()) {
				List<Result> group = groups.get(age);
				winner = group.stream().sorted(byTime).findFirst().get();
				System.out.format("  Ages %d-%d: %d runners, "
					+ "winner was %s with a time of %s.%n", 
						age * 10, age * 10 + 9, group.size(), winner.getName(), 
						formatTime(winner.getTime()));
			}
			
			System.out.println("Senior times in ascending order:");
			results.stream().filter(r -> r.getAge() >= 40)
				.mapToInt(Result::getTime)
				.sorted()
				.mapToObj(Analysis::formatTime)
				.map(t -> "  " + t)
				.forEach(System.out::println);
			
			System.out.println();
			System.out.format("Correlation betwen age and finishing time: %1.4f%n",
					Correlation.fromInts(results, Result::getAge, Result::getTime));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
