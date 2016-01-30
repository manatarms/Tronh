import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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
    float x = 10;
    float velocity = 0;
    float gravity = .5f;
    boolean canRun = false;
    boolean right = false;
    boolean left = false;
    boolean up = false;
    boolean down = false;
    Image banner;
    boolean first = true;
    boolean gotcoin =false;
    Coin coin = new Coin(WIDTH, HEIGHT);
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
		
		g.setColor(Color.DARK_GRAY);
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		if(p1.pressed(Button.R))
		{
			canRun = true;
			right = true;
			left = false;
			up = false;
			down = false;
		}
		if(p1.pressed(Button.L)){
			canRun = true;
			right = false;
			left = true;
			up = false;
			down = false;
		}
		if(p1.pressed(Button.U)){
			canRun = true;
			right = false;
			left = false;
			up = true;
			down = false;
		}
		if(p1.pressed(Button.D)){
			canRun = true;
			right = false;
			left = false;
			up = false;
			down = true;
		}
		
		
		if(canRun && right){
			velocity = 10;
			x += velocity;
		}
		
		if(canRun && left){
			velocity = 10;
			x -= velocity;
		}
		
		if(canRun && up){
			velocity = 10;
			y -= velocity;
		}
		
		if(canRun && down){
			velocity = 10;
			y += velocity;
		}		
		

		g.setColor(Color.RED);
		g.fillRect((int)x,(int)y,50, 50);
		
		int coinX = coin.getX(),coinY = coin.getY();
		
		//Check collisions
		Rectangle playerRect = new Rectangle((int)x,(int)y, 50, 50);
		Rectangle coinRectangle = new Rectangle((int)coinX,(int)coinY, 20, 20);
		
		if(collision(playerRect, coinRectangle)){
			coin = new Coin(WIDTH, HEIGHT);
			coinX = coin.getX();
			coinY = coin.getY();
		 	coin.drawCoin(g, coinX, coinY);
		}
		else{
			coin.drawCoin(g, coinX, coinY);
		}	
		
		//Reset Player
		if(outOfBounds((int) x,(int)y)){
			//Reset Player method comes here using hard coded for now
			   y = 10;
			   x = 10;
			   canRun = false;
		}
		
		
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
	
	//Detect collision
		public boolean collision(Rectangle r,Rectangle q){
		
		        if(r.intersects(q)){
		        	return true;}
		        else{
		        	return false;	
		        	}
		}
	
		//Detect collision
		public boolean outOfBounds(int x, int y){
				
		        if(x < 0 || x > WIDTH || y < 0 || y> HEIGHT){
		        	return true;}
		        else{
		        	return false;	
		        	}
				}

	public static void main(String[] args) {
		Arcadia.display(new Arcadia(new Game[] {new Tronh_Game(), new IntroGame(), new BasicGame()}));
	}
}
	
	//Class Coin
	final class Coin {
		int w=20,h = 20;
	    private final int Coinx;
	    private final int Coiny;
	        
	    public Coin(int WIDTH, int HEIGHT) {
	    	int minX =1, maxX = WIDTH-w;
			int minY =1, maxY = HEIGHT-h;
			int randX = minX + (int)(Math.random() * ((maxX - minX) + 1));
			int randY = minY + (int)(Math.random() * ((maxY - minY) + 1));
	        this.Coinx = randX;
	        this.Coiny = randY;
	    }
	    public int getX() {
	        return Coinx;
	    }
	    public int getY() {
	        return Coiny;
	    }
	      
	    public void drawCoin(Graphics2D g, int x,int y){
	    	g.setColor(Color.YELLOW);
			g.fillOval(x,y, w, h);
	    }
	        
	
}//End Tronh class