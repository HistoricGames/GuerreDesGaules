import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class PNJ {

	int x,y;
	Image pnj00;
	Image bulle;
	
	public PNJ(int x, int y, String urlImage, String urlBulle){
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.x = x;
		this.y = y;
		pnj00 = kit.getImage(urlImage);
		bulle = kit.getImage(urlBulle);
	}
	
	public void sayPhrase(Graphics g, Perso perso, Panneau pan){
		g.drawImage(this.bulle,((pan.getWidth()/2)-32-perso.x)+(this.x-64),((pan.getHeight()/2)-32-perso.y)+(this.y-64),pan);
	}
	
	public void bloquePerso(Graphics g, Perso perso, Panneau pan){
		if(perso.y+48>=this.y && perso.y+48<this.y+48 && perso.x+47>this.x && perso.x<this.x+47 && (perso.etat != 2)){perso.obstacle = -2; this.sayPhrase(g, perso, pan);} //SI PERSO EST EN HAUT
		else if(perso.x+48>=this.x && perso.x+64<this.x+48 && perso.y+47>this.y && perso.y<this.y+47 && (perso.etat != -1)){perso.obstacle = -1; this.sayPhrase(g, perso, pan);} //SI PERSO EST A GAUCHE
		else if(perso.x<=this.x+48 && perso.x>this.x && perso.y+47>this.y && perso.y<this.y+47 && (perso.etat != 1)){perso.obstacle = 1; this.sayPhrase(g, perso, pan);} //SI PERSO EST A DROITE
		else if(perso.y<=this.y+48 && perso.y>this.y && perso.x+47>this.x && perso.x<this.x+47 && (perso.etat != -2)){perso.obstacle = 2; this.sayPhrase(g, perso, pan);} //SI PERSO EST EN BAS
	}
	
	public void affichePNJ(Graphics g, Perso perso, Panneau pan){
		g.drawImage(this.pnj00,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);
	}
}
