package mecaniques;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import plateau.Plateau;

public class PathFinder {
	
	private static ArrayList<Coordonnees> lstOuverte;
	private static ArrayList<Coordonnees> lstFermee;
	
	public static ArrayList<Coordonnees> trouverChemin (Plateau plateau , Coordonnees nodeDepart , Coordonnees nodeArrivee) {
		
		lstOuverte = new ArrayList<>();
		lstFermee = new ArrayList<>();
		
		ArrayList<Coordonnees> cheminFinal = new ArrayList<>();
		
		lstOuverte.add(nodeDepart);
		
		Coordonnees nodeCourant = null;
		
		while (lstOuverte.size() > 0) {
			
			ArrayList<Coordonnees> voisins;
			int max;
			
			nodeCourant = recupNodeActuel(plateau);
			
			if (nodeCourant == nodeArrivee) {
				
				break;
				
			}
			
			ajouterALstFermee(nodeCourant);
			
			voisins = recupVoisins(nodeCourant, plateau);
			max = voisins.size();
			
			for (int i=0 ; i<max ; i++) {
				
				Coordonnees node = voisins.get(i);
				
				if (estDansLstFermee(node) == false && plateau.getTabCases()[node.getLigne()][node.getColonne()].isObstacle() == false) {
					
					int newG = plateau.getTabCases()[node.getLigne()][node.getColonne()].getParent().getG()
							 + plateau.getTabCases()[node.getLigne()][node.getColonne()].getF();
					int newH = Math.abs(plateau.getTabCases()[node.getLigne()][node.getColonne()].getPosX() - plateau.getTabCases()[nodeArrivee.getLigne()][nodeArrivee.getColonne()].getPosX())
							 + Math.abs(plateau.getTabCases()[node.getLigne()][node.getColonne()].getPosY() - plateau.getTabCases()[nodeArrivee.getLigne()][nodeArrivee.getColonne()].getPosY())
							 + plateau.getTabCases()[node.getLigne()][node.getColonne()].getF();
					int newF = newG + newH;
					
					if (estDansLstOuverte(node)) {
						
						if (newG < plateau.getTabCases()[node.getLigne()][node.getColonne()].getG()) {
							
							plateau.getTabCases()[node.getLigne()][node.getColonne()].setParent(plateau.getTabCases()[nodeCourant.getLigne()][nodeCourant.getColonne()]);
							plateau.getTabCases()[node.getLigne()][node.getColonne()].setG(newG);
							plateau.getTabCases()[node.getLigne()][node.getColonne()].setH(newH);
							plateau.getTabCases()[node.getLigne()][node.getColonne()].setF(newF);
							
						}
						
					} else {
						
						ajouterALstOuverte(node);
						plateau.getTabCases()[node.getLigne()][node.getColonne()].setParent(plateau.getTabCases()[nodeCourant.getLigne()][nodeCourant.getColonne()]);
						plateau.getTabCases()[node.getLigne()][node.getColonne()].setG(newG);
						plateau.getTabCases()[node.getLigne()][node.getColonne()].setH(newH);
						plateau.getTabCases()[node.getLigne()][node.getColonne()].setF(newF);
						
					}
					
				}
				
			}
			
		}
		
		if (lstOuverte.size() == 0) {
			
			return cheminFinal;
			
		}
		
		Coordonnees dernierNode = nodeArrivee;
		
		while (dernierNode != nodeArrivee) {
			
			cheminFinal.add(dernierNode);
			dernierNode = plateau.getTabCases()[dernierNode.getLigne()][dernierNode.getColonne()].getParent().getPosNode();
			
		}
		
		//TODO
		//inversion du chemin final
		return cheminFinal.
		
	}
	
	private static void enleverDeLstFermee (Coordonnees node) {
		
		ArrayList<Coordonnees> tmpLst = new ArrayList<>();
		int max = lstFermee.size();
		
		for (int i=0 ; i<max ; i++) {
			
			if (lstFermee.get(i) != node) {
				
				tmpLst.add(lstFermee.get(i));
				
			}
			
			lstFermee = tmpLst;
			
		}
		
	}
	
	private static void enleverDeLstOuverte (Coordonnees node) {
		
		ArrayList<Coordonnees> tmpLst = new ArrayList<>();
		int max = lstOuverte.size();
		
		for (int i=0 ; i<max ; i++) {
			
			if (lstOuverte.get(i) != node) {
				
				tmpLst.add(lstOuverte.get(i));
				
			}
			
			lstOuverte = tmpLst;
			
		}
		
	}
	
