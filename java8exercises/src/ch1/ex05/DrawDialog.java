package ch1.ex05;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * デジタルクロックの設定を行うクラス。 別スレッドで動作する。
 * 
 */

public class DrawDialog extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private static Thread th = null;
	private DrawPanel dp;
	private JPanel p_panel;
	private GridBagConstraints gbc;
	private JButton ok_button;
	private JButton cancel_button;
	private JLabel font_label = new JLabel("Font : ", JLabel.RIGHT);
	private JLabel font_style_label = new JLabel("Style : ", JLabel.RIGHT);
	private JLabel font_size_label = new JLabel("Size : ", JLabel.RIGHT);
	private JLabel font_color_label = new JLabel("Color : ", JLabel.RIGHT);
	private JLabel bg_color_label = new JLabel("BackGround Color : ",
			JLabel.RIGHT);

	private Color fontColor = Color.black;
	private Color bgColor = Color.white;

	private Font font;
	private int init_font = 0; // フォント名
	private int init_style = Font.PLAIN;
	private int init_size_idx = 0;
	private int init_color_idx = 0;
	private int init_bg_color_idx = 0;

	private JComboBox family_box, style_box, size_box, color_box, bgColor_box;

	MyCellRenderer color_renderer = new MyCellRenderer();

	private String font_family[] = { "timeRoman", "Arial", "Serif" }; // family
	private String styles[] = { "Plain", "Bold", "Italic", "BoldItalic" }; // style
	private String sizes[] = { "12", "14", "16", "18", "24", "36", "72" }; // size
	private String colors[] = { "Black", "Blue", "Cyan", "DarkGray", "Green",
			"LightGray", "Magenta", "Orange", "Pink", "Red", "White", "Yellow" };// color
	private String bgColors[] = { "White", "Black", "Blue", "Cyan", "DarkGray",
			"Green", "LightGray", "Magenta", "Orange", "Pink", "Red", "Yellow" };// bgcolor

	DrawDialog(String frameTitle, DrawPanel dp) {
		super(frameTitle);
		th = new Thread(this);
		this.dp = dp;

		p_panel = new JPanel();

		// JListのリスナーの登録
		Listener listener = new Listener();

		// combomodelを作成する
		// DefaultComboBoxModel model = new DefaultComboBoxModel(colors);
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(new ComboLabel("Black", new ImageIcon("./black.png")));
		model.addElement(new ComboLabel("Blue", new ImageIcon("./blue.png")));
		model.addElement(new ComboLabel("Cyan", new ImageIcon("./cyan.png")));
		model.addElement(new ComboLabel("DarkGray", new ImageIcon(
				"./darkgray.png")));
		model.addElement(new ComboLabel("Green", new ImageIcon("./green.png")));
		model.addElement(new ComboLabel("LightGray", new ImageIcon(
				"./lightgray.png")));
		model.addElement(new ComboLabel("Magenta", new ImageIcon(
				"./magenta.png")));
		model.addElement(new ComboLabel("Orange", new ImageIcon("./orange.png")));
		model.addElement(new ComboLabel("Pink", new ImageIcon("./pink.png")));
		model.addElement(new ComboLabel("Red", new ImageIcon("./red.png")));
		model.addElement(new ComboLabel("White", new ImageIcon("./white.png")));
		model.addElement(new ComboLabel("Yellow", new ImageIcon("./yellow.png")));

		populateFonts();
		populateStyles();
		populateSizes();
		populateColors(model);
		populateBgColors();

		GridBagLayout layout = new GridBagLayout();// レイアウトマネージャの指定
		p_panel.setLayout(layout);// GridBagLayoutにする
		gbc = new GridBagConstraints();// Layoutの設定を行うクラスの生成

		gbc.fill = GridBagConstraints.BOTH;// グリッドのマス領域一杯にする

		// Font
		gbc.gridx = 0;
		gbc.gridy = 0;
		layout.setConstraints(font_label, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		layout.setConstraints(family_box, gbc);

		// Style
		gbc.gridx = 0;
		gbc.gridy = 1;
		layout.setConstraints(font_style_label, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		layout.setConstraints(style_box, gbc);

		// Size
		gbc.gridx = 0;
		gbc.gridy = 2;
		layout.setConstraints(font_size_label, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		layout.setConstraints(size_box, gbc);

		// Color
		gbc.gridx = 0;
		gbc.gridy = 3;
		layout.setConstraints(font_color_label, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		layout.setConstraints(color_box, gbc);

		// BgColor
		gbc.gridx = 0;
		gbc.gridy = 4;
		layout.setConstraints(bg_color_label, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		layout.setConstraints(bgColor_box, gbc);

		gbc.fill = GridBagConstraints.NONE;// グリッドのマス領域一杯にする

		// Button
		ok_button = new JButton("OK");
		gbc.gridx = 1;
		gbc.gridy = 5;
		// gbc.gridwidth = 1;
		// gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 0.5d;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		layout.setConstraints(ok_button, gbc);
		cancel_button = new JButton("Cancel");
		gbc.gridx = 2;
		gbc.gridy = 5;
		// gbc.gridwidth = 1;
		// gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 0.5d;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		layout.setConstraints(cancel_button, gbc);

		// パネルに追加
		p_panel.add(font_label);
		p_panel.add(family_box);
		p_panel.add(font_style_label);
		p_panel.add(style_box);
		p_panel.add(font_size_label);
		p_panel.add(size_box);
		p_panel.add(font_color_label);
		p_panel.add(color_box);
		p_panel.add(bg_color_label);
		p_panel.add(bgColor_box);
		p_panel.add(ok_button);
		p_panel.add(cancel_button);

		add(p_panel);

		init();

		// OKボタンが押されたら
		ok_button.addActionListener(event -> {
			init_font = family_box.getSelectedIndex();
			init_style = style_box.getSelectedIndex();
			init_size_idx = size_box.getSelectedIndex();
			init_color_idx = color_box.getSelectedIndex();
			init_bg_color_idx = bgColor_box.getSelectedIndex();
			dispose();
		});

		// Cancelボタンが押されたら
		cancel_button.addActionListener(event -> {
			// boxの選択を前の状態に戻す
				family_box.setSelectedIndex(init_font);
				style_box.setSelectedIndex(init_style);
				size_box.setSelectedIndex(init_size_idx);
				color_box.setSelectedIndex(init_color_idx);
				bgColor_box.setSelectedIndex(init_bg_color_idx);

				setFontName(font_family[init_font]);
				setBackGroundColor(StringToColor(bgColors[init_bg_color_idx]));
				setFontColor(StringToColor(colors[init_color_idx]));
				setFontSytle(init_style);
				setFontSize(Integer.parseInt(sizes[init_size_idx]));
				dispose();
			});

		family_box.addItemListener(listener);
		style_box.addItemListener(listener);
		size_box.addItemListener(listener);
		color_box.addItemListener(listener);
		bgColor_box.addItemListener(listener);

		// ウィンドウメッセージを取得する
		addWindowListener(new WindowAdapter() {
			// ウィンドウが閉じるボタンがクリックされたら
			@Override
			public void windowClosing(WindowEvent event) {
				dispose();
				// System.exit(0);
			}
		});

		th.start(); // スレッドスタート
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		String load_name = dp.getFontName();
		int i = 0;
		for (String name : font_family) {
			if (name.compareTo(load_name) == 0) {
				family_box.setSelectedIndex(i);
				break;
			}
			i++;
		}

		int load_style = Integer.parseInt(dp.getLoadStyle());
		// System.out.println("loadstyle" + load_style);
		style_box.setSelectedIndex(load_style);

		String load_size = dp.getLoadSize();
		// System.out.println("loadsize" + load_size);
		i = 0;
		for (String name : sizes) {
			if (name.compareTo(load_size) == 0) {
				size_box.setSelectedIndex(i);
				break;
			}
			i++;
		}

		// フォントネームとフォントサイズを変えなければ、DrawPanelでフォントを新たには作成されない。

		String load_color = dp.getLoadColor();
		Color temp = new Color(Integer.parseInt(load_color));
		for (i = 0; i < colors.length; i++) {
			color_box.setSelectedIndex(i);
			// System.out.println("load color" +
			// StringToColor(colors[color_box.getSelectedIndex()]));
			if (temp.equals(StringToColor(colors[color_box.getSelectedIndex()]))) {
				// System.out.println("load color" + temp);
				break;
			}
		}

		String load_bgcolor = dp.getLoadbgColor();
		temp = new Color(Integer.parseInt(load_bgcolor));
		for (i = 0; i < bgColors.length; i++) {
			bgColor_box.setSelectedIndex(i);
			// System.out.println("load color" +
			// StringToColor(colors[color_box.getSelectedIndex()]));
			if (temp.equals(StringToColor(bgColors[bgColor_box
					.getSelectedIndex()]))) {
				// System.out.println("load bgcolor" + temp);
				break;
			}
		}

	}

	public void setFontColor(Color c) {
		dp.setFontColor(c);
	}

	public void setBackGroundColor(Color c) {
		dp.setBgColor(c);
	}

	public void setFontSize(int size) {
		dp.setFontSize(size);
	}

	public void setFontSytle(int style) {
		dp.setFontStyle(style);
	}

	public void setFontName(String name) {
		dp.setFontName(name);
	}

	@Override
	public Font getFont() {
		return font;
	}

	public Color getBackGroundColor() {
		return this.bgColor;
	}

	public Color getFontColor() {
		return this.fontColor;
	}

	/**
	 * 
	 * JListで選択された際に呼ばれるListener
	 * 
	 */
	public class Listener implements ItemListener {
		// 同期を取る
		@Override
		public synchronized void itemStateChanged(ItemEvent event) {
			// System.out.println("Listener::itemStateChanged");
			setFontName(font_family[family_box.getSelectedIndex()]);
			setBackGroundColor(StringToColor(bgColors[bgColor_box
					.getSelectedIndex()]));
			setFontColor(StringToColor(colors[color_box.getSelectedIndex()]));
			setFontSytle(style_box.getSelectedIndex());
			setFontSize(Integer.parseInt(sizes[size_box.getSelectedIndex()]));
		}

	}

	private void populateFonts() {
		family_box = new JComboBox(font_family);
		// for(int i=0; i < fontNames.length; ++i)
		// family_list.add(fontNames[i]);
	}

	private void populateStyles() {
		style_box = new JComboBox(styles);
	}

	private void populateSizes() {
		size_box = new JComboBox(sizes);
		// for(int i=0; i < sizes.length; ++i)
		// size_list.add(sizes[i]);
	}

	private void populateColors(DefaultComboBoxModel model) {
		// color_box = new JComboBox(colors);
		color_box = new JComboBox(model);
		color_box.setRenderer(color_renderer);

		// for(int i=0; i < sizes.length; ++i)
		// color_list.add(sizes[i]);
	}

	private void populateBgColors() {
		bgColor_box = new JComboBox(bgColors);
		// for(int i=0; i < sizes.length; ++i)
		// bgColor_list.add(sizes[i]);
	}

	/*
	 * 色を変換する{"Black","Blue","Cyan","DarkGray","Green","LightGray", "Magenta",
	 * "Orange","Pink","Red","White","Yellow"};
	 */
	public Color StringToColor(String str_color) {
		return dp.stringToColor(str_color);
	}

}
