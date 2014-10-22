package fourmiz;

import mecaniques.Fourmiliere;

public final class Guerriere extends FourmieAbstract {

	public Guerriere() {
		
		super("Guerriere " + nbrGuerriere , 100 , 2);
		nbrGuerriere++;		
	}

	@Override
	public void Comportement(Fourmiliere fourmiliere) {	
		
	}	
}
