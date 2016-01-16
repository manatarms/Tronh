import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import arcadia.Arcadia;
import arcadia.Game;
import arcadia.Input;
import arcadia.Sound;

public class Trohn_Game extends Game{
	float y =10;
	float velocity = 0;
	float gravity = 0.5f;
	boolean canJump = false;
	@Override
	public void tick(Graphics2D g, Input p1, Input p2, Sound s) {
		// TODO Auto-generated method stub
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		velocity += gravity;
		y+=velocity;
		if(y +100 > HEIGHT){
			velocity =0;
			y=HEIGHT - 100;
			canJump = true;
		}
		if(canJump){
			velocity =-10;
			canJump= false;
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image banner() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args){
		Arcadia.display(new Arcadia(new Trohn_Game()));
		
	}
}
