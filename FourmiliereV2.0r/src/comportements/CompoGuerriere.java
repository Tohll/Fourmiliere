package comportements;

import java.util.Random;

import javax.swing.JOptionPane;

import mecaniques.Fourmiliere;
import messages.BoiteMessagesSingleton;
import messages.MessageBlesse;
import fourmies.Guerriere;
import fourmies.Ouvriere;
import fourmies.Reine;
import fourmies.Soigneur;

/**
 * La classe <b>CompoGuerriere.java</b> contient une implementation des comportements de la fourmie guerriere.
 * @author Charbel FOUREL
 *
 */
public class CompoGuerriere implements ComportementsInterface {

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
		

	}

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Soigneur)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Soigneur soigneur) {
		

	}

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Guerriere)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Guerriere guerriere) {
		
		guerriere.perteDeVie(1);	//avant toute chose on fait perdre de la vie a la fourmie
			
		if (guerriere.getPointsDeVie() > 0) {	//si il lui reste de la vie
			
			if (guerriere.isAller()) {	//sur l'aller
				
				if (guerriere.getPosCourante() == 0) {	//si elle est positionnée au niveau dela fourmiliere
					
					int compteurSitesVides = 0;	//un compteur qui va servir a compter le nombre de sites de nourriture vides
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
					
					do {	//si il reste au moins un site de nourriture plein, on lance une boucle qui va choisir aleatoirement un site de nourriture plein
						
						guerriere.setChoix(aleatoire.nextInt(fourmiliere.getTabSites().length));
												
					} while (fourmiliere.getTabSites()[guerriere.getChoix()].isVide());	//on ne sort de la boucle que si le site choisit est plein
					
					System.out.println(guerriere.getNom() + " a choisi le " + fourmiliere.getTabSites()[guerriere.getChoix()].getNom() + " et se dirige vers lui.");
					
					guerriere.seDeplace();
					
				} else if (guerriere.getPosCourante() > 0 && guerriere.getPosCourante() < fourmiliere.getTabSites()[guerriere.getChoix()].getPosition()) {	//sinon si positionnée entre la fourmiliere et le site de nourriture choisit
					
					if (!fourmiliere.getTabSites()[guerriere.getChoix()].isVide()) {	//on verifie que le site choisit ne se soit pas vidé entre temps
						
						System.out.println(guerriere.getNom() + " se dirige vers le " + fourmiliere.getTabSites()[guerriere.getChoix()].getNom() + ".");
						
						guerriere.seDeplace();
						
					} else {	//si le site est devenu vide entre temps
						
						guerriere.setAller(false);	//on renvoie la fourmie a la fourmiliere pour une nouvelle affectation
						
						System.out.println("Le " + fourmiliere.getTabSites()[guerriere.getChoix()].getNom() + " etant vide, " + guerriere.getNom() + " rentre a la fourmiliere.");
						
						guerriere.seDeplace();
						
					}
					
				} else {	//si arrivee au site de nourriture choisit
					
					guerriere.setAller(false);	//on met la fourmie sur le chemin du retour
					guerriere.setPosCourante(fourmiliere.getTabSites()[guerriere.getChoix()].getPosition()); //au cas ou la position du site n'est pas un multiple de la vitesse de la fourmie
					
					System.out.println(guerriere.getNom() + " patrouille le " + fourmiliere.getTabSites()[guerriere.getChoix()].getNom() + ".");
					
				}
				
			} else {	//sur le retour
				
				if (guerriere.getPosCourante() > 0) {	//si la fourmie n'est pas a la position de la fourmiliere
					
					guerriere.seDeplace();
					
					System.out.println(guerriere.getNom() + " retourne vers la fourmiliere.");
					
				} else {	//si arrivee a la fourmiliere
					
					guerriere.setAller(true);	//on la met sur l'aller
					guerriere.setPosCourante(0);	//on definit sa potition a celle de la fourmiliere au cas ou la difference entre son point de depart et la fourmiliere ne soit pas un multiple de sa vitesse
					
					System.out.println(guerriere.getNom() + " patrouille la fourmiliere.");
					
				}
				
			}
			
		} else {	//points de vie tombent a zero
			
			BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageBlesse(guerriere));	//envoie d'un message "je suis blessée"
			
		}

	}

}
