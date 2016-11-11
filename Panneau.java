import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Panneau extends JPanel{
	
	private Perso perso = new Perso(0,0);
	ArrayList <Ennemi> listEnnemis; 
	ArrayList <PNJ> listPNJ;
	ArrayList <Obstacle> listObstacles;
	
	int moveH, moveV, cptSprites=0, shot, espace, cptDead;
	boolean downZ, downQ, downS, downD, premierTour = true, isExited = false;
	Map map = new Map();
	Archer archerTest = new Archer(-9999999,-9999999, false);
	int cptMusic = 17000, cptMenu = 0;
	boolean menu = true;
	int cine = -1;
	int film = 0;
	Toolkit kit = Toolkit.getDefaultToolkit();
	int lvl = 1;
	

	public void paintComponent(Graphics g){
		
		// AFFICHAGE DU MENU
		if(menu){
			if(cptMenu<500){
				g.setColor(Color.white);
				g.fillRect(0,0,this.getWidth(), this.getHeight());
				Image rs, specialMe, textMe;
				rs = kit.getImage("images/socialMedia00.png");
				specialMe = kit.getImage("images/me/testme.png");
				textMe = kit.getImage("images/pnj/bulles/bulleIntro.png");
				g.drawImage(rs,(this.getWidth()/2)-32-300, (this.getHeight()/2)-32-250, this);
				g.drawImage(specialMe,(this.getWidth()/2)-32, (this.getHeight()/2)-32+250, this);
				g.drawImage(textMe,(this.getWidth()/2)-32-64, (this.getHeight()/2)-32+250-64, this);
				if(espace == 1){cptMenu = 600;}
			}
			else{
				g.setColor(Color.black);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				if(cptMenu>700){
					if(cptMusic >= 15000){map.audio00 = new Audio(); map.audio00.start(); cptMusic = 0;}
					else{cptMusic++;}
					for(int j = 0; j<this.getHeight(); j = j+64){
						for(int i = 0; i<this.getWidth(); i = i+64){
							g.drawImage(kit.getImage("images/map/wallMenu.png"), i , j , this);
						}
					}
					g.drawImage(kit.getImage("images/titre.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-250, this);
					g.drawImage(kit.getImage("images/name.png"),(this.getWidth())-500, (this.getHeight())-100, this);
					g.drawImage(kit.getImage("images/commencer.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+250, this);
				}
			}	
			
			if(espace == 1 && cptMenu > 700){
				menu = false;
				espace = 0;
				map.audio00.stop();
				cptMenu = 0;
				cine=0; 
				cptMusic = 22000;
			}
			cptMenu ++;
		}
		
		// AFFICHAGE DES DIFFERENTES PARTIES NARRATIVES
		else if(cine == 0){
			if(cptMusic == 22000){map.audio00 = new Audio(); map.audio00.choix = 1; map.audio00.start(); cptMusic = 0;}
			cptMusic++;
			
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(kit.getImage("images/continuer.png"),this.getWidth()-500, this.getHeight()-100, this);
			
			if(film == 0){
				g.drawImage(kit.getImage("images/cine/11/map/europe58.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/11/bulles/textecine00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 1){
				g.drawImage(kit.getImage("images/cine/11/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/11/bulles/textecine01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 2){
				g.drawImage(kit.getImage("images/cine/11/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/11/bulles/textecine02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 3){
				g.drawImage(kit.getImage("images/cine/11/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/11/bulles/textecine03.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 4){
				g.drawImage(kit.getImage("images/cine/11/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/11/bulles/textecine04.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 5){
				g.drawImage(kit.getImage("images/cine/11/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/11/bulles/textecine05.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 6){
				g.drawImage(kit.getImage("images/cine/11/bulles/texteperso00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 7){
				g.drawImage(kit.getImage("images/cine/11/bulles/texteperso01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 8){
				g.drawImage(kit.getImage("images/cine/11/bulles/texteperso02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(espace == 1){
				if(film >= 5){map.audio00.stop(); cptMusic = 17000;}
				if(film == 8){this.cine = -1; map.changeMapLvl1(this); isExited = false; premierTour = true;}
				film++;
				espace = 0;
			}
		}
		
		else if(cine == 1){
			if(cptMusic == 22000){map.audio00 = new Audio(); map.audio00.choix = 1; map.audio00.start(); cptMusic = 0;}
			if(film>=3 && film !=5){cptMusic++;}
			
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			if(film != 5){g.drawImage(kit.getImage("images/continuer.png"),this.getWidth()-500, this.getHeight()-100, this);}
			
			if(film == 0){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/cine/12/bulles/texteperso00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 1){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/cine/12/bulles/texteperso01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 2){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/cine/12/bulles/texteperso02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 3){
				g.drawImage(kit.getImage("images/cine/12/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/12/bulles/textecine00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 4){
				g.drawImage(kit.getImage("images/cine/12/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/12/bulles/textecine01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 5){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/thanks.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-200, this);
			}
			
			if(espace == 1){
				if(film == 2){cptMusic = 22000;}
				if(film<4){film++;}
				else{cine=2; film =0;}
				espace = 0;
			}
		}
		
		else if(cine == 2){
			if(cptMusic == 22000){map.audio00 = new Audio(); map.audio00.choix = 1; map.audio00.start(); cptMusic = 0;}
			cptMusic++;
			
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(kit.getImage("images/continuer.png"),this.getWidth()-500, this.getHeight()-100, this);
			
			if(film == 0){
				g.drawImage(kit.getImage("images/cine/11/map/europe57.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/21/bulles/textecine00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 1){
				g.drawImage(kit.getImage("images/cine/11/map/europe56.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/21/bulles/textecine01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 2){
				g.drawImage(kit.getImage("images/cine/11/map/europe55.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/21/bulles/textecine02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 3){
				g.drawImage(kit.getImage("images/cine/21/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/21/bulles/textecine03.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 4){
				g.drawImage(kit.getImage("images/cine/21/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/21/bulles/textecine04.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 5){
				g.drawImage(kit.getImage("images/cine/21/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/21/bulles/textecine05.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 6){
				g.drawImage(kit.getImage("images/cine/21/map/carte02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/21/bulles/textecine06.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 7){
				g.drawImage(kit.getImage("images/cine/21/map/carte03.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/21/bulles/textecine07.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 8){
				g.drawImage(kit.getImage("images/cine/21/bulles/texteperso00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(film == 9){
				g.drawImage(kit.getImage("images/cine/21/bulles/texteperso01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(film == 10){
				g.drawImage(kit.getImage("images/cine/21/bulles/texteperso02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(espace == 1){
				if(film >= 7){map.audio00.stop(); cptMusic = 17000;}
				if(film == 10){isExited = false; this.cine = -2; map.changeMap(this); premierTour = true;}
				else{film++;}
				espace = 0;
			}
		}
		
		else if(cine == 3){
			
			if(cptMusic == 22000){map.audio00 = new Audio(); map.audio00.choix = 1; map.audio00.start(); cptMusic = 0;}
			cptMusic++;
			
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(kit.getImage("images/continuer.png"),this.getWidth()-500, this.getHeight()-100, this);
			
			
			if(film == 0){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/cine/22/bulles/texteperso00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(film == 1){
				g.drawImage(kit.getImage("images/cine/22/bulles/texteperso01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(film == 2){
				g.drawImage(kit.getImage("images/cine/22/bulles/texteperso02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(film == 3){
				g.drawImage(kit.getImage("images/cine/22/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/22/bulles/textecine00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 4){
				g.drawImage(kit.getImage("images/cine/22/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/22/bulles/textecine01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 5){
				g.drawImage(kit.getImage("images/cine/22/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/22/bulles/textecine02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 6){
				g.drawImage(kit.getImage("images/thanks.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-200, this);
			}
			
			if(espace == 1){
				if(film < 2){map.audio00.stop(); cptMusic = 17000;}
				if(film == 2){cptMusic = 22000;}
				if(film<5){film++;}
				else{cine=4; film =0;}
				espace = 0;
			}
		}
		
		else if(cine == 4){
			
			if(cptMusic == 22000){map.audio00 = new Audio(); map.audio00.choix = 1; map.audio00.start(); cptMusic = 0;}
			if(film <=4){cptMusic++;}
			
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(kit.getImage("images/continuer.png"),this.getWidth()-500, this.getHeight()-100, this);
			
			if(film == 0){
				g.drawImage(kit.getImage("images/cine/11/map/europe52.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/31/bulles/textecine00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 1){
				g.drawImage(kit.getImage("images/cine/11/map/europe51.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/31/bulles/textecine02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 2){
				g.drawImage(kit.getImage("images/cine/31/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/31/bulles/textecine03.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 3){
				g.drawImage(kit.getImage("images/cine/31/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/31/bulles/textecine04.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 4){
				g.drawImage(kit.getImage("images/cine/31/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/31/bulles/textecine05.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 5){
				g.drawImage(kit.getImage("images/cine/31/bulles/texteperso00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(film == 6){
				g.drawImage(kit.getImage("images/cine/31/bulles/texteperso01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(espace == 1){
				if(film >= 4 ){map.audio00.stop(); cptMusic = 9500;}
				//if(film == 2){cptMusic = 22000;}
				if(film == 6){cine = -3; map.changeMap(this); isExited = false; premierTour = true;}
				if(film <= 5 ){film++;}
				//else{cine=2; film =0;}
				espace = 0;
			}
		}
		
		else if(cine == 5){
			
			if(cptMusic == 22000){map.audio00 = new Audio(); map.audio00.choix = 1; map.audio00.start(); cptMusic = 0;}
			if(film > 1){cptMusic++;}
			
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(kit.getImage("images/continuer.png"),this.getWidth()-500, this.getHeight()-100, this);
			
			if(film == 0){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/cine/32/bulles/texteperso00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(film == 1){
				g.drawImage(kit.getImage("images/cine/32/bulles/texteperso01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(film == 2){
				g.drawImage(kit.getImage("images/cine/32/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/32/bulles/textecine00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 3){
				g.drawImage(kit.getImage("images/cine/32/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/32/bulles/textecine01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}		
				
			if(espace == 1){
				if(film == 0 ){map.audio00.stop();}
				if(film == 1){cptMusic = 22000;}
				if(film <= 2 ){film++;}
				if(film == 3){film = 0; cine++;}
				espace = 0;
			}
		}
		
		else if(cine == 6){
			if(cptMusic == 22000){map.audio00 = new Audio(); map.audio00.choix = 1; map.audio00.start(); cptMusic = 0;}
			if(film <=4){cptMusic++;}
			
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(kit.getImage("images/continuer.png"),this.getWidth()-500, this.getHeight()-100, this);
			
			if(film == 0){
				g.drawImage(kit.getImage("images/cine/32/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/41/bulles/textecine00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 1){
				g.drawImage(kit.getImage("images/cine/32/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/41/bulles/textecine01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 2){
				g.drawImage(kit.getImage("images/cine/32/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/41/bulles/textecine02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 3){
				g.drawImage(kit.getImage("images/cine/32/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/41/bulles/textecine03.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 4){
				g.drawImage(kit.getImage("images/cine/32/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/41/bulles/textecine04.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 5){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/cine/41/bulles/texteperso00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 6){
				g.drawImage(kit.getImage("images/cine/41/bulles/texteperso01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 7){
				g.drawImage(kit.getImage("images/cine/41/bulles/texteperso02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(espace == 1){
				if(film == 7){cine = -4; isExited = false; map.changeMap(this); premierTour = true; cptMusic = 15000;}
				film++;
				espace = 0;
			}
			
		}
		
		else if(cine == 7){
			if(cptMusic == 22000){map.audio00 = new Audio(); map.audio00.choix = 1; map.audio00.start(); cptMusic = 0;}
			if(film > 2){cptMusic++;}
			
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(kit.getImage("images/continuer.png"),this.getWidth()-500, this.getHeight()-100, this);
			
			if(film == 0){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/cine/42/bulles/texteperso00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 1){
				g.drawImage(kit.getImage("images/cine/42/bulles/texteperso01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 2){
				g.drawImage(kit.getImage("images/cine/42/bulles/texteperso02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 3){
				g.drawImage(kit.getImage("images/cine/42/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/42/bulles/textecine00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 4){
				g.drawImage(kit.getImage("images/cine/42/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/42/bulles/textecine01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 5){
				g.drawImage(kit.getImage("images/cine/42/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/42/bulles/textecine02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 6){
				g.drawImage(kit.getImage("images/cine/42/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/42/bulles/textecine03.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			
			if(film == 7){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/thanks.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-200, this);
			}
			
			if(espace == 1){
				if(film == 2){cptMusic = 22000;}
				if(film == 6){film = 0; cine = 8;}
				else if(film < 6){film++;}
				espace = 0;
			}
		}
		
		else if(cine == 8){
			if(cptMusic == 22000){map.audio00 = new Audio(); map.audio00.choix = 1; map.audio00.start(); cptMusic = 0;}
			if(film <=4){cptMusic++;}
			
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(kit.getImage("images/continuer.png"),this.getWidth()-500, this.getHeight()-100, this);
			
			if(film == 0){
				g.drawImage(kit.getImage("images/cine/51/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/51/bulles/textecine00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 1){
				g.drawImage(kit.getImage("images/cine/51/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/51/bulles/textecine01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 2){
				g.drawImage(kit.getImage("images/cine/51/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/51/bulles/textecine02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 3){
				g.drawImage(kit.getImage("images/cine/51/map/carte02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/51/bulles/textecine03.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 4){
				g.drawImage(kit.getImage("images/cine/51/map/carte02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/51/bulles/textecine04.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 5){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/cine/51/bulles/texteperso00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 6){
				g.drawImage(kit.getImage("images/cine/51/bulles/texteperso01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 7){
				g.drawImage(kit.getImage("images/cine/51/bulles/texteperso02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			
			if(espace == 1){
				if(film == 7){cine = -5; isExited = false; map.changeMap(this); premierTour = true; cptMusic = 9500;}
				film++;
				espace = 0;
			}
		}
		
		else if(cine == 9){
			if(cptMusic == 22000){map.audio00 = new Audio(); map.audio00.choix = 1; map.audio00.start(); cptMusic = 0;}
			if(film > 3){cptMusic++;}
			
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(kit.getImage("images/continuer.png"),this.getWidth()-500, this.getHeight()-100, this);
			
			if(film == 0){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/cine/52/bulles/texteperso00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 1){
				g.drawImage(kit.getImage("images/cine/52/bulles/texteperso01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 2){
				g.drawImage(kit.getImage("images/cine/52/bulles/texteperso02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 3){
				g.drawImage(kit.getImage("images/cine/52/bulles/texteperso03.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 4){
				g.drawImage(kit.getImage("images/cine/51/map/carte02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/52/bulles/textecine00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 5){
				g.drawImage(kit.getImage("images/cine/51/map/carte02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/52/bulles/textecine01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 6){
				g.drawImage(kit.getImage("images/cine/51/map/carte02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/52/bulles/textecine02.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 7){
				g.drawImage(kit.getImage("images/cine/52/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/52/bulles/textecine03.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 8){
				g.drawImage(kit.getImage("images/cine/52/map/carte00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/52/bulles/textecine04.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 9){
				g.drawImage(kit.getImage("images/cine/52/map/carte01.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/52/bulles/textecine05.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 10){
				g.drawImage(kit.getImage("images/cine/11/map/europe50.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-300, this);
				g.drawImage(kit.getImage("images/pnj/cesarnarrateur.png"),(this.getWidth()/2)-32-100, (this.getHeight()/2)-32+275, this);
				g.drawImage(kit.getImage("images/cine/52/bulles/textecine06.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32+150, this);
			}
			if(film == 11){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/cine/52/bulles/final00.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32,this);
			}
			if(film == 12){
				map.audio00.stop();
				g.drawImage(kit.getImage("images/thanks.png"),(this.getWidth()/2)-32-250, (this.getHeight()/2)-32-200, this);
			}
			
			if(espace == 1){
				if(film == 3){cptMusic = 22000;}
				if(film == 12){menu = true; film = 0; cine = 0; lvl = 1;}
				if(film < 12){film++;}
				espace = 0;
			}
		}
		
		else if(perso.life > 0 && !isExited){
			g.setColor(Color.black);
			g.fillRect(0,0, this.getWidth(), this.getHeight());
			
			//ENTREE DES ELEMENTS
			if(premierTour && cine == -1){
				
				perso = new Perso(1400,2950);
				
				listEnnemis = new ArrayList();
				listPNJ = new ArrayList();
				listObstacles = new ArrayList();
				
				// INIT DES ENNEMIS
				
				//BLOCUS A DROITE DU CAMP
				listEnnemis.add(new Lancier(2700, 2500, false));
				listEnnemis.add(new Lancier(2700, 2900, false));
				listEnnemis.add(new Epeiste(2900, 2500));
				listEnnemis.add(new Epeiste(2900, 2700));
				listEnnemis.add(new Epeiste(2900, 2900));
				
				//SUR LE CHEMIN DROIT (en montée)
				listEnnemis.add(new Epeiste(2800, 1800));
				listEnnemis.add(new Epeiste(3000,1700));
				listEnnemis.add(new Archer(2900, 1300, true));
				listEnnemis.add(new Archer(3000, 900, true));
				listEnnemis.add(new Epeiste(2900, 100));
				listEnnemis.add(new Epeiste(2700, 100));
				listEnnemis.add(new Epeiste(2500, 100));
				listEnnemis.add(new Epeiste(2800, 300));
				listEnnemis.add(new Epeiste(2600, 300));
				listEnnemis.add(new Lancier(2400, 900, true));
				listEnnemis.add(new Archer(2200,1400, true));
				
				//SUR LE CHEMIN GAUCHE (en montée)
				listEnnemis.add(new Epeiste(100, 1856));
				listEnnemis.add(new Epeiste (600, 1800));
				listEnnemis.add(new Epeiste(300, 1600));
				listEnnemis.add(new Archer(200, 1350, true));
				listEnnemis.add(new Epeiste(250, 500));
				
				//SUR LE CHEMIN GAUCHE (en descente)
				listEnnemis.add(new Epeiste(576, 640));
				listEnnemis.add(new Epeiste(700, 900));
				listEnnemis.add(new Archer(1300, 1200, true));
				
				//PREFINAL
				listEnnemis.add(new Epeiste(1472,1216));
				listEnnemis.add(new Epeiste(1920,1216));
				listEnnemis.add(new Epeiste(1664,1472));
				
				//FINAL : CAMP GAULOIS
				listEnnemis.add(new Epeiste(1280, 576));
				listEnnemis.add(new Epeiste(1472,640));
				listEnnemis.add(new Epeiste(1664, 576));
				listEnnemis.add(new Archer(1200, 300, false));
				listEnnemis.add(new Epeiste(1536, 400));
				listEnnemis.add(new Epeiste(1344, 300));
				listEnnemis.add(new Epeiste(1728, 300));
				listEnnemis.add(new Lancier(1300, 100, true));
				listEnnemis.add(new Lancier(1900, 100, true));
				
				
				
				
				// INIT DES PNJ
				listPNJ.add(new PNJ(300, 2650, "images/pnj/marcus.png", "images/pnj/bulles/1/bullemarcus.png"));
				listPNJ.add(new PNJ(820, 2825, "images/pnj/labienus.png", "images/pnj/bulles/1/bullelabienus.png"));
				listPNJ.add(new PNJ(350, 3000, "images/pnj/opiter.png", "images/pnj/bulles/1/bulleopiter.png"));
				listPNJ.add(new PNJ(1200, 2500, "images/pnj/cesar.png", "images/pnj/bulles/1/bullecesar.png"));
				listPNJ.add(new PNJ(1900, 2800, "images/pnj/tertius.png", "images/pnj/bulles/1/bulletertius.png"));
				listPNJ.add(new PNJ(1100, 3000, "images/pnj/sextus.png", "images/pnj/bulles/1/bullesextus.png"));
				listPNJ.add(new PNJ(100,50, "images/pnj/publius.png", "images/pnj/bulles/1/bullepublius.png"));
				listPNJ.add(new PNJ(720, 100, "images/pnj/hostus.png", "images/pnj/bulles/1/bullehostus.png"));
				listPNJ.add(new PNJ(3050,100, "images/pnj/aulus.png", "images/pnj/bulles/1/bulleaulus.png"));
				listPNJ.add(new PNJ(2500,0, "images/pnj/ceidio.png", "images/pnj/bulles/1/bulleceidio.png"));
				listPNJ.add(new PNJ(1450, 2325, "images/carreTrans.png", "images/pnj/bulles/1/bullesoldat1.png"));
				listPNJ.add(new PNJ(1600, 2325, "images/carreTrans.png", "images/pnj/bulles/1/bullesoldat2.png"));
				listPNJ.add(new PNJ(1750, 2325, "images/carreTrans.png", "images/pnj/bulles/1/bullesoldat3.png"));
				
				
				// INIT DES OBSTACLES
				listObstacles.add(new Obstacle(600, 2600,512, 256, "images/obstacles/tente00.png"));
				listObstacles.add(new Obstacle(655,2750,32,128, "images/obstacles/banniere00.png"));
				listObstacles.add(new Obstacle(1012,2750,32,128,"images/obstacles/banniere00.png"));
				listObstacles.add(new Obstacle(200, 2400, 256, 256, "images/obstacles/tente02.png"));
				listObstacles.add(new Obstacle(100,2800,256,256,"images/obstacles/tente01.png"));
				listObstacles.add(new Obstacle(1300, 2700, 256,256, "images/obstacles/tente01.png"));
				listObstacles.add(new Obstacle(825, 2950, 32, 128, "images/obstacles/banniere01.png"));
				listObstacles.add(new Obstacle(1472, 5, 256, 256, "images/obstacles/tente03.png"));
				listObstacles.add(new Obstacle(1980, 400, 256,192, "images/obstacles/tente04.png"));
				listObstacles.add(new Obstacle(1100, 500, 256, 128, "images/obstacles/charette00.png"));
				listObstacles.add(new Obstacle(1000, 50, 256, 128, "images/obstacles/charette00.png"));
				listObstacles.add(new Obstacle(2000, 30, 128, 256, "images/obstacles/charette01.png"));
				listObstacles.add(new Obstacle(1344, 2192, 576, 192, "images/obstacles/phalange00.png"));
				listObstacles.add(new Obstacle(832, 1664, 64, 64, "images/obstacles/casque00.png"));
				listObstacles.add(new Obstacle(1200, 2100, 64, 64, "images/enemies/archer/archer00.png"));
				listObstacles.add(new Obstacle(1600, 2000, 64, 64, "images/enemies/archer/archer00.png"));
				listObstacles.add(new Obstacle(2000, 2100, 64, 64, "images/enemies/archer/archer00.png"));
				
			}
			
			if(premierTour && cine == -2){
				perso = new Perso(1400,2700);
				
				listEnnemis = new ArrayList();
				listPNJ = new ArrayList();
				listObstacles = new ArrayList();
				
				// INIT DES ENNEMIS
				listEnnemis.add(new Epeiste(1400, 2100));
				listEnnemis.add(new Epeiste(900,2000));
				listEnnemis.add(new Epeiste(1900,2000));
				listEnnemis.add(new Epeiste(400,1900));
				listEnnemis.add(new Epeiste(2400,1900));
				
				listEnnemis.add(new Lancier(1400, 1500, true));
				listEnnemis.add(new Epeiste(1000, 1400));
				listEnnemis.add(new Epeiste(1800, 1400));
				listEnnemis.add(new Epeiste(1200, 1000));
				listEnnemis.add(new Epeiste(1600, 1000));
				listEnnemis.add(new Lancier(1200, 700, true));
				listEnnemis.add(new Lancier(1600, 700, true));
				listEnnemis.add(new Epeiste(500, 700));
				listEnnemis.add(new Epeiste(2636, 700));
				listEnnemis.add(new Lancier(600, 1000, false));
				listEnnemis.add(new Lancier(2536, 1000, false));
				
				listEnnemis.add(new Archer(1736, 200, true));
				listEnnemis.add(new Archer(1936, 100, true));
				listEnnemis.add(new Archer(1536, 100, true));
				listEnnemis.add(new Archer(300, 300, false));
				listEnnemis.add(new Archer(2900, 300, false));
				
				
				// INIT DES PNJ
				listPNJ.add(new PNJ(900, 2600, "images/pnj/sextus.png", "images/pnj/bulles/2/bullesextus.png"));
				listPNJ.add(new PNJ(2000, 2800, "images/pnj/opiter.png", "images/pnj/bulles/2/bulleopiter.png"));
				listPNJ.add(new PNJ(400, 2800, "images/pnj/marcus.png", "images/pnj/bulles/2/bullemarcus.png"));
				listPNJ.add(new PNJ(1600, 2500, "images/pnj/publius.png", "images/pnj/bulles/2/bullepublius.png"));
				listPNJ.add(new PNJ(2700, 3135, "images/pnj/tertius.png", "images/pnj/bulles/2/bulletertius.png"));
				listPNJ.add(new PNJ(1984, 512, "images/pnj/aulus.png", "images/pnj/bulles/2/bulleaulus.png"));
				
				
				// INIT DES OBSTACLES
				listObstacles.add(new Obstacle(1300, 2950, 512, 256, "images/obstacles/bateau00.png"));
				listObstacles.add(new Obstacle(400, 2950, 512, 256, "images/obstacles/bateau00.png"));
				listObstacles.add(new Obstacle(2200, 2950, 512, 256, "images/obstacles/bateau00.png"));
				
				listObstacles.add(new Obstacle(1000, 0, 256, 128, "images/obstacles/charette00.png"));
				listObstacles.add(new Obstacle(2200, 64, 128, 256, "images/obstacles/charette01.png"));
				listObstacles.add(new Obstacle(0, 0, 128, 256, "images/obstacles/charette01.png"));
				listObstacles.add(new Obstacle(2816, 448, 256, 128, "images/obstacles/charette00.png"));
				
			}
			
			if(premierTour && cine == -3){
				perso = new Perso(1400,2700);
				
				listEnnemis = new ArrayList();
				listPNJ = new ArrayList();
				listObstacles = new ArrayList();
				
				// INIT DES ENNEMIS
				listEnnemis.add(new Lancier(1200, 1800, true));
				listEnnemis.add(new Lancier(1680, 1800, true));
				listEnnemis.add(new Epeiste(1100, 1300));
				listEnnemis.add(new Epeiste(1100, 1500));
				listEnnemis.add(new Epeiste(1780, 1300));
				listEnnemis.add(new Epeiste(1780, 1500));
				listEnnemis.add(new Epeiste(600, 1600));
				listEnnemis.add(new Epeiste(2100, 1400));
				listEnnemis.add(new Epeiste(2200, 1100));
				listEnnemis.add(new Epeiste(2600, 1500));
				listEnnemis.add(new Lancier(2800, 2600, false));
				listEnnemis.add(new Epeiste(200,1300));
				listEnnemis.add(new Archer(1700, 600, true));
				
				listEnnemis.add(new Epeiste(1800, 400));
				listEnnemis.add(new Epeiste(1994, 450));
				listEnnemis.add(new Epeiste(2252, 400));
				listEnnemis.add(new Lancier(1700, 300, true));
				listEnnemis.add(new Lancier(2512, 300, true));
				
				
				// INIT DES PNJ
				listPNJ.add(new PNJ(1600, 2944, "images/pnj/cesar.png", "images/pnj/bulles/3/bullecesar.png"));
				listPNJ.add(new PNJ(1692, 2428, "images/pnj/marcus.png", "images/pnj/bulles/3/bullemarcus.png"));
				listPNJ.add(new PNJ(342, 356, "images/pnj/lucie.png", "images/pnj/bulles/3/bullelucie.png"));
				listPNJ.add(new PNJ(292, 2556, "images/pnj/publius.png", "images/pnj/bulles/3/bullepublius.png"));
				listPNJ.add(new PNJ(228, 2556, "images/pnj/ceidio.png", "images/pnj/bulles/3/bulleceidio.png"));
				listPNJ.add(new PNJ(2028, 256, "images/pnj/sextus.png", "images/pnj/bulles/3/bullesextus.png"));
				listPNJ.add(new PNJ(1200, 2600, "images/pnj/opiter.png", "images/pnj/bulles/3/bulleopiter.png"));
				listPNJ.add(new PNJ(2764, 1156, "images/pnj/aulus.png", "images/pnj/bulles/3/bulleaulus.png"));
				
				
				// INIT DES OBSTACLES
				listObstacles.add(new Obstacle(1664, 2900, 64, 128, "images/obstacles/garde00.png"));
				listObstacles.add(new Obstacle(1900, 0, 512, 256, "images/obstacles/mairie00.png"));
				listObstacles.add(new Obstacle(500, 1200, 256, 256, "images/obstacles/maison00.png"));
				listObstacles.add(new Obstacle(1900, 1800, 256,256,"images/obstacles/maison00.png"));
				listObstacles.add(new Obstacle(2600, 2000, 256, 256, "images/obstacles/maison00.png"));
				listObstacles.add(new Obstacle(600, 1900, 256, 256, "images/obstacles/ruine00.png"));
				listObstacles.add(new Obstacle(2700, 900, 256, 256, "images/obstacles/ruine00.png"));
				listObstacles.add(new Obstacle(1000,  700, 256, 128, "images/obstacles/etale00.png"));
				listObstacles.add(new Obstacle(1500, 2300, 256, 128, "images/obstacles/etale00.png"));
				listObstacles.add(new Obstacle(2800, 50, 256, 256, "images/obstacles/maison00.png"));
				//listObstacles.add(new Obstacle(2100, 2400, 256, 256, "images/obstacles/ruine00.png"));
				listObstacles.add(new Obstacle(100, 2300, 256, 256, "images/obstacles/maison00.png"));
				
				
				listObstacles.add(new Obstacle(1200, 1200, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1232,1200, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1296,1200, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1360,1200, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1424,1200, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1488,1200, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1552,1200, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1616,1200, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1680, 1200, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1200, 1264, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1200, 1328, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1200, 1392, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1200, 1456, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1200, 1520, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1200, 1584, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1200, 1648, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1680, 1264, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1680, 1328, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1680, 1392, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1680, 1456, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1680, 1520, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1680, 1584, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1680, 1648, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(1232,1680, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1296,1680, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1360,1680, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1424,1680, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1488,1680, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1552,1680, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(1616,1680, 64, 32, "images/obstacles/banc00.png"));
				
				listObstacles.add(new Obstacle(150, 100, 256, 256, "images/obstacles/fanum00.png"));
			}
			
			if(premierTour && cine == -4){
				perso = new Perso(3000,400);
				
				listEnnemis = new ArrayList();
				listPNJ = new ArrayList();
				listObstacles = new ArrayList();
				
				//INIT DES ENNEMIS
				listEnnemis.add(new Epeiste(3000, 1600));
				listEnnemis.add(new Epeiste(2700, 1800));
				listEnnemis.add(new Epeiste(2200, 1500));
				listEnnemis.add(new Epeiste(2200, 50));
				listEnnemis.add(new Lancier(2000, 500, false));
				listEnnemis.add(new Archer(1600, 300, false));
				listEnnemis.add(new Archer(1400, 700, false));
				listEnnemis.add(new Epeiste(1000, 1200));
				listEnnemis.add(new Epeiste(1200, 600));
				listEnnemis.add(new Epeiste(600, 300));
				listEnnemis.add(new Archer(100, 400, true));
				listEnnemis.add(new Epeiste(300, 1300));
				listEnnemis.add(new Lancier(100, 1600, true));
				listEnnemis.add(new Epeiste(500, 1900));
				listEnnemis.add(new Epeiste(1000, 2300));
				listEnnemis.add(new Epeiste(2200, 2700));
				listEnnemis.add(new Archer(2500, 3000, false));
				
				//INIT DES PNJ
				
				listPNJ.add(new PNJ(2232, 400, "images/pnj/aulus.png", "images/pnj/bulles/4/bulleaulus.png"));
				listPNJ.add(new PNJ(2296, 400, "images/pnj/tertius.png", "images/pnj/bulles/4/bulletertius.png"));
				
				listPNJ.add(new PNJ(296, 261, "images/pnj/vercingetorix.png", "images/pnj/bulles/4/bullevercingetorix.png"));
				listPNJ.add(new PNJ(424, 293, "images/pnj/melisso.png", "images/pnj/bulles/4/bullemelisso.png"));
				listPNJ.add(new PNJ(168, 293, "images/pnj/carvos.png", "images/pnj/bulles/4/bullecarvos.png"));
				
				listPNJ.add(new PNJ(360, 1668, "images/pnj/hostus.png", "images/pnj/bulles/4/bullehostus.png"));
				//listPNJ.add(new PNJ(720, 2630, "images/pnj/labienus.png", "images/pnj/bulles/4/bullelabienus.png"));
				listPNJ.add(new PNJ(150, 2350, "images/pnj/opiter.png", "images/pnj/bulles/4/bulleopiter.png"));
				listPNJ.add(new PNJ(1750, 2700, "images/pnj/marcus.png", "images/pnj/bulles/4/bullemarcus.png"));
				listPNJ.add(new PNJ(2550, 2600, "images/pnj/publius.png", "images/pnj/bulles/4/bullepublius.png"));
				listPNJ.add(new PNJ(2870, 2700, "images/pnj/cesarnarrateur.png", "images/pnj/bulles/4/bullecesar.png"));
				
				//INIT DES OBSTACLES
				
				listObstacles.add(new Obstacle(2900, 50, 256, 256, "images/obstacles/fanum00.png"));
				listObstacles.add(new Obstacle(2100, 150, 256, 256, "images/obstacles/maison00.png"));
				listObstacles.add(new Obstacle(2400, 750, 256, 256, "images/obstacles/maison00.png"));
				listObstacles.add(new Obstacle(1400, 350, 256, 256, "images/obstacles/maison00.png"));
				listObstacles.add(new Obstacle(1700, 950, 256, 256, "images/obstacles/maison00.png"));
				
				listObstacles.add(new Obstacle(2600, 1400, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(2664, 1400, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(2728, 1400, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(2792, 1400, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(2600, 1432, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(2600, 1496, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(2600, 1560, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(2824, 1432, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(2824, 1496, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(2824, 1560, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(2600, 1624, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(2664, 1624, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(2728, 1624, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(2792, 1624, 64, 32, "images/obstacles/banc00.png"));
				
				listObstacles.add(new Obstacle(1100, 5, 256, 128, "images/obstacles/etale00.png"));
				listObstacles.add(new Obstacle(1200, 1000, 256, 128, "images/obstacles/charette00.png"));
				
				listObstacles.add(new Obstacle(200, 20, 256, 256, "images/obstacles/tente03.png"));
				listObstacles.add(new Obstacle(750, 600, 256, 192, "images/obstacles/tente04.png"));
				listObstacles.add(new Obstacle(5, 1000, 128, 256, "images/obstacles/charette01.png"));
				
				listObstacles.add(new Obstacle(430, 1600, 64, 128, "images/obstacles/garde00.png"));
				
				listObstacles.add(new Obstacle(500, 2400, 512, 256, "images/obstacles/tente00.png"));
				listObstacles.add(new Obstacle(740, 2756, 32, 128, "images/obstacles/banniere01.png"));
				listObstacles.add(new Obstacle(1200, 2700, 256, 256, "images/obstacles/tente01.png"));
				listObstacles.add(new Obstacle(200, 2900, 256, 256, "images/obstacles/tente02.png"));
				listObstacles.add(new Obstacle(100, 2100, 256, 256, "images/obstacles/tente02.png"));
				
				listObstacles.add(new Obstacle(1850, 2635, 256, 128, "images/obstacles/charette00.png"));
				
				listObstacles.add(new Obstacle(2650, 2450, 512, 256, "images/obstacles/tente00.png"));
				listObstacles.add(new Obstacle(2750, 2578, 32, 128, "images/obstacles/banniere00.png"));
				listObstacles.add(new Obstacle(3025, 2578, 32, 128, "images/obstacles/banniere00.png"));
				
				listObstacles.add(new Obstacle(2000, 1800, 64, 64, "images/obstacles/casque00.png"));
				
			}
			
			if(premierTour && cine == -5){
				perso = new Perso(2700, 2800);
				
				listEnnemis = new ArrayList();
				listPNJ = new ArrayList();
				listObstacles = new ArrayList();
				
				//INIT DES ENNEMIS
				
				//Chemin de droite
				listEnnemis.add(new Epeiste(2700, 2000));
				listEnnemis.add(new Epeiste(2300, 2000));
				listEnnemis.add(new Epeiste(2500, 1900));
				listEnnemis.add(new Archer(2500, 1700, true));
				listEnnemis.add(new Lancier(2800, 1600, false));
				listEnnemis.add(new Lancier(2200, 1600, false));
				listEnnemis.add(new Epeiste(2300, 1400));
				listEnnemis.add(new Epeiste(2600, 1500));
				listEnnemis.add(new Archer(2200, 1300, false));
				listEnnemis.add(new Archer(2800, 1300, false));
				listEnnemis.add(new Epeiste(2100, 1100));
				listEnnemis.add(new Epeiste(2300, 1000));
				listEnnemis.add(new Epeiste(2800, 1000));
				listEnnemis.add(new Lancier(1900, 800, true));
				listEnnemis.add(new Epeiste(2500, 800));
				listEnnemis.add(new Epeiste(3000, 600));
				listEnnemis.add(new Epeiste(1600, 500));
				listEnnemis.add(new Epeiste(2000, 400));
				
				//Village
				listEnnemis.add(new Epeiste(200, 2700));
				listEnnemis.add(new Epeiste(900, 2600));
				listEnnemis.add(new Lancier(500, 2300, false));
				listEnnemis.add(new Archer(300, 1900, true));
				listEnnemis.add(new Archer(900, 1800, true));
				listEnnemis.add(new Epeiste(1400, 1900));
				listEnnemis.add(new Lancier(300, 1600, false));
				listEnnemis.add(new Epeiste(800, 1500));
				listEnnemis.add(new Archer(400, 1100, true));
				listEnnemis.add(new Epeiste(900, 1100));
				listEnnemis.add(new Epeiste(1300, 1300));
				listEnnemis.add(new Epeiste(500, 900));
				listEnnemis.add(new Epeiste(746, 900));
				listEnnemis.add(new Archer(1200, 800, false));
				listEnnemis.add(new Lancier(200, 700, true));
				listEnnemis.add(new Epeiste(500, 100));
				listEnnemis.add(new Epeiste(800, 300));
				listEnnemis.add(new Epeiste(1000, 350));
				
				//INIT DES PNJ
				listPNJ.add(new PNJ(2900, 2600, "images/pnj/cesar.png", "images/pnj/bulles/5/bullecesar.png"));
				listPNJ.add(new PNJ(2100, 2400, "images/pnj/publius.png", "images/pnj/bulles/5/bullepublius.png"));
				listPNJ.add(new PNJ(1850, 1550, "images/pnj/aulus.png", "images/pnj/bulles/5/bulleaulus.png"));
				listPNJ.add(new PNJ(1100, 2600, "images/pnj/opiter.png", "images/pnj/bulles/5/bulleopiter.png"));
				listPNJ.add(new PNJ(200, 2550, "images/pnj/ceidio.png", "images/pnj/bulles/5/bulleceidio.png"));
				listPNJ.add(new PNJ(1100, 580, "images/pnj/marcus.png", "images/pnj/bulles/5/bullemarcus.png"));
				listPNJ.add(new PNJ(623, 800, "images/pnj/vercingetorix.png", "images/pnj/bulles/5/bullevercingetorix.png"));
				listPNJ.add(new PNJ(1600, 800, "images/pnj/hostus.png", "images/pnj/bulles/5/bullehostus.png"));
				listPNJ.add(new PNJ(2400, 50, "images/pnj/labienus.png", "images/pnj/bulles/5/bullelabienus.png"));
				listPNJ.add(new PNJ(2200, 200, "images/pnj/sextus.png", "images/pnj/bulles/5/bullesextus.png"));
				listPNJ.add(new PNJ(2700, 100, "images/pnj/tertius.png", "images/pnj/bulles/5/bulletertius.png"));
				listPNJ.add(new PNJ(400, 200, "images/pnj/carvos.png", "images/pnj/bulles/5/bullecarvos.png"));
				
				
				//INIT DES OBSTACLES
				listObstacles.add(new Obstacle(150, 100, 256, 256, "images/obstacles/fanum00.png"));
				listObstacles.add(new Obstacle(400, 500, 512, 256, "images/obstacles/mairie00.png"));
				listObstacles.add(new Obstacle(1152, 512, 256, 128, "images/obstacles/etale00.png"));
				listObstacles.add(new Obstacle(100, 2300, 256, 256, "images/obstacles/maison00.png"));
				listObstacles.add(new Obstacle(1200, 1400, 256, 256, "images/obstacles/maison00.png"));
				listObstacles.add(new Obstacle(550, 1100, 256, 256, "images/obstacles/maison00.png"));
				listObstacles.add(new Obstacle(500, 1700, 256, 256, "images/obstacles/maison00.png"));
				listObstacles.add(new Obstacle(1300, 950, 128, 256, "images/obstacles/charette01.png"));
				listObstacles.add(new Obstacle(800, 50, 256, 128, "images/obstacles/charette00.png"));
				listObstacles.add(new Obstacle(1000, 2100, 256, 128, "images/obstacles/charette00.png"));
				
				listObstacles.add(new Obstacle(500, 2700, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(564, 2700, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(628, 2700, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(692, 2700, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(500, 2732, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(500, 2796, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(500, 2860, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(724, 2732, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(724, 2796, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(724, 2860, 32, 64, "images/obstacles/banc01.png"));
				listObstacles.add(new Obstacle(500, 2924, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(564, 2924, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(628, 2924, 64, 32, "images/obstacles/banc00.png"));
				listObstacles.add(new Obstacle(692, 2924, 64, 32, "images/obstacles/banc00.png"));
				
				listObstacles.add(new Obstacle(3000, 2500, 128, 256, "images/obstacles/charette01.png"));
				listObstacles.add(new Obstacle(1800, 1400, 256, 128, "images/obstacles/charette00.png"));
				listObstacles.add(new Obstacle(2300, 700, 128, 256, "images/obstacles/charette01.png"));
				listObstacles.add(new Obstacle(2800, 200, 256, 128, "images/obstacles/charette00.png"));
			}
			
			// AFFICHER MAP ET PERSO
			map.afficherMap(g, this.perso, this);
			isExited = perso.isExited(this.map, this);
			
			// GESTION / AFFICHAGE DES OBSTACLES
			for(int i = 0; i < listObstacles.size(); i++){
				listObstacles.get(i).afficherObstacle(g, perso, this);
			}
			
			perso.afficherPerso(g,this);
			perso.seDeplacer(this.map, this);
			
			// GESTION DES PNJ
			for(int i = 0; i < listPNJ.size(); i++){
				listPNJ.get(i).affichePNJ(g, perso, this);
				listPNJ.get(i).bloquePerso(g, perso, this);
			}			
						
			// GESTION DES ENNEMIS
			for(int i = 0; i< listEnnemis.size(); i++){
				listEnnemis.get(i).afficherEnnemi(g, this.perso, this);
				listEnnemis.get(i).depEnnemis(perso, map, this);
				if(perso.invincible == 0){listEnnemis.get(i).collision(perso, map, this);}
				if(listEnnemis.get(i).getClass() == archerTest.getClass()){
					((Archer)(listEnnemis.get(i))).fleche.isRunning(map, this);
					if(perso.invincible == 0){((Archer)(listEnnemis.get(i))).fleche.collision(perso, map, this);}
					((Archer)(listEnnemis.get(i))).fleche.afficherFleche(g, perso, this);
				}
			}
			
			
			// TIRS DU PERSO
			if(this.shot == 1){
				if(perso.spriteShot == 0){perso.spriteShot = 1;}
				//System.out.println(perso.spriteShot);
				for(int i = 0; i< listEnnemis.size(); i++){
					perso.shot(listEnnemis.get(i), this.map, this);
					if(listEnnemis.get(i).life<=0){listEnnemis.remove(i);}
				}
				this.shot = 2;
			} 
			
			if(perso.invincible > 0){perso.invincible++;}
			if(perso.invincible == 50){perso.invincible = 0;}
			
			g.drawImage(kit.getImage("images/commande00.png"), 0, this.getHeight()-150, this);
			g.drawImage(kit.getImage("images/commande01.png"), this.getWidth()-250, this.getHeight()-100, this);
			
			if(cptSprites<60){cptSprites++;}
			else{cptSprites = 0;}
			
			// GESTION DE LA MUSIQUE
			if(cine == -1){
				if(cptMusic < 12000){cptMusic++;}
				else{map.audio00 = new Audio(); map.audio00.start(); cptMusic = 0;}
			} else if(cine == -2){
				if(cptMusic < 17000){cptMusic++;}
				else{map.audio00 = new Audio(); map.audio00.choix=2; map.audio00.start(); cptMusic = 0;}
			} else if(cine == -3){
				if(cptMusic < 9500){cptMusic++;}
				else{map.audio00 = new Audio(); map.audio00.start(); cptMusic = 0;}
			} else if(cine == -4){
				if(cptMusic < 15000){cptMusic++;}
				else{map.audio00 = new Audio(); map.audio00.choix = 3; map.audio00.start(); cptMusic = 0;}
			}
			else if(cine == -5){
				if(cptMusic < 9500){ cptMusic++;}
				else{map.audio00 = new Audio(); map.audio00.choix = 4; map.audio00.start(); cptMusic = 0;}
			}
			
			
			premierTour = false;
			
		}
		
		
		// PERSO MORT
		else if(perso.life<=0){
			map.audio00.stop();
			g.setColor(Color.red);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			Toolkit kit = Toolkit.getDefaultToolkit();
			Image me00, retry00;
			retry00 = kit.getImage("images/retry00.png");
			if(cptDead<200){me00 = kit.getImage("images/me/me00.png");}
			else{
				if(cptDead == 200){
					perso.audio = new AudioPerso();
					perso.audio.chute = true;
					perso.audio.start();
				}
				me00 = kit.getImage("images/me/me14.png");
				Image gameover = kit.getImage("images/gameover.png");
				g.drawImage(gameover, (getWidth()/2)-32-240, (this.getHeight()/2)-32-128, this);
				g.drawImage(retry00,(getWidth()/2)-32-250, (this.getHeight()/2)-32+128, this);
				
				if(this.espace == 1){
					this.espace = 0;
					perso.life=3;
					premierTour = true;
					cptMusic = 17000;
					cptDead = 0;
					if(cine == -1){cine = 0; film = 6;}
					if(cine == -2){cine = 2; film = 8;}
					if(cine == -3){cine = 4; film = 5;}
					if(cine == -4){cine = 6; film = 5;}
					if(cine == -5){cine = 8; film = 5;}
				}
			}
			g.drawImage(me00, (this.getWidth()/2)-32, (this.getHeight()/2)-32, this);
		
	
			cptDead++;
		}
		
		
		
		// TEMPO DU JEU ET REPAINT
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	
	
	
	public Perso getPerso(){
		return this.perso;
	}




}