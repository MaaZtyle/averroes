package fr.maazouza.averroes.middleware.services;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.xml.ws.WebServiceContext;

import fr.maazouza.averroes.middleware.dao.MedecinDao;
import fr.maazouza.averroes.middleware.dao.PatientDao;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinAvecPatientsException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.NomOuMotDePasseException;
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
	
	// Rcherche mail d'un medecin
		@Override
		public boolean existerPareMail(String eMail) {
			
			//je cherche tous les medecins avec ce nom
			List<Medecin> listeMedecin = medecinDao.obtenir();
			
			// ensuite je vérifie si le prénom est aussi le même
			
			Medecin result = listeMedecin.stream()
				     .filter(item -> eMail.equals(item.getEmailMed())) 
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
	
	
	
	
	// Obtenir un medecin par nom et mot de passe
		@Override
		public String authentifierUnMedecin(String nom, String motDePasse ) throws NomOuMotDePasseException{ 
	    
			//je cherche tous les medecins avec ce nom
			List<Medecin> listeMedecin = medecinDao.obtenir();
			
			// ensuite je vérifie si le nom et mdp est aussi le même
			
			
			Medecin result = listeMedecin.stream()
				     .filter(item -> item.getNomMed().equals(nom))
				     .filter(item -> item.getMdpMed().equals(motDePasse))
				     .findFirst()
				     .orElse(null);
			
			 
			
			//si le nom et le mdp sont trouvé, je retourne true
			if( result == null)
				return null;
				//throw new NomOuMotDePasseException("Nom ou mot de passe incorrecte");
						
				else return result.getEmailMed();
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
		

// Récuperer le patient d'un medecin par id
		@Override
		public List<Patient> obtenirPatientsDunMedecin(Long idMed){
			    
			List<Patient> resultat = medecinDao.obtenirPatientsDunMedecin(idMed);
					
		return resultat;
		}		

// Récuperer le patient d'un medecin par email
		@Override
		public List<Patient> obtenirPatientsDunMedecinParEmail(String eMail){
			
			
					    
			List<Patient> resultat = medecinDao.obtenirPatientsDunMedecinParEmailMed(eMail);
						
		return resultat;
		}		
		
		
		
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
