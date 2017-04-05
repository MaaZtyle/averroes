package fr.maazouza.averroes.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.maazouza.averroes.middleware.dao.AntecedentDao;
import fr.maazouza.averroes.middleware.dao.TokenDao;
import fr.maazouza.averroes.middleware.objetmetier.antecedent.Antecedent;
import fr.maazouza.averroes.middleware.objetmetier.antecedent.AntecedentInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.token.Token;

/**
 * 
 * 
 * @author Maazouza
 *
 *         service de gestion de medecins
 */
@Stateless
public class TokenService   {

	@EJB
	private TokenDao tokenDao;

	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// TOKEN/////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////

	// Ajouter une maladie à un Dossier medical d'un patient

	public void ajouterToken(Token token) {

		tokenDao.persister(token);

	}

	
	public void supprimerToken(Long idToken) {

		tokenDao.supprimer(idToken);

	}
	
	public List<Token> obtenirToken(String token) {

		 return tokenDao.obtenirToken(token);

	}
	
	public List<Token> obtenirTokenParUtilisateur(String utilisateur) {

		 return tokenDao.obtenirTokenParUtilisateur(utilisateur);

	}
	
}
