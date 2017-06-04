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
@Table(name = "T_VACCIN_ARCHIVE")
@XmlRootElement
public class VaccinArchive implements Cloneable, Serializable {
	
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
	
	/** Date création vaccin */
	private String dateArchivage;
	
	/** Allerte patient vaccin */
	private Boolean alertePatientVac;
	
	/** Allerte medecin vaccin */
	private Boolean alerteMedecinVac;
	
	/** dossier medical du patient**/
	private Long idDos;
	
	
/**************************************/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/** Constructeur vide pour répondre à la norme Java Bean**/
	public VaccinArchive(){
		
	}
	// les getteurs et setteurs publiques
	@Id
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
	public String getDateArchivage() {
		return dateArchivage;
	}

	public void setDateArchivage(String dateArchivage) {
		this.dateArchivage = dateArchivage;
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
		
	@Column(name = "ID_DOSSIER")
	public Long getIdDos() {
		return idDos;
	}

	public void setIdDos(Long idDos) {
		this.idDos = idDos;
	}


}
