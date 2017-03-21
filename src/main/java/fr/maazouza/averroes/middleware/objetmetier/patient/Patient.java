package fr.maazouza.averroes.middleware.objetmetier.patient;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;

/**
 * 
 * 
 * @author Maazouza
 * 
 * Classe décriant l'objet metiet Patient
 *
 */

@Entity
@Table(name = "T_PATIENT")
@XmlRootElement
public class Patient implements Cloneable, Serializable  {
	
	private static final long serialVersionUID = 1L;

	/** Référence unique du patient */
	private long idPat;
	
	/** Nom */
	private String nomPat;
	
	/** Prénom */
	private String prenomPat;
	
	
		
	/** Mot de passe **/
	private String mdpPat;
	
	
	
	/** Mail */
	private String emailPat;
	
	/** Date création du patient */
	private String dateCreationPat;
	
	/** Medecin de mon patient, utilisé par le medecin pour avoir la liste de ses patients */
	private Medecin medecin;
	
	/** dossier medical du patient**/
	private DossierMedical dossierMedical;


	/**************************************/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/** Constructeur vide pour répondre à la norme Java Bean**/
	public Patient(){
		
	}
	
	// les getteurs et setteurs publiques
	@Id
	@GeneratedValue//clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_PAT")
	public long getIdPat() {
		return idPat;
	}

	public void setIdPat(long idPat) {
		this.idPat = idPat;
	}
	
	@Column(name = "NOM_PAT")
	public String getNomPat() {
		return nomPat;
	}

	public void setNomPat(String nomPat) {
		this.nomPat = nomPat;
	}

	@Column(name = "PRENOM_PAT")
	public String getPrenomPat() {
		return prenomPat;
	}

	public void setPrenomPat(String prenomPat) {
		this.prenomPat = prenomPat;
	}

	

	@Column(name = "EMAIL_PAT")
	public String getEmailPat() {
		return emailPat;
	}

	public void setEmailPat(String emailPat) {
		this.emailPat = emailPat;
	}

	@Column(name = "DATECREATION_PAT")
	public String getDateCreationPat() {
		return dateCreationPat;
	}

	public void setDateCreationPat(String dateCreationPat) {
		this.dateCreationPat = dateCreationPat;
	}

	

	
	@Column(name = "MDP_PAT")
	public String getMdpPat() {
		return mdpPat;
	}

	public void setMdpPat(String mdpPat) {
		this.mdpPat = mdpPat;
	}

	//déclaration de plusieurs patient à un medecin
	//avec un nom donné à ma table de jointure, non nullable. En mode Lazy, dès qu'on en aura besoin
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEDECIN_ID", nullable = false)
	@JsonIgnore
	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	
	@OneToOne(mappedBy="patient",fetch = FetchType.LAZY) // reférence de la relation de la classe DossierMedicale
	public DossierMedical getDossierMedical() {
		return dossierMedical;
	}

	public void setDossierMedical(DossierMedical dossierMedical) {
		this.dossierMedical = dossierMedical;
	}
	
	
}
