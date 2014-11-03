package fourmiz;

import java.awt.Graphics2D;
import java.util.Enumeration;
import java.util.Random;

import javax.swing.JOptionPane;

import mecaniques.Fourmiliere;
import mecaniques.Simulation;

public class Guerriere extends FourmieAbstract {

	/*private int[] tabX = {this.getPosX(),this.getPosX()+4,this.getPosX()+8,this.getPosX()+4};
	private int[] tabY = {this.getPosY()+4,this.getPosY(),this.getPosY()+4,this.getPosY()+8};*/
	private boolean attaque;
	
	public boolean isAttaque() {
		return attaque;
	}

	public void setAttaque(boolean attaque) {
		this.attaque = attaque;
	}

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

	public Guerriere (Fourmiliere f , Simulation simu) {
		
		super("Guerriere " + nbrGuerriere , 2 , 2000 , f , simu);
		
		this.attaque = false;
		
		Random rand = new Random();
		this.setPointsDeVie(this.getPointsDeVie() + rand.nextInt(500));
		
		simu.setLayout(null);
		/*simu.add(this.getNom());
		simu.add(this.getPv());*/
		simu.add(this.getIcone());
		
		/*this.getNom().setFont(new Font(this.getNom().getFont().getName() , Font.ITALIC , 10));
		Dimension size = this.getNom().getPreferredSize();		
		this.getNom().setBounds(this.getPosX()-20 , this.getPosY()-15 , size.width , size.height);*/
		
		/*this.getPv().setFont(new Font(this.getPv().getFont().getName() , Font.PLAIN , 9));
		size = this.getPv().getPreferredSize();
		this.getPv().setBounds(this.getPosX() , this.getPosY()+10 , size.width , size.height);*/
		
		this.getIcone().setBounds(this.getPosX() , this.getPosY() , 32 , 32);
		
		nbrGuerriere++;
		
	}
	
	@Override
	public void afficher(Graphics2D g, Simulation simu) {		
		
		/*if(this.isEstActive() == false) {
			
			g.setColor(Color.red);
			
		} else {
			
			g.setColor(Color.orange);
			
		}*/
		
		//this.getPv().setText("PV:" + this.getPointsDeVie());
		
		//g.fillPolygon(this.getTabX() , this.getTabY() , 4);
		
		//this.getNom().setBounds(this.getPosX()-20, this.getPosY()-15, this.getNom().getPreferredSize().width, this.getNom().getPreferredSize().height);
		//this.getPv().setBounds(this.getPosX()-10 , this.getPosY()+10 , this.getPv().getPreferredSize().width,this.getPv().getPreferredSize().height);
		this.getIcone().setBounds(this.getPosX(), this.getPosY(), 32, 32);
				
	}

	@Override
	public void comportement(Fourmiliere f, Simulation simu) {
		
		if (this.isEstActive()) {
			
			switch (this.getDirection()) {
			case "0-1":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[8]);
				break;

			case "1-1":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[9]);
				break;
				
			case "10":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[10]);
				break;
				
