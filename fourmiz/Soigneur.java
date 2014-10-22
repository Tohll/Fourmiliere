package fourmiz;

import mecaniques.Fourmiliere;

public final class Soigneur extends FourmieAbstract {

	public Soigneur() {
		
		super("Soigneur " + nbrSoigneur , 100 , 2);
		nbrSoigneur++;
	}
	
	@Override
	public void Comportement(Fourmiliere fourmiliere) {		

	}

}
