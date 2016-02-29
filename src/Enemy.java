import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

//Class Enemy
final class Enemy {
	int w = 50, h = 50;
	private int EnemyX;
	private int EnemyY;
	private String Direction;
	Image enemy_U, enemy_D, enemy_L, enemy_R;

	private int Speed = 100;

	public Enemy() {
		this.EnemyX = 950;
		this.EnemyY = 500;
<<<<<<< HEAD

		// Implementation of GIFs
		ImageIcon eU, eD, eL, eR;

		java.net.URL enemyU = getClass().getClassLoader().getResource("images/enemy_U.gif");
		java.net.URL enemyD = getClass().getClassLoader().getResource("images/enemy_D.gif");
		java.net.URL enemyL = getClass().getClassLoader().getResource("images/enemy_L.gif");
		java.net.URL enemyR = getClass().getClassLoader().getResource("images/enemy_R.gif");

		if (enemyU != null || enemyD != null || enemyL != null || enemyR != null) {
			eU = new ImageIcon(enemyU);
			eD = new ImageIcon(enemyD);
			eL = new ImageIcon(enemyL);
			eR = new ImageIcon(enemyR);
		} else {
			System.err.println("Couldn't find an image file for enemy");
			return;
=======
		Direction = "UP";
		try {
			enemy_U = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_U.png"));
			enemy_D = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_D.png"));
			enemy_L = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_L.png"));
			enemy_R = ImageIO.read(Tronh_Game.class.getResource("images/enemy_test_R.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
>>>>>>> 53e7f8369e88786c450a28f229cb4e3ce4f46a70
		}

		// Creating GIFs as Images
		enemy_U = eU.getImage();
		enemy_D = eD.getImage();
		enemy_L = eL.getImage();
		enemy_R = eR.getImage();

	}

	public int getX() {
		return EnemyX;
	}

	public int getY() {
		return EnemyY;
	}

	public void setX(int x) {
		EnemyX = x;
	}

	public void setY(int y) {
		EnemyX = y;
	}

	public int currentSpeed() {
		return Speed;
	}

	public int getEnemyHeight(String dir) {
		if (dir == "UP" || dir == "up" || dir == "DOWN" || dir == "down") {
			return enemy_U.getHeight(null);
		} else if (dir == "LEFT" || dir == "left" || dir == "RIGHT" || dir == "right") {
			return enemy_L.getHeight(null);
		} else {
			return 0;
		}
	}

	public int getEnemyWidth(String dir) {
		if (dir.equals("UP") || dir.equals("up") || dir.equals("DOWN") || dir.equals("down")) {
			return enemy_U.getWidth(null);
		} else if (dir.equals("LEFT") || dir.equals("left") || dir.equals("RIGHT") || dir.equals("right")) {
			return enemy_L.getWidth(null);
		} else {
			return 0;
		}
	}

	public void resetEnemy() {
		EnemyX = 950;
		EnemyY = 500;
		Direction = "UP";
	}

	public int speedUp(int speedup) {
		Speed = Speed - speedup;
		return Speed - speedup;
	}

	public int slowDown(int slowdown) {
		Speed = Speed + slowdown;
		return Speed + slowdown;
	}

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String dir) {
		if (dir.equals("UP") || dir.equals("up")) {
			Direction = "UP";
		} else if (dir.equals("DOWN") || dir.equals("down")) {
			Direction = "DOWN";
		} else if (dir.equals("LEFT") || dir.equals("left")) {
			Direction = "LEFT";
		} else if (dir.equals("RIGHT") || dir.equals("right")) {
			Direction = "RIGHT";
		}

	}

	public void moveEnemy(int playerX, int playerY, int speed) {
		if (playerX > EnemyX) {
			this.EnemyX = EnemyX + speed;
			Direction = "RIGHT";
		}
		if (playerX < EnemyX) {
			this.EnemyX = EnemyX - speed;
			Direction = "LEFT";
		}

		if (playerY > EnemyY) {
			this.EnemyY = EnemyY + speed;
			Direction = "DOWN";
		}
<<<<<<< HEAD

=======
>>>>>>> 53e7f8369e88786c450a28f229cb4e3ce4f46a70
		if (playerY < EnemyY) {
			this.EnemyY = EnemyY - speed;
			Direction = "UP";
		}
	}

	public void drawEnemy(Graphics2D g, int x, int y, String dir) {

		// DEFAULT (down)
		if (dir.equals("down") || dir.equals("DOWN")) {
			g.drawImage(enemy_D, x, y, null);
		}

		// UP
		if (dir.equals("up") || dir.equals("UP")) {
			g.drawImage(enemy_U, x, y, null);
		}

		// LEFT
		if (dir.equals("left") || dir.equals("LEFT")) {
			g.drawImage(enemy_L, x, y, null);
		}

		// RIGHT
		if (dir.equals("right") || dir.equals("RIGHT")) {
			g.drawImage(enemy_R, x, y, null);
		}
	}

}// End Enemy class