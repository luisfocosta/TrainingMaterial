package com.amica.mars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of Telemetry that simply keeps all received messages,
 * in a list.
 * 
 * @author Will Provost
 */
public class Recorder implements Telemetry {

	private List<String> record = new ArrayList<>();
	
	public List<String> getRecord() {
		return Collections.unmodifiableList(record);
	}
	
	public void sendMessage(String message) {
		record.add(message);
	}

}
