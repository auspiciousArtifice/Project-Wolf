package music;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class musicThread extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		
		//final Media media = new Media("http://inception.davepedu.com/inception.mp3");
		
		/*
		 * We need to choose a battle theme. Which one of these is the best?
		 * theme1
		 * theme4p2
		 * theme5
		 * theme6 (or maybe this could be room entrance theme)
		 * theme7
		 * theme8
		 */
		
		final Media media = new Media(new File("C:\\Users\\sami_\\OneDrive\\Documents\\workspace\\Project-Wolf\\src\\music\\battleTheme2.mp3").toURI().toString());
		final MediaPlayer mediaPlayer = new MediaPlayer(media);

		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			@Override public void run() {
				Platform.exit();
			}
		});

	}
	
}
