package fr.maazouza.averroes.middleware.services;

import java.util.Collection;
import java.util.List;

import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;

public interface IMedecinService {
	
	public void ajouterMedecin(Medecin medecin) throws MedecinDejaExistantException;
			
	
	public void ajouterMedecins(Collection<Medecin> medecins);
			

	public List<Medecin> obtenirMedecin(String nom);


	boolean existerParNomEtPrenom(Medecin medecin);

	
	Medecin obtenirUnMedecin(String nom, String prenom);


	public void modifierMedecin(Medecin medecin);


}
