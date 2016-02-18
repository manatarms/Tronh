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
	Image banner, background;
	boolean first = true;
	boolean gotcoin = false;
	int enemyTriggerCounter = 1;
	Coin coin = new Coin(WIDTH, HEIGHT);
	Enemy enemy = new Enemy(WIDTH, HEIGHT);
	int enemyX, enemyY;

	public Tronh_Game() {
		try {
			banner = ImageIO.read(Tronh_Game.class.getResource("tronh_banner.png"));
			background = ImageIO.read(Tronh_Game.class.getResource("Background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick(Graphics2D g, Input p1, Input p2, Sound s) {

		//g.setColor(Color.DARK_GRAY);
		
		//Added new background (not yet sized properly)
		g.drawImage(background, 0, 0, null);
		//g.fillRect(0, 0, WIDTH, HEIGHT);

		if (p1.pressed(Button.R)) {
			canRun = true;
			right = true;
			left = false;
			up = false;
			down = false;

		}
		if (p1.pressed(Button.L)) {
			canRun = true;
			right = false;
			left = true;
			up = false;
			down = false;
		}
		if (p1.pressed(Button.U)) {
			canRun = true;
			right = false;
			left = false;
			up = true;
			down = false;
		}
		if (p1.pressed(Button.D)) {
			canRun = true;
			right = false;
			left = false;
			up = false;
			down = true;
		}

		if (canRun && right) {
			velocity = 10;
			x += velocity;
			enemy.drawEnemy(g, (int) x - enemy.currentSpeed(), (int) y);
			enemyX = (int) x - enemy.currentSpeed();
			enemyY = (int) y;

		}

		if (canRun && left) {
			velocity = 10;
			x -= velocity;
			enemy.drawEnemy(g, (int) x + enemy.currentSpeed(), (int) y);
			enemyX = (int) x + enemy.currentSpeed();
			enemyY = (int) y;
		}

		if (canRun && up) {
			velocity = 10;
			y -= velocity;
			enemy.drawEnemy(g, (int) x, (int) y + enemy.currentSpeed());
			enemyX = (int) x;
			enemyY = (int) y + enemy.currentSpeed();
		}

		if (canRun && down) {
			velocity = 10;
			y += velocity;
			enemy.drawEnemy(g, (int) x, (int) y - enemy.currentSpeed());
			enemyX = (int) x;
			enemyY = (int) y - enemy.currentSpeed();
		}

		g.setColor(Color.BLUE);
		g.fillRect((int) x, (int) y, 50, 50);

		int coinX = coin.getX(), coinY = coin.getY();

		// Check collisions
		Rectangle playerRect = new Rectangle((int) x, (int) y, 50, 50);
		Rectangle coinRectangle = new Rectangle((int) coinX, (int) coinY, 20, 20);
		Rectangle enemyRectangle = new Rectangle((int) enemyX, (int) enemyY, 50, 50);

		if (collision(playerRect, coinRectangle)) {
			coin = new Coin(WIDTH, HEIGHT);
			coinX = coin.getX();
			coinY = coin.getY();
			coin.drawCoin(g, coinX, coinY);
			enemy.slowDown(1);
			enemyTriggerCounter = 1;
		} else {
			coin.drawCoin(g, coinX, coinY);
			if (enemyTriggerCounter > 100) {
				enemy.speedUp(1);
			}
			enemyTriggerCounter++;
		}

		if (collision(playerRect, enemyRectangle)) {
			y = 10;
			x = 10;
			enemy.resetEnemy();
			enemyTriggerCounter = 1;
			canRun = false;
		}

		// Reset Player out of bounds
		if (outOfBounds((int) x, (int) y)) {
			// Reset Player method comes here using hard coded for now
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

		if (x < 0 || x > WIDTH || y < 0 || y > HEIGHT) {
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
<<<<<<< HEAD
}//End Tronh class
	


	
	
=======
}// End Tronh class

// Class Coin
final class Coin {
	int w = 20, h = 20;
	private final int Coinx;
	private final int Coiny;

	public Coin(int WIDTH, int HEIGHT) {
		int minX = 1, maxX = WIDTH - w;
		int minY = 1, maxY = HEIGHT - h;
		int randX = minX + (int) (Math.random() * ((maxX - minX) + 1));
		int randY = minY + (int) (Math.random() * ((maxY - minY) + 1));
		this.Coinx = randX;
		this.Coiny = randY;
	}

	public int getX() {
		return Coinx;
	}

	public int getY() {
		return Coiny;
	}

	public void drawCoin(Graphics2D g, int x, int y) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, w, h);
	}

}// End Coin class

// Class Enemy
final class Enemy {
	int w = 50, h = 50;
	private int EnemyX;
	private int EnemyY;
	private int Speed = 100;

	public Enemy(int WIDTH, int HEIGHT) {
		this.EnemyX = -100;
		this.EnemyY = 10;
	}

	public int getX() {
		return EnemyX;
	}

	public int getY() {
		return EnemyY;
	}

	public int currentSpeed() {
		return Speed;
	}

	public void resetEnemy() {
		EnemyX = -100;
		EnemyY = 10;
		Speed = 100;
	}

	public int speedUp(int speedup) {
		Speed = Speed - speedup;
		return Speed - speedup;
	}

	public int slowDown(int slowdown) {
		Speed = Speed + slowdown;
		return Speed + slowdown;
	}

	public void drawEnemy(Graphics2D g, int x, int y) {
		g.setColor(Color.RED);
		g.fillRect(x, y, w, h);
		// g.fillRoundRect(x, y, w, h, 10,10);
	}

}// End Enemy class
>>>>>>> f194f09834cf859218d9567db85387e2bf04d901
