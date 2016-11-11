import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Perso {

	int life = 6;
	Image heart00;
	Image heart01;
	Image heart02;
	Image heart03;
	Image heart04;
	Image heart05;
	Image heart06;
	Image me00;
	Image me01;
	Image me02;
	Image me03;
	Image me04;
	Image me05;
	Image me06;
	Image me07;
	Image me08;
	Image me09;
	Image me10;
	Image me11;
	Image me12;
	Image me13;
	Image me14;
	int x = 192;
	int y = 192;
	int compteur=0;
	int etat = 0; // 0 = STATIK; 1 = DROITE; -1 = GAUCHE; 2 = HAUT; -2 = BAS;
	int statik = -2; //-1 = GAUCHE; 1 = DROITE; -2 = BAS; 2 = HAUT;
	int obstacle = 0; //-2 si on arrive d'en haut ; -1 si on arrive de gauche ; 0 si rien nous bloque ; -1 si on arrive de droite ; -2 si on arrive du bas
	int spriteShot = 0;
	int invincible = 0;
	AudioPerso audio;
	
	public Perso(){
		Toolkit kit = Toolkit.getDefaultToolkit();
		heart00 = kit.getImage("images/hearts/hearts00.png");
		heart01 = kit.getImage("images/hearts/hearts01.png");
		heart02 = kit.getImage("images/hearts/hearts02.png");
		heart03 = kit.getImage("images/hearts/hearts03.png");
		heart04 = kit.getImage("images/hearts/hearts04.png");
		heart05 = kit.getImage("images/hearts/hearts05.png");
		heart06 = kit.getImage("images/hearts/hearts06.png");
		me00=kit.getImage("images/me/me00.png");
		me01=kit.getImage("images/me/me01.png");
		me02=kit.getImage("images/me/me02.png");
		me03=kit.getImage("images/me/me03.png");
		me04=kit.getImage("images/me/me04.png");
		me05=kit.getImage("images/me/me05.png");
		me06=kit.getImage("images/me/me06.png");
		me07=kit.getImage("images/me/me07.png");
		me08=kit.getImage("images/me/me08.png");
		me09=kit.getImage("images/me/me09.png");
		me10=kit.getImage("images/me/me10.png");
		me11=kit.getImage("images/me/me11.png");
		me12=kit.getImage("images/me/me12.png");
		me13=kit.getImage("images/me/me13.png");
		me14=kit.getImage("images/me/me14.png");
		
	}
	
	public Perso(int x, int y){
		this();
		this.x=x;
		this.y=y;
	}
	
	
	//GESTION DES TIRS
	public void shot(Ennemi enn, Map map, Panneau pan){
		// QUAND ON TIRE DU BAS
		if(this.x>=enn.x-32 && this.x<=enn.x+32 && enn.y<this.y && this.y<=enn.y+64 && (((pan.getHeight()/2)-32-this.y)+enn.y+64)>0 && (etat == 2 || statik ==2)){
			if(enn.y-64>=0 && ((map.tab00[(int) Math.floor((enn.y-64)/64)][(int) Math.floor((enn.x)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y-64)/64)][(int) Math.floor((enn.x+64)/64)] != 7))){
				int isDisturbedForIndex;
				boolean bloque = false;
				for(int i=0; i<64; i++){
					for(isDisturbedForIndex = 0; isDisturbedForIndex<pan.listObstacles.size(); isDisturbedForIndex++){
						if(enn.y-1 <= pan.listObstacles.get(isDisturbedForIndex).y+pan.listObstacles.get(isDisturbedForIndex).height && enn.y>pan.listObstacles.get(isDisturbedForIndex).y && enn.x+64>pan.listObstacles.get(isDisturbedForIndex).x && enn.x<pan.listObstacles.get(isDisturbedForIndex).x+pan.listObstacles.get(isDisturbedForIndex).width){bloque = true; break;}
					}
					if(!bloque){enn.y--;}
				}
			}
			else if(enn.y-64<0){enn.y=0;}
			else if(!((map.tab00[(int) Math.floor((enn.y-64)/64)][(int) Math.floor((enn.x)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y-64)/64)][(int) Math.floor((enn.x+64)/64)] != 7))){
				while(enn.y%64!=0){
					enn.y--;
				}
			}
			else for(int i=64; i>0; i--){
				if(enn.y-i>=0 && ((map.tab00[(int) Math.floor((enn.y-i)/64)][(int) Math.floor((enn.x)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y-i)/64)][(int) Math.floor((enn.x+64)/64)] != 7))){enn.y=enn.y-i;}
			}
			if(enn.life>0){
				enn.audio = new AudioEnn();
				enn.audio.start();
			}
			enn.life--;
		}
					
		// QUAND ON TIRE DU HAUT
		if(this.x>=enn.x-32 && this.x<=enn.x+32 && enn.y>this.y && this.y>=enn.y-64 && (((pan.getHeight()/2)-32-this.y)+enn.y)>0 && (etat == -2 || statik ==-2)){
			if(enn.y+64<=63*(map.lignes) && ((map.tab00[(int) Math.floor((enn.y+64+64)/64)][(int) Math.floor((enn.x)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y+64+64)/64)][(int) Math.floor((enn.x+64)/64)] != 7))){
				int isDisturbedForIndex;
				boolean bloque = false;
				for(int i=0; i<64; i++){
					for(isDisturbedForIndex = 0; isDisturbedForIndex<pan.listObstacles.size(); isDisturbedForIndex++){
						if(enn.y+65 >= pan.listObstacles.get(isDisturbedForIndex).y && enn.y<pan.listObstacles.get(isDisturbedForIndex).y && enn.x+64>pan.listObstacles.get(isDisturbedForIndex).x && enn.x<pan.listObstacles.get(isDisturbedForIndex).x+pan.listObstacles.get(isDisturbedForIndex).width){bloque = true; break;}
					}
					if(!bloque){enn.y++;}
				}
			}
			else if(enn.y+64>(63*(map.lignes))){enn.y=63*(map.lignes);}
			else if(!((map.tab00[(int) Math.floor((enn.y+64+64)/64)][(int) Math.floor((enn.x)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y+64+64)/64)][(int) Math.floor((enn.x+64)/64)] != 7))){
				while(enn.y%63!=0){
					enn.y++;
				}
			}
			else for(int i=64; i>0; i--){
				if(enn.y+i<=63*(map.lignes-1) && ((map.tab00[(int) Math.floor((enn.y+64+i)/64)][(int) Math.floor((enn.x)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y+64+i)/64)][(int) Math.floor((enn.x+64)/64)] != 7))){enn.y=enn.y+i;}
				break;
			}
			if(enn.life>0){
				enn.audio = new AudioEnn();
				enn.audio.start();
			}
			enn.life--;
		}
					
		// QUAND ON TIRE DE GAUCHE
		if(this.y>=enn.y-32 && this.y<=enn.y+32 && enn.x<this.x+64 && enn.x+64>this.x+64 && (((pan.getHeight()/2)-32-this.x)+enn.x+64)>0 && (etat == 1 || statik ==1)){
			if(enn.x+64+64<=map.colonnes*63 && (((map.tab00[(int) Math.floor((enn.y)/64)][(int) Math.floor((enn.x+64+64)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y+64)/64)][(int) Math.floor((enn.x+64+64)/64)] != 7)))){
				int isDisturbedForIndex;
				boolean bloque = false;
				for(int i=0; i<64; i++){
					for(isDisturbedForIndex = 0; isDisturbedForIndex<pan.listObstacles.size(); isDisturbedForIndex++){
						if(enn.x+65 >= pan.listObstacles.get(isDisturbedForIndex).x && enn.x<pan.listObstacles.get(isDisturbedForIndex).x && enn.y+64>pan.listObstacles.get(isDisturbedForIndex).y && enn.y<pan.listObstacles.get(isDisturbedForIndex).y+pan.listObstacles.get(isDisturbedForIndex).height){bloque = true; break;}
					}
					if(!bloque){enn.x++;}
				}
			}
			else if(enn.x+64+64>map.colonnes*63){enn.x=(map.colonnes)*63;}
			else if(!(((map.tab00[(int) Math.floor((enn.y)/64)][(int) Math.floor((enn.x+64+64)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y+64)/64)][(int) Math.floor((enn.x+64+64)/64)] != 7)))){
				while(enn.x%63!=0){
					enn.x++;
				}
			}
			else {
				for(int i=64; i>0; i--){
					if(enn.x+64+i<=map.colonnes*63 && (((map.tab00[(int) Math.floor((enn.y)/64)][(int) Math.floor((enn.x+64+i)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y+64)/64)][(int) Math.floor((enn.x+64+i)/64)] != 7)))){enn.x=enn.x+i;}
					break;
				}
			}
			if(enn.life>0){
				enn.audio = new AudioEnn();
				enn.audio.start();
			}
			enn.life--;
		}
					
		// QUAND ON TIRE DE DROITE
		if(this.y>=enn.y-32 && this.y<=enn.y+32 && enn.x+64>this.x && enn.x<this.x && (((pan.getHeight()/2)-32-this.x)+enn.x)>0 && (etat == -1 || statik ==-1)){
			if(enn.x-64>=0 && ((map.tab00[(int) Math.floor((enn.y)/64)][(int) Math.floor((enn.x-64)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y+64)/64)][(int) Math.floor((enn.x-64)/64)] != 7))){
				int isDisturbedForIndex;
				boolean bloque = false;
				for(int i=0; i<64; i++){
					for(isDisturbedForIndex = 0; isDisturbedForIndex<pan.listObstacles.size(); isDisturbedForIndex++){
						if(enn.x-1 <= pan.listObstacles.get(isDisturbedForIndex).x+pan.listObstacles.get(isDisturbedForIndex).width && enn.x>pan.listObstacles.get(isDisturbedForIndex).x && enn.y+64>pan.listObstacles.get(isDisturbedForIndex).y && enn.y<pan.listObstacles.get(isDisturbedForIndex).y+pan.listObstacles.get(isDisturbedForIndex).height){bloque = true; break;}
					}
					if(!bloque){enn.x--;}
				}
			}
			else if(enn.x-64<0){enn.x=0;}
			else if(!((map.tab00[(int) Math.floor((enn.y)/64)][(int) Math.floor((enn.x-64)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y+64)/64)][(int) Math.floor((enn.x-64)/64)] != 7))){
				while(enn.x%64!=0){
					enn.x--;
				}
			}
			else{
				for(int i=64; i>0; i--){
					if(enn.x-i<=map.colonnes*64 && (((map.tab00[(int) Math.floor((enn.y)/64)][(int) Math.floor((enn.x-i)/64)] != 7) && (map.tab00[(int) Math.floor((enn.y+64)/64)][(int) Math.floor((enn.x-i)/64)] != 7)))){enn.x=enn.x-i;}
					break;
				}
			}
			if(enn.life>0){
				enn.audio = new AudioEnn();
				enn.audio.start();
			}
			enn.life--;
		}
	}


	public void seDeplacer(Map map, Panneau pan){
		// AVANCER VERS LA DROITE
		if(pan.moveH == 1){
			if(this.x<63*(map.colonnes) && ((map.tab00[(int) Math.floor((this.y+12)/64)][(int) Math.floor((this.x+53)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+52)/64)][(int) Math.floor((this.x+53)/64)] != 7)) && this.obstacle != -1){
				boolean isDisturbed = false;
				for(int i = 0; i<pan.listObstacles.size(); i++){
					if(this.x+53 >= pan.listObstacles.get(i).x && this.x+12<pan.listObstacles.get(i).x && this.y+51>pan.listObstacles.get(i).y && this.y+12<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
				}
				if(!isDisturbed){this.x=this.x+(pan.getWidth()/968)+1;}
			}
			if(this.etat == 0 || this.etat == 1){
				this.etat = 1;
				this.statik = 1;
			}
			}
		
		// AVANCER VERS LA GAUCHE
		if(pan.moveH == -1){
			if(this.x>0 && ((map.tab00[(int) Math.floor((this.y+12)/64)][(int) Math.floor((this.x+11)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+52)/64)][(int) Math.floor((this.x+11)/64)] != 7)) && this.obstacle != 1){
				boolean isDisturbed = false;
				for(int i = 0; i<pan.listObstacles.size(); i++){
					if(this.x+11 <= pan.listObstacles.get(i).x+pan.listObstacles.get(i).width && this.x+12>pan.listObstacles.get(i).x && this.y+51>pan.listObstacles.get(i).y && this.y+12<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
				}
				if(!isDisturbed){this.x=this.x-(pan.getWidth()/968)-1;}
			}
			if(this.etat == 0 || this.etat == -1){
				this.etat = -1;
				this.statik = -1;
			}
			}
		
		// AVANCER VERS LE HAUT
		if(pan.moveV == 1){
			if(this.y>0 && ((map.tab00[(int) Math.floor((this.y+11)/64)][(int) Math.floor((this.x+12)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+11)/64)][(int) Math.floor((this.x+51)/64)] != 7)) && this.obstacle != 2){
				boolean isDisturbed = false;
				for(int i = 0; i<pan.listObstacles.size(); i++){
					if(this.y+11 <= pan.listObstacles.get(i).y+pan.listObstacles.get(i).height && this.y+12>pan.listObstacles.get(i).y && this.x+51>pan.listObstacles.get(i).x && this.x+12<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
				}
				if(!isDisturbed){this.y=this.y-(pan.getHeight()/622)-1;}
			}
			if(this.etat == 0 || this.etat == 2){
				this.etat = 2;
				this.statik = 2;
			}
			}
		
		// AVANCER VERS LE BAS
		if(pan.moveV == -1){
			if(this.y<64*(map.lignes-1) && ((map.tab00[(int) Math.floor((this.y+60)/64)][(int) Math.floor((this.x+12)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+60)/64)][(int) Math.floor((this.x+51)/64)] != 7)) && this.obstacle != -2){
				boolean isDisturbed = false;
				for(int i = 0; i<pan.listObstacles.size(); i++){
					if(this.y+60 >= pan.listObstacles.get(i).y && this.y+12<pan.listObstacles.get(i).y && this.x+51>pan.listObstacles.get(i).x && this.x+12<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
				}
				if(!isDisturbed){this.y=this.y+(pan.getHeight()/622)+1;}
			}
			if(this.etat == 0 || this.etat == -2){
				this.etat = -2;
				this.statik = -2;
			}
			}
		
		// PERSONNAGE STATIQUE
		if(pan.moveH == 0 && pan.moveV == 0){
			this.etat = 0;
		}
		
		obstacle = 0;
		
	}
	
	public void afficherPerso(Graphics g, Panneau pan){
		
		if(spriteShot>0){
			if(this.statik == -1){g.drawImage(this.me12,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == 1){g.drawImage(this.me13,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == -2){g.drawImage(this.me10,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == 2){g.drawImage(this.me11,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(spriteShot == 20){spriteShot = 0;}
		}
		
		else if(this.etat == 0){
			if(this.statik == -1){g.drawImage(this.me05,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == 1){g.drawImage(this.me03,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == -2){g.drawImage(this.me00,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == 2){g.drawImage(this.me07,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
		}
		
		else{
			if(this.statik == -1 && compteur <= 30){g.drawImage(this.me05,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == 1 && compteur <= 30){g.drawImage(this.me03,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == -2 && compteur <= 30){g.drawImage(this.me01,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == 2 && compteur <= 30){g.drawImage(this.me08,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			
			if(this.statik == -1 && compteur > 30){g.drawImage(this.me06,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == 1 && compteur > 30){g.drawImage(this.me04,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == -2 && compteur > 30){g.drawImage(this.me02,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
			if(this.statik == 2 && compteur > 30){g.drawImage(this.me09,((pan.getWidth()/2)-32),((pan.getHeight()/2)-32),pan);}
		}
		
		
		if(life==6){g.drawImage(this.heart06, pan.getWidth()-196, 1, pan);}
		if(life==5){g.drawImage(this.heart05, pan.getWidth()-196, 1, pan);}
		if(life==4){g.drawImage(this.heart04, pan.getWidth()-196, 1, pan);}
		if(life==3){g.drawImage(this.heart00, pan.getWidth()-196, 1, pan);}
		if(life==2){g.drawImage(this.heart01, pan.getWidth()-196, 1, pan);}
		if(life==1){g.drawImage(this.heart02, pan.getWidth()-196, 1, pan);}
		
		
		
		if(compteur<60){this.compteur++;}
		else{compteur = 0;}
		
		if(spriteShot<20 && spriteShot!=0){this.spriteShot++;}
	}
	
	public boolean isExited(Map map, Panneau pan){
		if(map.tab00[(int)Math.floor((this.y+32)/64)][(int)Math.floor((this.x+32)/64)] == 8 && pan.lvl == 5){pan.cine = 9; pan.film = 0; pan.lvl++; return true;}
		if(map.tab00[(int)Math.floor((this.y+32)/64)][(int)Math.floor((this.x+32)/64)] == 8 && pan.lvl == 4){pan.cine = 7; pan.film = 0; pan.lvl++; return true;}
		if(map.tab00[(int)Math.floor((this.y+32)/64)][(int)Math.floor((this.x+32)/64)] == 8 && pan.lvl == 3){pan.cine = 5; pan.film = 0; pan.lvl++; return true;}
		if(map.tab00[(int)Math.floor((this.y+32)/64)][(int)Math.floor((this.x+32)/64)] == 8 && pan.lvl == 2){pan.cine = 3; pan.film = 0; pan.lvl++; return true;}
		if(map.tab00[(int)Math.floor((this.y+32)/64)][(int)Math.floor((this.x+32)/64)] == 8 && pan.lvl == 1){pan.cine = 1; pan.film = 0; pan.lvl++; return true;}
		return false;
	}
}
