package fr.maazouza.averroes.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.AllergieDao;
import fr.maazouza.averroes.middleware.dao.AntecedentDao;
import fr.maazouza.averroes.middleware.dao.DossierMedicalDao;
import fr.maazouza.averroes.middleware.dao.MaladieDao;
import fr.maazouza.averroes.middleware.dao.OrdonnanceDao;
import fr.maazouza.averroes.middleware.dao.PatientDao;
import fr.maazouza.averroes.middleware.dao.VaccinDao;
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
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.Ordonnance;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.OrdonnanceInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.vaccin.Vaccin;

/**
 * 
 * 
 * @author Maazouza
 *
 *         service de gestion de dossiers medicaux
 */
@Stateless
public class DossierMedicalService implements IDossierMedicalService {

	@EJB
	private DossierMedicalDao dossierMedicalDao;

	@EJB
	private PatientDao patientDao;

	@EJB
	private MaladieDao maladieDao;

	@EJB
	private AllergieDao allergieDao;
	
	@EJB
	private VaccinDao vaccinDao;
	
	@EJB
	private AntecedentDao antecedentDao;

	@EJB
	private OrdonnanceDao ordonnanceDao;

	// @EJB
	// private IMedecinService medecinService;

	@EJB
	private IPatientService patientService;

	@EJB
	private IMaladieService maladieService;

	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////// MEDICAL/////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////

	// Rechecher un dossier medical par idPat
	@Override
	public boolean existerParId(Long idPat) {

		// je cherche tous les dossiers
		List<DossierMedical> listeDossierMedical = dossierMedicalDao.obtenir();

		// ensuite je vérifie si l'id existe

		DossierMedical result = listeDossierMedical.stream().filter(item -> idPat.equals(item.getPatient().getIdPat()))
				.findFirst().orElse(null);

		// si on le trouve
		if (result == null)
			return false;
		else
			return true;

	}

	// Rechecher un dossier medical du patient
	@Override
	public boolean exister(Patient patient) {

		// je cherche tous les dossier medicaux
		List<DossierMedical> listeDossierMedical = dossierMedicalDao.obtenir();

		// je crée une arible pour stocke l'id
		Long idPat = patient.getIdPat();

		// ensuite je vérifie si le prénom est aussi le même

		DossierMedical result = listeDossierMedical.stream().filter(item -> idPat.equals(item.getPatient().getIdPat()))
				.findFirst().orElse(null);

		// si le nom et le prenom sont les memes, je retourne true
		if (result == null)
			return false;
		else
			return true;

	}

	// Rechecher un dossier medical par idDos
	@Override
	public boolean existerParIdDos(Long idDos) {

		// je cherche tous les dossiers
		List<DossierMedical> listeDossierMedical = dossierMedicalDao.obtenir();

		// ensuite je vérifie si le prénom est aussi le même

		DossierMedical result = listeDossierMedical.stream().filter(item -> idDos.equals(item.getIdDos())).findFirst()
				.orElse(null);

		// si le nom et le prenom sont les memes, je retourne true
		if (result == null)
			return false;
		else
			return true;

	}

	// Ajouter un Dossier Medical
	@Override
	public void ajouterDossierMedical(DossierMedical dossier) throws DossierMedicalDejaExistantException

	{

		// si le patient a déjà un dossier, je lève une exception
		if (exister(dossier.getPatient()) == true)
			throw new DossierMedicalDejaExistantException(
					"Le Patient" + dossier.getPatient().getNomPat() + " a déjà un dossier");
		// enfin je l'ajoute

		// si le patient n'existe pas
		// if(patientService.obtenirUnPatient(dossier.getPatient().getIdPat())==null)
		// throw new PatientInexistantException("Le Patient n'existe pas dans la
		// base");
		dossierMedicalDao.persister(dossier);

	}

	// Retourner un Dossier Medical d'un seul patient
	@Override
	public DossierMedical consulterUnDossierMedical(Long idPat)

	{

		return dossierMedicalDao.consulter(idPat);

	}

	// Retourner tous les dossiers medicaux
	@Override
	public List<DossierMedical> obtenir() {
		return dossierMedicalDao.obtenir();
	}

	// Modifier un dossier medical
	@Override
	public void modifierDossierMedical(DossierMedical dossierMedical) {
		dossierMedicalDao.modifier(dossierMedical);

	}

	// Obtenir un dossier par id
	@Override
	public DossierMedical obtenirUnDossierMedical(Long idDos) throws DossierMedicalInexistantException {

		// si je trouve le dossier, je le modifie, sinon exception
		if (existerParIdDos(idDos) == false)
			throw new DossierMedicalInexistantException("Le dossier" + idDos + " n'existe pas");
		else
			return dossierMedicalDao.obtenirUnDossierMedical(idDos);

	}
	// Supprimer un dossier medical

	@Override
	public void supprimerUnDossierMedical(Long idDos) throws DossierMedicalAvecMaladieException,
			DossierMedicalAvecAllergieException, DossierMedicalAvecOrdonnanceException, DossierMedicalAvecVaccinException {

		// s'il y a des allergies, je lève une exception
		if (dossierMedicalDao.obtenirUnDossierMedical(idDos).getAllergie().isEmpty() == false)
			throw new DossierMedicalAvecAllergieException("Le dossier" + idDos + " possède des allergies");

		// s'il y a des maladies, je lève une exception
		if (dossierMedicalDao.obtenirUnDossierMedical(idDos).getMaladie().isEmpty() == false)
			throw new DossierMedicalAvecMaladieException("Le dossier" + idDos + " possède des maladies");

		// s'il y a des ordonnances, je lève une exception
		if (dossierMedicalDao.obtenirUnDossierMedical(idDos).getOrdonnance().isEmpty() == false)
			throw new DossierMedicalAvecOrdonnanceException("Le dossier" + idDos + " possède des ordonnances");

		// s'il y a des antecédents, je lève une exception
		if (dossierMedicalDao.obtenirUnDossierMedical(idDos).getAntecedent().isEmpty() == false)
			throw new DossierMedicalAvecOrdonnanceException("Le dossier" + idDos + " possède des antecedents");
		
		// s'il y a des vaccins, je lève une exception
		if (dossierMedicalDao.obtenirUnDossierMedical(idDos).getVaccin().isEmpty() == false)
			throw new DossierMedicalAvecVaccinException("Le dossier" + idDos + " possède des vaccin");

		else
			// si on a rien je supprime
			dossierMedicalDao.supprimerUnDossierMedical(idDos);

	}
	
	// obtenir la liste des maladies
	
	public List<Maladie> obtenirMaladies(Long idDos){
		
		return maladieDao.obtenirMaladies(idDos);
	
	}

	//obtenir la listes des allergies
	
public List<Allergie> obtenirAllergies(Long idDos){
		
		return allergieDao.obtenirAllergies(idDos);
	
	}

//obtenir la listes des antecedents

public List<Antecedent> obtenirAntecedents(Long idDos){
		
		return antecedentDao.obtenirAntecedents(idDos);
	
	}


//obtenir la listes des ordonnances

public List<Ordonnance> obtenirOrdonnances(Long idDos){
		
		return ordonnanceDao.obtenirOrdonnances(idDos);
	
	}

//obtenir la listes des vaccins

public List<Vaccin> obtenirVaccins(Long idDos){
		
		return vaccinDao.obtenirVaccins(idDos);
	
	}

//obtenir la listes des vaccins Archivés

public List<Vaccin> obtenirVaccinsArchives(Long idDos){
		
		return vaccinDao.obtenirVaccinsArchives(idDos);
	
	}

}
