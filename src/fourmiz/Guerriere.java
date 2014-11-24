package fourmiz;

import java.util.Enumeration;
import java.util.Random;

import mecaniques.Fourmiliere;
import messages.BoiteMessagesSingleton;
import messages.MessageBlesse;
/**
 * La classe Guerriere permet d'instancier une fourmie de type guerriere paramétrée.
 * 
 * @author Charbel FOUREL
 */
public final class Guerriere extends FourmieAbstract {

	/**
	 * Contructeur de la guerriere.
	 */
	public Guerriere(Fourmiliere f) {
		
		super("Guerriere " + nbrGuerriere , 15 , 2 , f);	//on appel le constructeur de la classe abstraite en donnant:
														//"Guerriere" en nom et en y ajoutant le compteur des guerrieres
														//15 pour le nombre de points de vie
														//2 pour la vitesse
														//La premiere guerriere s'appelera donc "Guerriere 1"
		
		nbrGuerriere++;									//on incremente le compteur des guerrieres, en prévision de la prochaine instanciation.		
	}

	/* (non-Javadoc)
	 * @see fourmiz.FourmieAbstract#Comportement(mecaniques.Fourmiliere)
	 */
	@Override
	public void Comportement(Fourmiliere f) {	
		
		if (this.isEstActive()) {	//on ne lance les comportements que si la fourmie est active
			
			if (this.isAller()) {	//si la fourmie est sur l'allé
				
				if (this.getPosition() == 0) { //si elle est positionnée au niveau de la forumiliere
					
					Random choixAlea = new Random();
					
					this.setChoix(choixAlea.nextInt(f.getTerrain().getTerrain().length));	//on lui fait choisir un site de nourriture aleatoirement
					
					this.setPosition(this.getPosition() + this.getVitesse()); //la fourmie avance ensuite de sa vitesse vers le site et perd un point de vie.
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") avance vers le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
					
				} else if (this.getPosition() > 0 && this.getPosition() < f.getTerrain().getTerrain()[this.getChoix()].getDistance()) { //si la fourmie n'est pas a la fourmiliere et n'est pas non plus arrivee au site
					
					this.setPosition(this.getPosition() + this.getVitesse()); //la fourmie avance de sa vitesse vers le site et perd un point de vie.
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") avance vers le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
					
				}  else { //sinon la fourmie est arrivee a un site								
					
					this.setPosition(f.getTerrain().getTerrain()[this.getChoix()].getDistance()); // on definit sa position a celle du site ( au cas ou elle aurait depassé le site)
					this.setPointsDeVie(this.getPointsDeVie() - 1); //elle perd un point de vie et se retrouve en position de retour
					this.setAller(false);
					
					System.out.println("La fourmie (" + this.getType() + ") patrouille le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
				}
				
			} else { //si la fourmie est sur le retour
				
				if (this.getPosition() <= f.getTerrain().getTerrain()[this.getChoix()].getDistance() && this.getPosition() > 0) { //si elle est entre le site et la fourmiliere
					
					this.setPosition(this.getPosition() - this.getVitesse()); //la fourmie avance de sa vitesse vers la fourmiliere et perd un point de vie.
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") retourne a la fourmiliere.");
					
				} else { //sinon elle est arrivée a la fourmiliere
					
					this.setPosition(0); // on definit sa position a celle de la fourmiliere ( au cas ou elle aurait depassé la fourmiliere)
					this.setPointsDeVie(this.getPointsDeVie() - 1); //elle perd un point de vie et se retrouve en posisiton d'aller
					this.setAller(true);
					
					System.out.println("La fourmie (" + this.getType() + ") patrouille la fourmiliere.");									
					
					if (f.getStockNourriture() > 0) { //si il y a assez de nourriture la guerriere se nourrit de une unité d enourriture
						
						f.setStockNourriture(f.getStockNourriture() - 1);
						
					} else { //sinon elle perd un point de vie.
						
						this.setPointsDeVie(this.getPointsDeVie() - 2);
						
					}				
				}
			}
			
			if (this.getPointsDeVie() <= 0) { //si la fourmie n'a plus de points de vie
				
				this.setEstActive(false); //elle n'est plus active
			}			
		} else {
			
			if (!this.isPriseEnCharge()) {
				
				int index = -1;
				
				Enumeration<Integer> ePop = f.getPopulation().keys();
				
				while (ePop.hasMoreElements()) {
					
					int key = ePop.nextElement();
					
					if (f.getPopulation().get(key) == this) {
						
						index = key;
						break;
						
					}
					
				}
				
				BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageBlesse(this ,index));
				
			}
			
		}
	}	
}
