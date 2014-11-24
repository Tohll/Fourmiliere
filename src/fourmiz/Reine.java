package fourmiz;

import mecaniques.Fourmiliere;
import messages.BoiteMessagesSingleton;
import messages.MessageNaissances;

/**
 * La classe Reine permet d'instancier une fourmie de type reine paramétrée.
 * 
 * @author Charbel FOUREL
 */
public final class Reine extends FourmieAbstract {

	private int cyclePonte; //un int qui permet de gerer les cycles de ponte de la reine
	
	public int getCyclePonte() {
		return cyclePonte;
	}

	public void setCyclePonte(int cyclePonte) {
		this.cyclePonte = cyclePonte;
	}

	public Reine(Fourmiliere f) {
		
		super("Reine" , 50000 , 0 , f);//on appel le constructeur de la classe abstraite en donnant:
								   //"Reine" en nom
								   //50000 pour le nombre de points de vie
								   //0 pour la vitesse (ne se deplace pas dans cette version)
								   //La premiere reine s'appelera donc "Reine"
		
		this.cyclePonte = 0;		//on initialise le cycle de ponte a 0. cet int sera amené a etre incrementé a chaque cycle de jeu
									
	}

	/* (non-Javadoc)
	 * @see fourmiz.FourmieAbstract#Comportement(mecaniques.Fourmiliere)
	 */
	@Override
	public void Comportement(Fourmiliere f) {
				
		if (this.getCyclePonte() < 5) { //si le cycle de ponte est inferieur a 5
			
			this.setCyclePonte(this.getCyclePonte() + 1);	//on incremente le cycle de ponte
			
		} else  {	//sinon si le cycle est a 5 on donne naissance a des fourmies
			
			this.setCyclePonte(0);//on definit le cycle a 0 pour repasser le cycle en mode incrementiel
			
			System.out.println("La reine donne naissance a de nouvelles fourmies");
			
			BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageNaissances(this));
						
		}
			
		if (f.getStockNourriture() > 0) {	//si la fourmiliere a assez de nourriture
			
			f.setStockNourriture(f.getStockNourriture() - 1);	//la fourmie se nourrit de une unitee de nourriture
			
		} else {	//si pas assez de nourriture
			
			//this.setPointsDeVie(this.getPointsDeVie() - 2);	//la reine est immortelle donc pas de perte de points de vie
			
		}
	}	
}
