import java.util.Scanner;

import audio.SFX;
import audio.musicThread;

public class AudioTesting {

	public static void main(String[] args) throws InterruptedException {
		Scanner kb = new Scanner(System.in);
		musicThread kek = new musicThread("cena");
		Thread t = new Thread(kek);
		t.start();
		Thread.sleep(5000);
		t.interrupt();
		//Thread.sleep(5000);
		kek.changeTrack("besaid");
		t = new Thread(kek);
		t.start();
		new SFX("cena");
	}
}
