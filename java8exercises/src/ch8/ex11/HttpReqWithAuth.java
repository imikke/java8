package ch8.ex11;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HttpReqWithAuth {
	public static void main(String[] args) throws IOException {
		// ローカル上にapacheサーバを起動して、Basic認証を設定
		URL url = new URL("http://localhost/");
		URLConnection connection = url.openConnection();
		String username = "*****";
		String password = "*****";
		Base64.Encoder encoder = Base64.getEncoder();
		String original = username + ":" + password;
		String encoded = encoder.encodeToString(original
				.getBytes(StandardCharsets.UTF_8));
		connection.setRequestProperty("Authorization", "Basic " + encoded);
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		int numCharsRead;
		char[] charArray = new char[1024];
		StringBuffer strBuffer = new StringBuffer();
		while ((numCharsRead = streamReader.read(charArray)) > 0) {
			strBuffer.append(charArray, 0, numCharsRead);
		}
		String result = strBuffer.toString();
		System.out.println(result);
		/*
		 * Resutl: apacheのデフォルトページが取得できた
		 * <html><body><h1>It works!</h1></body></html>
		 */

	}
}
