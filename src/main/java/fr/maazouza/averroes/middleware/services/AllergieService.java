package fr.maazouza.averroes.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.AllergieDao;
import fr.maazouza.averroes.middleware.dao.MaladieDao;
import fr.maazouza.averroes.middleware.dao.MedecinDao;
import fr.maazouza.averroes.middleware.dao.PatientDao;
import fr.maazouza.averroes.middleware.objetmetier.allergie.Allergie;
import fr.maazouza.averroes.middleware.objetmetier.allergie.AllergieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
import fr.maazouza.averroes.middleware.objetmetier.maladie.MaladieInexistanteException;
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
 *         service de gestion de medecins
 */
@Stateless
public class AllergieService implements IAllergieService {

	
	@EJB
	private AllergieDao allergieDao;


	// @EJB
	// private IMedecinService medecinService;

	@EJB
	private IPatientService patientService;

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
}
