import java.awt.Graphics2D;

public class Player extends Tronh_Game {
      void Up(Graphics2D g){
        g.drawImage(player_U, (int) x, (int) y, null);
			  velocity = 10;
			  y -= velocity;
      }
      
      void Down(Graphics2D g){
        g.drawImage(player_D, (int) x, (int) y, null);
			  velocity = 10;
			  y += velocity;
      }
      
      void Left(Graphics2D g){
        g.drawImage(player_L, (int) x, (int) y, null);
			  velocity = 10;
			  x -= velocity;
      }
      
      void Right(Graphics2D g){
        g.drawImage(player_R, (int) x, (int) y, null);
			  velocity = 10;
			  x += velocity;
      }
}