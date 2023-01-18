package main;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
	private Clip m_clip;
	private URL m_soundURL[] = new URL[30];

	// Java enums make me sad
	public enum SoundByte
	{
		// Keep these values in like with the values in soundURL
		eMainTheme(0),
		eCoin(1),
		ePowerup(2),
		eUnlock(3),
		eFanFare(4);

		private int m_value;
		private static Map<Integer, SoundByte> map = new HashMap<Integer, SoundByte>();

		private SoundByte(int value)
		{
			m_value = value;
		}

		static
		{
			for (SoundByte eSoundByte:SoundByte.values())
				map.put(eSoundByte.m_value, eSoundByte);
		}

		public static SoundByte valueOf(int soundByte) { return (SoundByte)map.get(soundByte); }
		public int getValue() { return m_value; }
	}

	public Sound()
	{
		m_soundURL[0] = getClass().getResource("../res/sound/BlueBoyAdventure.wav");
		m_soundURL[1] = getClass().getResource("../res/sound/coin.wav");
		m_soundURL[2] = getClass().getResource("../res/sound/powerup.wav");
		m_soundURL[3] = getClass().getResource("../res/sound/unlock.wav");
		m_soundURL[4] = getClass().getResource("../res/sound/fanfare.wav");
	}

	public void setSoundFile(SoundByte eSoundByte)
	{
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(m_soundURL[eSoundByte.getValue()]);
			m_clip = AudioSystem.getClip();
			m_clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play()
	{
		m_clip.start();
	}

	public void loop()
	{
		m_clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop()
	{
		m_clip.stop();
	}
}
