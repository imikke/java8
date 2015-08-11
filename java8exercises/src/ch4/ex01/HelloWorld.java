package ch4.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorld extends Application {
	@Override
	public void start(Stage stage) {
		String initMsg = "Hello, JavaFX!";
		Label message = new Label(initMsg);
		TextField text = new TextField();
		message.setFont(new Font(100));
		text.setFont(new Font(100));
		text.setText(initMsg);
		text.textProperty().bindBidirectional(message.textProperty());
		VBox root = new VBox();
		root.getChildren().addAll(message, text);
		stage.setScene(new Scene(root));
		stage.setTitle("Hello");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
