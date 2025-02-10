package com.amica.help;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static com.amica.Utility.checkNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.stream.Stream;

import lombok.extern.java.Log;

/**
 * This class manages keyword tags, assuring that only one instance of the
 * {@link Tag} class is held for a given string, comparing the strings wihtout
 * sensitivity to case. It also maps synonyms to tags, and assures that any
 * attempt to resolve a string to a tag takes those synonyms into account.
 * 
 * @author Will Provost
 */
@Log
public class Tags {

	private TreeSet<Tag> tags = new TreeSet<>();
	private Map<String, Tag> synonyms = new HashMap<>();

	/**
	 * Create an empty tag repository.
	 */
	public Tags () {}
	
	/**
	 * Load synonyms from a thesaurus file.
	 */
	public Tags(String thesaurusFilename) {
		try ( Stream<String> lines = Files.lines(Paths.get(thesaurusFilename)); ) {
			lines.map(line -> line.split("=")).forEach(nv -> addSynonym(nv[0], nv[1]));
		} catch (IOException ex) {
			log.log(Level.WARNING, "Couldn't load thesaurus.", ex);
		}
	}
	
	/**
	 * Returns a view of all tags.
	 */
	public SortedSet<Tag> getTags() {
		return Collections.unmodifiableSortedSet(tags);
	}

	/**
	 * Returns a view of known synonyms.
	 */
	public Map<String, Tag> getSynonyms() {
		return Collections.unmodifiableMap(synonyms);
	}

	/**
	 * Adds a synonym to our dictionary. This can have the effect of adding
	 * the "term" (the thing for which the first parameter is a synonym)
	 * as a tag for the first time; or it will find an existing tag that matches. 
	 */
	public void addSynonym(String synonym, String term) {
		checkNotNull(synonym, "synonym");
		checkNotNull(term, "term");
		synonyms.put(synonym.toLowerCase(), getTag(term));
	}

	/**
	 * Gets an existing tag with a matching value, or adds a new tag with
	 * the given value and returns that. If the given value is a synonym,
	 * returns the tag for the translated term. 
	 */
	public Tag getTag(String value) {
		checkNotNull(value, "value");
		
		if (synonyms.containsKey(value.toLowerCase())) {
			return synonyms.get(value.toLowerCase());
		}

		Tag candidate = new Tag(value);
		SortedSet<Tag> found = tags.subSet(candidate, true, candidate, true);
		if (!found.isEmpty()) {
			return found.first();
		}
		
		tags.add(candidate);
		return candidate;
	}
}
		
