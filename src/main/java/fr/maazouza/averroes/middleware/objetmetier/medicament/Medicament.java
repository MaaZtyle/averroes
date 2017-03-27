package fr.maazouza.averroes.middleware.objetmetier.medicament;

import java.io.Serializable;

public class Medicament implements Cloneable, Serializable{
	
	
	/** Référence du medicament */
	private long idMed;
	
	/**code du medicament de la base du gouvernement*/
	private String codeCIS;
	
	/** denomination du medicament  de la base du gouvernement */
	private String denomination;
	
	public long getIdMed() {
		return idMed;
	}

	public void setIdMed(long idMed) {
		this.idMed = idMed;
	}

	public String getCodeCIS() {
		return codeCIS;
	}

	public void setCodeCIS(String codeCIS) {
		this.codeCIS = codeCIS;
	}

	public String getPosologie() {
		return posologie;
	}

	public void setPosologie(String posologie) {
		this.posologie = posologie;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	/** posologie du medicament */
	private String posologie;
	
	/** quantité du medicament que l'on va mettre sur l'ordonnance*/
	private Integer quantite;

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	
	
	

}
