package ch1_5;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * 
 * Comboboxで項目が描画を行うクラス
 * 
 *
 */

public class MyCellRenderer extends JLabel implements ListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		ComboLabel data = (ComboLabel) value;
		setText(data.getText());
		setIcon(data.getIcon());
		if (isSelected) {
			setForeground(Color.white);
			setBackground(Color.black);
		} else {
			setForeground(Color.black);
			setBackground(Color.white);
		}

		return this;
	}

}
