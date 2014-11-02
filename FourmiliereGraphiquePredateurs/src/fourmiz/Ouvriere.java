package fourmiz;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JOptionPane;

import mecaniques.Fourmiliere;
import mecaniques.Simulation;

public class Ouvriere extends FourmieAbstract {

	private int nourritureTrans;
	
	public int getNourritureTrans() {
		return nourritureTrans;
	}

	public void setNourritureTrans(int nourritureTrans) {
		this.nourritureTrans = nourritureTrans;
	}

	public Ouvriere(Fourmiliere f , Simulation simu) {
		
		super("Ouvriere " + nbrOuvriere , 1 , 2400 , f , simu);
		this.nourritureTrans = 0;		
		
		simu.setLayout(null);
		//simu.add(this.getNom());
		//simu.add(this.getPv());
		simu.add(this.getIcone());
				
		/*this.getNom().setFont(new Font(this.getNom().getFont().getName() , Font.ITALIC , 10));
		Dimension size = this.getNom().getPreferredSize();		
		this.getNom().setBounds(this.getPosX()-20 , this.getPosY()-15 , size.width , size.height);*/
		
		/*this.getPv().setFont(new Font(this.getPv().getFont().getName() , Font.PLAIN , 9));
		size = this.getPv().getPreferredSize();
		this.getPv().setBounds(this.getPosX() , this.getPosY()+15 , size.width , size.height);*/
		
		//size = this.getIcone().getPreferredSize();
		this.getIcone().setBounds(this.getPosX() , this.getPosY() , 16 , 16);
		
		nbrOuvriere++;
		
	}
	
	@Override
	public void deplacement(Fourmiliere f) {
		
		int xT = 0;
		int yT = 0;
				
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
		
		this.setDirection("" + xT + yT);
		
		
		this.setPosX(this.getPosX() + xT);
		this.setPosY(this.getPosY() + yT);
		
	}

	@Override
	public void afficher(Graphics2D g , Simulation simu) {		
			
		if(this.isEstActive() == false) {
			
			//g.setColor(Color.red);
			
		} else {
			
			g.setColor(Color.black);
			
			/*if (this.getNourritureTrans() > 0) {
				
				int ecart = 0;
				
				for (int i=0 ; i < 4 ; i++) {
					
					g.drawRect(this.getPosX() + 17 + ecart, this.getPosY(), 4, 6);
					ecart = ecart + 4;
					
				}
				
				ecart = 0;
				
				for (int i=0 ; i <this.getNourritureTrans() ; i++) {					
					
					g.fillRect(this.getPosX() + 19 + ecart, this.getPosY() + 2, 1, 3);
					ecart = ecart + 4;
					
				}
				
			}*/
			
		}		
		
		/*this.getPv().setText("PV:" + this.getPointsDeVie());		
				
		this.getNom().setBounds(this.getPosX()-20, this.getPosY()-15, this.getNom().getPreferredSize().width, this.getNom().getPreferredSize().height);
		this.getPv().setBounds(this.getPosX()-10 , this.getPosY()+20 , this.getPv().getPreferredSize().width,this.getPv().getPreferredSize().height);*/
		this.getIcone().setBounds(this.getPosX(), this.getPosY(), 16, 16);
	}

	@Override
	public void comportement(Fourmiliere f , Simulation simu) {
		
		if (this.isEstActive()) {
			
			switch (this.getDirection()) {
			case "0-1":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[0]);
				break;

			case "1-1":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[1]);
				break;
				
			case "10":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[2]);
				break;
				
			case "11":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[3]);
				break;
						
			case "01":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[4]);
				break;
				
			case "-11":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[5]);
				break;

			case "-10":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[6]);
				break;
				
			case "-1-1":
				
				this.getIcone().setIcon(simu.getSpritesFourmie()[7]);
				break;
				
			default:
				break;
			}
			
			if (this.isAller()) {
				
				if (this.getPosX() == f.getPosX() && this.getPosY() == f.getPosY()) {
					
					Random choixAlea = new Random();
					
					this.setChoix(choixAlea.nextInt(f.getTerrainInit().getTerrainTab().size()));
					
					this.deplacement(f);
					this.setPointsDeVie(this.getPointsDeVie() - 1);					
					
					//System.out.println("La fourmie (" + this.getType() + ") avance vers le " + f.getTerrainTab().getTerrain()[this.getChoix()].getNom() + ".");
					
				} else if (this.getChoix() < f.getTerrainInit().getTerrainTab().size()) {
					
					if (this.getPosX() == f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosX() && this.getPosY() == f.getTerrainInit().getTerrainTab().get(this.getChoix()).getPosY()) {
						
						int rslt;
						
						this.setAller(false);
						
						Random rand = new Random();
						rslt = rand.nextInt(4)+1;
						
						
						if (f.getTerrainInit().getTerrainTab().get(this.getChoix()).getStockNourriture() >= rslt) {
							
							this.setNourritureTrans(rslt);
							
							f.getTerrainInit().getTerrainTab().get(this.getChoix()).setStockNourriture(f.getTerrainInit().getTerrainTab().get(this.getChoix()).getStockNourriture() - this.getNourritureTrans());
							
						} else {
							
							this.setNourritureTrans(f.getTerrainInit().getTerrainTab().get(this.getChoix()).getStockNourriture());
							f.getTerrainInit().getTerrainTab().get(this.getChoix()).setStockNourriture(0);
							/*simu.remove(f.getTerrainInit().getTerrainTab().get(this.getChoix()).getQuantiteNourriture());
							simu.remove(f.getTerrainInit().getTerrainTab().get(this.getChoix()).getNom());*/
							
							simu.remove(f.getTerrainInit().getTerrainTab().get(this.getChoix()).getIcone());
							
							f.getTerrainInit().getTerrainTab().remove(this.getChoix());							
							
						}										
											
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
						
						JOptionPane.showMessageDialog(simu,
							    "Il n'y a plus de site de nourriture",
							    "Fin de simulation",
							    JOptionPane.WARNING_MESSAGE);
						
						System.exit(0);
						
					}
					
					this.deplacement(f);
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
				}
				
			} else {
				
				if (this.getPosX() == f.getPosX() && this.getPosY() == f.getPosY()) {
					
					this.setAller(true);					
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					//System.out.println("La fourmie (" + this.getType() + ") est retournee a la fourmiliere avec " + this.getNourritureTransportee() + " unites de nourriture.");
					
					//BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageDepotNourriture(this,this.getNourritureTransportee()));
					f.setStockNourriture(f.getStockNourriture() + this.getNourritureTrans());
					this.setNourritureTrans(0);					
					
					if (f.getStockNourriture() > 0) {
						
						f.setStockNourriture(f.getStockNourriture() - 1);
						
					} else {
						
						this.setPointsDeVie(this.getPointsDeVie() - 500);
						
					}
					
				} else {					
					
					this.deplacement(f);
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					//System.out.println("La fourmie (" + this.getType() + ") retourne a la fourmiliere.");
					
				}				
			}
			
			if (this.getPointsDeVie() <= 0) {
				
				this.setEstActive(false);
				
			}
			
		} else {
			
			this.getIcone().setIcon(simu.getSpritesFourmie()[16]);
			
		}

	}

}
