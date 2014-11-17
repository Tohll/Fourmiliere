/**
 * 
 */
package fourmies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import mecaniques.Pathfinding;
import plateau.CaseAbstract;
import plateau.CaseNourriture;
import plateau.Plateau;
import batiments.Fourmiliere;

/**
 * <b>Scout.java</b><br><br>
 * 
 * Les scouts ne s'affichent que si il y a trop peu de sites de nourriture connus par la fourmiliere.<br>
 * ils parcourent un chemin aleatoire jusqu'a une destination sur le plateau elle meme aleatoire. Si ils ne trouvent pas de site de nourriture sur leur chemin ils reviennent directement a la fourmiliere une fois arrive a destination.<br>
 * Si ils trouvent un site de nourriture ils se dirige vers lui puis tracent un chemin qui sera enregistre. C'est ce chemin que les ouvrieres emprunteront pour se rendre au site de nourriture. Une fois ceci fait le scout rentre a la fourmiliere
 * 
 * @since 10 nov. 2014
 * @version 0.1 
 * @author Charbel FOUREL
 *
 */
public class Scout extends FourmieAbstract {

	private boolean cherche;
	private boolean trouve;
	
	/**
	 * @return the trouve
	 */
	public boolean isTrouve() {
		return trouve;
	}

	/**
	 * @param trouve the trouve to set
	 */
	public void setTrouve(boolean trouve) {
		this.trouve = trouve;
	}

	/**
	 * @return the cherche
	 */
	public boolean isCherche() {
		return cherche;
	}

	/**
	 * @param cherche the cherche to set
	 */
	public void setCherche(boolean cherche) {
		this.cherche = cherche;
	}

	
	/**
	 * Constructeur des Scouts.
	 * 
	 * @param f La fourmiliere associee a la fourmie.
	 */
	public Scout(Fourmiliere f) {
		
		super("Scout " + f.getNom() + "-" + f.getNbrScouts(), 1, 1500, f);
		
		Random rand = new Random();
		this.setPointsDeVie(this.getPointsDeVie() + rand.nextInt(750));
		
		f.setNbrScouts(f.getNbrScouts()+1);
				
		this.cherche = false;
		this.trouve = false;
		
	}

