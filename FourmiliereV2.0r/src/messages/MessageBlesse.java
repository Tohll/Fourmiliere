package messages;

import mecaniques.Fourmiliere;
import fourmies.FourmieAbstract;

public class MessageBlesse extends MessageAbstract {
	
	public MessageBlesse(FourmieAbstract emeteur) {
		
		super("La fourmie " + emeteur.getNom() + " est blessee et a besoin d'un soigneur.", emeteur);
				
	}

	@Override
	public void action(Fourmiliere f) {
				
		f.getPopulationInactive().put(this.getEmeteur().getIndexPerso(), this.getEmeteur());
		f.getPopulationActive().remove(this.getEmeteur().getIndexPerso());
				
	}

}
