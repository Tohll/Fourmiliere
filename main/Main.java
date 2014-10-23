package main;

import java.io.IOException;

import fourmiz.*;
import mecaniques.*;
import messages.BoiteMessagesSingleton;
import messages.MessageAbstract;

/**
 * @author Charbel FOUREL
 * @version 0.1
 *
 */
public class Main {

	public static void main(String[] args) {		

		Fourmiliere f = new Fourmiliere();
		int i;
		
		f.setTerrain(new Terrain());
		
		for (i=0 ; i<5 ; i++) {
			
			f.getPopulation().add(new Ouvriere());
		}
		
		for (i=0 ; i<3 ; i++) {
			
			f.getPopulation().add(new Guerriere());
		}
		
		for (i=0 ; i<2 ; i++) {
			
			f.getPopulation().add(new Soigneur());
		}
		
		for (i=0 ; i<50 ; i++) {
			
			System.out.println("------Nouveau cycle------");
			for (i=0 ; i<f.getPopulation().size() ; i++) {
				
				f.getPopulation().get(i).Comportement(f);
			}
			System.out.println("------Fin du cycle------");
			System.out.println("***Messages***");			
						
			for (int j=0 ; j < BoiteMessagesSingleton.getInstance().getMessagesArrayList().size() ; j++) {
				MessageAbstract m = BoiteMessagesSingleton.getInstance().getMessagesArrayList().get(j);
				System.out.println(m.getMessage());
				m.action(f);
			}
			
			BoiteMessagesSingleton.getInstance().getMessagesArrayList().clear();
			
			System.out.println("stock nourriture: " + f.getStockNourriture());
			
			System.out.println("***Fin des messages***");
			System.out.println();
			
			/*System.out.println("Appuyer sur entrer pour lancer un nouveau cycle...");
			try {
				System.in.read();
			} catch (IOException e) {
				
				System.out.println("Probleme d'entrée/sortie");
			}*/
		}
		
		/*Fourmiliere f = new Fourmiliere();
		
		f.setTerrain(new Terrain());
		
		System.out.println("Fourmiliere:");
		System.out.println("Stock: " + f.getStockNourriture());
		System.out.println();
		System.out.println("Site disponibles:");
		
		for (int i=0 ; i<f.getTerrain().getTerrain().length ; i++) {
			
			System.out.println(f.getTerrain().getTerrain()[i].getNom());
		}*/
		
		/*Terrain terrainTest = new Terrain();
		
		for (int i=0 ; i<terrainTest.getTerrain().length ; i++) {
			
			System.out.println("Nom: " + terrainTest.getTerrain()[i].getNom());
			System.out.println("Distance: " + terrainTest.getTerrain()[i].getDistance());
			System.out.println("Stock: " + terrainTest.getTerrain()[i].getStockNourriture());
			System.out.println();
		}*/
		
		/*Fourmiliere f = new Fourmiliere();
		
		for (int i=0 ; i<f.getPopulation().size() ; i++) {
			
			System.out.println(f.getPopulation().get(i).getType());
		}*/
		
		/*SiteNourriture st;
		SiteNourriture[] st_tableau = new SiteNourriture[3];
		
		st = new SiteNourriture(5);
		st_tableau[0] = st;
		st = new SiteNourriture(7);
		st_tableau[1] = st;
		st = new SiteNourriture(3);
		st_tableau[2] = st;
		
		for (int i=0 ; i<st_tableau.length ; i++) {
			
			System.out.println(st_tableau[i].getNom());
			System.out.println("Distance: " + st_tableau[i].getDistance());
			System.out.println();
		}*/
		
		/*FourmieAbstract[] tableau = new FourmieAbstract[8];
		FourmieAbstract fourmie;		
		
		fourmie = new Ouvriere();
		tableau[0] = fourmie;
		fourmie = new Ouvriere();
		tableau[1] = fourmie;
		fourmie = new Ouvriere();
		tableau[2] = fourmie;
		fourmie = new Reine();
		tableau[3] = fourmie;
		fourmie = new Guerriere();
		tableau[4] = fourmie;
		fourmie = new Guerriere();
		tableau[5] = fourmie;
		fourmie = new Soigneur();
		tableau[6] = fourmie;
		fourmie = new Soigneur();
		tableau[7] = fourmie;		
		
		fourmie = null;
		
		for (int i=0 ; i<tableau.length ; i++) {
			
			System.out.println("Type: " + tableau[i].getType());
			System.out.println("Postion: " + tableau[i].getPosition());
			System.out.println("Points de vie: " + tableau[i].getPointsDeVie());
			System.out.println("Vitesse: " + tableau[i].getVitesse());
			System.out.println();
		}*/
	}
}
