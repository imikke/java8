package ch4.ex02;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Greeting extends Application {
	private String text = "";
	private StringProperty textProperty = null;
	private Runnable runner = () -> {
		System.out.println("create property object");
		textProperty = new SimpleStringProperty("");
		// add other properties.
	};

	public final StringProperty textProperty() {
		if (textProperty == null)
			runner.run();
		return textProperty;
	}

	public final void setText(String newValue) {
		if (textProperty != null) {
			textProperty.set(newValue);
		} else {
			text = newValue;
		}
	}

	public final String getText() {
		if (textProperty != null) {
			return textProperty.get();
		} else {
			return text;
		}
	}

	@Override
	public void start(Stage stage) {
		Greeting greeting = new Greeting();
		greeting.setText("Hello");
		Label message1 = new Label(greeting.getText());
		message1.setFont(new Font(100));
		greeting.textProperty().set("Hello by property");
		Label message2 = new Label(greeting.textProperty().get());
		message2.setFont(new Font(100));
		VBox root = new VBox();
		root.getChildren().addAll(message1, message2);
		stage.setScene(new Scene(root));
		stage.setTitle("Hello");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
