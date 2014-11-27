/**
 * 
 */
package mecaniques;

/**
 * La classe <b>NourritureAbstract.java</b>
 * @author Seldan
 *
 */
public abstract class NourritureAbstract {

	private int valeurNutritive;
	private String nom;
	
	/**
	 * Accesseur de l'attribut: <i>valeurNutritive</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>valeurNutritive</i>
	 */
	public int getValeurNutritive() {
		return valeurNutritive;
	}

	/**
	 * Accesseur de l'attribut: <i>nom</i>.
	 * @return la valeur de l'attribut <b>String</b>: <i>nom</i>
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Constructeur de la classe <b>NourritureAbstract.java</b>. 
	 * @param nom
	 * @param valeurNutritive
	 */
	protected NourritureAbstract (String nom, int valeurNutritive) {
		
		this.nom = nom;
		this.valeurNutritive = valeurNutritive;
		
	}
	
}
