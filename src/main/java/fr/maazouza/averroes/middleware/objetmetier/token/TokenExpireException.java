package fr.maazouza.averroes.middleware.objetmetier.token;

public class TokenExpireException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public TokenExpireException(String message) {
		super(message);
	}

}
