package fr.maazouza.averroes.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.AllergieDao;
import fr.maazouza.averroes.middleware.dao.DossierMedicalDao;
import fr.maazouza.averroes.middleware.dao.MaladieDao;
import fr.maazouza.averroes.middleware.dao.OrdonnanceDao;
import fr.maazouza.averroes.middleware.dao.PatientDao;
import fr.maazouza.averroes.middleware.objetmetier.allergie.Allergie;
import fr.maazouza.averroes.middleware.objetmetier.allergie.AllergieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
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
	private OrdonnanceDao ordonnanceDao;

	// @EJB
	// private IMedecinService medecinService;

	@EJB
	private IPatientService patientService;

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

	// Returner un Dossier Medical d'un seul patient
	@Override
	public DossierMedical consulterUnDossierMedical(Long IpPat)

	{

		return dossierMedicalDao.consulter(IpPat);

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
	// Supprimer un dossier

	@Override
	public void supprimerUnDossierMedical(Long idDos) {
		dossierMedicalDao.supprimerUnDossierMedical(idDos);

	}
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// MALADIES////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////

	// Ajouter une maladie à un Dossier medical d'un patient
	@Override
	public void ajouterMaladie(Maladie maladie) {

		maladieDao.persister(maladie);

	}

	// Consulter une maladie à un Dossier medical d'un patient par Id
	@Override
	public Maladie obtenirMaladie(Long idMal) throws MaladieInexistanteException {

		// si je trouve la maladie, je le modifie, sinon exception
		if (maladieDao.obtenirMaladie(idMal) == null)
			throw new MaladieInexistanteException("La maladie" + idMal + " n'existe pas");
		else
			return maladieDao.obtenirMaladie(idMal);

	}

	// Supprimer une maladie à un Dossier medical d'un patient par Id
	@Override
	public void supprimerMaladie(Long idMal) throws MaladieInexistanteException {
		// si je trouve pas la maladie, je la supprime pas, et je leve une
		// exception
		if (maladieDao.obtenirMaladie(idMal) == null)
			throw new MaladieInexistanteException("La maladie n'existe pas");
		else
			maladieDao.supprimerMaladie(idMal);
		;

	}

	@Override
	public void modifierMaladie(Maladie maladie) throws MaladieInexistanteException {

		if (maladieDao.obtenirMaladie(maladie.getIdMal()) == null)
			throw new MaladieInexistanteException("La maladie n'existe pas");
		else
			maladieDao.modifierMaladie(maladie);

	}

	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// Allergie////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////

	// Ajouter une maladie à un Dossier medical d'un patient
	@Override
	public void ajouterAllergie(Allergie allergie) {

		allergieDao.persister(allergie);

	}

	// Consulter une allergie à un Dossier medical d'un patient par Id
	@Override
	public Allergie obtenirAllergie(Long idAll) throws AllergieInexistanteException {

		// si je trouve la maladie, je le modifie, sinon exception
		if (allergieDao.obtenirAllergie(idAll) == null)
			throw new AllergieInexistanteException("L'allergie" + idAll + " n'existe pas");
		else
			return allergieDao.obtenirAllergie(idAll);

	}

	// Supprimer une maladie à un Dossier medical d'un patient par Id
	@Override
	public void supprimerAllergie(Long idAll) throws AllergieInexistanteException {
		// si je trouve pas la maladie, je la supprime pas, et je leve une
		// exception
		if (allergieDao.obtenirAllergie(idAll) == null)
			throw new AllergieInexistanteException("L'allergie n'existe pas");
		else
			allergieDao.supprimerAllergie(idAll);
		;

	}

	@Override
	public void modifierAllergie(Allergie allergie) throws AllergieInexistanteException {

		if (allergieDao.obtenirAllergie(allergie.getIdAll()) == null)
			throw new AllergieInexistanteException("L'allergie n'existe pas");
		else
			allergieDao.modifierAllergie(allergie);

	}

	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// Ordonnance/////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////

	// Ajouter une ordonnance à un Dossier medical d'un patient
	@Override
	public void ajouterOrdonnance(Ordonnance ordonnance) {

		ordonnanceDao.persister(ordonnance);

	}

	// Consulter une ordonnance à un Dossier medical d'un patient par Id
	@Override
	public Ordonnance obtenirOrdonnance(Long idOrd) throws OrdonnanceInexistanteException {

		// si je trouve la maladie, je le modifie, sinon exception
		if (ordonnanceDao.obtenirOrdonnance(idOrd) == null)
			throw new OrdonnanceInexistanteException("L'ordonnance" + idOrd + " n'existe pas");
		else
			return ordonnanceDao.obtenirOrdonnance(idOrd);

	}

	// Supprimer une ordonnance à un Dossier medical d'un patient par Id
	@Override
	public void supprimerOrdonnance(Long idOrd) throws OrdonnanceInexistanteException {
		// si je trouve pas la maladie, je la supprime pas, et je leve une
		// exception
		if (ordonnanceDao.obtenirOrdonnance(idOrd) == null)
			throw new OrdonnanceInexistanteException("L'ordonnance n'existe pas");
		else
			ordonnanceDao.supprimerOrdonnance(idOrd);
		;

	}

	@Override
	public void modifierOrdonnance(Ordonnance ordonnance) throws OrdonnanceInexistanteException {

		if (ordonnanceDao.obtenirOrdonnance(ordonnance.getIdOrd()) == null)
			throw new OrdonnanceInexistanteException("L'ordonnance n'existe pas");
		else
			ordonnanceDao.modifierOrdonnance(ordonnance);

	}

}
