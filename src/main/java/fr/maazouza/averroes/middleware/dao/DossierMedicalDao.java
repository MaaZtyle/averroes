package fr.maazouza.averroes.middleware.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;


@Stateless
//marqueur indiquant au CDI (context and dependencies injection) que c'est lui qui doit 
//gérer ce composant partagé.
@Transactional
//indique qu'on utilise des transactions. Rends la base dans son etat initial si la transaction echoue

public class DossierMedicalDao {
	
	// Injecte un manageur d'entité construit
	// depuis le paramétrage de l'unité de persistence par défaut.
	@PersistenceContext
	private EntityManager em;
			

	public void persister(DossierMedical dossier) {
		
		em.persist(dossier);		
		
	}

}
