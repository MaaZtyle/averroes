package fr.maazouza.averroes.middleware.objetmetier.dossierMedical;

public class DossierMedicalDejaExistantException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	public DossierMedicalDejaExistantException(String message) {
		super(message);
	}

}
