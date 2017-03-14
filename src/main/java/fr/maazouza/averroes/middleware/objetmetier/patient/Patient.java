package fr.maazouza.averroes.middleware.objetmetier.patient;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

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
	private long id_pat;
	
	/** Nom */
	private String nom_pat;
	
	/** Prénom */
	private String prenom_pat;
	
	/** Date de naissance */
	private Date dateNaissance_pat;
	
	/** Téléphone mobile*/
	private String tel_mob_pat;
	
	/** Téléphone fixe*/
	private String tel_fixe_pat;
		
	/** Mot de passe **/
	private String mdp_pat;
	
	/** Adresse */
	private String adresse_pat;
	
	/** Mail */
	private String email_pat;
	
	/** Date création du patient */
	private Date date_creation_pat;
	
	/** Medecin de mon patient, utilisé par le medecin pour avoir la liste de ses patients */
	private Medecin medecin;


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
	public long getId_pat() {
		return id_pat;
	}

	public void setId_pat(long id_pat) {
		this.id_pat = id_pat;
	}
	
	@Column(name = "NOM_PAT")
	public String getNom_pat() {
		return nom_pat;
	}

	public void setNom_pat(String nom_pat) {
		this.nom_pat = nom_pat;
	}

	@Column(name = "PRENOM_PAT")
	public String getPrenom_pat() {
		return prenom_pat;
	}

	public void setPrenom_pat(String prenom_pat) {
		this.prenom_pat = prenom_pat;
	}

	@Column(name = "DATENAISSANCE_PAT")
	public Date getDateNaissance_pat() {
		return dateNaissance_pat;
	}

	public void setDateNaissance_pat(Date dateNaissance) {
		this.dateNaissance_pat = dateNaissance;
	}
	
	@Column(name = "TELMOB_PAT")
	public String getTel_mob_pat() {
		return tel_mob_pat;
	}

	public void setTel_mob_pat(String tel_mob_pat) {
		this.tel_mob_pat = tel_mob_pat;
	}

	@Column(name = "ADRESSE_PAT")
	public String getAdresse_pat() {
		return adresse_pat;
	}

	public void setAdresse_pat(String adresse_pat) {
		this.adresse_pat = adresse_pat;
	}

	@Column(name = "EMAIL_PAT")
	public String getEmail_pat() {
		return email_pat;
	}

	public void setEmail_pat(String email_pat) {
		this.email_pat = email_pat;
	}

	@Column(name = "DATECREATION_PAT")
	public Date getDate_creation_pat() {
		return date_creation_pat;
	}

	public void setDate_creation_pat(Date date_creation_pat) {
		this.date_creation_pat = date_creation_pat;
	}

	@Column(name = "TELFIXE_PAT")
	public String getTel_fixe_pat() {
		return tel_fixe_pat;
	}


	public void setTel_fixe_pat(String tel_fixe_pat) {
		this.tel_fixe_pat = tel_fixe_pat;
	}

	
	@Column(name = "MDP_PAT")
	public String getMdp_pat() {
		return mdp_pat;
	}

	public void setMdp_pat(String mdp_pat) {
		this.mdp_pat = mdp_pat;
	}

	//déclaration de plusieurs patient à un medecin
	//avec un nom donné à ma table de jointure, non nullable. En mode Lazy, dès qu'on en aura besoin
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEDECIN_ID", nullable = false)
	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	
	
}
