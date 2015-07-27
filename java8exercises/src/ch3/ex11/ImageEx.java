package ch3.ex11;

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

	/**
	 * composeメソッドは、ColorTransformerオブジェクトを合成する。
	 * 
	 * @param ct1
	 * @param ct2
	 * @return
	 */
	public static ColorTransformer compose(ColorTransformer ct1,
			ColorTransformer ct2) {
		return (x, y, c) -> ct1.apply(x, y, ct2.apply(x, y, c));
	}

	/**
	 * toColorTransformerメソッドは、UnaryOperatorオブジェクトを
	 * ColorTransformerオブジェクトに変換する。
	 * 
	 * @param f
	 * @return
	 */
	public static ColorTransformer toColortTransformer(UnaryOperator<Color> f) {
		return (x, y, c) -> f.apply(c);
	}
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

	/**
	 * transformメソッドは、引数fで定義された画像処理を引数inの画像に対して実施する。
	 * 
	 * @param in
	 *            入力画像
	 * @param f
	 * @return 出力画像
	 */
	public static Image transform(Image in, ColorTransformer f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y,
						f.apply(x, y, in.getPixelReader().getColor(x, y)));
		return out;
	}

	@Override
	public void start(Stage stage) {
		// queen-mary.png does not include in this project.
		// Please refer to the sample code of this book.
		Image image = new Image("queen-mary.png");
		Image brightenedImage = transform(image,
				ColorTransformer.toColortTransformer(Color::brighter));

		// UnaryOperatorをColorTransformerに変換
		ColorTransformer ct = ColorTransformer
				.toColortTransformer(Color::brighter);
		// ColorTrnsformerオブジェクトを合成
		Image image2 = transform(
				image,
				ColorTransformer.compose(ct, (x, y, c) -> {
					if (x < 10 || (int) image.getWidth() - 10 < x || y < 10
							|| (int) image.getHeight() - 10 < y) {
						return Color.GRAY;
					}
					return c.brighter();
				}));
		stage.setScene(new Scene(new HBox(new ImageView(brightenedImage),
				new ImageView(image2))));

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
