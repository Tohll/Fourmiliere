package mecaniques;

/**
 * Classe container pour les coordonnees des nodes
 * @author Seldan
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
	 * @param ligne
	 * @param colonne
	 */
	public Coordonnees (int ligne , int colonne) {
		
		this.ligne = ligne;
		this.colonne = colonne;
		
	}
	
}
