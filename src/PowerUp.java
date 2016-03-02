import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerUp {
	private int xpos;
	private int ypos;
	private String type;

	public PowerUp(int WIDTH, int HEIGHT) {
		setType();
		setX(WIDTH, HEIGHT);
		setY(WIDTH, HEIGHT);
	}

	public void setX(int WIDTH, int HEIGHT) {
		int minX = 50, maxX = (WIDTH - 10) - 50;
		xpos = minX + (int) (Math.random() * ((maxX - minX) + 1));
	}

	public void setY(int WIDTH, int HEIGHT) {
		int minY = 50, maxY = (HEIGHT - 10) - 50;
		ypos = minY + (int) (Math.random() * ((maxY - minY) + 1));
	}

	public int getX() {
		return xpos;
	}

	public int getY() {
		return ypos;
	}

	public int SpeedUp(int velocity) {
		velocity = 15;
		return velocity;
	}

	public int SlowDown(int velocity) {
		velocity = 2;
		if (velocity <= 0)
			velocity = 1;
		return velocity;
	}

	public void drawPowerUp(Graphics2D g, int x, int y) {

		Image fastah = null, slowah = null;

		try {
			fastah = ImageIO.read(Tronh_Game.class.getResource("images/fastah.png"));
			slowah = ImageIO.read(Tronh_Game.class.getResource("images/slowah.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (getType().equals("Speed Up")) {
			g.drawImage(fastah, x, y, null);
		}

		if (getType().equals("Slow Down")) {
			g.drawImage(slowah, x, y, null);
		}
	}

	public void setType() {
		int rand = (int) (Math.random() * 2) + 1;
		if (rand == 1)
			type = "Speed Up";
		else
			type = "Slow Down";
	}

	public String getType() {
		return type;
	}

}
