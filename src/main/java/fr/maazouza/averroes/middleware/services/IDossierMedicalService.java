package fr.maazouza.averroes.middleware.services;

import java.util.List;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
import fr.maazouza.averroes.middleware.objetmetier.maladie.MaladieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;

public interface IDossierMedicalService{

	void ajouterDossierMedical(DossierMedical dossier) throws DossierMedicalDejaExistantException;

	DossierMedical consulterUnDossierMedical(Long idPat);

	boolean existerParId(Long idPat);

	List<DossierMedical> obtenir();

	void ajouterMaladie(Maladie maladie);

	void modifierDossierMedical(DossierMedical dossierMedical);

	DossierMedical obtenirUnDossierMedical(Long idDos) throws DossierMedicalInexistantException;

	
	void supprimerUnDossierMedical(Long idDos);

	boolean exister(Patient patient);

	boolean existerParIdDos(Long idDos);

	Maladie obtenirMaladie(Long idMal) throws  MaladieInexistanteException;

	void supprimerMaladie(Long idMal) throws MaladieInexistanteException;

	void modifierMaladie(Maladie maladie) throws MaladieInexistanteException;

	

}
