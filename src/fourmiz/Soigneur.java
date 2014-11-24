package fourmiz;

import java.util.Enumeration;

import mecaniques.Fourmiliere;
import messages.BoiteMessagesSingleton;
import messages.MessageBlesse;
import messages.MessageRecuperationOk;
/**
 * @author Charbel FOUREL
 * @version 0.1
 *
 */
public final class Soigneur extends FourmieAbstract {

	private boolean enService;
	
	public boolean isEnService() {
		return enService;
	}

	public void setEnService(boolean enService) {
		this.enService = enService;
	}

	public Soigneur(Fourmiliere f) {
		
		super("Soigneur " + nbrSoigneur , 15 , 2 , f);
		nbrSoigneur++;
		
		enService = false;
		
	}
	
	@Override
	public void Comportement(Fourmiliere f) {		

		if (this.isEstActive()) {
			
			if (this.isEnService()) {
				
				if (this.isAller()) {
					
					if (this.getPosition() < f.getPopulation().get(this.getChoix()).getPosition()) {
						
						this.setPosition(this.getPosition() + this.getVitesse());
						this.setPointsDeVie(this.getPointsDeVie() - 1);
						
						System.out.println("La fourmie (" + this.getType() + ") avance vers la fourmie " + f.getPopulation().get(this.getChoix()).getType() + ".");
						
					} else {						
						
						System.out.println("La fourmie (" + this.getType() + ") recupere la fourmie " + f.getPopulation().get(this.getChoix()).getType() + ".");
						
						this.setPointsDeVie(this.getPointsDeVie() - 1);
						
						this.setAller(false);
						
					}
					
					if (this.getPointsDeVie() <= 0) {
																		
						f.getPopulation().get(this.getChoix()).setPriseEnCharge(false);						
						this.setEstActive(false);
						
					}
					
				} else {
					
					if (this.getPosition() > 0) {
						
						this.setPosition(this.getPosition() - this.getVitesse());
						this.setPointsDeVie(this.getPointsDeVie() - 1);
						
						System.out.println("La fourmie (" + this.getType() + ") retourne a la fourmiliere avec la fourmie " + f.getPopulation().get(this.getChoix()).getType() + ".");
						
					} else {
						
						int index = -1;
						
						Enumeration<Integer> ePop = f.getPopulation().keys();
						
						while (ePop.hasMoreElements()) {
							
							int key = ePop.nextElement();
							
							if (f.getPopulation().get(key) == this) {
								
								index = key;
								break;
								
							}
							
						}
						
						BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageRecuperationOk(this , index));
						//this.setEnService(false);
						this.setAller(true);
						//this.setChoix(-1);
					}
					
					if (this.getPointsDeVie() <= 0) {
						
						this.setEstActive(false);
												
						f.getPopulation().get(this.getChoix()).setPosition(this.getPosition());
						f.getPopulation().get(this.getChoix()).setPriseEnCharge(false);						
						
					
				}
				
				
					
				}
				
			} else {
				
				this.setPointsDeVie(this.getPointsDeVie() - 1);
				
				if (this.getPointsDeVie() <= 0) {
					
					System.out.println(this.getType() + " est morte a la fourmiliere");
					
					int index = -1;
					
					Enumeration<Integer> ePop = f.getPopulation().keys();
					
					while (ePop.hasMoreElements()) {
						
						int key = ePop.nextElement();
						
						if (f.getPopulation().get(key) == this) {
							
							index = key;
							break;
							
						}
						
					}
					
					f.getPopulation().remove(index);					
					
				}
				
			}
			
		} else {
			
			if (!this.isPriseEnCharge()) {
				
				int index = -1;
				
				Enumeration<Integer> ePop = f.getPopulation().keys();
				
				while (ePop.hasMoreElements()) {
					
					int key = ePop.nextElement();
					
					if (f.getPopulation().get(key) == this) {
						
						index = key;
						break;
						
					}
					
				}
				
				BoiteMessagesSingleton.getInstance().getMessagesArrayList().add(new MessageBlesse(this , index));
				
			}
			
		}
				
	}

}
