/**
 * 
 */
package fourmies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;

import mecaniques.Pathfinding;
import plateau.Plateau;
import batiments.Fourmiliere;

/**
 * <b>Soigneuse.java</b><br><br>
 * 
 * Les soigneuses ne s'affichent a l'ecran que si elles sont en mission vers une fourmie inactive.<br>
 * Elles trouvent un chemin vers les fourmies inactives sur le terrain et les ramenent a la fourmiliere. une fois ramenees, les fourmies inactives sont considerees mortes et nettoyees par le systeme.
 * 
 * @since 11 nov. 2014
 * @version 0.1 
 * @author Charbel FOUREL
 *
 */
public class Soigneuse extends FourmieAbstract {

	private boolean enService;
	private boolean transporte;
	
	private int[] tabX = {this.getPosX(),this.getPosX()+3,this.getPosX()+3,this.getPosX()+6,this.getPosX()+6,this.getPosX()+9,this.getPosX()+9,this.getPosX()+6,this.getPosX()+6,this.getPosX()+3,this.getPosX()+3,this.getPosX()};
	private int[] tabY = {this.getPosY()+3,this.getPosY()+3,this.getPosY(),this.getPosY(),this.getPosY()+3,this.getPosY()+3,this.getPosY()+6,this.getPosY()+6,this.getPosY()+9,this.getPosY()+9,this.getPosY()+6,this.getPosY()+6};
	
	private FourmieAbstract fourmieChoisie;
		
	/**
	 * @return the tabY
	 */
	public int[] getTabY() {
		return tabY;
	}

	/**
	 * @param tabY the tabY to set
	 */
	public void setTabY(int[] tabY) {
		this.tabY = tabY;
	}

	/**
	 * @return the tabX
	 */
	public int[] getTabX() {
		return tabX;
	}

	/**
	 * @param tabX the tabX to set
	 */
	public void setTabX(int[] tabX) {
		this.tabX = tabX;
	}

	/**
	 * @return the transporte
	 */
	public boolean isTransporte() {
		return transporte;
	}

	/**
	 * @param transporte the transporte to set
	 */
	public void setTransporte(boolean transporte) {
		this.transporte = transporte;
	}

	/**
	 * @return the fourmieChoisie
	 */
	public FourmieAbstract getFourmieChoisie() {
		return fourmieChoisie;
	}

	/**
	 * @param fourmieChoisie the fourmieChoisie to set
	 */
	public void setFourmieChoisie(FourmieAbstract fourmieChoisie) {
		this.fourmieChoisie = fourmieChoisie;
	}

	/**
	 * @return the enService
	 */
	public boolean isEnService() {
		return enService;
	}

	/**
	 * @param enService the enService to set
	 */
	public void setEnService(boolean enService) {
		this.enService = enService;
	}

	/**
	 * @param f
	 */
	public Soigneuse(Fourmiliere f) {
		
		super("Soigneuse " + f.getNom() + "-" + f.getNbrSoigneuses() , 1 , 1000 , f);
		
		Random rand = new Random();
		this.setPointsDeVie(this.getPointsDeVie() + rand.nextInt(750));
		
		f.setNbrSoigneuses(f.getNbrSoigneuses()+1);
				
		this.enService = false;
		this.transporte = false;
		this.fourmieChoisie = null;
		
	}

	/* (non-Javadoc)
	 * @see fourmies.FourmieAbstract#comportement(batiments.Fourmiliere, plateau.Plateau)
	 */
	@Override
	public void comportement(Fourmiliere f, Plateau plateau) {
		
		this.getTabX()[0] = this.getPosX();
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
		this.getTabY()[11] = this.getPosY()+6;
		
		if (this.isActive()) {
			
			this.setPointsDeVie(this.getPointsDeVie()-1);
						
			if (!this.isEnService()) {
				
				if (this.getPointsDeVie() > 0) {
					
					Enumeration<Integer> ePop = f.getPopulation().keys();
					
					while (ePop.hasMoreElements()) {
						
						int tmp = ePop.nextElement();
						
						if(f.getPopulation().get(tmp) != null) {
							
							if (!f.getPopulation().get(tmp).isActive() && !f.getPopulation().get(tmp).isPriseEnCharge()) {
								
								f.getPopulation().get(tmp).setPriseEnCharge(true);
								this.setEnService(true);
								this.setNodeDestination(f.getPopulation().get(tmp).getNodeCourant());
								this.setFourmieChoisie(f.getPopulation().get(tmp));
								do {
									
									this.setChemin(Pathfinding.trouverChemin(this.getNodeCourant(), this.getNodeDestination(), plateau));
									
								} while (this.getChemin().isEmpty());
								
								
								break;
								
							}
							
						}						
						
					}
					
				} else {
					
					this.setVivante(false);
					
				}
				
				
				
			} else {
				
				if (this.getChemin().size() > 0) {
					
					if (this.getPointsDeVie() > 0) {					
						
						if (this.getChemin().size() > this.getMarqueurChemin()) {
							
							this.deplacement(this.getChemin().get(this.getMarqueurChemin()));
							
							if (this.getPosX() == this.getChemin().get(this.getMarqueurChemin()).getPosX() && this.getPosY() == this.getChemin().get(this.getMarqueurChemin()).getPosY()) {
								
								if (this.getMarqueurChemin() == this.getChemin().size()-1) {
									
									if (this.getPosX() == f.getPosNode().getPosX() && this.getPosY() == f.getPosNode().getPosY()) {
										
										if (f.getStockNourriture() >= 5) {
											
											f.setStockNourriture(f.getStockNourriture()-5);
											
										} else {
											
											f.setStockNourriture(0);
											this.setPointsDeVie(this.getPointsDeVie()-400);
											
										}
										
										this.getChemin().clear();
										this.setMarqueurChemin(0);
										this.setEnService(false);
										this.setTransporte(false);
										this.getFourmieChoisie().setVivante(false);
										this.setFourmieChoisie(null);
										
									} else {
										
										this.setTransporte(true);
										this.setMarqueurChemin(0);
										Collections.reverse(this.getChemin());
										
									}
									
								} else {
									
									this.setMarqueurChemin(this.getMarqueurChemin()+1);
									
								}					
								
							}
							
						}
						
						
						
					} else {
						
						this.setNodeCourant(this.getChemin().get(this.getMarqueurChemin()));
						this.setActive(false);					
						this.setTransporte(false);
						this.getFourmieChoisie().setPriseEnCharge(false);
						this.getFourmieChoisie().setNodeCourant(this.getChemin().get(this.getMarqueurChemin()));						
						
					}
					
				}
				
				
				
			}
			
		}

	}

	/* (non-Javadoc)
	 * @see fourmies.FourmieAbstract#afficher(java.awt.Graphics)
	 */
	@Override
	public void afficher(Graphics g) {
		
		if (this.isActive()) {
			
			if (this.isEnService()) {
				
				if (this.isTransporte()) {
					
					g.setColor(Color.white);
					g.fillPolygon(this.getTabX() , this.getTabY() , 12);
					
					if(this.getFourmieChoisie() != null) {
						
						this.getFourmieChoisie().setPosX(this.getPosX());
						this.getFourmieChoisie().setPosY(this.getPosY());
						
					}					
					
				} else {
					
					g.setColor(Color.white);
					g.fillPolygon(this.getTabX() , this.getTabY() , 12);
					
				}
				
			}
			
		} else {
			
			g.setColor(Color.red);
			g.fillPolygon(this.getTabX() , this.getTabY() , 12);
			
		}

	}

}
