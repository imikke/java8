package ch3.ex08;

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
	Color apply(int x, int y, Color colorAtXY, int flameLength, Color flameColor);

	/**
	 * generateCTWithFlameは、画像を明るくして、枠を付けたColorTransformerインスタンスを返す。
	 * 
	 * @param image
	 * @return
	 */
	public static ColorTransformer generateCTWithFlame(Image image) {
		return (x, y, c, fl, fc) -> {
			if (x < fl || (int) image.getWidth() - fl < x || y < fl
					|| (int) image.getHeight() - fl < y) {
				return fc;
			}
			return c.brighter();
		};
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
	 * @param f
	 *            入力画像
	 * @param fLength
	 *            枠の長さ
	 * @param fColor
	 *            枠の色
	 * @return 出力画像
	 */
	public static Image transform(Image in, ColorTransformer f, int fLength,
			Color fColor) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(
						x,
						y,
						f.apply(x, y, in.getPixelReader().getColor(x, y),
								fLength, fColor));
		return out;
	}

	@Override
	public void start(Stage stage) {
		// queen-mary.png does not include in this project.
		// Please refer to the sample code of this book.
		Image image = new Image("queen-mary.png");
		Image brightenedImage = transform(image, Color::brighter);
		Image image2 = transform(image,
				ColorTransformer.generateCTWithFlame(image), 20, Color.BLUE);
		stage.setScene(new Scene(new HBox(new ImageView(brightenedImage),
				new ImageView(image2))));

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
