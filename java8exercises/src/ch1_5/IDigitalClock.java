package ch1_5;

/*
 * デジタル時計のインタフェース
 * フレームサイズの変更など依存関係を崩さないために必要である。
 * 
 */

public interface IDigitalClock {

	// フレームのサイズを変更する
	public void setFrameSize(int width, int height);

}
