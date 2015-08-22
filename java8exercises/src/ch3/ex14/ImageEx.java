package ch3.ex14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, PixelReader reader);

	/**
	 * toColorTransformerメソッドは、UnaryOperatorオブジェクトを
	 * ColorTransformerオブジェクトに変換する。
	 * 
	 * @param f
	 * @return
	 */
	public static ColorTransformer toColorTransformer(UnaryOperator<Color> f) {
		return (x, y, reader) -> f.apply(reader.getColor(x, y));
	}
}

// TODO: ピクセルのキャッシュを保持する
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
		for (ColorTransformer f : pendingOperations) {
			WritableImage out = new WritableImage(width, height);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					PixelReader reader = in.getPixelReader();
					Color c = f.apply(x, y, reader);
					out.getPixelWriter().setColor(x, y, c);
				}
			}
			in = out;
		}
		return in;
	}

	/**
	 * blurメソッドは、画像にぼかしを入れる。
	 * 
	 * @return
	 */
	public LatentImage blur() {
		return this.transform((x, y, reader) -> {
			double[] sum = { 0.0, 0.0, 0.0 };// RGB
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						int m = x + i;
						int n = y + j;
						if (m < 0 || m > in.getWidth() - 1)
							m = x;
						if (n < 0 || n > in.getHeight() - 1)
							n = y;
						sum[0] += reader.getColor(m, n).getRed();
						sum[1] += reader.getColor(m, n).getGreen();
						sum[2] += reader.getColor(m, n).getBlue();
					}
				}
				// System.out.println("x:" + x + ",y:" + y + ",r:" + (int)
				// (sum[0])
				// + ",g:" + (int) (sum[1]) + ",b:" + (int) (sum[2]));
				return Color.color(sum[0] / 9.0, sum[1] / 9.0, sum[2] / 9.0);
			});
	}

	/**
	 * detctEdgeメソッドは、画像のエッジ検出処理を行う。
	 * 入力画像にはグレースケールの画像が必要とされる。
	 * 
	 * @return
	 */
	public LatentImage detectEdge() {
		return this.transform((x, y, reader) -> {
			double[] sum = { 0.0, 0.0, 0.0 };// RGB
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if ((i == -1 && j == -1) || (i == -1 && j == 1)
								|| (i == 0 && j == 0) || (i == 1 && j == -1)
								|| (i == 1 && j == 1)) {
							// nothing to do.
						} else {
							int m = x + i;
							int n = y + j;
							if (m < 0 || m > in.getWidth() - 1)
								m = x;
							if (n < 0 || n > in.getHeight() - 1)
								n = y;
							sum[0] += reader.getColor(m, n).getRed();
							sum[1] += reader.getColor(m, n).getGreen();
							sum[2] += reader.getColor(m, n).getBlue();
						}
					}
				}
				// System.out.println("x:" + x + ",y:" + y + ",r:" + (int)
				// (sum[0])
				// + ",g:" + (int) (sum[1]) + ",b:" + (int) (sum[2]));

				sum[0] = 4 * reader.getColor(x, y).getRed() - sum[0];
				sum[1] = 4 * reader.getColor(x, y).getGreen() - sum[1];
				sum[2] = 4 * reader.getColor(x, y).getBlue() - sum[2];
				for (int i = 0; i < sum.length; i++) {
					if (sum[i] > 1.0)
						sum[i] = 1.0;
					else if (sum[i] < 0.0) {
						sum[i] = 0;
					}
				}
				return Color.color(sum[0], sum[1], sum[2]);
			});
	}
}

public class ImageEx extends Application {

	@Override
	public void start(Stage stage) {
		Image image = new Image("eiffel-tower.jpg");
		Image finalImage = LatentImage.from(image).transform(Color::grayscale)
				.blur().toImage();
		Image finalImage2 = LatentImage.from(image).transform(Color::grayscale)
				.detectEdge().toImage();
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(
				finalImage), new ImageView(finalImage2))));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
