package fr.maazouza.averroes.middleware.webservices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.services.IDossierMedicalService;
import fr.maazouza.averroes.middleware.services.IMedecinService;
import fr.maazouza.averroes.middleware.services.IPatientService;

@WebService
@Path(value = "/dossiermedical/")
public class DossierMedicalWebService {
	
	@EJB 
	IPatientService patientService;
	
	
	@EJB 
	IMedecinService medecinService;
	
	@EJB 
	IDossierMedicalService dossierMedicalService;
	
	// Ajouter un medecin à partir de l'interface medecin
	//OK
		@POST
		@Path(value = "/ajouter")
		public Response ajouterMedecin(
				@QueryParam("idPat") Long idPat,
				@QueryParam("numSecu") Long numSecu 
				
		) 
		{
		
					DossierMedical dossierMedical = new DossierMedical();
					
					//je récupère le patient pour lequel je veux créer un dossier medical
					Patient patient = medecinService.obtenirUnPatient(idPat);
					
					//je défini un format date
					//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					
					//j'initialise tous les champs
					dossierMedical.setNumSecu(numSecu);
					dossierMedical.setPatient(patient);
					
					//On appelle le service d'ajout de dossier
				
					
					 dossierMedicalService.ajouterDossierMedical(dossierMedical);
						return Response.status(200)
								.entity("Le dossier  :" + numSecu + " a été ajouté")
								.build();
					
						
					}
					
					
		
	}


