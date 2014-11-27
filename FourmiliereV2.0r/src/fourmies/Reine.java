package fourmies;

import comportements.CompoReine;
import deplacements.DeplacementNull;
import mecaniques.Fourmiliere;

public class Reine extends FourmieAbstract {

	private int cycle;
		
	/**
	 * Accesseur de l'attribut: <i>cycle</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>cycle</i>
	 */
	public int getCycle() {
		return cycle;
	}

	/**
	 * Mutateur de l'attribut: <i>cycle.</i>
	 * @param cycle de type <b>int</b>, définit la valeur de l'attribut: <i>cycle.</i>
	 */
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
		
	/**
	 * Constructeur de la classe <b>Reine.java</b>:<br>
	 * Créé un objet paramétré de type <b>Reine</b>.
	 * @param fourmiliere
	 * @param index
	 */
	public Reine (Fourmiliere fourmiliere, int index) {
		
		super(fourmiliere, 
				"Reine", 
				50000, 
				0, 
				new CompoReine(), 
				new DeplacementNull(), 
				index);
		
		this.cycle = 0;
		
	}

	/* (non-Javadoc)
	 * @see fourmies.FourmieAbstract#Action(mecaniques.Fourmiliere)
	 */
	@Override
	public void Action(Fourmiliere fourmiliere) {
		
		this.getComportement().Comportement(fourmiliere, this);
		
	}

}
