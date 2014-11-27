/**
 * 
 */
package fourmies;

import comportements.CompoOuvriere;
import deplacements.DeplacementNormal;
import mecaniques.Fourmiliere;

/**
 * La classe <b>Ouvriere.java</b>
 * @author Seldan
 *
 */
public class Ouvriere extends FourmieAbstract {
	
	private int nourritureTransportee;
	
	/**
	 * Accesseur de l'attribut: <i>nourritureTransportee</i>.
	 * @return la valeur de l'attribut <b>int</b>: <i>nourritureTransportee</i>
	 */
	public int getNourritureTransportee() {
		return nourritureTransportee;
	}

	/**
	 * Mutateur de l'attribut: <i>nourritureTransportee.</i>
	 * @param nourritureTransportee de type <b>int</b>, définit la valeur de l'attribut: <i>nourritureTransportee.</i>
	 */
	public void setNourritureTransportee(int nourritureTransportee) {
		this.nourritureTransportee = nourritureTransportee;
	}
	
	/**
	 * Constructeur de la classe <b>Ouvriere.java</b>:<br>
	 * Créé un objet paramétré de type <b>Ouvriere</b>.
	 * @param fourmiliere
	 * @param index
	 */
	public Ouvriere(Fourmiliere fourmiliere, int index) {
		
		super(fourmiliere,
				"Ouvriere " + fourmiliere.getNbrOuvrieres(),
				10,
				1,
				new CompoOuvriere(),
				new DeplacementNormal(), 
				index);
		
		fourmiliere.setNbrOuvrieres(fourmiliere.getNbrOuvrieres() + 1);
		
		this.nourritureTransportee = 0;
		
	}

	/* (non-Javadoc)
	 * @see fourmies.FourmieAbstract#Action(mecaniques.Fourmiliere)
	 */
	@Override
	public void Action (Fourmiliere fourmiliere) {
		
		this.getComportement().Comportement(fourmiliere, this);
		
	}

}
