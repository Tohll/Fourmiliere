/**
 * 
 */
package fourmies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;

import plateau.CaseNourriture;
import plateau.Plateau;
import batiments.Fourmiliere;

/**
 * <b>Ouvriere.java</b><br><br>
 * 
 * Les ouvrieres empruntent les chemins traces par les scouts. Ils transportent la nourriture des sites de nourriture a la fourmiliere.
 * 
 * @since 10 nov. 2014
 * @version 0.1 
 * @author Charbel FOUREL
 *
 */
public class Ouvriere extends FourmieAbstract {

	private int nourritureTransportee;
	private boolean transporteuse;
	
	/**
	 * @return the nourritureTransportee
	 */
	public int getNourritureTransportee() {
		return nourritureTransportee;
	}

	/**
	 * @param nourritureTransportee the nourritureTransportee to set
	 */
	public void setNourritureTransportee(int nourritureTransportee) {
		this.nourritureTransportee = nourritureTransportee;
	}

	/**
	 * @return the transporteuse
	 */
	public boolean isTransporteuse() {
		return transporteuse;
	}

	/**
	 * @param transporteuse the transporteuse to set
	 */
	public void setTransporteuse(boolean transporteuse) {
		this.transporteuse = transporteuse;
	}

	/**
	 * Constructeur d'une ouvriere
	 * 
	 * @param f La fourmiliere associee a la fourmie.
	 */
	public Ouvriere(Fourmiliere f) {
				
		super(("Ouvriere " + f.getNom() + "-" + f.getNbrOuvrieres()), 1, 2000, f);
		
		Random rand = new Random();
		this.setPointsDeVie(this.getPointsDeVie() + rand.nextInt(750));
		
		f.setNbrOuvrieres(f.getNbrOuvrieres()+1);	
		
		this.nourritureTransportee = 0;
		this.transporteuse = false;
				
	}

	/* (non-Javadoc)
	 * @see fourmies.FourmieAbstract#comportement(batiments.Fourmiliere)
	 */
	@Override
	public void comportement(Fourmiliere f , Plateau plateau) {
		
		if (this.isActive()) {
			
			this.setPointsDeVie(this.getPointsDeVie()-1);
			
			if(!this.isTransporteuse()) {
				
				if (f.getSitesNourriture().size() > 0) {
					
					if (this.getChemin().isEmpty()) {
						
						Random rand = new Random();
						
						ArrayList<Integer> tabIndexs = new ArrayList<>();
						
						Enumeration<Integer> eSites = f.getSitesNourriture().keys();
						
						while (eSites.hasMoreElements()) {
							
							tabIndexs.add(eSites.nextElement());
							
						}
						
						this.setNodeDestination(f.getSitesNourriture().get(tabIndexs.get(rand.nextInt(tabIndexs.size()))));
						
						if (this.getNodeDestination().getChemin() != null) {
							
							this.setChemin(new ArrayList<>(this.getNodeDestination().getChemin()));
							
						}					
															
						this.setTransporteuse(true);
												
					}				
					
				}
				
			} else {
				
				if (this.getChemin().size() > 0) {
					
					this.deplacement(this.getChemin().get(this.getMarqueurChemin()));
					
					if (this.getPosX() == this.getChemin().get(this.getMarqueurChemin()).getPosX() && this.getPosY() == this.getChemin().get(this.getMarqueurChemin()).getPosY()) {
											
						if (this.getPointsDeVie() > 0) {
							
							if (this.getMarqueurChemin() == this.getChemin().size()-1) {
								
								if (this.getPosX() == f.getPosNode().getPosX() && this.getPosY() == f.getPosNode().getPosY()) {								
									//la fourmie ramene de la nourriture
									f.setStockNourriture(f.getStockNourriture()+this.getNourritureTransportee());							
									//la fourmie consomme de la nourriture
									f.setStockNourriture(f.getStockNourriture()-1);
									
									this.setNourritureTransportee(0);
									
									this.setTransporteuse(false);
									this.getChemin().clear();
									this.setMarqueurChemin(0);
									
								} else {							
										
									CaseNourriture tmp= (CaseNourriture) this.getNodeDestination();
									tmp.setStockNourriture(tmp.getStockNourriture()-4);															
										
									this.setNourritureTransportee(4);
									
									Collections.reverse(this.getChemin());
									this.setMarqueurChemin(0);
									
								}
								
							} else {
								
								this.setMarqueurChemin(this.getMarqueurChemin()+1);
								
							}
							
						} else {
							
							this.setNodeCourant(this.getChemin().get(this.getMarqueurChemin()));
							this.setActive(false);
							
						}
						
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
		
		if (this.isActive() && this.isTransporteuse()) {
			
			g.setColor(Color.black);
			g.fillOval(this.getPosX()-2, this.getPosY()-3, 6, 6);
			
		} else {
			
			g.setColor(Color.red);
			g.fillOval(this.getPosX()-2, this.getPosY()-3, 6, 6);
			
		}
		
	}

}
