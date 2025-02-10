package com.amica.mars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * An implementation of Telemetry that simply keeps all received messages,
 * in a list.
 * 
 * We keep this around for reference, but an even simpler approach in
 * {@link TestProgram3} makes the class obsolete for this version 
 * of the application.
 * 
 * @author Will Provost
 */
public class Recorder implements Consumer<String> {

	private List<String> record = new ArrayList<>();
	
	public List<String> getRecord() {
		return Collections.unmodifiableList(record);
	}
	
	public void accept(String message) {
		record.add(message);
	}

}
