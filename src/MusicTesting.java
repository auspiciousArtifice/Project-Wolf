import java.util.Scanner;

import audio.musicThread;

public class MusicTesting {

	public static void main(String[] args) throws InterruptedException {
		Scanner kb = new Scanner(System.in);
		musicThread kek = new musicThread("cena");
		Thread t = new Thread(kek);
		t.start();
		Thread.sleep(5000);
		t.interrupt();
		kek.changeTrack("besaid");
		t = new Thread(kek);
		t.start();
		System.out.println("why");
	}
}
