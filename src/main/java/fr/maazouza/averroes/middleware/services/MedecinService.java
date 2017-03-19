package fr.maazouza.averroes.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import fr.maazouza.averroes.middleware.dao.MedecinDao;
import fr.maazouza.averroes.middleware.dao.PatientDao;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinAvecPatientsException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;

/**
 * 
 * 
 * @author Maazouza
 *
 *service de gestion de medecins
 */
@Stateless
public class MedecinService implements IMedecinService {
	
	@EJB
	private MedecinDao medecinDao;
	
	@EJB
	private PatientDao patientDao;
	
	//@EJB
	//private IMedecinService medecinService;
	
	@EJB
	private IPatientService patientService;
	
	
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////MEDECIN/////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean existerParNomEtPrenom(Medecin medecin) {
		
		//je cherche tous les medecins avec ce nom
		List<Medecin> listeMedecin = medecinDao.obtenir();
		
		// ensuite je vérifie si le prénom est aussi le même
		
		Medecin result = listeMedecin.stream()
			     .filter(item -> medecin.getPrenomMed().equals(item.getPrenomMed()) &&
			    		 medecin.getNomMed().equals(item.getNomMed()))
			     .findAny()
			     .orElse(null);
		
		 
		
		//si le nom et le prenom sont les memes, je retourne true
		if( result == null)
			return false;
			else return true;
					
			
			     	
	}
// Rechecher un medecin par ID
	@Override
	public boolean existerParId(Long idMed) {
		
		//je cherche tous les medecins avec ce nom
		List<Medecin> listeMedecin = medecinDao.obtenir();
		
		// ensuite je vérifie si le prénom est aussi le même
		
		Medecin result = listeMedecin.stream()
			     .filter(item -> idMed.equals(item.getIdMed())) 
			     .findAny()
			     .orElse(null);
		
		 
		
		//si le nom et le prenom sont les memes, je retourne true
		if( result == null)
			return false;
			else return true;
					
			
			     	
	}
	
	
	
	// Obtenir la liste complète des medecins, le filtre se fera dans l'interface angular
	@Override
	public List<Medecin> obtenirMedecins() {
    List<Medecin> resultat = medecinDao.obtenir();
		
		return resultat;
	}
	
	
	// Ajouter un medecin
	@Override
	public void ajouterMedecin(Medecin medecin) throws MedecinDejaExistantException{
		
		if((existerParNomEtPrenom(medecin)==true ))
			throw new MedecinDejaExistantException("Le Medecin "+ medecin.getNomMed()+ "  " +medecin.getPrenomMed()+" existe déjà");
		else medecinDao.persister(medecin);
						
	}

	
	// Supprimer un medecin
		@Override
		public void supprimerMedecin(Long idMed) throws MedecinInexistantException,MedecinAvecPatientsException{
			
			//test existance du medecin
			if(existerParId(idMed)==false )
				{
				throw new MedecinInexistantException("Vous essayez de supprimer le Medecin "+ idMed + " qui n'existe pas  " );
								
				}
				// si n'a pas de patients
				if(obtenirPatientsDunMedecin(idMed) != null )
					throw new MedecinAvecPatientsException("Vous essayez de supprimer le Medecin "+ idMed + " qui possède des patients" );
					
					else medecinDao.supprimer(idMed);
							
		}
		
		
	// Obtenir un medecin par Id
	@Override
	public Medecin obtenirUnMedecin(Long idMed){ 
    
		//test existance du medecin
		if(existerParId(idMed)==false )
			{
			return null;
							
			}
		Medecin resultat = medecinDao.obtenirUnMedecin(idMed);
		
		return resultat;
	}
	
	// Obtenir la liste des patients de mon medecin
	@Override
	public List<Patient> obtenirPatientsDunMedecin(Long idMed) {
		// Récuperer le patient d'un medecin pour le modifier
			
				
					List<Patient> resultat = medecinDao.obtenirPatientsDunMedecin(idMed);
							
				return resultat;
	}
	
	/*@Override
	public List<Medecin> obtenirMedecin(String predicat) {
		List<Medecin> resultat = medecinDao.obtenir(predicat);
		
		
		
		return resultat;
	}*/


	//obtenir medecin d'un patient
/*	@Override
	public Medecin obtenirMedecinDunPatient(long idPat){
    
		Medecin resultat = patientDao.obtenirMedecinDunPatient(idPat);
		
		return resultat;
	}*/
	
	//Modifier les informations d'un medecin
	@Override
	public void modifierMedecin(Medecin medecin) {
		medecinDao.modifier(medecin);
		
	}



	/*@Override
	public List<Medecin> obtenirMedecin(String nom) {
		// TODO Auto-generated method stub
		return null;
	}*/
	

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////PATIENT/////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
	
// Récuperer le patient d'un medecin pour le modifier
		@Override
		public Patient obtenirUnPatient(Long idPat){
	    
			Patient resultat = patientDao.obtenirUnPatient(idPat);
			
			return resultat;
		}
		

/*// Récuperer le patient d'un medecin pour le modifier
		@Override
		public List<Patient> obtenirPatientsDunMedecin(Long idMed){
			    
			List<Patient> resultat = patientDao.obtenirPatientsDunMedecin(idMed);
					
		return resultat;
		}		

		*/
		
// Ajouter un patient au medecin
	@Override
	public void ajouterPatient(Patient patient) throws PatientDejaExistantException {
				
		if((patientService.existerParNomEtPrenom(patient)==true ))
			throw new PatientDejaExistantException("Le Patient"+ patient.getNomPat()+ " existe déjà");
		else patientDao.persister(patient);
		
	}
	
// Supprimer un patient du medecin
	@Override
	public void supprimerPatient(Long idPat) {
		// TODO Auto-generated method stub
		patientDao.supprimer(idPat);
		
	}

// Modifier un patient du medecin
	@Override
	public void modifierPatient(Patient patient) {
		// TODO Auto-generated method stub
		patientDao.modifier(patient);
		
	}
	@Override
	public List<Patient> obtenirPatients() {
		
	    List<Patient> resultat = patientDao.obtenir();
			
			return resultat;
		
	}
}