	private static void ajouterALstFermee (Coordonnees node) {
				
		lstOuverte.remove(lstOuverte.indexOf(node));
		lstFermee.add(node);
		
	}
	
	private static void ajouterALstOuverte (Coordonnees node) {
		
		lstFermee.remove(lstFermee.indexOf(node));
		lstOuverte.add(node);
		
	}
	
	private static Coordonnees recupNodeActuel (Plateau plateau) {
		
		int max = lstOuverte.size();
		int minF = 1000000;
		Coordonnees nodeActuel = null;
		
		for (int i=0 ; i < max ; i++) {
			
			Coordonnees node = lstOuverte.get(i);
			
			if (plateau.getTabCases()[node.getLigne()][node.getColonne()].getF() < minF) {
				
				minF = plateau.getTabCases()[node.getLigne()][node.getColonne()].getF();
				nodeActuel = node;
				
			}
			
		}
		
		return nodeActuel;
		
	}
	
	private static ArrayList<Coordonnees> recupVoisins (Coordonnees node , Plateau plateau) {
		
		ArrayList<Coordonnees> voisins = new ArrayList<>();
		
		int voisinN = node.getLigne()-1;
		int voisinS = node.getLigne()+1;
		int voisinE = node.getColonne()+1;
		int voisinO = node.getColonne()-1;
		
		//Axes
		if (voisinN > -1) {
			
			voisins.add(plateau.getTabCases()[node.getLigne()-1][node.getColonne()].getPosNode());
			plateau.getTabCases()[node.getLigne()-1][node.getColonne()].setF(10);
		}
		
		if (voisinS > plateau.getNBR_LIG()) {
			
			voisins.add(plateau.getTabCases()[node.getLigne()+1][node.getColonne()].getPosNode());
			plateau.getTabCases()[node.getLigne()+1][node.getColonne()].setF(10);
			
		}
		
		if (voisinE < plateau.getNBR_COL()) {
			
			voisins.add(plateau.getTabCases()[node.getLigne()][node.getColonne()+1].getPosNode());
			plateau.getTabCases()[node.getLigne()][node.getColonne()+1].setF(10);
			
		}
		
		if (voisinO > -1) {
			
			voisins.add(plateau.getTabCases()[node.getLigne()][node.getColonne()-1].getPosNode());
			plateau.getTabCases()[node.getLigne()][node.getColonne()-1].setF(10);
			
		}
		
		//Diagonales
		if (voisinN > -1 && voisinE < plateau.getNBR_COL()) {
			
			voisins.add(plateau.getTabCases()[node.getLigne()-1][node.getColonne()+1].getPosNode());
			plateau.getTabCases()[node.getLigne()-1][node.getColonne()+1].setF(14);
			
		}
		
		if (voisinS > plateau.getNBR_LIG() && voisinE < plateau.getNBR_COL()) {
			
			voisins.add(plateau.getTabCases()[node.getLigne()+1][node.getColonne()+1].getPosNode());
			plateau.getTabCases()[node.getLigne()+1][node.getColonne()+1].setF(14);
			
		}

		if (voisinN > -1 && voisinO > -1) {
	
			voisins.add(plateau.getTabCases()[node.getLigne()-1][node.getColonne()-1].getPosNode());
			plateau.getTabCases()[node.getLigne()-1][node.getColonne()-1].setF(14);
	
		}
		
		if (voisinS > plateau.getNBR_LIG() && voisinO > -1) {
			
			voisins.add(plateau.getTabCases()[node.getLigne()+1][node.getColonne()-1].getPosNode());
			plateau.getTabCases()[node.getLigne()+1][node.getColonne()-1].setF(14);
			
		}
				
		return voisins;
		
	}
	
	private static boolean estDansLstOuverte (Coordonnees node) {
		
		int max = lstOuverte.size();
		boolean bool = false;
		
		for (int i=0 ; i < max ; i++) {
			
			if (lstOuverte.get(i) == node) {
				
				bool = true;
				
			} else {
				
				bool = false;
				
			}
			
		}
		
		return bool;
				
	}
	
	private static boolean estDansLstFermee (Coordonnees node) {
		
		int max = lstFermee.size();
		boolean bool = false;
		
		for (int i=0 ; i < max ; i++) {
			
			if (lstFermee.get(i) == node) {
				
				bool = true;
				
			} else {
				
				bool = false;
				
			}
			
		}
		
		return bool;
				
	}

}
