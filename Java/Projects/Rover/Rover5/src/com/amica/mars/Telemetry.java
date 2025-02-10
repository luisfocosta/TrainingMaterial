package com.amica.mars;

/**
 * Represents the capability to receive telemetry.
 * Provided to the {@link ReportingRover}.
 * 
 * @author Will Provost
 */
public interface Telemetry {
	public void sendMessage(String message);
}
