package mecaniques;

public class SiteNourriture {
	
	private int distance;
	private String nom;
	private int stockNourriture;
	
	protected static int nbrSites = 1;	
	
	public int getStockNourriture() {
		return stockNourriture;
	}

	public void setStockNourriture(int stockNourriture) {
		this.stockNourriture = stockNourriture;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public SiteNourriture(int distance) {
		
		this.nom = ("Site n°" + nbrSites);
		this.distance = distance;
		this.stockNourriture = 100;
		nbrSites++;
	}

}
