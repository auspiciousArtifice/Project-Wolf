package audio;

import java.io.File;
import java.net.URL;
import java.util.Observable;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;
import javafx.util.Duration;

public class musicThread extends Application implements Runnable{
	static MediaPlayer mediaPlayer;
	static Media media;
	static String songName;
	
	public void initialize(){
			launch();
	}
	
	public musicThread(){} //this is here because Java will complain, great programming as always
	
	public musicThread(String s){
		songName = s;
	}
	
	@Override
	public void start(Stage primaryStage){
			//final Media media = new Media("http://inception.davepedu.com/inception.mp3");
			//This is a http streaming media. We'll be testing with regular files for now.
			media = new Media(new File("src/audio/" + songName + ".mp3").toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			System.out.println("kek");
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				@Override public void run() {
					mediaPlayer.seek(Duration.ZERO);
					//Platform.exit();
				}
			});

			System.out.println("rofl");
	}
	
	public void stop() throws InterruptedException{
		while(mediaPlayer.getVolume()>0){
			mediaPlayer.setVolume(mediaPlayer.getVolume()-.1);
			Thread.sleep(250);
		}
		mediaPlayer.stop();
	}
	
	public void changeTrack(String s) throws InterruptedException{
		songName = s;
		stop();
		start(null);
	}

	@Override
	public void run() {
		try{
			initialize();
		}
		catch(Exception e){
			e.printStackTrace();
			if(e.toString().contains("Unexpected exception")){
				try {
					stop();
				} catch (InterruptedException e1) {}
			}
			else if(e.toString().contains("more than once")){
				start(null);
			}
		}
	}



}
