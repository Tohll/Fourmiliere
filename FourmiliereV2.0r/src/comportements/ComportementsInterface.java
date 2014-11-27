package comportements;

import fourmies.Guerriere;
import fourmies.Ouvriere;
import fourmies.Reine;
import fourmies.Soigneur;
import mecaniques.Fourmiliere;

public interface ComportementsInterface {
	
	public void Comportement (Fourmiliere fourmiliere , Reine reine);
	public void Comportement (Fourmiliere fourmiliere , Ouvriere ouvriere);
	public void Comportement (Fourmiliere fourmiliere , Soigneur soigneur);
	public void Comportement (Fourmiliere fourmiliere , Guerriere guerriere);
}

