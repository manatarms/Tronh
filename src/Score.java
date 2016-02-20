
public class Score {
	// Class Score
		int coinTotal = 0;
		int highScore = 0;
		int totalCoins;
		
		//Initializes score board
		public Score(int c, int h){
			c = coinTotal;
			h = highScore;
		}
		
		// Incrementation of coins
		public int addCoin(int c) {
			totalCoins = c;
			System.out.println("Current Score: " + (totalCoins + 1));
			return totalCoins + 1;
		}
		
		// Saves highscore and resets coinTotal when game ends
		public void resetCoin(int coinTotal, int highScore) {
			System.out.println("Player Score: " + coinTotal);
			System.out.println("High Score : " + highScore);
			if (coinTotal > highScore) {
				System.out.println("new High Score: " + highScore);
				highScore = coinTotal;
			}
			coinTotal = 0;
		}
}