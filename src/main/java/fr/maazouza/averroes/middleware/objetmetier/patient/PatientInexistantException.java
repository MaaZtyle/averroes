package fr.maazouza.averroes.middleware.objetmetier.patient;

public class PatientInexistantException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public PatientInexistantException(String message) {
		super(message);
	}

}
