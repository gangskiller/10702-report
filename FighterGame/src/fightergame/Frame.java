package fightergame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Frame extends JFrame {
	// private JLayeredPane layeredPane;
	private BackgroundPanel backgroundpanel;
	private GamePanel gamepanel;

	public Frame() {
		super();
		gamepanel = new GamePanel(800, 800);
		add(gamepanel, BorderLayout.CENTER);

		/*
		 * JLayeredPanel測試 layeredPane=this.getLayeredPane(); backgroundpanel = new
		 * BackgroundPanel(); add(backgroundpanel,BorderLayout.CENTER);
		 * layeredPane.add(backgroundpanel,new Integer(0)); backgroundpanel.setBounds(0,
		 * 0, 800,800); gamepanel = new GamePanel(800,800);
		 * layeredPane.add(gamepanel,new Integer(200)); gamepanel.setBounds(0, 0,
		 * 800,800);
		 */
	}
}
