package fr.maazouza.averroes.middleware.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import fr.maazouza.averroes.middleware.objetmetier.allergie.Allergie;
import fr.maazouza.averroes.middleware.objetmetier.vaccin.Vaccin;


/**
 * @author Maazouza
 *
 */

@Stateless
//marqueur indiquant au CDI (context and dependencies injection) que c'est lui qui doit 
//gérer ce composant partagé.
@Transactional
//indique qu'on utilise des transactions. Rends la base dans son etat initial si la transaction echoue


public class VaccinDao {
	
	@PersistenceContext
	private EntityManager em;
	
// Ajouter un Vaccin
	public void persister(Vaccin vaccin) {
		
		
		em.persist(vaccin);
	}

// Consulter un Vaccin par son Id
	public Vaccin obtenirVaccin(Long idVac) {
			
		return em.find(Vaccin.class,idVac);
		
	}

	public void supprimerVaccin(Long idVac) {
		em.remove(em.getReference(Vaccin.class, idVac));
		
	}

	public void modifierVaccin(Vaccin vaccin) {
		em.merge(vaccin);
		
	}

}
