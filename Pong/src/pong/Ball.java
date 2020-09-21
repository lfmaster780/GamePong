package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public double x,y;
	public int width = 4;
	public int height = 4;
	
	public double dx,dy;
	public double speed = 1.7;
	
	public Ball(int x,int y) {
		this.x = x;
		this.y = y;
		int angle = new Random().nextInt(75) + 45;
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}

	
	public void update() {
		
		if (x+(dx*speed) + width >= Pong.WIDTH) {
			dx *= -1;
		}
		else if (x + (dx*speed) < 0) {
			dx *= -1;
		}
		
		if (y >= Pong.HEIGHT) {
			//PONTO INIMIGO
			System.out.println("Ponto Inimigo");
			new Pong();
			return;
		}
		else if (y <= 0) {
			//PONTO JOGADOR
			System.out.println("Ponto Jogador");
			new Pong();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+dy*speed), this.width, this.height);
		Rectangle playerbounds = new Rectangle((int)Pong.player.x,(int)Pong.player.y, Pong.player.width, Pong.player.height);
		Rectangle enemybounds = new Rectangle((int)Pong.enemy.x,(int)Pong.enemy.y, Pong.enemy.width, Pong.enemy.height);
		
		if(bounds.intersects(playerbounds)) {
			dy*= -1;
			int angle = new Random().nextInt(75) + 45;
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if (dy > 0) {
				dy *= -1;
			}
		}
		else if (bounds.intersects(enemybounds)) {
			dy *= -1;
			int angle = new Random().nextInt(75) + 45;
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if (dy < 0) {
				dy *= -1;
			}
		}
		x += dx*speed;
		y += dy*speed;
		
	}
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int)this.x, (int)this.y, width, height);
	}
}
