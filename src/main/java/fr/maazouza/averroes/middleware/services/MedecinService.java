package fr.maazouza.averroes.middleware.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import fr.maazouza.averroes.middleware.dao.MedecinDao;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;
import fr.maazouza.averroes.middleware.objetmetier.medecin.MedecinDejaExistantException;

/**
 * 
 * 
 * @author Maazouza
 *
 *service de gestion de medecins
 */
@Stateless
public class MedecinService implements IMedecinService {
	
	@EJB
	private MedecinDao medecinDao;
	
	
	
	@EJB
	private IMedecinService medecinService;
	
	
	@Override
	public boolean existerParNomEtPrenom(Medecin medecin) {
		
		//je cherche tous les patients avec ce nom
		List<Medecin> listeMedecin = medecinDao.obtenir(medecin.getNom_med());
		
		// ensuite je vérifie si le prénom est aussi le même
		
		Medecin result = listeMedecin.stream()
			     .filter(item -> item.getPrenom_med().equals(medecin.getPrenom_med()))
			     .findFirst()
			     .orElse(null);
		
		 
		
		//si le nom et le prenom sont les memes, je retourne true
		if( result == null)
			return false;
			else return true;
					
			
			     	
	}

	
	@Override
	public void ajouterMedecin(Medecin medecin) throws MedecinDejaExistantException{
		
		if((existerParNomEtPrenom(medecin)==true ))
			throw new MedecinDejaExistantException("Le Medecin "+ medecin.getNom_med()+ "  " +medecin.getPrenom_med()+" existe déjà");
		else medecinDao.persister(medecin);
						
	}

	
	@Override
	public Medecin obtenirUnMedecin(String nom, String prenom){
    
		Medecin resultat = medecinDao.obtenirUnMedecin(nom,prenom);
		
		return resultat;
	}
	
	@Override
	public void ajouterMedecins(Collection<Medecin> medecins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Medecin> obtenirMedecin(String predicat) {
		List<Medecin> resultat = medecinDao.obtenir(predicat);
		
		
		
		return resultat;
	}


	@Override
	public void modifierMedecin(Medecin medecin) {
		medecinDao.modifier(medecin);
		
	}

}
