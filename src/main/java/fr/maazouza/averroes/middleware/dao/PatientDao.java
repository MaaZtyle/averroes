/**
 * 
 */
package fr.maazouza.averroes.middleware.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;

/**
 * @author Maazouza
 *
 */

@Stateless
//marqueur indiquant au CDI (context and dependencies injection) que c'est lui qui doit 
//gérer ce composant partagé.
@Transactional
//indique qu'on utilise des transactions. Rends la base dans son etat initial si la transaction echoue

public class PatientDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public void persister(Patient patient) {
		
		
		em.persist(patient);
	}
	
	/*
	 * Avoir la liste des patients
	 * */
	
	public List<Patient> obtenir(String predicat) {
		
			final String requeteJPQL = "SELECT b FROM Patient b WHERE b.nom_pat = :filtre or b.prenom_pat =:filtre";
		
		
		final TypedQuery<Patient> requeteType = em.createQuery(requeteJPQL, Patient.class)
			.setParameter("filtre", predicat);
		
		return requeteType.getResultList();
		
		
	}
	
	//recupérer un seul patient pour le modifier par le nom et prénom
	public Patient obtenirUnPatient(String nom, String prenom) {
		
		
		final String requeteJPQL = "SELECT b FROM Patient b WHERE b.nom_pat = :filtre1 and b.prenom_pat =:filtre2";
		
		
		final TypedQuery<Patient> requeteType = em.createQuery(requeteJPQL, Patient.class)
			.setParameter("filtre1", nom)
			.setParameter("filtre2", prenom);
		
		
		//retourne le premier element de la liste, sinon null. il faut gerer le NPE
		List<Patient> elementList = requeteType.getResultList();
		return elementList.isEmpty( ) ? null : elementList.get(0);
			
	}
	
public Medecin obtenirMedecinDunPatient(long id_pat) {
		
		
		final String requeteJPQL = "SELECT b FROM Medecin b WHERE b.id_med = :filtre";
		
		
		final TypedQuery<Medecin> requeteType = em.createQuery(requeteJPQL, Medecin.class)
			.setParameter("filtre", id_pat);
		
		
		//retourne le premier element de la liste, sinon null. il faut gerer le NPE
		List<Medecin> elementList = requeteType.getResultList();
		return elementList.isEmpty( ) ? null : elementList.get(0);
			
	}
	
	
	
	
	
	
	public void modifier(Patient patient) {
		
		em.merge(patient);
	}
	
	public void supprimer(String nomPat){
		
		final String requeteJPQL = "SELECT b FROM Patient b WHERE b.nom_pat = :filtre or b.prenom_pat =:filtre";
		
		
		final TypedQuery<Patient> requeteType = em.createQuery(requeteJPQL, Patient.class)
			.setParameter("filtre", nomPat);
		
		List<Patient> elementList = requeteType.getResultList();
		
		em.remove(em.getReference(Patient.class, elementList.isEmpty( ) ? null : elementList.get(0).getId_pat()));
	}
	

}
