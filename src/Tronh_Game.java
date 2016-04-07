import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
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
	boolean first = true;
	boolean gotcoin = false;
	boolean pickUpDelay = true;
	boolean hasPower = false;
	boolean isCoinField;
	boolean maintrackPlayed = false;
	boolean isForceField;
	Image banner, background;
	int coinTotal;
	int highScore;
	int maxCount = 1;
	int trigger = 100;
	int timecounter = 0;
	int powerCount = 0;
	int timeLeft = 150;
	int enemySpeed = 5;
	int playerSpeed = 10;
	int bulletSpeed = 30;
	int currRand = 1;
	int coinFieldExtension = 0;
	int coinFieldAdjustment = 0;
	String prevType;
	String enemyDirection = "UP";
	String collectSound = "src/sounds/collect.wav";
	String collideSound = "src/sounds/collide.wav";
	String powerupSound = "src/sounds/powerup.wav";
	String enemycoinSound = "src/sounds/enemycoin.wav";
	static String mainSound = "src/sounds/main.wav";
	static Font customFont;

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

		// Checking if the player is active.
		player.checkPressed(p1);

		// Code that runs while the Player can move
		if (player.canRun) {
			// Generate score
			playerScore.drawScore(g, 20, 30, "Your score: ");

			// Generates enemy score
			enemyScore.drawScore(g, 830, 30, "Enemy score: ");

			// Show which level it is
			level.drawLevel(g, 450, 30);

			// Check score and run new level
			pickUpDelay = level.levelUp(playerScore.getHighScore(), pickUpDelay);

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

			// Resets power conditions when timer hits 100
			if (timecounter == 150) {
				timecounter = 0;
				playerSpeed = 10;
				enemySpeed = 5;
				isForceField = false;
				isCoinField = false;
				coinFieldAdjustment = 0;
				coinFieldExtension = 0;

			}

			// Deactivates power and resets time if time runs out
			if (timeLeft == 0) {
				hasPower = false;
				timeLeft = 150;
			}

			// Increments time
			timecounter++;

			// Checks if Power is active
			if (hasPower) {
				timeLeft--;
				powerUp.drawTimer(g, 100, 100, timeLeft, prevType);
			}

		}

		// Fires gun if triggered
		if (gun.shootStatus) {
			gun.starter(player.getX(), player.getY(), player.getDirection());
			gun.fire(bulletSpeed);
			gun.drawBullet(g);

			// Checks if enemy was hit
			if (gun.hitCheck(enemy)) {
				enemySpeed = 0;
				timecounter = 0;

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

			// Checks to see if Player has collected at least 5 coins to get
			// PowerUp
			if ((powerCount % 5 == 0 && powerCount != 0) || powerCount >= 5) {

				// Speed Up conditions
				if (powerUp.getType().equals("Speed Up") && !isForceField && !isCoinField) {
					playerSpeed = powerUp.SpeedUp(playerSpeed);
					powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
				}

				// Slow Down conditions
				if (powerUp.getType().equals("Slow Down") && !isForceField && !isCoinField) {
					enemySpeed = powerUp.SlowDown(enemySpeed);
					powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
				}

				// ForceField conditions
				if (powerUp.getType().equals("Force Field")) {
					powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
					isForceField = true;
				}

				// CoinField conditions
				if (powerUp.getType().equals("Coin Field")) {
					coinFieldExtension = 100;
					coinFieldAdjustment = 50;
					isCoinField = true;
					powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
				}

				// Properties for rendering PowerUps
				powerUp.currDrawn = true;
				hasPower = true;
				prevType = powerUp.getType();
				powerUp.setType();
				powerUp.setPrevType(prevType);
				powerUp = new PowerUp(WIDTH, HEIGHT);
				timecounter = 0;
				powerCount = 0;
				playSound(powerupSound, false);
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
				prevType = powerUp.getType();
				powerUp.setType();
				powerUp.setPrevType(prevType);
				isForceField = false;
				isCoinField = false;
				playerSpeed = 10;
				enemySpeed = 5;
				timecounter = 0;
				powerCount = 0;
				level.levelReset();
				playSound(collideSound, false);
				hasPower = false;
				timeLeft = 150;
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
			prevType = powerUp.getType();
			powerUp.setType();
			powerUp.setPrevType(prevType);
			isForceField = false;
			isCoinField = false;
			playerSpeed = 10;
			enemySpeed = 5;
			timecounter = 0;
			powerCount = 0;
			level.levelReset();
			playSound(collideSound, false);
			hasPower = false;
			timeLeft = 150;
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