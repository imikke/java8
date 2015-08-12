package ch4.ex05;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BindingEx extends Application {

	/**
	 * observeメソッドは、ObservableValue<T> tの値に変更があった場合、ObservableValue<R>型を返す。
	 * 
	 * @param f
	 * @param t
	 * @return
	 */
	public static <T, R> ObservableValue<R> observe(Function<T, R> f,
			ObservableValue<T> t) {
		return Bindings.createObjectBinding(() -> f.apply(t.getValue()), t);
	}

	/**
	 * observeメソッドは、ObservableValue<T> t、もしくはObservableValue<U> uの値に変更があった場合、
	 * ObservableValue<R>型を返す。
	 * 
	 * @param f
	 * @param t
	 * @param u
	 * @return
	 */
	public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f,
			ObservableValue<T> t, ObservableValue<U> u) {
		return Bindings.createObjectBinding(
				() -> f.apply(t.getValue(), u.getValue()), t, u);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Button smaller = new Button("Smaller");
		Button larger = new Button("Larger");
		Label message = new Label("");
		Rectangle gauge = new Rectangle(0, 5, 50, 15);
		Rectangle outline = new Rectangle(0, 5, 100, 15);
		outline.setFill(null);
		outline.setStroke(Color.BLACK);
		Pane pane = new Pane();
		pane.getChildren().addAll(gauge, outline);
		smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
		larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));
		// smaller.disableProperty().bind(
		// Bindings.lessThanOrEqual(gauge.widthProperty(), 0));
		smaller.disableProperty().bind(
				observe(t -> t.doubleValue() <= 0, gauge.widthProperty()));
		// larger.disableProperty().bind(
		// Bindings.greaterThanOrEqual(gauge.widthProperty(), 100));
		larger.disableProperty().bind(
				observe(t -> t.doubleValue() >= 100, gauge.widthProperty()));

		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(smaller, pane, larger);
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(hBox, message);
		Scene scene = new Scene(vBox);
		message.textProperty().bind(observe((t, u) -> {
			return "(width, height): (" + t + ", " + u + ")";
		}, scene.widthProperty(), scene.heightProperty()));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
