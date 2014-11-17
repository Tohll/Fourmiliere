/**
 * 
 */
package plateau;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @version 0.1 
 * @author Seldan
 *
 */
public class CaseNourriture extends CaseAbstract {

	public static final ImageIcon ICONE_SITE_NOURRITURE_ND = chargerIconeSiteNourritureND();
	public static final ImageIcon ICONE_SITE_NOURRITURE_D = chargerIconeSiteNourritureD();
	
	private JLabel icone;
	private JLabel affNourriture;
	
	private int stockNourriture;	
	
	private boolean decouvert;
	
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
	 * @return the decouvert
	 */
	public boolean isDecouvert() {
		return decouvert;
	}

	/**
	 * @param decouvert the decouvert to set
	 */
	public void setDecouvert(boolean decouvert) {
		this.decouvert = decouvert;
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
	 * @param x
	 * @param y
	 * @param lgn
	 * @param col
	 */
	public CaseNourriture(int x, int y, int lgn, int col , Plateau plateau) {
		
		super(x, y, lgn, col , plateau);
		
		this.stockNourriture = 5000;
		
		this.icone = new JLabel(ICONE_SITE_NOURRITURE_ND);
		this.affNourriture = new JLabel("Stock:" + this.stockNourriture);
				
		plateau.add(this.icone);
		plateau.add(this.affNourriture);
		
		this.affNourriture.setFont(new Font("Arial" , Font.BOLD , 8));
		this.affNourriture.setForeground(Color.white);
		this.affNourriture.setVisible(false);
			
		this.setNourriture(true);
		this.decouvert =false;		
		this.setChemin(null);
		
	}

	/* (non-Javadoc)
	 * @see plateau.CaseAbstract#afficher(int, int, int, int, java.awt.Graphics, plateau.Plateau)
	 */
	@Override
	public void afficher(int x, int y, int width, int height, Graphics g, Plateau plateau) {
		
		if (!this.isObstacle()) {
			
			if (!this.isDecouvert()) {				
								
				this.getIcone().setBounds(this.getPosX()-10 , this.getPosY()-7 , this.getIcone().getPreferredSize().width , this.getIcone().getPreferredSize().height);				
				
			} else {				
				
				if (!this.getAffNourriture().isVisible()) {
					
					this.getAffNourriture().setVisible(true);
					
				}	
				
				this.getAffNourriture().setText("Stock:" + this.getStockNourriture());				
				this.getAffNourriture().setBounds(this.getPosX()-20 , this.getPosY()+6 , this.getAffNourriture().getPreferredSize().width , this.getAffNourriture().getPreferredSize().height);
				
				if (!this.getIcone().getIcon().equals(ICONE_SITE_NOURRITURE_D)) {
					
					this.getIcone().setIcon(ICONE_SITE_NOURRITURE_D);
					
				}
				
				this.getIcone().setBounds(this.getPosX()-10 , this.getPosY()-7 , this.getIcone().getPreferredSize().width , this.getIcone().getPreferredSize().height);
				
			}
			
		} else {			
			
			plateau.remove(this.getAffNourriture());
			plateau.remove(this.getIcone());
			g.setColor(new Color(89 , 89 , 89));
			g.fillRect(x, y, width, height);					
			
		}
		
		
		
		/*g.setColor(Color.black);
		g.drawRect(x, y, width, height);*/

	}
	
	private static ImageIcon chargerIconeSiteNourritureND () {
		
		ImageIcon icone = null;
		
		try {
			
			icone = new ImageIcon("img/Bat_SNND.png");
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,
				    "Erreur",
				    "Probleme fichier Sprite",
				    JOptionPane.WARNING_MESSAGE);
			
			System.exit(0);
			
		}		
		
		return icone;
		
	}
	
private static ImageIcon chargerIconeSiteNourritureD () {
		
		ImageIcon icone = null;
		
		try {
			
			icone = new ImageIcon("img/Bat_SND.png");
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,
				    "Erreur",
				    "Probleme fichier Sprite",
				    JOptionPane.WARNING_MESSAGE);
			
			System.exit(0);
			
		}		
		
		return icone;
		
	}

}
