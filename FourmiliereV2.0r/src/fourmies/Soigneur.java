package fourmies;

import comportements.CompoSoigneur;
import deplacements.DeplacementNormal;
import mecaniques.Fourmiliere;

public class Soigneur extends FourmieAbstract {

	private boolean enService;
	
	/**
	 * Accesseur de l'attribut: <i>enService</i>.
	 * @return la valeur de l'attribut <b>boolean</b>: <i>enService</i>
	 */
	public boolean isEnService() {
		return enService;
	}

	/**
	 * Mutateur de l'attribut: <i>enService.</i>
	 * @param enService de type <b>boolean</b>, définit la valeur de l'attribut: <i>enService.</i>
	 */
	public void setEnService(boolean enService) {
		this.enService = enService;
	}
		
	/**
	 * Constructeur de la classe <b>Soigneur.java</b>:<br>
	 * Créé un objet paramétré de type <b>Soigneur</b>.
	 * @param fourmiliere
	 * @param index
	 */
	public Soigneur(Fourmiliere fourmiliere, int index) {
		
		super(fourmiliere, 
				"Soigneur " + fourmiliere.getNbrSoigneurs(), 
				20, 
				1, 
				new CompoSoigneur(), 
				new DeplacementNormal(), 
				index);
	
		this.enService = false;
		
		fourmiliere.setNbrSoigneurs(fourmiliere.getNbrSoigneurs() + 1);
	}

	/* (non-Javadoc)
	 * @see fourmies.FourmieAbstract#Action(mecaniques.Fourmiliere)
	 */
	@Override
	public void Action (Fourmiliere fourmiliere) {
		
		this.getComportement().Comportement(fourmiliere, this);
		
	}

	

}
