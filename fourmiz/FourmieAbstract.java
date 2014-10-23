package fourmiz;

import mecaniques.Fourmiliere;

/**
 * @author Charbel FOUREL
 * @version 0.1
 *
 */
public abstract class FourmieAbstract {	
	
	private boolean estActive;
	private int position;
	private String type;
	private int pointsDeVie;
	private int vitesse;
	private int choix;
	private boolean aller;
	
	protected static int nbrOuvriere = 1;
	protected static int nbrGuerriere = 1;
	protected static int nbrSoigneur = 1;	
	
	boolean isAller() {
		return aller;
	}

	void setAller(boolean aller) {
		this.aller = aller;
	}

	public boolean isEstActive() {
		return estActive;
	}

	public void setEstActive(boolean estActive) {
		this.estActive = estActive;
	}

	public int getChoix() {
		return choix;
	}

	public void setChoix(int choix) {
		this.choix = choix;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getPointsDeVie() {
		return pointsDeVie;
	}

	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}	
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public FourmieAbstract(String type , int pv , int vitesse) {
		
		this.estActive = true;
		this.choix = -1;
		this.pointsDeVie = pv;
		this.type = type;
		this.position = 0;
		this.vitesse = vitesse;
		this.aller = true;
	}
	
	public abstract void Comportement(Fourmiliere f);
}
