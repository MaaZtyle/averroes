package fr.maazouza.averroes.middleware.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import fr.maazouza.averroes.middleware.objetmetier.medicament.Medicament;


/**
 * @author Maazouza
 *
 */

@Stateless
//marqueur indiquant au CDI (context and dependencies injection) que c'est lui qui doit 
//gérer ce composant partagé.
@Transactional
//indique qu'on utilise des transactions. Rends la base dans son etat initial si la transaction echoue


public class MedicamentDao {
	
	@PersistenceContext
	private EntityManager em;
	
// Ajouter une maladie
	public void persister(Medicament medicament) {
		
		
		em.persist(medicament);
	}

// Consulter une medicament par son Id
	public Medicament obtenirMedicament(String codeCis) {
			
		return em.find(Medicament.class,codeCis);
		
	}

	public void supprimerMedicament(String codeCis) {
		em.remove(em.getReference(Medicament.class, codeCis));
		
	}

	public void modifierMedicament(Medicament medicament) {
		em.merge(medicament);
		
	}

}
