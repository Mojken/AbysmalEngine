package net.abysmal.engine.handlers.misc;

import java.io.File;
import java.io.FileNotFoundException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** @author Deepstream */
public class Sound {

	private File soundFile;
	public Clip clip;

	/**
	 * Abysmal object for sound
	 * 
	 * @param path
	 *            path of sound file
	 * @throws FileNotFoundException
	 *             if the specified file can't be found
	 */
	public Sound(String path) throws FileNotFoundException {
		if (path == null)
			throw new NullPointerException();
		soundFile = new File(path);
		if (!soundFile.exists())
			throw new FileNotFoundException("The specified sound file can not be found");
	}

	/**
	 * @param sound
	 *            input file
	 * @throws FileNotFoundException
	 *             if the specified file is not found.
	 * @throws NullPointerException
	 *             if the specified file is null.
	 */
	public Sound(File soundFile) throws FileNotFoundException {
		if (soundFile == null)
			throw new NullPointerException();
		if (!soundFile.exists())
			throw new FileNotFoundException("The specified sound file can not be found");
		this.soundFile = soundFile;
	}

	/** Opens the clip. */
	public void open() {
		try {
			clip = AudioSystem.getClip();
			AudioInputStream is = AudioSystem.getAudioInputStream(soundFile);
			clip.open(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Releases all the system resources used by this clip. */
	public void close() {
		clip.flush();
		clip.close();
	}

	/**
	 * Plays a sound clip.
	 * 
	 * @param loop
	 *            set to true if the clip should loop for ever.
	 */
	public synchronized void play(boolean loop) {
		new Thread(new Runnable() {

			public void run() {
				try {
					clip = AudioSystem.getClip();
					AudioInputStream is = AudioSystem.getAudioInputStream(soundFile);
					clip.open(is);
					// clip.loop(loop ? -1:1);
					clip.start();
					Thread.sleep(1);
					while (clip.isActive()) {
						Thread.sleep(1);
					}
					if (!clip.isActive()) {
						clip.flush();
						clip.close();
					}
				} catch (Exception e) {
				}
			}
		}).start();

	}
}
