package fr.maazouza.averroes.middleware.objetmetier.medecin;

public class MedecinDejaExistantException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	public MedecinDejaExistantException(String message) {
		super(message);
	}

}
