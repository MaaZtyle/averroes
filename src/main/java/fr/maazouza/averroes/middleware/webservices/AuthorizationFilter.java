package fr.maazouza.averroes.middleware.webservices;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;


import fr.maazouza.averroes.middleware.services.IMedecinService;
import fr.maazouza.averroes.middleware.webservices.Secured;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	
	@Context SecurityContext securityContext;
	
	@EJB
	IMedecinService medecinService;
	
	
	
	

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the resource class which matches with the requested URL
		// Extract the roles declared by it
		Class<?> resourceClass = resourceInfo.getResourceClass();
		List<Role> classRoles = extractRoles(resourceClass);

		// Get the resource method which matches with the requested URL
		// Extract the roles declared by it
		Method resourceMethod = resourceInfo.getResourceMethod();
		List<Role> methodRoles = extractRoles(resourceMethod);

		try {

			// Check if the user is allowed to execute the method
			// The method annotations override the class annotations
			if (methodRoles.isEmpty()) {
				checkPermissions(classRoles);
			} else {
				checkPermissions(methodRoles);
			}

		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
		}
	}

	// Extract the roles from the annotated element
	private List<Role> extractRoles(AnnotatedElement annotatedElement) {
		if (annotatedElement == null) {
			return new ArrayList<Role>();
		} else {
			Secured secured = annotatedElement.getAnnotation(Secured.class);
			if (secured == null) {
				return new ArrayList<Role>();
			} else {
				Role[] allowedRoles = secured.value();
				return Arrays.asList(allowedRoles);
			}
		}
	}


	private void checkPermissions(List<Role> allowedRoles) throws Exception {
		// Check if the user contains one of the allowed roles
		// Throw an Exception if the user has not permission to execute the
		// method
		
		Principal principal = securityContext.getUserPrincipal();
		String eMail = principal.getName();
		
		// je stocke les roles de mon enum pour pouvoir comparer dans ma liste de role autorisés obtenu
		Role role =null;
		
		// si c'est un medecin, role est medecin, sinon c'est un patient
		if(medecinService.existerPareMail(eMail))		
			 role=Role.medecin;
		else role=Role.patient;
		
		// si la liste des roles autorisé ne conteitn pas mon role, je lève une exception
		if(!allowedRoles.contains(role)) throw new Exception();
		
		// pour rechercher dans mon enum, je dois faire une map, et associé chaque valeur à un string
		
	//	String role;
		   
	//	EnumMap<Role, String> map =
	//			   new EnumMap<Role, String>(Role.class);
		
		// associate values in map
		//   map.put(Role.medecin, "medecin");
		 //  map.put(Role.patient, "patient");
		   
		
		// si c'est un medecin, j'affecte le role medecin à ma string pour chercher si elle est présente dans la liste des roles autorisés
	//	if (medecinService.existerPareMail(eMail))
		//	role="medecin";
		//else role="patient";
		
		
		
		// si le role de l'utilisateur n'est pas dans la liste des role, exception
		///if(allowedRoles.contains(map.containsValue(role))== false) throw new Exception();
		
	}
}