package messages;

import fourmies.FourmieAbstract;
import fourmies.Guerriere;
import fourmies.Ouvriere;
import fourmies.Soigneur;
import mecaniques.Fourmiliere;

public class MessageNaissances extends MessageAbstract {

	public MessageNaissances(FourmieAbstract emeteur) {
		
		super("Dix nouvelles fourmies viennent dans la fourmiliere.", emeteur);

	}

	@Override
	public void action(Fourmiliere fourmiliere) {
	
		int i;
			
		for(i=0 ; i<5 ; i++) {
			
			int index = fourmiliere.getIndexActives();
			fourmiliere.getPopulationActive().put(index, new Ouvriere(fourmiliere, index));
			
		}
		
		for(i=0 ; i<3 ; i++) {
			
			int index = fourmiliere.getIndexActives();
			fourmiliere.getPopulationActive().put(index, new Guerriere(fourmiliere, index));
			
		}

		for(i=0 ; i<3 ; i++) {
			
			int index = fourmiliere.getIndexActives();
			fourmiliere.getPopulationActive().put(index, new Soigneur(fourmiliere, index));
			
		}
		
		

	}

}
