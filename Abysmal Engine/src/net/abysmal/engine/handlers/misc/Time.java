package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.Constants;
import net.abysmal.engine.main.FundamentalGameSpecifics;

public class Time {

	Thread clock;
	public boolean proceedTime = false;

	public static final String MILLIS = "MILLIS";
	public static final String SECS = "SECS";
	public static final String MINS = "MINS";
	public static final String HOURS = "HOURS";

	/** Abysmal method to tell time in different formats.
	 * 
	 * @param Format
	 *            format of the time returned
	 * 
	 * @return current system time as a long */
	public static long getTime(String Format) {
		switch (Format) {
			case MILLIS:
				return System.currentTimeMillis();
			case SECS:
				return (long) Math.floor(System.currentTimeMillis() / 1000);
			case MINS:
				return (long) (Math.floor(System.currentTimeMillis() / 1000) / 60);
			case HOURS:
				return (long) (Math.floor((System.currentTimeMillis() / 1000) / 60) / 60);
		}
		return 0;
	}

	/** Abysmal method for determining how much time has elapsed.
	 * 
	 * @param start
	 *            timestamp to measure the elapsed time from
	 * @return elapsed time */

	public static int getTimeElapsed(long start) {
		return (int) (getTime(MILLIS) - start);
	}

	double currentTime = 100000.0;

	void clock() {
		clock = new Thread("Clock") {

			public void run() {
				int counter = 0;
				while (proceedTime) {
					counter++;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					currentTime += (0.01 / Constants.TIME_TO_2DM) * FundamentalGameSpecifics.getTimeSpeed();
					if (currentTime >= 200000) currentTime -= 200000;
					if (counter % 100 == 0) {
					}
				}
			}
		};
		clock.start();
		proceedTime = true;
	}

	/**
	 * Abysmal method for telling the in-game time
	 * @return current time, an int between 0 - 200000
	 * @see setCurrentTime()
	 */
	
	public int getCurrentTime() {
		return (int) currentTime;
	}

	/**
	 * Abysmal method to set the in-game time.
	 * @param currentTime int to set the current in-game time to.
	 * @see getCurrentTime()
	 */
	
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
}