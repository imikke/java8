package ch3.ex15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

class LatentImage {
	private Color[][] in;// Image -> Color[][]
	private List<UnaryOperator<Color>> pendingOperations;

	public static LatentImage from(Color[][] in) {
		LatentImage result = new LatentImage();
		result.in = in;
		result.pendingOperations = new ArrayList<>();
		return result;
	}

	public LatentImage parallelTransform(UnaryOperator<Color> f) {
		pendingOperations.add(f);
		return this;
	}

	public Color[][] toImage() {
		int n = Runtime.getRuntime().availableProcessors();
		int height = in.length;
		int width = in[0].length;
		Color[][] out = new Color[height][width];
		try {
			ExecutorService pool = Executors.newCachedThreadPool();
			for (int i = 0; i < n; i++) {
				int fromY = i * height / n;
				int toY = (i + 1) * height / n;
				pool.submit(() -> {
					System.out.printf("%s %d...%d\n", Thread.currentThread(),
							fromY, toY - 1);
					for (int x = 0; x < width; x++)
						for (int y = fromY; y < toY; y++)
							for (UnaryOperator<Color> f : pendingOperations) {
								out[y][x] = f.apply(in[y][x]);
								in[y][x] = out[y][x]; // outを次のinにする
							}
				});
			}
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		return out;

	}
}

public class ImageEx extends Application {

	@Override
	public void start(Stage stage) {
		Image image = new Image("eiffel-tower.jpg");
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		Color[][] pixels = new Color[height][width];
		Color[][] pixels2 = new Color[height][width];
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				pixels[y][x] = image.getPixelReader().getColor(x, y);
				pixels2[y][x] = image.getPixelReader().getColor(x, y);
			}
		// 遅延評価と並列評価を組み合わせて呼ぶ
		// grayscaleのみ
		pixels = LatentImage.from(pixels).parallelTransform(Color::grayscale)
				.toImage();
		// grayscale + brighter
		pixels2 = LatentImage.from(pixels2).parallelTransform(Color::grayscale)
				.parallelTransform(Color::brighter).toImage();

		WritableImage result = new WritableImage(width, height);
		WritableImage result2 = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				result.getPixelWriter().setColor(x, y, pixels[y][x]);
				result2.getPixelWriter().setColor(x, y, pixels2[y][x]);
			}
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(
				result), new ImageView(result2))));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
