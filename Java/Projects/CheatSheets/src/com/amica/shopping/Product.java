package com.amica.shopping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an available product for the retailer.
 *
 * @author Will Provost
 */
@Getter
@AllArgsConstructor
public class Product {

	private String SKU;
	private String name;
	private double price;
	@Setter
	private String department;
	
	@Override
	public String toString() {
		return String.format("%s %s, $%1.2f (%s)", SKU, name, price, department);
	}
}
