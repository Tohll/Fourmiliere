package messages;

import fourmies.FourmieAbstract;
import mecaniques.Fourmiliere;

public abstract class MessageAbstract {
	
	private String message;
	private FourmieAbstract emeteur;
	
	FourmieAbstract getEmeteur() {
		return emeteur;
	}

	void setEmeteur(FourmieAbstract emeteur) {
		this.emeteur = emeteur;
	}

	public String getMessage() {
		return message;
	}	

	public MessageAbstract(String message , FourmieAbstract emeteur) {		
		this.message = message;
		this.emeteur = emeteur;
	}	
		
	public abstract void action (Fourmiliere f);

}
