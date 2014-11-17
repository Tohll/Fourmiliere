package plateau;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import batiments.Fourmiliere;


/**
 * Plateau.java
 * @since 9 nov. 2014
 * @version 0.1 
 * @author Charbel FOUREL
 *
 */
@SuppressWarnings("serial")
public class Plateau extends JPanel {
		
	/*
	 * PARAMETRAGE DU PLATEAU
	 */
	public final int NBR_LIG = 60;   //Nombre de ligne du plateau
	public final int NBR_COL = 80;   //Nombre de colonees du plateau	
	public final int TAILLE_CASE_FAV = 10;   //taille des cases ** PENSER A CHANGER VALEUR F DANS PATHFINDING
	public final String FICHIER_FOND = new String("img/gazon800600.png");   //chemin vers un fichier pour l'image de fond
	/*
	 * ***********************
	 */		
	
	public static int indexFourmilieres = 0;
	
	private Image imageFond;
	
	private CaseAbstract[][] tabCases;
	private Hashtable<Integer , Fourmiliere> fourmilieresTab;
	
	/**
	 * @return the fourmilieresTab
	 */
	public Hashtable<Integer, Fourmiliere> getFourmilieresTab() {
		return fourmilieresTab;
	}

	/**
	 * @param fourmilieresTab the fourmilieresTab to set
	 */
	public void setFourmilieresTab(Hashtable<Integer, Fourmiliere> fourmilieresTab) {
		this.fourmilieresTab = fourmilieresTab;
	}

	/**
	 * @return the imageFond
	 */
	public Image getImageFond() {
		return imageFond;
	}	

	/**
	 * @return the tabCases
	 */
	public CaseAbstract[][] getTabCases() {
		return tabCases;
	}

	/**
	 * @param tabCases the tabCases to set
	 */
	public void setTabCases(CaseAbstract[][] tabCases) {
		this.tabCases = tabCases;
	}

	/**
	 * @return the nBR_LIG
	 */
	public int getNBR_LIG() {
		return NBR_LIG;
	}

	/**
	 * @return the nBR_COL
	 */
	public int getNBR_COL() {
		return NBR_COL;
	}

	/**
	 * @return the tAILLE_CASE_FAV
	 */
	public int getTAILLE_CASE_FAV() {
		return TAILLE_CASE_FAV;
	}

	/**
	 * Initialise un plateau et le remplit de maniere aleatoire d'obstacles et de site de nourriture.</br>
	 * Les parametres du plateau sont initialisés via les constantes <b>NBR_LIG, NBR_COL, FICHIER_FOND, TAILLE_CASE_FAV</b>.
	 */
	public Plateau () {
		
		this.tabCases = new CaseAbstract[NBR_LIG][NBR_COL];
		this.imageFond = chargerImageFond(FICHIER_FOND);
						
		this.setPreferredSize(new Dimension(NBR_COL*TAILLE_CASE_FAV , NBR_LIG*TAILLE_CASE_FAV));
		this.setSize(getPreferredSize());
		this.fourmilieresTab = new Hashtable<>();		
				
	}
	
	public void comportements () {
		
		Enumeration <Integer> eFourmilieres = this.getFourmilieresTab().keys();
		
		while (eFourmilieres.hasMoreElements()) {
			
			int indFourmiliere = eFourmilieres.nextElement();
			
			Enumeration <Integer> ePop = this.getFourmilieresTab().get(indFourmiliere).getPopulation().keys();
			
			while (ePop.hasMoreElements()) {
				
				int indPop = ePop.nextElement();
								
				if (!this.getFourmilieresTab().get(indFourmiliere).getPopulation().get(indPop).isVivante()) {
					
					this.getFourmilieresTab().get(indFourmiliere).getPopulation().remove(indPop);
					
				}						
				
			}
			
			ePop = this.getFourmilieresTab().get(indFourmiliere).getPopulation().keys();
			
			while (ePop.hasMoreElements()) {
				
				int indPop = ePop.nextElement();
				
				if (this.getFourmilieresTab().get(indFourmiliere).getPopulation().get(indPop) != null) {
					
					this.getFourmilieresTab().get(indFourmiliere).getPopulation().get(indPop).comportement(this.getFourmilieresTab().get(indFourmiliere), this);
					
				}			
				
			}
			
		}
				
				
		
		
	}
		
	/**
	 * @return
	 */
	public void fourmiliereInitiale () {
		
		Fourmiliere f = new Fourmiliere(this.getTabCases()[1][1] , this);		
		
		this.getFourmilieresTab().put(Plateau.indexFourmilieres, f);
		
		/*f = new Fourmiliere(this.getTabCases()[1][77], this);
		
		this.getFourmilieresTab().put(Plateau.indexFourmilieres, f);*/
		
	}
		
