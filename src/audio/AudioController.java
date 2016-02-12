package audio;
import java.util.Scanner;

import javafx.util.Duration;

public class AudioController {
	
	public static Duration audioDuration;
	public static musicThread m = new musicThread();
	public static Thread t = new Thread(m);
	
	public static void main(String[] args) throws InterruptedException {}
	
	
	public static void changeTrack(String s) throws InterruptedException{
		if(t.isAlive()){
			t.interrupt();
			t.join();
			Thread.sleep(1000);
		}
		m.changeTrack(s);
		t = new Thread(m);
		t.start();
	}
	
	public static void changeVolume(String s) throws InterruptedException{
		if(t.isAlive()){
			t.interrupt();
			t.join();
			Thread.sleep(1000);
		}
		m.changeVolume(asDouble(s));
		t = new Thread(m);
		t.start();
	}
	
	public static void changeAction(String s) throws InterruptedException{
		if(t.isAlive()){
			t.interrupt();
			t.join();
			Thread.sleep(1000);
		}
		m.changeAction(s);
		t = new Thread(m);
		t.start();
	}
	
	public void SFX(String s){
		new SFX(s);
	}
	
	static double asDouble(String s){
		return Double.parseDouble(s);
	}
}
