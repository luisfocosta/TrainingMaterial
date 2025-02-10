package com.amica.text;

import lombok.extern.java.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class utility that can count words and provide other statistics
 * given a stream of strings.
 * 
 * @author Will Provost
 */
@Log
public class WordCount {

	public static Stream<String> toWords(String contents) {
		return Stream.of(contents.split("[\\s\\.,]+"))
				.filter(word -> word.matches("^[A-Za-z\\-]+$"))
				.filter(word -> word.length() > 2);
	}
	
	public static Stream<String> toDistinctWords(String contents) {
		return toWords(contents).map(String::toLowerCase).distinct();
	}
	
	public static String countWords(InputStream in) {
		try ( BufferedReader reader = 
				new BufferedReader(new InputStreamReader(in))) {
			
			String contents = reader.lines().collect(Collectors.joining("\n"));
			long wordCount = toWords(contents).count();
			long distinctWordCount = toDistinctWords(contents).count();

			double averageWordLength = toWords(contents)
					.mapToInt(String::length)
					.average().getAsDouble();
			
			Map<Character,Long> countByFirstLetter = 
				toDistinctWords(contents)
					.collect(Collectors.groupingBy
						(word -> word.toUpperCase().charAt(0), 
							Collectors.counting()));
			
			return String.format
				("Words=%d, distinct=%d, weighted-average word length=%1.2f%n"
						+ "Distinct words by first letter:%n%s",
					wordCount, distinctWordCount, averageWordLength,
					countByFirstLetter.entrySet().stream()
						.map(entry -> String.format("  %s %4d", 
							entry.getKey(), entry.getValue()))
						.collect(Collectors.joining
							(System.getProperty("line.separator"))));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return "ERROR";
	}

	public static void writeWordsToFile(InputStream in, String outputFileName){
		String contents = null;
		log.info("Attempting to read from file");
		try ( BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			contents = reader.lines().collect(Collectors.joining("\n"));
		} catch (IOException ex){
			ex.printStackTrace();
		}

		String wordsFromFile = toWords(contents).collect(Collectors.joining("\n"));

		try (FileOutputStream output = new FileOutputStream(outputFileName)){
			output.write(wordsFromFile.getBytes());
		} catch (IOException ex){
			log.warning("IOException: " + ex);
			ex.printStackTrace();
		}
	}

	public static void printWordsFromFile(String fileName){
		try ( Stream<String> contents = Files.lines(Paths.get(fileName)) ) {
			contents.map(String::toLowerCase).distinct().sorted().forEach(System.out::println);

		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(countWords(new FileInputStream("Speech.txt")));
			writeWordsToFile(new FileInputStream("Speech.txt"), "Output.txt");
			printWordsFromFile("Output.txt");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
