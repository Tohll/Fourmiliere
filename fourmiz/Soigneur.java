package fourmiz;

import mecaniques.Fourmiliere;
/**
 * @author Charbel FOUREL
 * @version 0.1
 *
 */
public final class Soigneur extends FourmieAbstract {

	public Soigneur() {
		
		super("Soigneur " + nbrSoigneur , 100 , 2);
		nbrSoigneur++;
	}
	
	@Override
	public void Comportement(Fourmiliere fourmiliere) {		

	}

}
