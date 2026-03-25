package fr.umontpellier.jeeS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.umontpellier.jeeS.model.Batiment;
import fr.umontpellier.jeeS.model.Composante;
import fr.umontpellier.jeeS.model.Salle;
import fr.umontpellier.jeeS.repository.SalleRepository;

@Service
public class SalleService {
	
	@Autowired
	private SalleRepository salleRepository;
	
    /*****		READ		*****/
	//afficer tt les salles
	public List<Salle> getAllSalle() {
        return (List<Salle>) salleRepository.findAll();
    }
	
    /*****		ADD		*****/
	//AJOUT SALLE
    public Salle addSalle(Salle salle) {
        if (salleRepository.existsById(salle.getNumS())) {
            throw new IllegalArgumentException("La salle avec ce code existe déjà.");
        }
        return salleRepository.save(salle);
    }
    
    /*****		REMOVE		*****/
    //SUPPR BAT
    public void deleteSalle(String numS) {
        if (!salleRepository.existsById(numS)) {
            throw new IllegalArgumentException("Bâtiment non trouvé avec numéro : " + numS);
        }
        salleRepository.deleteById(numS);
    }
    
	
	// Sauvegarde d'une salle
    public void saveSalle(Salle salle) {
    	salleRepository.save(salle);
    }
    
    
    /*****		READ		*****/
    //FIND
  	public List<Salle> findByBatimentCodeB(String codeB) {
  		return salleRepository.findByBatiment_CodeB(codeB);
  	}


	
	public List<Salle> findByNumS(String numS) {
        return salleRepository.findByNumS(numS);
    }

    public List<Salle> findByCapacite(Integer capacite) {
        return salleRepository.findByCapacite(capacite);
    }

    public List<Salle> findByTypeS(String typeS) {
        return salleRepository.findByTypeS(typeS);
    }

    public List<Salle> findByEtage(String etage) {
        return salleRepository.findByEtage(etage);
    }

    public List<Salle> findByAcces(String acces) {
        return salleRepository.findByAcces(acces);
    }

    public List<Salle> findByNumSAndCapacite(String numS, Integer capacite) {
        return salleRepository.findByNumSAndCapacite(numS, capacite);
    }

    public List<Salle> findByNumSAndTypeS(String numS, String typeS) {
        return salleRepository.findByNumSAndTypeS(numS, typeS);
    }

    public List<Salle> findByNumSAndCapaciteAndTypeS(String numS, Integer capacite, String typeS) {
        return salleRepository.findByNumSAndCapaciteAndTypeS(numS, capacite, typeS);
    }

    //recherche par id
	public Salle getSalleByNumS(String numS) {
		// TODO Auto-generated method stub
		return salleRepository.findById(numS).orElseThrow(() -> new RuntimeException("Salle introuvable"));
	}

	
}
