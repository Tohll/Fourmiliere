package fourmiz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import mecaniques.Fourmiliere;
import mecaniques.Simulation;

public class Reine extends FourmieAbstract {

	private int cycle;
	private JLabel nom;
	private JLabel pv;
		
	public JLabel getNom() {
		return nom;
	}

	public void setNom(JLabel nom) {
		this.nom = nom;
	}

	public JLabel getPv() {
		return pv;
	}

	public void setPv(JLabel pv) {
		this.pv = pv;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public Reine (Fourmiliere f , Simulation simu) {
		
		super("Reine" , 0 , 100 , f , simu);
		this.cycle = 850;
		
		this.nom = new JLabel("Reine:");
		this.pv = new JLabel("PV:" + this.getPointsDeVie());
		
		simu.setLayout(null);
		simu.add(this.getNom());
		simu.add(this.getPv());
		
		this.getNom().setForeground(Color.lightGray);
		this.getPv().setForeground(Color.lightGray);
		
		this.getNom().setFont(new Font(this.getNom().getFont().getName() , Font.PLAIN , 15));
		Dimension size = this.getNom().getPreferredSize();		
		this.getNom().setBounds(25 , 390 , size.width , size.height);
		
		this.getPv().setFont(new Font(this.getPv().getFont().getName() , Font.ITALIC , 15));
		size = this.getPv().getPreferredSize();
		this.getPv().setBounds(25 , 405 , size.width , size.height);
				
	}
	
	@Override
	public void afficher(Graphics2D g , Simulation simu) {
		
		Dimension size = this.getPv().getPreferredSize();
		this.getPv().setText("Vie:" + this.getPointsDeVie());
		this.getPv().setBounds(25 , 405 , size.width , size.height);

	}

	@Override
	public void comportement(Fourmiliere f , Simulation simu) {
		
		this.setCycle(this.getCycle()+1);
		
		if (this.getCycle() == 250) {
			
			if (f.getStockNourriture() >= 9) {
				
				f.getPopulation().put(Fourmiliere.index , new Ouvriere(f, simu));
				f.setStockNourriture(f.getStockNourriture()-9);
				
			} else {
				
				this.setPointsDeVie(this.getPointsDeVie()-(9-f.getStockNourriture()));
				f.setStockNourriture(0);
				
			}
			
		}
		
		if (this.getCycle() == 900) {
			
			if (f.getStockNourriture() >= 9) {
				
				f.setStockNourriture(f.getStockNourriture()-9);
				
				for (int i = 0 ; i < 10 ; i++) {
					
					f.getPopulation().put(Fourmiliere.index , new Ouvriere(f, simu));			
					
				}
				
				for (int i=0 ; i < 3 ; i++) {
					
					f.getPopulation().put(Fourmiliere.index , new Guerriere(f, simu));			
					
				}
				
				for (int i=0 ; i < 3 ; i++) {
					
					f.getPopulation().put(Fourmiliere.index , new Soigneur(f, simu));
					
				}
				
			} else {
				
				this.setPointsDeVie(this.getPointsDeVie()-5);
				f.setStockNourriture(Math.round(f.getStockNourriture()/2));
				f.getPopulation().put(Fourmiliere.index , new Ouvriere(f, simu));
				
			}
			
			this.setCycle(0);
			
		}
		
		if (this.getPointsDeVie() <= 0) {
			
			JOptionPane.showMessageDialog(simu,
				    "La reine est morte",
				    "Fin de simulation",
				    JOptionPane.WARNING_MESSAGE);
			
			System.exit(0);
			
		}

	}

	@Override
	public void deplacement(Fourmiliere f) {
		//ne se deplace pas

	}

}
