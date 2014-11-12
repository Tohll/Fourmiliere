package plateau;

import java.awt.Color;
import java.awt.Graphics;

/**
 * CaseStandard.java
 * @since 9 nov. 2014
 * @version 0.1 
 * @author Charbel FOUREL
 *
 */
public class CaseStandard extends CaseAbstract {

	/**
	 * Initialise une case standart avec le parametre obstacle en false par defaut et les parametres suivants:
	 * @param x Position absolue de la case
	 * @param y position absolue de la case
	 * @param lgn index de ligne de la case dans le tableau des cases
	 * @param col index de colonne de la case dans le tableau des cases
	 */
	public CaseStandard(int x, int y, int lgn, int col , Plateau plateau) {
		
		super(x, y, lgn, col , plateau);
		
	}

	/* (non-Javadoc)
	 * @see plateau.CaseAbstract#afficher(int, int, int, int, java.awt.Graphics, plateau.Plateau)
	 */
	@Override
	public void afficher(int x, int y, int width, int height, Graphics g, Plateau plateau) {
		
		if (this.isObstacle()) {
			
			g.setColor(new Color(89 , 89 , 89));
			
			if (plateau.getTabCases()[this.getPosNode().getLigne()+1][this.getPosNode().getColonne()].isObstacle() && plateau.getTabCases()[this.getPosNode().getLigne()][this.getPosNode().getColonne()-1].isObstacle() && plateau.getTabCases()[this.getPosNode().getLigne()-1][this.getPosNode().getColonne()].isObstacle() == false && plateau.getTabCases()[this.getPosNode().getLigne()][this.getPosNode().getColonne()+1].isObstacle() == false) {
				
				this.getTabX()[0] = x;
				this.getTabX()[1] = x;
				this.getTabX()[2] = x+10;
				
				this.getTabY()[0] = y;
				this.getTabY()[1] = y+10;
				this.getTabY()[2] = y+10;
				
				g.fillPolygon(this.getTabX() , this.getTabY() , 3);
				
			} else if(plateau.getTabCases()[this.getPosNode().getLigne()][this.getPosNode().getColonne()-1].isObstacle() && plateau.getTabCases()[this.getPosNode().getLigne()-1][this.getPosNode().getColonne()].isObstacle() && plateau.getTabCases()[this.getPosNode().getLigne()][this.getPosNode().getColonne()+1].isObstacle() == false && plateau.getTabCases()[this.getPosNode().getLigne()+1][this.getPosNode().getColonne()].isObstacle() == false) {
				
				this.getTabX()[0] = x;
				this.getTabX()[1] = x+10;
				this.getTabX()[2] = x;
				
				this.getTabY()[0] = y;
				this.getTabY()[1] = y;
				this.getTabY()[2] = y+10;
				
				g.fillPolygon(this.getTabX() , this.getTabY() , 3);
				
			} else if(plateau.getTabCases()[this.getPosNode().getLigne()-1][this.getPosNode().getColonne()].isObstacle() && plateau.getTabCases()[this.getPosNode().getLigne()][this.getPosNode().getColonne()+1].isObstacle() && plateau.getTabCases()[this.getPosNode().getLigne()+1][this.getPosNode().getColonne()].isObstacle() == false && plateau.getTabCases()[this.getPosNode().getLigne()][this.getPosNode().getColonne()-1].isObstacle() == false) {
				
				this.getTabX()[0] = x;
				this.getTabX()[1] = x+10;
				this.getTabX()[2] = x+10;
				
				this.getTabY()[0] = y;
				this.getTabY()[1] = y;
				this.getTabY()[2] = y+10;
				
				g.fillPolygon(this.getTabX() , this.getTabY() , 3);
				
			} else if(plateau.getTabCases()[this.getPosNode().getLigne()+1][this.getPosNode().getColonne()].isObstacle() && plateau.getTabCases()[this.getPosNode().getLigne()][this.getPosNode().getColonne()+1].isObstacle() && plateau.getTabCases()[this.getPosNode().getLigne()][this.getPosNode().getColonne()-1].isObstacle() == false && plateau.getTabCases()[this.getPosNode().getLigne()-1][this.getPosNode().getColonne()].isObstacle() == false) {
				
				this.getTabX()[0] = x+10;
				this.getTabX()[1] = x+10;
				this.getTabX()[2] = x;
				
				this.getTabY()[0] = y;
				this.getTabY()[1] = y+10;
				this.getTabY()[2] = y+10;
				
				g.fillPolygon(this.getTabX() , this.getTabY() , 3);
				
			} else {
				
				g.fillRect(x, y, width, height);
				
			}
			
		}		
		
		/*g.setColor(Color.black);
		g.drawRect(x, y, width, height);*/
		
	}

}
