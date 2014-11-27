package messages;

import fourmies.FourmieAbstract;
import mecaniques.Fourmiliere;

public class MessageDepotNourriture extends MessageAbstract {

	private int quantiteNourriture;
	
	int getQuantiteNourriture() {
		return quantiteNourriture;
	}

	void setQuantiteNourriture(int quantiteNourriture) {
		this.quantiteNourriture = quantiteNourriture;
	}

	public MessageDepotNourriture(FourmieAbstract emeteur , int quantiteNourriture) {
				
		super("De la nourriture a ete deposee par " +  emeteur.getNom() + "." , emeteur);
		this.quantiteNourriture = quantiteNourriture;
	}	
	
	@Override
	public void action(Fourmiliere f) {
		
		f.setStockNourriture(f.getStockNourriture() + this.quantiteNourriture);		
		
	}
}
