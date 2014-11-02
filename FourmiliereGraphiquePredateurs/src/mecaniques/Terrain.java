package mecaniques;

import java.util.ArrayList;

public class Terrain {

	private ArrayList<SiteNourriture> terrainTab;
	
	public ArrayList<SiteNourriture> getTerrainTab() {
		return terrainTab;
	}

	public void setTerrainTab(ArrayList<SiteNourriture> terrainTab) {
		this.terrainTab = terrainTab;
	}

	public Terrain(Simulation panel) {
		
		this.terrainTab = new ArrayList<>();
		initTerrain(panel);
		
	}
	
	private void initTerrain(Simulation simu) {
		
		this.getTerrainTab().add(new SiteNourriture(20, 20, simu));
		this.getTerrainTab().add(new SiteNourriture(30, 300, simu));
		this.getTerrainTab().add(new SiteNourriture(380, 500, simu));
		this.getTerrainTab().add(new SiteNourriture(700, 520, simu));
		this.getTerrainTab().add(new SiteNourriture(600, 80, simu));
		this.getTerrainTab().add(new SiteNourriture(500, 240, simu));
		this.getTerrainTab().add(new SiteNourriture(300, 380, simu));
		this.getTerrainTab().add(new SiteNourriture(350, 150, simu));
		
	}
	
}
