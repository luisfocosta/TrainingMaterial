package com.amica.help;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the {@link Tags} class.
 * 
 * @author Will Provost
 */
public class TagsTest {

	public static final String CAPS = "TAG";
	public static final String MIXED = "Tag";
	public static final String SYNONYM1 = "SYNONYM1";
	public static final String SYNONYM2 = "SYNONYM2";
	public static final String TERM = "TERM";
	
	private Tags tags;
	
	@BeforeEach
	public void setUp() {
		tags = new Tags();
		
		tags.getTag(CAPS);
		tags.addSynonym(SYNONYM1, TERM);
		tags.addSynonym(SYNONYM2, TERM);
	}
	
	@Test
	public void testCapitalization() {
		assertThat(tags.getTag(CAPS).getValue(), equalTo(CAPS));
		assertThat(tags.getTag(MIXED).getValue(), equalTo(CAPS));
		assertThat(tags.getTag(MIXED), equalTo(tags.getTag(CAPS)));
	}

	@Test
	public void testSynonyms() {
		assertThat(tags.getTag(SYNONYM1), equalTo(tags.getTag(SYNONYM2)));
		assertThat(tags.getTag(SYNONYM1).getValue(), equalTo(TERM));
	}
}
