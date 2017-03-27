package fr.maazouza.averroes.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.MaladieDao;
import fr.maazouza.averroes.middleware.dao.MedecinDao;
import fr.maazouza.averroes.middleware.dao.OrdonnanceDao;
import fr.maazouza.averroes.middleware.dao.PatientDao;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
import fr.maazouza.averroes.middleware.objetmetier.maladie.MaladieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinAvecPatientsException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.Ordonnance;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.OrdonnanceInexistanteException;
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
public class OrdonnanceService implements IOrdonnanceService {

	@EJB
	private OrdonnanceDao ordonnanceDao;

	// @EJB
	// private IMedecinService medecinService;

	@EJB
	private IPatientService patientService;

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
