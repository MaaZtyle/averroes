package fr.maazouza.averroes.middleware.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import fr.maazouza.averroes.middleware.objetmetier.ordonnance.Ordonnance;


/**
 * @author Maazouza
 *
 */

@Stateless
//marqueur indiquant au CDI (context and dependencies injection) que c'est lui qui doit 
//gérer ce composant partagé.
@Transactional
//indique qu'on utilise des transactions. Rends la base dans son etat initial si la transaction echoue


public class OrdonnanceDao {
	
	@PersistenceContext
	private EntityManager em;
	
// Ajouter une maladie
	public void persister(Ordonnance ordonnance) {
		
		
		em.persist(ordonnance);
	}

// Consulter une ordonnance par son Id
	public Ordonnance obtenirOrdonnance(Long idAll) {
			
		return em.find(Ordonnance.class,idAll);
		
	}

	public void supprimerOrdonnance(Long idAll) {
		em.remove(em.getReference(Ordonnance.class, idAll));
		
	}

	public void modifierOrdonnance(Ordonnance ordonnance) {
		em.merge(ordonnance);
		
	}

}
