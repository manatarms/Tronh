import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Level {
	
	int currentLevel;
	
	Level()
	{
		currentLevel = 0;
	}
	void levelInitiate(int score, Score s)
	{
		if(LevelUpCheck(score))
		{
			currentLevel++;
			s.resetCoin();
		}
		
		if(currentLevel == 0)
		{
			System.out.println("Hello World");
		}
		
		if(currentLevel == 1)
		{
			
		}
		
		if(currentLevel == 1)
		{
			
		}
	}
	boolean LevelUpCheck(int score)
	{
		if(score == 5)
		{
			return true;
		}
		return false;
	}
	
	public void levelReset()
	{
		currentLevel = 0;
	}
	
	public void drawLevel(Graphics2D g, int x, int y)
	{
		g.setColor(Color.WHITE);
		String level = "Level: " + currentLevel;
		Font f = new Font("Futura", Font.PLAIN, 15);
		g.setFont(f);
		g.drawString(level, x, y);
	}
	
	
}
