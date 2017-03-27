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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medicament.Medicament;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
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
	
	//Afficher la liste des patients
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/medicament/nom")
	public String obtenirMedicament( 
			@QueryParam("nomMed") String nomMed
			
	) 
	{
		
		String medocs = null;
		
		try {

			URL url = new URL("https://www.open-medicaments.fr/api/v1/medicaments?query="+nomMed);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			
			medocs = br.readLine();
			

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e1) {

			e1.printStackTrace();

		  }
		return medocs;

	}
	
	////////////////
	
	//test
	
	//Afficher la liste des patients
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path(value = "/")
		public List<Patient> obtenirPatients( 
				
		) 
		{
			
			return patientService.obtenirPatients();

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


