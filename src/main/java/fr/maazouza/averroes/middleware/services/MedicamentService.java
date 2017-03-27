package fr.maazouza.averroes.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.MedicamentDao;
import fr.maazouza.averroes.middleware.dao.MedecinDao;
import fr.maazouza.averroes.middleware.dao.MedicamentDao;
import fr.maazouza.averroes.middleware.dao.PatientDao;
import fr.maazouza.averroes.middleware.objetmetier.medicament.Medicament;
import fr.maazouza.averroes.middleware.objetmetier.medicament.MedicamentInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.medicament.MedicamentInexistantException;
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
public class MedicamentService implements IMedicamentService {

	
	@EJB
	private MedicamentDao medicamentDao;

	// @EJB
	// private IMedecinService medecinService;

	@EJB
	private IPatientService patientService;

	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// MEDICAMENT/////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////


	// Ajouter une medicament à un Dossier medical d'un patient
	@Override
	public void ajouterMedicament(Medicament medicament) {

		medicamentDao.persister(medicament);

	}

	// Consulter une medicament à un Dossier medical d'un patient par Id
	@Override
	public Medicament obtenirMedicament(String codeCis) throws MedicamentInexistantException {

		// si je trouve la medicament, je le modifie, sinon exception
		if (medicamentDao.obtenirMedicament(codeCis) == null)
			throw new MedicamentInexistantException("La medicament" + codeCis + " n'existe pas");
		else
			return medicamentDao.obtenirMedicament(codeCis);

	}

	// Supprimer une medicament à un Dossier medical d'un patient par Id
	@Override
	public void supprimerMedicament(String codeCis) throws MedicamentInexistantException {
		// si je trouve pas la medicament, je la supprime pas, et je leve une
		// exception
		if (medicamentDao.obtenirMedicament(codeCis) == null)
			throw new MedicamentInexistantException("La medicament n'existe pas");
		else
			medicamentDao.supprimerMedicament(codeCis);
		;

	}

	@Override
	public void modifierMedicament(Medicament medicament) throws MedicamentInexistantException {

		if (medicamentDao.obtenirMedicament(medicament.getCodeCIS()) == null)
			throw new MedicamentInexistantException("La medicament n'existe pas");
		else
			medicamentDao.modifierMedicament(medicament);

	}
}
