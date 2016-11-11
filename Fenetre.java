import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class Fenetre extends JFrame  implements KeyListener{
	 
	Panneau pan = new Panneau();
	
	public Fenetre(){
		this.setTitle("Historic Games : La guerre des Gaules");
		this.setSize(1400,1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setContentPane(pan);
		this.addKeyListener(this);
		this.setVisible(true);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		// CODE DU D
		if(arg0.getKeyCode() == 68){
			pan.moveH = 1;
			pan.downD=true;
			if(pan.getPerso().etat==-1){pan.getPerso().etat=1;}
		}
		
		// CODE DU Q
		if(arg0.getKeyCode() == 81){
			pan.moveH = -1;
			pan.downQ=true;
			if(pan.getPerso().etat==1){pan.getPerso().etat=-1;}
		}
		
		// CODE DU Z
		if(arg0.getKeyCode() == 90){
			pan.moveV = 1;
			pan.downZ=true;
			if(pan.getPerso().etat==-2){pan.getPerso().etat=2;}
		}
		
		// CODE DU S
		if(arg0.getKeyCode() == 83){
			pan.moveV = -1;
			pan.downS=true;
			if(pan.getPerso().etat==2){pan.getPerso().etat=-2;}
		}
		
		// CODE DE L'ESPACE
		if(arg0.getKeyCode() == 32){
			if(pan.shot == 0){pan.shot=1; pan.espace=1;}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 68){
			if(!pan.downQ){pan.moveH=0;}
			else{pan.moveH=-1;}
			pan.downD=false;
			if(pan.getPerso().etat == 1){pan.getPerso().etat = 0;}
		}
		
		if(arg0.getKeyCode() == 81){
			if(!pan.downD){pan.moveH=0;}
			else{pan.moveH=1;}
			pan.downQ=false;
			if(pan.getPerso().etat == -1){pan.getPerso().etat = 0;}
		}
		
		if(arg0.getKeyCode() == 90){
			if(!pan.downS){pan.moveV=0;}
			else{pan.moveV=-1;}
			pan.downZ=false;
			if(pan.getPerso().etat == 2){pan.getPerso().etat = 0;}
		}
		
		if(arg0.getKeyCode() == 83){
			if(!pan.downZ){pan.moveV=0;}
			else{pan.moveV=1;}
			pan.downS=false;
			if(pan.getPerso().etat == -2){pan.getPerso().etat = 0;}
		}
		
		if(arg0.getKeyCode() == 32){
			pan.shot = 0;
			pan.espace=0;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}