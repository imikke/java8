package ch1_5;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JPanel;

/**
 * 
 * デジタル時計を表示する。 イベントを受け取ると、文字列を書き換える。
 * 
 * 気になる点：フォントの色を変えたときに一度に変わらないところ。
 * 
 * @author Isamu
 *
 */

public class DrawPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private IDigitalClock idc;// デジタルクロックフレームのインタフェース

	private volatile String fontName = "timeRoman"; // フォント名
	private volatile int fontStyle = Font.PLAIN; // フォントスタイル
	private volatile int fontSize = 12; // フォントサイズ

	private Font font = new Font(fontName, fontStyle, fontSize); // フォント
	private volatile boolean font_change_flg = false;// フォントが変わった時に立つフラグ
	private volatile Color fontColor = Color.black; // フォントカラー
	private volatile Color backGroundColor = Color.white; // 背景色

	private Preferences prefs; // プリファレンス
	private static final String KEY_FONT_NAME = "name";
	private static final String KEY_FONT_STYLE = "0";
	private static final String KEY_FONT_SIZE = "12";
	private static final String KEY_FONT_COLOR = String.valueOf(Color.black);
	private static final String KEY_BG_COLOR = String.valueOf(Color.white);

	private String loadStyle;
	private String loadSize;
	private String loadColor;
	private String loadbgColor;

	// 実装は見ないようにする
	public DrawPanel(IDigitalClock idc) {
		this.idc = idc;
		setBackground(Color.white);
		prefs = Preferences.userNodeForPackage(this.getClass());
		load();
		/*
		 * System.out.println("constructor name: " + fontName);
		 * System.out.println("constructor style: " + fontStyle);
		 * System.out.println("constructor size: " + fontSize);
		 * System.out.println("constructor color: " + fontColor);
		 * System.out.println("constructor bg_color: " + backGroundColor);
		 */
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// フォントが変わっていたなら
		if (font_change_flg == true) {
			/*
			 * System.out.println("the name: " + fontName);
			 * System.out.println("the style: " + fontStyle);
			 * System.out.println("the size: " + fontSize);
			 * System.out.println("the color: " + fontColor);
			 * System.out.println("the bg_color: " + backGroundColor);
			 */
			this.createFont(); // この時点で最新のフォントを作成する
			save();
			font_change_flg = false;
		}
		repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(fontColor);

		g.setFont(font);

		setBackground(backGroundColor);

		// 第一引数から日付/時刻データ用の共通書式を出力
		String clkStr = String.format("%1$tI:%1$tM:%1$tS", new Date());

		FontMetrics fontMetrics = g.getFontMetrics();

		idc.setFrameSize(fontMetrics.stringWidth(clkStr) + 100,
				fontMetrics.getAscent() - fontMetrics.getDescent() + 100);

		g.drawString(clkStr,
				(getWidth() - fontMetrics.stringWidth(clkStr)) / 2,
				(getHeight() + fontMetrics.getAscent() - fontMetrics
						.getDescent()) / 2);
	}

	/*
	 * デジタル時計のフォントを作成する
	 */
	public void createFont() {
		font = new Font(fontName, fontStyle, fontSize);
	}

	/*
	 * フォントを設定する
	 */
	public void setFontName(String name) {
		if (font_change_flg == false) {
			font_change_flg = true;
		}
		fontName = name;

	}

	/*
	 * フォントサイズを設定する
	 */
	public void setFontSize(int size) {
		if (font_change_flg == false) {
			font_change_flg = true;
		}
		fontSize = size;

	}

	/*
	 * フォント色を設定する
	 */
	public void setFontColor(Color name) {
		fontColor = name;
	}

	/*
	 * フォントスタイルを設定する
	 */
	public void setFontStyle(int style) {
		fontStyle = style;
	}

	/*
	 * 背景色を設定する
	 */
	public void setBgColor(Color name) {
		backGroundColor = name;
	}

	/*
	 * 色を変換する{"Black","Blue","Cyan","DarkGray","Green","LightGray", "Magenta",
	 * "Orange","Pink","Red","White","Yellow"};
	 */

	public Color stringToColor(String str_color) {
		Color cnv_color = Color.black;

		if (str_color.compareToIgnoreCase("Black") == 0) {
			cnv_color = Color.black;
		} else if (str_color.compareToIgnoreCase("Blue") == 0) {
			cnv_color = Color.blue;
		} else if (str_color.compareToIgnoreCase("Cyan") == 0) {
			cnv_color = Color.cyan;
		} else if (str_color.compareToIgnoreCase("DarkGray") == 0) {
			cnv_color = Color.darkGray;
		} else if (str_color.compareToIgnoreCase("Green") == 0) {
			cnv_color = Color.green;
		} else if (str_color.compareToIgnoreCase("LightGray") == 0) {
			cnv_color = Color.lightGray;
		} else if (str_color.compareToIgnoreCase("Magenta") == 0) {
			cnv_color = Color.magenta;
		} else if (str_color.compareToIgnoreCase("Orange") == 0) {
			cnv_color = Color.orange;
		} else if (str_color.compareToIgnoreCase("Pink") == 0) {
			cnv_color = Color.pink;
		} else if (str_color.compareToIgnoreCase("Red") == 0) {
			cnv_color = Color.red;
		} else if (str_color.compareToIgnoreCase("White") == 0) {
			cnv_color = Color.white;
		} else if (str_color.compareToIgnoreCase("Yellow") == 0) {
			cnv_color = Color.yellow;
		} else {
			// 何もしない
		}

		return cnv_color;
	}

	private void save() {
		try {
			/*
			 * System.out.println("Save the name: " + fontName);
			 * System.out.println("Save the style: " + fontStyle);
			 * System.out.println("Save the size: " + fontSize);
			 * System.out.println("Save the color: " + fontColor);
			 * System.out.println("Save the bg_color: " + backGroundColor);
			 */
			prefs.put(KEY_FONT_NAME, fontName);
			prefs.put(KEY_FONT_STYLE, String.valueOf(fontStyle));
			prefs.put(KEY_FONT_SIZE, String.valueOf(fontSize));
			prefs.put(KEY_FONT_COLOR, String.valueOf(fontColor.getRGB()));
			prefs.put(KEY_BG_COLOR, String.valueOf(backGroundColor.getRGB()));
			prefs.flush();
		} catch (BackingStoreException ex) {
			ex.printStackTrace();
		}
	}

	private void load() {
		// System.out.println("Load the name1: " + fontStyle);
		// loadStart();
		fontName = prefs.get(KEY_FONT_NAME, fontName);
		loadStyle = prefs.get(KEY_FONT_STYLE, String.valueOf(fontStyle));
		fontStyle = Integer.parseInt(loadStyle);
		loadSize = prefs.get(KEY_FONT_SIZE, String.valueOf(fontSize));
		fontSize = Integer.parseInt(loadSize);
		loadColor = prefs.get(KEY_FONT_COLOR,
				String.valueOf(fontColor.getRGB()));
		fontColor = new Color(Integer.parseInt(loadColor));
		loadbgColor = prefs.get(KEY_BG_COLOR,
				String.valueOf(backGroundColor.getRGB()));
		backGroundColor = new Color(Integer.parseInt(loadbgColor));
		this.createFont();
		/*
		 * setFontName(fontName); setFontStyle(fontStyle);
		 * setFontSize(fontSize); setFontColor(fontColor);
		 * setBgColor(backGroundColor);
		 */

		/*
		 * System.out.println("Load the name: " + fontName);
		 * System.out.println("Load the style: " + fontStyle);
		 * System.out.println("Load the size: " + fontSize);
		 * System.out.println("Load the color: " + fontColor);
		 * System.out.println("Load the bg_color: " + backGroundColor);
		 */
		// loadEnd();
	}

	public String getLoadStyle() {
		return loadStyle;
	}

	public String getLoadSize() {
		return loadSize;
	}

	public String getLoadColor() {
		return loadColor;
	}

	public String getLoadbgColor() {
		return loadbgColor;
	}

	public String getFontName() {
		return fontName;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public int getFontSize() {
		return fontSize;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public Color getBackGroundColor() {
		return backGroundColor;
	}

}
