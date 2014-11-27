/**
 * 
 */
package deplacements;

import fourmies.FourmieAbstract;

/**
 * La classe <b>DeplacementNormal.java</b>
 * @author Seldan
 *
 */
public class DeplacementNormal implements DeplacementsInterface {

	/* (non-Javadoc)
	 * @see comportements.DeplacementsInterface#Deplacement(fourmies.FourmieAbstract)
	 */
	@Override
	public void Deplacer(FourmieAbstract fourmie) {
		
		if (fourmie.isAller()) {
			
			fourmie.setPosCourante(fourmie.getPosCourante() + fourmie.getVitesse());
			
		} else {
			
			fourmie.setPosCourante(fourmie.getPosCourante() - fourmie.getVitesse());
			
		}
		
	}

}
