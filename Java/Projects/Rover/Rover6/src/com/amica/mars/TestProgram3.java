package com.amica.mars;

import java.util.ArrayList;
import java.util.List;

/**
 * This program creates one instance of each of three Rover subclasses,
 * connects them all to a {@link Recorder}, runs each through a different 
 * command string, and prints out the recorded messages. 
 *
 * @author Will Provost
 */
public class TestProgram3 {

	public static void main(String[] args) {

		List<String> record = new ArrayList<>();
		
		ReportingRover soilSampler = new SoilSampler();
		soilSampler.subscribe(record::add);
		soilSampler.receiveCommands("4R2R1L2");

		ReportingRover photographer = new Photographer();
		photographer.subscribe(record::add);;
		photographer.receiveCommands("L4R2L1");

		ReportingRover groundPounder = new GroundPounder();
		groundPounder.subscribe(record::add);;
		groundPounder.receiveCommands("RR3PL1R1PL1R1P");
		
		List<Rover> rovers = new ArrayList<>();
		rovers.add(soilSampler);
		rovers.add(photographer);
		rovers.add(groundPounder);
		
		boolean running = true;
		while (running) {
			
			boolean anyStillRunning = false;
			for (Rover rover : rovers) {
				rover.takeNextStep();
				if (rover.isBusy()) {
					anyStillRunning = true;
				}
			}
			if (!anyStillRunning) {
				running = false;
			}
		}
		
		System.out.println("All reports, in chronological order:");
		record.stream().map(r -> "  " + r).forEach(System.out::println);
		System.out.println();
		
		System.out.println("Just the GPR reports:");
		record.stream().filter(r -> r.contains("Pounded"))
				.map(r -> "  " + r).forEach(System.out::println);
		System.out.println();
	}
	
}
