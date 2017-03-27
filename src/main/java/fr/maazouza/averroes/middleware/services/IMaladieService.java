/**
 * 
 */
package fr.maazouza.averroes.middleware.services;

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
public interface IMaladieService {

	Maladie obtenirMaladie(Long idMal) throws MaladieInexistanteException;

	void ajouterMaladie(Maladie maladie);

	void modifierMaladie(Maladie maladie) throws MaladieInexistanteException;

	void supprimerMaladie(Long idMal) throws MaladieInexistanteException;
	


}


	
	



