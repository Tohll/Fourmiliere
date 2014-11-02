package mecaniques;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JLabel;

import predateurs.Predateur;
import fourmiz.*;

public class Fourmiliere {

	public static int index = 0;
	
	private int posX;
	private int posY;
	private int stockNourriture;
	private JLabel icone;
	
	private Terrain terrainInit;
	private JLabel nom;
	private JLabel affichageNourriture;	
	private JLabel affPopTotale;
	private JLabel etat;
	private JLabel affOuv;
	private JLabel affGue;
	private JLabel affSoin;
	
	private Hashtable<Integer, FourmieAbstract> population;
	private Hashtable<Integer, Predateur> listePred;
	
	/*private int[] tabX = {400,425,450};
	private int[] tabY = {325,300,325};*/
	
	public Hashtable<Integer, Predateur> getListePred() {
		return listePred;
	}

	public JLabel getAffOuv() {
		return affOuv;
	}

	public void setAffOuv(JLabel affOuv) {
		this.affOuv = affOuv;
	}

	public JLabel getAffGue() {
		return affGue;
	}

	public void setAffGue(JLabel affGue) {
		this.affGue = affGue;
	}

	public JLabel getAffSoin() {
		return affSoin;
	}

	public void setAffSoin(JLabel affSoin) {
		this.affSoin = affSoin;
	}

	public JLabel getEtat() {
		return etat;
	}

	public void setEtat(JLabel etat) {
		this.etat = etat;
	}

	public JLabel getAffPopTotale() {
		return affPopTotale;
	}

	public void setAffPopTotale(JLabel affPopTotale) {
		this.affPopTotale = affPopTotale;
	}

	public JLabel getIcone() {
		return icone;
	}

	public void setIcone(JLabel icone) {
		this.icone = icone;
	}

	public void setListePred(Hashtable<Integer, Predateur> listePred) {
		this.listePred = listePred;
	}

	public Hashtable<Integer, FourmieAbstract> getPopulation() {
		return population;
	}

	public void setPopulation(Hashtable<Integer, FourmieAbstract> population) {
		this.population = population;
	}

	/*public int[] getTabX() {
		return tabX;
	}

	public void setTabX(int[] tabX) {
		this.tabX = tabX;
	}

	public int[] getTabY() {
		return tabY;
	}

	public void setTabY(int[] tabY) {
		this.tabY = tabY;
	}*/

	public int getStockNourriture() {
		return stockNourriture;
	}

	public void setStockNourriture(int stockNourriture) {
		this.stockNourriture = stockNourriture;
	}

	public Terrain getTerrainInit() {
		return terrainInit;
	}

	public void setTerrainInit(Terrain terrainInit) {
		this.terrainInit = terrainInit;
	}

	public JLabel getNom() {
		return nom;
	}

	public void setNom(JLabel nom) {
		this.nom = nom;
	}

	public JLabel getAffichageNourriture() {
		return affichageNourriture;
	}

