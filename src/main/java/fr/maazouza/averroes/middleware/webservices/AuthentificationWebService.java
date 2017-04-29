/**
 * 
 */
package fr.maazouza.averroes.middleware.webservices;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.resource.spi.work.SecurityContext;
import javax.ws.rs.core.Context;

import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.NomOuMotDePasseException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.token.Token;
import fr.maazouza.averroes.middleware.services.IMedecinService;
import fr.maazouza.averroes.middleware.services.IPatientService;
import fr.maazouza.averroes.middleware.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.Principal;  
/**
 * @author Maazouza
 *
 */

@WebService

@Path("/authentification")
public class AuthentificationWebService {

	@EJB
	TokenService tokenService;

	@EJB
	IMedecinService medecinService;

	@EJB
	IPatientService patientService;
	
	// pour récupérer le role de l'utilisateur
	private Role role;

	// pour récupérer son id
	private Long id;
	
	// Pour récupérer son nom
	private String nom;
	
	
	
	
	// les claims du token
	
	
	
	Claims claims;
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password
			) {

		try {

			// Authenticate the user using the credentials provided
			String eMail = authenticate(username, password);

			// Issue a token for the user
			//String token = issueToken(username);
			
			//je génére un token avec le mail et le délai d'expiration
			String token = issueToken(eMail,600000);
			
			// Return the token on the response
			return Response.status(200)
					.entity (token)
					.build();

		} catch (NomOuMotDePasseException e) {
			String toto=null;

			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
		}
	}

	private String authenticate(String username, String password) throws NomOuMotDePasseException {
		// Authenticate against a database, LDAP, file or whatever
		// Throw an Exception if the credentials are invalid
		
		//Si je suis identifié, je renvoie le mail
		
		String eMail= null;

		
		//recherche dans la table medecin
			eMail = medecinService.authentifierUnMedecin(username, password);
		// si onle trouve pas dans medecin, on le cherche dans patient et on retourne le mail
			if(eMail==null)		
			eMail = patientService.authentifierUnPatient(username, password);
		// si c'ets toujorus null, je lève une exception
			
			if(eMail==null) 
				throw new NomOuMotDePasseException("Nom ou mot de passe incorrect");
		
		return eMail;

	}

	// sauvegarde dans la table token
	/*private String issueToken(String username) {
		// Issue a token (can be a random String persisted to a database or a
		// JWT token)
		// The issued token must be associated to a user
		// Return the issued token

		
		
		
		Token token = new Token();

		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date()); // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour

		Random random = new SecureRandom();
		String jeton = new BigInteger(130, random).toString(32);

		token.setDateExpToken(cal.getTime());
		token.setToken(jeton);
		token.setUtilisateur(username);
		
		List<Token> listeToken = tokenService.obtenirTokenParUtilisateur(username);// je cherche tous les tokens de l'utilisateur
		
		//supprimer l'ancien token
		Token result = listeToken.stream().filter(item -> item.getUtilisateur().equals(username)).findFirst().orElse(null);
		if(result != null)
		tokenService.supprimerToken(result.getIdToken());
		
		tokenService.ajouterToken(token);

		return jeton;

	}*/
	
	private String issueToken(String eMail, long ttlMillis) {
		// Issue a token (can be a random String persisted to a database or a
		// JWT token)
		// The issued token must be associated to a user
		// Return the issued token

		
		//The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	 
	    
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("doranco");
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    
	    //Let's set the JWT Claims
	   
	    
	    // je mets le role dans le token ca peut servir
	    if(medecinService.existerPareMail(eMail))		
			 {
	    		role=Role.medecin;
			 	//l'id de l'utilisateur aussi
			    Medecin medecin = medecinService.obtenirMedecinPareMail(eMail);
			    id=medecin.getIdMed();
			    nom=medecin.getNomMed();
			    
			 }
			 
		else {
			role = Role.patient;
			//l'id de l'utilisateur aussi
		    Patient patient = patientService.obtenirPatientPareMail(eMail);
		    id=patient.getIdPat();
		    nom=patient.getNomPat();
		}
	    
	    
	    
	    
	    // initialisation des claims
	   
	  	    
	    
	    
	    JwtBuilder builder = Jwts.builder()//.setId(username)
	    									.setHeaderParam("typ", "JWT")
                							.setIssuedAt(now)
                							.setSubject(eMail)
                							.setIssuer("Averroes")
	    									.claim("nom", nom)
	    									.claim("role", role)
	    								    .claim("id", id)
	    									.signWith(signatureAlgorithm, signingKey);
	    									
	 
	    //if it has been specified, let's add the expiration
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();

	}
	
	
	
	

}
