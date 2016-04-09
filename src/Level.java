import java.awt.Color;
import java.awt.Graphics2D;
//import java.util.logging.Level;

public class Level {

	int currentLevel;
	boolean died;

	Level() {
		currentLevel = 0;
		died = false;
	}

	// sets what happens in each level, and changes level
	boolean levelUp(int score, boolean pickupdelay, PowerUp p, boolean death) {
		if ((score % 5 == 0) && (pickupdelay) && !(death)) {
			currentLevel++;
			if (currentLevel >= 0 && currentLevel < 5) {
				p.lockStatus = "locked";
			}

			if (currentLevel >= 5 && currentLevel < 10) {
				p.lockStatus = "unlock_1";
			}

			if (currentLevel >= 10) {
				p.lockStatus = "unlock_2";
			}
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
