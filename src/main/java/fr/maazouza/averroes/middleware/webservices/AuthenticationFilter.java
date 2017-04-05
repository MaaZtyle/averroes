package fr.maazouza.averroes.middleware.webservices;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import fr.maazouza.averroes.middleware.dao.TokenDao;
import fr.maazouza.averroes.middleware.objetmetier.token.Token;
import fr.maazouza.averroes.middleware.objetmetier.token.TokenExpireException;
import fr.maazouza.averroes.middleware.objetmetier.token.TokenInconnuException;

import fr.maazouza.averroes.middleware.services.IMedecinService;
import fr.maazouza.averroes.middleware.services.IPatientService;
import fr.maazouza.averroes.middleware.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@EJB
	TokenService tokenService;
	
	@EJB
	IMedecinService medecinService;
	
	@EJB
	IPatientService patientService;
	
	String adresseMail;
/*
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted
		// correctly
		// Check if the HTTP Authorization header is present and formatted correctly 
		
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
       
        
        String token = authorizationHeader.substring(authorizationHeader.indexOf("\"") + 1);     
        token = token.split("\"")[0];

        
		try {
						
			// Validate the token
			validateToken(token);

		} catch (TokenInexistantException e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
		catch (TokenExpireException e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
	
	*/
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted
		// correctly
		// Check if the HTTP Authorization header is present and formatted correctly 
		
        if (authorizationHeader == null) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
       
        
        //String token = authorizationHeader.substring(authorizationHeader.indexOf("\"") + 1);     
        //token = token.split("\"")[0];

        
		try {
						
			// Validate the token
			validateToken(authorizationHeader);
			
			// si c'est validé, je persiste l'adresse mail dans le context security

		} catch (TokenInconnuException e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
		catch (TokenExpireException e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
		
		
		/*Within your ContainerRequestFilter.filter(ContainerRequestContext) method, you can set a new security context information for the current request.

		Override the SecurityContext.getUserPrincipal(), returning a Principal instance.

		The Principal's name is the username of the user you issued the token for. You will have to know it when validating the token.
		*/
		final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
		requestContext.setSecurityContext(new SecurityContext() {

		    @Override
		    public Principal getUserPrincipal() {

		        return new Principal() {

		            @Override
		            public String getName() {
		                return  adresseMail;
		            }
		        };
		    }

		    @Override
		    public boolean isUserInRole(String role) {
		        return true;
		    }

		    @Override
		    public boolean isSecure() {
		        return currentSecurityContext.isSecure();
		    }

		    @Override
		    public String getAuthenticationScheme() {
		        return "Bearer";
		    }
		});
	}
	
	

	/*private void validateToken(String token) throws TokenInexistantException,TokenExpireException {
		// Check if it was issued by the server and if it's not expired
		// Throw an Exception if the token is invalid

		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date()); // sets calendar time/date

		List<Token> listeToken = tokenService.obtenirToken(token);// je cherche tous
																// les tokens

		Token result = listeToken.stream().filter(item -> item.getToken().equals(token)).findFirst().orElse(null);

		if (result == null)
			throw new TokenInexistantException("Autorisation refusée, pas de token prout");
		if (result.getDateExpToken().before(cal.getTime()))
			
		throw new TokenExpireException("Token expiré, il faut se reconntecter");
	}
	*/
	
	//Sample method to validate and read the JWT
	private void validateToken(String jwt) throws TokenExpireException,TokenInconnuException {
	 
		
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date()); // sets calendar time/date
		
		
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary("doranco"))
	       .parseClaimsJws(jwt).getBody();
	    
	    // je récupère l'adresse mail du token dans le subject
	    adresseMail = claims.getSubject();
	    
	    
	    // si on trouve pas de mail chez les patients et medecin, exception
	   // if((medecinService.existerPareMail(adresseMail)== false) && (patientService.existerPareMail(adresseMail)== false))
	    	
	    
	    // si la date d'expiration depasse
	    if (claims.getExpiration().before(cal.getTime()))			
			throw new TokenExpireException("Token expiré, il faut se reconntecter");
	    
	   
	    // si la date d'expiration depasse
	    if (!claims.getIssuer().equals("Averroes"))		
			throw new TokenInconnuException("Token inconnu");
	    
	    //System.out.println("ID: " + claims.getId());
	   // System.out.println("Subject: " + claims.getSubject());
	    //System.out.println("Issuer: " + claims.getIssuer());
	   // System.out.println("Expiration: " + claims.getExpiration());
	}
	
	
}


