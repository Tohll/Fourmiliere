/**
 * 
 */
package comportements;

import java.util.Random;

import javax.swing.JOptionPane;

import mecaniques.Fourmiliere;
import messages.BoiteMessagesSingleton;
import messages.MessageBlesse;
import messages.MessageDepotNourriture;
import messages.MessageFinNourriture;
import fourmies.Guerriere;
import fourmies.Ouvriere;
import fourmies.Reine;
import fourmies.Soigneur;

/**
 * La classe <b>CompoOuvriere.java</b>
 * @author Seldan
 *
 */
public class CompoOuvriere implements ComportementsInterface {

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Reine)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Reine reine) {
		

	}

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Ouvriere)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Ouvriere ouvriere) {
					
		ouvriere.perteDeVie(1);
		
		if (ouvriere.getPointsDeVie() > 0) {
			
			if (ouvriere.isAller()) {	//sur l'aller
				
				if (ouvriere.getPosCourante() == 0) {	//positionnee a la fourmiliere
					
					int compteurSitesVides = 0;
					Random aleatoire = new Random();
					
					for (int i=0 ; i<fourmiliere.getTabSites().length ; i++) { // on compte le nombre de sites vides
						
						if (fourmiliere.getTabSites()[i].isVide()) {
							
							compteurSitesVides++;
							
						}
						
					}
					
					if (compteurSitesVides >= fourmiliere.getTabSites().length) {	//on verifie qu'il y ait encore des sites de nourritures avec de la nourriture
						
						JOptionPane.showMessageDialog(null,
							    "Il n'y a plus de site de nourriture !",
							    "Fin de simulation",
							    JOptionPane.WARNING_MESSAGE);	//sinon on popup un dialog et on termine le programme
						
						System.exit(0);
						
					}
					
					do {
						
						ouvriere.setChoix(aleatoire.nextInt(fourmiliere.getTabSites().length));
												
					} while (fourmiliere.getTabSites()[ouvriere.getChoix()].isVide());
					
					System.out.println(ouvriere.getNom() + " a choisi le " + fourmiliere.getTabSites()[ouvriere.getChoix()].getNom() + " et se dirige vers lui.");
					
					ouvriere.seDeplace();
					
				} else if (ouvriere.getPosCourante() > 0 && ouvriere.getPosCourante() < fourmiliere.getTabSites()[ouvriere.getChoix()].getPosition()) {	//en route vers un site
					
					if (!fourmiliere.getTabSites()[ouvriere.getChoix()].isVide()) {
						
						System.out.println(ouvriere.getNom() + " se dirige vers le " + fourmiliere.getTabSites()[ouvriere.getChoix()].getNom() + ".");
						ouvriere.seDeplace();
						
					} else {
						
						ouvriere.setAller(false);
						
						System.out.println("Le " + fourmiliere.getTabSites()[ouvriere.getChoix()].getNom() + " etant vide, " + ouvriere.getNom() + " rentre a la fourmiliere.");
						
						ouvriere.seDeplace();
						
					}
					
				} else { //arrivee au site de nourriture
					
					ouvriere.setAller(false);
					ouvriere.setPosCourante(fourmiliere.getTabSites()[ouvriere.getChoix()].getPosition()); //au cas ou la position du site n'est pas un multiple de la vitesse de la fourmie
					
					if (fourmiliere.getTabSites()[ouvriere.getChoix()].getStockNourriture() > 0) {
						
						fourmiliere.getTabSites()[ouvriere.getChoix()].setStockNourriture(fourmiliere.getTabSites()[ouvriere.getChoix()].getStockNourriture() - 1);
						ouvriere.setNourritureTransportee(fourmiliere.getTabSites()[ouvriere.getChoix()].getContenu().getValeurNutritive());
						
						System.out.println(ouvriere.getNom() + " recupere " + fourmiliere.getTabSites()[ouvriere.getChoix()].getContenu().getNom() + " dans le " + fourmiliere.getTabSites()[ouvriere.getChoix()].getNom() + ".");
						
					} else {
						
						fourmiliere.getTabSites()[ouvriere.getChoix()].setVide(true);	//on definit le statut vide en dehors du message pour eviter que d'autres fourmies arrivent au site avant l'execution du message
						
						System.out.println(ouvriere.getNom() + " est arrivee sur un site vide: " + fourmiliere.getTabSites()[ouvriere.getChoix()].getNom());
						
						BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageFinNourriture(ouvriere, fourmiliere));
													
					}
					
				}
				
			} else { //sur le retour
				
				if (ouvriere.getPosCourante() > 0) {	//en route vers la fourmiliere
					
					if (ouvriere.getNourritureTransportee() > 0) {
						
						System.out.println(ouvriere.getNom() + " retourne a la fourmiliere avec " + ouvriere.getNourritureTransportee() + " unite(s) de nourriture.");
						
						ouvriere.seDeplace();
						
					} else {
						
						System.out.println(ouvriere.getNom() + " retourne a la fourmiliere sans nourriture.");
						
						ouvriere.seDeplace();
						
					}
					
				} else {	//est arrivee a la fourmiliere
					
					ouvriere.setAller(true);
					ouvriere.setPosCourante(0);
					ouvriere.setChoix(-1);
					
					if (ouvriere.getNourritureTransportee() > 0) {
						
						System.out.println(ouvriere.getNom() + " est rentree a la fourmiliere avec " + ouvriere.getNourritureTransportee() + " unite(s) de nourriture.");
						
						BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageDepotNourriture(ouvriere,ouvriere.getNourritureTransportee()));
						
						ouvriere.setNourritureTransportee(0);
						
					} else {
						
						System.out.println(ouvriere.getNom() + " est rentree sans nourriture a la fourmiliere");
						
					}
					
				}
				
			}
			
		} else {	//n'a plus de points de vie		
			
			BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageBlesse(ouvriere));
			
		}
			
	}	

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Guerriere)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Guerriere guerriere) {
		

	}

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Soigneur)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Soigneur soigneur) {
		
		
	}

}
