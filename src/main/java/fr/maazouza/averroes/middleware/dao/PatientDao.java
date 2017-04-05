/**
 * 
 */
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
	 * Avoir la liste des patients complète
	 * */
	
	public List<Patient> obtenir() {
		
			//final String requeteJPQL = "SELECT b FROM Patient b WHERE b.nomPat = :filtre or b.prenomPat =:filtre";
		final String requeteJPQL = "SELECT b FROM Patient b ";
		
		final TypedQuery<Patient> requeteType = em.createQuery(requeteJPQL, Patient.class);
			//.setParameter("filtre", predicat);
		
		
		return requeteType.getResultList();
		
		
	}
	
	//recupérer un seul patient pour le modifier par le nom et prénom
	public Patient obtenirUnPatient(Long idPat) {
		
		
		final String requeteJPQL = "SELECT b FROM Patient b WHERE b.idPat =:filtre";
		
		
		final TypedQuery<Patient> requeteType = em.createQuery(requeteJPQL, Patient.class)
			//.setParameter("filtre1", nom)
			.setParameter("filtre", idPat);
		
		
		//retourne le premier element de la liste, sinon null. il faut gerer le NPE
		List<Patient> elementList = requeteType.getResultList();
		return elementList.isEmpty( ) ? null : elementList.get(0);
			
	}
	
public Medecin obtenirMedecinDunPatient(long idPat) {
		
		
		final String requeteJPQL = "SELECT b FROM Medecin b WHERE b.idMed =:filtre";
		
		
		final TypedQuery<Medecin> requeteType = em.createQuery(requeteJPQL, Medecin.class)
			.setParameter("filtre", idPat);
		
		
		//retourne le premier element de la liste, sinon null. il faut gerer le NPE
		List<Medecin> elementList = requeteType.getResultList();
		return elementList.isEmpty( ) ? null : elementList.get(0);
			
	}
	
	
	
	
	
	
	public void modifier(Patient patient) {
		
		em.merge(patient);
	}
	
	public void supprimer(Long idPat){
		
		
		em.remove(em.getReference(Patient.class, idPat));
	}
// obtenir un seul patient avec son email
	public Patient obtenirPatientPareMail(String eMail) {
		final String requeteJPQL = "SELECT b FROM Patient b where b.emailPat =:filtre";
		
		final TypedQuery<Patient> requeteType = em.createQuery(requeteJPQL, Patient.class)
			.setParameter("filtre", eMail);
	
		
		
		List<Patient> elementList = requeteType.getResultList();
		return elementList.isEmpty( ) ? null : elementList.get(0);
	}

	
	
	}
	