	/* (non-Javadoc)
	 * @see fourmies.FourmieAbstract#comportement(batiments.Fourmiliere)
	 */
	@Override
	public void comportement(Fourmiliere f , Plateau plateau) {
		
		if (this.isActive()) {
			
			this.setPointsDeVie(this.getPointsDeVie()-1);
						
			if (!this.isCherche()) {
				
				if(this.getPointsDeVie() > 0) {
					
					this.setMarqueurChemin(0);
					Random rand = new Random();
					int test;
					int test2;				
					
					if (f.getSitesNourriture().size() < 5) {
						
						test = rand.nextInt(30)+3;
						test2 = rand.nextInt(30)+3;
						
						int x = 0;
						int y = 0;
						
						do {						
							
							x = test + f.getPosNode().getPosNode().getColonne();
							y = test2 + f.getPosNode().getPosNode().getLigne();
							
							
						} while(x < 4 || x > plateau.NBR_COL-3 || y < 4 || y > plateau.NBR_LIG-3);
											
						this.setNodeDestination(plateau.getTabCases()[y][x]);					
							
						if(!this.getNodeDestination().equals(this.getNodeCourant())) {
							
							do { 
								
								this.setChemin(new ArrayList<>(Pathfinding.trouverChemin(this.getNodeCourant(), this.getNodeDestination(), plateau)));
								
							} while (this.getChemin().isEmpty());
							
							this.setCherche(true);					
							
						}										
						
					}
					
				} else {
					
					this.setVivante(false);
					
				}			
				
			} else {
				if (this.getChemin().size() > this.getMarqueurChemin()) {
					
					deplacement(this.getChemin().get(this.getMarqueurChemin()));
					
					if (this.getPosX() == this.getChemin().get(this.getMarqueurChemin()).getPosX() && this.getPosY() == this.getChemin().get(this.getMarqueurChemin()).getPosY()) {
						
						if(this.getPointsDeVie() > 0) {
							
							if (this.getMarqueurChemin() == this.getChemin().size()-1) {
								
								if (this.getChemin().get(this.getMarqueurChemin()).equals(f.getPosNode())) {
									
									this.setCherche(false);
									this.setTrouve(false);
									this.setMarqueurChemin(0);
									this.getChemin().clear();
									
								} else {
									
									if (!analyserTerrain(this.getChemin(), this.getMarqueurChemin(), plateau, f)) {
										
										this.setTrouve(true);
										Collections.reverse(this.getChemin());
										this.setMarqueurChemin(0);
										
									}
									
								}
								
							} else {							
								
								if (!this.isTrouve()) {
									
									if (!analyserTerrain(this.getChemin(), this.getMarqueurChemin(), plateau, f)) {
										
										this.setMarqueurChemin(this.getMarqueurChemin()+1);
										
									}
									
								} else {
									
									this.setMarqueurChemin(this.getMarqueurChemin()+1);
									
								}							
								
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
		
		if (this.isActive()) {
			
			if (this.isCherche()) {
				
				g.setColor(new Color(204 , 163 , 0));
				g.fillOval(this.getPosX()-2, this.getPosY()-4, 8, 8);
				
			}
			
		} else {
			
			g.setColor(Color.red);
			g.fillOval(this.getPosX()-2, this.getPosY()-4, 8, 8);
			
		}

	}
		
	/**
	 * Permet d'analyser les cases voisines pour chercher un potentiel site de nourriture non detecte encore.
	 * 
	 * @param chemin Le chemin sur lequel se trouve le node en cours d'analyse
	 * @param marqueur Le marqueur qui permet de trouver le node sur le chemin.
	 * @param plateau Le plateau sur lequel se trouvent les nodes.
	 * @param f La fourmiliere associee a la fourmie.
	 * @return <b>true</b> si un site a ete trouve, ou <b>false</b> si aucun site n'a ete trouve.
	 */
	private boolean analyserTerrain (ArrayList<CaseAbstract> chemin , int marqueur , Plateau plateau , Fourmiliere f) {
		
		boolean trouver = false;
		
		CaseAbstract nodeAnalyse = chemin.get(marqueur);
		int x = nodeAnalyse.getPosNode().getColonne();
		int y = nodeAnalyse.getPosNode().getLigne();
		
		ArrayList<CaseAbstract> tabNodes = new ArrayList<>();
		
		tabNodes.add(plateau.getTabCases()[y-1][x-1]);
		tabNodes.add(plateau.getTabCases()[y-1][x]);
		tabNodes.add(plateau.getTabCases()[y-1][x+1]);
		tabNodes.add(plateau.getTabCases()[y][x+1]);
		tabNodes.add(plateau.getTabCases()[y+1][x+1]);
		tabNodes.add(plateau.getTabCases()[y+1][x]);
		tabNodes.add(plateau.getTabCases()[y+1][x-1]);
		tabNodes.add(plateau.getTabCases()[y][x-1]);
		
		for (int i=0 ; i<tabNodes.size() ; i++) {
			
			if (tabNodes.get(i) instanceof CaseNourriture && !tabNodes.get(i).isObstacle()) {
				
				CaseNourriture nodeNourriture = (CaseNourriture) tabNodes.get(i);
				
				if (!nodeNourriture.isDecouvert()) {
					
					trouver = true;
					
					this.setTrouve(true);					
					
					f.getSitesNourriture().put(f.getIndexNourriture(), nodeNourriture);
					f.setIndexNourriture(f.getIndexNourriture()+1);
					
					do {
						this.getChemin().clear();
						this.setChemin(Pathfinding.trouverChemin(nodeNourriture, f.getPosNode(), plateau));
						
					} while (this.getChemin().isEmpty());				
					
					nodeNourriture.setChemin(new ArrayList<CaseAbstract>(this.getChemin()));
					Collections.reverse(nodeNourriture.getChemin());				
					
					this.setMarqueurChemin(0);
												
					nodeNourriture.setDecouvert(true);
					
					break;					
					
				}
				
			}
			
		}
		
		
		
		return trouver;
		
	}

}
