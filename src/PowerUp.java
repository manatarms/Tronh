import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerUp {
	private int xpos;
	private int ypos;
	private String type;
	boolean currDrawn;
	String lockStatus;
	int currRand;
	int rand = 1;
	String prevType;

	// PowerUP constructor
	public PowerUp(int WIDTH, int HEIGHT) {
		setType();
		setX(WIDTH);
		setY(HEIGHT);
		lockStatus = "locked";
	}

	// Set x-pos of PowerUp
	public void setX(int WIDTH) {
		int minX = 50, maxX = (WIDTH - 10) - 50;
		xpos = minX + (int) (Math.random() * ((maxX - minX) + 1));
	}

	// Set y-pos of PowerUp
	public void setY(int HEIGHT) {
		int minY = 50, maxY = (HEIGHT - 10) - 50;
		ypos = minY + (int) (Math.random() * ((maxY - minY) + 1));
	}

	// Method to call for x-pos of PowerUp
	public int getX() {
		return xpos;
	}

	// Method to call for y-pos of PowerUp
	public int getY() {
		return ypos;
	}

	// SpeedUp properties
	public int SpeedUp(int velocity) {
		velocity = 15;
		return velocity;
	}

	// SlowDown properties
	public int SlowDown(int velocity) {
		velocity = 2;
		if (velocity <= 0)
			velocity = 1;
		return velocity;
	}

	// Renders PowerUp images
	public void drawPowerUp(Graphics2D g, int x, int y) {

		currDrawn = true;
		Image fastah = null, slowah = null, forceFieldIcon = null, coinFieldIcon = null, lifeIcon = null;

		try {
			fastah = ImageIO.read(Tronh_Game.class.getResource("images/fastah.png"));
			slowah = ImageIO.read(Tronh_Game.class.getResource("images/slowah.png"));
			forceFieldIcon = ImageIO.read(Tronh_Game.class.getResource("images/ForceFieldIcon.png"));
			coinFieldIcon = ImageIO.read(Tronh_Game.class.getResource("images/CoinFieldIcon.png"));
			lifeIcon = ImageIO.read(Tronh_Game.class.getResource("images/heartIcon.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Draws images depending on what type is
		if (getType().equals("Speed Up")) {
			g.drawImage(fastah, x, y, null);
		}

		if (getType().equals("Slow Down")) {
			g.drawImage(slowah, x, y, null);
		}

		if (getType().equals("Force Field")) {
			g.drawImage(forceFieldIcon, x, y, null);
		}
		
		if (getType().equals("Coin Field")) {
			g.drawImage(coinFieldIcon, x, y, null);
		}
		
		if (getType().equals("Life")) {
			g.drawImage(lifeIcon, x, y, null);
		}
	}

	public void drawField(Graphics2D g, int x, int y, String s) {

		currDrawn = true;
		Image forceField = null;
		Image coinField = null;

		try {
			forceField = ImageIO.read(Tronh_Game.class.getResource("images/ForceField.png"));
			coinField = ImageIO.read(Tronh_Game.class.getResource("images/CoinField.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (s.equals("Force Field")) {
			g.drawImage(forceField, x, y, null);
		} else if (s.equals("Coin Field")) {
			g.drawImage(coinField, x, y, null);
		} else {
			return;
		}
	}

	// Randomly creates a SpeedUp or SlowDown
	public void setType() {

		if (lockStatus == "locked") {
			rand = (int) Tronh_Game.randFloat(1, 2);
		}

		if (lockStatus == "unlock_1") {
			rand = (int) Tronh_Game.randFloat(1, 3);
		}

		if (lockStatus == "unlock_2") {
			rand = (int) Tronh_Game.randFloat(1, 4);
		}

		if (lockStatus == "unlock_3") {
			rand = (int) Tronh_Game.randFloat(1, 5);
		}
		
		if (rand == 1) {
			type = "Speed Up";
		}
		if (rand == 2) {
			type = "Slow Down";
		}
		if (rand == 3) {
			type = "Force Field";
		}
		if (rand == 4) {
			type = "Coin Field";
		}
		if (rand == 5) {
			type = "Life";
		}
		currRand = rand;

	}

	public static void drawTimer(Graphics2D g, int x, int y, int timeLeft, String type) {
		if (type.equals("Life")) {
			return;
		}
		g.setColor(Color.WHITE);
		String s = type + " : " + (timeLeft / 30 + 1);
		g.setFont(Tronh_Game.customFont);
		g.drawString(s, x, y);
	}

	// Call for type
	public String getType() {
		return type;
	}

	public String prevType() {
		return prevType;
	}

	public void setPrevType(String s) {
		prevType = s;
	}

}