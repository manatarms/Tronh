import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import arcadia.Arcadia;
import arcadia.Button;
import arcadia.Game;
import arcadia.Input;
import arcadia.Sound;
import basicGame.BasicGame;
import intro.IntroGame;

public class Tronh_Game extends Game{
	
    float y = 10;
    float velocity = 0;
    float gravity = .5f;
    boolean canJump = false;
    Image banner;
    
    public Tronh_Game(){
    	try{
    	banner = ImageIO.read(Tronh_Game.class.getResource("banner.png"));
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    	}
    
	@Override
	public void tick(Graphics2D g, Input p1, Input p2, Sound s) {
		
		g.setColor(Color.pink);
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		velocity += gravity;
		
		y+= velocity;
		
		if(y +100 > HEIGHT){
			velocity = 0;
			y = HEIGHT - 100;
		canJump = true;
		}
		
		if(canJump && p1.pressed(Button.A))
		{
			velocity = -10;
			canJump = false;
		}
		g.setColor(Color.BLACK);
		g.fillOval(0,(int)y,100, 100);
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image banner() {
		// Dimensions : 512 x 128
		return banner;
	}
	public static void main(String[] args) {
		Arcadia.display(new Arcadia(new Game[] {new Tronh_Game(), new IntroGame(), new BasicGame()}));
	}
}
