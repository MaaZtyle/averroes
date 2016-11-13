package fr.maazouza.averroes.middleware.objetmetier.medecin;

import java.io.Serializable;
import java.util.List;

import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;

public class Medecin implements Cloneable, Serializable  {
	
	private static final long serialVersionUID = 1L;

	/** Référence unique du médecin */
	private Integer id;

	/** Nom */
	private String nom;
	
	/** Prénom */
	private String prenom;
	
	/** Téléphone */
	private String telephone;
	
	/** Mail */
	private String mail;
	
	/** Liste de patient */
	private List<Patient> patients;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	
	
}
