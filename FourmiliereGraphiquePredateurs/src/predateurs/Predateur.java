package predateurs;

import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import mecaniques.Fourmiliere;
import mecaniques.Simulation;

public class Predateur {
	
	public static int nbrPredateur = 1;
	
	/*private int[] tabX = {this.getPosX(),this.getPosX()+1,this.getPosX()+2};
	private int[] tabY = {this.getPosY()+7,this.getPosY(),this.getPosY()+7};*/
	
	/*public int[] getTabX() {
		return tabX;
	}

	public void setTabX(int[] tabX) {
		this.tabX = tabX;
	}

	public int[] getTabY() {
		return tabY;
	}

	public void setTabY(int[] tabY) {
		this.tabY = tabY;
	}*/

	private int cycle;
	private int posX;
	private int posY;
	
	private int vitesse;
	private int choix;
	private int pointsDeVie;
	
	private boolean mort;
	
	/*private JLabel affPV;
	private JLabel nom;*/
	private JLabel icone;
	
	private String direction;
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public JLabel getIcone() {
		return icone;
	}

	public void setIcone(JLabel icone) {
		this.icone = icone;
	}

	public int getPointsDeVie() {
		return pointsDeVie;
	}

	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	public boolean isMort() {
		return mort;
	}

	public void setMort(boolean mort) {
		this.mort = mort;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
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

	public int getChoix() {
		return choix;
	}

	public void setChoix(int choix) {
		this.choix = choix;
	}

	/*public JLabel getAffPV() {
		return affPV;
	}

	public void setAffPV(JLabel affPV) {
		this.affPV = affPV;
	}

	public JLabel getNom() {
		return nom;
	}

	public void setNom(JLabel nom) {
		this.nom = nom;
	}*/

	public Predateur (Fourmiliere f , Simulation simu) {
		
		Random r = new Random();
		
		this.posX = (r.nextInt(700)+50);
		this.posY = (r.nextInt(500)+50);
		
		this.pointsDeVie = 1000;
		this.direction = new String();
		
		/*this.nom = new JLabel("Predateur " + nbrPredateur);
		this.affPV = new JLabel("PV:" + this.getPointsDeVie());*/
		
		this.icone = new JLabel(simu.getSpritesFourmie()[19]);
		
		this.mort = false;
		this.cycle = 0;
		this.vitesse = 1;
		this.choix = r.nextInt(f.getTerrainInit().getTerrainTab().size());
		
		simu.setLayout(null);
		simu.add(this.icone);
		/*simu.add(this.getNom());
		simu.add(this.getAffPV());
		
		this.getNom().setForeground(Color.red);
		this.getAffPV().setForeground(Color.red);
		
		this.getNom().setFont(new Font(this.getNom().getFont().getName() , Font.ITALIC , 11));
		Dimension size = this.getNom().getPreferredSize();		
		this.getNom().setBounds(this.getPosX()-25 , this.getPosY()-16 , size.width , size.height);
		
		this.getAffPV().setFont(new Font(this.getAffPV().getFont().getName() , Font.BOLD , 11));
		size = this.getAffPV().getPreferredSize();
		this.getAffPV().setBounds(this.getPosX()-12 , this.getPosY()+20 , size.width , size.height);*/
		
		this.icone.setBounds(this.getPosX() , this.getPosY() , 40 , 44);
		
		nbrPredateur++;
		
	}

	public void afficher(Graphics2D g, Simulation simu) {
		
		/*this.getAffPV().setText("PV:" + this.getPointsDeVie());
		g.setColor(Color.red);
		g.drawPolygon(this.getTabX() , this.getTabY() , 3);
		
		this.getNom().setBounds(this.getPosX()-25, this.getPosY()-16, this.getNom().getPreferredSize().width, this.getNom().getPreferredSize().height);
		this.getAffPV().setBounds(this.getPosX()-12 , this.getPosY()+20 , this.getAffPV().getPreferredSize().width,this.getAffPV().getPreferredSize().height);*/
		this.getIcone().setBounds(this.getPosX() , this.getPosY() , 40 , 44);
		
	}

	public void comportement(Fourmiliere f, Simulation simu) {
		
		if (this.isMort() == false) {
			
			switch (this.getDirection()) {
			case "0-1":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[18]);
				break;

			case "1-1":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[19]);
				break;
				
			case "10":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[20]);
				break;
				
			case "11":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[21]);
				break;
						
