package music;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;
import javafx.util.Duration;

public class musicThread extends Application implements Runnable{
	static MediaPlayer mediaPlayer;

	@Override
	public void start(Stage primaryStage){
			//final Media media = new Media("http://inception.davepedu.com/inception.mp3");

			final Media media = new Media(new File("src/music/opening.mp3").toURI().toString());
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

	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}

	@Override
	public void run() {
		launch(null);
	}



}
