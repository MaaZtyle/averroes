package fr.maazouza.averroes.middleware.objetmetier.antecedent;

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
@Table(name = "T_ANTECEDENT")
@XmlRootElement
public class Antecedent implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** Référence unique du dossier */
	private long idAnt;
	
	/** Date de l'antecedent */
	private String dateAnt;
	
	/** Description de  l'antecedent */
	private String descriptionAnt;
	
	/** Commentaire de  l'antecedent */
	private String commentaireAnt;
	
	/** Sujet de  l'antecedent */
	private String sujetAnt;
	
	
	/** Date création de l'antecedent */
	private String dateCreationAnt;
	
	/** dossier medical du patient**/
	private DossierMedical dossierMedical;
	
	
/**************************************/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/** Constructeur vide pour répondre à la norme Java Bean**/
	public Antecedent(){
		
	}
	// les getteurs et setteurs publiques
	@Id
	@GeneratedValue//clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_AND", nullable = false)
	public long getIdAnt() {
		return idAnt;
	}

	public void setIdAnt(long idAnt) {
		this.idAnt = idAnt;
	}
	
	
	@Column(name = "DATE_ANT")
	public String getDateAnt() {
		return dateAnt;
	}

	public void setDateAnt(String dateAnt) {
		this.dateAnt = dateAnt;}
		
	@Column(name = "DESCRIPTION_ANT")
	public String getDescriptionAnt() {
		return descriptionAnt;
	}

	public void setDescriptionAnt(String descriptionAnt) {
		this.descriptionAnt = descriptionAnt;
	}
	
	@Column(name = "COMMENTAIRE_ANT")
	public String getCommentaireAnt() {
		return commentaireAnt;
	}

	public void setCommentaireAnt(String commentaireAnt) {
		this.commentaireAnt = commentaireAnt;
	}

	@Column(name = "SUJET_ANT")
	public String getSujetAnt() {
		return sujetAnt;
	}

	public void setSujetAnt(String sujetAnt) {
		this.sujetAnt = sujetAnt;
	}
	
	
	@Column(name = "DATE_CREATION")
	public String getDateCreationAnt() {
		return dateCreationAnt;
	}

	public void setDateCreationAnt(String dateCreationAnt) {
		this.dateCreationAnt = dateCreationAnt;
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
