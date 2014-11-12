package batiments;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import plateau.CaseAbstract;
import plateau.CaseNourriture;
import plateau.Plateau;
import fourmies.FourmieAbstract;
import fourmies.Reine;


/**
 * <b>Fourmiliere.java</b><br><br>
 * 
 * La fourmiliere est l'objet auquel sont rattachees les fourmies crees.<br>
 * Les attributs sont entre autre un tableau de population, le tableau des sites de nourriture connus par la fourmiliere, ainsi que differents indexs necessaires a la gestion des tableaux et des noms de population.<br>
 * Elle contient aussi un stock de nourriture incremente ou decremente par les differents elements de la population.
 * 
 * @since 9 nov. 2014
 * @version 0.1 
 * @author Charbel FOUREL
 *
 */
public class Fourmiliere {
	
	/**
	 * Compteur des fourmilieres
	 */
	public static int nbrFourmilieres = 1;
	public static final ImageIcon ICONE_FOURMILIERE = chargerIconeFourmiliere();
	
	private String nom;
	
	private JLabel icone;
	private JLabel affNourriture;
	
	private int indexPop;
	private int indexNourriture;
	private int nbrOuvrieres;
	private int nbrGuerrieres;
	private int nbrSoigneuses;
	private int nbrScouts;
	
	private Hashtable<Integer, FourmieAbstract> population;
	private Hashtable<Integer , CaseNourriture> sitesNourriture;
	
	private int stockNourriture;
	
	private CaseAbstract posNode;
	private int posX;
	private int posY;
		
	/**
	 * @return the affNourriture
	 */
	public JLabel getAffNourriture() {
		return affNourriture;
	}

	/**
	 * @param affNourriture the affNourriture to set
	 */
	public void setAffNourriture(JLabel affNourriture) {
		this.affNourriture = affNourriture;
	}

	/**
	 * @return the icone
	 */
	public JLabel getIcone() {
		return icone;
	}

	/**
	 * @param icone the icone to set
	 */
	public void setIcone(JLabel icone) {
		this.icone = icone;
	}

	/**
	 * @return the sitesNourriture
	 */
	public Hashtable<Integer , CaseNourriture> getSitesNourriture() {
		return sitesNourriture;
	}

	/**
	 * @param sitesNourriture the sitesNourriture to set
	 */
	public void setSitesNourriture(Hashtable<Integer , CaseNourriture> sitesNourriture) {
		this.sitesNourriture = sitesNourriture;
	}

	/**
	 * @return the indexNourriture
	 */
	public int getIndexNourriture() {
		return indexNourriture;
	}

	/**
	 * @param indexNourriture the indexNourriture to set
	 */
	public void setIndexNourriture(int indexNourriture) {
		this.indexNourriture = indexNourriture;
	}

	/**
	 * @return the indexPop
	 */
	public int getIndexPop() {
		return indexPop;
	}

	/**
	 * @param indexPop the indexPop to set
	 */
	public void setIndexPop(int indexPop) {
		this.indexPop = indexPop;
	}

