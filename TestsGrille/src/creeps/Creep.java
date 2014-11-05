package creeps;

import java.awt.Color;
import java.awt.Graphics;

import mecaniques.Coordonnees;
import plateau.Case;
import plateau.Plateau;

public class Creep {
	
	private int posX;
	private int posY;
	private int vitesse;	
		
	private Coordonnees nodeDestination;
	private Coordonnees nodeProchain;
	private Coordonnees nodeCourant;
	
	public Coordonnees getNodeDestination() {
		return nodeDestination;
	}

	public void setNodeDestination(Coordonnees nodeDestination) {
		this.nodeDestination = nodeDestination;
	}

	public Coordonnees getNodeProchain() {
		return nodeProchain;
	}

	public void setNodeProchain(Coordonnees nodeProchain) {
		this.nodeProchain = nodeProchain;
	}

	public Coordonnees getNodeCourant() {
		return nodeCourant;
	}

	public void setNodeCourant(Coordonnees nodeCourant) {
		this.nodeCourant = nodeCourant;
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
		
		this.nodeDestination = new Coordonnees ();
		this.nodeProchain = new Coordonnees ();
		this.nodeCourant = new Coordonnees (30 , 20);		
		
		this.posX = plateau.getTabCases()[this.nodeCourant.getLigne()][this.nodeCourant.getColonne()].getPosX();
		this.posY = plateau.getTabCases()[this.nodeCourant.getLigne()][this.nodeCourant.getColonne()].getPosY();		
		
		this.vitesse = 1;
		
	}
	
	public void afficher (Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillOval(this.getPosX()-4, this.getPosY()-4, 8, 8);
		
	}
	
	public void comportement (Plateau plateau) {
		
		
		
	}
		
	@SuppressWarnings("unused")
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
