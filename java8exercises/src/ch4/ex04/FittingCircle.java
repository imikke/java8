package ch4.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class FittingCircle extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Circle circle = new Circle(100, 100, 100);
		circle.setFill(Color.RED);
		Pane pane = new Pane();
		pane.getChildren().add(circle);
		Scene scene = new Scene(pane);
		circle.centerXProperty()
				.bind(Bindings.divide(scene.widthProperty(), 2));
		circle.centerYProperty().bind(
				Bindings.divide(scene.heightProperty(), 2));
		// シーンの縦横の長さによって半径の長さを変える
		circle.radiusProperty().bind(Bindings.createDoubleBinding(() -> {
			Double height = scene.heightProperty().doubleValue();
			Double width = scene.widthProperty().doubleValue();
			if (height > width)
				return width / 2;
			return height / 2;
		}, scene.heightProperty()));
		circle.radiusProperty().bind(Bindings.createDoubleBinding(() -> {
			Double height = scene.heightProperty().doubleValue();
			Double width = scene.widthProperty().doubleValue();
			if (height > width)
				return width / 2;
			return height / 2;
		}, scene.widthProperty()));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
