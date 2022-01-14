package com.Game.main;

import java.awt.Color;
import java.awt.Graphics;

public class FallingCircle extends GameObject {
	
	public static float radius = 32;
	public static float outlineRadius = 40;
	
	boolean stop = false;

	public FallingCircle(int xPos, int yPos, Layer layer, String name) {
		super(xPos, yPos, layer, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		if (stop) return;
		y += Constants.gravity/6;
		
		if (y > Game.HEIGHT) {
			Game.Instance.handler.removeObject(this);
			stop = true;
		}
		
	}

	@Override
	public void draw(Graphics g) {
		if (stop) return;
		// TODO Auto-generated method stub
		
		g.setColor(Color.red);
		g.fillOval((int)(x-(outlineRadius/2)), (int)(y-(outlineRadius/2)), (int)outlineRadius, (int)outlineRadius);
		
		g.setColor(Color.yellow);
		g.fillOval((int)(x-(radius/2)), (int)(y-(radius/2)), (int)radius, (int)radius);
		
	}

}
