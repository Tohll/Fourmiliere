package messages;

import mecaniques.Fourmiliere;
import fourmiz.FourmieAbstract;
import fourmiz.Guerriere;
import fourmiz.Ouvriere;
import fourmiz.Soigneur;

public class MessageNaissances extends MessageAbstract {

	public MessageNaissances(FourmieAbstract emeteur) {
		
		super("Dix nouvelles fourmies viennent dans la fourmiliere", emeteur);

	}

	@Override
	public void action(Fourmiliere f) {
	
		int i;
			
		for (i=0 ; i<5 ; i++) {
			
			f.getPopulation().put(f.getIndexPop(), new Ouvriere(f));
		}
		
		for (i=0 ; i<3 ; i++) {
			
			f.getPopulation().put(f.getIndexPop(), new Guerriere(f));
		}
		
		for (i=0 ; i<2 ; i++) {
			
			f.getPopulation().put(f.getIndexPop(), new Soigneur(f));
		}
		
		

	}

}
