import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;



import arcadia.Button;
import arcadia.Input;

public class Player {
	String direction;
	int currentx;
	int currenty;
	
	Image player_U, player_D, player_R, player_L;
	
	Player()
	{
		currentx = 10;
		currenty = 10;
		
		player_U = null;
		player_D = null;
		player_L = null;
		player_R = null;
		
	}
      //public void Up(Graphics2D g, java.awt.Image image, int velocity, int x, int y){
	 public void Move(int xh, int yh, int v){
		if(direction == "right"){
			  xh += v;
			  currentx = xh;
			 
		}else if(direction == "left"){
			  xh -= v;
			  currentx = xh;
		
		}else if(direction == "up"){
		  yh -= v;
		  currenty =yh;
	
		}else if(direction == "down"){
		  yh += v;
		  currenty =yh;
		}
		
	 }
	 
	 public int getX() {
		return currentx;
	}
	 public int getY() {
			return currenty;
	}
	 
	 public void setX(int newX) {
			currentx = newX;
		}
		 public void setY(int newY) {
				currenty = newY;
		}
		 
	 public int getPlayerHeight(String dir)
	 {
		 if(dir == "up" || dir == "UP" || dir == "down" || dir == "DOWN")
		 {
			 return player_U.getHeight(null);
		 }else if(dir == "left" || dir == "LEFT" || dir == "RIGHT" || dir == "right")
		 {
			 return player_L.getHeight(null);
		 }else{
			 return 0;
		 }
	 }
	 
	 public int getPlayerWidth(String dir)
	 {
		 if(dir == "up" || dir == "UP" || dir == "down" || dir == "DOWN")
		 {
			 return player_U.getWidth(null);
		 }else if(dir == "left" || dir == "LEFT" || dir == "RIGHT" || dir == "right")
		 {
			 return player_L.getWidth(null);
		 }else{
			 return 0;
		 }
	 }
	 
	 
	 
	 public int getWidth(String dir)
	 {
		 return 0;
	 }
	 boolean checkPressed(Input pl1)
	 {
		 if (pl1.pressed(Button.R)) {
				direction = "right";
				return true;
			}else if (pl1.pressed(Button.L)) {
				direction = "left";
				return true;
			}else if (pl1.pressed(Button.U)) {
				direction = "up";
				return true;
			}else if (pl1.pressed(Button.D)) {
				direction = "down";
				return true;
			}
		 return false;
	 }
	 
	 public String getDirection()
	 {
		 return direction;
	 }
	 
	 public void setDirection(String dir)
	 {
		 if(dir == "up" || dir == "UP")
		 {
			 direction = "up";
		 }else if(dir == "down" || dir == "DOWN")
		 {
			 direction = "down";
		 }else if(dir == "left" || dir == "LEFT")
		 {
			 direction = "left";
		 }else if(dir == "right" || dir == "RIGHT")
		 {
			 direction = "right";
		 }
		 
	 }
	 
	 public void drawPlayer(Graphics2D gh, int xh, int yh){
		 Image player_U = null, player_D = null, player_R = null, player_L = null;
		 
			try {
//				player_U = ImageIO.read(")
				player_U = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_U.png"));
				player_D = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_D.png"));
				player_L = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_L.png"));
				player_R = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_R.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(direction == "up"){
				gh.drawImage(player_U,  getX(),  getY(), null);
			}else if(direction == "down"){
				gh.drawImage(player_D,  getX(),  getY(), null);
			}else if(direction == "left"){
				gh.drawImage(player_L,  getX(),  getY(), null);
			}else if(direction == "right"){
				gh.drawImage(player_R,  getX(),  getY(), null);
			}
	 }
}