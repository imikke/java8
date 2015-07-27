package ch3.ex12;

import java.util.ArrayList;
import java.util.List;
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
	 * toColorTransformerメソッドは、UnaryOperatorオブジェクトを
	 * ColorTransformerオブジェクトに変換する。
	 * 
	 * @param f
	 * @return
	 */
	public static ColorTransformer toColorTransformer(UnaryOperator<Color> f) {
		return (x, y, c) -> f.apply(c);
	}
}

class LatentImage {
	private Image in;
	// ColorTransformerのリストを持つようにする
	private List<ColorTransformer> pendingOperations;

	public static LatentImage from(Image in) {
		LatentImage result = new LatentImage();
		result.in = in;
		result.pendingOperations = new ArrayList<>();
		return result;
	}

	// ch3.ex11で作成したtoColorTransformer()を利用する
	LatentImage transform(UnaryOperator<Color> f) {
		ColorTransformer ct = ColorTransformer.toColorTransformer(f);
		pendingOperations.add(ct);
		return this;
	}

	LatentImage transform(ColorTransformer f) {
		pendingOperations.add(f);
		return this;
	}

	public Image toImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (ColorTransformer f : pendingOperations)
					c = f.apply(x, y, c);
				out.getPixelWriter().setColor(x, y, c);
			}
		return out;
	}
}

public class ImageEx extends Application {
	@Override
	public void start(Stage stage) {
		Image image = new Image("eiffel-tower.jpg");
		Image finalImage = LatentImage.from(image).transform(Color::brighter)
				.transform(Color::grayscale).toImage();
		Image finalImage2 = LatentImage.from(image)
				.transform((x, y, c) -> c.brighter())
				.transform((x, y, c) -> c.grayscale()).toImage();
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(
				finalImage), new ImageView(finalImage2))));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
