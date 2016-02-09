package audio;

import java.io.File;

import javafx.scene.media.AudioClip;

public class SFX {
	public SFX(String s){
		AudioClip sound = new AudioClip(new File("src/audio/" + s + ".mp3").toURI().toString());
		sound.play();
	}
}
