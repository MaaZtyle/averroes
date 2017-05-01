package fr.maazouza.averroes.middleware.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

import fr.maazouza.averroes.middleware.dao.MaladieDao;
import fr.maazouza.averroes.middleware.objetmetier.allergie.Allergie;
import fr.maazouza.averroes.middleware.objetmetier.allergie.AllergieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.antecedent.Antecedent;
import fr.maazouza.averroes.middleware.objetmetier.antecedent.AntecedentInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedical;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalAvecAllergieException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalAvecMaladieException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalAvecOrdonnanceException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalAvecVaccinException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.dossierMedical.DossierMedicalInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
import fr.maazouza.averroes.middleware.objetmetier.maladie.MaladieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.medicament.Medicament;
import fr.maazouza.averroes.middleware.objetmetier.medicament.MedicamentInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.Ordonnance;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.OrdonnanceInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.vaccin.Vaccin;
import fr.maazouza.averroes.middleware.objetmetier.vaccin.VaccinInexistantException;
import fr.maazouza.averroes.middleware.services.IAllergieService;
import fr.maazouza.averroes.middleware.services.IAntecedentService;
import fr.maazouza.averroes.middleware.services.IDossierMedicalService;
import fr.maazouza.averroes.middleware.services.IMaladieService;
import fr.maazouza.averroes.middleware.services.IMedecinService;
import fr.maazouza.averroes.middleware.services.IMedicamentService;
import fr.maazouza.averroes.middleware.services.IOrdonnanceService;
import fr.maazouza.averroes.middleware.services.IPatientService;
import fr.maazouza.averroes.middleware.services.IVaccinService;
import fr.maazouza.averroes.middleware.services.MedicamentService;

@WebService
@Path(value = "/dossiermedical/")
public class DossierMedicalWebService {

	@EJB
	IPatientService patientService;

	@EJB
	IMedecinService medecinService;

	@EJB
	IDossierMedicalService dossierMedicalService;

	@EJB
	IMaladieService maladieService;

	@EJB
	IAllergieService allergieService;

	@EJB
	IOrdonnanceService ordonnanceService;

	@EJB
	IMedicamentService medicamentService;

	@EJB
	IAntecedentService antecedentService;

	@EJB
	IVaccinService vaccinService;

	/////////////////////////////////////////////////////////////////////////

	/*
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Path(value = "/") public List<Patient> obtenirPatients(
	 * 
	 * ) {
	 * 
	 * return patientService.obtenirPatients();
	 * 
	 * }
	 */

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	/////////////////////////// DOSSIER MEDICAL/////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	// Afficher la liste des Dossiers medicaux
	// http://localhost:8080/AVERROES_MIDDLEWARE/ws/dossiermedical/
	// OK
	@GET
	@Secured({ Role.medecin }) // que les medecins ont le droit
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/")

	public List<DossierMedical> obtenir(@Context SecurityContext securityContext) {

		return dossierMedicalService.obtenir();

	}

	
	// Afficher un dossier medical d'un patient par son id
	// http://localhost:8080/AVERROES_MIDDLEWARE/ws/dossiermedical/
	// OK
	@GET
	@Secured({ Role.medecin }) // que les medecins ont le droit
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/id")

	public DossierMedical consulterUnDossierMedical(@Context SecurityContext securityContext,
	@QueryParam("idPat") Long idPat){

		return dossierMedicalService.consulterUnDossierMedical(idPat);

	}
	

