package fr.maazouza.averroes.middleware.services;

import java.util.List;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;

public interface IDossierMedicalService{

	void ajouterDossierMedical(DossierMedical dossier);

	DossierMedical consulterUnDossierMedical(Long idPat);

	boolean existerParId(Long idPat);

	List<DossierMedical> obtenir();

	void ajouterMaladie(Maladie maladie);

	void modifierDossierMedical(DossierMedical dossierMedical);

	DossierMedical obtenirUnDossier(Long idDos);

	
	void supprimerUnDossierMedical(Long idDos);

}
