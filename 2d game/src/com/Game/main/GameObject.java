package com.Game.main;

import java.awt.Graphics;
import java.util.Random;

public abstract class GameObject {
	
	protected int objectId;

	protected double x = 0, y = 0;
	
	protected Layer objectLayer = Layer.Static;
	
	protected String objectName = "gameObject";
	
	public GameObject(int xPos, int yPos, Layer layer, String name) {
		Game.Instance.handler.addObject(this);
		Random rand = new Random();
		objectId = rand.nextInt((int)Math.pow(2, 32));
		boolean isIdUnique = false;
		while (!isIdUnique) {
			if (Game.Instance.handler.findObjectByID(objectId) != this) {
				objectId = rand.nextInt((int)Math.pow(2, 32));
				isIdUnique = false;
			} else {
				isIdUnique = true;
			}
			
		}
		if (name != null && name != "") objectName = name;
		if (layer != null) objectLayer = layer;
		x = xPos;
		y = yPos;
		System.out.println("Created GameObject.   " + objectName + "   " + objectLayer + "    " + objectId);
	}
	
	public abstract void tick();
	public abstract void draw(Graphics g); 
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setId(int id) {
		this.objectId = id;
	}
	
	public int getId() {
		return objectId;
	}
	
	public void setLayer(Layer layer) {
		this.objectLayer = layer;
	}
	
	public Layer getLayer() {
		return objectLayer;
	}
	
	public void setName(String name) {
		this.objectName = name;
	}
	
	public String getName() {
		return objectName;
	}
	
}
