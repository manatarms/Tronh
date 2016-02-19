import java.awt.Color;
import java.awt.Graphics2D;
//Class Coin
final class Coin {
		int w=20,h = 20;
	    private final int Coinx;
	    private final int Coiny;
	        
	    public Coin(int WIDTH, int HEIGHT) {
	    	int minX =1, maxX = WIDTH-w;
			int minY =1, maxY = HEIGHT-h;
			int randX = minX + (int)(Math.random() * ((maxX - minX) + 1));
			int randY = minY + (int)(Math.random() * ((maxY - minY) + 1));
	        this.Coinx = randX;
	        this.Coiny = randY;
	    }
	    public int getX() {
	        return Coinx;
	    }
	    public int getY() {
	        return Coiny;
	    }
	      
	    public void drawCoin(Graphics2D g, int x,int y){
	    	g.setColor(Color.YELLOW);
			g.fillOval(x,y, w, h);
	    }
	        
	
}//End Coin class