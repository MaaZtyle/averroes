package fr.maazouza.averroes.middleware.objetmetier.maladie;

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
@Table(name = "T_MALADIE")
@XmlRootElement
public class Maladie implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** Référence unique du dossier */
	private long idMal;
	
	/** Designation de la maladie */
	private String designationMal;
	
	/** Description de la maladie */
	private String descriptionMal;
	
	/** Date apparition de la maladie */
	private String dateAppMal;
	
	/** Date création Maladie */
	private String dateCreationMal;
	
	
	/** dossier medical du patient**/
	private DossierMedical dossierMedical;
	
	
/**************************************/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/** Constructeur vide pour répondre à la norme Java Bean**/
	public Maladie(){
		
	}
	// les getteurs et setteurs publiques
	@Id
	@GeneratedValue//clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_MAL",nullable = false)
	public long getIdMal() {
		return idMal;
	}

	public void setIdMal(long idMal) {
		this.idMal = idMal;
	}
	@Column(name = "DESIGNATION_MAL")
	public String getDesignationMal() {
		return designationMal;
	}

	public void setDesignationMal(String designationMal) {
		this.designationMal = designationMal;
	}
	
	@Column(name = "DESCRIPTION_MAL")
	public String getDescriptionMal() {
		return descriptionMal;
	}

	public void setDescriptionMal(String descriptionMal) {
		this.descriptionMal = descriptionMal;
	}
	@Column(name = "DATE_APPARITION_MAL")
	public String getDateAppMal() {
		return dateAppMal;
	}

	public void setDateAppMal(String dateAppMal) {
		this.dateAppMal = dateAppMal;
	}
	
	
	@Column(name = "DATE_CREATION")
	public String getDateCreationMal() {
		return dateCreationMal;
	}

	public void setDateCreationMal(String dateCreationMal) {
		this.dateCreationMal = dateCreationMal;
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
