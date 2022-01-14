package com.Game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Player extends GameObject {
	
	public double velX, velY;
	private int size = 24;
	private double walkSpeed = 48;
	private double airMove = 24;
	public double speedModifier = 1;
	private double drag = 4;
	private double airdrag = 1/3;
	private double maxMoveSpeed = 10;
	private boolean grounded;
	private double jumpForce = 20;
	private double lastvelY;
	private boolean moving;
	private boolean jumping;
	private boolean falling;
	public int groundCounter = 50;
	private double lerpedGroundCount = 50;
	
	public static Player Instance;
	
	public boolean walkingLeft = false;
	public boolean walkingRight = false;

	public Player(int xPos, int yPos, Layer layer, String name) {
		super(xPos, yPos, layer, name);
		
		Instance = this;
	}
	
	public void Jump() {
		if (!grounded) return;
			
		velY = -jumpForce;
		groundCounter = 50;
	}

	@Override
	public void tick() {
		grounded = (velY == 0 && lastvelY == 0);
		falling = ((int)velY < 0 && !grounded);
		jumping = ((int)velY > 0 && !grounded);
		
		if (grounded) groundCounter--;
		
		lastvelY = velY;
		
		velY += Constants.gravity / Game.tickRate;
		
		boolean overwriteDrag = false;
		
		double fixedDeltaTime = 1/Game.tickRate;
		
		double amount = (walkSpeed*speedModifier*fixedDeltaTime);
		if (!grounded) amount = (airMove*speedModifier*fixedDeltaTime);
		
		if (walkingLeft) {
			velX -= (amount + (velX/36));
			if (velX > 0) overwriteDrag=true;
		}
		if (walkingRight) {
			velX += (amount - (velX/36));
			if (velX < 0) overwriteDrag=true;
		}
		
		moving = walkingLeft | walkingRight;
		
		if (walkingLeft && walkingRight) overwriteDrag = true;
		
		if ((!moving || overwriteDrag) && grounded) velX /= (1+(drag*fixedDeltaTime));
		if (!moving && !grounded) velX /= (1+(airdrag*fixedDeltaTime));
		
		if (velX > maxMoveSpeed) velX = maxMoveSpeed;
		if (velX < -maxMoveSpeed) velX = -maxMoveSpeed;
		
		x += velX;
		y += velY;
		
		if ((y-(size/2)) > 537) {
			y = 537+(size/2);
			if (velY > 0) velY = 0;
		}
		
		if (x+size > 956) {
			x = 956-size;
			if (velX > 0) velX = 0;
		}
		if (x < 0+(size/2)) {
			x = 0+(size/2);
			if (velX < 0) velX = 0;
		}
		
		if (Math.abs(velX) < 0.13333) velX = 0;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.fillRect((int)(x - (size/2)), (int)(y-(size/2)), size, size);
		
		g.setColor(Color.white);
		
		int os = 3;
		
		if (!walkingLeft && !walkingRight || walkingLeft && walkingRight) {
			int offset = 0;
		
			if (jumping) offset = os;
			else if (falling) offset = -os;
		
			g.fillRect((int)(x-6)-1, (int)(y-8)+offset, 4, 4);
			g.fillRect((int)(x+6)-2, (int)(y-8)+offset, 4, 4);
		}
			else if (walkingRight) {
			int offset = 0;
			
			if (jumping) offset = os;
			else if (falling) offset = -os;
			
			g.fillRect((int)(x-6)+2, (int)(y-8)+offset, 4, 4);
			g.fillRect((int)(x+6)+2, (int)(y-8)+offset, 4, 4);
		} else if (walkingLeft) {
			
			int offset = 0;
			
			if (jumping) offset = os;
			else if (falling) offset = -os;
			
			g.fillRect((int)(x-6)-5, (int)(y-8)+offset, 4, 4);
			g.fillRect((int)(x+6)-5, (int)(y-8)+offset, 4, 4);
		}
		
		g.setColor(Color.red);
		
		double bigger =(x+(velX*6));
		if (bigger <= (y+(velY*6))) bigger = (y+(velY*6));
		
		double mag = Utilities.GetMagnitude(((velX)), ((velY)));
				
		double avx = ((velX)) / bigger;
		double avy = ((velY)) / bigger;
		
		avx *= mag*384;
		avy *= mag*128;
		
		avx += x;
		avy += y;
		
		//g.drawLine((int)x, (int)y, (int)avx, (int)avy);
		
		lerpedGroundCount = Utilities.Lerp(lerpedGroundCount, (double)groundCounter, 0.05*Game.Instance.deltaTime);
		
		g.fillRect(180, 40, (int)(lerpedGroundCount*3*4), 32);
	}

}
