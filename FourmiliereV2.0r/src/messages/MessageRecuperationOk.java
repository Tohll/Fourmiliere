package messages;

import fourmies.FourmieAbstract;
import mecaniques.Fourmiliere;

public class MessageRecuperationOk extends MessageAbstract {
	
	public MessageRecuperationOk(FourmieAbstract emeteur) {
		
		super("La fourmie " + emeteur.getNom() + " a ramené un cadavre a la fourmiliere.", emeteur);
		
	}

	@Override
	public void action(Fourmiliere fourmiliere) {		
		
			fourmiliere.getPopulationInactive().remove(this.getEmeteur().getChoix());
			this.getEmeteur().setChoix(-1);
		
	}

}
