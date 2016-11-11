import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Archer extends Ennemi{
	
	Image archer00, archer01, archer02, archer03, archer04, archer05, archer06, archer07, archer08, archer09;
	boolean shotInCollumn;
	Fleche fleche;
	int frequenceTirs = 0;

	public Archer(int x, int y, boolean shotInCollumn) {
		super(x, y);
		super.life = 3;
		this.shotInCollumn = shotInCollumn;
		Toolkit kit = Toolkit.getDefaultToolkit();
		archer00=kit.getImage("images/enemies/archer/archer00.png");
		archer01=kit.getImage("images/enemies/archer/archer01.png");
		archer02=kit.getImage("images/enemies/archer/archer02.png");
		archer03=kit.getImage("images/enemies/archer/archer03.png");
		archer04=kit.getImage("images/enemies/archer/archer04.png");
		archer05=kit.getImage("images/enemies/archer/archer05.png");
		archer06=kit.getImage("images/enemies/archer/archer06.png");
		archer07=kit.getImage("images/enemies/archer/archer07.png");
		archer08=kit.getImage("images/enemies/archer/archer08.png");
		archer09=kit.getImage("images/enemies/archer/archer09.png");
		fleche = new Fleche(0,0,1,false);
	}
	
	public void depEnnemis(Perso perso, Map map, Panneau pan){
		// SI L'ENNEMI ARRIVE D'EN HAUT
		if(((pan.getWidth()/2)-32-perso.x)+64+this.x>0 && ((pan.getWidth()/2)-32-perso.x)+this.x<pan.getWidth() && ((pan.getHeight()/2)-32-perso.y)+64+this.y > 0 && perso.y>this.y && this.compteur!=0){
			if(this.y-(pan.getHeight()/622)>=0){
				if(this.shotInCollumn){
					if(this.y<perso.y-193 && ((map.tab00[(int) Math.floor((this.y+64+1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64+1)/64)][(int) Math.floor((this.x+64)/64)] != 7))){
						boolean isDisturbed = false;
						for(int i = 0; i<pan.listObstacles.size(); i++){
							if(this.y+65 >= pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y && this.x+64>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
						}
						if(!isDisturbed){this.y=this.y+(pan.getHeight()/622)+1;}
					}
					else if(this.y>perso.y-192 && this.y-(pan.getHeight()/622)-1>0 && ((map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x+64)/64)] != 7))){
						boolean isDisturbed = false;
						for(int i = 0; i<pan.listObstacles.size(); i++){
							if(this.y-1 <= pan.listObstacles.get(i).y+pan.listObstacles.get(i).height && this.y>pan.listObstacles.get(i).y && this.x+64>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
						}
						if(!isDisturbed){this.y=this.y-(pan.getHeight()/622)-1;}
					}
					}
				else if(this.y+1<(map.lignes)*63 && ((map.tab00[(int) Math.floor((this.y+64+1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64+1)/64)][(int) Math.floor((this.x+64)/64)] != 7))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.y+65 >= pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y && this.x+64>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
					}
					if(!isDisturbed){this.y=this.y+(pan.getHeight()/622)+1;}
				}
				}
			if(Math.abs(perso.x-this.x) < Math.abs(perso.y-this.y)){this.orientation = 1;}
			
			if(this.frequenceTirs == 100 && shotInCollumn){fleche = new Fleche(this.x,this.y,1,true);}
		}
		
		// SI L'ENNEMI ARRIVE DU BAS
		if(((pan.getWidth()/2)-32-perso.x)+64+this.x>0 && ((pan.getWidth()/2)-32-perso.x)+this.x<pan.getWidth() && ((pan.getHeight()/2)-32-perso.y)+this.y < pan.getHeight() && perso.y<this.y && this.compteur!=0){
			if(this.y+(pan.getHeight()/622)<=63*(map.lignes)){
				if(this.shotInCollumn){
					if(this.y>perso.y+193 && this.y-(pan.getHeight()/622)-1>0 && ((map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x+64)/64)] != 7))){
						boolean isDisturbed = false;
						for(int i = 0; i<pan.listObstacles.size(); i++){
							if(this.y-1 <= pan.listObstacles.get(i).y+pan.listObstacles.get(i).height && this.y>pan.listObstacles.get(i).y && this.x+64>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
						}
						if(!isDisturbed){this.y=this.y-(pan.getHeight()/622)-1;}
					}
					else if(this.y<perso.y+191 && this.y+1<(map.lignes)*63 && ((map.tab00[(int) Math.floor((this.y+64+1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64+1)/64)][(int) Math.floor((this.x+64)/64)] != 7))){
						boolean isDisturbed = false;
						for(int i = 0; i<pan.listObstacles.size(); i++){
							if(this.y+65 >= pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y && this.x+64>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
						}
						if(!isDisturbed){this.y=this.y+(pan.getHeight()/622)+1;}
					}
				}
				else if(this.y-(pan.getHeight()/622)-1>0 && ((map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x+64)/64)] != 7))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.y-1 <= pan.listObstacles.get(i).y+pan.listObstacles.get(i).height && this.y>pan.listObstacles.get(i).y && this.x+64>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
					}
					if(!isDisturbed){this.y=this.y-(pan.getHeight()/622)-1;}
				}
			}
			if(Math.abs(perso.x-this.x) < Math.abs(perso.y-this.y)){this.orientation = -1;}
			
			if(this.frequenceTirs == 100 && shotInCollumn){fleche = new Fleche(this.x,this.y,-1, true);}
		}
		
		// SI L'ENNEMI ARRIVE DE GAUCHE
		if(((pan.getHeight()/2)-32-perso.y)+64+this.y>0 && ((pan.getHeight()/2)-32-perso.y)+this.y<pan.getHeight() && ((pan.getWidth()/2)-32-perso.x)+64+this.x > 0 && perso.x>this.x && this.compteur!=0){
			if(this.x-(pan.getWidth()/968)>=0){
				if(!this.shotInCollumn){
					if(this.x+64<perso.x-128 && (((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x+64+1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x+64+1)/64)] != 7)))){
						boolean isDisturbed = false;
						for(int i = 0; i<pan.listObstacles.size(); i++){
							if(this.x+65 >= pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
						}
						if(!isDisturbed){this.x=this.x+(pan.getWidth()/968)+1;}
					}
					else if(this.x+64>perso.x-128 && (((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x-1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x-1)/64)] != 7)))){
						boolean isDisturbed = false;
						for(int i = 0; i<pan.listObstacles.size(); i++){
							if(this.x-1 <= pan.listObstacles.get(i).x+pan.listObstacles.get(i).width && this.x>pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
						}
						if(!isDisturbed){this.x=this.x-(pan.getWidth()/968)-1;}
					}
				}
				else if(this.x+1<(map.colonnes)*63 &&(((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x+64+1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x+64+1)/64)] != 7)))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.x+65 >= pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
					}
					if(!isDisturbed){this.x=this.x+(pan.getWidth()/968)+1;}
				}
			}
			else if(this.x+64<perso.x-128){
				boolean isDisturbed = false;
				for(int i = 0; i<pan.listObstacles.size(); i++){
					if(this.x+65 >= pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
				}
				if(!isDisturbed){this.x=this.x+(pan.getWidth()/968)+1;}
			}
			if(Math.abs(perso.x-this.x) > Math.abs(perso.y-this.y)){this.orientation = 2;}
			
			if(this.frequenceTirs == 100 && !shotInCollumn){fleche = new Fleche(this.x,this.y,2, true);}
		}
		
		// SI L'ENNEMI ARRIVE DE DROITE
		if(((pan.getHeight()/2)-32-perso.y)+64+this.y>0 && ((pan.getHeight()/2)-32-perso.y)+this.y<pan.getHeight() && ((pan.getWidth()/2)-32-perso.x)+this.x < pan.getWidth() && perso.x<this.x && this.compteur!=0){
			if(this.x+(pan.getWidth()/968)<=(map.colonnes)*64){
				if(!this.shotInCollumn){
					if(this.x>perso.x+192 && (((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x-1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x-1)/64)] != 7)))){
						boolean isDisturbed = false;
						for(int i = 0; i<pan.listObstacles.size(); i++){
							if(this.x-1 <= pan.listObstacles.get(i).x+pan.listObstacles.get(i).width && this.x>pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
						}
						if(!isDisturbed){this.x=this.x-(pan.getWidth()/968)-1;}
					}
					else if(this.x<perso.x+192 && this.x+1<(map.colonnes)*63 && (((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x+64+1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x+64+1)/64)] != 7)))){
						boolean isDisturbed = false;
						for(int i = 0; i<pan.listObstacles.size(); i++){
							if(this.x+65 >= pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
						}
						if(!isDisturbed){this.x=this.x+(pan.getWidth()/968)+1;}
					}
				}
				else if((((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x-1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x-1)/64)] != 7)))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.x-1 <= pan.listObstacles.get(i).x+pan.listObstacles.get(i).width && this.x>pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
					}
					if(!isDisturbed){this.x=this.x-(pan.getWidth()/968)-1;}
				}
			}
			else if(this.x>perso.x+192){
				boolean isDisturbed = false;
				for(int i = 0; i<pan.listObstacles.size(); i++){
					if(this.x-1 <= pan.listObstacles.get(i).x+pan.listObstacles.get(i).width && this.x>pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
				}
				if(!isDisturbed){this.x=this.x-(pan.getWidth()/968)-1;}
			}
			if(Math.abs(perso.x-this.x) > Math.abs(perso.y-this.y)){this.orientation = -2;}
			
			if(this.frequenceTirs == 100 && !shotInCollumn){fleche = new Fleche(this.x,this.y,-2, true);}
		}
		
		if(this.compteur<2){
			this.compteur++;
		}
		else{this.compteur=0;}
		
		if(frequenceTirs<100){this.frequenceTirs++;}
		else{frequenceTirs=0;}
		
	}
	
	public void collision(Perso perso, Map map, Panneau pan){
		super.collision(perso, map, pan);
	}
	
	public void afficherEnnemi(Graphics g, Perso perso, Panneau pan ){
		if(this.life>0){
			if(pan.cptSprites < 30 && this.shotInCollumn && this.y<=perso.y){g.drawImage(this.archer01,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(pan.cptSprites >= 30 && this.shotInCollumn && this.y<=perso.y){g.drawImage(this.archer02,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(pan.cptSprites < 30 && this.shotInCollumn && this.y>perso.y){g.drawImage(this.archer08,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(pan.cptSprites >= 30 && this.shotInCollumn && this.y>perso.y){g.drawImage(this.archer09,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(pan.cptSprites < 30 && !this.shotInCollumn && this.x<=perso.x){g.drawImage(this.archer03,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(pan.cptSprites >= 30 && !this.shotInCollumn && this.x<=perso.x){g.drawImage(this.archer04,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(pan.cptSprites < 30 && !this.shotInCollumn && this.x>perso.x){g.drawImage(this.archer05,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(pan.cptSprites >= 30 && !this.shotInCollumn && this.x>perso.x){g.drawImage(this.archer06,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
		}
	}

}
