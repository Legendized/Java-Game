package com.Game.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Window  extends Canvas{

	private static final long serialVersionUID = -2952851119845379454L;

	public Window(Dimension resolution, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(resolution.width, resolution.height));
		frame.setMinimumSize(new Dimension(resolution.width, resolution.height));
		frame.setMaximumSize(new Dimension(resolution.width, resolution.height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		frame.requestFocus();
		
		ImageIcon img = new ImageIcon("icon.png");
        frame.setIconImage(img.getImage());
        
		game.start();
	}
	
	
}
