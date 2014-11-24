package main;

import java.awt.Color;

import javax.swing.JFrame;

import plateau.Plateau;

public class Main {	
	
	public static void main(String[] args) {
		
		JFrame fenetre = new JFrame("Grille");
		final Plateau plateau = new Plateau();		
				
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
		fenetre.setSize(plateau.getPreferredSize().width+27, plateau.getPreferredSize().height+49);
		
		fenetre.setVisible(true);
			
		Thread tComportements = new Thread("Thead des comportements") {
			
            public void run() {
            	
            	while (true) {           		
                	
            		plateau.comportements();
   					
        			try {
        				
        				Thread.sleep(25);
        				
        			} catch (InterruptedException e) {
        				
        				System.out.println("probleme de thread");
        				System.exit(0);
        				
        			}
            		
            	}
            	
            }
            
        };
		
        
        
		Thread tAffichage = new Thread("Thead des affichages") {
			
            public void run() {
            	
            	while (true) {           		
                	
            		plateau.repaint();  					
        			
            		try {
        				
        				Thread.sleep(25);
        				
        			} catch (InterruptedException e) {
        				
        				System.out.println("probleme de thread");
        				System.exit(0);
        				
        			}
            		
            	}
            	
            }
            
        };
        
        
        tAffichage.start();
        tComportements.start();
	}

}
