package fr.maazouza.averroes.middleware.objetmetier.medicament;

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
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.Ordonnance;

@Entity
@Table(name = "T_MEDICAMENT")
@XmlRootElement
public class Medicament implements Cloneable, Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Constructeur vide pour répondre à la norme Java Bean**/
	public Medicament(){
		
	}

	/** Référence du medicament */
	
	//private long idMed;
	
	/**code du medicament de la base du gouvernement*/
	private String codeCIS;
	
	/** denomination du medicament  de la base du gouvernement */
	private String denomination;
	

	/** posologie du medicament */
	private String posologie;
	
	/** quantité du medicament que l'on va mettre sur l'ordonnance*/
	private Integer quantite;

	/** Date création Maladie */
	private String dateCreationMed;
	
	/** dossier medical du patient**/
	private Ordonnance ordonnance;
	
	
	// les getteurs et setteurs publiques
	/*@Id
	@GeneratedValue//clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_MED")
	public long getIdMed() {
		return idMed;
	}

	public void setIdMed(long idMed) {
		this.idMed = idMed;
	}*/
	@Id
	//@GeneratedValue//clé primaire générée automatiquement par Hibernate
	@Column(name = "CODE_CIS")
	public String getCodeCIS() {
		return codeCIS;
	}

	public void setCodeCIS(String codeCIS) {
		this.codeCIS = codeCIS;
	}
	

	@Column(name = "DENOMINATION")
	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getPosologie() {
		return posologie;
	}
	@Column(name = "POSOLOGIE")
	public void setPosologie(String posologie) {
		this.posologie = posologie;
	}
	@Column(name = "QUANTITE")
	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	@Column(name = "DATE_CREATION")
	public String getDateCreationMed() {
		return dateCreationMed;
	}

	public void setDateCreationMed(String dateCreationMed) {
		this.dateCreationMed = dateCreationMed;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDONNANCE_ID", nullable = false)
	@JsonIgnore
	public Ordonnance getOrdonnance() {
		return ordonnance;
	}

	public void setOrdonnance(Ordonnance ordonnance) {
		this.ordonnance = ordonnance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
