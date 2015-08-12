package ch4.ex06;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BorderPaneEx extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane1 = new BorderPane();
		BorderPane pane2 = new BorderPane();
		BorderPane pane3 = new BorderPane();
		pane1.setCenter(new Button("Top"));
		pane2.setLeft(new Button("Left"));
		pane2.setCenter(new Button("Center"));
		pane2.setRight(new Button("Right"));
		pane3.setCenter(new Button("Bottom"));
		VBox box = new VBox();
		box.getChildren().addAll(pane1, pane2, pane3);
		stage.setScene(new Scene(box));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
