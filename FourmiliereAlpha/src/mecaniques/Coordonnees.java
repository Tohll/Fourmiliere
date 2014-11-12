package mecaniques;


/**
 * <b>Coordonnees.java</b> <br><br>
 * 
 * Classe permettant de creer un Objet de type Coordonnees pouvant stocker deux entiers.
 * 
 * @since 9 nov. 2014
 * @version 0.1 
 * @author Charbel FOUREL
 *
 */
public class Coordonnees {

	/**
	 * Index de ligne
	 */
	private Integer ligne;
	/**
	 * Index de colonne
	 */
	private Integer colonne;
	
	public Integer getLigne() {
		return ligne;
	}

	public void setLigne(Integer ligne) {
		this.ligne = ligne;
	}

	public Integer getColonne() {
		return colonne;
	}

	public void setColonne(Integer colonne) {
		this.colonne = colonne;
	}

	/**
	 * Initialise un bloc de coordonnees vide
	 */
	public Coordonnees () {
		
		this.ligne = null;
		this.colonne = null;
		
	}
	
	/**
	 * Initialise un bloc de coordonnees avec les valeurs de ligne et colonne
	 * @param int ligne
	 * @param int colonne
	 */
	public Coordonnees (int ligne , int colonne) {
		
		this.ligne = ligne;
		this.colonne = colonne;
		
	}
	
}
