package mecaniques;

import java.util.ArrayList;

import plateau.Plateau;

public class PathFinder {
	
	private static ArrayList<Integer> lstOuverte;
	private static ArrayList<Integer> lstFermee;
	
	public static ArrayList<Integer> trouverChemin (Plateau plateau , int nodeDepart , int nodeArrivee) {
		
		lstOuverte = new ArrayList<>();
		lstFermee = new ArrayList<>();
		
		ArrayList<Integer> cheminFinal = new ArrayList<>();
		
		lstOuverte.add(nodeDepart);
		
		int nodeCourant = -1;
		
		while (lstOuverte.size() > 0) {
			
			
			
		}
		
		return cheminFinal;
		
	}
	
	private static void enleverDeLstFermee (int node) {
		
		ArrayList<Integer> tmpLst = new ArrayList<>();
		int max = lstFermee.size();
		
		for (int i=0 ; i<max ; i++) {
			
			if (lstFermee.get(i) != node) {
				
				tmpLst.add(lstFermee.get(i));
				
			}
			
			lstFermee = tmpLst;
			
		}
		
	}
	
	private static void enleverDeLstOuverte (int node) {
		
		ArrayList<Integer> tmpLst = new ArrayList<>();
		int max = lstOuverte.size();
		
		for (int i=0 ; i<max ; i++) {
			
			if (lstOuverte.get(i) != node) {
				
				tmpLst.add(lstOuverte.get(i));
				
			}
			
			lstOuverte = tmpLst;
			
		}
		
	}
	
	private static void ajouterALstFermee (int node) {
				
		lstOuverte.remove(lstOuverte.indexOf(node));
		lstFermee.add(node);
		
	}
	
	private static void ajouterALstOuverte (int node) {
		
		lstFermee.remove(lstFermee.indexOf(node));
		lstOuverte.add(node);
		
	}
	
	private static int recupNodeActuel (Plateau plateau) {
		
		int max = lstOuverte.size();
		int minF = 1000000;
		int nodeActuel = -1;
		
		for (int i=0 ; i < max ; i++) {
			
			int node = lstOuverte.get(i);
			
			if (plateau.getTabCases()[node].getF() < minF) {
				
				minF = plateau.getTabCases()[node].getF();
				nodeActuel = node;
				
			}
			
		}
		
		return nodeActuel;
		
	}
	
	private static ArrayList<Integer> recupVoisins (int node , Plateau plateau) {
		
		ArrayList<Integer> voisins = new ArrayList<>();
		
		int nodeNO = (node-plateau.getNBR_COL())-1;
		int nodeN = node-plateau.getNBR_COL();
		int nodeNE = (node-plateau.getNBR_COL())+1;
		int nodeE = node+1;
		int nodeSE = (node+plateau.getNBR_COL())+1;
		int nodeS = node+plateau.getNBR_COL();
		int nodeSO = (node+plateau.getNBR_COL())-1;
		int nodeO = node-1;
		
		//TODO 
		//http://forums.mediabox.fr/wiki/tutoriaux/flashplatform/jeux/pathfinder_algorithme_astar_pratique
		//http://www.cokeandcode.com/main/tutorials/path-finding/
		
		return voisins;
		
	}

}
