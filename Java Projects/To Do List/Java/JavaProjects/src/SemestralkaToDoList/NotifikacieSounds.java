package SemestralkaToDoList;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/** 
 * tento enum mi bude robit zvuky opozornenia 
 * tak ze do konstruktora dam cestu k zvukovej stope 
 * a metoda playSound() mi to prehra pomocou AudioInput stream a Clip
 * **/
public enum NotifikacieSounds {
	BELL("happy_bells.wav"), MUSICAL("musical.wav"), REVEAL("reveal.wav"), DEFAULT("");

	private String nazov;

	/** Konštruktor **/
	NotifikacieSounds(String nazov) {
		this.nazov = nazov;

	}
	/** Táto metoda mi načita a prehra skladbu z Enumu **/
	public void playSound() {
		if (!this.nazov.equals("")) {
			File file = new File(this.nazov);

			AudioInputStream audioStream = null;
			try {
				audioStream = AudioSystem.getAudioInputStream(file);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			try {
				clip.open(audioStream);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			clip.start();
		}
	}

}
