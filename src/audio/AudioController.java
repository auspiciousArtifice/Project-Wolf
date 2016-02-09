package audio;
import java.util.Scanner;

import javafx.util.Duration;

public class AudioController {
	
	public static Duration audioDuration;

	public static void main(String[] args) throws InterruptedException {
		/*musicThread kek = new musicThread("cena");
		Thread t = new Thread(kek);
		t.setDaemon(true);
		t.start();
		Thread.sleep(5000);
		t.interrupt();*/
		//Thread.sleep(5000);
		//kek.changeTrack("besaid");
		musicThread kek = new musicThread();
		kek.changeTrack("cena");
		Thread t = new Thread(kek);
		t.start();
		Thread.sleep(5000);
		t.interrupt();
		t.join();
		System.out.println("fhdsfs" + audioDuration.toString());
		//kek.changeTrack("besaid");
		kek.changeVolume(1);
		t = new Thread(kek);
		t.start();
		while(true){
			System.out.print("stop");
			Thread.sleep(1000);
		}
		//new SFX("cena");
	}
	
	public void changeTrack(){
		
	}
}
