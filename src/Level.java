
public class Level {
	
	Score score = new Score();
	boolean LevelUpEligible()
	{
		if(score.highScore == 7)
		{
			return true;
		}
		return false;
	}
	
	
}
