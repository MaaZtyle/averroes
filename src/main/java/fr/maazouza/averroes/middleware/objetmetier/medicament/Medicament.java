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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Check;

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

	// je prends comme clé primaire le code cis unique à chaque produit
	// c'est ma clé primaire fonctionnelle qui est non nule et non vide
	@Id
	@NotNull
	@Size(min=1)
	@Column(name = "CODE_CIS",nullable= false)
	
	public String getCodeCIS() {
		return codeCIS;
	}

	public void setCodeCIS(String codeCIS) {
		this.codeCIS = codeCIS;
	}
	

	@Column(name = "DENOMINATION", nullable = false)
	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getPosologie() {
		return posologie;
	}
	@Column(name = "POSOLOGIE", nullable = false)
	public void setPosologie(String posologie) {
		this.posologie = posologie;
	}
	@Column(name = "QUANTITE", nullable = false)
	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	@Column(name = "DATE_CREATION", nullable = false)
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
