import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

//Class Enemy
		final class Enemy {
			int w=50,h = 50;
		    private int EnemyX ;
		    private int EnemyY ;
		    private int Speed=100;
		        
		    public Enemy(int WIDTH, int HEIGHT) {
		        this.EnemyX = -100;
		        this.EnemyY = 10;
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
		    
		    public void resetEnemy(){
		    	EnemyX= -100;
		    	EnemyY=10;
		    	Speed =100;
		    }
		    
		    public int speedUp(int speedup) {
		    	Speed=Speed-speedup;
		        return Speed-speedup;
		    }
		    
		    public int slowDown(int slowdown) {
		    	Speed=Speed+slowdown;
		        return Speed+slowdown;
		    }
		      
		    public void drawEnemy(Graphics2D g, int x,int y){
		    	Image enemy_char = null;
				try {
					enemy_char = ImageIO.read(Tronh_Game.class.getResource("enemy_test.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	g.drawImage(enemy_char, x, y, null);
		    }
		        
		
	}//End Enemy class