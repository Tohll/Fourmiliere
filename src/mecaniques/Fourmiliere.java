package mecaniques;

import java.util.Hashtable;


import fourmiz.*;
/**
 * @author Charbel FOUREL
 * @version 0.1
 *
 */
public class Fourmiliere {
	
	private Terrain terrain;
	private int stockNourriture;	
	private Hashtable<Integer, FourmieAbstract> population;
	private int indexPop;
	
	public Hashtable<Integer, FourmieAbstract> getPopulation() {
		return population;
	}

	public void setPopulation(Hashtable<Integer, FourmieAbstract> population) {
		this.population = population;
	}

	public int getIndexPop() {
		return indexPop;
	}

	public void setIndexPop(int indexPop) {
		this.indexPop = indexPop;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public int getStockNourriture() {
		return stockNourriture;
	}

	public void setStockNourriture(int stockNourriture) {
		this.stockNourriture = stockNourriture;
	}

	public Fourmiliere() {
		
		this.indexPop = 0;
		this.terrain = null;
		this.population = new Hashtable<>();
		population.put(this.indexPop, new Reine(this));
		this.stockNourriture = 0;
	}
}
