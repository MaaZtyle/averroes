package fr.maazouza.averroes.middleware.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;

/**
 * Classe de gestion de medecins
 * @author Maazouza
 *
 */

@Stateless
// marqueur indiquant au CDI (context and dependencies injection) que c'est lui qui doit 
// gérer ce composant partagé.
@Transactional
//indique qu'on utilise des transactions. Rends la base dans son etat initial si la transaction echoue

public class MedecinDao {
	
	
	    // Injecte un manageur d'entité construit
		// depuis le paramétrage de l'unité de persistence par défaut.
		@PersistenceContext
		private EntityManager em;
		
		public boolean ping() {
			
			final String requeteSQL = "SELECT 1";
			
			final Query requete = em.createNamedQuery(requeteSQL);
			
			return requete.getSingleResult() != null;
		}
		

		
		public void persister(Medecin medecin) {
			
			em.persist(medecin);
		}
		
		
		public Medecin obtenir(Integer id) {
			
			// Je récupère juste la référence de mon entité.
			//return em.find(Medecin.class, id);
			
			// J'écris ma requête.
			final String requeteSQL = "SELECT * FROM t_medecin where med_id=1"; 
			
			// Je crée la représentation java de ma requête
			final Query requete = em.createNativeQuery(requeteSQL);
			
			/*
			 * J'exécute ma requête
			 * Elle est envoyée à la BdD
			 * La BdD répond
			 * Le résultat m'est fourni sous la forme d'une liste de "truc"
			 * Je cast la liste de "truc" en une liste de bouteilles.
			 */
			//return (List<Medecin>) requete.getResultList();
			
			return em.find(Medecin.class, id);
		}
		
		public void desactiver(Integer id) {
			
			em.remove(em.getReference(Medecin.class, id));
		}
		
		public void modifier(Medecin medecin) {
			
			em.merge(medecin);
		}
		
}