	// Ajouter un dossier medical à partir de l'interface medecin
	// http://localhost:8080/AVERROES_MIDDLEWARE/ws/dossiermedical/ajouter
	// OK
	@POST
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/")
	public Response ajouterDossier(@Context SecurityContext securityContext,
			@FormParam("idPat") Long idPat, 
			@FormParam("numSecu") Long numSecu,
			@FormParam("dateNaissance") String dateNaissance, 
			@FormParam("telMobPat") String telMobPat,
			@FormParam("adressePat") String adressePat, 
			@FormParam("telFixePat") String telFixePat,
			@FormParam("mutuelle") String mutuelle, 
			@FormParam("sexe") String sexe,
			@FormParam("statutFamiliale") String statutFamiliale, 
			@FormParam("age") Integer age,
			@FormParam("taille") Double taille, 
			@FormParam("poids") Double poids, 
			@FormParam("imc") Double imc,
			@FormParam("groupeSanguin") String groupeSanguin, 
			@FormParam("donneurOrgane") Boolean donneurOrgane,
			@FormParam("suivi") Boolean suivi, 
			@FormParam("contactFamille") String contactFamille,
			@FormParam("telContactFamille") String telContactFamille

	) {

		DossierMedical dossierMedical = new DossierMedical();
		Patient patient = new Patient();

		// je récupère le patient pour lequel je veux créer un dossier medical
		try {
			patient = patientService.obtenirUnPatient(idPat);

		} catch (PatientInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

		// je défini un format date de création
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// j'initialise tous les champs
		dossierMedical.setPatient(patient);// j'affecte le patient
		dossierMedical.setNumSecu(numSecu);
		dossierMedical.setAdressePat(adressePat);
		dossierMedical.setAge(age);
		dossierMedical.setContactFamille(telContactFamille);
		dossierMedical.setDateNaissancePat(dateNaissance);
		dossierMedical.setDonneurOrgane(donneurOrgane);
		dossierMedical.setDossierOuvert(true);// true à la création
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
		dossierMedical.setDateCreationDos(LocalDateTime.now().format(formatter));

		// On appelle le service d'ajout de dossier
		try {

			dossierMedicalService.ajouterDossierMedical(dossierMedical);

			return Response.status(200).entity("Le dossier du patient  :" + dossierMedical.getPatient().getNomPat()
					+ " " + dossierMedical.getPatient().getPrenomPat() + " a été ajouté").build();

		} catch (DossierMedicalDejaExistantException e) {
			return Response.status(200).entity(e.getMessage()).build();

		}

	}

	// Modifier les infos du dossier medical à partir de l'interface medecin,
	// avec son ID
	// OK
	@PUT
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/")
	public Response modifierDossier(@Context SecurityContext securityContext,
			@FormParam("idDos") Long idDos, 
			@FormParam("numSecu") Long numSecu,
			@FormParam("dateNaissance") String dateNaissance, 
			@FormParam("telMobPat") String telMobPat,
			@FormParam("adressePat") String adressePat, 
			@FormParam("telFixePat") String telFixePat,
			@FormParam("mutuelle") String mutuelle, 
			@FormParam("sexe") String sexe,
			@FormParam("statutFamiliale") String statutFamiliale,
			@FormParam("age") Integer age,
			@FormParam("taille") Double taille, 
			@FormParam("poids") Double poids, 
			@FormParam("imc") Double imc,
			@FormParam("groupeSanguin") String groupeSanguin,
			@FormParam("donneurOrgane") Boolean donneurOrgane,
			@FormParam("suivi") Boolean suivi, 
			@FormParam("contactFamille") String contactFamille,
			@FormParam("telContactFamille") String telContactFamille

	) {

		DossierMedical dossierMedical = new DossierMedical();
		// je récupère mon dossier

		Patient patient = new Patient();

		try {

			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);
			patient = patientService.obtenirUnPatient(dossierMedical.getPatient().getIdPat());
			// je défini un format date de création
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			// j'initialise tous les champs
			// dossierMedical.setIdDos(idDos);
			dossierMedical.setPatient(patient);
			dossierMedical.setNumSecu(numSecu);
			dossierMedical.setAdressePat(adressePat);
			dossierMedical.setAge(age);
			dossierMedical.setContactFamille(telContactFamille);
			dossierMedical.setDateNaissancePat(dateNaissance);
			dossierMedical.setDonneurOrgane(donneurOrgane);
			dossierMedical.setDossierOuvert(true);// true à la création
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
			dossierMedical.setDateCreationDos(LocalDateTime.now().format(formatter));
			;

			// On appelle le service de modification de dossier

			dossierMedicalService.modifierDossierMedical(dossierMedical);
			return Response.status(200).entity("Le dossier du patient :" + dossierMedical.getPatient().getNomPat()
					+ " ID " + dossierMedical.getIdDos() + " a été modifié").build();

		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

		catch (PatientInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	// Supprimer un dossier medical

	// Modifier les infos du dossier medical à partir de l'interface medecin,
	// avec son ID
	// OK
	@DELETE
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/")
	public Response supprimerUnDossierMedical(@Context SecurityContext securityContext,
			@FormParam("idDos") Long idDos

	) {

		DossierMedical dossierMedical = new DossierMedical();
		// je récupère mon dossier
		try {
			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

		// il faut tester la présense d'autres données avant de supprimer
		// vérifier les contraintes d'intégrités

		// On appelle le service de suppression de dossier

		try {
			dossierMedicalService.supprimerUnDossierMedical(idDos);
		} catch (DossierMedicalAvecMaladieException e) {
			return Response.status(200).entity(e.getMessage()).build();
		} catch (DossierMedicalAvecAllergieException e) {
			return Response.status(200).entity(e.getMessage()).build();
		} catch (DossierMedicalAvecOrdonnanceException e) {
			return Response.status(200).entity(e.getMessage()).build();
		} catch (DossierMedicalAvecVaccinException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

		return Response.status(200).entity("Le dossier du patient :" + dossierMedical.getPatient().getNomPat() + " ID "
				+ dossierMedical.getIdDos() + " a été supprimé").build();

	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	/////////////////////////// LES AUTRES TABLES///////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////
	/////////////////////////// MALADIES///////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////

	// Pas besoin de faire consulter, car ells sont déjà toutes visible sur le
	// dossier medical
	// Ajouter une maladie sur un dossier medical
	// Ajouter un dossier medical à partir de l'interface medecin
	// OK
	@POST
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/maladie")
	public Response ajouterMaladie(@Context SecurityContext securityContext,
			@FormParam("idDos") Long idDos, 
			@FormParam("designationMal") String designationMal,
			@FormParam("descriptionMal") String descriptionMal, 
			@FormParam("dateAppMal") String dateAppMal) {

		Maladie maladie = new Maladie();
		DossierMedical dossierMedical = new DossierMedical();

		try {
			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

		// je défini un format date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		maladie.setDateCreationMal(LocalDateTime.now().format(formatter));
		maladie.setDateAppMal(dateAppMal);
		maladie.setDescriptionMal(descriptionMal);
		maladie.setDesignationMal(designationMal);
		// Je l'affecte au dossier medical
		maladie.setDossierMedical(dossierMedical);

		maladieService.ajouterMaladie(maladie);
		return Response.status(200).entity("La maladie   :" + designationMal + " " + descriptionMal
				+ " a été ajouté au dossier " + dossierMedical.getIdDos()).build();

	}

	// Afficher la maladie d'un patient, à partir de son dossier
	// ok
	@GET
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/maladie")
	@Produces(MediaType.APPLICATION_JSON)
	public Maladie obtenirMaladie(@Context SecurityContext securityContext,
			@FormParam("idMal") Long idMal)

	{

		Maladie maladie = new Maladie();
		try {
			maladie = maladieService.obtenirMaladie(idMal);
		} catch (MaladieInexistanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maladie;

	}

	// Modifier une maladie d'un dossier medical d'un patient
	// ok
	@PUT
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/maladie")
	public Response modifierMaladie(@Context SecurityContext securityContext,
			@FormParam("idMal") Long idMal, 
			@FormParam("idDos") Long idDos,
			@FormParam("designationMal") String designationMal, 
			@FormParam("descriptionMal") String descriptionMal,
			@FormParam("dateAppMal") String dateAppMal) {

		try {
			Maladie maladie = new Maladie();
			maladie = maladieService.obtenirMaladie(idMal);

			// je récupère le dossier du patient
			DossierMedical dossierMedical;

			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);

			// Je modifie tous les champs
			// maladie.setDossierMedical(dossierMedical);
			maladie.setDateAppMal(dateAppMal);
			maladie.setDescriptionMal(descriptionMal);
			maladie.setDesignationMal(designationMal);

			maladieService.modifierMaladie(maladie);

			return Response.status(200).entity("La maladie   " + idMal + " a été modifiée ").build();

		}

		catch (MaladieInexistanteException e) {
			return Response.status(200).entity(e.getMessage()).build();
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	// Supprimer une maladie d'un dossier medical d'un patient
	@DELETE
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/maladie")
	public Response supprimerMaladie(@Context SecurityContext securityContext,
			@FormParam("idMal") Long idMal)

	{

		try {
			maladieService.supprimerMaladie(idMal);

			return Response.status(200).entity("La maladie   " + idMal + " a été supprimée au dossier ").build();
		}

		catch (MaladieInexistanteException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////// MALADIES///////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////// 
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////
	/////////////////////////// ALLERGIE///////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////

	// Pas besoin de faire consulter, car ells sont déjà toutes visible sur le
	// dossier medical
	// Ajouter une allergie sur un dossier medical
	// Ajouter un dossier medical à partir de l'interface medecin
	// OK
	@POST
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/allergie")
	public Response ajouterAllergie(@Context SecurityContext securityContext,
			@FormParam("idDos") Long idDos,
			@FormParam("designationAll") String designationAll, 
			@FormParam("descriptionAll") String descriptionAll,
			@FormParam("dateAppAll") String dateAppAll, 
			@FormParam("etatAll") Boolean etatAll) {

		Allergie allergie = new Allergie();
		DossierMedical dossierMedical = new DossierMedical();

		try {
			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

		// je défini un format date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		allergie.setDateCreationAll(LocalDateTime.now().format(formatter));
		allergie.setDateAppAll(dateAppAll);
		allergie.setDescriptionAll(descriptionAll);
		allergie.setDesignationAll(designationAll);
		allergie.setEtatAll(etatAll);
		// Je l'affecte au dossier medical
		allergie.setDossierMedical(dossierMedical);

		allergieService.ajouterAllergie(allergie);
		return Response.status(200).entity("L'allergie   :" + designationAll + " " + descriptionAll
				+ " a été ajouté au dossier " + dossierMedical.getIdDos()).build();

	}

	// Afficher l'allergie d'un patient, à partir de son dossier
	// ok
	@GET
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/allergie")
	@Produces(MediaType.APPLICATION_JSON)
	public Allergie obtenirAllergie(@Context SecurityContext securityContext,
			@FormParam("idAll") Long idAll)

	{

		Allergie allergie = new Allergie();
		try {
			allergie = allergieService.obtenirAllergie(idAll);
		} catch (AllergieInexistanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allergie;

	}

	// Modifier une maladie d'un dossier medical d'un patient
	// ok
	@PUT
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/allergie")
	public Response modifierAllergie(@Context SecurityContext securityContext,
			@FormParam("idAll") Long idAll, 
			@FormParam("idDos") Long idDos,
			@FormParam("designationAll") String designationAll, 
			@FormParam("descriptionAll") String descriptionAll,
			@FormParam("dateAppAll") String dateAppAll, 
			@FormParam("etatAll") Boolean etatAll) {

		try {
			Allergie allergie = new Allergie();
			allergie = allergieService.obtenirAllergie(idAll);

			// je récupère le dossier du patient
			DossierMedical dossierMedical;

			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);

			// Je modifie tous les champs
			// maladie.setDossierMedical(dossierMedical);
			allergie.setDateAppAll(dateAppAll);
			allergie.setDescriptionAll(descriptionAll);
			allergie.setDesignationAll(designationAll);
			allergie.setEtatAll(etatAll);

			allergieService.modifierAllergie(allergie);

			return Response.status(200).entity("l'allergie   " + idAll + " a été modifiée ").build();

		}

		catch (AllergieInexistanteException e) {
			return Response.status(200).entity(e.getMessage()).build();
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	// Supprimer une allergie d'un dossier medical d'un patient
	@DELETE
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/allergie")
	public Response supprimerAllergie(@Context SecurityContext securityContext,
			@FormParam("idAll") Long idAll)

	{

		try {
			allergieService.supprimerAllergie(idAll);

			return Response.status(200).entity("L'allergie   " + idAll + " a été supprimée au dossier ").build();
		}

		catch (AllergieInexistanteException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////// ALLERGIE//////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////// 
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////
	/////////////////////////// ORDONNANCE///////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////

	// Pas besoin de faire consulter, car ells sont déjà toutes visible sur le
	// dossier medical
	// Ajouter une maladie sur un dossier medical
	// Ajouter un dossier medical à partir de l'interface medecin
	// OK
	@POST
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/ordonnance/")
	public Response ajouterOrdonnance(@Context SecurityContext securityContext,
			@FormParam("idDos") Long idDos, 
			@FormParam("dateOrd") String dateOrd) {

		Ordonnance ordonnance = new Ordonnance();
		DossierMedical dossierMedical = new DossierMedical();

		// je défini un format date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ordonnance.setDateCreationOrd(LocalDateTime.now().format(formatter));
		ordonnance.setDateOrd(dateOrd);

		try {
			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

		// Je l'affecte au dossier medical
		ordonnance.setDossierMedical(dossierMedical);

		ordonnanceService.ajouterOrdonnance(ordonnance);
		return Response.status(200).entity("L'ordonnance a été ajouté au dossier ").build();

	}

	// Afficher l'ordonnance d'un patient, à partir de son dossier
	// ok
	@GET
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/ordonnance")
	@Produces(MediaType.APPLICATION_JSON)
	public Ordonnance obtenirOrdonnance(@Context SecurityContext securityContext,
			@FormParam("idOrd") Long idOrd)

	{

		Ordonnance ordonnance = new Ordonnance();
		try {
			ordonnance = ordonnanceService.obtenirOrdonnance(idOrd);
		} catch (OrdonnanceInexistanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ordonnance;

	}

	// Modifier une ordonnance d'un dossier medical d'un patient
	// ok
	@PUT
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/ordonnance")
	public Response modifierOrdonnance(@Context SecurityContext securityContext,
			@FormParam("idOrd") Long idOrd, 
			@FormParam("idDos") Long idDos,
			@FormParam("dateOrd") String dateOrd) {
		Ordonnance ordonnance = new Ordonnance();
		DossierMedical dossierMedical = new DossierMedical();

		if ((idOrd == null) || (idDos == null))
			return Response.status(200).entity("il faut l'idDos et idOrd").build();

		try {

			ordonnance = ordonnanceService.obtenirOrdonnance(idOrd);

			// je récupère le dossier du patient

			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);

			// Je modifie tous les champs
			// maladie.setDossierMedical(dossierMedical);
			ordonnance.setDateOrd(dateOrd);

			ordonnanceService.modifierOrdonnance(ordonnance);

			return Response.status(200).entity("l'Ordonnance  " + idOrd + " a été modifiée ").build();

		}

		catch (OrdonnanceInexistanteException e) {
			return Response.status(200).entity(e.getMessage()).build();
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	// Supprimer une ordonnance d'un dossier medical d'un patient
	@DELETE
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/ordonnance")
	public Response supprimerOrdonnance(@Context SecurityContext securityContext,
			@FormParam("idOrd") Long idOrd)

	{

		try {
			ordonnanceService.supprimerOrdonnance(idOrd);

			return Response.status(200).entity("L'ordonnance   " + idOrd + " a été supprimée au dossier ").build();
		}

		catch (OrdonnanceInexistanteException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////// ORDONNANCE////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////// MEDICAMENT/////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

	// Appel du WS de base des medicaments
	// Ajout d'un medicament sur une ordonnance
	@GET
	@Secured({ Role.medecin }) // medecins ont le droit
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/ordonnance/medicament/")
	public String rechercheMedicament(@Context SecurityContext securityContext,
			@FormParam("nomMed") String nomMed

	) {

		String medocs = null;

		try {

			URL url = new URL("https://www.open-medicaments.fr/api/v1/medicaments?query=" + nomMed);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			medocs = br.readLine();

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e1) {

			e1.printStackTrace();

		}
		return medocs;

	}

	// pour avoir toutes les infos d'un medicament
	@GET
	@Secured({ Role.medecin }) // medecins ont le droit
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/ordonnance/medicament/information")
	public String InformationMedicament(@Context SecurityContext securityContext,
			@FormParam("codeCIS") String codeCIS

	) {

		String info = null;

		try {

			URL url = new URL("https://www.open-medicaments.fr/api/v1/medicaments/" + codeCIS);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			info = br.readLine();

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e1) {

			e1.printStackTrace();

		}
		return info;

	}

	//////////////////////////////////////////////////////////////////////////
	/////////////////////////// CRUD MEDICAMENT///////////////////////////////
	//////////////////////////////////////////////////////////////////////////

	// Pas besoin de faire consulter, car ells sont déjà toutes visible sur le
	// dossier medical
	// Ajouter une maladie sur un dossier medical
	// Ajouter un dossier medical à partir de l'interface medecin
	// OK
	@POST
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/ordonnance/medicament")
	public Response ajouterMedicament(@Context SecurityContext securityContext,
			@FormParam("idOrd") Long idOrd, 
			@FormParam("codeCis") String codeCis,
			@FormParam("denomination") String denomination, 
			@FormParam("posologie") String posologie,
			@FormParam("quantite") Integer quantite) {

		Ordonnance ordonnance = new Ordonnance();
		Medicament medicament = new Medicament();

		if (codeCis == null)
			return Response.status(200).entity("Merci de renseigner le code CIS").build();

		try {
			ordonnance = ordonnanceService.obtenirOrdonnance(idOrd);
		} catch (OrdonnanceInexistanteException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

		// je défini un format date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		medicament.setDateCreationMed(LocalDateTime.now().format(formatter));
		medicament.setCodeCIS(codeCis);
		medicament.setDenomination(denomination);
		medicament.setPosologie(posologie);
		medicament.setQuantite(quantite);

		// Je les medicaments à l'ordonnace
		medicament.setOrdonnance(ordonnance);
		;

		medicamentService.ajouterMedicament(medicament);
		return Response.status(200).entity("Le medicament   :" + denomination + " a été ajouté à l'ordonnance ")
				.build();

	}

	// Afficher le medicmanet d'un patient
	// ok
	@GET
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/ordonnance/medicament")
	@Produces(MediaType.APPLICATION_JSON)
	public Medicament obtenirMedicament(@Context SecurityContext securityContext,
			@FormParam("codeCis") String codeCis)

	{

		Medicament medicament = new Medicament();
		try {
			medicament = medicamentService.obtenirMedicament(codeCis);
		} catch (MedicamentInexistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medicament;

	}

	// Modifier un medicament sur une ordonnance
	// ok
	@PUT
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/ordonnance/medicament")
	public Response modifierMedicament(@Context SecurityContext securityContext,
			@FormParam("idOrd") Long idOrd, 
			@FormParam("codeCis") String codeCis,
			@FormParam("denomination") String denomination, 
			@FormParam("posologie") String posologie,
			@FormParam("quantite") Integer quantite) {

		try {
			Medicament medicament = new Medicament();
			medicament = medicamentService.obtenirMedicament(codeCis);

			// je récupère l'ordonnance
			Ordonnance ordonnance;

			ordonnance = ordonnanceService.obtenirOrdonnance(idOrd);

			// Je modifie tous les champs
			// maladie.setDossierMedical(dossierMedical);
			medicament.setCodeCIS(codeCis);
			medicament.setDenomination(denomination);
			medicament.setPosologie(posologie);
			medicament.setQuantite(quantite);
			medicament.setOrdonnance(ordonnance);

			medicamentService.modifierMedicament(medicament);

			return Response.status(200).entity("Le medicament   " + codeCis + " a été modifiée ").build();

		}

		catch (MedicamentInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		} catch (OrdonnanceInexistanteException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	// Supprimer un medicament d'une ordonnance
	@DELETE
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/ordonnance/medicament")
	public Response supprimerMedicament(@Context SecurityContext securityContext,
			@FormParam("codeCis") String codeCis)

	{

		try {
			medicamentService.supprimerMedicament(codeCis);

			return Response.status(200).entity("Le medicament   " + codeCis + " a été supprimée ").build();
		}

		catch (MedicamentInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}
	//////////////////////////////////////////////////////////////////////////
	/////////////////////////// ANTECEDENT///////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////

	// Pas besoin de faire consulter, car ells sont déjà toutes visible sur le
	// dossier medical
	// Ajouter un antécédent sur un dossier medical
	// Ajouter un dossier medical à partir de l'interface medecin
	// OK
	@POST
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/antecedent/")
	public Response ajouterAntecedent(@Context SecurityContext securityContext,
			@FormParam("idDos") Long idDos, 
			@FormParam("dateAnt") String dateAnt,
			@FormParam("descriptionAnt") String descriptionAnt, 
			@FormParam("commentaireAnt") String commentaireAnt,
			@FormParam("sujetAnt") String sujetAnt) {

		Antecedent antecedent = new Antecedent();
		DossierMedical dossierMedical = new DossierMedical();

		// je défini un format date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		antecedent.setDateCreationAnt(LocalDateTime.now().format(formatter));
		antecedent.setDateAnt(dateAnt);
		antecedent.setDescriptionAnt(descriptionAnt);
		antecedent.setCommentaireAnt(commentaireAnt);
		antecedent.setSujetAnt(sujetAnt);

		try {
			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

		// Je l'affecte au dossier medical
		antecedent.setDossierMedical(dossierMedical);

		antecedentService.ajouterAntecedent(antecedent);
		return Response.status(200).entity("L'antecedent a été ajouté au dossier ").build();

	}

	// Afficher l'antecedent d'un patient, à partir de son dossier
	// ok
	@GET
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/antecedent")
	@Produces(MediaType.APPLICATION_JSON)
	public Antecedent obtenirAntecedent(@Context SecurityContext securityContext,
			@FormParam("idAnt") Long idAnt)

	{

		Antecedent antecedent = new Antecedent();
		try {
			antecedent = antecedentService.obtenirAntecedent(idAnt);
		} catch (AntecedentInexistantException e) {
			e.printStackTrace();
		}
		return antecedent;

	}

	// Modifier un Antecedent d'un dossier medical d'un patient
	// ok
	@PUT
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/antecedent")
	public Response modifierAntecedent(@Context SecurityContext securityContext,
			@FormParam("idDos") Long idDos, 
			@FormParam("idAnt") Long idAnt,
			@FormParam("dateAnt") String dateAnt, 
			@FormParam("descriptionAnt") String descriptionAnt,
			@FormParam("commentaireAnt") String commentaireAnt, 
			@FormParam("sujetAnt") String sujetAnt) {

		Antecedent antecedent = new Antecedent();
		DossierMedical dossierMedical = new DossierMedical();

		if ((idAnt.equals(null)) || (idDos.equals(null)))
			return Response.status(200).entity("il faut l'idDos et idOrd").build();

		// marche pas

		try {

			antecedent = antecedentService.obtenirAntecedent(idAnt);

			// je récupère le dossier du patient

			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);

			// Je modifie tous les champs
			// maladie.setDossierMedical(dossierMedical);

			antecedent.setDateAnt(dateAnt);
			antecedent.setDescriptionAnt(descriptionAnt);
			antecedent.setDescriptionAnt(commentaireAnt);
			antecedent.setSujetAnt(sujetAnt);

			antecedentService.modifierAntecedent(antecedent);

			return Response.status(200).entity("l'Antecedent  " + idAnt + " a été modifiée ").build();

		}

		catch (AntecedentInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	// Supprimer une ordonnance d'un dossier medical d'un patient
	@DELETE
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/antecedent")
	public Response supprimerAntecedent(@Context SecurityContext securityContext,
			@FormParam("idAnt") Long idAnt)

	{

		try {
			antecedentService.supprimerAntecedent(idAnt);

			return Response.status(200).entity("L'antecedent   " + idAnt + " a été supprimée au dossier ").build();
		}

		catch (AntecedentInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////// ANTECEDENT/////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////// 
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////
	/////////////////////////// VACCIN///////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////

	// Pas besoin de faire consulter, car ells sont déjà toutes visible sur le
	// dossier medical
	// Ajouter une maladie sur un dossier medical
	// Ajouter un dossier medical à partir de l'interface medecin
	// OK
	@POST
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/vaccin/")
	public Response ajouterVaccin(@Context SecurityContext securityContext,
			@FormParam("idDos") Long idDos, 
			@FormParam("nomVac") String nomVac,
			@FormParam("descriptionVac") String descriptionVac,
			@FormParam("dateDernierVac") String dateDernierVac,
			@FormParam("dateProchainVac") String dateProchainVac,
			@FormParam("alertePatientVac") Boolean alertePatientVac,
			@FormParam("alerteMedecinVac") Boolean alerteMedecinVac

	) {

		Vaccin vaccin = new Vaccin();
		DossierMedical dossierMedical = new DossierMedical();

		// je défini un format date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		vaccin.setDateCreationVac(LocalDateTime.now().format(formatter));
		vaccin.setNomVac(nomVac);
		vaccin.setDescriptionVac(descriptionVac);
		vaccin.setDateDernierVac(dateDernierVac);
		vaccin.setDateProchainVac(dateProchainVac);
		vaccin.setAlertePatientVac(alertePatientVac);
		vaccin.setAlerteMedecinVac(alerteMedecinVac);
		try {
			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

		// Je l'affecte au dossier medical
		vaccin.setDossierMedical(dossierMedical);

		vaccinService.ajouterVaccin(vaccin);
		return Response.status(200).entity("vaccin a été ajouté au dossier ").build();

	}

	// Afficher le vaccin d'un patient, à partir de son dossier
	// ok
	@GET
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/vaccin")
	@Produces(MediaType.APPLICATION_JSON)
	public Vaccin obtenirVaccin(@Context SecurityContext securityContext,
			@FormParam("idVac") Long idVac)

	{

		Vaccin vaccin = new Vaccin();
		try {
			vaccin = vaccinService.obtenirVaccin(idVac);
		} catch (VaccinInexistantException e) {
			e.printStackTrace();
		}
		return vaccin;

	}

	// Modifier un vaccin d'un dossier medical d'un patient
	// ok
	@PUT
	@Secured({ Role.medecin }) // medecins ont le droit
	@Path(value = "/vaccin")
	public Response modifierVaccin(@Context SecurityContext securityContext,
			@FormParam("idDos") Long idDos,
			@FormParam("idVac") Long idVac,
			@FormParam("nomVac") String nomVac,
			@FormParam("descriptionVac") String descriptionVac,
			@FormParam("dateDernierVac") String dateDernierVac,
			@FormParam("dateProchainVac") String dateProchainVac,
			@FormParam("alertePatientVac") Boolean alertePatientVac,
			@FormParam("alerteMedecinVac") Boolean alerteMedecinVac) {

		Vaccin vaccin = new Vaccin();
		DossierMedical dossierMedical = new DossierMedical();

		try {

			vaccin = vaccinService.obtenirVaccin(idVac);

			// je récupère le dossier du patient

			dossierMedical = dossierMedicalService.obtenirUnDossierMedical(idDos);

			// Je modifie tous les champs
			// maladie.setDossierMedical(dossierMedical);

			vaccin.setNomVac(nomVac);
			vaccin.setDescriptionVac(descriptionVac);
			vaccin.setDateDernierVac(dateDernierVac);
			vaccin.setDateProchainVac(dateProchainVac);
			vaccin.setAlertePatientVac(alertePatientVac);
			vaccin.setAlerteMedecinVac(alerteMedecinVac);

			vaccinService.modifierVaccin(vaccin);

			return Response.status(200).entity("Le vaccin  " + idVac + " a été modifiée ").build();

		}

		catch (VaccinInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		} catch (DossierMedicalInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	// Supprimer un vaccin d'un dossier medical d'un patient
	@DELETE
	@Secured({ Role.medecin, Role.patient }) // patients et medecins ont le
												// droit
	@Path(value = "/vaccin")
	public Response supprimerVaccin(@Context SecurityContext securityContext,
			@FormParam("idVac") Long idVac)

	{

		try {
			vaccinService.supprimerVaccin(idVac);

			return Response.status(200).entity("Le vaccin   " + idVac + " a été supprimée au dossier ").build();
		}

		catch (VaccinInexistantException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	}

	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////// VACCIN/////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////// 
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

}

//// Il manque messages, rendez vous et remarques