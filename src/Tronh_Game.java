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

	Image banner, background;
    static Font customFont;
	boolean first = true;
	boolean gotcoin = false;
	int coinTotal;
	int highScore;
	int maxCount = 1;
	int trigger = 100;
	int timecounter = 0;
	int powerCount = 0;

	int enemySpeed = 5;
	int playerSpeed = 10;
	int bulletSpeed = 30;
	String enemyDirection = "UP";
	String collectSound = "src/sounds/collect.wav";
	String collideSound = "src/sounds/collide.wav";
	String powerupSound = "src/sounds/powerup.wav";
	String enemycoinSound = "src/sounds/enemycoin.wav";
	static String mainSound = "src/sounds/main.wav";
	boolean maintrackPlayed = false;
	
	PowerUp powerUp = new PowerUp(WIDTH, HEIGHT);
	boolean isForceField;
	String prevType;

	Coin coin = new Coin(WIDTH, HEIGHT);
	Score sc = new Score();
	Score enemyScore = new Score();
	Enemy enemy = new Enemy();
	Player player = new Player();
	Shoot gun = new Shoot();
	Level level = new Level();

	public Tronh_Game() {
		try {
			banner = ImageIO.read(Tronh_Game.class.getResource("images/tronh_banner.png"));
			background = ImageIO.read(Tronh_Game.class.getResource("images/tronh_background.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//register a new font
		try {
			 customFont = Font.createFont(Font.TRUETYPE_FONT, Tronh_Game.class.getResourceAsStream("fonts/seed.ttf")).deriveFont(12f);
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Tronh_Game.class.getResourceAsStream("fonts/seed.ttf")));
		   System.out.println(ge.getAvailableFontFamilyNames());
		} catch (IOException|FontFormatException e) {
		     e.printStackTrace();
		}
	}

	@Override
	public void tick(Graphics2D g, Input p1, Input p2, Sound s) {
		//Play game sound
		if(!maintrackPlayed){
		playSound(mainSound,true);
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

		// Rendering graphics
		g.drawImage(background, 0, 0, null);
		player.drawPlayer(g);
		enemy.drawEnemy(g, enemy.getX(), enemy.getY(), enemy.getDirection());

		// Checking if the player is active.
		player.checkPressed(p1);

		if (player.canRun) {
			// generate score
			sc.drawScore(g, 20, 30, "Your score: ");
<<<<<<< HEAD

			// check if gun is fired
=======
			enemyScore.drawScore(g, 830, 30, "Enemy score: ");
			//Show which level it is
			level.drawLevel(g, 450, 30);
			//check score and run new level
			level.levelInitiate(sc.getNumCoins(), sc);
			//check if gun is fired
>>>>>>> 90ce0fbc1b8272ce7bee65ad8910424f73e6c348
			gun.checkTrigger(p1);
			
			// move player and enemy
			player.Move(player.getX(), player.getY(), playerSpeed);
			
			enemy.moveEnemy(coinX, coinY, enemySpeed);

			if (timecounter == 100) {
				timecounter = 0;
				playerSpeed = 10;
				enemySpeed = 5;
				isForceField = false;
				System.out.println();
			}
			timecounter++;

			if (isForceField == true && prevType != null) {
				powerUp.drawForceField(g, player.getX() - (player.getPlayerHeight(player.direction) / 2 - 10),
						player.getY() - (player.getPlayerWidth(player.direction) / 2 - 10), prevType);
			}
		}

		// fires gun if triggered
		// also checks if enemy was hit
		if (gun.shootStatus) {
			gun.starter(player.getX(), player.getY(), player.getDirection());
			gun.fire(bulletSpeed);
			gun.drawBullet(g);
<<<<<<< HEAD

=======
			if(gun.hitCheck(enemy))
			{
				enemySpeed = 0;
				timecounter = 0;
>>>>>>> 90ce0fbc1b8272ce7bee65ad8910424f73e6c348
			}
		}

		// Check collisions initialization
		playerRect = new Rectangle(player.getX(), player.getY(), player.getPlayerWidth(player.getDirection()),
				player.getPlayerHeight(player.getDirection()));
		enemyRectangle = new Rectangle(enemy.getX(), enemy.getY(), enemy.getEnemyWidth(enemy.getDirection()),
				enemy.getEnemyHeight(enemy.getDirection()));

		coinRectangle = new Rectangle(coinX, coinY, 20, 20);
		Rectangle powerUpRectangle = new Rectangle(powerUp.getX(), powerUp.getY(), 60, 60);

		// g.draw(playerRect);
		// g.draw(enemyRectangle);
		// g.draw(coinRectangle);
		g.draw(powerUpRectangle);

		// Check collisions between player and enemy

		if (collision(playerRect, coinRectangle)) {
			coin = new Coin(WIDTH, HEIGHT);
			coin.drawCoin(g, coin.getX(), coin.getY());
			sc.addCoin();
			sc.saveScore();
			powerCount += 1;
			playSound(collectSound,false);
		}
		// Check collisions between enemy and coin
		else if (collision(enemyRectangle, coinRectangle)) {
			coin = new Coin(WIDTH, HEIGHT);
			coin.drawCoin(g, coin.getX(), coin.getY());
			enemyScore.addCoin();
			enemyScore.saveScore();
			playSound(enemycoinSound,false);
		}

		// Speed up/ slow down based on collision
		if (collision(playerRect, powerUpRectangle)) {
<<<<<<< HEAD

			if ((powerCount % 5 == 0 && powerCount != 0) || powerCount >= 5) {

				if (powerUp.getType().equals("Speed Up") && isForceField == false) {
=======
		

>>>>>>> 90ce0fbc1b8272ce7bee65ad8910424f73e6c348
					playerSpeed = powerUp.SpeedUp(playerSpeed);
					isForceField = false;
					powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
				}
				if (powerUp.getType().equals("Slow Down") && isForceField == false) {
					enemySpeed = powerUp.SlowDown(enemySpeed);
					isForceField = false;
					powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
				}
				if (powerUp.getType() == "Force Field") {
					powerUp.drawPowerUp(g, player.getX() - (player.getPlayerHeight(player.direction) / 2 - 10),
							player.getY() - (player.getPlayerWidth(player.direction) / 2 - 10));
					isForceField = true;
				}

				powerUp.currDrawn = true;
				prevType = powerUp.getType();
				powerUp.setType();
				powerUp = new PowerUp(WIDTH, HEIGHT);
				timecounter = 0;
				powerCount = 0;
				playSound(powerupSound,false);
			}

		}

		// Check collision between player and enemy
		else if (collision(playerRect, enemyRectangle)) {
<<<<<<< HEAD
			if (isForceField == true) {
				enemy.resetEnemy();
			} else {
				player.playerReset();
				enemy.resetEnemy();
				sc.resetCoin();
				enemyScore.resetCoin();
				playerSpeed = 10;
				enemySpeed = 5;
				timecounter = 0;
				powerCount = 0;
			}
=======

>>>>>>> 90ce0fbc1b8272ce7bee65ad8910424f73e6c348
		}

		// Else no collisions
		else {
			coin.drawCoin(g, coin.getX(), coin.getY());
			if ((powerCount % 1 == 0 && powerCount != 0) || powerCount >= 1) {
				powerUp.drawPowerUp(g, powerUp.getX(), powerUp.getY());
			} else if (powerUp.getType() == "Force Field" && powerUp.currDrawn == true) {
				powerUp.drawPowerUp(g, player.getX() - (player.getPlayerHeight(player.direction) / 2 - 10),
						player.getY() - (player.getPlayerWidth(player.direction) / 2 - 10));
				System.out.println(powerUp.getType());
			}
		}

		// Reset Player out of bounds
		if (outOfBounds(player.getX(), player.getY())) {
			player.playerReset();
			sc.resetCoin();
			enemyScore.resetCoin();
			enemy.resetEnemy();
			playerSpeed = 10;
			enemySpeed = 5;
			timecounter = 0;
			powerCount = 0;
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
	public static void playSound(String url,Boolean loop) {

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
			if(loop){
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

	// Detect collision between rectangles
	public boolean collision(Rectangle r, Rectangle q) {

		if (r.intersects(q)) {
			return true;
		}
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