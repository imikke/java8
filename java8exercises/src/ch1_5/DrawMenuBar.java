package ch1_5;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DrawMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DrawPanel dp;

	private JMenu menu1;
	private JMenu menu2;
	private JMenu submenu1;
	private JMenu submenu2;
	private JMenu submenu3;
	private JMenu submenu4;

	DrawMenuBar(DrawPanel dp) {
		this.dp = dp;

		menu1 = new JMenu("File");
		menu2 = new JMenu("Edit");
		submenu1 = new JMenu("Font");
		submenu2 = new JMenu("Font Size");
		submenu3 = new JMenu("Font Color");
		submenu4 = new JMenu("Bg Color");

		this.add(menu1);
		this.add(menu2);

		createMenuItemClose(menu1);// クローズアイテムを作成し、メニューに追加する
		createMenuItemFont(submenu1);// フォントアイテムを作成し、メニューに追加する
		createMenuItemFontSize(submenu2);// フォントサイズアイテムを作成し、メニューに追加する
		createMenuItemFontColor(submenu3);// フォントカラーアイテムを作成し、メニューに追加する
		createMenuItemBgColor(submenu4);// バッググラウンドカラーアイテムを作成し、メニューに追加する

		// menu1.add(menuitem1);
		menu2.add(submenu1);
		menu2.add(submenu2);
		menu2.add(submenu3);
		menu2.add(submenu4);
	}

	/*
	 * ファイルクローズのメニューアイテムを作成し、追加する
	 * 
	 * @return
	 */
	private void createMenuItemClose(JMenu menu) {
		JMenuItem menuitem1 = new JMenuItem("Close");
		menu.add(menuitem1);
		menuitem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				actFileClose();
			}
		});

	}

	/*
	 * フォントメニューを作成する。
	 */
	private void createMenuItemFont(JMenu menu) {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String fontNames[] = ge.getAvailableFontFamilyNames();

		JMenuItem[] fontItem = new JMenuItem[fontNames.length];
		for (int i = 0; i < fontNames.length; i++) {
			menu.add(fontItem[i] = new JMenuItem(fontNames[i]));
			fontItem[i].addActionListener(new ActionListener() {
				// メニューボタンが呼ばれたら
				@Override
				public void actionPerformed(ActionEvent event) {
					JMenuItem item = (JMenuItem) event.getSource();
					System.out.println(item.getText());
					dp.setFontName(item.getText());
				}
			});
		}
	}

	/*
	 * フォントサイズメニューを作成する
	 */
	private void createMenuItemFontSize(JMenu menu) {
		String fontSizeNames[] = { "12", "14", "16", "18", "24", "36", "72" };
		JMenuItem[] fontSizeItem = new JMenuItem[fontSizeNames.length];
		for (int i = 0; i < fontSizeNames.length; i++) {
			menu.add(fontSizeItem[i] = new JMenuItem(fontSizeNames[i]));
			fontSizeItem[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					JMenuItem item = (JMenuItem) event.getSource();
					dp.setFontSize(Integer.valueOf(item.getText()).intValue());
				}
			});
		}
	}

	/*
	 * フォントカラーのメニューを作成する
	 */
	private void createMenuItemFontColor(JMenu menu) {
		String fontColorNames[] = { "Black", "Blue", "Cyan", "DarkGray",
				"Green", "LightGray", "Magenta", "Orange", "Pink", "Red",
				"White", "Yellow" };
		JMenuItem[] fontColorItem = new JMenuItem[fontColorNames.length];
		for (int i = 0; i < fontColorNames.length; i++) {
			menu.add(fontColorItem[i] = new JMenuItem(fontColorNames[i]));
			fontColorItem[i].setBackground(dp.stringToColor(fontColorNames[i]));
			fontColorItem[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					JMenuItem item = (JMenuItem) event.getSource();
					dp.setFontColor(dp.stringToColor(item.getText()));
				}
			});
		}

	}

	private void createMenuItemBgColor(JMenu menu) {
		String BackGroundColorNames[] = { "Black", "Blue", "Cyan", "DarkGray",
				"Green", "LightGray", "Magenta", "Orange", "Pink", "Red",
				"White", "Yellow" };
		JMenuItem[] BackGroundColorItem = new JMenuItem[BackGroundColorNames.length];
		for (int i = 0; i < BackGroundColorNames.length; i++) {
			menu.add(BackGroundColorItem[i] = new JMenuItem(
					BackGroundColorNames[i]));
			BackGroundColorItem[i].setBackground(dp
					.stringToColor(BackGroundColorNames[i]));
			BackGroundColorItem[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					JMenuItem item = (JMenuItem) event.getSource();
					dp.setBgColor(dp.stringToColor(item.getText()));
				}
			});
		}
	}

	/*
	 * ファイルクローズ処理
	 */
	private void actFileClose() {
		System.exit(0);
	}

	public JMenu getFontFamilyMenu() {
		return submenu1;
	}

}
