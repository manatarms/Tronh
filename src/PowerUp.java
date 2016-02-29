import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class PowerUp
{
	public PowerUp(int WIDTH, int HEIGHT)
	{
		setType();
		setX(WIDTH, HEIGHT);
		setY(WIDTH, HEIGHT);
	}
	
	public void setX(int WIDTH, int HEIGHT)
	{
		int minX = 50, maxX = (WIDTH - 10) - 20;
		xpos = minX + (int) (Math.random() * ((maxX - minX) + 1));
	}
	
	public void setY(int WIDTH, int HEIGHT)
	{
		int minY = 50, maxY = (HEIGHT - 10) - 20;
		ypos = minY + (int) (Math.random() * ((maxY - minY) + 1));
	}
	
	public int getX()
	{
		return xpos;
	}
	
	public int getY()
	{
		return ypos;
	}
	
	public float SpeedUp (float velocity)
	{
		velocity += 5;
		return velocity;
	}
	
	public float SlowDown (float velocity)
	{
		velocity -= 5;
		if(velocity <= 0)
			velocity=1;
		return velocity;
	}
	
	public void drawSpeedUp(Graphics2D g, int x, int y)
	{
		g.setColor(Color.BLUE);
		String s = "Spd + 2";
		Font f = new Font("Futura", Font.PLAIN, 15);
		g.setFont(f);
		g.drawString(s, x, y);
	}
	
	public void drawSlowDown(Graphics2D g, int x, int y)
	{
		g.setColor(Color.BLUE);
		String s = "Spd - 2";
		Font f = new Font("Futura", Font.PLAIN, 15);
		g.setFont(f);
		g.drawString(s, x, y);
	}
	
	public void setType()
	{
		int rand = (int) (Math.random()*2)+1;
		if(rand == 1)
			type = "Speed Up";
		else
			type = "Slow Down";
	}
	
	public String getType()
	{
		return type;
	}
	
	private int xpos;
	private int ypos;
	private String type;
}
