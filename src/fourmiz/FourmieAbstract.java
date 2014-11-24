package fourmiz;

import mecaniques.Fourmiliere;


/**
 * Classe abstraite liée a tous les types de fourmie possibles<br>
 * Contient tous les parametres de base ainsi qu'une fonction abstraite a implementer pour definir les comportements
 * @author Charbel FOUREL
 * 
 *
 */
public abstract class FourmieAbstract {	
	
	private boolean priseEnCharge;
	private boolean estActive;	//sert a verifier si une fourmie est blessée ou pas
	private int position;		//sert a positionner la fourmie
	private String type;		//sert a donner un nom a la fourmie
	private int pointsDeVie;	//sert a definir un nombre de points de vie a la fourmie
	private int vitesse;		//sert a definir une vitesse a la fourmie
	private int choix;			//sert a stocker le choix de destination de la fourmie (via l'index dans le tableau du terrain)
	private boolean aller;		//sert a voir si la fourmie est sur l'allé ou le retour
		
	protected static int nbrOuvriere = 1;	//compteur qui sert a construire le nom des fourmies ouvrieres
	protected static int nbrGuerriere = 1;	//compteur qui sert a construire le nom des fourmies guerrieres
	protected static int nbrSoigneur = 1;	//compteur qui sert a construire le nom des fourmies soigneuses
	
	public boolean isPriseEnCharge() {
		return priseEnCharge;
	}

	public void setPriseEnCharge(boolean priseEnCharge) {
		this.priseEnCharge = priseEnCharge;
	}

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

	/**
	 * Constructeur de la classe abstraite, à appeler par chaque fourmie que l'on souhaite construire.
	 * 
	 * @param type de type <b>String</b>, pour définir une partie du nom.
	 * @param pv de type <b>int</b>, pour définir le nombre de points de vie.
	 * @param vitesse de type <b>int</b>, pour définir la vitesse.
	 */
	public FourmieAbstract(String type , int pv , int vitesse , Fourmiliere f) {
				
		this.priseEnCharge = false;
		this.estActive = true;	//la fourmie commence toujours en etant active
		this.choix = -1;		//le choix est mis a -1 pour etre sur qu'il soit correctement initialisé par la methode de comportement. si ce n'etait pas le cas, la valeur renverrait une erreur lors de al consultation des tableaux.
		this.pointsDeVie = pv;	//on recupere les points de vie en entrée.
		this.type = type;		//on recupere une partie du nom.
		this.position = 0;		//0 correspond a la position de la fourmiliere dans un rapport de distance
		this.vitesse = vitesse;	//on recupere la vitesse
		this.aller = true;		//partant necessairement de la fourmiliere, on initialise a true (la fourmie est sur l'allé)
		
		f.setIndexPop(f.getIndexPop() + 1);
		
	}
	
	/**
	 * La methode Comportement est à implementer pour définir le comportement de la fourmie.
	 * 
	 * @param f de type <b>Fourmiliere</b>, la fourmiliere qui heberge la fourmie. Necessaire pour recuperer les elements relatifs a l'environement.
	 */
	public abstract void Comportement(Fourmiliere f);
}
