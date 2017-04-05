package fr.maazouza.averroes.middleware.webservices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.resource.spi.work.SecurityContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinAvecPatientsException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;
import fr.maazouza.averroes.middleware.services.IMedecinService;
import fr.maazouza.averroes.middleware.services.IPatientService;

@WebService
@Path(value = "/medecin")
public class MedecinWebService {
	
	@EJB
	IMedecinService medecinService;
	
	@EJB
	IPatientService patientService;
	
	
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////MEDECIN/////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
	
	
// Afficher la liste des medecins 
//http://localhost:8080/AVERROES_MIDDLEWARE/ws/medecin/
// OK
			@GET
			@Secured({Role.medecin})// que les medecins ont le droit
			@Produces(MediaType.APPLICATION_JSON)
			
			@Path(value = "/")
			
			public List<Medecin> obtenirMedecins(@Context SecurityContext securityContext) 
			{
				
				return medecinService.obtenirMedecins();

			}
		
		
	
// Afficher un medecin par Id
//http://localhost:8080/AVERROES_MIDDLEWARE/ws/medecin/nometprenom/
//OK
	@GET
	@Secured({Role.medecin})// que les medecins ont le droit
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/id")
	public Medecin obtenirUnMedecin( @Context SecurityContext securityContext,
			@FormParam("idMed") Long idMed
			
	) 
	{
			
			return medecinService.obtenirUnMedecin(idMed);
		
			}
	
	
	
	
// Ajouter un medecin à partir de l'interface medecin
//OK
	@POST
	@Secured({Role.medecin})// que les medecins ont le droit
	@Path(value = "/")
	public Response ajouterMedecin(@Context SecurityContext securityContext,
			@FormParam("nomMed") String nomMed,
			@FormParam("prenomMed") String prenomMed,
			@FormParam("mdpMed") String mdpMed,
			@FormParam("emailMed") String emailMed			
			
	) 
	{
	
				
				
				Medecin medecin = new Medecin();
				
				//je défini un format date
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				
				//j'initialise tous les champs
				medecin.setNomMed(nomMed);
				medecin.setPrenomMed(prenomMed);
				medecin.setMdpMed(mdpMed);
				medecin.setEmailMed(emailMed);
				medecin.setDateCreationMed(LocalDateTime.now().format(formatter));
				
				
				//On appelle le service d'ajout de patient
			
				try {
					medecinService.ajouterMedecin(medecin);
					return Response.status(200)
							.entity("Le medecin  : " + nomMed + ", Prenom : " + prenomMed + " a été ajouté")
							.build();
				} catch (MedecinDejaExistantException e) {
					
					return Response.status(200)
							.entity(e.getMessage())
							.build();
					
				}
				
				
	
}
	
	
// Modifier un medecin
	//OK
	@PUT
	@Secured({Role.medecin})// que les medecins ont le droit
	@Path(value = "/")
	public Response modifierMedecin (@Context SecurityContext securityContext,
			@FormParam("nomMed") String nomMed,
			@FormParam("prenomMed") String prenomMed,
			@FormParam("idMed") Long idMed,
			@FormParam("telMobMed") String telMobMed,
			@FormParam("telFixeMed") String telFixeMed,
			@FormParam("mdpMed") String mdpMed,
			@FormParam("emailMed") String emailMed			
						
	) 
	{
		Medecin medecin = new Medecin();
		
		// Je cherche si le medecin existe par l'ID
		
			medecin= medecinService.obtenirUnMedecin(idMed);
			// Si j'ai un résultat, je merge tous les champs
			
			if(medecin != null){
			
				medecin.setNomMed(nomMed);
				medecin.setPrenomMed(prenomMed);
				medecin.setTelMobMed(telMobMed);
				medecin.setTelFixeMed(telFixeMed);
				medecin.setMdpMed(mdpMed);
				medecin.setEmailMed(emailMed);
				
				medecinService.modifierMedecin(medecin);
				
				return Response.status(200)
						.entity("Le medecin  : " + nomMed + ", prenom : " + prenomMed + " a été modifié")
						.build();
			
			}
		
		return Response.status(200)
				.entity("Le medecin  : " + nomMed + ", prenom : " + prenomMed + " n'existe pas")
				.build();
		}
		
		
	


	
//supprimer un medecin avec l'id. Je suppose qu'il y aune liste, on coche le medecin et on supprime. On a donc besoin que de l'id
// je vérifie l'existance du medecin et s'il na pas de patients, sinon je lève les exceptions
//OK
			@DELETE
			@Secured({Role.medecin})// que les medecins ont le droit
			@Path(value = "/")
			public Response supprimerMedecin(@Context SecurityContext securityContext,
					@FormParam("idMed") Long idMed
					
			) 
			{
				//Medecin medecin = new Medecin();
				
				
				try {
					medecinService.supprimerMedecin(idMed);
					return Response.status(200)
							.entity("Le medecin a été supprimé")
							.build();
				} catch (MedecinInexistantException e) {
					
					return Response.status(200)
							.entity(e.getMessage())
							.build();
					
				}
				catch (MedecinAvecPatientsException e) {
					
					return Response.status(200)
							.entity(e.getMessage())
							.build();
					
				}
				
				
			}
	
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////PATIENT/////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
			
// Afficher la liste des patients  d'un medecin
// sera le point d'entrée du medecin après s'être identifié, j'affiche la liste de ses patients
// je vais récupérer l'id du medecin via l'adresse mail, puis afficher ses patients
//http://localhost:8080/AVERROES_MIDDLEWARE/ws/medecin/patients
// OK
				@GET
				@Secured({Role.medecin})// que les medecins ont le droit
				@Produces(MediaType.APPLICATION_JSON)
				@Path(value = "/patients")
							
