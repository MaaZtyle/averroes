package fr.maazouza.averroes.middleware.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.VaccinDao;
import fr.maazouza.averroes.middleware.objetmetier.vaccin.Vaccin;
import fr.maazouza.averroes.middleware.objetmetier.vaccin.VaccinArchive;
import fr.maazouza.averroes.middleware.objetmetier.vaccin.VaccinInexistantException;

/**
 * 
 * 
 * @author Maazouza
 *
 *         service de gestion de medecins
 */
@Stateless
public class VaccinService implements IVaccinService {

	
	@EJB
	private VaccinDao vaccinDao;


	// @EJB
	// private IMedecinService medecinService;

	@EJB
	private IPatientService patientService;

	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// Vaccin////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////

	// Ajouter un vaccin à un Dossier medical d'un patient
	@Override
	public void ajouterVaccin(Vaccin vaccin) {

		vaccinDao.persister(vaccin);

	}
	
	// Ajouter un vaccin à dans l'archive
		@Override
		public void ajouterVaccinArchive(VaccinArchive vaccin) {

			vaccinDao.persisterArchive(vaccin);

		}


	// Consulter un vaccin à un Dossier medical d'un patient par Id
	@Override
	public Vaccin obtenirVaccin(Long idVac) throws VaccinInexistantException {

		// si je trouve le vaccin, je le modifie, sinon exception
		if (vaccinDao.obtenirVaccin(idVac) == null)
			throw new VaccinInexistantException("Le vaccin" + idVac + " n'existe pas");
		else
			return vaccinDao.obtenirVaccin(idVac);

	}

	// Supprimer une maladie à un Dossier medical d'un patient par Id
	@Override
	public void supprimerVaccin(Long idVac) throws VaccinInexistantException {
		// si je trouve pas la maladie, je la supprime pas, et je leve une
		// exception
		if (vaccinDao.obtenirVaccin(idVac) == null)
			throw new VaccinInexistantException("L'vaccin n'existe pas");
		else
			vaccinDao.supprimerVaccin(idVac);
		;

	}

	@Override
	public void modifierVaccin(Vaccin vaccin) throws VaccinInexistantException {

		if (vaccinDao.obtenirVaccin(vaccin.getIdVac()) == null)
			throw new VaccinInexistantException("Le vaccin n'existe pas");
		else
			vaccinDao.modifierVaccin(vaccin);

	}
}
