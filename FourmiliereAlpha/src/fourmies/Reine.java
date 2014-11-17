/**
 * 
 */
package fourmies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import mecaniques.Pathfinding;
import plateau.Plateau;
import batiments.Fourmiliere;

/**
 * <b>Reine.java</b><br><br>
 * 
 * La reine a deux modes comportementaux:<br>
 * - Soit elle est une pondeuse et dans ce cas elle reste dans la fourmiliere et genere des pontes de maniere reguliere tant que les ressources necessaires sont disponbiles et en fonction de ces dernieres.<br>
 * - Soit elle n'est pas en mode pondeuse et dans ce cas elle parcourt la carte jusqu'a trouver un endroit propice pour installer une nouvelle fourmiliere, auquel cas elle passera en mode pondeuse a l'interieur de la nouvelle fourmiliere.
 * 
 * @since 10 nov. 2014
 * @version 0.1 
 * @author Charbel FOUREL
 *
 */
public class Reine extends FourmieAbstract {

	private boolean pondeuse;
	
	/**
	 * @return the pondeuse
	 */
	public boolean isPondeuse() {
		return pondeuse;
	}

	/**
	 * @param pondeuse the pondeuse to set
	 */
	public void setPondeuse(boolean pondeuse) {
		this.pondeuse = pondeuse;
	}

	
	/**
	 * Initialise une reine parametree.
	 * @param pondeuse Definit si la reine est en mode pondeuse (<b>true</b>) ou pas (<b>false</b>). Doit etre defini.
	 * @param f la fourmiliere associee a la reine.
	 */
	public Reine(boolean pondeuse , Fourmiliere f) {
		
		super("Reine " + nbrReines , 1 , 5000 , f);
		
		nbrReines++;		
		
		this.pondeuse = pondeuse;		
		
	}

	/* (non-Javadoc)
	 * @see fourmies.FourmieAbstract#comportement()
	 */
	@Override
	public void comportement(Fourmiliere f ,Plateau plateau) {
		
		if (this.isActive()) {
			
			if (this.isPondeuse()) {
				
				this.setCycle(this.getCycle()+1);
				//cycle secondaire
				if (this.getCycle() == 500) {
					
					if (f.getStockNourriture() >= 10) {
						
						f.setStockNourriture(f.getStockNourriture()-10);
						
						if (f.getSitesNourriture().size() < 5) {
							
							for (int i=0 ; i<1 ; i++) {
								
								f.getPopulation().put(f.getIndexPop(), new Scout(f));
																
							}
							
						}
						
						if (f.getStockNourriture() > 550 && f.isFertile()) {
							
							f.getPopulation().put(f.getIndexPop(), new Reine(false, f));
							f.setStockNourriture(f.getStockNourriture()-500);
							f.setFertile(false);
						}
						
					} else {
						
						this.setPointsDeVie(this.getPointsDeVie() - (10 - f.getStockNourriture()));
						f.setStockNourriture(0);
						
					}					
					
				}
				//cycle principal
				if (this.getCycle() == 1000) {
					
					if (f.getStockNourriture() >= 15) {
						
						f.setStockNourriture(f.getStockNourriture()-15);												
						
						for (int i=0 ; i<10 ; i++) {
							
							f.getPopulation().put(f.getIndexPop(), new Ouvriere (f));						
							
						}
						
						for (int i=0 ; i<3 ; i++) {
							
							f.getPopulation().put(f.getIndexPop(), new Soigneuse(f));						
							
						}						
												
					} else {
						
						this.setPointsDeVie(this.getPointsDeVie() - (15 - f.getStockNourriture()));
						f.setStockNourriture(0);
						
						f.getPopulation().put(f.getIndexPop(), new Ouvriere (f));
						f.getPopulation().put(f.getIndexPop(), new Scout(f));
						
					}
					
					this.setCycle(0);
					
				}
				
				if (this.getPointsDeVie() <= 0) {
					
					this.setActive(false);
					
				}
				
			} else {
				
				if (this.getChemin().isEmpty()) {
					
					Random rand = new Random();
					int test;
					int test2;					
					
					test = rand.nextInt(20)+15;
					test2 = rand.nextInt(20)+15;
					
					int x = 0;
					int y = 0;
					
					do {
						
						this.getChemin().clear();
						
						do {						
							
							x = test + f.getPosNode().getPosNode().getColonne();
							y = test2 + f.getPosNode().getPosNode().getLigne();
							
							
						} while(x < 4 || x > plateau.NBR_COL-4 || y < 4 || y > plateau.NBR_LIG-4);
						
						this.setNodeDestination(plateau.getTabCases()[y][x]);
						
						if (!this.getNodeDestination().isObstacle() || !this.getNodeDestination().isNourriture()) {
														
							this.setChemin(new ArrayList<>(Pathfinding.trouverChemin(this.getNodeCourant(), this.getNodeDestination(), plateau)));
							
						}
						
					} while (this.getChemin().isEmpty());					
					
				} else {
					
					if (this.getChemin().size() > this.getMarqueurChemin()) {
						
						this.deplacement(this.getChemin().get(this.getMarqueurChemin()));
						
						if (this.getPosX() == this.getChemin().get(this.getMarqueurChemin()).getPosX() && this.getPosY() == this.getChemin().get(this.getMarqueurChemin()).getPosY()) {
							
							if (this.getMarqueurChemin() == this.getChemin().size()-1) {
								
								Fourmiliere newFourmiliere = new Fourmiliere(plateau.getTabCases()[this.getNodeDestination().getPosNode().getLigne()][this.getNodeDestination().getPosNode().getColonne()], plateau);
								plateau.getFourmilieresTab().put(Plateau.indexFourmilieres, newFourmiliere);
								this.setVivante(false);							
								
							} else {
								
								this.setMarqueurChemin(this.getMarqueurChemin()+1);
								
							}
							
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
		
		if (!this.isPondeuse()) {
			
			g.setColor(Color.blue);
			g.fillOval(this.getPosX()-2, this.getPosY()-4, 8, 8);
			
		}
		
	}	

}
