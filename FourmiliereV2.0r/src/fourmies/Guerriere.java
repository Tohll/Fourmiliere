package fourmies;

import comportements.CompoGuerriere;
import deplacements.DeplacementNormal;
import mecaniques.Fourmiliere;

/**
 * La classe <b>Guerriere.java</b>
 * @author Seldan
 *
 */
public class Guerriere extends FourmieAbstract {

	/**
	 * Constructeur de la classe <b>Guerriere.java</b>:<br>
	 * Créé un objet paramétré de type <b>Guerriere</b>.
	 * @param fourmiliere
	 * @param indexPersonnel
	 */
	public Guerriere(Fourmiliere fourmiliere, int indexPersonnel) {
		super(fourmiliere,
				"Guerriere " + fourmiliere.getNbrGuerrieres(),
				25,
				2,
				new CompoGuerriere(),
				new DeplacementNormal(), 
				indexPersonnel);
		
		fourmiliere.setNbrGuerrieres(fourmiliere.getNbrGuerrieres() + 1);
		
	}

	/* (non-Javadoc)
	 * @see fourmies.FourmieAbstract#Action(mecaniques.Fourmiliere)
	 */
	@Override
	public void Action (Fourmiliere fourmiliere) {
		
		this.getComportement().Comportement(fourmiliere, this);
		
	}

}
