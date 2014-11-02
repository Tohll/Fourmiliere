package mecaniques;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.util.Enumeration;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import predateurs.Predateur;
import fourmiz.FourmieAbstract;

@SuppressWarnings("serial")
public class Simulation extends JPanel {	
		
	public static boolean attaque = false;
	
	private Fourmiliere f;	
	private Image imageFond;
	private Icon [] spritesFourmie;
	
	public Icon[] getSpritesFourmie() {
		return spritesFourmie;
	}

	public void setSpritesFourmie(Icon[] spritesFourmie) {
		this.spritesFourmie = spritesFourmie;
	}

	public Image getImageFond() {
		return imageFond;
	}

	public void setImageFond(Image imageFond) {
		this.imageFond = imageFond;
	}

	public static boolean isAttaque() {
		return attaque;
	}

	public static void setAttaque(boolean attaque) {
		Simulation.attaque = attaque;
	}

	public Simulation () {		
		
		this.imageFond = chargerImageFond("img/gazon800600terre.png");
		initSprites();
		this.f = new Fourmiliere(this);
		
	}
	
	private void initSprites () {
		
		this.spritesFourmie = new Icon[30];
		
		try {
			//sprites fourmis 16x16
			this.spritesFourmie[0] = new ImageIcon("img/Fou_N16.gif");
			this.spritesFourmie[1] = new ImageIcon("img/Fou_NE16.gif");
			this.spritesFourmie[2] = new ImageIcon("img/Fou_E16.gif");
			this.spritesFourmie[3] = new ImageIcon("img/Fou_SE16.gif");
			this.spritesFourmie[4] = new ImageIcon("img/Fou_S16.gif");
			this.spritesFourmie[5] = new ImageIcon("img/Fou_SO16.gif");
			this.spritesFourmie[6] = new ImageIcon("img/Fou_O16.gif");
			this.spritesFourmie[7] = new ImageIcon("img/Fou_NO16.gif");
			//sprites fourmis 32x32
			this.spritesFourmie[8] = new ImageIcon("img/Fou_N.gif");
			this.spritesFourmie[9] = new ImageIcon("img/Fou_NE.gif");
			this.spritesFourmie[10] = new ImageIcon("img/Fou_E.gif");
			this.spritesFourmie[11] = new ImageIcon("img/Fou_SE.gif");
			this.spritesFourmie[12] = new ImageIcon("img/Fou_S.gif");
			this.spritesFourmie[13] = new ImageIcon("img/Fou_SO.gif");
			this.spritesFourmie[14] = new ImageIcon("img/Fou_O.gif");
			this.spritesFourmie[15] = new ImageIcon("img/Fou_NO.gif");
			//sprites fourmis morte 16x16 et 32x32
			this.spritesFourmie[16] = new ImageIcon("img/Fou_M16.png");
			this.spritesFourmie[17] = new ImageIcon("img/Fou_M.png");
			//sprites predateur 40x44
			this.spritesFourmie[18] = new ImageIcon("img/Pred_N4044.gif");
			this.spritesFourmie[19] = new ImageIcon("img/Pred_NE4044.gif");
			this.spritesFourmie[20] = new ImageIcon("img/Pred_E4044.gif");
			this.spritesFourmie[21] = new ImageIcon("img/Pred_SE4044.gif");
			this.spritesFourmie[22] = new ImageIcon("img/Pred_S4044.gif");
			this.spritesFourmie[23] = new ImageIcon("img/Pred_SO4044.gif");
			this.spritesFourmie[24] = new ImageIcon("img/Pred_O4044.gif");
			this.spritesFourmie[25] = new ImageIcon("img/Pred_NO4044.gif");
			//sprites batiments
			this.spritesFourmie[26] = new ImageIcon("img/Bat_F.png");
			this.spritesFourmie[27] = new ImageIcon("img/Bat_SN9050.png");
			this.spritesFourmie[28] = new ImageIcon("img/Bat_SN6033.png");
			this.spritesFourmie[29] = new ImageIcon("img/Bat_SN3017.png");
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(this,
				    "Erreur",
				    "Probleme fichier Sprite",
				    JOptionPane.WARNING_MESSAGE);
			
			System.exit(0);
			
		}
		
	}
	
	private Image chargerImageFond (String fichier) {
		
		Image image = null;
		
		try {
			
			image = ImageIO.read(new File(fichier));
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(this,
				    "Erreur",
				    "Probleme fichier de fond",
				    JOptionPane.WARNING_MESSAGE);
			
			System.exit(0);
			
		}
		
		return image;
		
	}
	
	public void comportements() {		
		
		if (attaque == false) {			
			
			Random rand = new Random();
			int alea = rand.nextInt(599)+1;
			
			if (alea == 1) {
				
				attaque = true;
				f.getListePred().put(Predateur.nbrPredateur, new Predateur(f, this));
				
			}
			
		}
		
		Integer x;		
		Enumeration<Integer> ePop = f.getPopulation().keys();
		Enumeration<Integer> ePred	= f.getListePred().keys();
		
		while (ePop.hasMoreElements()) {
			
			x = ePop.nextElement();
			
			if (f.getPopulation().get(x).isMorte()) {
				
				f.getPopulation().remove(x);
				
			} else {
				
				f.getPopulation().get(x).comportement(f, this);
				
			}			
			
		}
		
		while (ePred.hasMoreElements()) {
			
			x = ePred.nextElement();
			
			if (f.getListePred().get(x).isMort()) {
				
				f.getListePred().remove(x);
				attaque = false;
				
			} else {
				
				f.getListePred().get(x).comportement(f, this);
				
			}
			
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(this.getImageFond(), 0 , 0 , this);
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Enumeration<FourmieAbstract> ePop = f.getPopulation().elements();
		Enumeration<Predateur> ePred = f.getListePred().elements();
		
		f.afficher(g2d);
		
		while (ePred.hasMoreElements()) {
			
			ePred.nextElement().afficher(g2d, this);
			
		}
		
		while (ePop.hasMoreElements()) {
			
			ePop.nextElement().afficher(g2d, this);
			
		}		
		
		
		
		
		
		for (int i=0 ; i < f.getTerrainInit().getTerrainTab().size() ; i++) {
			
			f.getTerrainInit().getTerrainTab().get(i).afficher(g2d);			
			
		}		
		
	}

}
