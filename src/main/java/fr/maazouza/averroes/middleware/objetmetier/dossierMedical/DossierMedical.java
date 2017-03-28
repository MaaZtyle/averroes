package fr.maazouza.averroes.middleware.objetmetier.dossierMedical;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fr.maazouza.averroes.middleware.objetmetier.allergie.Allergie;
import fr.maazouza.averroes.middleware.objetmetier.antecedent.Antecedent;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.Ordonnance;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;

@Entity
@Table(name = "T_DOSSIERMEDICAL")
@XmlRootElement
public class DossierMedical implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Référence unique du dossier */
	private long idDos;

	/** Date de naissance */
	private String dateNaissancePat;

	/** Téléphone mobile */
	private String telMobPat;

	/** Téléphone fixe */
	private String telFixePat;

	/** Adresse */
	private String adressePat;

	/** Numéro de sécurité sociale */
	private long numSecu;

	/** Mutuelle */
	private String mutuelle;

	/** Sexe */
	private String sexe;

	/** Status familliale */
	private String statutFamiliale;

	/** Age */
	private Integer age;

	/** Taille */
	private Double taille;

	/** poids */
	private Double poids;

	/** IMC */
	private Double imc;

	/** Groupe sanguin */
	private String groupeSanguin;

	/** Donneur d'organes */
	private Boolean donneurOrgane;

	/** Suivi */
	private Boolean suivi;

	/** Contact famille */
	private String contactFamille;

	/** Telephone contact famille */
	private String telContactFamille;

	/** Date creation */
	private String dateCreationDos;

	/** Etat dossier */
	private Boolean dossierOuvert;

	/** Le dossier du patient */
	private Patient patient;

	/** Toutes les infos des autres tables **/

	/** Maladie */
	private List<Maladie> maladie;

	/** Allergies */
	private List<Allergie> allergie;

	/** Ordonnances */
	private List<Ordonnance> ordonnance;

	/** Antecedents */
	private List<Antecedent> antecedent;

	/**************************************/

	/** Constructeur vide pour répondre à la norme Java Bean **/
	public DossierMedical() {

	}
	// les getteurs et setteurs publiques

	@Id
	@GeneratedValue // clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_DOSSIER", nullable = false)
	public long getIdDos() {
		return idDos;
	}

	public void setIdDos(long idDos) {
		this.idDos = idDos;
	}

	@Column(name = "DATENAISSANCE_PAT")
	public String getDateNaissancePat() {
		return dateNaissancePat;
	}

	public void setDateNaissancePat(String dateNaissance) {
		this.dateNaissancePat = dateNaissance;
	}

	@Column(name = "TELMOB_PAT")
	public String getTelMobPat() {
		return telMobPat;
	}

	public void setTelMobPat(String telMobPat) {
		this.telMobPat = telMobPat;
	}

	@Column(name = "ADRESSE_PAT")
	public String getAdressePat() {
		return adressePat;
	}

	public void setAdressePat(String adressePat) {
		this.adressePat = adressePat;
	}

	@Column(name = "TELFIXE_PAT")
	public String getTelFixePat() {
		return telFixePat;
	}

	public void setTelFixePat(String telFixePat) {
		this.telFixePat = telFixePat;
	}

	@Column(name = "NUM_SECURITE_SOCIALE")
	public long getNumSecu() {
		return numSecu;
	}

	public void setNumSecu(long numSecu) {
		this.numSecu = numSecu;
	}

	@Column(name = "MUTUELLE")
	public String getMutuelle() {
		return mutuelle;
	}

	public void setMutuelle(String mutuelle) {
		this.mutuelle = mutuelle;
	}

	@Column(name = "SEXE")
	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	@Column(name = "STATUT_FAMILIALE")
	public String getStatutFamiliale() {
		return statutFamiliale;
	}

	public void setStatutFamiliale(String statutFamiliale) {
		this.statutFamiliale = statutFamiliale;
	}

	@Column(name = "AGE")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "TAILLE")
	public Double getTaille() {
		return taille;
	}

	public void setTaille(Double taille) {
		this.taille = taille;
	}

	@Column(name = "POIDS")
	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	@Column(name = "IMC")
	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	@Column(name = "GROUPE_SANGUIN")
	public String getGroupeSanguin() {
		return groupeSanguin;
	}

	public void setGroupeSanguin(String groupeSanguin) {
		this.groupeSanguin = groupeSanguin;
	}

	@Column(name = "DONNEUR_ORGANE")
	public Boolean getDonneurOrgane() {
		return donneurOrgane;
	}

	public void setDonneurOrgane(Boolean donneurOrgane) {
		this.donneurOrgane = donneurOrgane;
	}

	@Column(name = "SUIVI")
	public Boolean getSuivi() {
		return suivi;
	}

	public void setSuivi(Boolean suivi) {
		this.suivi = suivi;
	}

	@Column(name = "CONTACT_FAMILLE")
	public String getContactFamille() {
		return contactFamille;
	}

	public void setContactFamille(String contactFamille) {
		this.contactFamille = contactFamille;
	}

	@Column(name = "TELEPHONE_CONTACT_FAMILLE")
	public String getTelContactFamille() {
		return telContactFamille;
	}

	public void setTelContactFamille(String telContactFamille) {
		this.telContactFamille = telContactFamille;
	}

	@Column(name = "DATE_CREATION")
	public String getDateCreationDos() {
		return dateCreationDos;
	}

	public void setDateCreationDos(String dateCreationDos) {
		this.dateCreationDos = dateCreationDos;
	}

	@Column(name = "DOSSIER_OUVERT")
	public Boolean getDossierOuvert() {
		return dossierOuvert;
	}

	public void setDossierOuvert(Boolean dossierOuvert) {
		this.dossierOuvert = dossierOuvert;
	}

	// Un dossier medical n'est qu'a un seul patient
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID", nullable = false, unique = true)
	@JsonIgnore
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	// Un dossier medical a plusieurs maladies, je référence la relation avec le
	// dossier médical
	@OneToMany(mappedBy = "dossierMedical", fetch = FetchType.LAZY)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idMal") // JsonIdentityInfo,
																									// va
																									// récupérer
																									// les
																									// entités
																									// maladies
																									// liées
	public List<Maladie> getMaladie() {
		return maladie;
	}

	public void setMaladie(List<Maladie> maladie) {
		this.maladie = maladie;
	}

	// Un dossier medical a plusieurs allergies, je référence la relation avec
	// le dossier médical
	@OneToMany(mappedBy = "dossierMedical", fetch = FetchType.LAZY)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idAll") // JsonIdentityInfo,
																									// va
																									// récupérer
																									// les
																									// entités
																									// allergies
																									// liées
	public List<Allergie> getAllergie() {
		return allergie;
	}

	public void setAllergie(List<Allergie> allergie) {
		this.allergie = allergie;
	}

	// Un dossier medical a plusieurs ordonnances, je référence la relation avec
	// le dossier médical
	@OneToMany(mappedBy = "dossierMedical", fetch = FetchType.LAZY)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idOrd") // JsonIdentityInfo,
																									// va
																									// récupérer
																									// les
																									// entités
																									// allergies
																									// liées
	public List<Antecedent> getAntecedent() {
		return antecedent;
	}

	public void setAntecedent(List<Antecedent> antecedent) {
		this.antecedent = antecedent;
	}

	// Un dossier medical a plusieurs ordonnances, je référence la relation avec
	// le dossier médical
	@OneToMany(mappedBy = "dossierMedical", fetch = FetchType.LAZY)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idAnt") // JsonIdentityInfo,
																									// va
																									// récupérer
																									// les
																									// entités
																									// allergies
																									// liées
	public List<Ordonnance> getOrdonnance() {
		return ordonnance;
	}

	public void setOrdonnance(List<Ordonnance> ordonnance) {
		this.ordonnance = ordonnance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
