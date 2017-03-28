package fr.maazouza.averroes.middleware.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import fr.maazouza.averroes.middleware.objetmetier.antecedent.Antecedent;


/**
 * @author Maazouza
 *
 */

@Stateless
//marqueur indiquant au CDI (context and dependencies injection) que c'est lui qui doit 
//gérer ce composant partagé.
@Transactional
//indique qu'on utilise des transactions. Rends la base dans son etat initial si la transaction echoue


public class AntecedentDao {
	
	@PersistenceContext
	private EntityManager em;
	
// Ajouter une maladie
	public void persister(Antecedent antecedent) {
		
		
		em.persist(antecedent);
	}

// Consulter une antecedent par son Id
	public Antecedent obtenirAntecedent(Long idAll) {
			
		return em.find(Antecedent.class,idAll);
		
	}

	public void supprimerAntecedent(Long idAll) {
		em.remove(em.getReference(Antecedent.class, idAll));
		
	}

	public void modifierAntecedent(Antecedent antecedent) {
		em.merge(antecedent);
		
	}

}
