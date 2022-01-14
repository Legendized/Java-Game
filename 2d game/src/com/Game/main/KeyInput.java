package com.Game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private Player player;
	
	public KeyInput(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		char ascii = Utilities.toChar(key);
		
		if (ascii == 'A' || key == KeyEvent.VK_LEFT) player.walkingLeft = true;
		if (ascii == 'D' || key == KeyEvent.VK_RIGHT) player.walkingRight = true;
		
		if (ascii == ' ' || key == KeyEvent.VK_UP || ascii == 'W') player.Jump();
		
		//System.out.println(ascii + " pressed.");
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		char ascii = Utilities.toChar(key);
		
		if (ascii == 'A' || key == KeyEvent.VK_LEFT) player.walkingLeft = false;
		if (ascii == 'D' || key == KeyEvent.VK_RIGHT) player.walkingRight = false;
		
		
		//System.out.println(ascii + " released.");
	}
	
}
