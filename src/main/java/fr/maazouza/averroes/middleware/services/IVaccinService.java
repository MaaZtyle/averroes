/**
 * 
 */
package fr.maazouza.averroes.middleware.services;

import fr.maazouza.averroes.middleware.objetmetier.vaccin.Vaccin;
import fr.maazouza.averroes.middleware.objetmetier.vaccin.VaccinInexistantException;

/**
 * @author Maazouza
 *
 */
public interface IVaccinService {

	void ajouterVaccin(Vaccin vaccin);

	Vaccin obtenirVaccin(Long idVac) throws VaccinInexistantException, VaccinInexistantException;

	void supprimerVaccin(Long idVac) throws VaccinInexistantException;

	void modifierVaccin(Vaccin vaccin) throws VaccinInexistantException;

	

	
	


}


	
	