				public List<Patient> obtenirPatientsDunMedecin(
						@QueryParam("eMailMed") String eMailMed,
						@Context SecurityContext securityContext)
						
				{
										
						return medecinService.obtenirPatientsDunMedecinParEmail(eMailMed);

				}	
				
/*
			
// Afficher la liste des patients 
//http://localhost:8080/AVERROES_MIDDLEWARE/ws/medecin/patients

// OK
			@GET
			@Secured({Role.medecin})// que les medecins ont le droit
			@Produces(MediaType.APPLICATION_JSON)
			@Path(value = "/patients/")
						
			public List<Patient> obtenirPatients(@Context SecurityContext securityContext) 
			{
							
					return medecinService.obtenirPatients();

			}
			
*/
	
				
							
//ajouter un patient à partir de l'interface medecin, avec son id medecin
		@POST
		@Secured({Role.medecin})// que les medecins ont le droit
		@Path(value = "/patient")
		public Response ajouterPatient(@Context SecurityContext securityContext,
				@FormParam("nomPat") String nomPat,
				@FormParam("prenomPat") String prenomPat,
				@FormParam("mdpPat") String mdpPat,
				@FormParam("emailPat") String emailPat,
				@FormParam("idMedecin") long idMedecin
				
				
		) 
		{
			//on créé un nouveau patient, et on lui affecte les valeurs, et le medecin
			Patient patient = new Patient();
			Medecin medecin = new Medecin();
				// je vérifie qu'il y a bien un medecin dans la base avec l'id fourni	
				 medecin = medecinService.obtenirUnMedecin(idMedecin);
				
				if(medecin != null){
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				patient.setNomPat(nomPat);
				patient.setPrenomPat(prenomPat);
				patient.setMdpPat(mdpPat);
				patient.setEmailPat(emailPat);
				patient.setDateCreationPat(LocalDateTime.now().format(formatter));
				
				patient.setMedecin(medecin);
				
				//On appelle le service d'ajout de patient
				
				try {
					try {
						medecinService.ajouterPatient(patient);
					} catch (PatientInexistantException e) {
						
						return Response.status(200)
								.entity(e.getMessage())
								.build();
					}
					return Response.status(200)
							.entity("Le patient  : " + nomPat + ", Prenom : " + prenomPat + " a été ajouté")
							.build();
				} catch (PatientDejaExistantException e) {
					
					return Response.status(200)
							.entity(e.getMessage())
							.build();
									}
				
					
			}
			
			
				return Response.status(200)
						.entity("Le medecin  : " + idMedecin +" n'existe pas")
						.build();
					
		}
		
//supprimer le patient d'un medecin
//OK
		@DELETE
		@Secured({Role.medecin})// que les medecins ont le droit
		@Path(value = "/patient")
		public Response supprimerPatient(@Context SecurityContext securityContext,
			@FormParam("idPat") long idPat
				
				
				
		) 
		{
				
			Patient patient = new Patient();
			
			patient= medecinService.obtenirUnPatient(idPat);
			// si le patient existe dans la base, je le supprime
			if(patient != null)
			{medecinService.supprimerPatient(idPat);
			
			return Response.status(200)
					.entity("Le patient  : " + idPat +" a été supprimé")
					.build();}
			
			else
				return Response.status(200)
						.entity("Le patient  : " + idPat +" n'existe pas, et ne peut donc pas être supprimé")
						.build();
			
		}
		
//Modifier le patient d'un medecin
// OK
		@PUT
		@Secured({Role.medecin})// que les medecins ont le droit
		@Path(value = "/patient")
		public Response modifierPatient (@Context SecurityContext securityContext,
				@FormParam("idPat") Long idPat,
				@FormParam("nomPat") String nomPat,
				@FormParam("prenomPat") String prenomPat,
				@FormParam("mdpPat") String mdpPat,
				@FormParam("emailPat") String emailPat,
				@FormParam("idmedecin") long idMedecin
				
		) 
		{
			Patient patient = new Patient();
			Medecin medecin = new Medecin();
			
			// je récupère le patient avec l'id fourni
			patient= medecinService.obtenirUnPatient(idPat);
			
			// je récupère le medecin du patient avec l'id fourni
			medecin = patientService.obtenirMedecinDunPatient(idMedecin);
			
			
			//si le medecin existe
			if( medecin != null){
					// si le patient existe, je le modifie
					if(patient != null){
						patient.setNomPat(nomPat);
						patient.setPrenomPat(prenomPat);
						patient.setMdpPat(mdpPat);
						patient.setEmailPat(emailPat);
						patient.setMedecin(medecin);		
			
			
						medecinService.modifierPatient(patient);
						
						return Response.status(200)
								.entity("Le patient  : " + nomPat + ", prenom : " + prenomPat + " a été modifié")
								.build();
						}
			
						else{
						return Response.status(200)
								.entity("Le patient  : " + nomPat + ", age : " + prenomPat + " n'a pas été modifié car son id n'existe pas")
								.build();
						
						}
			}
				else{
				return Response.status(200)
						.entity("Le patient  : " + nomPat + ", age : " + prenomPat + " n'a pas été modifié car l'id medecin n'existe pas")
						.build();
						
				}
			
		

	
	
		}
}
