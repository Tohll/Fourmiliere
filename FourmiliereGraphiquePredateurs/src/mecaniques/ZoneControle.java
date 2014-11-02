package mecaniques;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ZoneControle extends JPanel {
	
	private JTextArea champTexte;
	
	public ZoneControle () {
		
		this.champTexte = new JTextArea();
		this.champTexte.setPreferredSize(new Dimension(175,590));
		this.champTexte.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(champTexte);		
		this.add(scroll, BorderLayout.CENTER);
		
	}

}