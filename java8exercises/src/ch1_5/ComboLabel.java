package ch1_5;

import javax.swing.Icon;

/**
 * 
 * ComboBoxでテキストと画像を表示するために使用するクラス
 * 
 *
 */

public class ComboLabel {
	String text;
	Icon icon;

	ComboLabel(String text, Icon icon) {
		this.text = text;
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public Icon getIcon() {
		return icon;
	}

}
