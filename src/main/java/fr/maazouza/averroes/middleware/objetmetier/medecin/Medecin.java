package fr.maazouza.averroes.middleware.objetmetier.medecin;

import java.io.Serializable;

public class Medecin implements Cloneable, Serializable  {
	
	private static final long serialVersionUID = 1L;

	/** Référence unique */
	private String reference;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

}
