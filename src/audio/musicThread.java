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
	static double volume = 1;
	static boolean volumeChange = false;
	static boolean trackChange = false;
	static boolean actionChange = false;
	
	@Override
	public void run() {
		try{
			launch();
		}
		catch(Exception e){
			//e.printStackTrace();
			if(e.toString().contains("Unexpected exception")){ //interrupted
				try {
					stop();
				} catch (InterruptedException e1) {}
			}
			else if(e.toString().contains("more than once")){ //changing music
				try {
					//System.out.println("Volume= " + volumeChange + " track= " + trackChange);
					if(trackChange){
						trackChange = false;
						resetDuration();
						start(null);
					}
					else if(volumeChange){
						volumeChange = false;
						start(null);
					}
					else if(actionChange){
						actionChange = false;
						start(null);
					}
				} catch (InterruptedException e1) {}
			}
		}
	}
	
	public musicThread(){} //this is here because Java will complain, great programming as always
	
	@Override
	public void start(Stage primaryStage) throws InterruptedException{
			
			//final Media media = new Media("http://inception.davepedu.com/inception.mp3");
			//This is a http streaming media. We'll be testing with regular files for now.
			trackChange = false;
			volumeChange = false;
			actionChange = false;
			media = new Media(new File("src/audio/" + songName + ".mp3").toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			//System.out.println("kek");
			//System.out.println(AudioController.audioDuration + " why whay ");
			
			
			mediaPlayer.play();
			mediaPlayer.pause();
			Thread.sleep(1000);
			mediaPlayer.seek(AudioController.audioDuration);
			mediaPlayer.setVolume(volume);
			mediaPlayer.play();


			mediaPlayer.setOnEndOfMedia(new Runnable() {
				@Override public void run() {
					mediaPlayer.seek(Duration.ZERO);
					//Platform.exit();
				}
			});
			//System.out.println("rofl");
	}
	
	public void stop() throws InterruptedException{
		while(mediaPlayer.getVolume()>0){
			mediaPlayer.setVolume(mediaPlayer.getVolume()-.1);
			Thread.sleep(250);
		}
		setDuration();
		mediaPlayer.stop();
		volume = 1;
	}
	
	public void changeTrack(String s) throws InterruptedException{
		songName = s;
		trackChange = true;
	}
	
	public void changeVolume(double d) throws InterruptedException{
		volume = d;
		volumeChange = true;
	}
	
	public void changeAction(String s){
		if(s.equals("pause")) setDuration();
		mediaPlayer.stop();
	}
	
	public void setDuration(){
		//System.out.println(mediaPlayer.getCurrentTime());
		AudioController.audioDuration = mediaPlayer.getCurrentTime();
	}
	
	public void resetDuration(){
		AudioController.audioDuration = new Duration(0);
	}
	



}
