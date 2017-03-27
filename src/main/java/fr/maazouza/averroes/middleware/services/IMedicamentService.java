package fr.maazouza.averroes.middleware.services;

import java.util.List;

import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinAvecPatientsException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.medicament.Medicament;
import fr.maazouza.averroes.middleware.objetmetier.medicament.MedicamentInexistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;

public interface IMedicamentService {

	void ajouterMedicament(Medicament medicament);

	//Medicament obtenirMedicament(Long idMal) throws MedicamentInexistantException;

	//void supprimerMedicament(Long idMed) throws MedicamentInexistantException;

	void modifierMedicament(Medicament medicament) throws MedicamentInexistantException;

	Medicament obtenirMedicament(String idMed) throws MedicamentInexistantException;

	void supprimerMedicament(String idMed) throws MedicamentInexistantException;
	
	


}
