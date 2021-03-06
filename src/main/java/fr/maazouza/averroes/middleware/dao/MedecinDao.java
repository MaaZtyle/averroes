package fr.maazouza.averroes.middleware.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;

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
		
		
		//public List<Medecin> obtenir(String predicat) {
			
			// Je récupère juste la référence de mon entité.
			//return em.find(Medecin.class, id);
			
			// J'écris ma requête.
			//final String requeteSQL = "SELECT * FROM t_medecin where med_nom='"+nom+"'"; 
			
			// Je crée la représentation java de ma requête
			//final Query requete = em.createNativeQuery(requeteSQL);
			
			/*
			 * J'exécute ma requête
			 * Elle est envoyée à la BdD
			 * La BdD répond
			 * Le résultat m'est fourni sous la forme d'une liste de "truc"
			 * Je cast la liste de "truc" en une liste de bouteilles.
			 */
			//return (List<Medecin>) requete.getResultList();
			
			/*final String requeteJPQL = "SELECT b FROM Medecin b WHERE b.nomMed = :filtre or b.prenomMed =:filtre";
			
			final TypedQuery<Medecin> requeteType = em.createQuery(requeteJPQL, Medecin.class)
				.setParameter("filtre", predicat);
			
			return requeteType.getResultList();
			
			//return em.find(Medecin.class, nom);
		}
		*/
		
		//recupérer un seul medecin pour le modifier par nom et prénom
		public Medecin obtenirUnMedecin(Long idMed) {
			
			
			final String requeteJPQL = "SELECT b FROM Medecin b WHERE b.idMed =:filtre";
			
			
			final TypedQuery<Medecin> requeteType = em.createQuery(requeteJPQL, Medecin.class)
				.setParameter("filtre", idMed);
				//.setParameter("filtre2", prenom);
		
			
			
			//retourne le premier element de la liste, sinon null. il faut gerer le NPE
			List<Medecin> elementList = requeteType.getResultList();
			return elementList.isEmpty( ) ? null : elementList.get(0);
				
		}
		
		public void desactiver(Integer id) {
			
			em.remove(em.getReference(Medecin.class, id));
		}
		
		public void modifier(Medecin medecin) {
			
			em.merge(medecin);
		}


		//liste des medecins
		public List<Medecin> obtenir() {
			final String requeteJPQL = "SELECT b FROM Medecin b";
			
			
			final TypedQuery<Medecin> requeteType = em.createQuery(requeteJPQL, Medecin.class);
				
			
			return requeteType.getResultList();
		}

		// obtenir liste des patients par email du medecin par id medecin	
		public List<Patient> obtenirPatientsDunMedecin(Long idMed) {

			
			final String requeteJPQL = "SELECT b FROM Patient b where b.medecin.idMed =:filtre";
			
			final TypedQuery<Patient> requeteType = em.createQuery(requeteJPQL, Patient.class)
				.setParameter("filtre", idMed);
		
			
			List<Patient> elementList = requeteType.getResultList();
			return elementList.isEmpty( ) ? null : elementList;
						
		}

		// obtenir liste des patients par email du medecin
public List<Patient> obtenirPatientsDunMedecinParEmailMed(String email) {

			
			final String requeteJPQL = "SELECT b FROM Patient b where b.medecin.emailMed =:filtre";
			
			final TypedQuery<Patient> requeteType = em.createQuery(requeteJPQL, Patient.class)
					.setParameter("filtre", email);
		
			
			List<Patient> elementList = requeteType.getResultList();
			return elementList.isEmpty( ) ? null : elementList;
						
		}

		
		//Obtenir medecin par eMail
		
			public Medecin obtenirMedecinPareMail(String eMail) {

			
			final String requeteJPQL = "SELECT b FROM Medecin b where b.emailMed =:filtre";
			
			final TypedQuery<Medecin> requeteType = em.createQuery(requeteJPQL, Medecin.class)
				.setParameter("filtre", eMail);
		
			
			
			List<Medecin> elementList = requeteType.getResultList();
			return elementList.isEmpty( ) ? null : elementList.get(0);
						
		}

		public void supprimer(Long idMed) {
			em.remove(em.getReference(Medecin.class, idMed));
			
		}
		
}
