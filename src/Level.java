import java.awt.Color;
import java.awt.Graphics2D;

public class Level {

	int currentLevel;

	Level() {
		currentLevel = 0;
	}

	// sets what happens in each level, and changes level
	boolean levelUp(int score, boolean pickupdelay) {
		if ((score % 5 == 0) && (pickupdelay)) {
			currentLevel++;
			// if(currentLevel == 0)
			// {
			//
			// }
			//
			// if(currentLevel == 1)
			// {
			//
			// }
			//
			// if(currentLevel == 1)
			// {
			//
			// }
		}
		if (score % 5 > 0) {
			return true;
		}
		return false;
	}

	// resets back to level 0
	public void levelReset() {
		currentLevel = 0;
	}

	// prints the current level on the game screen
	public void drawLevel(Graphics2D g, int x, int y) {
		g.setColor(Color.WHITE);
		String level = "Level: " + currentLevel;
		g.setFont(Tronh_Game.customFont);
		g.drawString(level, x, y);
	}

}
