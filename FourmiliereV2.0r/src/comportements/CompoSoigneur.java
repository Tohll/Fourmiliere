package comportements;

import java.util.Enumeration;

import fourmies.Guerriere;
import fourmies.Ouvriere;
import fourmies.Reine;
import fourmies.Soigneur;
import mecaniques.Fourmiliere;
import messages.BoiteMessagesSingleton;
import messages.MessageBlesse;
import messages.MessageRecuperationOk;

public class CompoSoigneur implements ComportementsInterface {
	
	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Reine)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Reine reine) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Soigneur)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Soigneur soigneur) {
		
		soigneur.perteDeVie(1);
		
		if (soigneur.getPointsDeVie() > 0) {
			
			if (soigneur.isAller()) {
				
				if (!soigneur.isEnService()) {	//n'est pas en service
					
					if (!fourmiliere.getPopulationInactive().isEmpty()) {
						
						Enumeration<Integer> enumInPopKeys = fourmiliere.getPopulationInactive().keys();
						int key;
						
						while (enumInPopKeys.hasMoreElements()) {
							
							key = enumInPopKeys.nextElement();
							
							if (!fourmiliere.getPopulationInactive().get(key).isPriseEnCharge()) {
								
								fourmiliere.getPopulationInactive().get(key).setPriseEnCharge(true);
								
								soigneur.setChoix(key);
								soigneur.setEnService(true);
								
								System.out.println(soigneur.getNom() + " prend " + fourmiliere.getPopulationInactive().get(key).getNom() + " en charge et commence a se diriger vers elle.");
								
								soigneur.seDeplace();
								break;
							}
							
						}
						
					}
					
				} else {	//est en service
					
					if (soigneur.getPosCourante() < fourmiliere.getPopulationInactive().get(soigneur.getChoix()).getPosCourante()) {
						
						System.out.println(soigneur.getNom() + " se dirige vers " + fourmiliere.getPopulationInactive().get(soigneur.getChoix()).getNom() + ".");
						
						soigneur.seDeplace();
						
					} else {	//est arrivee a la fourmie blessee
						
						soigneur.setAller(false);
						soigneur.setPosCourante(fourmiliere.getPopulationInactive().get(soigneur.getChoix()).getPosCourante());
						
						System.out.println(soigneur.getNom() + " recupere " + fourmiliere.getPopulationInactive().get(soigneur.getChoix()).getNom() + ".");
						
					}
					
				}
				
			} else {	//sur le retour
				
				if (soigneur.getPosCourante() > 0) {
					
					System.out.println(soigneur.getNom() + " rentre a la fourmiliere avec " + fourmiliere.getPopulationInactive().get(soigneur.getChoix()).getNom() + ".");
					
					soigneur.seDeplace();
					
				} else {	//est arrivee a la fourmiliere
					
					soigneur.setAller(true);
					soigneur.setEnService(false);
					soigneur.setPosCourante(0);
					
					System.out.println(soigneur.getNom() + " a ramene " + fourmiliere.getPopulationInactive().get(soigneur.getChoix()).getNom() + " a la fourmiliere.");
					
					BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageRecuperationOk(soigneur));
					
				}
				
			}
			
		} else {	//n'a plus de points de vie
			
			if (soigneur.isEnService()) {	//si en service
				
				if (soigneur.isAller()) {	//si sur l'aller
					
					fourmiliere.getPopulationInactive().get(soigneur.getChoix()).setPriseEnCharge(false);	//on definit la fourmie transportee sur non prise en charge pour qu'une autre soigneuse puisse s'en occuper
					
					System.out.println(soigneur.getNom() + " est blessee et doit abandonner " + fourmiliere.getPopulationInactive().get(soigneur.getChoix()).getNom() + ".");
					
					BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageBlesse(soigneur));	//envoi d'un message de blessure de la part de la soigneuse
					
				} else {	// sur le retour
					
					fourmiliere.getPopulationInactive().get(soigneur.getChoix()).setPriseEnCharge(false);	//on definit la fourmie transportee sur non prise en charge pour qu'une autre soigneuse puisse s'en occuper
					fourmiliere.getPopulationInactive().get(soigneur.getChoix()).setPosCourante(soigneur.getPosCourante());	//on definit la position de la fourmie transportee a celle courante de la soigneuse
					
					System.out.println(soigneur.getNom() + " est blessee et doit abandonner " + fourmiliere.getPopulationInactive().get(soigneur.getChoix()).getNom() + ".");
					
					BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageBlesse(soigneur));	//envoi d'un message de blessure de la part de la soigneuse
					
				}
				
			} else {	//si pas en service
				
				BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageBlesse(soigneur));	//envoi d'un message de blessure de la part de la soigneuse
				
			}
			
		}
		
	}

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Guerriere)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Guerriere guerriere) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see comportements.ComportementsInterface#Comportement(mecaniques.Fourmiliere, fourmies.Ouvriere)
	 */
	@Override
	public void Comportement(Fourmiliere fourmiliere, Ouvriere ouvriere) {
		// TODO Auto-generated method stub
		
	}

}
