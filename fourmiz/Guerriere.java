package fourmiz;

import mecaniques.Fourmiliere;
/**
 * @author Charbel FOUREL
 * @version 0.1
 *
 */
public final class Guerriere extends FourmieAbstract {

	public Guerriere() {
		
		super("Guerriere " + nbrGuerriere , 100 , 2);
		nbrGuerriere++;		
	}

	@Override
	public void Comportement(Fourmiliere fourmiliere) {	
		
	}	
}
