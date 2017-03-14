/**
 * 
 */
package fr.maazouza.averroes.middleware.objetmetier.patient;

/**
 * @author Maazouza
 *
 */
public class PatientDejaExistantException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public PatientDejaExistantException(String message) {
		super(message);
	}

}
