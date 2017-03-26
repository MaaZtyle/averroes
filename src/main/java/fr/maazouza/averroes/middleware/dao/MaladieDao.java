package fr.maazouza.averroes.middleware.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;

/**
 * @author Maazouza
 *
 */

@Stateless
//marqueur indiquant au CDI (context and dependencies injection) que c'est lui qui doit 
//gérer ce composant partagé.
@Transactional
//indique qu'on utilise des transactions. Rends la base dans son etat initial si la transaction echoue


public class MaladieDao {
	
	@PersistenceContext
	private EntityManager em;
	
// Ajouter une maladie
	public void persister(Maladie maladie) {
		
		
		em.persist(maladie);
	}

// Consulter une maladie par son Id
	public Maladie obtenirMaladie(Long idMal) {
			
		return em.find(Maladie.class,idMal);
		
	}

	public void supprimerMaladie(Long idMal) {
		em.remove(em.getReference(Maladie.class, idMal));
		
	}

	public void modifierMaladie(Maladie maladie) {
		em.merge(maladie);
		
	}

}
