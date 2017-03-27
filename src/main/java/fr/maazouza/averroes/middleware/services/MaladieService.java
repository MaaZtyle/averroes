package fr.maazouza.averroes.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.MaladieDao;
import fr.maazouza.averroes.middleware.dao.MedecinDao;
import fr.maazouza.averroes.middleware.dao.PatientDao;
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
public class MaladieService implements IMaladieService {

	
	@EJB
	private MaladieDao maladieDao;

	// @EJB
	// private IMedecinService medecinService;

	@EJB
	private IPatientService patientService;

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
}
