package fourmiz;

import java.util.Random;

import mecaniques.Fourmiliere;
import messages.BoiteMessagesSingleton;
import messages.MessageDepotNourriture;
/**
 * @author Charbel FOUREL
 * @version 0.1
 *
 */
public final class Ouvriere extends FourmieAbstract {	
	
	private int nourritureTransportee;
	
	public int getNourritureTransportee() {
		return nourritureTransportee;
	}

	public void setNourritureTransportee(int nourritureTransportee) {
		this.nourritureTransportee = nourritureTransportee;
	}

	public Ouvriere() {
		
		super("Ouvriere " + nbrOuvriere , 10 , 1);
		this.nourritureTransportee = 0;
		nbrOuvriere++;		
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
					
				} else {
					
					this.setAller(false);
					this.setNourritureTransportee(2);
					f.getTerrain().getTerrain()[this.getChoix()].setStockNourriture(f.getTerrain().getTerrain()[this.getChoix()].getStockNourriture() - 3);				
					
					this.setPosition(f.getTerrain().getTerrain()[this.getChoix()].getDistance());
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") prend de la nourriture dans le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
				}
				
			} else {
				
				if (this.getPosition() <= f.getTerrain().getTerrain()[this.getChoix()].getDistance() && this.getPosition() > 0) {
					
					this.setPosition(this.getPosition() - this.getVitesse());
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") retourne a la fourmiliere.");
					
				} else {
					
					this.setAller(true);
					this.setPosition(0);
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") est retournee a la fourmiliere avec " + this.getNourritureTransportee() + " unites de nourriture.");
					
					BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageDepotNourriture(this,this.getNourritureTransportee()));
					
					this.setNourritureTransportee(0);					
					
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
