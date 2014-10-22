package mecaniques;

import java.util.ArrayList;
import fourmiz.*;
/**
 * @author Charbel FOUREL
 * @version 0.1
 *
 */
public class Fourmiliere {
	
	private Terrain terrain;
	private int stockNourriture;	
	private ArrayList<FourmieAbstract> population;
	
	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public ArrayList<FourmieAbstract> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<FourmieAbstract> population) {
		this.population = population;
	}

	public int getStockNourriture() {
		return stockNourriture;
	}

	public void setStockNourriture(int stockNourriture) {
		this.stockNourriture = stockNourriture;
	}

	public Fourmiliere() {
		
		this.terrain = null;
		this.population = new ArrayList<>();
		population.add(new Reine());
		this.stockNourriture = 10;
	}
}