			case "11":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[11]);
				break;
						
			case "01":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[12]);
				break;
				
			case "-11":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[13]);
				break;

			case "-10":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[14]);
				break;
				
			case "-1-1":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[15]);
				break;
				
			case "0-2":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[8]);
				break;

			case "2-2":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[9]);
				break;
				
			case "20":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[10]);
				break;
				
			case "22":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[11]);
				break;
						
			case "02":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[12]);
				break;
				
			case "-22":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[13]);
				break;

			case "-20":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[14]);
				break;
				
			case "-2-2":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[15]);
				break;
				
			default:
				break;
			}
			
			if (Simulation.isAttaque() && this.isAttaque() == false) {
				
				this.setPointsDeVie(this.getPointsDeVie()-1);
				this.setVitesse(1);
				this.setAttaque(true);
				
				Integer x;
				Enumeration<Integer> Epred = f.getListePred().keys();
				
				while (Epred.hasMoreElements()) {
					
					x = Epred.nextElement();
					this.setChoix(x);
					this.deplacement(f);
					this.setPointsDeVie(this.getPointsDeVie()-1);
					
				} 
				
			} else if (Simulation.isAttaque() && this.isAttaque()){
				
				if (f.getListePred().get(this.getChoix()) != null) {
					
					if(this.getPosX() == f.getListePred().get(this.getChoix()).getPosX() && this.getPosY() == f.getListePred().get(this.getChoix()).getPosY()) {
						
						this.setPointsDeVie(this.getPointsDeVie()-1);
						f.getListePred().get(this.getChoix()).setPointsDeVie(f.getListePred().get(this.getChoix()).getPointsDeVie() - 1);
						
					}
					
					this.setPointsDeVie(this.getPointsDeVie()-1);
					this.deplacement(f);
					
				}			
				
			} else {
				
				this.setAttaque(false);
				
				if (this.isAller()) {
					
					if (this.getPosX() == f.getPosX() && this.getPosY() == f.getPosY()) {
						
						Random choixAlea = new Random();
						
						this.setChoix(choixAlea.nextInt(f.getTerrainInit().getTerrainTab().size()));
						
						this.deplacement(f);
						this.setPointsDeVie(this.getPointsDeVie() - 1);					
						
						//System.out.println("La fourmie (" + this.getType() + ") avance vers le " + f.getTerrainTab().getTerrain()[this.getChoix()].getNom() + ".");
						
					} else if (this.getChoix() < f.getTerrainInit().getTerrainTab().size()) {
						
						if (this.getPosX() == f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosX() && this.getPosY() == f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosY()) {
							
							this.setAller(false);									
							this.setVitesse(2);
							
							this.setPointsDeVie(this.getPointsDeVie() - 1);
							
							//System.out.println("La fourmie (" + this.getType() + ") prend de la nourriture dans le " + f.getTerrainTab().getTerrain()[this.getChoix()].getNom() + ".");
												
						} else {
							
							this.deplacement(f);
							this.setPointsDeVie(this.getPointsDeVie() - 1);
							
							//System.out.println("La fourmie (" + this.getType() + ") avance vers le " + f.getTerrainTab().getTerrain()[this.getChoix()].getNom() + ".");
							
						}
						
					} else {
						
						Random choixAlea = new Random();
						
						try {
							
							this.setChoix(choixAlea.nextInt(f.getTerrainInit().getTerrainTab().size()));
							
						} catch(Exception e) {
							
							JOptionPane.showMessageDialog(simu , "Il n'y a plus de site de nourriture", "Fin de simulation" , JOptionPane.WARNING_MESSAGE);							
							System.exit(0);
							
						}
						
						this.deplacement(f);
						this.setPointsDeVie(this.getPointsDeVie() - 1);
						
					}
					
				} else {
					
					if (this.getPosX() == f.getPosX() && this.getPosY() == f.getPosY()) {
						
						this.setVitesse(2);
						this.setAller(true);					
						this.setPointsDeVie(this.getPointsDeVie() - 1);
						
						//System.out.println("La fourmie (" + this.getType() + ") est retournee a la fourmiliere avec " + this.getNourritureTransportee() + " unites de nourriture.");
						
						//BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageDepotNourriture(this,this.getNourritureTransportee()));								
						
						if (f.getStockNourriture() > 1) {
							
							f.setStockNourriture(f.getStockNourriture() - 2);
							
						} else {
							
							f.setStockNourriture(0);
							this.setPointsDeVie(this.getPointsDeVie() - 500);
							
						}
						
					} else {					
						
						this.deplacement(f);
						this.setPointsDeVie(this.getPointsDeVie() - 1);
						
						//System.out.println("La fourmie (" + this.getType() + ") retourne a la fourmiliere.");
						
					}				
				}
				
			}
			
			if (this.getPointsDeVie() <= 0) {
				
				this.setEstActive(false);
				
			}
			
		} else {			
			
			this.getIcone().setIcon(simu.getSpritesFourmie()[17]);
			
			/*this.getTabX()[0] = this.getPosX();
			this.getTabX()[1] = this.getPosX()+4;
			this.getTabX()[2] = this.getPosX()+8;
			this.getTabX()[3] = this.getPosX()+4;
			
			this.getTabY()[0] = this.getPosY()+4;
			this.getTabY()[1] = this.getPosY();
			this.getTabY()[2] = this.getPosY()+4;
			this.getTabY()[3] = this.getPosY()+8;*/
						
		}

	}

	@Override
	public void deplacement(Fourmiliere f) {
		
		int xT = 0;
		int yT = 0;
		
		if (this.isAttaque()) {
			
			//gestion de X
			if (this.getPosX() < f.getListePred().get(this.getChoix()).getPosX()) {
				
				xT = this.getVitesse();
				
			}
			
			if (this.getPosX() > f.getListePred().get(this.getChoix()).getPosX()) {
				
				xT = - this.getVitesse();
				
			}
			
			if (this.getPosX() == f.getListePred().get(this.getChoix()).getPosX()) {
				
				xT = 0;
				
			}
			
			//gestion de Y
			if (this.getPosY() < f.getListePred().get(this.getChoix()).getPosY()) {
				
				yT = this.getVitesse();
				
			}
			
			if (this.getPosY() > f.getListePred().get(this.getChoix()).getPosY()) {
				
				yT = - this.getVitesse();
				
			}
			
			if (this.getPosY() == f.getListePred().get(this.getChoix()).getPosY()) {
				
				yT = 0;
				
			}
			
		} else {
			
			if(this.isAller()) {
				
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
				
			} else {
				
				if (this.getPosX() < f.getPosX()) {
					
					xT = this.getVitesse();
					
				}
				
				if (this.getPosX() > f.getPosX()) {
					
					xT = - this.getVitesse();
					
				}
				
				if (this.getPosX() == f.getPosX()) {
					
					xT = 0;
					
				}
				
				//gestion de Y
				if (this.getPosY() < f.getPosY()) {
					
					yT = this.getVitesse();
					
				}
				
				if (this.getPosY() > f.getPosY()) {
					
					yT = - this.getVitesse();
					
				}
				
				if (this.getPosY() == f.getPosY()) {
					
					yT = 0;
					
				}
				
			}
			
		}
		
		this.setDirection("" + xT + yT);
		
		this.setPosX(this.getPosX() + xT);
		this.setPosY(this.getPosY() + yT);
		
		/*this.getTabX()[0] = this.getPosX();
		this.getTabX()[1] = this.getPosX()+4;
		this.getTabX()[2] = this.getPosX()+8;
		this.getTabX()[3] = this.getPosX()+4;
		
		this.getTabY()[0] = this.getPosY()+4;
		this.getTabY()[1] = this.getPosY();
		this.getTabY()[2] = this.getPosY()+4;
		this.getTabY()[3] = this.getPosY()+8;*/		

	}

}
