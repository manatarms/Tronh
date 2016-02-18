import java.awt.Color;
import java.awt.Graphics2D;

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
		    	g.setColor(Color.RED);
				g.fillRect(x,y, w, h);
				//g.fillRoundRect(x, y, w, h, 10,10);
		    }
		        
		
	}//End Enemy class