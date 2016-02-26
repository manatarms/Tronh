import java.awt.Color;
import java.awt.Graphics2D;

import com.sun.prism.Image;

public class Player {
      //public void Up(Graphics2D g, java.awt.Image image, int velocity, int x, int y){
	float Up(Graphics2D gh, java.awt.Image image, float xh, float yh, float v){
        gh.drawImage(image, (int) xh, (int) yh, null);
			  v = 10;
			  yh -= v;
			  return yh;
      }
      /*
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
      }*/
}