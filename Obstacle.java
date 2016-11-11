import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Obstacle {

	int x, y, width, height;
	Image img;
	
	public Obstacle(int x,int y,int width,int height, String srcImg){
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.img = kit.getImage(srcImg);
	}
	
	public void afficherObstacle(Graphics g, Perso perso, Panneau pan){
		g.drawImage(this.img,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);
	}
}
