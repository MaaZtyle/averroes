/**
 * 
 */
package fr.maazouza.averroes.middleware.services;

import fr.maazouza.averroes.middleware.objetmetier.antecedent.Antecedent;
import fr.maazouza.averroes.middleware.objetmetier.antecedent.AntecedentInexistantException;

/**
 * @author Maazouza
 *
 */
public interface IAntecedentService {

	void ajouterAntecedent(Antecedent antecedent);

	Antecedent obtenirAntecedent(Long idAll) throws AntecedentInexistantException;

	void supprimerAntecedent(Long idAnt) throws AntecedentInexistantException;

	void modifierAntecedent(Antecedent antecedent) throws AntecedentInexistantException;

	

	
	


}


	
	



