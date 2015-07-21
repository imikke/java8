package ch3.ex06;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY);
}

public class ImageEx extends Application {
	public static Image transform(Image in, UnaryOperator<Color> f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y,
						f.apply(in.getPixelReader().getColor(x, y)));
		return out;
	}

	public static UnaryOperator<Color> brighten(double factor) {
		return c -> c.deriveColor(0, 1, factor, 1);
	}

	/**
	 * transformメソッドは、引数fで定義された画像処理を引数inの画像に対して実施する。
	 * 
	 * @param in
	 *            入力画像
	 * @param f
	 * @param arg
	 *            画像処理パラメータ
	 * @return 出力画像
	 */
	public static <T> Image transform(Image in, BiFunction<Color, T, Color> f,
			T arg) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y,
						f.apply(in.getPixelReader().getColor(x, y), arg));
		return out;
	}

	@Override
	public void start(Stage stage) {
		// queen-mary.png does not include in this project.
		// Please refer to the sample codße of this book.
		Image image = new Image("queen-mary.png");
		Image brightenedImage = transform(image, brighten(1.2));
		Image image2 = transform(image, (c, factor) -> {
			return c.deriveColor(0, 1, factor, 1);
		}, 2.0);
		stage.setScene(new Scene(new HBox(new ImageView(brightenedImage),
				new ImageView(image2))));

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
