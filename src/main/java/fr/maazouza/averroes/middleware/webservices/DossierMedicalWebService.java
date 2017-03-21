package fr.maazouza.averroes.middleware.webservices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
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
	
	
	
	// Afficher la liste des Dossiers medicaux 
	//http://localhost:8080/AVERROES_MIDDLEWARE/ws/dossiermedical/
	// OK
				@GET
				@Produces(MediaType.APPLICATION_JSON)
				@Path(value = "/")
							
				public List<DossierMedical> obtenir() 
				{
								
						return dossierMedicalService.obtenir();

				}
				
		// Afficher la liste des Dossiers medicaux 
		//http://localhost:8080/AVERROES_MIDDLEWARE/ws/dossiermedical/
		// OK
				@GET
				@Produces(MediaType.APPLICATION_JSON)
				@Path(value = "/id")
										
				public DossierMedical obtenirUnDossier(
						@QueryParam("idPat") Long idPat
						) 
					{
											
						return dossierMedicalService.consulterUnDossierMedical(idPat);

				}
							
				
	
	// Ajouter un medecin à partir de l'interface medecin
	//OK
		@POST
		@Path(value = "/ajouter")
		public Response ajouterMedecin(
				@QueryParam("idPat") Long idPat,
				@QueryParam("numSecu") Long numSecu,
				@QueryParam("dateNaissance") String dateNaissance,
				@QueryParam("telMobPat") String telMobPat,
				@QueryParam("adressePat") String adressePat,
				@QueryParam("telFixePat") String telFixePat,
				@QueryParam("mutuelle") String mutuelle,
				@QueryParam("sexe") String sexe,
				@QueryParam("statutFamiliale") String statutFamiliale,
				@QueryParam("age") Integer age,
				@QueryParam("taille") Double taille,
				@QueryParam("poids") Double poids,
				@QueryParam("imc") Double imc,
				@QueryParam("groupeSanguin") String groupeSanguin,
				@QueryParam("donneurOrgane") Boolean donneurOrgane,
				@QueryParam("suivi") Boolean suivi,
				@QueryParam("contactFamille") String contactFamille,
				@QueryParam("telContactFamille") String telContactFamille
				
				
		) 
		{
		
					DossierMedical dossierMedical = new DossierMedical();
					
					//je récupère le patient pour lequel je veux créer un dossier medical
					Patient patient = medecinService.obtenirUnPatient(idPat);
					
					//je défini un format date de création
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					
					
					
					
					//j'initialise tous les champs
					dossierMedical.setPatient(patient);// j'affecte le patient
					dossierMedical.setNumSecu(numSecu);					
					dossierMedical.setAdressePat(adressePat);
					dossierMedical.setAge(age);
					dossierMedical.setContactFamille(telContactFamille);
					dossierMedical.setDateNaissancePat(dateNaissance);
					dossierMedical.setDonneurOrgane(donneurOrgane);
					dossierMedical.setDossierOuvert(true);//true à la création
					dossierMedical.setGroupeSanguin(groupeSanguin);
					dossierMedical.setImc(imc);
					dossierMedical.setMutuelle(mutuelle);
					dossierMedical.setNumSecu(numSecu);
					dossierMedical.setPoids(poids);
					dossierMedical.setSexe(sexe);
					dossierMedical.setSexe(sexe);
					dossierMedical.setStatutFamiliale(statutFamiliale);
					dossierMedical.setSuivi(suivi);
					dossierMedical.setTaille(taille);
					dossierMedical.setTelContactFamille(telContactFamille);
					dossierMedical.setTelFixePat(telFixePat);
					dossierMedical.setTelMobPat(telMobPat);
					dossierMedical.setDateCreationDos(LocalDateTime.now().format(formatter));;
					
					
					
					//On appelle le service d'ajout de dossier
				
					
					 dossierMedicalService.ajouterDossierMedical(dossierMedical);
						return Response.status(200)
								.entity("Le dossier du patient  :" + patient.getNomPat() +" "+ patient.getPrenomPat() + " a été ajouté")
								.build();
					
						
					}
					
					
		
	}


