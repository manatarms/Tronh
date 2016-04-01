import java.awt.Color;
import java.awt.Graphics2D;

import arcadia.Button;
import arcadia.Input;

public class Shoot {
	
	int bulletX;
	int bulletY;
	String bikeDir;
	
	boolean shootStatus;
	boolean starter;
	
	boolean impact;
	
	Shoot()
	{
		shootStatus = false;
		starter = true;
		bikeDir = "DOWN";
		impact = false;
	}
	
	//draws a white circular bullet of radius 10.0
	void drawBullet(Graphics2D gh)
	{
		gh.setColor(Color.WHITE);
		gh.fillOval(bulletX, bulletY, 10, 10);
	}
	
	//gets the position from where the shot is fired
	void starter(int x, int y, String dir)
	{
		if(starter)
		{
			bulletX = x;
			bulletY = y;
			bikeDir = dir;
			starter = false;
		}
	}
	
	//bullet movement and boundary collision check
	void fire(int speed)
	{
		
		if(bikeDir == "RIGHT")
		{
			bulletX += speed;
		} else if(bikeDir == "LEFT")
		{
			bulletX -= speed;
		} else if(bikeDir == "UP")
		{
			bulletY -= speed;
		} else if(bikeDir == "DOWN")
		{
			bulletY += speed;
		}
		
		if(bulletX > 1024 || bulletX < 0 || bulletY > 576 || bulletY < 0)
		{
			shootStatus = false;
			starter = true;
		}
		
	}
	
	//checks if you pulled the trigger ('~')
	void checkTrigger(Input pl1)
	{
		if (pl1.pressed(Button.A)) {
			shootStatus = true;
		}
	}
	
	//checks bullet collision with the enemy
	//must be made accurate..
	boolean hitCheck(Enemy e)
	{
		if(((bulletX + 10 >= e.getX()) && (bulletX <= e.getX() + e.getEnemyWidth(e.getDirection())))
				&& ((bulletY + 10 >= e.getY()) && (bulletY <= e.getY() + e.getEnemyHeight(e.getDirection()))))
		{
			starter = true;
			shootStatus = false;
			return true;
		}
		return false;
	}
	
}
