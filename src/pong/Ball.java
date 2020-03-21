package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	private final int WIDTH = 5;
	private final int HEIGHT = 5;
	
	private double x, y;
	private double dx, dy;
	private double speed = 2;
	
	public Ball() {
		this.x = (Game.WIDTH-WIDTH)/2;
		this.y = (Game.HEIGHT-HEIGHT)/2;
		setAngle();
	}
	
	public double getX() {
		return x;
	}
	
	private void setAngle() {
		int angle = new Random().nextInt(120 - 45) + 46;
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		
		if (x + (dx * speed) + WIDTH >= Game.WIDTH) {
			dx *= -1;
		} else if (x + (dx * speed) < 0) {
			dx *= -1;
		}
		
		if (y > Game.HEIGHT) {
			// Ponto do Inimigo.
			System.out.println("Ponto do Adversário!");
			new Game();
			return;
		} else if (y < 0) {
			// Ponto do Jogador.
			System.out.println("Ponto do Jogador!");
			new Game();
			return;
		} 
		
		Rectangle bounds = new Rectangle((int)(x + (dx * speed)), (int)(y + (dy * speed)), WIDTH, HEIGHT);
		Rectangle boundsPlayer = Game.player.getBounds();
		Rectangle boundsEnemy = Game.enemy.getBounds();
		
		if (bounds.intersects(boundsPlayer)) {
			setAngle();
			if (dy > 0)
				dy *= -1;
		} else if (bounds.intersects(boundsEnemy)) {
			setAngle();
			if (dy < 0)
				dy *= -1;
		}
		
		x += dx * speed;
		y += dy * speed;
	}
	
	public void render(Graphics graph) {
		graph.setColor(Color.RED);
		graph.fillOval((int)x, (int)y, WIDTH, HEIGHT);
	}
}
