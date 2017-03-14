/**
 * 
 */
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
	
	
	//Afficher les patients par le nom ou prénom
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/{predicat}")
	public List<Patient> obtenirPatients( 
			@PathParam("predicat") String predicat
	) 
	{
		
		return patientService.obtenirPatients(predicat);

	}
	
	//ajouter un patient à partir de l'interface medecin, avec son id medecin
	@POST
	@Path(value = "/ajouter")
	public Response ajouterPatient(
			@QueryParam("nom_pat") String nom_pat,
			@QueryParam("prenom_pat") String prenom_pat,
			@QueryParam("dateNaissance_pat") Date dateNaissance_pat,
			@QueryParam("tel_mob_pat") String tel_mob_pat,
			@QueryParam("tel_fixe_pat") String tel_fixe_pat,
			@QueryParam("mdp_pat") String mdp_pat,
			@QueryParam("adresse_pat") String adresse_pat,
			@QueryParam("email_pat") String email_pat,
			@QueryParam("idmedecin") long idmedecin
			
			
	) 
	{
		//on créé un nouveau patient, et on lui affecte les valeurs, et le medecin
		Patient patient = new Patient();
		
		//pour l'instant je créé un medecin, mais après il sera dejà dans la base
		// je devrais juste récupérer son id et l'insérer comme un champs normal
		Medecin medecin = new Medecin();
		
		//medecin.setNom_med("Docteur Jordan");
		medecin.setId_med(idmedecin);
		
		patient.setNom_pat(nom_pat);
		patient.setPrenom_pat(prenom_pat);
		patient.setDateNaissance_pat(dateNaissance_pat);
		patient.setTel_mob_pat(tel_mob_pat);
		patient.setTel_fixe_pat(tel_fixe_pat);
		patient.setMdp_pat(mdp_pat);
		patient.setAdresse_pat(adresse_pat);
		patient.setEmail_pat(email_pat);
		
		patient.setMedecin(medecin);
		
		//On appelle le service d'ajout de patient
	
		try {
			patientService.ajouterPatient(patient);
			return Response.status(200)
					.entity("Le patient  : " + nom_pat + ", Prenom : " + prenom_pat + " a été ajouté")
					.build();
		} catch (PatientDejaExistantException e) {
			
			return Response.status(200)
					.entity(e.getMessage())
					.build();
			
		}
		
		
		
	}
	
	
	
	@POST
	@Path(value = "/supprimer")
	public Response supprimerPatient(
			@QueryParam("nom") String nom_pat,
			@QueryParam("prenom") String prenom_pat,
			@QueryParam("idpatient") long idpatient
			
			
			
	) 
	{
		Patient patient = new Patient();
		
		patient.setNom_pat(nom_pat);
		patient.setPrenom_pat(prenom_pat);
		patient.setId_pat(idpatient);
		
		
		patientService.supprimerPatient(nom_pat);
		
		return Response.status(200)
				.entity("Le patient  : " + nom_pat + ", age : " + prenom_pat + " a été supprimé")
				.build();
		
	}
	
	
	
	
	@POST
	@Path(value = "/modifier")
	public Response modifierPatient (
			@QueryParam("nom_pat") String nom_pat,
			@QueryParam("prenom_pat") String prenom_pat,
			@QueryParam("idmedecin") long idMedecin
			
	) 
	{
		Patient patient = new Patient();
		
		patient= patientService.obtenirUnPatient(nom_pat,prenom_pat);
		if(patient != null){
		patient.setNom_pat(nom_pat);
		patient.setPrenom_pat(prenom_pat);
		// je récupère le medecin du patient
		patient.setMedecin(patientService.obtenirMedecinDunPatient(idMedecin));
		
		patientService.modifierPatient(patient);
		
		return Response.status(200)
				.entity("Le patient  : " + nom_pat + ", age : " + prenom_pat + " a été modifié")
				.build();
		}
		
		else{
		return Response.status(200)
				.entity("Le patient  : " + nom_pat + ", age : " + prenom_pat + " n'a pas été modifié")
				.build();
		
		}
		
		
	}

}


