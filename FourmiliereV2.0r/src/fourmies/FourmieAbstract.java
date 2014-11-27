package fourmies;

import comportements.ComportementsInterface;
import deplacements.DeplacementsInterface;
import mecaniques.Fourmiliere;

public abstract class FourmieAbstract {
	
	private ComportementsInterface comportement;
	private DeplacementsInterface deplacement;
	
	private String nom;
	
	private int vitesse;
	private int pointsDeVie;
	private int posCourante;
	private int choix;
	
	private int indexPerso;
		
	private boolean priseEnCharge;
	private boolean aller;
	
	
	/**
	 * Accesseur de l'attribut: <i>indexPerso</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>indexPerso</i>
	 */
	public int getIndexPerso() {
		return indexPerso;
	}

	/**
	 * Mutateur de l'attribut: <i>indexPerso.</i>
	 * @param indexPerso de type <b>int</b>, définit la valeur de l'attribut: <i>indexPerso.</i>
	 */
	public void setIndexPerso(int indexPerso) {
		this.indexPerso = indexPerso;
	}

	/**
	 * Accesseur de l'attribut: <i>deplacement</i>.
	 * @return la valeur de l'attribut <b>DeplacementsInterface</b>: <i>deplacement</i>
	 */
	public DeplacementsInterface getDeplacement() {
		return deplacement;
	}

	/**
	 * Mutateur de l'attribut: <i>deplacement.</i>
	 * @param deplacement de type <b>DeplacementsInterface</b>, définit la valeur de l'attribut: <i>deplacement.</i>
	 */
	public void setDeplacement(DeplacementsInterface deplacement) {
		this.deplacement = deplacement;
	}

	/**
	 * Accesseur de l'attribut: <i>aller</i>.
	 * @return la valeur de l'attribut <b>boolean</b>: <i>aller</i>
	 */
	public boolean isAller() {
		return aller;
	}

	/**
	 * Mutateur de l'attribut: <i>aller.</i>
	 * @param aller de type <b>boolean</b>, définit la valeur de l'attribut: <i>aller.</i>
	 */
	public void setAller(boolean aller) {
		this.aller = aller;
	}

	/**
	 * Accesseur de l'attribut: <i>comportement</i>.
	 * @return la valeur de l'attribut <b>ComportementsInterface</b>: <i>comportement</i>
	 */
	public ComportementsInterface getComportement() {
		return comportement;
	}	

	/**
	 * Accesseur de l'attribut: <i>nom</i>.
	 * @return la valeur de l'attribut <b>String</b>: <i>nom</i>
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Accesseur de l'attribut: <i>vitesse</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>vitesse</i>
	 */
	public int getVitesse() {
		return vitesse;
	}	

	/**
	 * Accesseur de l'attribut: <i>pointsDeVie</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>pointsDeVie</i>
	 */
	public int getPointsDeVie() {
		return pointsDeVie;
	}

	/**
	 * Mutateur de l'attribut: <i>pointsDeVie.</i>
	 * @param pointsDeVie de type <b>int</b>, définit la valeur de l'attribut: <i>pointsDeVie.</i>
	 */
	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	/**
	 * Accesseur de l'attribut: <i>posCourante</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>posCourante</i>
	 */
	public int getPosCourante() {
		return posCourante;
	}

	/**
	 * Mutateur de l'attribut: <i>posCourante.</i>
	 * @param posCourante de type <b>int</b>, définit la valeur de l'attribut: <i>posCourante.</i>
	 */
	public void setPosCourante(int posCourante) {
		this.posCourante = posCourante;
	}

	/**
	 * Accesseur de l'attribut: <i>choix</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>choix</i>
	 */
	public int getChoix() {
		return choix;
	}

	/**
	 * Mutateur de l'attribut: <i>choix.</i>
	 * @param choix de type <b>int</b>, définit la valeur de l'attribut: <i>choix.</i>
	 */
	public void setChoix(int choix) {
		this.choix = choix;
	}

	/**
	 * Accesseur de l'attribut: <i>priseEnCharge</i>.
	 * @return la valeur de l'attribut <b>boolean</b>: <i>priseEnCharge</i>
	 */
	public boolean isPriseEnCharge() {
		return priseEnCharge;
	}

	/**
	 * Mutateur de l'attribut: <i>priseEnCharge.</i>
	 * @param priseEnCharge de type <b>boolean</b>, définit la valeur de l'attribut: <i>priseEnCharge.</i>
	 */
	public void setPriseEnCharge(boolean priseEnCharge) {
		this.priseEnCharge = priseEnCharge;
	}
	
	/**
	 * Constructeur de la classe <b>FourmieAbstract.java</b>:
	 * @param fourmiliere
	 * @param nom
	 * @param pointsDeVie
	 * @param vitesse
	 * @param comportement
	 * @param deplacement
	 * @param indexPersonnel
	 */
	protected FourmieAbstract (Fourmiliere fourmiliere , String nom , int pointsDeVie , int vitesse , ComportementsInterface comportement , DeplacementsInterface deplacement, int indexPersonnel) {
				
		this.aller = true;
		
		this.comportement = comportement;
		this.deplacement = deplacement;
		
		this.nom = nom;
		this.pointsDeVie = pointsDeVie;
		this.vitesse = vitesse;
		this.posCourante = 0;
		
		this.choix = -1;
		
		this.indexPerso = indexPersonnel;
		
		fourmiliere.setIndexActives(fourmiliere.getIndexActives() + 1);
		
	}
	
	/**
	 * Doit etre implementé pour appeler le comportement de la fourmie.
	 * @param fourmiliere
	 */
	public abstract void Action (Fourmiliere fourmiliere);
	
	/**
	 * Fait perdre à la fourmie la quantité de points de vie entrée en paramètre.
	 * @param quantitePv
	 */
	public void perteDeVie (int quantitePv) {
		
		this.setPointsDeVie(this.getPointsDeVie() - quantitePv);
		
	}
	
	/**
	 * Fait se deplacer la fourmie en foncton de son comportement de déplacement.
	 */
	public void seDeplace () {
		
		this.getDeplacement().Deplacer(this);
		
	}
	
}
