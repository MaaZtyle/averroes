package fr.maazouza.averroes.middleware.services;

import java.util.List;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;

public interface IDossierMedicalService{

	void ajouterDossierMedical(DossierMedical dossier);

	DossierMedical consulterUnDossierMedical(Long idPat);

	boolean existerParId(Long idPat);

	List<DossierMedical> obtenir();

}
