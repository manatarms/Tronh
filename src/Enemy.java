import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

//Class Enemy
final class Enemy {
	int w = 50, h = 50;
	private int EnemyX;
	private int EnemyY;

	private int Speed = 100;
	
	
	public Enemy(int WIDTH, int HEIGHT) {
		this.EnemyX = -200;
		this.EnemyY = -200;
	}

	
	public int getX() {
		return EnemyX;
	}

	public int getY() {
		return EnemyY;
	}

	public int currentSpeed() {
		return Speed;
	}

	public void resetEnemy() {
		EnemyX = -200;
		EnemyY = -200;
	}

	public int speedUp(int speedup) {
		Speed = Speed - speedup;
		return Speed - speedup;
	}

	public int slowDown(int slowdown) {
		Speed = Speed + slowdown;
		return Speed + slowdown;
	}
	
	public void moveEnemy(int playerX,int playerY,int speed){
		if(playerX > EnemyX){
			this.EnemyX = EnemyX+speed;
		}
		else if(playerX < EnemyX){
			this.EnemyX = EnemyX - speed;
		}
		
		if(playerY > EnemyY){
			this.EnemyY = EnemyY+speed;
		}
		else if(playerY < EnemyY){
			this.EnemyY = EnemyY-speed;
		}
	}

	public void drawEnemy(Graphics2D g, int x, int y, int dir) {
		Image enemy_U = null, enemy_D = null, enemy_L = null, enemy_R = null;
		try {
			enemy_U = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_U.png"));
			enemy_D = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_D.png"));
			enemy_L = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_L.png"));
			enemy_R = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_R.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// DEFAULT (down)
		if (dir == 0) {
			g.drawImage(enemy_D, x, y, null);
		}

		// UP
		if (dir == 1) {
			g.drawImage(enemy_U, x, y, null);
		}

		// LEFT
		if (dir == 2) {
			g.drawImage(enemy_L, x, y, null);
		}

		// RIGHT
		if (dir == 3) {
			g.drawImage(enemy_R, x, y, null);
		}
	}

}// End Enemy class