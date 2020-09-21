package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right;
	public boolean left;
	public int x;
	public int y;
	public int width = 40;
	public int height = 5;
	
	public Player(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		if (right) {
			x++;
		}
		else if(left) {
			x--;
		}
		
		if(this.x+width > Pong.WIDTH) {
			this.x = Pong.WIDTH - width;
		}
		else if(this.x < 0) {
			this.x = 0;
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
