package fightergame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ContainerBox extends JPanel {
	int minX, maxX, minY, maxY;
	private Color colorBorder;
	protected Image backgroundimage;
	private static final Color DEFAULT_COLOR_BORDER = Color.YELLOW;

	public ContainerBox(int x, int y, int width, int height, Color colorBorder) {
		minX = x;
		minY = y;
		maxX = x + width - 1;
		maxY = y + height - 1;
		this.colorBorder = colorBorder;
		backgroundimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("星系.jpeg"));
	}

	public ContainerBox(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_COLOR_BORDER);
	}

	public void set(int x, int y, int width, int height) {
		minX = x;
		minY = y;
		maxX = x + width - 1;
		maxY = y + height - 1;
	}

	public void draw(Graphics g) {
		if (backgroundimage != null) {
			g.drawImage(backgroundimage, 0, 0, maxX - minX - 1, maxY - minY - 1, this);
		}
		g.setColor(colorBorder);
		g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
	}

	/*
	 * JLayeredPanel測試 public ContainerBox(int x, int y, int width, int height,
	 * Color colorFilled, Color colorBorder) { minX = x; minY = y; maxX = x + width
	 * - 1; maxY = y + height - 1; this.colorFilled = colorFilled; this.colorBorder
	 * = colorBorder; }
	 * 
	 * public ContainerBox(int x, int y, int width, int height) { this(x, y, width,
	 * height, DEFAULT_COLOR_FILLED, DEFAULT_COLOR_BORDER); }
	 * 
	 * public void set(int x, int y, int width, int height) { minX = x; minY = y;
	 * maxX = x + width - 1; maxY = y + height - 1; }
	 * 
	 * public void draw(Graphics g) { g.setColor(colorFilled); g.fillRect(minX,
	 * minY, maxX - minX - 1, maxY - minY - 1); g.setColor(colorBorder);
	 * g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1); }
	 */
}
