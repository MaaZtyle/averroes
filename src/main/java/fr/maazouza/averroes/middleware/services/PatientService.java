/**
 * 
 */
package fr.maazouza.averroes.middleware.services;

import java.util.Collection;
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
		
		//je cherche tous les patients avec ce nom
		List<Patient> listePatient = patientDao.obtenir(patient.getNom_pat());
		
		// ensuite je vérifie si le prénom est aussi le même
		
		Patient result = listePatient.stream()
			     .filter(item -> item.getPrenom_pat().equals(patient.getPrenom_pat()))
			     .findFirst()
			     .orElse(null);
		
		 
		
		//si le nom et le prenom sont les meme, je retourne true
		if( result == null)
			return false;
			else return true;
					
			
			     	
	}

	@Override
	public void ajouterPatient(Patient patient) throws PatientDejaExistantException {
				
		if((existerParNomEtPrenom(patient)==true ))
			throw new PatientDejaExistantException("Le Patient"+ patient.getNom_pat()+ " existe déjà");
		else patientDao.persister(patient);
		
	}

	@Override
	public void ajouterPatients(Collection<Patient> patients) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Patient> obtenirPatients(String predicat) {
    List<Patient> resultat = patientDao.obtenir(predicat);
		
		return resultat;
	}

	@Override
	public Patient obtenirUnPatient(String nom, String prenom){
    
		Patient resultat = patientDao.obtenirUnPatient(nom,prenom);
		
		return resultat;
	}

	
	@Override
	public Medecin obtenirMedecinDunPatient(long id_pat){
    
		Medecin resultat = patientDao.obtenirMedecinDunPatient(id_pat);
		
		return resultat;
	}

	
	
	@Override
	public void modifierPatient(Patient patient) {
		// TODO Auto-generated method stub
		patientDao.modifier(patient);
		
	}

	
	@Override
	public void supprimerPatient(String nomPatient) {
		// TODO Auto-generated method stub
		patientDao.supprimer(nomPatient);
		
	}

	
	
}
