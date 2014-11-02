package fourmiz;

import java.awt.Graphics2D;
import java.util.Enumeration;

import mecaniques.Fourmiliere;
import mecaniques.Simulation;

public class Soigneur extends FourmieAbstract {	
	
	/*private int[] tabX = {this.getPosX(),this.getPosX()+3,this.getPosX()+3,this.getPosX()+6,this.getPosX()+6,this.getPosX()+9,this.getPosX()+9,this.getPosX()+6,this.getPosX()+6,this.getPosX()+3,this.getPosX()+3,this.getPosX()};
	private int[] tabY = {this.getPosY()+3,this.getPosY()+3,this.getPosY(),this.getPosY(),this.getPosY()+3,this.getPosY()+3,this.getPosY()+6,this.getPosY()+6,this.getPosY()+9,this.getPosY()+9,this.getPosY()+6,this.getPosY()+6};*/
	
	private boolean enService;	

	public boolean isEnService() {
		return enService;
	}

	public void setEnService(boolean enService) {
		this.enService = enService;
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

	public Soigneur (Fourmiliere f , Simulation simu) {
		
		super("Soin     " + nbrSoigneur , 1 , 2500 , f , simu);
		
		this.enService = false;			
		
		simu.setLayout(null);
		//simu.add(this.getNom());
		//simu.add(this.getPv());
		simu.add(this.getIcone());
		
		/*this.getNom().setFont(new Font(this.getNom().getFont().getName() , Font.ITALIC , 10));
		Dimension size = this.getNom().getPreferredSize();		
		this.getNom().setBounds(this.getPosX()-25 , this.getPosY()-3 , size.width , size.height);
		this.getNom().setVisible(false);*/
		
		/*this.getPv().setFont(new Font(this.getPv().getFont().getName() , Font.PLAIN , 9));
		size = this.getPv().getPreferredSize();
		this.getPv().setBounds(this.getPosX() , this.getPosY()+15 , size.width , size.height);
		this.getPv().setVisible(false);*/
		
		this.getIcone().setBounds(this.getPosX() , this.getPosY() , 16 , 16);
		
		nbrSoigneur++;
		
	}
	
	@Override
	public void afficher(Graphics2D g, Simulation simu) {
		
		//this.getPv().setText("PV:" + this.getPointsDeVie());
		
		/*if(this.isEstActive() == false) {
			
			g.setColor(Color.red);
			
		} else {
			
			g.setColor(Color.white);
			
		}*/
		
		if (this.isEnService()) {
			
			this.getIcone().setVisible(true);
			//this.getNom().setVisible(true);
			//this.getPv().setVisible(true);
			
			/*g.fillPolygon(this.getTabX() , this.getTabY() , 12);
			this.getNom().setBounds(this.getPosX()-26, this.getPosY()-3, this.getNom().getPreferredSize().width, this.getNom().getPreferredSize().height);*/
			//this.getPv().setBounds(this.getPosX()-10 , this.getPosY()+10 , this.getPv().getPreferredSize().width,this.getPv().getPreferredSize().height);
			
			this.getIcone().setBounds(this.getPosX() , this.getPosY() , 16 , 16);
			
		} else {
			
			this.getIcone().setVisible(false);
			//this.getNom().setVisible(false);
			//this.getPv().setVisible(false);
			
		}

	}

	@Override
	public void comportement(Fourmiliere f, Simulation simu) {
		
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
			
			if (this.isEnService() == false) {
				
				if (f.getPopulation().size() > 0) {
					
					Enumeration<Integer> e = f.getPopulation().keys();
					
					while (e.hasMoreElements()) {
						
						int x = e.nextElement();
						
						if (f.getPopulation().get(x).isEstActive() == false && f.getPopulation().get(x).isPriseEnCharge() == false) {
							
							this.setChoix(x);
							this.setEnService(true);							
							
							f.getPopulation().get(this.getChoix()).setPriseEnCharge(true);
							
							this.deplacement(f);
							break;
							
						}
						
					}					
					
				}			
				
			} else if(this.isAller() == false && this.getPosX() == f.getPosX() && this.getPosY() == f.getPosY()) {
				
				this.setEnService(false);
				this.setAller(true);								
				
				if (f.getStockNourriture() >= 3) {
					
					f.setStockNourriture(f.getStockNourriture() - 3);
					
				} else {
					
					this.setPointsDeVie(this.getPointsDeVie() - 500);
					
				}
				
				/*simu.remove(f.getPopulation().get(this.getChoix()).getNom());
				simu.remove(f.getPopulation().get(this.getChoix()).getPv());*/
				simu.remove(f.getPopulation().get(this.getChoix()).getIcone());
				f.getPopulation().remove(this.getChoix());
				
			} else if (this.getPosX() == f.getPopulation().get(this.getChoix()).getPosX() && this.getPosY() == f.getPopulation().get(this.getChoix()).getPosY() && this.isAller()) {
				
				this.setAller(false);				
				this.deplacement(f);
				
			} else {
				
				this.deplacement(f);
				
			}
			
			this.setPointsDeVie(this.getPointsDeVie() - 1);
			if (this.getPointsDeVie() <= 0 && this.isEnService() == true) {
				
				f.getPopulation().get(this.getChoix()).setPriseEnCharge(false);
				
				this.setEstActive(false);						
				
			} else if (this.getPointsDeVie() <= 0 && this.isEnService() == false) {
				
				//simu.remove(this.getNom());
				this.setMorte(true);
				
			}
			
		} else {
			
			this.getIcone().setIcon(simu.getSpritesFourmie()[16]);
			
			/*this.getTabX()[0] = this.getPosX();
			this.getTabX()[1] = this.getPosX()+3;
			this.getTabX()[2] = this.getPosX()+3;
			this.getTabX()[3] = this.getPosX()+6;
			this.getTabX()[4] = this.getPosX()+6;
			this.getTabX()[5] = this.getPosX()+9;
			this.getTabX()[6] = this.getPosX()+9;
			this.getTabX()[7] = this.getPosX()+6;
			this.getTabX()[8] = this.getPosX()+6;
			this.getTabX()[9] = this.getPosX()+3;
			this.getTabX()[10] = this.getPosX()+3;
			this.getTabX()[11] = this.getPosX();
					
			this.getTabY()[0] = this.getPosY()+3;
			this.getTabY()[1] = this.getPosY()+3;
			this.getTabY()[2] = this.getPosY();
			this.getTabY()[3] = this.getPosY();
			this.getTabY()[4] = this.getPosY()+3;
			this.getTabY()[5] = this.getPosY()+3;
			this.getTabY()[6] = this.getPosY()+6;
			this.getTabY()[7] = this.getPosY()+6;
			this.getTabY()[8] = this.getPosY()+9;
			this.getTabY()[9] = this.getPosY()+9;
			this.getTabY()[10] = this.getPosY()+6;
			this.getTabY()[11] = this.getPosY()+6;*/
			
		}
		
	}

