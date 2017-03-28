package fr.maazouza.averroes.middleware.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.AntecedentDao;
import fr.maazouza.averroes.middleware.objetmetier.antecedent.Antecedent;
import fr.maazouza.averroes.middleware.objetmetier.antecedent.AntecedentInexistantException;

/**
 * 
 * 
 * @author Maazouza
 *
 *         service de gestion de medecins
 */
@Stateless
public class AntecedentService implements IAntecedentService {

	@EJB
	private AntecedentDao antecedentDao;

	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// Antecedent/////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////

	// Ajouter une maladie à un Dossier medical d'un patient
	@Override
	public void ajouterAntecedent(Antecedent antecedent) {

		antecedentDao.persister(antecedent);

	}

	// Consulter une antecedent à un Dossier medical d'un patient par Id
	@Override
	public Antecedent obtenirAntecedent(Long idAnt) throws AntecedentInexistantException {

		// si je trouve la maladie, je le modifie, sinon exception
		if (antecedentDao.obtenirAntecedent(idAnt) == null)
			throw new AntecedentInexistantException("L'antecedent" + idAnt + " n'existe pas");
		else
			return antecedentDao.obtenirAntecedent(idAnt);

	}

	// Supprimer un antecedent à un Dossier medical d'un patient par Id
	@Override
	public void supprimerAntecedent(Long idAnt) throws AntecedentInexistantException {
		// si je trouve pas la maladie, je la supprime pas, et je leve une
		// exception
		if (antecedentDao.obtenirAntecedent(idAnt) == null)
			throw new AntecedentInexistantException("L'antecedent n'existe pas");
		else
			antecedentDao.supprimerAntecedent(idAnt);
		;

	}

	@Override
	public void modifierAntecedent(Antecedent antecedent) throws AntecedentInexistantException {

		if (antecedentDao.obtenirAntecedent(antecedent.getIdAnt()) == null)
			throw new AntecedentInexistantException("L'antecedent n'existe pas");
		else
			antecedentDao.modifierAntecedent(antecedent);

	}
}
