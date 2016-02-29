import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import arcadia.Arcadia;
import arcadia.Game;
import arcadia.Input;
import arcadia.Sound;
import basicGame.BasicGame;
import intro.IntroGame;

public class Tronh_Game extends Game {

	int timecounter = 0;

	Image banner, background;
	boolean first = true;
	boolean gotcoin = false;
	int coinTotal;
	int highScore;
	int maxCount = 1;
	int trigger = 100;

	int enemySpeed = 5;
	int playerSpeed = 10;
	String enemyDirection = "UP";
	String collectsound = "src/sounds/collect.wav";

	PowerUp pup = new PowerUp(WIDTH, HEIGHT);
	Coin coin = new Coin(WIDTH, HEIGHT);
	Score sc = new Score();
	Score enemyScore = new Score();
	Enemy enemy = new Enemy();
	Player player = new Player();

	public Tronh_Game() {
		try {
			banner = ImageIO.read(Tronh_Game.class.getResource("images/tronh_banner.png"));
			background = ImageIO.read(Tronh_Game.class.getResource("images/tronh_background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		enemy.setDirection("UP");
=======
		
>>>>>>> 53e7f8369e88786c450a28f229cb4e3ce4f46a70
	}

	@Override
	public void tick(Graphics2D g, Input p1, Input p2, Sound s) {

		// Setting the graphics objects
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Initializations in tick
		int coinX = coin.getX(), coinY = coin.getY();
		Rectangle playerRect = null;
<<<<<<< HEAD
		Rectangle enemyRectangle = null;
		Rectangle coinRectangle = null;

=======
		
		
>>>>>>> 53e7f8369e88786c450a28f229cb4e3ce4f46a70
		// Rendering graphics
		g.drawImage(background, 0, 0, null);
		player.drawPlayer(g);
		enemy.drawEnemy(g, enemy.getX(), enemy.getY(), enemy.getDirection());

		// Checking if the player is active.
		player.checkPressed(p1);

		if (player.canRun) {
			// generate score
			sc.drawScore(g, 20, 30, "Your score: ");
			enemyScore.drawScore(g, 890, 30, "Enemy score: ");

			// move player and enemy
			player.Move(player.getX(), player.getY(), playerSpeed);
			enemy.moveEnemy(coinX, coinY, enemySpeed);
		}

		timecounter++;
		if (timecounter == 100) {
			timecounter = 0;
			playerSpeed = 10;
		}

		// Check collisions initialization
		playerRect = new Rectangle(player.getX(), player.getY(), player.getPlayerWidth(player.getDirection()),
				player.getPlayerHeight(player.getDirection()));
		enemyRectangle = new Rectangle(enemy.getX(), enemy.getY(), enemy.getEnemyWidth(enemy.getDirection()),
				enemy.getEnemyHeight(enemy.getDirection()));// hard coded this
															// for now
		coinRectangle = new Rectangle(coinX, coinY, 20, 20);
		Rectangle powerUpRectangle = new Rectangle(pup.getX() - 60, pup.getY() - 60, 60, 60);
		
<<<<<<< HEAD
	
=======
		
>>>>>>> 53e7f8369e88786c450a28f229cb4e3ce4f46a70
		//g.draw(playerRect);
		//g.draw(enemyRectangle);
		//g.draw(coinRectangle);
		g.draw(powerUpRectangle);

		// Check collisions between player and enemy

		if (collision(playerRect, coinRectangle)) {
			coin = new Coin(WIDTH, HEIGHT);
			coin.drawCoin(g, coin.getX(), coin.getY());
			sc.addCoin();
			sc.saveScore();
			playSound(collectsound);
		}
		// Check collisions between enemy and coin
		else if (collision(enemyRectangle, coinRectangle)) {
			coin = new Coin(WIDTH, HEIGHT);
			coin.drawCoin(g, coin.getX(), coin.getY());
			enemyScore.addCoin();
			enemyScore.saveScore();
		}

		else if (collision(playerRect, powerUpRectangle)) {
			pup.setType();
			pup = new PowerUp(WIDTH, HEIGHT);
			timecounter = 0;
		}

		// Check collision between player and enemy
		else if (collision(playerRect, enemyRectangle)) {
			player.playerReset();
			enemy.resetEnemy();
			sc.resetCoin();
			enemyScore.resetCoin();
			playerSpeed = 10;
		}

		// Else no collisions
		else {
			coin.drawCoin(g, coin.getX(), coin.getY());
			g.draw(powerUpRectangle);
			pup.setType();
		}

		// Reset Player out of bounds
		if (outOfBounds(player.getX(), player.getY())) {
			player.playerReset();
			sc.resetCoin();
			enemyScore.resetCoin();
			enemy.resetEnemy();

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

	// ----- Utilities for class -----
	// -------------------------------------------------------------

	// Play sound method
	public static void playSound(String url) {

		try {
			// Open an audio input stream.
			File soundFile = new File(url); // you could also get the sound file
											// with an URL

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

	// Detect collision between rectangles
	public boolean collision(Rectangle r, Rectangle q) {

		if (r.intersects(q)) {
			return true;
		} else
			return false;
	}

	// Detect if player out of bounds
	public boolean outOfBounds(int x, int y) {

		if (x < 0 || x > WIDTH - player.getPlayerWidth(player.getDirection()) || y < 0
				|| y > HEIGHT - player.getPlayerHeight(player.getDirection())) {
			return true;
		}
		return false;
	}

	// Generate random numbers
	public float randFloat(float Min, float Max) {
		return Min + (float) (Math.random() * (Max - Min + 1));
	}

	// Main method
	public static void main(String[] args) {
		Arcadia.display(new Arcadia(new Game[] { new Tronh_Game(), new IntroGame(), new BasicGame() }));
	}

}// End Tronh class