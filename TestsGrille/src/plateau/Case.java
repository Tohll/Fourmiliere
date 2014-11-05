package plateau;

import java.awt.Color;
import java.awt.Graphics;

public class Case {	
	
	private int posX;
	private int posY;
	private int g;
	private int h;
	private int f;
	private Case parent;	
	private boolean obstacle;
	private boolean siteNourriture;
		
	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public Case getParent() {
		return parent;
	}

	public void setParent(Case parent) {
		this.parent = parent;
	}

	public boolean isSiteNourriture() {
		return siteNourriture;
	}

	public void setSiteNourriture(boolean siteNourriture) {
		this.siteNourriture = siteNourriture;
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
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

	public Case (int x , int y) {
		
		this.siteNourriture = false;
		this.obstacle = false;
		this.posX = x;
		this.posY = y;
		this.g = this.f = this.h = 0;
		this.parent = this;
		
	}
	
	public void afficher (int x , int y , int width , int height , Graphics g) {
				
		if (this.isObstacle()) {
			
			g.setColor(Color.gray);
			g.fillRect(x, y, width, height);
			
		}			
		
		if (this.isSiteNourriture()) {
			
			g.setColor(new Color(102 , 68 , 0));
			g.fillRect(x, y, width, height);
			
		}
		
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);	
		
		
	}
	
}


