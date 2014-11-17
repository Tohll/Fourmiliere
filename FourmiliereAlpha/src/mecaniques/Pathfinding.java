package mecaniques;

import java.util.ArrayList;
import java.util.Collections;

import plateau.CaseAbstract;
import plateau.Plateau;

public class Pathfinding {

	public static synchronized ArrayList<CaseAbstract> trouverChemin (CaseAbstract depart , CaseAbstract arrivee , Plateau plateau) {
		
		ArrayList<CaseAbstract> chemin = new ArrayList<>();
		ArrayList<CaseAbstract> lstOuverte = new ArrayList<>();
		ArrayList<CaseAbstract> lstFermee = new ArrayList<>();
		
		lstOuverte.add(depart);
		depart.setG(0);
		depart.setF(depart.getG() + heuristic(depart, arrivee));		
		
		while (lstOuverte.size() > 0) {
			
			CaseAbstract caseCourante = fLePlusBas(lstOuverte);						
			
			if (caseCourante.equals(arrivee)) {
									
				chemin= construireChemin(arrivee , depart);
				
				lstOuverte.clear();
				lstFermee.clear();
				break;
				
			}
			
			lstOuverte.remove(lstOuverte.indexOf(caseCourante));
			
			lstFermee.add(caseCourante);			
			
			ArrayList<CaseAbstract> voisins = voisinage(caseCourante, plateau);
			
			for (int i=0 ; i<voisins.size() ; i++) {
				
				CaseAbstract nodeTeste = voisins.get(i);
				
				if (nodeTeste.isObstacle() || estDansLstFermee(nodeTeste, lstFermee)) {
					
					continue;
					
				}				
				
				if (estDansLstOuverte(nodeTeste, lstOuverte) == false) {
					
					lstOuverte.add(nodeTeste);
					nodeTeste.setParent(caseCourante);
					
					nodeTeste.setG(nodeTeste.getParent().getG() + nodeTeste.getDistanceEntre());
					nodeTeste.setH(heuristic(nodeTeste, arrivee));
					nodeTeste.setF(nodeTeste.getG() + nodeTeste.getH());
					
				} else {
					
					int testG = nodeTeste.getParent().getG() + nodeTeste.getDistanceEntre();
					
					if (testG < nodeTeste.getG()) {
						
						nodeTeste.setParent(caseCourante);
						nodeTeste.setG(testG);
						nodeTeste.setH(heuristic(nodeTeste, arrivee));
						nodeTeste.setF(nodeTeste.getG() + nodeTeste.getH());
						
					}
					
				}
				
			}
			
		}
		
		return chemin;
		
	}
	
	private static int heuristic (CaseAbstract courante , CaseAbstract arrivee) {
		
		int rslt = Math.abs(courante.getPosX() - arrivee.getPosX()) + Math.abs(courante.getPosY() - arrivee.getPosY());
			
		return rslt;
		
	}
	
	private static CaseAbstract fLePlusBas (ArrayList<CaseAbstract> lstOuverte) {
		
		CaseAbstract courante;
		
		int fMin = 0;
		
		for (int i=0 ; i<lstOuverte.size() ; i++) {
			
			if (lstOuverte.get(i).getF() < lstOuverte.get(fMin).getF()) {
				
				fMin = i;
				
			}
			
		}
		
		courante = lstOuverte.get(fMin);
		
		return courante;
		
	}
	
	private static ArrayList<CaseAbstract> construireChemin (CaseAbstract arrivee , CaseAbstract depart) {		
		
		ArrayList<CaseAbstract> chemin = new ArrayList<>();
		chemin.add(arrivee);
		CaseAbstract node = arrivee;		
		
		while (node != depart) {			
			
			node = node.getParent();
			chemin.add(node);
			
		}
		
		Collections.reverse(chemin);
		
		return chemin;
		
	}
	
	private static ArrayList<CaseAbstract> voisinage (CaseAbstract node , Plateau plateau) {
		
		ArrayList<CaseAbstract> voisins = new ArrayList<>();
		
		int voisinN = node.getPosNode().getLigne()-1;
		int voisinS = node.getPosNode().getLigne()+1;
		int voisinE = node.getPosNode().getColonne()+1;
		int voisinO = node.getPosNode().getColonne()-1;
		
		//Axes
		if (voisinN > -1) {
			
			plateau.getTabCases()[node.getPosNode().getLigne()-1][node.getPosNode().getColonne()].setDistanceEntre(10);
			voisins.add(plateau.getTabCases()[node.getPosNode().getLigne()-1][node.getPosNode().getColonne()]);
			
		}
		
		if (voisinS > plateau.getNBR_LIG()) {
			
			plateau.getTabCases()[node.getPosNode().getLigne()+1][node.getPosNode().getColonne()].setDistanceEntre(10);
			voisins.add(plateau.getTabCases()[node.getPosNode().getLigne()+1][node.getPosNode().getColonne()]);
			
			
		}
		
		if (voisinE < plateau.getNBR_COL()) {
			
			plateau.getTabCases()[node.getPosNode().getLigne()][node.getPosNode().getColonne()+1].setDistanceEntre(10);
			voisins.add(plateau.getTabCases()[node.getPosNode().getLigne()][node.getPosNode().getColonne()+1]);
						
		}
		
		if (voisinO > -1) {
			
			plateau.getTabCases()[node.getPosNode().getLigne()][node.getPosNode().getColonne()-1].setDistanceEntre(10);
			voisins.add(plateau.getTabCases()[node.getPosNode().getLigne()][node.getPosNode().getColonne()-1]);
						
		}
		
		//Diagonales
		if (voisinN > -1 && voisinE < plateau.getNBR_COL()) {
			
			plateau.getTabCases()[node.getPosNode().getLigne()-1][node.getPosNode().getColonne()+1].setDistanceEntre(14);
			voisins.add(plateau.getTabCases()[node.getPosNode().getLigne()-1][node.getPosNode().getColonne()+1]);
						
		}
		
		if (voisinS < plateau.getNBR_LIG() && voisinE < plateau.getNBR_COL()) {
			
			plateau.getTabCases()[node.getPosNode().getLigne()+1][node.getPosNode().getColonne()+1].setDistanceEntre(14);
			voisins.add(plateau.getTabCases()[node.getPosNode().getLigne()+1][node.getPosNode().getColonne()+1]);
						
		}

		if (voisinN > -1 && voisinO > -1) {
			
			plateau.getTabCases()[node.getPosNode().getLigne()-1][node.getPosNode().getColonne()-1].setDistanceEntre(14);
			voisins.add(plateau.getTabCases()[node.getPosNode().getLigne()-1][node.getPosNode().getColonne()-1]);
				
		}
		
		if (voisinS < plateau.getNBR_LIG() && voisinO > -1) {
			
			plateau.getTabCases()[node.getPosNode().getLigne()+1][node.getPosNode().getColonne()-1].setDistanceEntre(14);
			voisins.add(plateau.getTabCases()[node.getPosNode().getLigne()+1][node.getPosNode().getColonne()-1]);
						
		}
		
		return voisins;
		
	}
	
	private static boolean estDansLstOuverte (CaseAbstract node , ArrayList<CaseAbstract> lstOuverte) {
		
		
		boolean bool = false;
		
		if(lstOuverte.contains(node)) {
			
			bool = true;
			
		}
		
		return bool;
				
	}
	
	private static boolean estDansLstFermee (CaseAbstract node , ArrayList<CaseAbstract> lstFermee) {
				
		boolean bool = false;
		
		if(lstFermee.contains(node)) {
			
			bool = true;
			
		}
		
		return bool;
		
	}
	
}
