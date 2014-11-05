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
	private final int TAILLE_CASE_FAV = 10;   //taille des cases ** PENSER A CHANGER VALEUR F DANS PATHFINDING
	private final String FICHIER_FOND = new String("img/gazon800600.png");   //chemin vers un fichier pour l'image de fond
	/*
	 * ***********************
	 */	
	
	private Creep creature;
	
	private Image imageFond;
	
	private Case[][] tabCases;
	
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

	public Case[][] getTabCases() {
		return tabCases;
	}

	public void setTabCases(Case[][] tabCases) {
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
		
		this.tabCases = new Case[NBR_LIG][NBR_COL];
		this.imageFond = chargerImageFond(FICHIER_FOND);		
				
		this.setPreferredSize(new Dimension(NBR_COL*TAILLE_CASE_FAV , NBR_LIG*TAILLE_CASE_FAV));
		this.setSize(getPreferredSize());
				
	}
	
	/**
	 * Remplissage d'un Plateau
	 */
	public void remplissageTabCases () {
				
		for (int i=0 ; i<NBR_LIG ; i++) {
			
			for (int j=0 ; j<NBR_COL ; j++) {
								
				this.getTabCases()[i][j] = new Case ((j*TAILLE_CASE_FAV)+(TAILLE_CASE_FAV/2) , (i*TAILLE_CASE_FAV)+(TAILLE_CASE_FAV/2) , i , j);
				
			}
			
		}
		
		//Optionnel pour test
		this.getTabCases()[25][15].setObstacle(true);
		this.getTabCases()[25][16].setObstacle(true);
		this.getTabCases()[25][17].setObstacle(true);
		this.getTabCases()[25][18].setObstacle(true);
		this.getTabCases()[25][19].setObstacle(true);
		
		this.getTabCases()[25][21].setObstacle(true);
		this.getTabCases()[25][22].setObstacle(true);
		this.getTabCases()[26][22].setObstacle(true);
		this.getTabCases()[27][22].setObstacle(true);
		this.getTabCases()[28][22].setObstacle(true);
		this.getTabCases()[29][22].setObstacle(true);
		this.getTabCases()[30][22].setObstacle(true);
		this.getTabCases()[31][22].setObstacle(true);
		
		this.getTabCases()[27][24].setSiteNourriture(true);
				
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
				
		for (int i=0 ; i<NBR_LIG ; i++) {
			
			for (int j=0 ; j<NBR_COL ; j++) {				
			
			this.getTabCases()[i][j].afficher(this.getTabCases()[i][j].getPosX()-(TAILLE_CASE_FAV/2), this.getTabCases()[i][j].getPosY()-(TAILLE_CASE_FAV/2), TAILLE_CASE_FAV, TAILLE_CASE_FAV, g);
						
			}
			
		}
		
		this.getCreature().comportement(this);
		this.getCreature().afficher(g);		
		
	}

}
