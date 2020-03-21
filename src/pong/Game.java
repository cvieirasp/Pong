package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	public final static int WIDTH = 200;
	public final static int HEIGHT = 200;
	public final static int SCALE = 3;
	private final double FPS = 60.0;
	
	private static boolean isRunning = false;
	private BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this);
		this.setFocusable(true);
		player = new Player();
		enemy = new Enemy();
		ball = new Ball();		
	}

	public static void main(String[] args) {
		Game game = new Game();
		
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);		
		frame.setVisible(true);
		
		isRunning = true;
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graph = layer.getGraphics();
		
		graph.setColor(Color.BLACK);
		graph.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(graph);
		enemy.render(graph);
		ball.render(graph);
		
		graph = bs.getDrawGraphics();
		graph.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		bs.show();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double ns = 1000000000 / FPS;
		double delta = 0;
		// int frames = 0;
		// double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				// frames++;
				delta--;
			}
			
			/* 
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
		 		timer += 1000;
			}
			*/
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.moveToRight();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.moveToLeft();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
