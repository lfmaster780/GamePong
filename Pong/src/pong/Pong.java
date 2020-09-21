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

public class Pong extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 240;
	public static int HEIGHT = 150;
	public static int SCALE = 3;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public Pong() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
		Pong.player = new Player(100,HEIGHT-5);
		Pong.enemy = new Enemy(100,0);
		Pong.ball = new Ball(100,HEIGHT/2 - 1);
	}

	public static void main(String[] args) {
		Pong game = new Pong();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void update() {
		enemy.update();
		player.update();
		ball.update();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
		
	}
	
	@Override
	public void run() {
		while(true) {
			this.update();
			this.render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = false;
		}
		
	}
	

}
