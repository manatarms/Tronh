import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

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
		
		g.setColor(Color.GREEN);
		
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
		

		g.setColor(Color.BLACK);
		g.fillRoundRect((int)x,(int)y,50, 50, 20, 20);
		
		int coinX = coin.getX(),coinY = coin.getY();		
		
		if((int)x != coinX){
			System.out.println("This is x"+x);
			System.out.println("This is CoinX"+coinX);
			coin.drawCoin(g, coinX, coinY);
		 	//System.out.println("Drawing a OLD coin");
			
		}
			
		else{
				Coin coinnew = new Coin(WIDTH, HEIGHT);
				int coinnewX = coinnew.getX(),coinnewY = coinnew.getY();
			 	coinnew.drawCoin(g, coinnewX, coinnewY);
			 //	System.out.println("Drawing a new coin----------------------");
				
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
	
	
	
	
	public static void main(String[] args) {
		Arcadia.display(new Arcadia(new Game[] {new Tronh_Game(), new IntroGame(), new BasicGame()}));
	}
}
	
	//Class Coin
	final class Coin {
        
	    private final int Coinx;
	    private final int Coiny;
	        
	    public Coin(int WIDTH, int HEIGHT) {
	    	int minX =1, maxX = WIDTH;
			int minY =1, maxY = HEIGHT;
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
	    	int w=15,h = 15;
	    	g.setColor(Color.BLACK);
			g.fillOval(x,y, w, h);
	    }
	        
	}

