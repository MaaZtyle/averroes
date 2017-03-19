package fr.maazouza.averroes.middleware.webservices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinAvecPatientsException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
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
			@Produces(MediaType.APPLICATION_JSON)
			@Path(value = "/")
			
			public List<Medecin> obtenirMedecins() 
			{
				
				return medecinService.obtenirMedecins();

			}
		
		
	
// Afficher un medecin par Id
//http://localhost:8080/AVERROES_MIDDLEWARE/ws/medecin/nometprenom/
//OK
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/id")
	public Medecin obtenirUnMedecin( 
			@QueryParam("idMed") Long idMed
			
	) 
	{
			
			return medecinService.obtenirUnMedecin(idMed);
		
			}
	
	
	
	
// Ajouter un medecin à partir de l'interface medecin
//OK
	@POST
	@Path(value = "/ajouter")
	public Response ajouterMEdecin(
			@QueryParam("nomMed") String nomMed,
			@QueryParam("prenomMed") String prenomMed,
			@QueryParam("telMobMed") String telMobMed,
			@QueryParam("telFixeMed") String telFixeMed,
			@QueryParam("mdpMed") String mdpMed,
			@QueryParam("emailMed") String emailMed			
			
	) 
	{
	
		
		
				
				//pour l'instant je créé un medecin, mais après il sera dejà dans la base
				// je devrais juste récupérer son id et l'insérer comme un champs normal
				Medecin medecin = new Medecin();
				
				//je défini un format date
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				
				//j'initialise tous les champs
				medecin.setNomMed(nomMed);
				medecin.setPrenomMed(prenomMed);
				medecin.setTelMobMed(telMobMed);
				medecin.setTelFixeMed(telFixeMed);
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
	@POST
	@Path(value = "/modifier")
	public Response modifierMedecin (
			@QueryParam("nomMed") String nomMed,
			@QueryParam("prenomMed") String prenomMed,
			@QueryParam("idMed") Long idMed,
			@QueryParam("telMobMed") String telMobMed,
			@QueryParam("telFixeMed") String telFixeMed,
			@QueryParam("mdpMed") String mdpMed,
			@QueryParam("emailMed") String emailMed			
						
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
			@POST
			@Path(value = "/supprimer")
			public Response supprimerMedecin(
					@QueryParam("idMed") Long idMed
					
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
	

			
// Afficher la liste des patients 
//http://localhost:8080/AVERROES_MIDDLEWARE/ws/medecin/patients
// OK
			@GET
			@Produces(MediaType.APPLICATION_JSON)
			@Path(value = "/patients/")
						
			public List<Patient> obtenirPatients() 
			{
							
					return medecinService.obtenirPatients();

			}
			

			
// Afficher la liste des patients  d'un medecin
//http://localhost:8080/AVERROES_MIDDLEWARE/ws/medecin/patients
// OK
				@GET
				@Produces(MediaType.APPLICATION_JSON)
				@Path(value = "/patientsMedecin")
							
				public List<Patient> obtenirPatientsDunMedecin(
						@QueryParam("idMed") Long idMed
						
						)  
				{
										
						return medecinService.obtenirPatientsDunMedecin(idMed);

				}		
				
							
//ajouter un patient à partir de l'interface medecin, avec son id medecin
		@POST
		@Path(value = "/patient/ajouter")
		public Response ajouterPatient(
				@QueryParam("nomPat") String nomPat,
				@QueryParam("prenomPat") String prenomPat,
				@QueryParam("dateNaissancePat") Date dateNaissancePat,
				@QueryParam("telMobPat") String telMobPat,
				@QueryParam("telFixePat") String telFixePat,
				@QueryParam("mdpPat") String mdpPat,
				@QueryParam("adressePat") String adressePat,
				@QueryParam("emailPat") String emailPat,
				@QueryParam("idmedecin") long idmedecin
				
				
		) 
		{
			//on créé un nouveau patient, et on lui affecte les valeurs, et le medecin
			Patient patient = new Patient();
			
			//pour l'instant je créé un medecin, mais après il sera dejà dans la base
			// je devrais juste récupérer son id et l'insérer comme un champs normal
		
				Medecin medecin = medecinService.obtenirUnMedecin(idmedecin);
				
				if(medecin != null){
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				patient.setNomPat(nomPat);
				patient.setPrenomPat(prenomPat);
				patient.setDateNaissancePat(dateNaissancePat);
				patient.setTelMobPat(telMobPat);
				patient.setTelFixePat(telFixePat);
				patient.setMdpPat(mdpPat);
				patient.setAdressePat(adressePat);
				patient.setEmailPat(emailPat);
				patient.setDateCreationPat(LocalDateTime.now().format(formatter));
				
				patient.setMedecin(medecin);
				
				//On appelle le service d'ajout de patient
				
				try {
					medecinService.ajouterPatient(patient);
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
						.entity("Le medecin  : " + idmedecin +" n'existe pas")
						.build();
					
		}
		
//supprimer le patient d'un medecin
//OK
		@POST
		@Path(value = "/patient/supprimer")
		public Response supprimerPatient(
			@QueryParam("idPat") long idPat
				
				
				
		) 
		{
				
			Patient patient = new Patient();
			
			patient= medecinService.obtenirUnPatient(idPat);
			// si le patient existe dans la base, je le supprimer
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
		@POST
		@Path(value = "/patient/modifier")
		public Response modifierPatient (
				@QueryParam("idPat") Long idPat,
				@QueryParam("nomPat") String nomPat,
				@QueryParam("prenomPat") String prenomPat,
				@QueryParam("dateNaissancePat") Date dateNaissancePat,
				@QueryParam("telMobPat") String telMobPat,
				@QueryParam("telFixePat") String telFixePat,
				@QueryParam("mdpPat") String mdpPat,
				@QueryParam("adressePat") String adressePat,
				@QueryParam("emailPat") String emailPat,
				@QueryParam("idmedecin") long idMedecin
				
		) 
		{
			Patient patient = new Patient();
			Medecin medecin = new Medecin();
			
			patient= medecinService.obtenirUnPatient(idPat);
			
			// je récupère le medecin du patient
			medecin = patientService.obtenirMedecinDunPatient(idMedecin);
			
			
			//si le medecin existe
			if( medecin != null){
					// si le patient existe, je le modifie
					if(patient != null){
						patient.setNomPat(nomPat);
						patient.setPrenomPat(prenomPat);
						patient.setDateNaissancePat(dateNaissancePat);
						patient.setTelMobPat(telMobPat);
						patient.setTelFixePat(telFixePat);
						patient.setMdpPat(mdpPat);
						patient.setAdressePat(adressePat);
						patient.setEmailPat(emailPat);
						patient.setMedecin(medecin);		
			
			
						medecinService.modifierPatient(patient);
						
						return Response.status(200)
								.entity("Le patient  : " + nomPat + ", age : " + prenomPat + " a été modifié")
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
