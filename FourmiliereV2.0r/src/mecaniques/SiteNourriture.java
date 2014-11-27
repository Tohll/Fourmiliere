/**
 * 
 */
package mecaniques;

import java.util.Random;

/**
 * La classe <b>SiteNourriture.java</b>
 * @author Seldan
 *
 */
public class SiteNourriture {

	private static final NourritureAbstract [] TAB_NOURRITURE = {new Sucre(), new Viande(), new Herbe()};	
	private static int nbrSite = 1;
	private static int distance = 1;
	
	private boolean vide;
	private NourritureAbstract contenu;
	private int stockNourriture;
	private String nom;
	private int position;
	
	/**
	 * Accesseur de l'attribut: <i>position</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>position</i>
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Accesseur de l'attribut: <i>nom</i>.
	 * @return la valeur de l'attribut <b>String</b>: <i>nom</i>
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Accesseur de l'attribut: <i>stockNourriture</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>stockNourriture</i>
	 */
	public int getStockNourriture() {
		return stockNourriture;
	}

	/**
	 * Mutateur de l'attribut: <i>stockNourriture.</i>
	 * @param stockNourriture de type <b>int</b>, définit la valeur de l'attribut: <i>stockNourriture.</i>
	 */
	public void setStockNourriture(int stockNourriture) {
		this.stockNourriture = stockNourriture;
	}

	/**
	 * Accesseur de l'attribut: <i>contenu</i>.
	 * @return la valeur de l'attribut <b>NourritureAbstract</b>: <i>contenu</i>
	 */
	public NourritureAbstract getContenu() {
		return contenu;
	}
	
	/**
	 * Accesseur de l'attribut: <i>vide</i>.
	 * @return la valeur de l'attribut <b>boolean</b>: <i>vide</i>
	 */
	public boolean isVide() {
		return vide;
	}

	/**
	 * Mutateur de l'attribut: <i>vide.</i>
	 * @param vide de type <b>boolean</b>, définit la valeur de l'attribut: <i>vide.</i>
	 */
	public void setVide(boolean vide) {
		this.vide = vide;
	}

	/**
	 * Constructeur de la classe <b>SiteNourriture.java</b>:<br>
	 * Créé un objet paramétré de type <b>SiteNourriture</b>.
	 */
	public SiteNourriture () {
		
		Random rand = new Random();
		this.contenu = TAB_NOURRITURE [rand.nextInt(TAB_NOURRITURE.length)];
		
		this.nom = new String("Site n°" + nbrSite);
		nbrSite++;
		
		this.position = distance;
		distance++;
		
		this.stockNourriture = 20;
		this.vide = false;
		
	}
	
}
