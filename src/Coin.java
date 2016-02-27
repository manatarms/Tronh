import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

//Class Coin
final class Coin {

	int w = 20, h = 20;
	private final int Coinx;
	private final int Coiny;

	public Coin(int WIDTH, int HEIGHT) {
		int minX = 50, maxX = (WIDTH - 10) - w;
		int minY = 50, maxY = (HEIGHT - 10) - h;
		int randX = minX + (int) (Math.random() * ((maxX - minX) + 1));
		int randY = minY + (int) (Math.random() * ((maxY - minY) + 1));
		this.Coinx = randX;
		this.Coiny = randY;
	}

	public int getX() {
		return Coinx;
	}

	public int getY() {
		return Coiny;
	}

	public void drawCoin(Graphics2D g, int x, int y) {
		Image image = Toolkit.getDefaultToolkit().createImage("e:/java/spin.gif");
		g.drawImage(image, x, y, null);
	}

}// End Coin class