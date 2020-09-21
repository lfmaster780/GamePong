package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	public double x,y;
	public int width = 40;
	public int height = 5;
	
	public Enemy(int x,int y) {
		this.x = x;
		this.y = y;
	}

	
	public void update() {
		
		if (x+(Pong.ball.x - this.x - 7) * 0.07 > Pong.WIDTH-40) {
			x = Pong.WIDTH-40;
		}
		else if (x + (Pong.ball.x - this.x - 7) * 0.07 < 0) {
			x = 0;
		}
		else {
			x +=  (Pong.ball.x - this.x - 7) * 0.07;
		}
		
	}
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)this.x, (int)this.y, width, height);
	}
}
