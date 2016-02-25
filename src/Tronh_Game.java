import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import arcadia.Arcadia;
import arcadia.Button;
import arcadia.Game;
import arcadia.Input;
import arcadia.Sound;
import basicGame.BasicGame;
import intro.IntroGame;
import sun.applet.Main;

public class Tronh_Game extends Game {

	float y = 10;
	float x = 10;
	float velocity = 0;
	float gravity = .5f;
	boolean canRun = false;
	boolean right = false;
	boolean left = false;
	boolean up = false;
	boolean down = false;
	Image banner, background, player_L, player_R, player_U, player_D;
	boolean first = true;
	boolean gotcoin = false;
	int coinTotal;
	int highScore;
	int maxCount = 1;
	int trigger = 100;
	Coin coin = new Coin(WIDTH, HEIGHT);
	Score sc = new Score();
	Enemy enemy = new Enemy(WIDTH, HEIGHT);
	int enemyX, enemyY;
	int dir;
	int enemySpeed =5;
	String	collectsound ="src/sounds/collect.wav";

	public Tronh_Game() {
		try {
			banner = ImageIO.read(Tronh_Game.class.getResource("images/tronh_banner.png"));
			background = ImageIO.read(Tronh_Game.class.getResource("images/Background2.png"));
			player_U = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_U.png"));
			player_D = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_D.png"));
			player_L = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_L.png"));
			player_R = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_R.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick(Graphics2D g, Input p1, Input p2, Sound s) {

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// Added new background (not yet sized properly)
		g.drawImage(background, 0, 0, null);
		sc.drawScore(g, 890,30);
		
		if (p1.pressed(Button.R)) {
			canRun = true;
			right = true;
			left = false;
			up = false;
			down = false;
			dir = 3;
		}
		if (p1.pressed(Button.L)) {
			canRun = true;
			right = false;
			left = true;
			up = false;
			down = false;
			dir = 2;
		}
		if (p1.pressed(Button.U)) {
			canRun = true;
			right = false;
			left = false;
			up = true;
			down = false;
			dir = 1;
		}
		if (p1.pressed(Button.D)) {
			canRun = true;
			right = false;
			left = false;
			up = false;
			down = true;
			dir = 0;
		}

		if (canRun && right) {
			g.drawImage(player_R, (int) x, (int) y, null);
			velocity = 10;
			x += velocity;
		

		}

		if (canRun && left) {
			g.drawImage(player_L, (int) x, (int) y, null);
			velocity = 10;
			x -= velocity;
		
		}

		if (canRun && up) {
			g.drawImage(player_U, (int) x, (int) y, null);
			velocity = 10;
			y -= velocity;
			
		}

		if (canRun && down) {
			g.drawImage(player_D, (int) x, (int) y, null);
			velocity = 10;
			y += velocity;
		}

		// Player rendering
		if (!canRun) {
			g.drawImage(player_D, (int) x, (int) y, null);
			
		}

		int coinX = coin.getX(), coinY = coin.getY();

		// Check collisions based on player movement direction
		Rectangle playerRect = null;
		Rectangle enemyRectangle =null;
		if (down || up || !canRun){
			playerRect = new Rectangle((int) x, (int) y, player_U.getWidth(null), player_U.getHeight(null));
			enemyRectangle = new Rectangle(enemy.getX(), enemy.getY(),player_U.getWidth(null), player_U.getHeight(null));//hard coded this for now
		}
		if (left || right){
			playerRect = new Rectangle((int) x, (int) y, player_L.getWidth(null), player_L.getHeight(null));
			enemyRectangle = new Rectangle(enemy.getX(), enemy.getY(),  player_L.getWidth(null), player_L.getHeight(null));//hard coded this for now
		}
		
		//Checks collision
		Rectangle coinRectangle = new Rectangle((int) coinX, (int) coinY, 20, 20);
		g.draw(coinRectangle);
		g.draw(enemyRectangle);
		g.draw(playerRect);
		//enemy.moveEnemy((int)x, (int)y,enemySpeed);
		enemy.moveEnemy((int)coinX, (int)coinY,enemySpeed);
		enemy.drawEnemy(g, enemy.getX(), enemy.getY(), dir);
		
		if (collision(playerRect, coinRectangle)) {
			coin = new Coin(WIDTH, HEIGHT);
			coinX = coin.getX();
			coinY = coin.getY();
			coin.drawCoin(g, coinX, coinY);
			sc.addCoin();
		    sc.saveScore();
		    playSound(collectsound);
		
//			enemyTriggerCounter = 1;
		} 
		else if(collision(enemyRectangle, coinRectangle)){
			coin = new Coin(WIDTH, HEIGHT);
			coinX = coin.getX();
			coinY = coin.getY();
			coin.drawCoin(g, coinX, coinY);
			
		}
		else {
			coin.drawCoin(g, coinX, coinY);
			trigger = (trigger + 1 < maxCount ? trigger + 1 :0);
			if(maxCount==100){
				enemySpeed++;
			}
		
		}

		if (collision(playerRect, enemyRectangle)) {
			y = 25;
			x = 25;
			enemy.resetEnemy();
//			enemyTriggerCounter = 1;
			canRun = false;
			sc.resetCoin();
			
		}

		// Reset Player out of bounds
		if (outOfBounds((int) x, (int) y)) {
			// Reset Player method comes here using hard coded for now
			y = 25;
			x = 25;
			canRun = false;
			sc.resetCoin();
		}

	}

	public static void playSound( String url) {
		 
		 
		try {
	         // Open an audio input stream.           
	          File soundFile = new File(url); //you could also get the sound file with an URL
	       
	          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
	         // Get a sound clip resource.
	         Clip clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioIn);
	         clip.start();
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
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

	// ----- Utilities -----
	// Detect collision
	public boolean collision(Rectangle r, Rectangle q) {

		if (r.intersects(q)) {
			return true;
		} else {
			return false;
		}
	}

	// Detect collision
	public boolean outOfBounds(int x, int y) {

		if (x < 0 || x > WIDTH - 0 || y < 0 || y > HEIGHT - 0) {
			return true;
		} else {
			return false;
		}
	}

	// random
	public float randFloat(float Min, float Max) {
		return Min + (float) (Math.random() * (Max - Min + 1));
	}

	public static void main(String[] args) {
		Arcadia.display(new Arcadia(new Game[] { new Tronh_Game(), new IntroGame(), new BasicGame() }));
	}

}// End Tronh class