package fourmiz;

import java.util.Enumeration;
import java.util.Random;

import mecaniques.Fourmiliere;
import messages.BoiteMessagesSingleton;
import messages.MessageBlesse;
import messages.MessageDepotNourriture;

/**
 * La classe Ouvriere permet d'instancier une fourmie de type ouvriere paramétrée.
 * 
 * @author Charbel FOUREL
 */
public final class Ouvriere extends FourmieAbstract {	
	
	private int nourritureTransportee;	//quantitee de nourriture transportée par la fourmie
	
	public int getNourritureTransportee() {
		return nourritureTransportee;
	}

	public void setNourritureTransportee(int nourritureTransportee) {
		this.nourritureTransportee = nourritureTransportee;
	}

	/**
	 * Constructeur de l'ouvriere.
	 */
	public Ouvriere(Fourmiliere f) {
		
		super("Ouvriere " + nbrOuvriere , 10 , 1 , f); //on appel le constructeur de la classe abstraite en donnant:
												   //"Ouvriere" en nom et en y ajoutant le compteur des ouvrieres
												   //10 pour le nombre de points de vie
												   //1 pour la vitesse
												   //La premiere ouvriere s'appelera donc "Ouvriere 1"
		
		this.nourritureTransportee = 0;				//on définit lke stock de nourriture transportée a 0
		nbrOuvriere++;								//on incremente le compteur des ouvrieres, en prévision de la prochaine instanciation.		
	}

	/* (non-Javadoc)
	 * @see fourmiz.FourmieAbstract#Comportement(mecaniques.Fourmiliere)
	 */
	@Override
	public void Comportement(Fourmiliere f) {		
		
		if (this.isEstActive()) { //si la fourmie est active
			
			if (this.isAller()) {	//si elle est sur l'allé
				
				if (this.getPosition() == 0) { //si elle est postionnée au niveau de la fourmiliere
					
					Random choixAlea = new Random();
					
					this.setChoix(choixAlea.nextInt(f.getTerrain().getTerrain().length)); //on lui fait choisir un site de nourriture aléatoirement
					
					this.setPosition(this.getPosition() + this.getVitesse());	//on la fait avancer de sa vitesse vers le site de nourriture et elle perd un point de vie.
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") avance vers le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
					
				} else if (this.getPosition() > 0 && this.getPosition() < f.getTerrain().getTerrain()[this.getChoix()].getDistance()) { //si elle est entre la fourmiliere et le site de nourriture
					
					this.setPosition(this.getPosition() + this.getVitesse());	//on la fait avancer de sa vitesse vers le site de nourriture et elle perd un point de vie.
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") avance vers le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
					
				} else { //sinon elle est arrivee au site de nourriture
					
					this.setAller(false);	//on la definit sur le retour
					this.setNourritureTransportee(2);	//on lui fait transporte 2 unites de nourriture
					f.getTerrain().getTerrain()[this.getChoix()].setStockNourriture(f.getTerrain().getTerrain()[this.getChoix()].getStockNourriture() - 2);	//on enleve 2 unites de nourriture au site choisit			
					
					this.setPosition(f.getTerrain().getTerrain()[this.getChoix()].getDistance());	//on la fait avancer de sa vitesse vers la fourmiliere et elle perd un point de vie.
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") prend de la nourriture dans le " + f.getTerrain().getTerrain()[this.getChoix()].getNom() + ".");
				}
				
			} else {	//si elle est sur le retour
				
				if (this.getPosition() <= f.getTerrain().getTerrain()[this.getChoix()].getDistance() && this.getPosition() > 0) {	//si elle est entre la fourmiliere et le site de nourriture
					
					this.setPosition(this.getPosition() - this.getVitesse());	//on la fait avancer de sa vitesse vers la fourmiliere et elle perd un point de vie
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") retourne a la fourmiliere.");
					
				} else {	//sinon elle est arrivee a la fourmiliere
					
					this.setAller(true); //on la definit sur l'allé
					this.setPosition(0);	//on definit sa position a celle de la fourmiliere et elle eprd un point de vie
					this.setPointsDeVie(this.getPointsDeVie() - 1);
					
					System.out.println("La fourmie (" + this.getType() + ") est retournee a la fourmiliere avec " + this.getNourritureTransportee() + " unites de nourriture.");
					
					BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageDepotNourriture(this,this.getNourritureTransportee())); //on stock un message de retour de nourriture dans la boite a message
					
					this.setNourritureTransportee(0); //on vide le stock de nourriture tarsnportee de la fourmie					
					
					if (f.getStockNourriture() > 0) { //si la fourmiliere a assez de nourriture
						
						f.setStockNourriture(f.getStockNourriture() - 1); //la fourmie se nourrit de 1 unite de nourriture
						
					} else { //si pas assez de nourriture
						
						this.setPointsDeVie(this.getPointsDeVie() - 2); //la fourmie perd 2 points de vie
						
					}					
				}				
			}
			
			if (this.getPointsDeVie() <= 0) { //si la fourmie n'a plus de points de vie 
				
				this.setEstActive(false);	//la fourmie n'est plus active				
				
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
