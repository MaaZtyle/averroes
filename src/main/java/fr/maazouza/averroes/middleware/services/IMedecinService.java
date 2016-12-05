package fr.maazouza.averroes.middleware.services;

import java.util.Collection;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;

public interface IMedecinService {
	
	public void ajouterMedecin(Medecin medecin);
			
	
	public void ajouterMedecins(Collection<Medecin> medecins);
			

	public Medecin obtenirMedecin(Integer id);


}
