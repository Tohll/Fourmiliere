package main;

import java.util.Collections;
import java.util.Enumeration;

import mecaniques.Fourmiliere;
import messages.BoiteMessagesSingleton;
import messages.MessageAbstract;

/**
 * La classe <b>Main.java</b> gere l'execution de la boucle principale du programme.
 * @author Seldan
 *
 */
public class Main {

	/**
	 * La fonction main de ce programme instancie une fourmiliere, puis lance une boucle de traitement de longeur définie dans le FOR EACH principal.<br>
	 * A chaque itération de la boucle, on execute la methode <i>action</i>(<b>Fourmiliere</b>) de chaque fourmie active, puis on execute le bloc de la boite a message.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Fourmiliere f = new Fourmiliere(5);	//Instanciation d'une fourmiliere
				
		for (int i=0 ; i<100 ; i++) {	//FOR EACH principal: on definit la taille de la boucle dans le parmetrage du FOR EACH
			
			//*********ACTION FOURMIES****************
			System.out.println();
			System.out.println("------Nouveau cycle------");
						
			Enumeration<Integer> ePop = f.getPopulationActive().keys();	//on crré une enumeration des Key du tableau des populations actives
			
			while (ePop.hasMoreElements()) {	//tant qu'il y a un nouvel element dans l'enumeration
				
				int key = ePop.nextElement();	//on stocke le prochain element dans key
				
				f.getPopulationActive().get(key).Action(f);	//on execute l'action liée a l'element stocké a l'index key du tableau des populations actives
				
			}	//sortie de la boucle cycle principal
			
			System.out.println("------Fin du cycle------");
			System.out.println();
			//**********FIN ACTIONS FOURMIES***********
			
			//*******BOITE A MESSAGES********
			System.out.println("***Messages***");			
				
			Enumeration<MessageAbstract> eMessages = Collections.enumeration(BoiteMessagesSingleton.getInstance().getMessagesArrayList());	//on créé une enumeration des messages contenus dans la boite a message
			
			while (eMessages.hasMoreElements()) {	//tant qu'il y a un nouvel element dans l'enumeration
				
				MessageAbstract m = eMessages.nextElement();	//on stock le prochain element dans m
				
				System.out.println(m.getMessage());	//affichage du message stocké dans m
				
				m.action(f);	//action liee au message stocké dans m
				
			}	//sortie dela boucle boite a message
						
			BoiteMessagesSingleton.getInstance().getMessagesArrayList().clear();	//on vide la boite a message
			
			System.out.println("stock nourriture: " + f.getStockNourriture());	//feedback du stock de nourriture de la fourmiliere
			
			System.out.println("***Fin des messages***");
			System.out.println();
			System.out.println("///////////////////////////////////////////");
			//*******FIN BOITE A MESSAGES*******	
			
		}	//sortie du FOR EACH principal

	}

}
