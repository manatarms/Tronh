import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

import arcadia.Arcadia;
import arcadia.Game;
import arcadia.Input;
import arcadia.Sound;
import basicGame.BasicGame;
import intro.IntroGame;

public class Tronh_Game extends Game {
	boolean first = true;
	boolean gotcoin = false;
	boolean pickUpDelay = true;
	boolean hasPower = false;
	boolean isCoinField;
	boolean maintrackPlayed = false;
	boolean stopped = true;
	boolean gameOver = false;
	boolean isForceField;
	Image banner, background;
	int coinTotal;
	int highScore;
	int maxCount = 1;
	int trigger = 100;
	int timeLeft = 0;
	int powerCount = 0;
	int enemySpeed = 5;
	int playerSpeed = 10;
	int bulletSpeed = 30;
	int coinFieldExtension = 0;
	int coinFieldAdjustment = 0;
	String lockMode = "locked";
	String prevType;
	String enemyDirection = "UP";
	String collectSound = "src/sounds/collect.wav";
	String collideSound = "src/sounds/collide.wav";
	String powerupSound = "src/sounds/powerup.wav";
	String enemycoinSound = "src/sounds/enemycollect.wav";
	String shieldSound = "src/sounds/shield.wav";
	String levelSound = "src/sounds/level.wav";
	String gunSound = "src/sounds/gun.wav";
	String freezeSound = "src/sounds/freeze.wav";
	static String mainSound = "src/sounds/main.wav";
	static Font customFont;
	static Font largeCustomFont;
	
	// Calling for outside classes
	PowerUp powerUp = new PowerUp(WIDTH, HEIGHT);
	Coin coin = new Coin(WIDTH, HEIGHT);
	Score playerScore = new Score();
	Score enemyScore = new Score();
	Enemy enemy = new Enemy();
	Player player = new Player();
	Shoot gun = new Shoot();
	Level level = new Level();
	

