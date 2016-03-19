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
	
	void drawBullet(Graphics2D gh)
	{
		gh.setColor(Color.WHITE);
		gh.fillOval(bulletX, bulletY, 10, 10);
	}
	
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
	
	void checkTrigger(Input pl1)
	{
		if (pl1.pressed(Button.A)) {
			shootStatus = true;
		}
	}
	
	boolean hitCheck(int enX, int enY)
	{
		if(((bulletX - 10 > enX) && (bulletX < enX + 85)) && ((bulletY - 10 > enY) && (bulletY < enY + 85)))
		{
			starter = true;
			shootStatus = false;
			return true;
		}
		return false;
	}
	
}
