package net.abysmal.engine.handlers.misc;

public class Time {

	public static final String MILLIS = "MILLIS";
	public static final String SECS = "SECS";
	public static final String MINS = "MINS";
	public static final String HOURS = "HOURS";

	/**
	 * Abysmal method to tell time in different formats.
	 * 
	 * @param Format format of the time returned
	 * 
	 * @return current system time as a long
	 */
	public static long getTime(String Format) {
		switch (Format) {
			case MILLIS: return System.currentTimeMillis();
			case SECS: return (long) Math.floor(System.currentTimeMillis() / 1000);
			case MINS: return (long) (Math.floor(System.currentTimeMillis() / 1000) / 60);
			case HOURS: return (long) (Math.floor((System.currentTimeMillis() / 1000) / 60) / 60);
		}
		return 0;
	}

	/** Abysmal method for determining how much time has elapsed.
	 * 
	 * @param start time to mesure the time from
	 * @return elapsed time
	 */
	
	public static int getTimeElapsed(long start) {
		return (int) (getTime(MILLIS) - start);
	}

}