import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Ennemi {
	
	int life;
	int x,y;
	Image epouvantail;
	int compteur = 0;
	int orientation; //1 vers le bas, -1 vers le haut, 2 vers la droite, -2 vers la gauche
	AudioEnn audio;
	
	public Ennemi(int x, int y){
		Toolkit kit = Toolkit.getDefaultToolkit();
		epouvantail = kit.getImage("enemies/epouvantail.png");
		this.x=x;
		this.y=y;
		this.life = 1;
		this.orientation=1;
	}
	
	// COLLISION ENTRE PERSO ET ENNEMIS
		public void collision(Perso perso, Map map, Panneau pan){
					if(perso.x<=this.x+20 && perso.x+20>=this.x && perso.y+32>=this.y && perso.y<=this.y+32){
						//PERSO ORIENTE SUR LA GAUCHE
						if(perso.etat == -1 || perso.statik == -1){
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
						}
						//PERSO ORIENTE SUR LA DROITE
						if(perso.etat == 1 || perso.statik == 1){
							if(perso.x-64>=0 && ((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x-64)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x-64)/64)] != 7)*/)){
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
							else if(!((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x-64)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x-64)/64)] != 7)*/)){
								while(perso.x%64!=0){
									perso.x--;
								}
							}
							else{
								for(int i=64; i>0; i--){
									if(perso.x-i<=map.colonnes*64 && (((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x-i)/64)] != 7) && (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x-i)/64)] != 7)))){perso.x=perso.x-i;}
									break;
								}
							}
						}
						//PERSO ORIENTE VERS LE BAS
						if(perso.etat == -2 || perso.statik == -2){
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
							else if(!((map.tab00[(int) Math.floor((perso.y-64)/64)][(int) Math.floor((perso.x)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y-64)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){
								while(perso.y%64!=0){
									perso.y--;
								}
							}
							else for(int i=64; i>0; i--){
								if(perso.y-i>=0 && ((map.tab00[(int) Math.floor((perso.y-i)/64)][(int) Math.floor((perso.x)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y-i)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){perso.y=perso.y-i;}
							}
						}
						//PERSO ORIENTE VERS LE HAUT
						if(perso.etat == 2 || perso.statik == 2){
							if(perso.y+64<=63*(map.lignes-1) && ((map.tab00[(int) Math.floor((perso.y+64+64)/64)][(int) Math.floor((perso.x)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64+64)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){
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
								if(perso.y+i<=63*(map.lignes-1) && ((map.tab00[(int) Math.floor((perso.y+64+i)/64)][(int) Math.floor((perso.x)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y+64+i)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){perso.y=perso.y+i;}
							}
						}
						if(perso.life>1){
							perso.audio = new AudioPerso();
							perso.audio.start();
						}
						perso.life--;
						perso.invincible = 1;
					}
		}
		
		public void depEnnemis(Perso perso, Map map, Panneau pan){}
		
		public void afficherEnnemi(Graphics g, Perso perso, Panneau pan){}
}
