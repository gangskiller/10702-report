package fightergame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	protected Image backgroundimage;
	public BackgroundPanel() {
		super();	
		backgroundimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("星系.jpeg"));
		
	}
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundimage!=null){
            g.drawImage(backgroundimage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
