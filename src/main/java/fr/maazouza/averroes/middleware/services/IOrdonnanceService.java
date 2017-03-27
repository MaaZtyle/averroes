/**
 * 
 */
package fr.maazouza.averroes.middleware.services;

import fr.maazouza.averroes.middleware.objetmetier.maladie.Maladie;
import fr.maazouza.averroes.middleware.objetmetier.maladie.MaladieInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.Ordonnance;
import fr.maazouza.averroes.middleware.objetmetier.ordonnance.OrdonnanceInexistanteException;
import fr.maazouza.averroes.middleware.objetmetier.patient.Patient;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientDejaExistantException;
import fr.maazouza.averroes.middleware.objetmetier.patient.PatientInexistantException;

import java.util.Collection;
import java.util.List;

/**
 * @author Maazouza
 *
 */
public interface IOrdonnanceService {

	

	void ajouterOrdonnance(Ordonnance ordonnance);

	Ordonnance obtenirOrdonnance(Long idOrd) throws OrdonnanceInexistanteException;

	void supprimerOrdonnance(Long idOrd) throws OrdonnanceInexistanteException;

	void modifierOrdonnance(Ordonnance ordonnance) throws OrdonnanceInexistanteException;

	

	
	


}


	
	



