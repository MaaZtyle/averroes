package fr.maazouza.averroes.middleware.services;

import java.util.Collection;
import java.util.List;

import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinAvecPatientsException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;

public interface IMedecinService {
	
	public void ajouterMedecin(Medecin medecin) throws MedecinDejaExistantException;
			
	
	
	boolean existerParNomEtPrenom(Medecin medecin);

	
	Medecin obtenirUnMedecin(Long idMed);


	public void modifierMedecin(Medecin medecin);


	List<Medecin> obtenirMedecins();





	void ajouterPatient(Patient patient) throws PatientDejaExistantException,PatientInexistantException;


	void supprimerPatient(Long idPat);


	Patient obtenirUnPatient(Long idPat);


	//Medecin obtenirMedecinDunPatient(long idPat);


	public void modifierPatient(Patient patient);



	public void supprimerMedecin(Long idMed) throws MedecinInexistantException, MedecinAvecPatientsException;



	boolean existerParId(Long idMed);



	//List<Patient> obtenirPatientsDunMedecin(Long idMed);



	public List<Patient> obtenirPatients();



	List<Patient> obtenirPatientsDunMedecin(Long idMed);


}
