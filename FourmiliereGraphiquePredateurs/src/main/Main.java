package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mecaniques.Simulation;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Fourmiliere");
		JPanel zoneAffichage = new JPanel();
		Simulation simulation = new Simulation();
		//ZoneControle zoneControle = new ZoneControle();
				
		frame.setSize(800, 600);
		//zoneControle.setPreferredSize(new Dimension(200,600));
		//zoneControle.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		
		simulation.setPreferredSize(new Dimension(800,600));
		simulation.setBackground(new Color(0,153,51));
				
		//zoneAffichage.add(zoneControle , BorderLayout.WEST);
		zoneAffichage.add(simulation , BorderLayout.CENTER);
		
		frame.add(zoneAffichage);
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);	
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		while (true) {			
			
			simulation.comportements();
			zoneAffichage.repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
