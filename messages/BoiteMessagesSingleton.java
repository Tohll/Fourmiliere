package messages;

import java.util.ArrayList;

public class BoiteMessagesSingleton {

	private static final BoiteMessagesSingleton instance = new BoiteMessagesSingleton();
	private ArrayList<String> messagesArrayList;
	
	public ArrayList<String> getMessagesArrayList() {
		return messagesArrayList;
	}

	public void setMessagesArrayList(ArrayList<String> messagesArrayList) {
		this.messagesArrayList = messagesArrayList;
	}

	private BoiteMessagesSingleton() {
		
		this.messagesArrayList = new ArrayList<>();
	}
	
	public static BoiteMessagesSingleton getInstance() {
		
		return instance;
	}
}
