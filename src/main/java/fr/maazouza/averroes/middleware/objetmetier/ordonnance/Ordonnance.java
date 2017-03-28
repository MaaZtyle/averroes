package fr.maazouza.averroes.middleware.objetmetier.ordonnance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.medicament.Medicament;

@Entity
@Table(name = "T_ORDONNANCE")
@XmlRootElement
public class Ordonnance implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** Référence unique du dossier */
	private long idOrd;
	
	/** Date de la ordonnance */
	private String dateOrd;
	
	/** Medicaments de l'ordonnance */
	List<Medicament> medicament;
	
	

	/** Date création Maladie */
	private String dateCreationOrd;
	
	/** dossier medical du patient**/
	private DossierMedical dossierMedical;
	
	
/**************************************/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/** Constructeur vide pour répondre à la norme Java Bean**/
	public Ordonnance(){
		
	}
	// les getteurs et setteurs publiques
	@Id
	@GeneratedValue//clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_ORD", nullable = false)
	public long getIdOrd() {
		return idOrd;
	}

	public void setIdOrd(long idOrd) {
		this.idOrd = idOrd;
	}
	

	@Column(name = "DATE_ORD")
	public String getDateOrd() {
		return dateOrd;
	}

	public void setDateOrd(String dateOrd) {
		this.dateOrd = dateOrd;
	}
	
	@Column(name = "DATE_CREATION")
	public String getDateCreationOrd() {
		return dateCreationOrd;
	}

	public void setDateCreationOrd(String dateCreationOrd) {
		this.dateCreationOrd = dateCreationOrd;
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
	
	// Une ordonnace a plusieurs medicaments, je référence la relation avec l'ordonnance
	@OneToMany(mappedBy="ordonnance",fetch = FetchType.LAZY)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
	property = "idMed") //JsonIdentityInfo, va récupérer les entités allergies liées
	public List<Medicament> getMedicament() {
		return medicament;
	}

	public void setMedicament(List<Medicament> medicament) {
		this.medicament = medicament;
	}
	

	
	
	

}
