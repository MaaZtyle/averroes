/**
 * 
 */
package fr.maazouza.averroes.middleware.services;

import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;

import java.util.Collection;
import java.util.List;

/**
 * @author Maazouza
 *
 */
public interface IPatientService {
	
public void ajouterPatient(Patient patient)  throws PatientDejaExistantException ;
			
	
public void ajouterPatients(Collection<Patient> patients);
			

public List<Patient> obtenirPatients(String predicat);

public void modifierPatient(Patient patient);



public void supprimerPatient(String nomPatient);


boolean existerParNomEtPrenom(Patient patient);


Patient obtenirUnPatient(String nom, String prenom);


Medecin obtenirMedecinDunPatient(long id_pat);


}


	
	