	/**
	 * @return the population
	 */
	public Hashtable<Integer, FourmieAbstract> getPopulation() {
		return population;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(Hashtable<Integer, FourmieAbstract> population) {
		this.population = population;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the nbrOuvrieres
	 */
	public int getNbrOuvrieres() {
		return nbrOuvrieres;
	}

	/**
	 * @param nbrOuvrieres the nbrOuvrieres to set
	 */
	public void setNbrOuvrieres(int nbrOuvrieres) {
		this.nbrOuvrieres = nbrOuvrieres;
	}

	/**
	 * @return the nbrGuerrieres
	 */
	public int getNbrGuerrieres() {
		return nbrGuerrieres;
	}

	/**
	 * @param nbrGuerrieres the nbrGuerrieres to set
	 */
	public void setNbrGuerrieres(int nbrGuerrieres) {
		this.nbrGuerrieres = nbrGuerrieres;
	}

	/**
	 * @return the nbrSoigneuses
	 */
	public int getNbrSoigneuses() {
		return nbrSoigneuses;
	}

	/**
	 * @param nbrSoigneuses the nbrSoigneuses to set
	 */
	public void setNbrSoigneuses(int nbrSoigneuses) {
		this.nbrSoigneuses = nbrSoigneuses;
	}

	/**
	 * @return the nbrScouts
	 */
	public int getNbrScouts() {
		return nbrScouts;
	}

	/**
	 * @param nbrScouts the nbrScouts to set
	 */
	public void setNbrScouts(int nbrScouts) {
		this.nbrScouts = nbrScouts;
	}

	/**
	 * @return the stockNourriture
	 */
	public int getStockNourriture() {
		return stockNourriture;
	}

	/**
	 * @param stockNourriture the stockNourriture to set
	 */
	public void setStockNourriture(int stockNourriture) {
		this.stockNourriture = stockNourriture;
	}

	/**
	 * @return the posNode
	 */
	public CaseAbstract getPosNode() {
		return posNode;
	}

	/**
	 * @param posNode the posNode to set
	 */
	public void setPosNode(CaseAbstract posNode) {
		this.posNode = posNode;
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
	 * Constructeur de la fourmiliere.
	 * 
	 * @param posnode Le node auquel est liee la fourmiliere.
	 * @param plateau Le plateau sur lequel cette fourmiliere sera affichee.
	 */
	public Fourmiliere (CaseAbstract posnode , Plateau plateau) {
		
		this.nom = new String("" + nbrFourmilieres);
		nbrFourmilieres++;
		
		this.population = new Hashtable<>();
		this.sitesNourriture = new Hashtable<>();
		
		this.nbrGuerrieres = 1;
		this.nbrOuvrieres = 1;
		this.nbrSoigneuses = 1;
		this.nbrScouts = 1;
		
		this.indexPop = 0;
		
		this.stockNourriture = 200;
		
		this.icone = new JLabel(ICONE_FOURMILIERE);
		this.affNourriture = new JLabel("Stock:" + this.stockNourriture);
		
		plateau.add(this.affNourriture);
		plateau.add(this.icone);
		
		this.affNourriture.setFont(new Font("Arial" , Font.BOLD , 9));
		this.affNourriture.setForeground(Color.white);				
		
		this.posNode = posnode;
		this.posX = this.posNode.getPosX();
		this.posY = this.posNode.getPosY();	
		
		this.population.put(this.indexPop, new Reine(true, this));
		
		Plateau.indexFourmilieres++;
		
	}
	
	/**
	 * Gere le chargement pour le programme d'un fichier image externe.
	 * 
	 * @return Une ImageIcon
	 */
	private static ImageIcon chargerIconeFourmiliere () {
		
		ImageIcon icone = null;
		
		try {
			
			icone = new ImageIcon("img/Bat_F30x30.png");
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,
				    "Erreur",
				    "Probleme fichier Sprite",
				    JOptionPane.WARNING_MESSAGE);
			
			System.exit(0);
			
		}		
		
		return icone;
		
	}
	
	/**
	 * Methode contenant les parametres d'affichage des elements graphiques de la fourmiliere.
	 * 
	 * @param g L'objet graphique utilise pour le dessin.
	 */
	public void afficher (Graphics g) {
		
		this.getAffNourriture().setText("Stock:" + this.stockNourriture);
		
		this.getIcone().setBounds(this.getPosX()-12 , this.getPosY()-10 , this.getIcone().getPreferredSize().width , this.getIcone().getPreferredSize().height);
		this.getAffNourriture().setBounds(this.getPosX()-14 , this.getPosY()+10 , this.getAffNourriture().getPreferredSize().width , this.getAffNourriture().getPreferredSize().height);
		
	}

}