	/**
	 * Tronh - The Game
	 */
	public Tronh_Game() {

		// Gets banner and background pictures
		try {
			banner = ImageIO.read(Tronh_Game.class.getResource("images/tronh_banner.png"));
			background = ImageIO.read(Tronh_Game.class.getResource("images/tronh_background.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Registering font style
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, Tronh_Game.class.getResourceAsStream("fonts/seed.ttf"))
					.deriveFont(12f);
			largeCustomFont =Font.createFont(Font.TRUETYPE_FONT, Tronh_Game.class.getResourceAsStream("fonts/seed.ttf"))
					.deriveFont(48f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(
					Font.createFont(Font.TRUETYPE_FONT, Tronh_Game.class.getResourceAsStream("fonts/seed.ttf")));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tronh game physics
	 */
	@Override
	public void tick(Graphics2D g, Input p1, Input p2, Sound s) {

		// Play game sound
		if (!maintrackPlayed) {
			playSound(mainSound, true);
			maintrackPlayed = true;
		}
		
		
		
		// Setting the graphics objects
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Initializations in tick
		int coinX = coin.getX(), coinY = coin.getY();
		Rectangle playerRect = null;
		Rectangle enemyRectangle = null;
		Rectangle coinRectangle = null;
		Rectangle coinFieldRect = null;
		Rectangle powerUpRectangle = null;

		// Rendering graphics
		g.drawImage(background, 0, 0, null);

		// Rendering player
		player.drawPlayer(g);

		// Rendering enemy
		enemy.drawEnemy(g, enemy.getX(), enemy.getY(), enemy.getDirection());
		
		// Rendering Game Over 
		if(gameOver){
		g.setColor(new Color(255, 0, 0));
		g.setFont(largeCustomFont);
		g.drawString("GAME OVER", 285, 300);
		}
		
		// Checking if the player is active.
		player.checkPressed(p1, stopped);
		
		// Code that runs while the Player can move
		if (player.canRun) {
			
			// Activate all actions
			stopped = false;
			
			// Remove Game Over
			gameOver = false;
			
			// Generate score
			playerScore.drawScore(g, 20, 30, "Your score: ");

			// Generates enemy score
			enemyScore.drawScore(g, 830, 30, "Enemy score: ");

			// Show which level it is
			level.drawLevel(g, 450, 30);
			//level.died = false;

			if (prevType != null && timeLeft != 150 && (timeLeft % 5 == 0 || timeLeft % 4 == 0)) {
				if (timeLeft > 100) {
					g.drawString(prevType + " activated", 400, 300);
				}
				if (level.currentLevel == 5 && timeLeft < 90) {
					g.drawString("Force Field Unlocked!", 400, 300);
					playSound(levelSound, false);
				}
				if (level.currentLevel == 10 && timeLeft < 90) {
					g.drawString("Coin Field Unlocked!", 400, 300);
					playSound(levelSound, false);
				}

			}

			// Send level Status
			powerUp.lockStatus = lockMode;

			// Check score and run new level
			pickUpDelay = level.levelUp(playerScore.getNumCoins(), pickUpDelay, powerUp, level.died);

			// Save level status
			lockMode = powerUp.lockStatus;

			// Check if gun is fired
			gun.checkTrigger(p1);

			// Moves player
			player.Move(player.getX(), player.getY(), playerSpeed);
			
			// Moves enemy player
			enemy.moveEnemy(coinX, coinY, enemySpeed);

			
			
			// Renders coinField when it is active (visually)
			if (isForceField == true) {
				powerUp.drawField(g, player.getX() - player.getPlayerHeight(player.direction) / 2 + 5,
						player.getY() - player.getPlayerWidth(player.direction) / 2 + 5, "Force Field");
			}

			// Renders coinField when it is active (visually)
			if (isCoinField == true) {
				powerUp.drawField(g, player.getX() - player.getPlayerHeight(player.direction) / 2 - 30,
						player.getY() - player.getPlayerWidth(player.direction) / 2 - 30, "Coin Field");
			}

			// Resets power conditions when timer hits 150
			if (timeLeft == 0) {
				timeLeft = 150;
				playerSpeed = 10;
				enemySpeed = 5;
				isForceField = false;
				isCoinField = false;
				hasPower = false;
				coinFieldAdjustment = 0;
				coinFieldExtension = 0;

			}

			// Checks if Power is active
			if (hasPower) {
				timeLeft--;
				powerUp.drawTimer(g, 100, 100, timeLeft, prevType);
			}
			
			//Draw player lives
			player.drawPlayerLives(g);

		}

		// Fires gun if triggered
		if (gun.shootStatus) {
			gun.starter(player.getX(), player.getY(), player.getDirection());
			gun.fire(bulletSpeed);
			gun.drawBullet(g);
			playSound(gunSound, false);
			// Checks if enemy was hit
			if (gun.hitCheck(enemy)) {
				enemySpeed = 0;
				timeLeft = 150;
				playSound(freezeSound, false);
			}
		}
		

		// ------------- Collision methods -------------------

		// Rectangles for collisions
		playerRect = new Rectangle(player.getX(), player.getY(), player.getPlayerWidth(player.getDirection()),
				player.getPlayerHeight(player.getDirection()));
		enemyRectangle = new Rectangle(enemy.getX(), enemy.getY(), enemy.getEnemyWidth(enemy.getDirection()),
				enemy.getEnemyHeight(enemy.getDirection()));
		coinFieldRect = new Rectangle(player.getX() - coinFieldAdjustment, player.getY() - coinFieldAdjustment,
				player.getPlayerWidth(player.getDirection()) + coinFieldExtension,
				player.getPlayerHeight(player.getDirection()) + coinFieldExtension);
		coinRectangle = new Rectangle(coinX, coinY, 20, 20);
		powerUpRectangle = new Rectangle(powerUp.getX(), powerUp.getY(), 60, 60);

		// DEBUGGING CODE (adds visibility for collision rectangles):
		// g.draw(playerRect);
		// g.draw(enemyRectangle);
		// g.draw(coinRectangle);
		// g.draw(powerUpRectangle);
		// g.draw(coinFieldRect);

		// Check collisions between player and enemy
		if (collision(playerRect, coinRectangle)) {
			coin = new Coin(WIDTH, HEIGHT);
			coin.drawCoin(g, coin.getX(), coin.getY());
			playerScore.addCoin();
			playerScore.saveScore();
			powerCount += 1;
			playSound(collectSound, false);
			level.died = false;
			stopped = true;
		}

		// Checks collisions between coin and PowerUp coinField
		else if (collision(coinFieldRect, coinRectangle) && isCoinField) {
			coin = new Coin(WIDTH, HEIGHT);
			coin.drawCoin(g, coin.getX(), coin.getY());
			playerScore.addCoin();
			playerScore.saveScore();
			powerCount += 1;
			playSound(collectSound, false);
		}

		// Check collisions between enemy and coin
		else if (collision(enemyRectangle, coinRectangle)) {
			coin = new Coin(WIDTH, HEIGHT);
			coin.drawCoin(g, coin.getX(), coin.getY());
			enemyScore.addCoin();
			enemyScore.saveScore();
			playSound(enemycoinSound, false);
		}

		// Check collisions between player and PowerUp
		if (collision(playerRect, powerUpRectangle)) {

			// Save level status
			lockMode = powerUp.lockStatus;

			// Checks to see if Player has collected at least 5 coins to get
			// PowerUp
			if ((powerCount % 5 == 0 && powerCount != 0) || powerCount >= 5) {

				// Speed Up conditions
				if (powerUp.getType().equals("Speed Up") && !isForceField && !isCoinField) {
					playerSpeed = powerUp.SpeedUp(playerSpeed);
					powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
					playSound(powerupSound, false);
				}

				// Slow Down conditions
				if (powerUp.getType().equals("Slow Down") && !isForceField && !isCoinField) {
					enemySpeed = powerUp.SlowDown(enemySpeed);
					powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
					playSound(powerupSound, false);
				}

				// ForceField conditions
				if (powerUp.getType().equals("Force Field")) {
					powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
					isForceField = true;
					playSound(shieldSound, false);
				}

				// CoinField conditions
				if (powerUp.getType().equals("Coin Field")) {
					coinFieldExtension = 100;
					coinFieldAdjustment = 50;
					isCoinField = true;
					powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
					playSound(shieldSound, false);
				}

				// Properties for rendering PowerUps
				powerUp.currDrawn = true;
				hasPower = true;
				prevType = powerUp.getType();
				powerUp.setType();
				powerUp.setPrevType(prevType);
				timeLeft = 150;
				powerCount = 0;
				
			}

			// Draws timer for PowerUp limits
			if (hasPower)
				powerUp.drawTimer(g, 100, 100, timeLeft, prevType);

		}

		// Check collision between player and enemy
		else if (collision(playerRect, enemyRectangle)) {

			if (isForceField == true) {
				enemy.resetEnemy();
				prevType = powerUp.getType();
				powerUp.setType();
				powerUp.setPrevType(prevType);
				isForceField = false;
				hasPower = false;
				playSound(collideSound, false);
			} else {
				player.playerReset();
				enemy.resetEnemy();
				playerScore.resetCoin();
				enemyScore.resetCoin();
				prevType = null;
				powerUp.setType();
				powerUp.setPrevType(prevType);
				isForceField = false;
				isCoinField = false;
				playerSpeed = 10;
				enemySpeed = 5;
				timeLeft = 150;
				powerCount = 0;
				level.died = true;
				stopped = true;
				playSound(collideSound, false);
				hasPower = false;
				if(player.lives == 1){
					playerScore.resetHighScore();
					enemyScore.resetHighScore();
					level.levelReset();
					gameOver = true;
			
				}
				player.lives = (player.lives == 1) ? 5 : player.lives-1;
				
			}

		}

		// No collision case
		else {
			coin.drawCoin(g, coin.getX(), coin.getY());
			if ((powerCount % 5 == 0 && powerCount != 0) || powerCount >= 5) {
				powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
			}
		}

		// Reset Player when out of bounds
		if (outOfBounds(player.getX(), player.getY())) {
			player.playerReset();
			playerScore.resetCoin();
			enemyScore.resetCoin();
			enemy.resetEnemy();
			prevType = null;
			powerUp.setType();
			powerUp.setPrevType(prevType);
			isForceField = false;
			isCoinField = false;
			playerSpeed = 10;
			enemySpeed = 5;
			timeLeft = 150;
			powerCount = 0;
			level.died = true;
			stopped = true;
			playSound(collideSound, false);
			hasPower = false;
			if(player.lives == 1){
				playerScore.resetHighScore();
				enemyScore.resetHighScore();
				level.levelReset();
				gameOver = true;
			}
			player.lives = (player.lives == 1) ? 5 : player.lives-1;
		}
	}

	// ---------------- End of Collision checks -----------------

	/**
	 * Resets game.
	 */
	@Override
	public void reset() {
	}

	/**
	 * Draws banner in Main Menu.
	 */
	@Override
	public Image banner() {
		// Dimensions : 512 x 128
		return banner;
	}

	// -------------- Class Utilities ------------------

	/**
	 * Method for playing sounds.
	 * 
	 * @param url
	 *            location of sound
	 * @param loop
	 *            replays sound
	 */
	public static void playSound(String url, Boolean loop) {

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
			if (loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Detect collision between rectangles
	 * 
	 * @param r
	 *            First object
	 * @param q
	 *            Second object
	 * @return True if objects collide, false otherwise.
	 */
	public boolean collision(Rectangle r, Rectangle q) {

		if (r.intersects(q)) {
			return true;
		}
		return false;
	}

	/**
	 * Detected if Player is out of bounds.
	 * 
	 * @param x
	 *            Player x-position
	 * @param y
	 *            Player y-position
	 * @return True if player is out of bounds, false otherwise.
	 */
	public boolean outOfBounds(int x, int y) {

		if (x < 0 || x > WIDTH - player.getPlayerWidth(player.getDirection()) || y < 0
				|| y > HEIGHT - player.getPlayerHeight(player.getDirection())) {
			return true;
		}
		return false;
	}

	/**
	 * Generates random float number.
	 * 
	 * @param Min
	 *            minimum limit for random number
	 * @param Max
	 *            maximum limit for random number
	 * @return Random number value in between parameter Min and Max.
	 */
	public static float randFloat(float Min, float Max) {
		return Min + (float) (Math.random() * (Max - Min + 1));
	}

	/**
	 * Main method for the game creation.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Arcadia.display(new Arcadia(new Game[] { new Tronh_Game(), new IntroGame(), new BasicGame() }));

	}

}// End Tronh class