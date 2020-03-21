package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
	
	private final int WIDTH = 40;
	private final int HEIGHT = 5;
	
	private boolean moveToRight;
	private boolean moveToLeft;
	private int x, y;
	
	public void moveToLeft() {
		this.moveToLeft = true;
		this.moveToRight = false;
	}
	
	public void moveToRight() {
		this.moveToRight = true;
		this.moveToLeft = false;
	}
	
	public Player() {
		this.x = (Game.WIDTH-WIDTH)/2;
		this.y = Game.HEIGHT-HEIGHT;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public void tick() {
		if (moveToRight) {
			x++;
		} else if (moveToLeft) {
			x--;
		}
		
		if (x + WIDTH > Game.WIDTH) {
			x = Game.WIDTH - WIDTH;
		}
		
		if (x < 0) {
			x = 0;
		}
	}
	
	public void render(Graphics graph) {
		graph.setColor(Color.WHITE);
		graph.fillRect(x, y, WIDTH, HEIGHT);
	}
}
