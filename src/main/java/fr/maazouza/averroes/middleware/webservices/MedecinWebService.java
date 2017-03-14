package fr.maazouza.averroes.middleware.webservices;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
import fr.maazouza.averroes.middleware.services.IMedecinService;

@WebService
@Path(value = "/medecin")
public class MedecinWebService {
	
	@EJB
	IMedecinService medecinService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/{predicat}")
	public List<Medecin> obtenirUnMedecin( 
			@PathParam("predicat") String predicat
	) 
	{
		
		return medecinService.obtenirMedecin(predicat);

	}
	
	//ajouter un medecin à partir de l'interface medecin, avec son id medecin
	@POST
	@Path(value = "/ajouter")
	public Response ajouterMEdecin(
			@QueryParam("nom_med") String nom_med,
			@QueryParam("prenom_med") String prenom_Med,
			@QueryParam("tel_mob_med") String tel_mob_med,
			@QueryParam("tel_fixe_med") String tel_fixe_med,
			@QueryParam("mdp_med") String mdp_med,
			@QueryParam("email_med") String email_med			
			
	) 
	{
	
		
		//on créé un nouveau patient, et on lui affecte les valeurs, et le medecin
				Patient patient = new Patient();
				
				//pour l'instant je créé un medecin, mais après il sera dejà dans la base
				// je devrais juste récupérer son id et l'insérer comme un champs normal
				Medecin medecin = new Medecin();
				
				medecin.setNom_med(nom_med);
				medecin.setPrenom_med(prenom_Med);
				medecin.setTel_mob_med(tel_mob_med);
				medecin.setTel_fixe_med(tel_fixe_med);
				medecin.setMdp_med(mdp_med);
				medecin.setMail_med(email_med);
				
				
				//On appelle le service d'ajout de patient
			
				try {
					medecinService.ajouterMedecin(medecin);
					return Response.status(200)
							.entity("Le medecin  : " + nom_med + ", Prenom : " + prenom_Med + " a été ajouté")
							.build();
				} catch (MedecinDejaExistantException e) {
					
					return Response.status(200)
							.entity(e.getMessage())
							.build();
					
				}
				
				
	
}
	
	

	@POST
	@Path(value = "/modifier")
	public Response modifierMedecin (
			@QueryParam("nom_med") String nom_med,
			@QueryParam("prenom_med") String prenom_med
						
	) 
	{
		Medecin medecin = new Medecin();
		
		medecin= medecinService.obtenirUnMedecin(nom_med,prenom_med);
		if(medecin != null){
			medecin.setNom_med(nom_med);
			medecin.setPrenom_med(nom_med);
		
		
		medecinService.modifierMedecin(medecin);
		
		return Response.status(200)
				.entity("Le medecin  : " + nom_med + ", prenom : " + prenom_med + " a été modifié")
				.build();
		}
		
		else{
		return Response.status(200)
				.entity("Le medecin  : " + nom_med + ", prenom : " + prenom_med + " n'a pas été modifié")
				.build();
		
		}
		
		
	}
	
	
}
