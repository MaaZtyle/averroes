package fr.maazouza.averroes.middleware.objetmetier.vaccin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;

@Entity
@Table(name = "T_VACCIN")
@XmlRootElement
public class Vaccin implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** Référence unique du vaccin */
	private long idVac;
	
	/** Nom du vaccine */
	private String nomVac;
	
	/** Description du vaccin */
	private String descriptionVac;
	
	/** Date dernier vaccin */
	private String dateDernierVac;
	
	/** Date prochain vaccin */
	private String dateProchainVac;
	
	/** Date création vaccin */
	private String dateCreationVac;
	
	/** Date archivage vaccin */
	private String dateArchivageVac;
	
	/** Allerte patient vaccin */
	private Boolean alertePatientVac;
	
	/** Allerte medecin vaccin */
	private Boolean alerteMedecinVac;
	
	/** dossier medical du patient**/
	private DossierMedical dossierMedical;
	
	
/**************************************/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/** Constructeur vide pour répondre à la norme Java Bean**/
	public Vaccin(){
		
	}
	// les getteurs et setteurs publiques
	@Id
	@GeneratedValue//clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_VAC", nullable = false)
	public long getIdVac() {
		return idVac;
	}

	public void setIdVac(long idVac) {
		this.idVac = idVac;
	}
	
	@Column(name = "DESCRIPTION_VAC")
	public String getDescriptionVac() {
		return descriptionVac;
	}

	public void setDescriptionVac(String descriptionVac) {
		this.descriptionVac = descriptionVac;
	}
	
	@Column(name = "NOM_VAC")
	public String getNomVac() {
		return nomVac;
	}

	public void setNomVac(String nomVac) {
		this.nomVac = nomVac;
	}

	@Column(name = "DATE_DERNIER_VAC")
	public String getDateDernierVac() {
		return dateDernierVac;
	}

	public void setDateDernierVac(String dateDernierVac) {
		this.dateDernierVac = dateDernierVac;
	}

	@Column(name = "DATE_PROCHAIN_VAC")
	public String getDateProchainVac() {
		return dateProchainVac;
	}

	public void setDateProchainVac(String dateProchainVac) {
		this.dateProchainVac = dateProchainVac;
	}

	@Column(name = "DATE_CREATION")
	public String getDateCreationVac() {
		return dateCreationVac;
	}

	public void setDateCreationVac(String dateCreationVac) {
		this.dateCreationVac = dateCreationVac;
	}

	
	@Column(name = "DATE_ARCHIVAGE")
	public String getDateArchivageVac() {
		return dateArchivageVac;
	}

	public void setDateArchivageVac(String dateArchivageVac) {
		this.dateArchivageVac = dateArchivageVac;
	}
	
	@Column(name = "ALERTE_PATIENT_VAC")
	public Boolean getAlertePatientVac() {
		return alertePatientVac;
	}

	public void setAlertePatientVac(Boolean alertePatientVac) {
		this.alertePatientVac = alertePatientVac;
	}
	
	@Column(name = "ALERTE_MEDECIN_VAC")
	public Boolean getAlerteMedecinVac() {
		return alerteMedecinVac;
	}

	public void setAlerteMedecinVac(Boolean alerteMedecinVac) {
		this.alerteMedecinVac = alerteMedecinVac;
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOSSIER_ID", nullable = false)
	@JsonIgnore
	public DossierMedical getDossierMedical() {
		return dossierMedical;
	}

	public void setDossierMedical(DossierMedical dossierMedical) {
		this.dossierMedical = dossierMedical;
	}


	
	
	

}
