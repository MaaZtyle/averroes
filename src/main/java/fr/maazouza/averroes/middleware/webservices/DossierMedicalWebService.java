package fr.maazouza.averroes.middleware.webservices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
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
	
	
	
	
	
///////////////////////////////////////////////////////////////////////////		
///////////////////////////////////////////////////////////////////////////				
///////////////////////////////////////////////////////////////////////////			
///////////////////////////DOSSIER MEDICAL/////////////////////////////////				
///////////////////////////////////////////////////////////////////////////			
///////////////////////////////////////////////////////////////////////////		
	
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
				
// Afficher un dossier medical par son ID
//http://localhost:8080/AVERROES_MIDDLEWARE/ws/dossiermedical/id
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
							
				
	
// Ajouter un dossier medical à partir de l'interface medecin
//http://localhost:8080/AVERROES_MIDDLEWARE/ws/dossiermedical/ajouter
//OK
		@POST
		@Path(value = "/ajouter")
		public Response ajouterDossier(
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
					Patient patient = new Patient();
					
					//je récupère le patient pour lequel je veux créer un dossier medical
					patient = patientService.obtenirUnPatient(idPat);
					
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
					dossierMedical.setPoids(poids);
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
								.entity("Le dossier du patient  :" + dossierMedical.getPatient().getNomPat()+" "+ dossierMedical.getPatient().getPrenomPat() + " a été ajouté")
								.build();
					
						
					}
		
		
		
// Modifier les infos du dossier medical à partir de l'interface medecin, avec son ID
//OK
			@PUT
			@Path(value = "/modifier")
			public Response modifierDossier(
					@QueryParam("idDos") Long idDos,
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
						// je récupère mon dossier
						dossierMedical = dossierMedicalService.obtenirUnDossier(idDos);
						
						//je défini un format date de création
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						//j'initialise tous les champs
						//dossierMedical.setIdDos(idDos);
						dossierMedical.setPatient(patientService.obtenirUnPatient(dossierMedical.getPatient().getIdPat()));
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
						dossierMedical.setPoids(poids);
						dossierMedical.setSexe(sexe);
						dossierMedical.setStatutFamiliale(statutFamiliale);
						dossierMedical.setSuivi(suivi);
						dossierMedical.setTaille(taille);
						dossierMedical.setTelContactFamille(telContactFamille);
						dossierMedical.setTelFixePat(telFixePat);
						dossierMedical.setTelMobPat(telMobPat);
						dossierMedical.setDateCreationDos(LocalDateTime.now().format(formatter));;
						
						
						
						//On appelle le service de modification de dossier
					
						
						 dossierMedicalService.modifierDossierMedical(dossierMedical);
							return Response.status(200)
									.entity("Le dossier du patient :" + dossierMedical.getPatient().getNomPat() +" ID "+ dossierMedical.getIdDos() + " a été modifié")
									.build();
						
							
						}
			
// Supprimer un dossier medical
			
			// Modifier les infos du dossier medical à partir de l'interface medecin, avec son ID
			//OK
				@DELETE
				@Path(value = "/supprimer")
				public Response supprimerUnDossierMedical(
						@QueryParam("idDos") Long idDos
								
								
								
				) 
				{
						
							DossierMedical dossierMedical = new DossierMedical();
							// je récupère mon dossier
							dossierMedical = dossierMedicalService.obtenirUnDossier(idDos);
									
							// il faut tester la présense d'autres données avant de supprimer
							// vérifier les contraintes d'intégrités
									
									
									
							//On appelle le service de suppression de dossier
								
									
							 dossierMedicalService.supprimerUnDossierMedical(idDos);
								return Response.status(200)
										.entity("Le dossier du patient :" + dossierMedical.getPatient().getNomPat() +" ID "+ dossierMedical.getIdDos() + " a été supprimé")
										.build();
									
										
							}	
		
		
///////////////////////////////////////////////////////////////////////////		
///////////////////////////////////////////////////////////////////////////				
///////////////////////////////////////////////////////////////////////////			
///////////////////////////LES AUTRES TABLES///////////////////////////////				
///////////////////////////////////////////////////////////////////////////			
///////////////////////////////////////////////////////////////////////////			
		
// Ajouter une maladie sur un dossier medical
		// Ajouter un dossier medical à partir de l'interface medecin
		//OK
			@POST
			@Path(value = "/maladie/ajouter")
			public Response ajouterMaladie(
					@QueryParam("idDos") Long idDos,
					@QueryParam("designationMal") String designationMal,
					@QueryParam("descriptionMal") String descriptionMal,
					@QueryParam("dateAppMal") String dateAppMal){
				
				
				
				Maladie maladie = new Maladie();
				DossierMedical dossierMedical = new DossierMedical();
				
				
				maladie.setDateAppMal(dateAppMal);
				maladie.setDescriptionMal(descriptionMal);
				maladie.setDesignationMal(designationMal);
				maladie.setDossierMedical(dossierMedical);
				
								
				 dossierMedicalService.ajouterMaladie(maladie);
					return Response.status(200)
							.entity("La maladie   :" + designationMal +" "+ descriptionMal + " a été ajouté au dossier "+ dossierMedical.getIdDos())
							.build();
				
				
				
				
			}
			
					
					
		
}


