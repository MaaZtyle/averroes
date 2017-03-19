/**
 * 
 */
package fr.maazouza.averroes.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import fr.maazouza.averroes.middleware.dao.PatientDao;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;

/**
 * @author Maazouza
 *
 */
@Stateless
public class PatientService implements IPatientService{
	
	@EJB
	private PatientDao patientDao;
	
	
	
	@EJB
	private IPatientService patientService;
	
	@Override
	public boolean existerParNomEtPrenom(Patient patient) {
		
		//je cherche tous les patients
		List<Patient> listePatient = patientDao.obtenir();
		
		// ensuite je filtre sur le nom et prénom
		
		Patient result = listePatient.stream()
			     .filter(item -> item.getPrenomPat().equals(patient.getPrenomPat()))
			     .filter(item -> item.getNomPat().equals(patient.getNomPat()))
			     .findFirst()
			     .orElse(null);
		
		 
		
		//si le nom et le prenom sont les meme, je retourne true
		if( result == null)
			return false;
			else return true;
					
			
			     	
	}

	/*@Override
	public void ajouterPatient(Patient patient) throws PatientDejaExistantException {
				
		if((existerParNomEtPrenom(patient)==true ))
			throw new PatientDejaExistantException("Le Patient"+ patient.getNomPat()+ " existe déjà");
		else patientDao.persister(patient);
		
	}*/


	@Override
	public List<Patient> obtenirPatients() {
    List<Patient> resultat = patientDao.obtenir();
		
		return resultat;
	}

	/*@Override
	public Patient obtenirUnPatient(String nom, String prenom){
    
		Patient resultat = patientDao.obtenirUnPatient(nom,prenom);
		
		return resultat;
	}
*/
	
	@Override
	public Medecin obtenirMedecinDunPatient(long idPat){
    
		Medecin resultat = patientDao.obtenirMedecinDunPatient(idPat);
		
		return resultat;
	}

	

	
	
	/*
	@Override
	public void modifierPatient(Patient patient) {
		// TODO Auto-generated method stub
		patientDao.modifier(patient);
		
	}
*/
	
	/*@Override
	public void supprimerPatient(String nomPatient) {
		// TODO Auto-generated method stub
		patientDao.supprimer(nomPatient);
		
	}*/
/*
	@Override
	public List<Patient> obtenirUnPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	*/
	
}
