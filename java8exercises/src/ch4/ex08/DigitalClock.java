package ch4.ex08;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DigitalClock extends Application implements Initializable {

	@FXML
	private Label clockLabel;
	@FXML
	private Button jpButton;
	@FXML
	private Button hawaiiButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TimeZone tz1 = TimeZone.getTimeZone("Asia/Tokyo");
		Timeline timer1 = new Timeline(new KeyFrame(Duration.millis(100),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Calendar cal = Calendar.getInstance(tz1);
						cal.setTimeZone(tz1);
						clockLabel.setText(DigitalClock.displayTime(cal));
					}
				}));
		timer1.setCycleCount(Timeline.INDEFINITE);

		TimeZone tz2 = TimeZone.getTimeZone("US/Hawaii");
		Timeline timer2 = new Timeline(new KeyFrame(Duration.millis(100),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Calendar cal = Calendar.getInstance(tz2);
						cal.setTimeZone(tz2);
						clockLabel.setText(DigitalClock.displayTime(cal));
					}
				}));
		timer2.setCycleCount(Timeline.INDEFINITE);

		// 初期設定
		timer1.play();

		jpButton.setOnAction(event -> {
			timer2.stop();
			timer1.play();
		});

		hawaiiButton.setOnAction(event -> {
			timer1.stop();
			timer2.play();
		});
	}

	private static String displayTime(Calendar cal) {
		return String.format("%02d:%02d:%02d %s",
				cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
				cal.get(Calendar.SECOND), cal.getTimeZone().getDisplayName());
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("clock.fxml"));
			stage.setScene(new Scene(root));
			stage.setWidth(250);
			stage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

}
