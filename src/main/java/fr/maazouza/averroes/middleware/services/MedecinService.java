package fr.maazouza.averroes.middleware.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import fr.maazouza.averroes.middleware.dao.MedecinDao;
import fr.maazouza.averroes.middleware.objetmetier.medecin.Medecin;

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
	public void ajouterMedecin(Medecin medecin) {
		// TODO Auto-generated method stub
		
		medecinDao.persister(medecin);
		
	}

	@Override
	public void ajouterMedecins(Collection<Medecin> medecins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Medecin obtenirMedecin(Integer id) {
		Medecin resultat = medecinDao.obtenir(id);
		
		
		return resultat;
	}

}
