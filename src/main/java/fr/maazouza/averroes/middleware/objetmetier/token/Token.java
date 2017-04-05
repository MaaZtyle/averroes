package fr.maazouza.averroes.middleware.objetmetier.token;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "T_TOKEN")
@XmlRootElement
public class Token implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Référence unique du token */
	private long idToken;

	/** Utilisateur du token */
	private String utilisateur;

	/** Le token */
	private String token;

	/** Date expiration du token */
	private Date dateExpToken;

	/**************************************/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** Constructeur vide pour répondre à la norme Java Bean **/
	public Token() {

	}

	// les getteurs et setteurs publiques
	@Id
	@GeneratedValue // clé primaire générée automatiquement par Hibernate
	@Column(name = "ID_TOK", nullable = false)
	public long getIdToken() {
		return idToken;
	}

	public void setIdToken(long idToken) {
		this.idToken = idToken;
	}

	@Column(name = "UTILISATEUR")
	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Column(name = "DATE_EXPIRATION_TOKEN")
	public Date getDateExpToken() {
		return dateExpToken;
	}

	public void setDateExpToken(Date dateExpToken) {
		this.dateExpToken = dateExpToken;
	}

	@Column(name = "TOKEN")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