	public void setAffichageNourriture(JLabel affichageNourriture) {
		this.affichageNourriture = affichageNourriture;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Fourmiliere (Simulation simu) {
		
		this.stockNourriture = 40;
		this.terrainInit = new Terrain(simu);
		this.population = new Hashtable<>();
		this.listePred = new Hashtable<>();
		
		this.initPopulation(simu);
		
		this.posX = 400;
		this.posY = 300;
		
		this.nom = new JLabel("Fourmiliere:");
		this.affichageNourriture = new JLabel("Nourriture:" + this.stockNourriture);
		this.icone = new JLabel(simu.getSpritesFourmie()[26]);
		this.affPopTotale = new JLabel("Population:" + this.getPopulation().size());
		
		this.etat = new JLabel("Patrouilles...");
		this.affOuv = new JLabel("Ouvriere(s):");
		this.affGue = new JLabel("Guerriere(s):");
		this.affSoin = new JLabel("Soigneur(s):");
		
		simu.setLayout(null);
		simu.add(this.getNom());
		simu.add(this.getAffichageNourriture());
		simu.add(this.getIcone());
		simu.add(this.getAffPopTotale());
		simu.add(this.getEtat());
		simu.add(this.getAffOuv());
		simu.add(this.getAffGue());
		simu.add(this.getAffSoin());
		
		this.getNom().setFont(new Font(this.getNom().getFont().getName() , Font.PLAIN , 16));
		Dimension size = this.getNom().getPreferredSize();		
		this.getNom().setBounds(25 , 434 , size.width , size.height);		
		
		this.getAffPopTotale().setForeground(Color.lightGray);
		this.getAffichageNourriture().setForeground(Color.lightGray);	
		this.getNom().setForeground(Color.lightGray);
		
		this.getAffichageNourriture().setFont(new Font(this.getAffichageNourriture().getFont().getName() , Font.ITALIC , 15));
		size = this.getAffichageNourriture().getPreferredSize();
		this.getAffichageNourriture().setBounds(this.getPosX() , this.getPosY()+30 , size.width , size.height);
		
		this.getAffPopTotale().setFont(new Font(this.getAffPopTotale().getFont().getName() , Font.ITALIC , 15));
		size = this.getAffPopTotale().getPreferredSize();
		this.getAffPopTotale().setBounds(this.getPosX() , this.getPosY()+30 , size.width , size.height);
		
		size = this.getIcone().getPreferredSize();
		this.getIcone().setBounds(this.getPosX()-40 , this.getPosY()-20 , size.width , size.height);
		
		this.getEtat().setFont(new Font(this.getEtat().getFont().getName() , Font.ITALIC , 20));
		size = this.getEtat().getPreferredSize();
		this.getEtat().setBounds(this.getPosX() , this.getPosY()+30 , size.width , size.height);
		
	}
	
	public void initPopulation(Simulation simu) {
		
		this.getPopulation().put(Fourmiliere.index , new Reine(this, simu));				
		
		/*for (int i = 0 ; i < 10 ; i++) {
			
			this.getPopulation().put(Fourmiliere.index , new Ouvriere(this, simu));			
			
		}
		
		for (int i=0 ; i<3 ; i++) {
			
			this.getPopulation().put(Fourmiliere.index , new Guerriere(this, simu));			
			
		}
		
		for (int i=0 ; i<2 ; i++) {
			
			this.getPopulation().put(Fourmiliere.index , new Soigneur(this, simu));
			
		}*/
		
	}
	
	public void afficher (Graphics2D g) {
		
		int comptActifs = 0;
		int comptOuv = 0;
		int comptGuer = 0;
		int comptSoin = 0;		
		
		Enumeration<FourmieAbstract> ePop = this.getPopulation().elements();
		
		while (ePop.hasMoreElements()) {
					
			FourmieAbstract fourmie;
			fourmie = ePop.nextElement();
			
			if(fourmie.isEstActive()) {
				
				comptActifs++;
				
				if (fourmie instanceof Ouvriere) {
					
					comptOuv++;
					
				}
				
				if (fourmie instanceof Guerriere) {
					
					comptGuer++;
					
				}
				
				if (fourmie instanceof Soigneur) {
					
					comptSoin++;
					
				}
				
			}			
			
		}
		
		if (Simulation.attaque) {
			
			this.getEtat().setForeground(Color.red);
			this.getEtat().setText("Attaque en cours !");
			
		} else {
			
			this.getEtat().setForeground(Color.green);
			this.getEtat().setText("Patrouilles...");
			
		}
		
		//g.setColor(new Color(102,102,0));
		Dimension size = this.getAffichageNourriture().getPreferredSize();
		this.getAffichageNourriture().setBounds(25 , 451 , size.width , size.height);
		this.getAffichageNourriture().setText("Nourriture:" + this.getStockNourriture());
		
		size = this.getAffPopTotale().getPreferredSize();
		this.getAffPopTotale().setBounds(25 , 465 , size.width , size.height);
		this.getAffPopTotale().setText("Population / Active: " + this.getPopulation().size() + " / " + comptActifs);
		//g.fillPolygon(this.getTabX() , this.getTabY() , 3);
		
		size = this.getEtat().getPreferredSize();
		this.getEtat().setBounds(25 , 480 , size.width , size.height);
				
	}
	
}
