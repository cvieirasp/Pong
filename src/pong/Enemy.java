package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy {
	
	private final int WIDTH = 40;
	private final int HEIGHT = 5;
	
	private double x, y;
	
	public Enemy() {
		this.x = (Game.WIDTH-WIDTH)/2;
		this.y = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}
	
	public void tick() {
		double start = 0.05;
		double end = 0.10;
		double random = new Random().nextDouble();
		double result = start + (random * (end - start));
		x += (Game.ball.getX() - x - 6) * result;
		
		if (x + WIDTH > Game.WIDTH) {
			x = Game.WIDTH - WIDTH;
		}
		
		if (x < 0) {
			x = 0;
		}
	}
	
	public void render(Graphics graph) {
		graph.setColor(Color.WHITE);
		graph.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
}
