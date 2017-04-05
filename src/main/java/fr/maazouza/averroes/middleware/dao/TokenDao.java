package fr.maazouza.averroes.middleware.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import fr.maazouza.averroes.middleware.objetmetier.allergie.Allergie;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.token.Token;

@Stateless
// marqueur indiquant au CDI (context and dependencies injection) que c'est lui
// qui doit
// gérer ce composant partagé.
@Transactional
// indique qu'on utilise des transactions. Rends la base dans son etat initial
// si la transaction echoue

public class TokenDao {

	/**
	 * @author Maazouza
	 *
	 */

	@PersistenceContext
	private EntityManager em;

	// Ajouter un token
	public void persister(Token token) {

		em.persist(token);
	}
	
	public void supprimer(Long idToken) {

		em.remove(em.getReference(Token.class, idToken));
	}
	
	// Obtenir un token
		public List<Token> obtenirToken(String token) {

			//final String requeteJPQL = "SELECT b FROM Patient b WHERE b.nomPat = :filtre or b.prenomPat =:filtre";
			final String requeteJPQL = "SELECT b FROM Token b where b.token =  :filtre";
			
			final TypedQuery<Token> requeteType = em.createQuery(requeteJPQL, Token.class)
				.setParameter("filtre", token);
			
			
			return requeteType.getResultList();
			
			
		}
		
		// Obtenir un token par le nom
		public List<Token> obtenirTokenParUtilisateur(String nom) {

			//final String requeteJPQL = "SELECT b FROM Patient b WHERE b.nomPat = :filtre or b.prenomPat =:filtre";
			final String requeteJPQL = "SELECT b FROM Token b where b.utilisateur =  :filtre";
			
			final TypedQuery<Token> requeteType = em.createQuery(requeteJPQL, Token.class)
				.setParameter("filtre", nom);
			
			
			return requeteType.getResultList();
			
			
		}

}
