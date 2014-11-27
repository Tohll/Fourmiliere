package comportements;

import mecaniques.Fourmiliere;
import messages.BoiteMessagesSingleton;
import messages.MessageNaissances;
import fourmies.Guerriere;
import fourmies.Ouvriere;
import fourmies.Reine;
import fourmies.Soigneur;

public class CompoReine implements ComportementsInterface {

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Reine)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Reine reine) {
		
		reine.setCycle(reine.getCycle() + 1);
		
		if (reine.getCycle() == 5) {
			
			reine.setCycle(0);
			
			System.out.println("La reine donne naissance a de nouvelles fourmies.");
			
			BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageNaissances(reine));
			
		}
		
	}

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Soigneur)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Soigneur soigneur) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Guerriere)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Guerriere guerriere) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Ouvriere)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Ouvriere ouvriere) {
		// TODO Auto-generated method stub
		
	}

}
