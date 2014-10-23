package messages;

import mecaniques.Fourmiliere;

public abstract class MessageAbstract {
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageAbstract(String message) {
		
		this.message = message;
	}
	
	public abstract void action (Fourmiliere f);

}
