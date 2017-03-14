package fr.maazouza.averroes.middleware.objetmetier.medecin;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;


@Entity
@Table(name = "T_MEDECIN")
@XmlRootElement
public class Medecin implements Cloneable, Serializable  {
	
	private static final long serialVersionUID = 1L;

	/** Référence unique du médecin */
	private long id_med;

	/** Nom */
	private String nom_med;
	
	/** Prénom */
	private String prenom_med;
	
	/** Téléphone mobile*/
	private String tel_mob_med;
	
	/** Téléphone fixe*/
	private String tel_fixe_med;
	
	/** Mot de passe **/
	private String mdp_med;
	
	/** Mail */
	private String mail_med;
	
	/** Patient */
	private List<Patient> patients;
	
	public Medecin(){
		
	}
	
	// les getteurs et setteurs publiques
	@Id
	@GeneratedValue//clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_MED")		
	public long getId_med() {
		return id_med;
	}


	public void setId_med(long id_med) {
		this.id_med = id_med;
	}

	@Column(name = "NOM_MED")
	public String getNom_med() {
		return nom_med;
	}


	public void setNom_med(String nom_med) {
		this.nom_med = nom_med;
	}

	@Column(name = "PRENOM_MED")
	public String getPrenom_med() {
		return prenom_med;
	}


	public void setPrenom_med(String prenom_med) {
		this.prenom_med = prenom_med;
	}

	@Column(name = "TELMOB_MED")
	public String getTel_mob_med() {
		return tel_mob_med;
	}
	
	public void setTel_mob_med(String tel_mob_med) {
		this.tel_mob_med = tel_mob_med;
	}

	@Column(name = "TELFIXE_MED")
	public String getTel_fixe_med() {
		return tel_fixe_med;
	}


	public void setTel_fixe_med(String tel_fixe_med) {
		this.tel_fixe_med = tel_fixe_med;
	}

	@Column(name = "MDP_MED")
	public String getMdp_med() {
		return mdp_med;
	}


	public void setMdp_med(String mdp_med) {
		this.mdp_med = mdp_med;
	}

	@Column(name = "EMAIL_MED")
	public String getMail_med() {
		return mail_med;
	}


	public void setMail_med(String mail) {
		this.mail_med = mail;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	//@OneToMany(cascade = CascadeType.ALL) pour créer en cascade
	@OneToMany(fetch = FetchType.LAZY,mappedBy="medecin")
	public List<Patient> getPatients() {
		return patients;
	}
	
	

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	
}
