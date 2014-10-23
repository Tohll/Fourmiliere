package fourmiz;

import java.util.Random;

import mecaniques.Fourmiliere;
/**
 * @author Charbel FOUREL
 * @version 0.1
 *
 */
public final class Guerriere extends FourmieAbstract {

	
	
	public Guerriere() {
		
		super("Guerriere " + nbrGuerriere , 15 , 2);
		nbrGuerriere++;		
	}

	@Override
	public void Comportement(Fourmiliere f) {	
		
		if (this.isEstActive()) {
			
			if (this.isAller()) {
				
				if (this.getPosition() == 0) {
					
					Random choixAlea = new Random();
					
					this.setChoix(choixAlea.nextInt(f.getTerrain().getTerrain().length));
					
					this.setPosition(this.getPosition() + this.getVitesse());
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") avance vers le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
					
				} else if (this.getPosition() > 0 && this.getPosition() < f.getTerrain().getTerrain()[this.getChoix()].getDistance()) {
					
					this.setPosition(this.getPosition() + this.getVitesse());
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") avance vers le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
					
				}  else {								
					
					this.setPosition(f.getTerrain().getTerrain()[this.getChoix()].getDistance());
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					this.setAller(false);
					
					System.out.println("La fourmie (" + this.getType() + ") patrouille le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
				}
				
			} else {
				
				if (this.getPosition() <= f.getTerrain().getTerrain()[this.getChoix()].getDistance() && this.getPosition() > 0) {
					
					this.setPosition(this.getPosition() - this.getVitesse());
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") retourne a la fourmiliere.");
					
				} else {
					
					this.setPosition(0);
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					this.setAller(true);
					
					System.out.println("La fourmie (" + this.getType() + ") patrouille la fourmiliere.");									
					
					if (f.getStockNourriture() > 0) {
						
						f.setStockNourriture(f.getStockNourriture() - 1);
						
					} else {
						
						this.setPointsDeVie(this.getPointsDeVie() - 2);
						
					}				
				}
			}
			
			if (this.getPointsDeVie() <= 0) {
				
				this.setEstActive(false);
			}			
		}
	}	
}
