package main;

import java.awt.Color;

import javax.swing.JFrame;

import plateau.Plateau;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame fenetre = new JFrame("Grille");
		Plateau plateau = new Plateau();		
				
		fenetre.setLayout(null);		
		//fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.getContentPane().setBackground(new Color(102 , 68 , 0));
		
		fenetre.getContentPane().add(plateau);						
				
		plateau.setBounds(10 , 10 , plateau.getPreferredSize().width+1 , plateau.getPreferredSize().height+1);
		plateau.remplissageTabCases();
		
		plateau.fourmiliereInitiale();
		
		fenetre.pack();
		fenetre.setVisible(true);
		
		while (true) {					
			
			plateau.comportements();
			
			fenetre.setSize(plateau.getPreferredSize().width+27, plateau.getPreferredSize().height+49);
			
			plateau.repaint();
			
			try {
				
				Thread.sleep(5);
				
			} catch (InterruptedException e) {
				
				System.out.println("probleme de thread");
				System.exit(0);
				
			}
			
		}

	}

}