			case "01":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[22]);
				break;
				
			case "-11":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[23]);
				break;

			case "-10":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[24]);
				break;
				
			case "-1-1":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[25]);
				break;
				
			default:
				break;
			}
			
			this.setCycle(this.getCycle()+1);
			
			if (this.getCycle() == 5) {
				
				this.setCycle(0);
				if (this.getChoix() < f.getTerrainInit().getTerrainTab().size()) {
					
					this.deplacement(f);
					
				} else {
					
					Random r = new Random();
					
					try {
						
						this.setChoix(r.nextInt(f.getTerrainInit().getTerrainTab().size()));
						
					} catch (Exception e) {
						
						JOptionPane.showMessageDialog(simu,
							    "Il n'y a plus de site de nourriture",
							    "Fin de simulation",
							    JOptionPane.WARNING_MESSAGE);
						
						System.exit(0);
						
					}					
					
					this.deplacement(f);
					
				}
				
			}	
			
			if (this.getChoix() < f.getTerrainInit().getTerrainTab().size()) {
				
				if (this.getPosX() == f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosX() && this.getPosY() == f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosY()) {
					
					f.getTerrainInit().getTerrainTab().get(this.getChoix()).setStockNourriture(f.getTerrainInit().getTerrainTab().get(this.getChoix()).getStockNourriture()-150);
					if(f.getTerrainInit().getTerrainTab().get(this.getChoix()).getStockNourriture() <= 0) {
						
						/*simu.remove(f.getTerrainInit().getTerrainTab().get(this.getChoix()).getQuantiteNourriture());
						simu.remove(f.getTerrainInit().getTerrainTab().get(this.getChoix()).getNom());*/
						
						simu.remove(f.getTerrainInit().getTerrainTab().get(this.getChoix()).getIcone());
						
						f.getTerrainInit().getTerrainTab().remove(this.getChoix());
						
					}
					
					/*simu.remove(this.getNom());
					simu.remove(this.getAffPV());*/
					
					simu.remove(this.getIcone());
					this.setMort(true);
					
									
				}
				
			} else {
				
				Random r = new Random();
				
				try {
					
					this.setChoix(r.nextInt(f.getTerrainInit().getTerrainTab().size()));
					
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(simu,
						    "Il n'y a plus de site de nourriture",
						    "Fin de simulation",
						    JOptionPane.WARNING_MESSAGE);
					
					System.exit(0);
					
				}
				
				this.deplacement(f);
				
			}
			
		}
		
		if (this.getPointsDeVie() <= 0) {
			
			/*simu.remove(this.getNom());
			simu.remove(this.getAffPV());*/
			simu.remove(this.getIcone());
			
			this.setMort(true);
			
		}
		
	}

	public void deplacement(Fourmiliere f) {
		
		int xT = 0;
		int yT = 0;
		
		//gestion de X
		if (this.getPosX() < f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosX()) {
			
			xT = this.getVitesse();
			
		}
		
		if (this.getPosX() > f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosX()) {
			
			xT = - this.getVitesse();
			
		}
		
		if (this.getPosX() == f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosX()) {
			
			xT = 0;
			
		}
		
		//gestion de Y
		if (this.getPosY() < f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosY()) {
			
			yT = this.getVitesse();
			
		}
		
		if (this.getPosY() > f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosY()) {
			
			yT = - this.getVitesse();
			
		}
		
		if (this.getPosY() == f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosY()) {
			
			yT = 0;
			
		}		
		
		this.setDirection("" + xT + yT);
		
		this.setPosX(this.getPosX() + xT);
		this.setPosY(this.getPosY() + yT);
		
		/*this.getTabX()[0] = this.getPosX();
		this.getTabX()[1] = this.getPosX()+4;
		this.getTabX()[2] = this.getPosX()+8;		
		
		this.getTabY()[0] = this.getPosY()+16;
		this.getTabY()[1] = this.getPosY();
		this.getTabY()[2] = this.getPosY()+16;*/
		
	}	
	
}
