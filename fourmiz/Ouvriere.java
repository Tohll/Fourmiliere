package fourmiz;

import java.util.Random;

import mecaniques.Fourmiliere;

public final class Ouvriere extends FourmieAbstract {	
	
	private int nourritureTransportee;
	
	public int getNourritureTransportee() {
		return nourritureTransportee;
	}

	public void setNourritureTransportee(int nourritureTransportee) {
		this.nourritureTransportee = nourritureTransportee;
	}

	public Ouvriere() {
		
		super("Ouvriere " + nbrOuvriere , 50 , 1);
		this.nourritureTransportee = 0;
		nbrOuvriere++;		
	}

	@Override
	public void Comportement(Fourmiliere f) {		
		
		if (this.isEstActive()) {
			
			if (this.nourritureTransportee == 0) {
				
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
					
					this.setNourritureTransportee(3);
					f.getTerrain().getTerrain()[this.getChoix()].setStockNourriture(f.getTerrain().getTerrain()[this.getChoix()].getStockNourriture() - 3);				
					
					this.setPosition(f.getTerrain().getTerrain()[this.getChoix()].getDistance());
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") prend de la nourriture dans le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
				}
				
			} else {
				
				if (this.getPosition() <= f.getTerrain().getTerrain()[this.getChoix()].getDistance() && this.getPosition() > 0) {
					
					this.setPosition(this.getPosition() - this.getVitesse());
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") retourne a la fourmiliere avec " + this.getNourritureTransportee() + " unites de nourriture.");
					
				} else {
					
					this.setPosition(0);
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") est retournee a la fourmiliere avec " + this.getNourritureTransportee() + " unites de nourriture.");
					
					f.setStockNourriture(f.getStockNourriture() + this.getNourritureTransportee());
					this.setNourritureTransportee(0);
					
					System.out.println("Stock de la fourmiliere: " + f.getStockNourriture() + " unites de nourriture.");
					
				}
			} if (this.getPointsDeVie() <= 0) {
				
				this.setEstActive(false);
			}
		}
	}	
}
