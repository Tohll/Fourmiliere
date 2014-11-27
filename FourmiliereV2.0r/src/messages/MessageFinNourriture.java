/**
 * 
 */
package messages;

import fourmies.FourmieAbstract;
import mecaniques.Fourmiliere;

/**
 * La classe <b>MessageFinNourriture.java</b>
 * @author Seldan
 *
 */
public class MessageFinNourriture extends MessageAbstract {
	
	/**
	 * Constructeur de la classe <b>MessageFinNourriture.java</b>:<br>
	 * Créé un objet paramétré de type <b>MessageFinNourriture</b>.
	 * @param emeteur
	 * @param fourmiliere
	 */
	public MessageFinNourriture(FourmieAbstract emeteur, Fourmiliere fourmiliere) {
		
		super(emeteur.getNom() + " signale que le " + fourmiliere.getTabSites()[emeteur.getChoix()].getNom() + " est vide.", emeteur);
		
	}

	/* (non-Javadoc)
	 * @see messages.MessageAbstract#action(mecaniques.Fourmiliere)
	 */
	@Override
	public void action(Fourmiliere f) {
		

	}

}
