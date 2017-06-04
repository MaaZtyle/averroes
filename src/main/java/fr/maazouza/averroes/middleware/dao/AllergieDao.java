package fr.maazouza.averroes.middleware.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import fr.maazouza.averroes.middleware.objetmetier.allergie.Allergie;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;


/**
 * @author Maazouza
 *
 */

@Stateless
//marqueur indiquant au CDI (context and dependencies injection) que c'est lui qui doit 
//gérer ce composant partagé.
@Transactional
//indique qu'on utilise des transactions. Rends la base dans son etat initial si la transaction echoue


public class AllergieDao {
	
	@PersistenceContext
	private EntityManager em;
	
// Ajouter une maladie
	public void persister(Allergie allergie) {
		
		
		em.persist(allergie);
	}

// Consulter une allergie par son Id
	public Allergie obtenirAllergie(Long idAll) {
			
		return em.find(Allergie.class,idAll);
		
	}

	public void supprimerAllergie(Long idAll) {
		em.remove(em.getReference(Allergie.class, idAll));
		
	}

	public void modifierAllergie(Allergie allergie) {
		em.merge(allergie);
		
	}
	
	//Afficher la liste des allergies
	public List<Allergie> obtenirAllergies(Long idDos) {
		final String requeteJPQL = "SELECT b FROM Allergie b where dossierMedical.idDos = :filtre";
		
		
		final TypedQuery<Allergie> requeteType = em.createQuery(requeteJPQL, Allergie.class)
				.setParameter("filtre", idDos);
			
		
		return requeteType.getResultList();
	}

}
