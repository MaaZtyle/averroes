package fr.maazouza.averroes.middleware.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.DossierMedicalDao;
import fr.maazouza.averroes.middleware.dao.PatientDao;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
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
	
	//@EJB
	//private IMedecinService medecinService;
	
	@EJB
	private IPatientService patientService;
	
	
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////DOSSIER MEDICAL/////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
	
	
	
// Ajouter un Dossier Medical
		@Override
		public void ajouterDossierMedical(DossierMedical dossier) 
		
		{
			dossierMedicalDao.persister(dossier);
							
		}


}
