/**
 * 
 */
package fr.maazouza.averroes.middleware.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.resource.spi.work.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medicament.Medicament;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;
import fr.maazouza.averroes.middleware.services.IDossierMedicalService;
import fr.maazouza.averroes.middleware.services.IPatientService;


/**
 * @author Maazouza
 *
 */

@WebService
@Path(value = "/patient")
public class PatientWebService {
	
	@EJB
	IPatientService patientService;
	
	@EJB
	IDossierMedicalService dossierMedicalService;
	
	// Afficher un medecin par Id
	//http://localhost:8080/AVERROES_MIDDLEWARE/ws/medecin/id/
	//OK
		@GET
		@Secured({Role.medecin,Role.patient})// que les medecins ont le droit
		@Produces(MediaType.APPLICATION_JSON)
		@Path(value = "/id")
		public Patient obtenirUnPatient( @Context SecurityContext securityContext,
				@QueryParam("idPat") Long idPat
				
		) 
		{
				
				
					try {
						return patientService.obtenirUnPatient(idPat);
					} catch (PatientInexistantException e) {
						return null;
					}
				
			
				}
		
		
	
	//Afficher la liste des patients
		@GET
		@Secured({Role.medecin})// que les medecins ont le droit
		@Produces(MediaType.APPLICATION_JSON)
		@Path(value = "/")
		public List<Patient> obtenirPatients( @Context SecurityContext securityContext
				
		) 
		{
			
			return patientService.obtenirPatients();

		}
	
		
		
		// Afficher un dossier medical par son id patient
		// http://localhost:8080/AVERROES_MIDDLEWARE/ws/dossiermedical/id
		// OK
		@GET
		@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
													// droit
		@Produces(MediaType.APPLICATION_JSON)
		@Path(value = "/dossierMedical")

		public DossierMedical obtenirUnDossier(@QueryParam("idPat") Long idPat) {

			return dossierMedicalService.consulterUnDossierMedical(idPat);

		}
	
	//Afficher un patient par le nom et prénom
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/{predicat}")
	public Patient obtenirUnPatient( 
			@QueryParam("nomPat") String nomPat,
			@QueryParam("prenomPat") String prenomPat
	) 
	{
		
		return medecinService.obtenirUnPatient(nomPat,prenomPat);

	}
	*/

	
	
	
	/*@POST
	@Path(value = "/modifier")
	public Response modifierPatient (
			@QueryParam("nomPat") String nomPat,
			@QueryParam("prenomPat") String prenomPat,
			@QueryParam("idmedecin") long idMedecin
			
	) 
	{
		Patient patient = new Patient();
		
		patient= patientService.obtenirUnPatient(nomPat,prenomPat);
		if(patient != null){
		patient.setNomPat(nomPat);
		patient.setPrenomPat(prenomPat);
		// je récupère le medecin du patient
		patient.setMedecin(patientService.obtenirMedecinDunPatient(idMedecin));
		
		patientService.modifierPatient(patient);
		
		return Response.status(200)
				.entity("Le patient  : " + nomPat + ", age : " + prenomPat + " a été modifié")
				.build();
		}
		
		else{
		return Response.status(200)
				.entity("Le patient  : " + nomPat + ", age : " + prenomPat + " n'a pas été modifié")
				.build();
		
		}
		
		
	}*/

}


