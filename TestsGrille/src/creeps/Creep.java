package creeps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import plateau.Case;
import plateau.Plateau;

public class Creep {
	
	private int posX;
	private int posY;
	private int vitesse;	
	
	private int nodeCourant;
	private int nodeProchain;
	private int nodeDestination;
		
	public int getNodeCourant() {
		return nodeCourant;
	}

	public void setNodeCourant(int nodeCourant) {
		this.nodeCourant = nodeCourant;
	}

	public int getNodeProchain() {
		return nodeProchain;
	}

	public void setNodeProchain(int nodeProchain) {
		this.nodeProchain = nodeProchain;
	}

	public int getNodeDestination() {
		return nodeDestination;
	}

	public void setNodeDestination(int nodeDestination) {
		this.nodeDestination = nodeDestination;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Creep (Plateau plateau) {
		
		this.nodeDestination = 1460;
		this.nodeProchain = -1;
		this.nodeCourant = 660;		
		
		this.posX = plateau.getTabCases()[this.nodeCourant].getPosX();
		this.posY = plateau.getTabCases()[this.nodeCourant].getPosY();		
		
		this.vitesse = 1;
		
	}
	
	public void afficher (Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillOval(this.getPosX()-4, this.getPosY()-4, 8, 8);
		
	}
	
	public void comportement (Plateau plateau) {
		
		
		
	}
		
	private void deplacement (Case prochaineCase) {
		
		int xT = 0;
		int yT = 0;
		
		//gestion de X
		if (this.getPosX() < prochaineCase.getPosX()) {
			
			xT = this.getVitesse();
			
		}
		
		if (this.getPosX() > prochaineCase.getPosX()) {
			
			xT = - this.getVitesse();
			
		}
		
		if (this.getPosX() == prochaineCase.getPosX()) {
			
			xT = 0;
			
		}
		
		//gestion de Y
		if (this.getPosY() < prochaineCase.getPosY()) {
			
			yT = this.getVitesse();
			
		}
		
		if (this.getPosY() > prochaineCase.getPosY()) {
			
			yT = - this.getVitesse();
			
		}
		
		if (this.getPosY() == prochaineCase.getPosY()) {
			
			yT = 0;
			
		}
		
		this.setPosX(this.getPosX() + xT);
		this.setPosY(this.getPosY() + yT);
		
	}

}
