package fr.maazouza.averroes.middleware.objetmetier.medecin;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;


@Entity
@Table(name = "T_MEDECIN")
@XmlRootElement
public class Medecin implements Cloneable, Serializable  {
	
	private static final long serialVersionUID = 1L;

	/** Référence unique du médecin */
	private long idMed;
	
	/** Nom */
	private String nomMed;
	
	/** Prénom */
	private String prenomMed;
	
	/** Téléphone mobile*/
	private String telMobMed;
	
	/** Téléphone fixe*/
	private String telFixeMed;
	
	/** Mot de passe **/
	private String mdpMed;
	
	/** Mail */
	private String emailMed;
	
	/** Date création medecin */
	private String dateCreationMed;
	
	/** Profil Medecin **/
	private final Boolean profilMedecin=true;
	

	/** Patient */
	private List<Patient> patients;
	
	public Medecin(){
		
	}
	
	
	
	// les getteurs et setteurs publiques
	@Id
	@GeneratedValue//clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_MED", nullable = false)		
	public long getIdMed() {
		return idMed;
	}


	public void setIdMed(long idMed) {
		this.idMed = idMed;
	}

	@Column(name = "NOM_MED", nullable = false)
	public String getNomMed() {
		return nomMed;
	}


	public void setNomMed(String nomMed) {
		this.nomMed = nomMed;
	}

	@Column(name = "PRENOM_MED")
	public String getPrenomMed() {
		return prenomMed;
	}


	public void setPrenomMed(String prenomMed) {
		this.prenomMed = prenomMed;
	}

	@Column(name = "TELMOB_MED")
	public String getTelMobMed() {
		return telMobMed;
	}
	
	public void setTelMobMed(String telMobMed) {
		this.telMobMed = telMobMed;
	}

	@Column(name = "TELFIXE_MED")
	public String getTelFixeMed() {
		return telFixeMed;
	}


	public void setTelFixeMed(String telFixeMed) {
		this.telFixeMed = telFixeMed;
	}

	@Column(name = "MDP_MED", nullable = false)
	public String getMdpMed() {
		return mdpMed;
	}

	public void setMdpMed(String mdpMed) {
		this.mdpMed = mdpMed;
	}

	@Column(name = "EMAIL_MED", nullable = false)
	public String getEmailMed() {
		return emailMed;
	}


	public void setEmailMed(String email) {
		this.emailMed = email;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "DATECREATION_MED", nullable = false)
	public String getDateCreationMed() {
		return dateCreationMed;
	}

	public void setDateCreationMed(String dateCreationMed) {
		this.dateCreationMed = dateCreationMed;
	}
	/*AccessType.FIELD
	 * les lectures / modifications se font directement sur les champs */

	@Access(AccessType.FIELD)
	@Column(name = "PROFIL_MEDECIN")
	public Boolean getProfilMedecin() {
		return profilMedecin;
	}


	//@OneToMany(cascade = CascadeType.ALL) pour créer en cascade
	
	@OneToMany(mappedBy="medecin",fetch = FetchType.LAZY)
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
	//		  property = "idPat") permet d'avoir chaque medecin avec les patients correspondant
	@JsonIgnore
	public List<Patient> getPatients() {
		return patients;
	}
	

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}



	
}
