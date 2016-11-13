package fr.maazouza.averroes.middleware.objetmetier.patient;

import java.io.Serializable;

public class Patient implements Cloneable, Serializable  {
	
	private static final long serialVersionUID = 1L;

	/** Référence unique du patient */
	private Integer id;

	/** Nom */
	private String nom;
	
	/** Prénom */
	private String prenom;
	
	/** Date de naissance */
	private String dateNaissance;
	
	/** Téléphone */
	private String telephone;
	
	/** Adresse */
	private String adresse;
	
	/** Mail */
	private String mail;

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

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	
}
