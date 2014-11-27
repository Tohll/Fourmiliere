package mecaniques;

import java.util.Hashtable;

import fourmies.FourmieAbstract;
import fourmies.Reine;

public class Fourmiliere {

	private int indexActives;	
	
	private int nbrOuvrieres;
	private int nbrGuerrieres;
	private int nbrSoigneurs;
	
	private int stockNourriture;
	
	private Hashtable<Integer, FourmieAbstract> populationInactive;
	private Hashtable<Integer, FourmieAbstract> populationActive;

	private SiteNourriture [] tabSites;
	
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
	 * Accesseur de l'attribut: <i>indexActives</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>indexActives</i>
	 */
	public int getIndexActives() {
		return indexActives;
	}

	/**
	 * Mutateur de l'attribut: <i>indexActives.</i>
	 * @param indexActives de type <b>int</b>, définit la valeur de l'attribut: <i>indexActives.</i>
	 */
	public void setIndexActives(int indexActives) {
		this.indexActives = indexActives;
	}

	/**
	 * Accesseur de l'attribut: <i>nbrOuvrieres</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>nbrOuvrieres</i>
	 */
	public int getNbrOuvrieres() {
		return nbrOuvrieres;
	}

	/**
	 * Mutateur de l'attribut: <i>nbrOuvrieres.</i>
	 * @param nbrOuvrieres de type <b>int</b>, définit la valeur de l'attribut: <i>nbrOuvrieres.</i>
	 */
	public void setNbrOuvrieres(int nbrOuvrieres) {
		this.nbrOuvrieres = nbrOuvrieres;
	}

	/**
	 * Accesseur de l'attribut: <i>nbrGuerrieres</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>nbrGuerrieres</i>
	 */
	public int getNbrGuerrieres() {
		return nbrGuerrieres;
	}

	/**
	 * Mutateur de l'attribut: <i>nbrGuerrieres.</i>
	 * @param nbrGuerrieres de type <b>int</b>, définit la valeur de l'attribut: <i>nbrGuerrieres.</i>
	 */
	public void setNbrGuerrieres(int nbrGuerrieres) {
		this.nbrGuerrieres = nbrGuerrieres;
	}

	/**
	 * Accesseur de l'attribut: <i>nbrSoigneurs</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>nbrSoigneurs</i>
	 */
	public int getNbrSoigneurs() {
		return nbrSoigneurs;
	}

	/**
	 * Mutateur de l'attribut: <i>nbrSoigneurs.</i>
	 * @param nbrSoigneurs de type <b>int</b>, définit la valeur de l'attribut: <i>nbrSoigneurs.</i>
	 */
	public void setNbrSoigneurs(int nbrSoigneurs) {
		this.nbrSoigneurs = nbrSoigneurs;
	}

	/**
	 * Accesseur de l'attribut: <i>populationInactive</i>.
	 * @return la valeur de l'attribut <b>Hashtable<Integer,FourmieAbstract></b>: <i>populationInactive</i>
	 */
	public Hashtable<Integer, FourmieAbstract> getPopulationInactive() {
		return populationInactive;
	}

	/**
	 * Accesseur de l'attribut: <i>populationActive</i>.
	 * @return la valeur de l'attribut <b>Hashtable<Integer,FourmieAbstract></b>: <i>populationActive</i>
	 */
	public Hashtable<Integer, FourmieAbstract> getPopulationActive() {
		return populationActive;
	}

	/**
	 * Accesseur de l'attribut: <i>tabSites</i>.
	 * @return la valeur de l'attribut <b>SiteNourriture[]</b>: <i>tabSites</i>
	 */
	public SiteNourriture[] getTabSites() {
		return tabSites;
	}

	/**
	 * Constructeur de la classe <b>Fourmiliere.java</b>:<br>
	 * Créé un objet paramétré de type <b>Fourmiliere</b>.
	 * @param nbrSitesNourriture
	 */
	public Fourmiliere (int nbrSitesNourriture) {
		
		this.tabSites = initTableauSites(nbrSitesNourriture);
		
		this.populationActive = new Hashtable<>();
		this.populationInactive = new Hashtable<>();
		
		this.indexActives = 0;
		
		this.populationActive.put(this.indexActives, new Reine(this, this.indexActives));
		
		this.nbrOuvrieres = 1;
		this.nbrGuerrieres = 1;
		this.nbrSoigneurs = 1;
		
		this.stockNourriture = 0;
		
	}
	
	private SiteNourriture[] initTableauSites(int nbrSites) {
		
		SiteNourriture[] tempTab = new SiteNourriture [nbrSites];
		for (int i=0 ; i<tempTab.length ; i++) {
			
			tempTab[i] = new SiteNourriture();
			
		}
		
		return tempTab;
		
	}
	
}
