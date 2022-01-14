package com.Game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 7008609043023548655L;
	
	public static Game Instance;
    public static double deltaTime = 0;

	public static final int WIDTH = 960, HEIGHT = WIDTH / 16*10;
	private Thread thread;
	private boolean running = false;
	
	private Random rand;
	
	public Handler handler;
	
	public Window window;
	
	public Player player;
	
	public int score = 0;
	
	public boolean gameOver = false;
	
    public static double tickRate = 48.0;
	
	public Game() {
		Dimension dim = new Dimension(WIDTH, HEIGHT);
		window = new Window(dim, "Let's Build a Game!", this);
		System.out.println("Created instance of window.");
		
		handler = new Handler(); System.out.println("Created instance of handler.");
		player = new Player(480, 100, Layer.Player, "Player");System.out.println("Created instance of player.");
		this.addKeyListener(new KeyInput(handler, player));System.out.println("Created instance of keylistener.");
		rand = new Random();
	}
	
	public synchronized void start() {
		System.out.println("OnStart();");
		thread = new Thread(this);
		thread.start(); System.out.print("Thread Started, ");
		running = true; System.out.println("Running!");
		Instance = this;
	}
	
	public synchronized void stop() {
		try {
			System.out.println("Stopping.");
			thread.join();
			running = false;
		} catch(Exception _ex) {
			System.out.println("-----------------error in stop method in game.java---------------");
			_ex.printStackTrace();
			System.out.println("-----------------------------------------------------------------");
		}
	}
	
	public void die() {
		for (int i = 0; i<handler.object.size(); i++) {
			GameObject tObject = handler.object.get(i);
			
			if (tObject.objectLayer == Layer.Enemy) {
				if (Utilities.GetDistanceSquared2D(tObject.x, tObject.y, player.x, player.y) < 24) {
					gameOver = true;
				}
			}
		}
	}
	
	public void run()
    {
        long lastTime = System.nanoTime();
        double ns = 1000000000 / tickRate;
        long timer = System.currentTimeMillis();
        long timer2 = System.currentTimeMillis();
        int dec = 1000;
        int frames = 0;
        while(running)
        {
                    long now = System.nanoTime();
                    deltaTime += (now - lastTime) / ns;
                    lastTime = now;
                    while(deltaTime >=1)
                            {
                                tick();
                                die();
                                deltaTime--;
                            }
                            if(running)
                                render();
                            frames++;
                            
                            if(System.currentTimeMillis() - timer > 1000)
                            {
                                timer += 1000;
                                //System.out.println("FPS: "+ frames);
                                score++;
                                System.out.println("Score: " + (score-1));
                                frames = 0;
                                if (dec<66)dec=66;
                                if (dec>66)dec--;
                                if (dec>100)dec--;
                                if (dec>200)dec--;
                                if (dec>300)dec--;
                                if (dec>400)dec--;
                                if (dec>500)dec--;
                                if (dec>600)dec--;
                                if (dec>700)dec--;
                                if (dec>800)dec--;
                                if (dec>900)dec--;
                            }
                            if(System.currentTimeMillis() - timer2 > dec)
                            {
                                timer2 += dec;
                                int xPos = rand.nextInt(WIDTH-40);
                                handler.addObject(new FallingCircle(xPos, -200, Layer.Enemy, "FallingCircle"));
                            }
        }
                stop();
    }
	
	private void tick() {
		if (player.groundCounter <= 0) gameOver = true;
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs==null) {
			this.createBufferStrategy(3);
			System.out.println("no buffer strategy found, creating one.");
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.draw(g);
		
		g.setFont(g.getFont().deriveFont(40.0f));
		g.drawString("Score: " + score, 4, 36);
		
		if (gameOver) {
			g.setFont(g.getFont().deriveFont(128.0f));
			g.drawString("GAME OVER", 76, 156);
		}
		
		g.dispose();
		bs.show();
		
		if (gameOver) stop();
	}
	
	public static void main(String args[]) {
		System.out.println("Running main: " + args.toString());
		new Game();
	}
	
}
