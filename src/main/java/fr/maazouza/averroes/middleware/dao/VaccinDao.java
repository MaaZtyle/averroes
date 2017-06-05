package fr.maazouza.averroes.middleware.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import fr.maazouza.averroes.middleware.objetmetier.allergie.Allergie;
import fr.maazouza.averroes.middleware.objetmetier.antecedent.Antecedent;
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

	
	// Archiver un Vaccin 
		public void archiverVaccin(Vaccin vaccin) {
			
			em.merge(vaccin);
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
	
	//Afficher la liste des vaccins
			public List<Vaccin> obtenirVaccins(Long idDos) {
				final String requeteJPQL = "SELECT b FROM Vaccin b where dossierMedical.idDos = :filtre and b.dateArchivageVac is NULL" ;
				
				
				final TypedQuery<Vaccin> requeteType = em.createQuery(requeteJPQL, Vaccin.class)
						.setParameter("filtre", idDos);
					
				
				return requeteType.getResultList();
			}
	
	//Afficher la liste des vaccins Archivés
			public List<Vaccin> obtenirVaccinsArchives(Long idDos) {
				final String requeteJPQL = "SELECT b FROM Vaccin b where dossierMedical.idDos = :filtre and b.dateArchivageVac is NOT NULL" ;
				
				
				final TypedQuery<Vaccin> requeteType = em.createQuery(requeteJPQL, Vaccin.class)
						.setParameter("filtre", idDos);
					
				
				return requeteType.getResultList();
			}
			

}
