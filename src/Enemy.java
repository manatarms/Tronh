import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

//Class Enemy
final class Enemy {
	int w = 50, h = 50;
	private int EnemyX;
	private int EnemyY;
	private String Direction;
	Image enemy_U , enemy_D , enemy_L, enemy_R;

	private int Speed = 100;

	public Enemy(int WIDTH, int HEIGHT) {
		this.EnemyX = 950;
		this.EnemyY = 500;
		try {
			enemy_U = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_U.png"));
			enemy_D = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_D.png"));
			enemy_L = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_L.png"));
			enemy_R = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_R.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public int getX() {
		return EnemyX;
	}

	public int getY() {
		return EnemyY;
	}

	public void setX(int x){
		EnemyX = x;
	}
	public void setY(int y){
		EnemyX = y;
	}
	public int currentSpeed() {
		return Speed;
	}
	public int getEnemyHeight(String dir){
		if(dir == "UP"||dir == "up" ||dir == "DOWN"||dir == "down"){
			return enemy_U.getHeight(null);
		}
		else if(dir == "LEFT"||dir == "left"||dir == "RIGHT"||dir == "right"){
			return enemy_L.getHeight(null);
		}
		else{
		return 0;
		}
	}
	public int getEnemyWidth(String dir){
		if(dir == "UP"||dir == "up" ||dir == "DOWN"||dir == "down"){
			return enemy_U.getWidth(null);
		}
		else if(dir == "LEFT"||dir == "left"||dir == "RIGHT"||dir == "right"){
			return enemy_L.getWidth(null);
		}
		else{
		return 0;
		}
	}
	public void resetEnemy() {
		EnemyX = 950;
		EnemyY = 500;
	}

	public int speedUp(int speedup) {
		Speed = Speed - speedup;
		return Speed - speedup;
	}

	public int slowDown(int slowdown) {
		Speed = Speed + slowdown;
		return Speed + slowdown;
	}
	
	public String getDirection(){
		return Direction;
	}

	public void setDirection(String dir){
		if(dir == "UP"||dir == "up"){
			Direction = "UP";
		}
		else if(dir == "DOWN"||dir == "down"){
			Direction = "DOWN";
		}
		else if(dir == "LEFT"||dir == "left"){
			Direction = "LEFT";
		}
		else if(dir == "RIGHT"||dir == "right"){
			Direction = "RIGHT";
		}
		
	}
	
	public void moveEnemy(int playerX, int playerY, int speed) {
		if (playerX > EnemyX) {
			this.EnemyX = EnemyX + speed;
		} else if (playerX < EnemyX) {
			this.EnemyX = EnemyX - speed;
		}

		if (playerY > EnemyY) {
			this.EnemyY = EnemyY + speed;
		} else if (playerY < EnemyY) {
			this.EnemyY = EnemyY - speed;
		}
	}
	
	
	public void drawEnemy(Graphics2D g, int x, int y, String dir) {
		
		
		// DEFAULT (down)
		if (dir == "down"||dir == "DOWN") {
			g.drawImage(enemy_D, x, y, null);
			 
		}

		// UP
		if (dir == "up"||dir == "UP") {
			g.drawImage(enemy_U, x, y, null);
		}

		// LEFT
		if (dir == "left"||dir == "LEFT") {
			g.drawImage(enemy_L, x, y, null);
		}

		// RIGHT
		if (dir == "right"||dir == "RIGHT") {
			g.drawImage(enemy_R, x, y, null);
		}
	}

}// End Enemy class