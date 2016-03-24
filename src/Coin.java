import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

//Class Coin
final class Coin {

	int w = 20, h = 20;
	private final int Coinx;
	private final int Coiny;

	// Renders Coinh position
	public Coin(int WIDTH, int HEIGHT) {
		int minX = 50, maxX = (WIDTH - 10) - w;
		int minY = 50, maxY = (HEIGHT - 10) - h;
		int randX = minX + (int) (Math.random() * ((maxX - minX) + 1));
		int randY = minY + (int) (Math.random() * ((maxY - minY) + 1));
		this.Coinx = randX;
		this.Coiny = randY;
	}

	// x-pos of Coinh
	public int getX() {
		return Coinx;
	}

	// y-pos of Coinh
	public int getY() {
		return Coiny;
	}

	// Renders Coinh image
	public void drawCoin(Graphics2D g, int x, int y) {
		ImageIcon image = null;

		/** Returns an ImageIcon, or null if the path was invalid. */
		java.net.URL imgURL = getClass().getClassLoader().getResource("images/coinh.gif");
		if (imgURL != null) {
			image = new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: images/coinh/gif");
			return;
		}

		// Converts imageIcon to image (for GIFs)
		Image img = image.getImage();
		g.drawImage(img, x, y, null);

	}

}// End Coin class