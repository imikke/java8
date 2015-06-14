package ch1_5;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * ch1_5をラムダ式を使わない場合の行数：674
 */

/*
 * GUI課題2-4
 * 
 * デジタル時計をSwingで作成する。
 * 
 */

/*
 * DigitalClockクラス
 * フレームとなるクラス。様々なコンポーネントを追加できる。
 * 
 */

public class DigitalClockImpl extends JFrame implements IDigitalClock {

	private static final long serialVersionUID = 1L;
	private final static int TIMER_TICK = 1000;
	private DrawDialog dc_dialog; // メニューダイアログ
	private DrawPanel dp; // クロックパネル
	private DrawMenuBar menubar; // メニューバー

	private int d_width = 300; // ダイアログの幅
	private int d_height = 200; // ダイアログの高さ
	private int d_stx = 200; // フレーム内のダイアログの開始点x
	private int d_sty = 300; // フレーム内のダイアログの開始点y

	public DigitalClockImpl() {
		dp = new DrawPanel(this);
		// パネルをDigitalClock内に加える
		add(dp);

		// ダイアログの作成
		JPanel p_panel = new JPanel();
		JButton p_button = new JButton("show dialog");
		p_panel.add(p_button);
		add("North", p_panel);

		dc_dialog = new DrawDialog("DigitalClockDialog", dp);// ダイアログに登録

		p_button.addActionListener(event -> {
			dc_dialog.setSize(d_width, d_height);
			dc_dialog.setLocation(d_stx, d_sty);
			dc_dialog.setVisible(true);
		});

		// メニューバーを追加する
		menubar = new DrawMenuBar(dp);
		setJMenuBar(menubar);

		// iconを変える
		ImageIcon icon = new ImageIcon("./icon_watch.jpg");
		setIconImage(icon.getImage());

		// タイマーを起動させて、1000ms間隔でパネルを呼び出す
		new Timer(TIMER_TICK, dp).start();
	}

	@Override
	public void setFrameSize(int width, int height) {
		// System.out.println(width + ", " + height);
		this.setSize(width, height);

	}

	public static void main(String[] strArray) {
		// サイズ変更によって、動的にコンポーネントの再レイアウト
		Toolkit.getDefaultToolkit().setDynamicLayout(true);

		DigitalClockImpl dc = new DigitalClockImpl();
		dc.setTitle("Digital Clock");

		// ×ボタンが押されたら、プログラムも終了する
		dc.setDefaultCloseOperation(EXIT_ON_CLOSE);
		dc.setBackground(Color.red);
		dc.setSize(200, 100);

		dc.setVisible(true);
	}

}
