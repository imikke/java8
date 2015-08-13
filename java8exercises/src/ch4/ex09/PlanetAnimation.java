package ch4.ex09;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlanetAnimation extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Group group = new Group();
		Circle circle = new Circle(10);
		circle.setFill(Color.FORESTGREEN);

		Path path = new Path();
		path.setStroke(Color.BLACK);
		path.getStrokeDashArray().setAll(5d, 5d);

		ArcTo arcTo = new ArcTo();
		double centerX = 250;
		double centerY = 100;
		double radiusX = 100;
		double radiusY = 50;
		// 開始位置
		path.getElements()
				.add(new MoveTo(centerX - radiusX, centerY - radiusY));
		// 円弧の終点位置
		arcTo.setX(centerX - radiusX + 1); // x方向に1pxずらす
		arcTo.setY(centerY - radiusY);
		arcTo.setRadiusX(radiusX);
		arcTo.setRadiusY(radiusY);
		// 開始位置から終点位置までの大きい方の円弧を描く
		arcTo.setLargeArcFlag(true);
		path.getElements().add(arcTo);
		path.getElements().add(new ClosePath());

		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(3000));
		pathTransition.setNode(circle);
		pathTransition.setPath(path);
		pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(Animation.INDEFINITE);

		pathTransition.play();

		Scene scene = new Scene(group, 300, 200);
		group.getChildren().add(path);
		group.getChildren().add(circle);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
