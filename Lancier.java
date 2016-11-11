import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Lancier extends Ennemi {

	Image lancier00, lancier01, lancier02, lancier03, lancier04, lancier05, lancier06, lancier07, lancier08, lancier09;
	boolean orientCollumn;
	
	public Lancier(int x, int y, boolean orient) {
		super(x, y);
		this.life = 3;
		this.orientCollumn = orient;
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.lancier00 = kit.getImage("images/enemies/lancier/lancier00.png");
		this.lancier01 = kit.getImage("images/enemies/lancier/lancier01.png");
		this.lancier02 = kit.getImage("images/enemies/lancier/lancier02.png");
		this.lancier03 = kit.getImage("images/enemies/lancier/lancier03.png");
		this.lancier04 = kit.getImage("images/enemies/lancier/lancier04.png");
		this.lancier05 = kit.getImage("images/enemies/lancier/lancier05.png");
		this.lancier06 = kit.getImage("images/enemies/lancier/lancier06.png");
		this.lancier07 = kit.getImage("images/enemies/lancier/lancier07.png");
		this.lancier08 = kit.getImage("images/enemies/lancier/lancier08.png");
		this.lancier09 = kit.getImage("images/enemies/lancier/lancier09.png");
	}
	
	public void depEnnemis(Perso perso, Map map, Panneau pan){
		
		// ORIENTE EN COLONNE
		if(this.orientCollumn){
		// SI L'ENNEMI ARRIVE D'EN HAUT
		if(((pan.getWidth()/2)-32-perso.x)+64+this.x>0 && ((pan.getWidth()/2)-32-perso.x)+this.x<pan.getWidth() && ((pan.getHeight()/2)-32-perso.y)+this.y < pan.getHeight() && perso.y<this.y && this.compteur!=0){
			if(this.y-1>=0 && ((map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x+64)/64)] != 7))){
				boolean isDisturbed = false;
				for(int i = 0; i<pan.listObstacles.size(); i++){
					if(this.y-1 <= pan.listObstacles.get(i).y+pan.listObstacles.get(i).height && this.y>pan.listObstacles.get(i).y && this.x+64>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
				}
				if(!isDisturbed){this.y=this.y-(pan.getHeight()/622)-1;}
			}
			if(Math.abs(perso.x-this.x) < Math.abs(perso.y-this.y)){this.orientation = 1;}
		}
		
		// SI L'ENNEMI ARRIVE DU BAS
		if(((pan.getWidth()/2)-32-perso.x)+64+this.x>0 && ((pan.getWidth()/2)-32-perso.x)+this.x<pan.getWidth() && ((pan.getHeight()/2)-32-perso.y)+64+this.y > 0 && perso.y>this.y && this.compteur!=0){
			if(this.y+1<=63*(map.lignes-1) && ((map.tab00[(int) Math.floor((this.y+128+1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+128+1)/64)][(int) Math.floor((this.x+64)/64)] != 7))){
				boolean isDisturbed = false;
				for(int i = 0; i<pan.listObstacles.size(); i++){
					if(this.y+65+64 >= pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y && this.x+64>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
				}
				if(!isDisturbed){this.y=this.y+(pan.getHeight()/622)+1;}
			}
			if(Math.abs(perso.x-this.x) < Math.abs(perso.y-this.y)){this.orientation = -1;}
		}
		
		// SI L'ENNEMI ARRIVE DE GAUCHE
		if(((pan.getHeight()/2)-32-perso.y)+64+this.y>0 && ((pan.getHeight()/2)-32-perso.y)+this.y<pan.getHeight() && ((pan.getWidth()/2)-32-perso.x)+64+this.x > 0 && perso.x>this.x && this.compteur!=0){
			if(this.x+64+1<=map.colonnes*63 && (((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x+64+1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+128)/64)][(int) Math.floor((this.x+64+1)/64)] != 7)))){
				boolean isDisturbed = false;
				for(int i = 0; i<pan.listObstacles.size(); i++){
					if(this.x+65 >= pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x && this.y+128>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
				}
				if(!isDisturbed){this.x=this.x+(pan.getWidth()/968)+1;}
			}
			if(Math.abs(perso.x-this.x) > Math.abs(perso.y-this.y)){this.orientation = 2;}
		}
		
		// SI L'ENNEMI ARRIVE DE DROITE
		if(((pan.getHeight()/2)-32-perso.y)+64+this.y>0 && ((pan.getHeight()/2)-32-perso.y)+this.y<pan.getHeight() && ((pan.getWidth()/2)-32-perso.x)+this.x < pan.getWidth() && perso.x<this.x && this.compteur!=0){
			if(this.x-1<=map.colonnes*64 && (((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x-1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x-1)/64)] != 7)))){
				boolean isDisturbed = false;
				for(int i = 0; i<pan.listObstacles.size(); i++){
					if(this.x-1-64 <= pan.listObstacles.get(i).x+pan.listObstacles.get(i).width && this.x>pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
				}
				if(!isDisturbed){this.x=this.x-(pan.getWidth()/968)-1;}
			}
			if(Math.abs(perso.x-this.x) > Math.abs(perso.y-this.y)){this.orientation = -2;}
		}
		
		if(this.compteur<2){
			this.compteur++;
		}
		else{this.compteur=0;}
	}
		
		// ORIENTE EN LIGNE
		else{
			// SI L'ENNEMI ARRIVE D'EN HAUT
			if(((pan.getWidth()/2)-32-perso.x)+64+this.x>0 && ((pan.getWidth()/2)-32-perso.x)+this.x<pan.getWidth() && ((pan.getHeight()/2)-32-perso.y)+this.y < pan.getHeight() && perso.y<this.y && this.compteur!=0){
				if(this.y-1>=0 && ((map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y-1)/64)][(int) Math.floor((this.x+128)/64)] != 7))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.y-1-64 <= pan.listObstacles.get(i).y+pan.listObstacles.get(i).height && this.y>pan.listObstacles.get(i).y && this.x+128>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
					}
					if(!isDisturbed){this.y=this.y-(pan.getHeight()/622)-1;}
				}
				if(Math.abs(perso.x-this.x) < Math.abs(perso.y-this.y)){this.orientation = 1;}
			}
			
			// SI L'ENNEMI ARRIVE DU BAS
			if(((pan.getWidth()/2)-32-perso.x)+64+this.x>0 && ((pan.getWidth()/2)-32-perso.x)+this.x<pan.getWidth() && ((pan.getHeight()/2)-32-perso.y)+64+this.y > 0 && perso.y>this.y && this.compteur!=0){
				if(this.y+1<=63*(map.lignes)-20 && ((map.tab00[(int) Math.floor((this.y+64+1)/64)][(int) Math.floor((this.x)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64+1)/64)][(int) Math.floor((this.x+128)/64)] != 7))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.y+65+64 >= pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y && this.x+128>pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x+pan.listObstacles.get(i).width){isDisturbed = true;}
					}
					if(!isDisturbed){this.y=this.y+(pan.getHeight()/622)+1;}
				}
				if(Math.abs(perso.x-this.x) < Math.abs(perso.y-this.y)){this.orientation = -1;}
			}
			
			// SI L'ENNEMI ARRIVE DE GAUCHE
			if(((pan.getHeight()/2)-32-perso.y)+64+this.y>0 && ((pan.getHeight()/2)-32-perso.y)+this.y<pan.getHeight() && ((pan.getWidth()/2)-32-perso.x)+64+this.x > 0 && perso.x>this.x && this.compteur!=0){
				if(this.x+64+1+64<=map.colonnes*63 && (((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x+64+1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x+64+1)/64)] != 7)))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.x+65+64 >= pan.listObstacles.get(i).x && this.x<pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
					}
					if(!isDisturbed){this.x=this.x+(pan.getWidth()/968)+1;}
				}
				if(Math.abs(perso.x-this.x) > Math.abs(perso.y-this.y)){this.orientation = 2;}
			}
			
			// SI L'ENNEMI ARRIVE DE DROITE
			if(((pan.getHeight()/2)-32-perso.y)+64+this.y>0 && ((pan.getHeight()/2)-32-perso.y)+this.y<pan.getHeight() && ((pan.getWidth()/2)-32-perso.x)+this.x < pan.getWidth() && perso.x<this.x && this.compteur!=0){
				if(this.x-1<=map.colonnes*64 && (((map.tab00[(int) Math.floor((this.y)/64)][(int) Math.floor((this.x-1)/64)] != 7) && (map.tab00[(int) Math.floor((this.y+64)/64)][(int) Math.floor((this.x-1)/64)] != 7)))){
					boolean isDisturbed = false;
					for(int i = 0; i<pan.listObstacles.size(); i++){
						if(this.x-1 <= pan.listObstacles.get(i).x+pan.listObstacles.get(i).width && this.x>pan.listObstacles.get(i).x && this.y+64>pan.listObstacles.get(i).y && this.y<pan.listObstacles.get(i).y+pan.listObstacles.get(i).height){isDisturbed = true;}
					}
					if(!isDisturbed){this.x=this.x-(pan.getWidth()/968)-1;}
				}
				if(Math.abs(perso.x-this.x) > Math.abs(perso.y-this.y)){this.orientation = -2;}
			}
			
			if(this.compteur<2){
				this.compteur++;
			}
			else{this.compteur=0;}
		}
	}
	

	
	public void collision(Perso perso, Map map, Panneau pan){
		
		// ORIENTATION EN COLONNES
		if(perso.x<=this.x+20 && perso.x+20>=this.x && perso.y+96>=this.y && perso.y<=this.y+96 && this.orientCollumn){
			//ENNEMI ORIENTE SUR LA DROITE
			if(this.orientation == 2){
				if(perso.x+64<=map.colonnes*63 && (((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x+64+64)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x+64+64)/64)] != 7)*/))){
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
				else if(!(((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x+64+64)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x+64+64)/64)] != 7)*/))){
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
			//ENNEMI ORIENTE SUR LA GAUCHE
			if(this.orientation == -2){
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
						if(perso.x-i<=map.colonnes*64 && (((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x-i)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x-i)/64)] != 7)*/))){perso.x=perso.x-i;}
						break;
					}
				}
			}
			//ENNEMI ORIENTE SUR LE HAUT
			if(this.orientation == -1){
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
					if(perso.y-i>=0 && ((map.tab00[(int) Math.floor((perso.y-i)/64)][(int) Math.floor((perso.x)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y-i)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){perso.y=perso.y-i;}
				}
			}
			//ENNEMI ORIENTE VERS LE BAS
			if(this.orientation == 1){
				if(perso.y+64<=63*(map.lignes-1) && ((map.tab00[(int) Math.floor((perso.y+64+64)/64)][(int) Math.floor((perso.x)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64+64)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){
					int isDisturbedForIndex;
					boolean bloque = false;
					for(int i=0; i<64; i++){
						for(isDisturbedForIndex = 0; isDisturbedForIndex<pan.listObstacles.size(); isDisturbedForIndex++){
							if(perso.y+65+64 >= pan.listObstacles.get(isDisturbedForIndex).y && perso.y<pan.listObstacles.get(isDisturbedForIndex).y && perso.x+64>pan.listObstacles.get(isDisturbedForIndex).x && perso.x<pan.listObstacles.get(isDisturbedForIndex).x+pan.listObstacles.get(isDisturbedForIndex).width){bloque = true; break;}
						}
						if(!bloque){perso.y++;}
					}
				}
				else if(perso.y+64>(63*(map.lignes-1))){perso.y=63*(map.lignes-1);}
				else if(!((map.tab00[(int) Math.floor((perso.y+64+64)/64)][(int) Math.floor((perso.x)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64+64)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){
					while(perso.y%63!=0){
						perso.y++;
					}
				}
				else for(int i=64; i>0; i--){
					if(perso.y+i<=63*(map.lignes-1) && ((map.tab00[(int) Math.floor((perso.y+64+i)/64)][(int) Math.floor((perso.x)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64+i)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){perso.y=perso.y+i;}
				}
			}
			if(perso.life>1){
				perso.audio = new AudioPerso();
				perso.audio.start();
			}
			perso.life--;
			perso.invincible = 1;
		}
		
		// ORIENTATION EN LIGNES
		if(perso.x<=this.x+84 && perso.x+84>=this.x && perso.y+32>=this.y && perso.y<=this.y+32 && !this.orientCollumn){
			//ENNEMI ORIENTE SUR LA DROITE
			if(this.orientation == 2){
				if(perso.x+64+64<=map.colonnes*63 && (((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x+64+64)/64)] != 7) /*&& (map.tab00[(int) Math.floor((perso.y+64)/64)][(int) Math.floor((perso.x+64+64)/64)] != 7)*/))){
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
			//ENNEMI ORIENTE SUR LA GAUCHE
			if(this.orientation == -2){
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
						if(perso.x-i<=map.colonnes*64 && (((map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x-i)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y)/64)][(int) Math.floor((perso.x-i)/64)] != 7)*/))){perso.x=perso.x-i;}
						break;
					}
				}
			}
			//ENNEMI ORIENTE SUR LE HAUT
			if(this.orientation == -1){
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
			}
			//ENNEMI ORIENTE VERS LE BAS
			if(this.orientation == 1){
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
					if(perso.y+i<=63*(map.lignes-1) && ((map.tab00[(int) Math.floor((perso.y+64+i)/64)][(int) Math.floor((perso.x)/64)] != 7)/* && (map.tab00[(int) Math.floor((perso.y+64+i)/64)][(int) Math.floor((perso.x+64)/64)] != 7)*/)){perso.y=perso.y+i;}
				}
			}
			perso.life--;
			perso.invincible=1;
		}
}
	
	public void afficherEnnemi(Graphics g, Perso perso, Panneau pan){
		if(this.life>0){
			if(this.orientCollumn && pan.cptSprites < 30 && this.y<=perso.y){g.drawImage(this.lancier01,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(this.orientCollumn && pan.cptSprites >= 30 && this.y<=perso.y){g.drawImage(this.lancier02,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(this.orientCollumn && pan.cptSprites < 30 && this.y>perso.y){g.drawImage(this.lancier08,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y-64,pan);}
			if(this.orientCollumn && pan.cptSprites >= 30 && this.y>perso.y){g.drawImage(this.lancier09,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y-64,pan);}
			if(!this.orientCollumn && pan.cptSprites < 30 && this.x<=perso.x){g.drawImage(this.lancier03,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(!this.orientCollumn && pan.cptSprites >= 30 && this.x<=perso.x){g.drawImage(this.lancier04,((pan.getWidth()/2)-32-perso.x)+this.x,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(!this.orientCollumn && pan.cptSprites < 30 && this.x>perso.x){g.drawImage(this.lancier05,((pan.getWidth()/2)-32-perso.x)+this.x-64,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
			if(!this.orientCollumn && pan.cptSprites >= 30 && this.x>perso.x){g.drawImage(this.lancier06,((pan.getWidth()/2)-32-perso.x)+this.x-64,((pan.getHeight()/2)-32-perso.y)+this.y,pan);}
		}
	}
	
}
