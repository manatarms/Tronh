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
	boolean canRun;
	int lives;
	
	Image player_U, player_D, player_R, player_L;

	Player() {
		currentx = 20;
		currenty = 20;
		direction = "DOWN";
		lives = 5;
		canRun = false;

		player_U = null;
		player_D = null;
		player_L = null;
		player_R = null;

	}

	public void Move(int xh, int yh, int v) {
		if (direction.equals("RIGHT") && canRun) {
			xh += v;
			currentx = xh;

		} else if (direction.equals("LEFT") && canRun) {
			xh -= v;
			currentx = xh;

		} else if (direction.equals("UP") && canRun) {
			yh -= v;
			currenty = yh;

		} else if (direction.equals("DOWN") && canRun) {
			yh += v;
			currenty = yh;
		}

	}

	public int getX() {
		return currentx;
	}

	public int getY() {
		return currenty;
	}

	public boolean getCanRun() {
		return canRun;
	}

	public void playerReset() {
		canRun = false;
		currentx = 20;
		currenty = 20;
		direction = "DOWN";

	}

	public void setX(int newX) {
		currentx = newX;
	}

	public void setY(int newY) {
		currenty = newY;
	}

	public int getPlayerHeight(String dir) {
		if (dir.equals("up") || dir.equals("UP") || dir.equals("down") || dir.equals("DOWN")) {
			return player_U.getHeight(null);
		} else if (dir.equals("left") || dir.equals("LEFT") || dir.equals("RIGHT") || dir.equals("right")) {
			return player_L.getHeight(null);
		} else {
			return 0;
		}
	}

	public int getPlayerWidth(String dir) {
		if (dir.equals("up") || dir.equals("UP") || dir.equals("down") || dir.equals("DOWN")) {
			return player_U.getWidth(null);
		} else if (dir.equals("left") || dir.equals("LEFT") || dir.equals("RIGHT") || dir.equals("right")) {
			return player_L.getWidth(null);
		} else {
			return 0;
		}
	}

	public void checkPressed(Input pl1,boolean stopped) {
		if (pl1.pressed(Button.R)) {
			direction = "RIGHT";
			canRun = true;
		} else if (pl1.pressed(Button.L)&&!stopped) {
			direction = "LEFT";
			canRun = true;
		} else if (pl1.pressed(Button.U)&&!stopped) {
			direction = "UP";
			canRun = true;
		} else if (pl1.pressed(Button.D)) {
			direction = "DOWN";
			canRun = true;
		} else if (pl1.pressed(Button.A)) {

		}
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String dir) {
		if (dir.equals("up") || dir.equals("UP")) {
			direction = "UP";
		} else if (dir.equals("down") || dir.equals("DOWN")) {
			direction = "DOWN";
		} else if (dir.equals("left") || dir.equals("LEFT")) {
			direction = "LEFT";
		} else if (dir.equals("right") || dir.equals("RIGHT")) {
			direction = "RIGHT";
		}

	}

	public void drawPlayer(Graphics2D gh) {

		try {
			// player_U = ImageIO.read(")
			player_U = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_U.png"));
			player_D = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_D.png"));
			player_L = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_L.png"));
			player_R = ImageIO.read(Tronh_Game.class.getResource("images/player1_test_R.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (direction == "UP") {
			gh.drawImage(player_U, getX(), getY(), null);
		} else if (direction == "DOWN") {
			gh.drawImage(player_D, getX(), getY(), null);
		} else if (direction == "LEFT") {
			gh.drawImage(player_L, getX(), getY(), null);
		} else if (direction == "RIGHT") {
			gh.drawImage(player_R, getX(), getY(), null);
		}
	}
	
	public void drawPlayerLives(Graphics2D gh) {

		Image player_life = null;
		try {
		
			player_life = ImageIO.read(Tronh_Game.class.getResource("images/heart.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	      for(int i = lives;i>=1;i--){
			gh.drawImage(player_life,(i*20), 60, null);
	      }
	}
}