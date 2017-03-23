package fr.maazouza.averroes.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.DossierMedicalDao;
import fr.maazouza.averroes.middleware.dao.MaladieDao;
import fr.maazouza.averroes.middleware.dao.PatientDao;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;

/**
 * 
 * 
 * @author Maazouza
 *
 *service de gestion de dossiers medicaux
 */
@Stateless
public class DossierMedicalService implements IDossierMedicalService {
	
	
	@EJB
	private DossierMedicalDao dossierMedicalDao;
	
	@EJB
	private PatientDao patientDao;
	
	@EJB
	private MaladieDao maladieDao;
	

	//@EJB
	//private IMedecinService medecinService;
	
	@EJB
	private IPatientService patientService;
	
	
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////DOSSIER MEDICAL/////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
	

// Rechecher un dossier medical par idPat
		@Override
		public boolean existerParId(Long idPat) {
			
			//je cherche tous les medecins avec ce nom
			List<DossierMedical> listeDossierMedical= dossierMedicalDao.obtenir();
			
			// ensuite je vérifie si le prénom est aussi le même
			
			DossierMedical result = listeDossierMedical.stream()
				     .filter(item -> idPat.equals(item.getPatient().getIdPat())) 
				     .findFirst()
				     .orElse(null);
			
			 
			
			//si le nom et le prenom sont les memes, je retourne true
			if( result == null)
				return false;
				else return true;
						
				
				     	
		}
	
// Ajouter un Dossier Medical
		@Override
		public void ajouterDossierMedical(DossierMedical dossier) 
		
		{
			
			dossierMedicalDao.persister(dossier);
							
		}
		
// Ajouter une maladie à un Dossier medical d'un patient
		@Override
		public void ajouterMaladie(Maladie maladie) 				
		{
					
			maladieDao.persister(maladie);
									
		}		
		
// Returner un Dossier Medical d'un seul patient
				@Override
				public DossierMedical consulterUnDossierMedical(Long IpPat) 
				
				{
					
					return dossierMedicalDao.consulter(IpPat);
					 
									
				}
				
//Retourner tous les dossiers medicaux
				@Override
				public List<DossierMedical> obtenir() {
					return dossierMedicalDao.obtenir();
				}
				
//Modifier un dossier medical
				@Override
				public void modifierDossierMedical(DossierMedical dossierMedical) {
					dossierMedicalDao.modifier(dossierMedical);
					
				}
// Obtenir un dossier
				@Override
				public DossierMedical obtenirUnDossier(Long idDos) {
					// Obtenir un medecin par Id
					
					return dossierMedicalDao.obtenirUnDossier(idDos);
						
					}
// Supprimer un dossier
		
				@Override
				public void supprimerUnDossierMedical(Long idDos) {
					 dossierMedicalDao.supprimerUnDossierMedical(idDos);
					
				}
				
				


}
