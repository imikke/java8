package ch4.ex10;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class OriginalBrowser extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		String location = "https://www.google.com";
		WebView browser = new WebView();
		WebEngine engine = browser.getEngine();
		TextField addressText = new TextField(location);
		addressText.setPrefWidth(300);
		Button goButton = new Button("Go");
		Button backButton = new Button("Back");
		engine.load(addressText.getText());

		// 開いているページのURLをアドレスバーに反映する
		engine.locationProperty().addListener(
				property -> addressText.setText(engine.getLocation()));
		goButton.setOnAction(event -> {
			engine.load(addressText.getText());
		});

		backButton.setOnAction(event -> {
			WebHistory history = engine.getHistory();
			if (history.getCurrentIndex() > 0) {
				history.go(-1); // 一つ戻る
			}
		});

		HBox addressBar = new HBox();
		addressBar.getChildren().addAll(backButton, addressText, goButton);
		addressBar.setAlignment(Pos.CENTER);
		VBox box = new VBox();
		box.getChildren().addAll(addressBar, browser);
		Scene scene = new Scene(box);
		stage.setScene(scene);
		stage.setWidth(500);
		stage.setHeight(500);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
