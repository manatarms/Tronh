import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

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

		if (getType().equals("Speed Up")) {
			g.setColor(Color.BLUE);
			String speed = "Spd ++";
			Font speedFont = new Font("Futura", Font.PLAIN, 15);
			g.setFont(speedFont);
			g.drawString(speed, x, y);
		}

		if (getType().equals("Slow Down")) {
			g.setColor(Color.BLUE);
			String slow = "Spd --";
			Font slowFont = new Font("Futura", Font.PLAIN, 15);
			g.setFont(slowFont);
			g.drawString(slow, x, y);
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
