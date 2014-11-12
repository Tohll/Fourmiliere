package plateau;

import java.awt.Graphics;
import java.util.ArrayList;

import mecaniques.Coordonnees;



/**
 * CaseAbstract.java
 * @since 9 nov. 2014
 * @version 0.1 
 * @author Charbel FOUREL
 *
 */
public abstract class CaseAbstract {
	
	private int posX;
	private int posY;
	private int g;
	private int h;
	private int f;
	private int distanceEntre;
	private CaseAbstract parent;	
	private Coordonnees posNode;
	
	private ArrayList<CaseAbstract> chemin;
		
	private boolean obstacle;
	
	private int[] tabX;
	private int[] tabY;
	
	/**
	 * @return the chemin
	 */
	public ArrayList<CaseAbstract> getChemin() {
		return chemin;
	}

	/**
	 * @param chemin the chemin to set
	 */
	public void setChemin(ArrayList<CaseAbstract> chemin) {
		this.chemin = chemin;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the obstacle
	 */
	public boolean isObstacle() {
		return obstacle;
	}

	/**
	 * @param obstacle the obstacle to set
	 */
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @return the g
	 */
	public int getG() {
		return g;
	}

	/**
	 * @param g the g to set
	 */
	public void setG(int g) {
		this.g = g;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(int h) {
		this.h = h;
	}

	/**
	 * @return the f
	 */
	public int getF() {
		return f;
	}

	/**
	 * @param f the f to set
	 */
	public void setF(int f) {
		this.f = f;
	}

	/**
	 * @return the distanceEntre
	 */
	public int getDistanceEntre() {
		return distanceEntre;
	}

	/**
	 * @param distanceEntre the distanceEntre to set
	 */
	public void setDistanceEntre(int distanceEntre) {
		this.distanceEntre = distanceEntre;
	}

	/**
	 * @return the parent
	 */
	public CaseAbstract getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(CaseAbstract parent) {
		this.parent = parent;
	}

	/**
	 * @return the posNode
	 */
	public Coordonnees getPosNode() {
		return posNode;
	}

	/**
	 * @param posNode the posNode to set
	 */
	public void setPosNode(Coordonnees posNode) {
		this.posNode = posNode;
	}

	/**
	 * @return the tabX
	 */
	public int[] getTabX() {
		return tabX;
	}

	/**
	 * @param tabX the tabX to set
	 */
	public void setTabX(int[] tabX) {
		this.tabX = tabX;
	}

	/**
	 * @return the tabY
	 */
	public int[] getTabY() {
		return tabY;
	}

	/**
	 * @param tabY the tabY to set
	 */
	public void setTabY(int[] tabY) {
		this.tabY = tabY;
	}

	/**
	 * Parametres d'initialisation par defaut de tous les types de cases
	 * @param x position de x
	 * @param y position de y
	 * @param lgn index de ligne dans le tableau des cases
	 * @param col index de colonne dans le tableau des cases
	 */
	protected CaseAbstract (int x , int y , int lgn , int col , Plateau plateau) {
		
		this.tabX = new int[3];
		this.tabY = new int[3];
		
		this.setObstacle(false);
		this.posNode = new Coordonnees(lgn , col);		
		this.posX = x;
		this.posY = y;
		this.g = this.f = this.h = this.distanceEntre = 0;
		this.parent = this;
		
		this.setChemin(null);
		
	}
	
	/**
	 * Parametres d'affichages de la case
	 * @param x position absolue de la case
	 * @param y position absolue de la case
	 * @param width taille de la case
	 * @param height taille de la case
	 * @param g Objet graphique utilisé pour le dessin
	 * @param plateau plateau de jeu initialisé
	 */
	public abstract void afficher (int x , int y , int width , int height , Graphics g , Plateau plateau);

}
