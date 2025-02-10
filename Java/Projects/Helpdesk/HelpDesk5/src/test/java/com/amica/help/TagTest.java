package com.amica.help;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.jupiter.api.Test;

/**
 * Unit test for the {@link Tag} class.
 * 
 * @author Will Provost
 */
public class TagTest {

	@Test
	public void testComparison() {
		assertThat(new Tag("B"), greaterThan(new Tag("A")));
	}
}
