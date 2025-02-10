package com.amica.shopping;

import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

/**
 * A hard-coded catalog of available products.
 *
 * @author Will Provost
 */
public class Catalog {

	public static class BySKU implements Comparator<Product> {
		public int compare(Product a, Product b){
			return a.getSKU().compareTo(b.getSKU());
		}
	}

	public static int compareByDepartmentAndName(Product a, Product b){
		return (a.getDepartment()+a.getName()).compareTo(b.getDepartment()+b.getName());
	}
	
	private static List<Product> products = new ArrayList<>();

	static {
		products.add(new Product("573814", "Kelty Companion", 299.99, "Tents"));
		products.add(new Product("002483", "Sierra Designs Lhasa", 229.99, "Tents"));
		products.add(new Product("867822", "Sierra Designs Winter Warmer", 429.99, "Tents"));
		products.add(new Product("919137", "MSR Soloist", 279.99, "Tents"));
		products.add(new Product("237899", "Mountain Hard Wear LiteNite", 189.99, "Sleeping bags"));
		products.add(new Product("801167", "Kelty Cocoon", 279.99, "Sleeping bags"));
		products.add(new Product("946345", "REI Zero-Tolerance", 359.99, "Sleeping bags"));
		products.add(new Product("449216", "Thermarest Basic", 40.99, "Mattresses"));
		products.add(new Product("794551", "Thermarest Techno", 54.99, "Mattresses"));
		products.add(new Product("834044", "REI Foam", 29.99, "Mattresses"));
		products.add(new Product("851900", "Pockie Daybag", 179.99, "Backpacks"));
		products.add(new Product("547310", "Gregory Z", 299.99, "Backpacks"));
		products.add(new Product("928752", "Lowe Ascent", 219.99, "Backpacks"));
		products.add(new Product("118943", "North Face Denali", 379.99, "Backpacks"));

		Collections.sort(products, new BySKU());
		Collections.sort(products, (a,b) -> a.getSKU().compareTo(b.getSKU()));
		products.sort((a,b) -> a.getSKU().compareTo(b.getSKU()));

		Collections.sort(products, Collections.reverseOrder(new BySKU()));
		Collections.sort(products, (a,b) -> b.getSKU().compareTo(a.getSKU()));

		products.sort(Comparator.comparing(Product::getSKU));
		products.sort(Comparator.comparing(Product::getPrice));

		Collections.sort(products, Catalog::compareByDepartmentAndName);
	}
	
	public static List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}
	
	public static void main(String[] args) {
//		for (Product product : Catalog.getProducts()) {
//			System.out.println(product);
//		}

		int[] numbers = new int[]{1,-5,4};

		int[] intFromStream = IntStream.of(numbers).toArray();
		intFromStream = IntStream.of(1,5,-2).toArray();
		intFromStream = IntStream.range(1,7).toArray();
		intFromStream = IntStream.rangeClosed(1,7).toArray();

		for(int i : intFromStream){
			System.out.format("%d, ", i);
		}

		System.out.println();

		OptionalDouble maxPrice = products.stream().mapToDouble(Product::getPrice).max();
		System.out.println(maxPrice.getAsDouble());

		//Projects/Rover/RoverN/src
		IntStream.rangeClosed(1,6).mapToObj(i -> "/Projects/Rover/Rover" + String.valueOf(i) + "/src")
				.map(Paths::get)
				.forEach(System.out::println);

	}
}