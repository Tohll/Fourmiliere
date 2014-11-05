package plateau;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import creeps.Creep;

/**
 * Permet la creation et l'affichage d'un plateau bi-dimensionel parametrable.
 * Necessite une classe de Type Case.
 * 
 * @author Seldan
 *
 */
@SuppressWarnings("serial")
public class Plateau extends JPanel {
		
	/*
	 * PARAMETRAGE DU PLATEAU
	 */
	private final int NBR_LIG = 60;   //Nombre de ligne du plateau
	private final int NBR_COL = 80;   //Nombre de colonees du plateau	
	private final int TAILLE_CASE_FAV = 10;   //taille des cases
	private final String FICHIER_FOND = new String("img/gazon800600.png");   //chemin vers un fichier pour l'image de fond
	/*
	 * ***********************
	 */	
	
	private Creep creature;
	
	private Image imageFond;
	
	private Case[] tabCases;
	
	public Creep getCreature() {
		return creature;
	}

	public void setCreature(Creep creature) {
		this.creature = creature;
	}	
	
	public Image getImageFond() {
		return imageFond;
	}

	public void setImageFond(Image imageFond) {
		this.imageFond = imageFond;
	}

	public Case[] getTabCases() {
		return tabCases;
	}

	public void setTabCases(Case[] tabCases) {
		this.tabCases = tabCases;
	}

	public int getNBR_LIG() {
		return NBR_LIG;
	}

	public int getNBR_COL() {
		return NBR_COL;
	}

	public int getTAILLE_CASE_FAV() {
		return TAILLE_CASE_FAV;
	}

	/**
	 * 
	 */
	public Plateau () {
		
		this.tabCases = new Case[NBR_LIG*NBR_COL];
		this.imageFond = chargerImageFond(FICHIER_FOND);		
				
		this.setPreferredSize(new Dimension(NBR_COL*TAILLE_CASE_FAV , NBR_LIG*TAILLE_CASE_FAV));
		this.setSize(getPreferredSize());
				
	}
	
	/**
	 * Remplissage d'un Plateau
	 */
	public void remplissageTabCases () {
		
		int n = 0;
		
		for (int i=0 ; i<NBR_LIG ; i++) {
			
			for (int j=0 ; j<NBR_COL ; j++) {
								
				this.getTabCases()[n] = new Case((j*TAILLE_CASE_FAV)+(TAILLE_CASE_FAV/2) , (i*TAILLE_CASE_FAV)+(TAILLE_CASE_FAV/2));
				n++;
				
			}
			
		}
		
		//Optionnel pour test
		this.getTabCases()[340].setObstacle(true);
		this.getTabCases()[341].setObstacle(true);
		this.getTabCases()[342].setObstacle(true);
		this.getTabCases()[343].setObstacle(true);
		this.getTabCases()[344].setObstacle(true);
		this.getTabCases()[424].setObstacle(true);
		this.getTabCases()[584].setObstacle(true);
		this.getTabCases()[664].setObstacle(true);
		this.getTabCases()[3].setSiteNourriture(true);		
	}
	
	public Creep remplissageCreatures () {
		
		Creep creature = new Creep(this);
		
		return creature;
		
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
				
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
			
		int n = 0;
		
		for (int i=0 ; i<NBR_LIG ; i++) {
			
			for (int j=0 ; j<NBR_COL ; j++) {				
			
			this.getTabCases()[n].afficher(this.getTabCases()[n].getPosX()-(TAILLE_CASE_FAV/2), this.getTabCases()[n].getPosY()-(TAILLE_CASE_FAV/2), TAILLE_CASE_FAV, TAILLE_CASE_FAV, g);
			n++;	
			
			}
			
		}
		
		this.getCreature().comportement(this);
		this.getCreature().afficher(g);		
		
	}

}
