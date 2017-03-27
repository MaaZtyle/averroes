/**
 * 
 */
package fr.maazouza.averroes.middleware.services;

import fr.maazouza.averroes.middleware.objetmetier.allergie.Allergie;
import fr.maazouza.averroes.middleware.objetmetier.allergie.AllergieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
import fr.maazouza.averroes.middleware.objetmetier.maladie.MaladieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;

import java.util.Collection;
import java.util.List;

/**
 * @author Maazouza
 *
 */
public interface IAllergieService {

	void ajouterAllergie(Allergie allergie);

	Allergie obtenirAllergie(Long idAll) throws AllergieInexistanteException;

	void supprimerAllergie(Long idAll) throws AllergieInexistanteException;

	void modifierAllergie(Allergie allergie) throws AllergieInexistanteException;

	
	


}


	
	



