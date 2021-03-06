import java.awt.Color;
import java.awt.Graphics2D;

public class Score {
	// Class Score

	private int currentCoins;
	int highScore;

	// Initializes score board
	public Score() {
		highScore = 0;
		currentCoins = 0;
	}

	public int getNumCoins() {
		return currentCoins;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setCoins(int n) {
		currentCoins = n;
	}

	public void saveScore() {
		if (currentCoins > highScore)
			highScore = currentCoins;
	}

	// Incrementation of coins
	public int addCoin() {
		currentCoins++;
		return currentCoins;
	}

	// Saves highscore and resets coinTotal when game ends
	public void resetCoin() {
		currentCoins = 0;
	}
	
	public void resetHighScore() {
		currentCoins = 0;
		highScore = 0;
	}

	public void drawScore(Graphics2D g, int x, int y, String text) {
		/*
		 * RANODM COLORS int rand = (int) (Math.random()*100)+1; if(rand > 0 &&
		 * rand < 20) g.setColor(Color.BLUE);
		 * 
		 * else if(rand > 20 && rand < 40) g.setColor(Color.RED);
		 * 
		 * else if(rand > 40 && rand < 60) g.setColor(Color.GREEN);
		 * 
		 * else if(rand > 60 && rand < 80) g.setColor(Color.YELLOW);
		 * 
		 * else g.setColor(Color.MAGENTA);
		 */

		/*
		 * color changes based off score if((currentCoins/2)*2 == currentCoins)
		 * g.setColor(Color.BLUE); else g.setColor(Color.GREEN);
		 */
		g.setColor(new Color(157, 241, 55));
		// g.setColor(Color.GREEN);
		String s = text + currentCoins;
		String hs = "High Score: " + highScore;
		// Font f = new Font("Futura", Font.PLAIN, 15);
		// g.setFont(f);
		g.setFont(Tronh_Game.customFont);
		g.drawString(s, x, y);
		g.drawString(hs, x, y + 20);

	}

}
