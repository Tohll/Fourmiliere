package messages;

import mecaniques.Fourmiliere;
import fourmiz.FourmieAbstract;
import fourmiz.Soigneur;

public class MessageRecuperationOk extends MessageAbstract {

	private int index;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public MessageRecuperationOk(FourmieAbstract emeteur , int index) {
		
		super("La fourmie " + emeteur.getType() + " a ramené un cadavre a la fourmiliere", emeteur);
		this.index = index;
	}

	@Override
	public void action(Fourmiliere f) {		
		
		f.getPopulation().remove(this.getEmeteur().getChoix());
		((Soigneur) f.getPopulation().get(this.getIndex())).setEnService(false);
		this.getEmeteur().setChoix(0);
		
		
	}

}
