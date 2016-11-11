import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Fleche {
	
	int x,y;
	int shotFromX, shotFromY;
	boolean isReal = false;
	Image fleche00;
	int sens; 	// 1 vers le BAS
				// -1 vers le HAUT
				// 2 vers la DROITE
				// -2 vers la GAUCHE
	
	public Fleche(int x, int y, int sens, boolean real){
		this.x = x; this.shotFromX = x;
		this.y = y; this.shotFromY = y;
		this.sens = sens;
		this.isReal = real;
		Toolkit kit = Toolkit.getDefaultToolkit();
		if(sens == 1){fleche00 = kit.getImage("images/enemies/archer/fleche02.png");}
		if(sens == -1){fleche00 = kit.getImage("images/enemies/archer/fleche03.png");}
		if(sens == 2){fleche00 = kit.getImage("images/enemies/archer/fleche01.png");}
		if(sens == -2){fleche00 = kit.getImage("images/enemies/archer/fleche00.png");}
	}
	
	public void isRunning(Map map, Panneau pan){
		
		if(isReal){
			// VERS LE BAS
			if(sens == 1){
				if(this.y - this.shotFromY < 192 && this.y<(pan.map.lignes-1)*63 && ((map.tab00[(int) Math.floor((this.y+64+1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64+1)/64)][(int) Math.floor((this.x+64)/64)] != 7))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.y+65 >= pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y && this.x+64>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
					}
					if(!isDisturbed){this.y=this.y+(pan.getHeight()/622)+2;}
					else{isReal = false;}
				}
				else{isReal = false;}
			}
		
			// VERS LE HAUT
			if(sens == -1){
				if(this.shotFromY - this.y < 192 && this.y>0 && ((map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x+64)/64)] != 7))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.y-1 <= pan.listObstacles.get(i).y+pan.listObstacles.get(i).height && this.y>pan.listObstacles.get(i).y && this.x+64>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
					}
					if(!isDisturbed){this.y=this.y-(pan.getHeight()/622)-2;}
					else{isReal = false;}
				}
				else{isReal = false;}
			}
		
			// VERS LA DROITE
			if(sens == 2){
				if(this.x - this.shotFromX < 192 && this.x<(pan.map.colonnes-1)*63 && (((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x+64+1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x+64+1)/64)] != 7)))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.x+65 >= pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
					}
					if(!isDisturbed){this.x=this.x+(pan.getWidth()/968)+2;}
					else{isReal = false;}
				}
				else{isReal = false;}
			}
		
			// VERS LA GAUCHE
			if(sens == -2){
				if(this.shotFromX - this.x < 192 && this.x>0 && (((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x-1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x-1)/64)] != 7)))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.x-1 <= pan.listObstacles.get(i).x+pan.listObstacles.get(i).width && this.x>pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
					}
					if(!isDisturbed){this.x=this.x-(pan.getWidth()/968)-2;}
					else{isReal = false;}
				}
				else{isReal = false;}
			}
		}
	}
	
	
	public void collision(Perso perso, Map map, Panneau pan){
		
		if(isReal){
			
			// VERS LE BAS
			if(sens == 1){
				if(this.y+48 >= perso.y && this.x>perso.x-16 && this.x+64<perso.x+80){
					if(perso.y+64<=63*(map.lignes-1) && ((map.tab00[(int) Math.floor((perso.y+64+64)/64)][(int) Math.floor((perso.x)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y+64+64)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){
						int isDisturbedForIndex;
						boolean bloque = false;
						for(int i=0; i<64; i++){
							for(isDisturbedForIndex = 0; isDisturbedForIndex<pan.listObstacles.size(); isDisturbedForIndex++){
								if(perso.y+65 >= pan.listObstacles.get(isDisturbedForIndex).y && perso.y<pan.listObstacles.get(isDisturbedForIndex).y && perso.x+64>pan.listObstacles.get(isDisturbedForIndex).x && perso.x<pan.listObstacles.get(isDisturbedForIndex).x+pan.listObstacles.get(isDisturbedForIndex).width){bloque = true; break;}
							}
							if(!bloque){perso.y++;}
						} 
					}
					else if(perso.y+64>(63*(map.lignes-1))){perso.y=63*(map.lignes-1);}
					else if(!((map.tab00[(int) Math.floor((perso.y+64+64)/64)][(int) Math.floor((perso.x)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y+64+64)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){
						while(perso.y%63!=0){
							perso.y++;
						}
					}
					else for(int i=64; i>0; i--){
						if(perso.y+i<=63*(map.lignes-1) && ((map.tab00[(int) Math.floor((perso.y+64+i)/64)][(int) Math.floor((perso.x)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64+i)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){perso.y=perso.y+i;}
					}
					
					this.isReal = false;
					if(perso.life>1){
						perso.audio = new AudioPerso();
						perso.audio.start();
					}
					perso.life--;
					perso.invincible = 1;
				}
			}
			
			// VERS LE HAUT
			if(sens == -1){
				if(this.y <= perso.y+48 && this.x>perso.x-16 && this.x+64<perso.x+80){
					if(perso.y-64>=0 && ((map.tab00[(int) Math.floor((perso.y-64)/64)][(int) Math.floor((perso.x)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y-64)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){
						int isDisturbedForIndex;
						boolean bloque = false;
						for(int i=0; i<64; i++){
							for(isDisturbedForIndex = 0; isDisturbedForIndex<pan.listObstacles.size(); isDisturbedForIndex++){
								if(perso.y-1 <= pan.listObstacles.get(isDisturbedForIndex).y+pan.listObstacles.get(isDisturbedForIndex).height && perso.y>pan.listObstacles.get(isDisturbedForIndex).y && perso.x+64>pan.listObstacles.get(isDisturbedForIndex).x && perso.x<pan.listObstacles.get(isDisturbedForIndex).x+pan.listObstacles.get(isDisturbedForIndex).width){bloque = true; break;}
							}
							if(!bloque){perso.y--;}
						}
		
					}
					else if(perso.y-64<0){perso.y=0;}
					else if(!((map.tab00[(int) Math.floor((perso.y-64)/64)][(int) Math.floor((perso.x)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y-64)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){
						while(perso.y%64!=0){
							perso.y--;
						}
					}
					else for(int i=64; i>0; i--){
						if(perso.y-i>=0 && ((map.tab00[(int) Math.floor((perso.y-i)/64)][(int) Math.floor((perso.x)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y-i)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){perso.y=perso.y-i;}
					}
				
					this.isReal = false;
					if(perso.life>1){
						perso.audio = new AudioPerso();
						perso.audio.start();
					}
					perso.life--;
					perso.invincible = 1;
				}
			}
			
			// VERS LA DROITE
			if(sens == 2){
				if(this.x+48 >= perso.x && this.y>perso.y-16 && this.y+64<perso.y+80){
					if(perso.x+64+64<=map.colonnes*63 && (((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x+64+64)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x+64+64)/64)] != 7)*/))){
						int isDisturbedForIndex;
						boolean bloque = false;
						for(int i=0; i<64; i++){
							for(isDisturbedForIndex = 0; isDisturbedForIndex<pan.listObstacles.size(); isDisturbedForIndex++){
								if(perso.x+65 >= pan.listObstacles.get(isDisturbedForIndex).x && perso.x<pan.listObstacles.get(isDisturbedForIndex).x && perso.y+64>pan.listObstacles.get(isDisturbedForIndex).y && perso.y<pan.listObstacles.get(isDisturbedForIndex).y+pan.listObstacles.get(isDisturbedForIndex).height){bloque = true; break;}
							}
							if(!bloque){perso.x++;}
						}
					}
					else if(perso.x+64+64>map.colonnes*63){perso.x=(map.colonnes-1)*63;}
					else if(!(((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x+64+64)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x+64+64)/64)] != 7)*/))){
						while(perso.x%63!=0){
							perso.x++;
						}
					}
					else {
						for(int i=64; i>0; i--){
							if(perso.x+64+i<=map.colonnes*63 && (((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x+64+i)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x+64+i)/64)] != 7)*/))){perso.x=perso.x+i;}
							break;
						}
					}
				
					this.isReal = false;
					if(perso.life>1){
						perso.audio = new AudioPerso();
						perso.audio.start();
					}
					perso.life--;
					perso.invincible = 1;
				}
			}
			
			// VERS LA GAUCHE
			if(sens == -2){
				if(this.x <= perso.x+48 && this.y>perso.y-16 && this.y+64<perso.y+80){
					if(perso.x-64>=0 && ((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x-64)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x-64)/64)] != 7)*/)){
						int isDisturbedForIndex;
						boolean bloque = false;
						for(int i=0; i<64; i++){
							for(isDisturbedForIndex = 0; isDisturbedForIndex<pan.listObstacles.size(); isDisturbedForIndex++){
								if(perso.x-1 <= pan.listObstacles.get(isDisturbedForIndex).x+pan.listObstacles.get(isDisturbedForIndex).width && perso.x>pan.listObstacles.get(isDisturbedForIndex).x && perso.y+64>pan.listObstacles.get(isDisturbedForIndex).y && perso.y<pan.listObstacles.get(isDisturbedForIndex).y+pan.listObstacles.get(isDisturbedForIndex).height){bloque = true; break;}
							}
							if(!bloque){perso.x--;}
						}
					}
					else if(perso.x-64<0){perso.x=0;}
					else if(!((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x-64)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x-64)/64)] != 7)*/)){
						while(perso.x%64!=0){
							perso.x--;
						}
					}
					else{
						for(int i=64; i>0; i--){
							if(perso.x-i<=map.colonnes*64 && (((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x-i)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x-i)/64)] != 7)*/))){perso.x=perso.x-i;}
							break;
						}
					}
				
					this.isReal = false;
					if(perso.life>1){
						perso.audio = new AudioPerso();
						perso.audio.start();
					}
					perso.life--;
					perso.invincible = 1;
				}
			}
			
		}
	}
	
	public void afficherFleche(Graphics g, Perso perso, Panneau pan){
		if(this.isReal){
			g.drawImage(this.fleche00,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);
		}
	}
	
	
}

