package com.amica.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.DoubleStream;

/**
 * A stream-based utility for finding the Pearson correlation coefficient
 * between two properties of a collection of objects.
 * 
 * @author Will Provost
 */
public class Correlation {

	private static <T> DoubleStream propStream(Collection<T> data, Function<T,Double> prop) {
		return data.stream().mapToDouble(d -> prop.apply(d));
	}
	public static <T> double fromInts(Collection<T> data, 
			Function<T,Number> xProperty, Function<T,Number> yProperty) {
		return fromDoubles(data, d -> xProperty.apply(d).doubleValue(),
				d -> yProperty.apply(d).doubleValue());
	}
	
	public static <T> double fromDoubles(Collection<T> data, 
				Function<T,Double> xProperty, Function<T,Double> yProperty) {
		
		long count = data.size();
		double xSum = propStream(data, xProperty).sum();
		double ySum = propStream(data, yProperty).sum();
		double xxSum = propStream(data, xProperty).map(x -> x * x).sum();
		double yySum = propStream(data, yProperty).map(y -> y * y).sum();
		
		Iterator<Double> x = propStream(data, xProperty).iterator();
		double xySum = propStream(data, yProperty).map(y -> x.next() * y).sum();
		
		return ((count * xySum - xSum * ySum) / Math.sqrt(count * xxSum - xSum * xSum) 
				/ Math.sqrt(count * yySum - ySum * ySum));
	}
	
	public static void main(String[] args) {
		
		String[] sunlightAndTemperature = {
			"8 40",
			"9 42",
			"10 45",
			"11 52",
			"12 62",
			"13 75",
			"14 78",
			"15 80",
			"16 81"
		};
		
		System.out.format("Correlation between hours of sunlight and high temperature:%n  %1.4f%n", 
			fromInts(Arrays.asList(sunlightAndTemperature), 
				s -> Integer.parseInt(s.split(" ")[0]),
				s -> Integer.parseInt(s.split(" ")[1])));
				
		
		String[] wantAndGet = {
			"1 6",
			"2 3",
			"10 4",
			"5 3",
			"3 4",
			"2 2",
			"9 8"
		};
		
		System.out.format("Correlation between what we want and what we get:%n  %1.4f%n", 
			fromInts(Arrays.asList(wantAndGet), 
				s -> Integer.parseInt(s.split(" ")[0]),
				s -> Integer.parseInt(s.split(" ")[1])));
	}
}
