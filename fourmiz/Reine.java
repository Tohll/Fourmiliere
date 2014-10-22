package fourmiz;

import mecaniques.Fourmiliere;
/**
 * @author Charbel FOUREL
 * @version 0.1
 *
 */
public final class Reine extends FourmieAbstract {

	private int cyclePonte;
	
	public int getCyclePonte() {
		return cyclePonte;
	}

	public void setCyclePonte(int cyclePonte) {
		this.cyclePonte = cyclePonte;
	}

	public Reine() {
		
		super("Reine" , 50000 , 0);
		this.cyclePonte = 1;
	}

	@Override
	public void Comportement(Fourmiliere f) {
		
		int i;
		
		if (this.getCyclePonte() < 5 && this.getCyclePonte() > 0) {
			
			this.setCyclePonte(this.getCyclePonte() + 1);
			
		} else if(this.getCyclePonte() == 0) {
			
			this.setCyclePonte(1);
			
			for (i=0 ; i<5 ; i++) {
				
				f.getPopulation().add(new Ouvriere());
			}
			
			for (i=0 ; i<3 ; i++) {
				
				f.getPopulation().add(new Guerriere());
			}
			
			for (i=0 ; i<2 ; i++) {
				
				f.getPopulation().add(new Soigneur());
			}
			
		} else {
			
			this.setCyclePonte(0);
			System.out.println("La "+ this.getType() + " donne naissance a de nouvelles fourmies");
			
		}
		
		if (f.getStockNourriture() > 0) {
			
			f.setStockNourriture(f.getStockNourriture() - 1);
			
		} else {
			
			//this.setPointsDeVie(this.getPointsDeVie() - 2);
			
		}
	}	
}
