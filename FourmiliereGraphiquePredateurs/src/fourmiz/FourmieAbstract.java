package fourmiz;

import java.awt.Graphics2D;

import javax.swing.JLabel;

import mecaniques.Fourmiliere;
import mecaniques.Simulation;

public abstract class FourmieAbstract {
	
	private boolean priseEnCharge;
	private boolean estActive;	
	private boolean aller;
	private boolean morte;
	
	private int posX;
	private int posY;	
	private int pointsDeVie;
	private int vitesse;
	private int choix;
	
	private String direction;
	
	//private JLabel nom;
	//private JLabel pv;
	private JLabel icone;	
	
	public JLabel getIcone() {
		return icone;
	}

	public void setIcone(JLabel icone) {
		this.icone = icone;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public boolean isMorte() {
		return morte;
	}

	public void setMorte(boolean morte) {
		this.morte = morte;
	}

	public boolean isPriseEnCharge() {
		return priseEnCharge;
	}

	public void setPriseEnCharge(boolean priseEnCharge) {
		this.priseEnCharge = priseEnCharge;
	}

	public boolean isEstActive() {
		return estActive;
	}

	public void setEstActive(boolean estActive) {
		this.estActive = estActive;
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

	/*public JLabel getNom() {
		return nom;
	}

	public void setNom(JLabel nom) {
		this.nom = nom;
	}

	public JLabel getPv() {
		return pv;
	}

	public void setPv(JLabel pv) {
		this.pv = pv;
	}*/

	public int getPointsDeVie() {
		return pointsDeVie;
	}

	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getChoix() {
		return choix;
	}

	public void setChoix(int choix) {
		this.choix = choix;
	}

	public boolean isAller() {
		return aller;
	}

	public void setAller(boolean aller) {
		this.aller = aller;
	}

	protected static int nbrOuvriere = 1;
	protected static int nbrGuerriere = 1;
	protected static int nbrSoigneur = 1;
	
	protected FourmieAbstract(String nom , int vitesse , int pv , Fourmiliere f , Simulation simu) {
		
		this.priseEnCharge = false;
		this.estActive = true;
		this.aller = true;		
		this.posX = 400;
		this.posY = 300;		
		this.direction = new String();
		this.icone = new JLabel(simu.getSpritesFourmie()[7]);
		
		this.morte = false;
		this.choix = -1;
		/*this.nom = new JLabel(nom);
		this.pv = new JLabel("PV:" + pv);*/
		this.vitesse = vitesse;
		this.pointsDeVie = pv;
		
		Fourmiliere.index++;
		
	}
	
	public abstract void afficher(Graphics2D g , Simulation simu);
	
	public abstract void comportement (Fourmiliere f , Simulation simu);
	
	public abstract void deplacement (Fourmiliere f);

}
