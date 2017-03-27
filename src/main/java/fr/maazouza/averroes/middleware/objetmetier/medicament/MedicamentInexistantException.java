package fr.maazouza.averroes.middleware.objetmetier.medicament;

public class MedicamentInexistantException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MedicamentInexistantException(String message) {
		super(message);
	}

}
