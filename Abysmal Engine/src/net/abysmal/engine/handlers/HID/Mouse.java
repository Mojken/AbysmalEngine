package net.abysmal.engine.handlers.HID;

import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JApplet;
import javax.swing.JFrame;

import net.abysmal.engine.handlers.misc.Time;

public class Mouse implements MouseListener {

	/** {button ID} {press x, press y, release x, release y, pressed boolean} */
	int[][] clickInfo = new int[32][5];

	/** {button ID} {press timestamp, release timestamp, hold duration} */
	long[][] clickTime = new long[32][3];

	private JFrame f;
	private JApplet a;

	public Mouse(JFrame f) {
		this.f = f;
	}

	public Mouse(JApplet a) {
		this.a = a;
	}

	public void mouseClicked(MouseEvent e) {
		// ClickCoordinates[0] = e.getX();
		// ClickCoordinates[1] = e.getY();
		// ClickCoordinates[2] = 1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Insets i = (f == null ? a.getInsets() : f.getInsets());
		clickInfo[e.getButton()][0] = e.getX() - i.left;
		clickInfo[e.getButton()][1] = e.getY() - i.top;
		clickInfo[e.getButton()][4] = 1;
		clickTime[e.getButton()][0] = e.getWhen();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clickInfo[e.getButton()][2] = e.getX();
		clickInfo[e.getButton()][3] = e.getY();
		clickInfo[e.getButton()][4] = 0;
		clickTime[e.getButton()][1] = e.getWhen();
	}

	public long[] getDuration(int buttonID) {
		if (clickInfo[buttonID][4] == 1)
			clickTime[buttonID][2] = Time.getTime(Time.MILLIS) - clickTime[buttonID][0];
		else if (clickInfo[buttonID][4] == 0)
			clickTime[buttonID][2] = clickTime[buttonID][1] - clickTime[buttonID][0];
		return clickTime[buttonID];
	}

	public int[] getDragBounds(int ButtonID) {
		int[] bounds = { clickInfo[ButtonID][0], clickInfo[ButtonID][1], clickInfo[ButtonID][2],
				clickInfo[ButtonID][3] };
		return bounds;
	}

	public int[][] getClickInfo() {
		return clickInfo;
	}

	public long[][] getClickTime() {
		return clickTime;
	}

	public float[] getMousePosition() {
//		Point p = (f == null ? a.getMousePosition() : f.getMousePosition());
		Point p = a.getMousePosition();
		if (p != null)
		return new float[] { (float) p.getX(), (float) p.getY() };
		else return new float[] {-1f, -1f};
	}
}