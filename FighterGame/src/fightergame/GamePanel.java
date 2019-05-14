package fightergame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel {
	private static final int UPDATE_RATE = 30; // Frames per second (fps)

	private Ball ball;// A single bouncing Ball's instance
	private Ball ball2;
	private Ball balls[];
	final int Max = 100;
	private ContainerBox box; // The container rectangular box
	private int z = 5;
	private Color color;
	private int ballcount;
	private volatile boolean go = false;

	private DrawCanvas canvas; // Custom canvas for drawing the box/ball
	private int canvasWidth;
	private int canvasHeight;
	private JPanel bottompanel;
	private JButton stop;
	private JButton cont;
	private JButton start;
	private JTextField text;
	private volatile boolean suspend = false;

	/**
	 * Constructor to create the UI components and init the game objects. Set the
	 * drawing canvas to fill the screen (given its width and height).
	 * 
	 * @param width  : screen width
	 * @param height : screen height
	 */

	public GamePanel(int width, int height) {
		this.setLayout(new BorderLayout());
		canvasWidth = width;
		canvasHeight = height;

		box = new ContainerBox(0, 0, canvasWidth, canvasHeight, Color.WHITE);

		canvas = new DrawCanvas();
		this.setLayout(new BorderLayout());
		this.add(canvas, BorderLayout.CENTER);

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component) e.getSource();
				Dimension dim = c.getSize();
				canvasWidth = dim.width;
				canvasHeight = dim.height;

				box.set(0, 0, canvasWidth, canvasHeight);
			}
		});

		gameStart();
	}

	public void gameStart() {
		Random rand = new Random();
		balls = new Ball[Max];

		for (int i = 0; i < z; i++) {

			int radius = rand.nextInt(50) + 50;
			int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
			int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
			int speed = 5;
			int angleInDegree = rand.nextInt(360);
			Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

			balls[i] = new Ball(x, y, radius, speed, angleInDegree, color);
		}

		Thread gameThread = new Thread() {
			public void run() {
				while (true) {

					gameUpdate();

					repaint();

					try {
						Thread.sleep(1000 / UPDATE_RATE);
					} catch (InterruptedException ex) {
					}

				}
			}
		};

		gameThread.start(); // Invoke GaemThread.run()

	}

	public void gameUpdate() {
		for (int i = 0; i < z; i++) {
			balls[i].moveOneStepWithCollisionDetection(box);
		}
	}

	class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			box.draw(g);
			for (int i = 0; i < z; i++) {
				balls[i].draw(g);
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier New", Font.PLAIN, 12));
		}

		@Override
		public Dimension getPreferredSize() {
			return (new Dimension(canvasWidth, canvasHeight));
		}
	}

	/*
	 * JLayeredPanel測試 public GamePanel(int width, int height) { this.setLayout(new
	 * BorderLayout()); canvasWidth = width; canvasHeight = height;
	 * 
	 * box = new ContainerBox(0, 0, canvasWidth, canvasHeight, Color.BLACK,
	 * Color.WHITE);
	 * 
	 * canvas = new DrawCanvas(); this.setLayout(new BorderLayout());
	 * this.add(canvas, BorderLayout.CENTER);
	 * 
	 * this.addComponentListener(new ComponentAdapter() {
	 * 
	 * @Override public void componentResized(ComponentEvent e) { Component c =
	 * (Component) e.getSource(); Dimension dim = c.getSize(); canvasWidth =
	 * dim.width; canvasHeight = dim.height;
	 * 
	 * box.set(0, 0, canvasWidth, canvasHeight); } });
	 * 
	 * gameStart(); }
	 * 
	 * 
	 * public void gameStart() { Random rand = new Random(); balls = new Ball[Max];
	 * 
	 * for (int i = 0; i < z; i++) {
	 * 
	 * int radius = rand.nextInt(50) + 50; int x = rand.nextInt(canvasWidth - radius
	 * * 2 - 20) + radius + 10; int y = rand.nextInt(canvasHeight - radius * 2 - 20)
	 * + radius + 10; int speed = 5; int angleInDegree = rand.nextInt(360); Color
	 * color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	 * 
	 * balls[i] = new Ball(x, y, radius, speed, angleInDegree, color); }
	 * 
	 * Thread gameThread = new Thread() { public void run() { while (true) {
	 * 
	 * gameUpdate();
	 * 
	 * repaint();
	 * 
	 * try { Thread.sleep(1000 / UPDATE_RATE); } catch (InterruptedException ex) { }
	 * 
	 * } } };
	 * 
	 * gameThread.start(); // Invoke GaemThread.run()
	 * 
	 * }
	 * 
	 * public void gameUpdate() { for (int i = 0; i < z; i++) {
	 * balls[i].moveOneStepWithCollisionDetection(box); } }
	 * 
	 * class DrawCanvas extends JPanel {
	 * 
	 * @Override public void paintComponent(Graphics g) { super.paintComponent(g);
	 * box.draw(g); for (int i = 0; i < z; i++) { balls[i].draw(g); }
	 * g.setColor(Color.WHITE); g.setFont(new Font("Courier New", Font.PLAIN, 12));
	 * }
	 * 
	 * @Override public Dimension getPreferredSize() { return (new
	 * Dimension(canvasWidth, canvasHeight)); } }
	 */

}
