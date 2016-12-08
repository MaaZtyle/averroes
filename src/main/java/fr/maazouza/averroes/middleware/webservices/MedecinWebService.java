package fr.maazouza.averroes.middleware.webservices;

import java.time.Instant;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.services.IMedecinService;

@WebService
@Path(value = "/medecin")
public class MedecinWebService {
	
	@EJB
	IMedecinService medecinService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/{id}")
	public Medecin obtenirUnMedecin( 
			@PathParam("id") Integer id
	) 
	{
		
		return medecinService.obtenirMedecin(id);

}
	
	
}
