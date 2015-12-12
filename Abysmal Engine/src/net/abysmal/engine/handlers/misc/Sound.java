package net.abysmal.engine.handlers.misc;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static synchronized void playSound(String url, boolean loop) {
		try {
			Clip c = AudioSystem.getClip();
			AudioInputStream is = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream(url));
			c.open(is);
			c.loop(loop ? -1:1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
