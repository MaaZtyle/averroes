package fr.maazouza.averroes.middleware.objetmetier.medecin;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "T_MEDECIN")
@XmlRootElement
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
	//private List<Patient> patients;
	
	@Id
	@Column(name = "MED_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "MED_NOM")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Column(name = "MED_PRENOM")
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Column(name = "MED_TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name = "MED_MAIL")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	/*public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	*/
	
}