	/**
	 * Remplissage d'un Plateau
	 */	
	public void remplissageTabCases () {		
		
		Random rand = new Random();
		int test;
		
		for (int i=0 ; i<NBR_LIG ; i++) {
			
			for (int j=0 ; j<NBR_COL ; j++) {
								
				test = rand.nextInt(90);
				
				if (test > 1) {
					
					this.getTabCases()[i][j] = new CaseStandard((j*TAILLE_CASE_FAV)+(TAILLE_CASE_FAV/2) , (i*TAILLE_CASE_FAV)+(TAILLE_CASE_FAV/2) , i , j , this);
					
				} else if (i > 5 && i < NBR_LIG-6 && j > 5 && j < NBR_COL-6){
					
					this.getTabCases()[i][j] = new CaseNourriture((j*TAILLE_CASE_FAV)+(TAILLE_CASE_FAV/2) , (i*TAILLE_CASE_FAV)+(TAILLE_CASE_FAV/2) , i , j , this);
					
				} else {
					
					this.getTabCases()[i][j] = new CaseStandard((j*TAILLE_CASE_FAV)+(TAILLE_CASE_FAV/2) , (i*TAILLE_CASE_FAV)+(TAILLE_CASE_FAV/2) , i , j , this);
					
				}
								
			}
			
		}
				
		for (int i=1 ; i<NBR_LIG-1 ; i++) {
			
			for (int j=1 ; j<NBR_COL-1 ; j++) {
				
				test = rand.nextInt(40);
				if (test == 1) {
					
					if ( i > 2 && i < NBR_LIG-3 && j > 2 && j < NBR_COL-3) {
						
						this.getTabCases()[i][j].setObstacle(true);
						this.getTabCases()[i][j].setNourriture(false);;
						
					}					
					
					if (i-1 > 2 && i+1 < NBR_LIG-3 && j-1 > 2 && j+1 < NBR_COL-3) {
						
						this.getTabCases()[i-1][j].setObstacle(true);
						this.getTabCases()[i-1][j].setNourriture(false);
						this.getTabCases()[i+1][j].setObstacle(true);
						this.getTabCases()[i+1][j].setNourriture(false);
						this.getTabCases()[i][j+1].setObstacle(true);
						this.getTabCases()[i][j+1].setNourriture(false);
						this.getTabCases()[i][j-1].setObstacle(true);
						this.getTabCases()[i][j-1].setNourriture(false);
						
						this.getTabCases()[i-1][j-1].setObstacle(true);
						this.getTabCases()[i-1][j-1].setNourriture(false);
						this.getTabCases()[i+1][j+1].setObstacle(true);
						this.getTabCases()[i+1][j+1].setNourriture(false);
						this.getTabCases()[i-1][j+1].setNourriture(false);
						this.getTabCases()[i+1][j-1].setNourriture(false);
																		
					}					
					
				}			
				
			}
			
		}
		
		for (int i=2 ; i<NBR_LIG-2 ; i++) {
			
			for (int j=2 ; j<NBR_COL-2 ; j++) {
				
				if (this.getTabCases()[i-1][j].isObstacle() && this.getTabCases()[i][j+1].isObstacle() && this.getTabCases()[i+1][j].isObstacle() && this.getTabCases()[i][j-1].isObstacle()) {
					
					this.getTabCases()[i][j].setObstacle(true);
					
					this.getTabCases()[i-1][j].setObstacle(true);
					this.getTabCases()[i+1][j].setObstacle(true);
					this.getTabCases()[i][j+1].setObstacle(true);
					this.getTabCases()[i][j-1].setObstacle(true);
					
					this.getTabCases()[i-1][j-1].setObstacle(true);
					this.getTabCases()[i+1][j+1].setObstacle(true);
					this.getTabCases()[i-1][j+1].setObstacle(true);
					this.getTabCases()[i+1][j-1].setObstacle(true);
					
				}
				
			}			
			
		}
				
	}	
	
	/**
	 * @param fichier - de type String, chemin vers le fichier image sur le disque
	 * @return image - de type Image, image de fond du plateau
	 */
	private Image chargerImageFond (String fichier) {
		
		Image image = null;
		
		try {
			
			image = ImageIO.read(new File(fichier));
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(this,
				    "Probleme fichier de fond",
				    "Erreur",
				    JOptionPane.WARNING_MESSAGE);
			
			System.exit(0);
			
		}

		return image;
		
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
				
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(this.getImageFond(), 0 , 0 , this.getWidth() , this.getHeight() , null);
		
		for (int i=0 ; i<NBR_LIG ; i++) {
			
			for (int j=0 ; j<NBR_COL ; j++) {				
			
			this.getTabCases()[i][j].afficher(this.getTabCases()[i][j].getPosX()-(TAILLE_CASE_FAV/2), this.getTabCases()[i][j].getPosY()-(TAILLE_CASE_FAV/2), TAILLE_CASE_FAV, TAILLE_CASE_FAV, g , this);
						
			}
			
		}
		
		Enumeration <Integer> eFourmilieres = this.getFourmilieresTab().keys();
		
		while (eFourmilieres.hasMoreElements()) {
			
			int indFourmiliere = eFourmilieres.nextElement();
						
			Enumeration <Integer> ePop = this.getFourmilieresTab().get(indFourmiliere).getPopulation().keys();
			
			while (ePop.hasMoreElements()) {
				
				int indPop = ePop.nextElement();
				
				if (this.getFourmilieresTab().get(indFourmiliere).getPopulation().get(indPop) != null) {
					
					this.getFourmilieresTab().get(indFourmiliere).getPopulation().get(indPop).afficher(g);
					
				}				
				
			}
			
			this.getFourmilieresTab().get(indFourmiliere).afficher(g);
			
		}
		
	}	

}
