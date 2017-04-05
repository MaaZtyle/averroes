/**
 * 
 */
package fr.maazouza.averroes.middleware.services;

import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.NomOuMotDePasseException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;

import java.util.Collection;
import java.util.List;

/**
 * @author Maazouza
 *
 */
public interface IPatientService {
	


public List<Patient> obtenirPatients();

//public void modifierPatient(Patient patient);


Medecin obtenirMedecinDunPatient(long idPat);


boolean existerParNomEtPrenom(Patient patient);

Patient obtenirUnPatient(Long idPat) throws PatientInexistantException;

boolean existerId(Long idPat);

String authentifierUnPatient(String nom, String motDePasse) throws NomOuMotDePasseException;

boolean existerPareMail(String eMail);










//public List<Patient> obtenirUnPatients();




}


	
	



