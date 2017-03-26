package fr.maazouza.averroes.middleware.objetmetier.allergie;

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
@Table(name = "T_ALLERGIE")
@XmlRootElement
public class Allergie implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** Référence unique du dossier */
	private long idAll;
	
	/** Designation de la allergie */
	private String designationAll;
	
	/** Description de la allergie */
	private String descriptionAll;
	
	/** Date apparition de la allergie */
	private String dateAppAll;
	
	/** Etat de la allergie */
	private Boolean etatAll;
	
	/** dossier medical du patient**/
	private DossierMedical dossierMedical;
	
	
/**************************************/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/** Constructeur vide pour répondre à la norme Java Bean**/
	public Allergie(){
		
	}
	// les getteurs et setteurs publiques
	@Id
	@GeneratedValue//clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_ALL")
	public long getIdAll() {
		return idAll;
	}

	public void setIdAll(long idAll) {
		this.idAll = idAll;
	}
	@Column(name = "DESIGNATION_ALL")
	public String getDesignationAll() {
		return designationAll;
	}

	public void setDesignationAll(String designationAll) {
		this.designationAll = designationAll;
	}
	
	@Column(name = "DESCRIPTION_ALL")
	public String getDescriptionAll() {
		return descriptionAll;
	}

	public void setDescriptionAll(String descriptionAll) {
		this.descriptionAll = descriptionAll;
	}
	@Column(name = "DATE_APPARITION_ALL")
	public String getDateAppAll() {
		return dateAppAll;
	}

	public void setDateAppAll(String dateAppAll) {
		this.dateAppAll = dateAppAll;
	}
	@Column(name = "ETAT_ALL")
	public Boolean getEtatAll() {
		return etatAll;
	}

	public void setEtatAll(Boolean etatAll) {
		this.etatAll = etatAll;
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
