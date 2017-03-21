package fr.maazouza.averroes.middleware.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;


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
//Consulter le dossier d'un patient, en lui passant l'id du patient

	public DossierMedical consulter(Long idPat) {
		
		final String requeteJPQL = "SELECT b FROM DossierMedical b WHERE b.patient.idPat = :filtre1";
		
		
		final TypedQuery<DossierMedical> requeteType = em.createQuery(requeteJPQL, DossierMedical.class)
			.setParameter("filtre1", idPat);
				
		
		
		//retourne le premier element de la liste, sinon null
		List<DossierMedical> elementList = requeteType.getResultList();
		return elementList.isEmpty( ) ? null : elementList.get(0);
		
	}

	//liste des dossiers
		public List<DossierMedical> obtenir() {
			final String requeteJPQL = "SELECT b FROM DossierMedical b";
			
			
			final TypedQuery<DossierMedical> requeteType = em.createQuery(requeteJPQL, DossierMedical.class);
				
			
			return requeteType.getResultList();
		}

}
