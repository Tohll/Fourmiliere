package mecaniques;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.JLabel;

public class SiteNourriture {
	
	protected static int nbrSites = 1;
	
	/*private JLabel nom;
	private JLabel quantiteNourriture;*/
	private int stockNourriture;
	private int posX;
	private int posY;
	
	private Icon icone60;
	private Icon icone30;
	
	private JLabel icone;
	
	public Icon getIcone60() {
		return icone60;
	}

	public void setIcone60(Icon icone60) {
		this.icone60 = icone60;
	}

	public Icon getIcone30() {
		return icone30;
	}

	public void setIcone30(Icon icone30) {
		this.icone30 = icone30;
	}

	public JLabel getIcone() {
		return icone;
	}

	public void setIcone(JLabel icone) {
		this.icone = icone;
	}

	/*public JLabel getNom() {
		return nom;
	}

	public void setNom(JLabel nom) {
		this.nom = nom;
	}

	public JLabel getQuantiteNourriture() {
		return quantiteNourriture;
	}

	public void setQuantiteNourriture(JLabel quantiteNourriture) {
		this.quantiteNourriture = quantiteNourriture;
	}*/

	public int getStockNourriture() {
		return stockNourriture;
	}

	public void setStockNourriture(int stockNourriture) {
		this.stockNourriture = stockNourriture;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public SiteNourriture(int x , int y , Simulation simu) {		
				
		this.posX = x;
		this.posY = y;
		this.stockNourriture = 900;
		
		/*this.nom = new JLabel("Site n°" + nbrSites);
		this.quantiteNourriture = new JLabel("Nourriture:" + this.stockNourriture);*/
		this.icone = new JLabel(simu.getSpritesFourmie()[27]);
		this.icone60 = simu.getSpritesFourmie()[28];
		this.icone30 = simu.getSpritesFourmie()[29];
		
		simu.setLayout(null);
		
		/*simu.add(this.nom);
		simu.add(this.quantiteNourriture);*/
		
		simu.add(this.getIcone());
		
		Dimension size = this.getIcone().getPreferredSize();
		
		/*this.nom.setFont(new Font(this.nom.getFont().getName() , Font.PLAIN , 12));
		Dimension size = this.nom.getPreferredSize();		
		this.nom.setBounds(x+27 , y , size.width , size.height);
		
		this.quantiteNourriture.setFont(new Font(this.quantiteNourriture.getFont().getName() , Font.PLAIN , 10));
		size = this.quantiteNourriture.getPreferredSize();
		this.quantiteNourriture.setBounds(x+27 , this.nom.getY()+12 , size.width , size.height);*/
		
		this.getIcone().setBounds(this.getPosX()-30, this.getPosY()-15, size.width, size.height);
		
		nbrSites++;
		
	}
	
	public void afficher(Graphics2D g) {
		
		if (this.getStockNourriture() >=300 && this.getStockNourriture() < 600) {
			
			this.getIcone().setIcon(this.getIcone60());
			
		} else if (this.getStockNourriture() < 300 && this.getStockNourriture() > 0) {
			
			this.getIcone().setIcon(this.getIcone30());
			
		}
		
		/*g.setColor(new Color(153,128,0));				
		this.getQuantiteNourriture().setText("Nourriture:" +this.getStockNourriture());
		
		
		if (this.getStockNourriture() > 300) {
			
			g.fillRect(this.getPosX(), this.getPosY(), 25 , 25);
			
		}else if (this.getStockNourriture() > 200) {
			
			g.fillRect(this.getPosX(), this.getPosY()+5, 25 , 20);
			
		}else if (this.getStockNourriture() > 100) {
			
			g.fillRect(this.getPosX(), this.getPosY()+10, 25 , 15);
			
		} else if (this.getStockNourriture() > 50) {
			
			g.fillRect(this.getPosX(), this.getPosY()+15, 25 , 10);
			
		} else {
			
			g.fillRect(this.getPosX(), this.getPosY()+20, 25 , 5);
			
		}
		
		g.setColor(Color.black);
		g.drawRect(this.getPosX(), this.getPosY(), 25, 25);*/
		
	}

}
