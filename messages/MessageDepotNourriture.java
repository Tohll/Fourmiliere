package messages;

import mecaniques.Fourmiliere;

public class MessageDepotNourriture extends MessageAbstract {

	public MessageDepotNourriture() {
		
		super("De la nourriture a ete deposee");		
	}

	@Override
	public void action(Fourmiliere f) {
		
		f.setStockNourriture(f.getStockNourriture() + 3);
	}
}
