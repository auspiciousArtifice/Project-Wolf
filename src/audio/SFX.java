package audio;

import javafx.scene.media.AudioClip;

public class SFX {
	public SFX(String s){
		AudioClip sound = new AudioClip(s);
		sound.play();
	}
}
