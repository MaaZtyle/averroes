package fr.maazouza.averroes.middleware.services;

import java.util.List;

import fr.maazouza.averroes.middleware.objetmetier.allergie.Allergie;
import fr.maazouza.averroes.middleware.objetmetier.allergie.AllergieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.antecedent.Antecedent;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalAvecAllergieException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalAvecMaladieException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalAvecOrdonnanceException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalAvecVaccinException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
import fr.maazouza.averroes.middleware.objetmetier.maladie.MaladieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.Ordonnance;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.OrdonnanceInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.vaccin.Vaccin;


public interface IDossierMedicalService{

	void ajouterDossierMedical(DossierMedical dossier) throws DossierMedicalDejaExistantException;

	DossierMedical consulterUnDossierMedical(Long idPat);

	boolean existerParId(Long idPat);

	List<DossierMedical> obtenir();

	void modifierDossierMedical(DossierMedical dossierMedical);

	DossierMedical obtenirUnDossierMedical(Long idDos) throws DossierMedicalInexistantException;

	
	void supprimerUnDossierMedical(Long idDos) throws DossierMedicalAvecMaladieException, DossierMedicalAvecAllergieException, DossierMedicalAvecOrdonnanceException, DossierMedicalAvecVaccinException;

	boolean exister(Patient patient);

	boolean existerParIdDos(Long idDos);

	List<Maladie> obtenirMaladies(Long idDos);

	List<Allergie> obtenirAllergies(Long idDos);

	List<Antecedent> obtenirAntecedents(Long idDos);

	List<Ordonnance> obtenirOrdonnances(Long idDos);

	List<Vaccin> obtenirVaccins(Long idDos);

	List<Vaccin> obtenirVaccinsArchives(Long idDos);

	
	

}
