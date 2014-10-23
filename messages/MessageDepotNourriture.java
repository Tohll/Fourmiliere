package messages;

import fourmiz.FourmieAbstract;
import fourmiz.Ouvriere;
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
				
		super("De la nourriture a ete deposee par " +  emeteur.getType() , emeteur);
		this.quantiteNourriture = quantiteNourriture;
	}	
	
	@Override
	public void action(Fourmiliere f) {
		
		f.setStockNourriture(f.getStockNourriture() + this.quantiteNourriture);
	}
}
