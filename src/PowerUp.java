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
	int currRand;

	// PowerUP constructor
	public PowerUp(int WIDTH, int HEIGHT) {
		setType();
		setX(WIDTH);
		setY(HEIGHT);
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
	public void ForceField (Graphics2D g, int x, int y) {
		g.drawOval(x, y, 70, 70);
	}

	// Renders PowerUp images
	public void drawPowerUp(Graphics2D g, int x, int y) {

		currDrawn = true;
		Image fastah = null, slowah = null;

		try {
			fastah = ImageIO.read(Tronh_Game.class.getResource("images/fastah.png"));
			slowah = ImageIO.read(Tronh_Game.class.getResource("images/slowah.png"));
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
			g.setColor(Color.green);
			g.drawOval(x, y, 70, 70);
		}
	}
	
	public void drawForceField(Graphics2D g, int x, int y, String s) {
		currDrawn = true;
		if (s.equals("Force Field")) {
			g.setColor(Color.green);
			g.drawOval(x, y, 70, 70);
		}
		else{
			return;
		}
	}

	// Randomly creates a SpeedUp or SlowDown
	public void setType() {
		//int rand = (int) (Math.random() * 3) + 1;
		int rand = (int)Tronh_Game.randFloat(1, 3);
		
		if (currRand == 3 && rand == 3){
		 rand = (int)Tronh_Game.randFloat(1, 2);
		}
		
		//int rand = 3;
		if (rand == 1) {
			type = "Speed Up";
		}
		if (rand == 2) {
			type = "Slow Down";
		}
		if (rand == 3) {
			type = "Force Field";
		}
		currRand = rand;

	}

	public void drawTimer(Graphics2D g, int x, int y, int timeLeft)
	{
		g.setColor(Color.WHITE);
		String s = "" + (timeLeft/30 + 1);
		g.setFont(Tronh_Game.customFont);
		g.drawString(s, x, y);
	}
	
	
	// Call for type
	public String getType() {
		return type;
	}

}