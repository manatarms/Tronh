import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

import arcadia.Button;
import arcadia.Input;

public class Shoot {

	int bulletX;
	int bulletY;
	int freezeTime;
	String bikeDir;

	boolean shootStatus;
	boolean starter;
	boolean isFrozen;

	boolean impact;
	Image bullet;

	Shoot() {
		shootStatus = false;
		starter = true;
		bikeDir = "DOWN";
		impact = false;
		freezeTime = 150;
		isFrozen  = false;
	}
	
	void freezeReset()
	{
		freezeTime = 150;
		isFrozen  = false;
	}
	
	void frozen()
	{
		freezeTime = 150;
		isFrozen = true;
	}

	// draws a white circular bullet of radius 10.0
	void drawBullet(Graphics2D gh) {
		try {
			bullet = ImageIO.read(Tronh_Game.class.getResource("images/bullet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gh.drawImage(bullet, bulletX, bulletY, null);
	}

	// gets the position from where the shot is fired
	void starter(int x, int y, String dir) {
		if (starter) {
			bulletX = x;
			bulletY = y;
			bikeDir = dir;
			starter = false;
		}
	}

	// bullet movement and boundary collision check
	void fire(int speed) {

		if (bikeDir == "RIGHT") {
			bulletX += speed;
		} else if (bikeDir == "LEFT") {
			bulletX -= speed;
		} else if (bikeDir == "UP") {
			bulletY -= speed;
		} else if (bikeDir == "DOWN") {
			bulletY += speed;
		}

		if (bulletX > 1024 || bulletX < 0 || bulletY > 576 || bulletY < 0) {
			shootStatus = false;
			starter = true;
		}

	}

	// checks if you pulled the trigger ('~')
	void checkTrigger(Input pl1) {
		if (pl1.pressed(Button.A)) {
			shootStatus = true;
		}
	}

	// checks bullet collision with the enemy
	// must be made accurate..
	boolean hitCheck(Enemy e) {
		if (((bulletX + 10 >= e.getX()) && (bulletX <= e.getX() + e.getEnemyWidth(e.getDirection())))
				&& ((bulletY + 10 >= e.getY()) && (bulletY <= e.getY() + e.getEnemyHeight(e.getDirection())))) {
			starter = true;
			shootStatus = false;
			return true;
		}
		return false;
	}

}
