package messages;

import java.util.Enumeration;

import mecaniques.Fourmiliere;
import fourmiz.FourmieAbstract;
import fourmiz.Soigneur;

public class MessageBlesse extends MessageAbstract {

	private int index;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public MessageBlesse(FourmieAbstract emeteur , int indexEmeteur) {
		
		super("La fourmie " + emeteur.getType() + " est blessee et a besoin d'un soigneur", emeteur);
		
		index = indexEmeteur;
		
	}

	@Override
	public void action(Fourmiliere f) {
				
		Enumeration<Integer> ePop = f.getPopulation().keys();
		
		while (ePop.hasMoreElements()) {
			
			int key = ePop.nextElement();
			
			if (f.getPopulation().get(key) instanceof Soigneur) {
				
				if (!((Soigneur) f.getPopulation().get(key)).isEnService()) {
					
					f.getPopulation().get(key).setChoix(this.getIndex());
					((Soigneur) f.getPopulation().get(key)).setEnService(true);
					this.getEmeteur().setPriseEnCharge(true);
					
					break;
					
				}
				
			}
			
		}
				
	}

}
