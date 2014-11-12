package fourmies;

import java.awt.Graphics;
import java.util.ArrayList;

import batiments.Fourmiliere;
import plateau.CaseAbstract;
import plateau.Plateau;


/**
 * <b>FourmieAbstract.java</b><br><br>
 * 
 * Cette classe est la classe parente de toutes les classes de fourmies.<br>
 * Elle contient deux contrats: un relatif a l'affichage des fourmies, un autre relatif au comportement des fourmies.<br>
 * Elle contient aussi la methode de deplacement commune a toutes les foumies.
 * 
 * @since 10 nov. 2014
 * @version 0.1 
 * @author Charbel FOUREL
 *
 */
public abstract class FourmieAbstract {

	public static int nbrReines = 1;
	
	private String nom;
	
	private ArrayList<CaseAbstract> chemin;
	
	private int pointsDeVie;
	private int vitesse;
	private int cycle;
	
	private int marqueurChemin;
	
	private CaseAbstract nodeDestination;	
	private CaseAbstract nodeCourant;
	
	private int posX;
	private int posY;
	
	private boolean active;
	private boolean vivante;
	private boolean priseEnCharge;
	
	/**
	 * @return the priseEnCharge
	 */
	public boolean isPriseEnCharge() {
		return priseEnCharge;
	}

	/**
	 * @param priseEnCharge the priseEnCharge to set
	 */
	public void setPriseEnCharge(boolean priseEnCharge) {
		this.priseEnCharge = priseEnCharge;
	}

	/**
	 * @return the vivante
	 */
	public boolean isVivante() {
		return vivante;
	}

	/**
	 * @param vivante the vivante to set
	 */
	public void setVivante(boolean vivante) {
		this.vivante = vivante;
	}

	/**
	 * @return the cycle
	 */
	public int getCycle() {
		return cycle;
	}

	/**
	 * @param cycle the cycle to set
	 */
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return
	 */
	public ArrayList<CaseAbstract> getChemin() {
		return chemin;
	}

	/**
	 * @param chemin
	 */
	public void setChemin(ArrayList<CaseAbstract> chemin) {
		this.chemin = chemin;
	}

	/**
	 * @return
	 */
	public int getMarqueurChemin() {
		return marqueurChemin;
	}

	/**
	 * @param marqueurChemin
	 */
	public void setMarqueurChemin(int marqueurChemin) {
		this.marqueurChemin = marqueurChemin;
	}

	/**
	 * @return
	 */
	public CaseAbstract getNodeDestination() {
		return nodeDestination;
	}

	/**
	 * @param nodeDestination
	 */
	public void setNodeDestination(CaseAbstract nodeDestination) {
		this.nodeDestination = nodeDestination;
	}

	/**
	 * @return
	 */
	public CaseAbstract getNodeCourant() {
		return nodeCourant;
	}

	/**
	 * @param nodeCourant
	 */
	public void setNodeCourant(CaseAbstract nodeCourant) {
		this.nodeCourant = nodeCourant;
	}

	/**
	 * @return
	 */
	public int getPointsDeVie() {
		return pointsDeVie;
	}

	/**
	 * @param pointsDeVie
	 */
	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	/**
	 * @return
	 */
	public int getVitesse() {
		return vitesse;
	}

	/**
	 * @param vitesse
	 */
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	/**
	 * @return
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	
	/**
	 * Methode de construction de la classe abstraite, contenant l'initialisation des principaux parametres communs des fourmies.
	 * 
	 * @param nom
	 * @param x position x initiale dela fourmie
	 * @param y position y intiale de la fourmie
	 * @param vitesse vitesse de la fourmie
	 * @param pointsDeVie points de vie de la fourmie
	 */
	protected FourmieAbstract (String nom  , int vitesse , int pointsDeVie , Fourmiliere f) {
		
		this.nom = new String(nom);
		
		this.active = true;
		this.vivante = true;
		this.priseEnCharge = false;
		
		this.pointsDeVie = pointsDeVie;
		this.cycle = 0;
		
		this.nodeDestination = null;		
		this.nodeCourant = f.getPosNode();		
		
		this.posX = this.nodeCourant.getPosX();
		this.posY = this.nodeCourant.getPosY();		
		
		this.marqueurChemin = 0;
		this.chemin = null;
		this.vitesse = vitesse;
		
		f.setIndexPop(f.getIndexPop()+1);
		
	}
	
	/**
	 * A implementer des comportements specifiques a chaques fourmie
	 * @param f la fourmiliere associée a la fourmie
	 */
	public abstract void comportement (Fourmiliere f , Plateau plateau);
	
	/**
	 * A implementer pour gerer l'affichage specifique de chaque fourmie
	 * @param g Objet graphique necessaire pour le dessin
	 */
	public abstract void afficher (Graphics g);
	
	/**
	 * Gere le deplacement des fourmies entre chaques nodes
	 * @param prochaineCase La prochaine case vers laquelle la fourmie doit se deplacer
	 */
	protected void deplacement (CaseAbstract prochaineCase) {
		
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
