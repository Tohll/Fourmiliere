package mecaniques;

import java.util.ArrayList;

public class Terrain {

	private SiteNourriture[] terrain;
	
	public SiteNourriture[] getTerrain() {
		return terrain;
	}

	public void setTerrain(SiteNourriture[] terrain) {
		this.terrain = terrain;
	}

	public Terrain() {
		
		this.terrain = InitTerrain();
	}
	
	private SiteNourriture[] InitTerrain() {
		
		ArrayList<SiteNourriture> terrainArrayList = new ArrayList<>();
		SiteNourriture[] terrainInit;
		
		for (int i=1 ; i<=5 ; i++) {
			
			terrainArrayList.add(new SiteNourriture(i));
		}
		
		terrainArrayList.toArray(terrainInit = new SiteNourriture[terrainArrayList.size()]);
		
		return terrainInit;
	}
}