	@Override
	public void deplacement(Fourmiliere f) {		
		
		int xT = 0;
		int yT = 0;
		
		if(this.isAller()) {			
			
			//gestion de X
			if (this.getPosX() < f.getPopulation().get(this.getChoix()).getPosX()) {
				
				xT = this.getVitesse();
				
			}
			
			if (this.getPosX() > f.getPopulation().get(this.getChoix()).getPosX()) {
				
				xT = - this.getVitesse();
				
			}
			
			if (this.getPosX() == f.getPopulation().get(this.getChoix()).getPosX()) {
				
				xT = 0;
				
			}
			
			//gestion de Y
			if (this.getPosY() < f.getPopulation().get(this.getChoix()).getPosY()) {
				
				yT = this.getVitesse();
				
			}
			
			if (this.getPosY() > f.getPopulation().get(this.getChoix()).getPosY()) {
				
				yT = - this.getVitesse();
				
			}
			
			if (this.getPosY() == f.getPopulation().get(this.getChoix()).getPosY()) {
				
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
			
			f.getPopulation().get(this.getChoix()).setPosX(this.getPosX()+1);
			f.getPopulation().get(this.getChoix()).setPosY(this.getPosY()+1);
			
		}		
		
		this.setDirection("" + xT + yT);
		
		this.setPosX(this.getPosX() + xT);
		this.setPosY(this.getPosY() + yT);
		
		/*this.getTabX()[0] = this.getPosX();
		this.getTabX()[1] = this.getPosX()+3;
		this.getTabX()[2] = this.getPosX()+3;
		this.getTabX()[3] = this.getPosX()+6;
		this.getTabX()[4] = this.getPosX()+6;
		this.getTabX()[5] = this.getPosX()+9;
		this.getTabX()[6] = this.getPosX()+9;
		this.getTabX()[7] = this.getPosX()+6;
		this.getTabX()[8] = this.getPosX()+6;
		this.getTabX()[9] = this.getPosX()+3;
		this.getTabX()[10] = this.getPosX()+3;
		this.getTabX()[11] = this.getPosX();
				
		this.getTabY()[0] = this.getPosY()+3;
		this.getTabY()[1] = this.getPosY()+3;
		this.getTabY()[2] = this.getPosY();
		this.getTabY()[3] = this.getPosY();
		this.getTabY()[4] = this.getPosY()+3;
		this.getTabY()[5] = this.getPosY()+3;
		this.getTabY()[6] = this.getPosY()+6;
		this.getTabY()[7] = this.getPosY()+6;
		this.getTabY()[8] = this.getPosY()+9;
		this.getTabY()[9] = this.getPosY()+9;
		this.getTabY()[10] = this.getPosY()+6;
		this.getTabY()[11] = this.getPosY()+6;*/

	}

}
