package fr.umontpellier.jeeS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

import fr.umontpellier.jeeS.model.Campus;
import fr.umontpellier.jeeS.model.Composante;
import fr.umontpellier.jeeS.repository.CampusRepository;
import fr.umontpellier.jeeS.repository.ComposanteRepository;

@Service
public class CampusService {
	
	@Autowired
    private CampusRepository campusRepository;
	
	@Autowired
	private BatimentService batimentService;
	
	/*****		ADD		*****/
	// Méthode pour ajouter un campus
    public Campus addCampus(Campus campus) {
        if (campusRepository.existsById(campus.getNomC())) {
            throw new IllegalArgumentException("Le campus avec ce nom existe déjà.");
        }
        return campusRepository.save(campus);
    }

    
    /*****		REMOVE		*****/
    // Méthode pour supprimer un campus
    public void deleteCampus(String nomC) {
        if (!campusRepository.existsById(nomC)) {
            throw new IllegalArgumentException("Campus non trouvé avec nomC : " + nomC);
        }
        campusRepository.deleteById(nomC);
    }
    
    /*****		READ		*****/
 // Méthode pour récupérer tous les campus
    public List<Campus> getAllCampus() {
        return (List<Campus>) campusRepository.findAll();
    }

    
    //RECHERCHE PAR NOM, VILLE, NOM && VILLE
    public List<Campus> findByNomC(String nomC) {
    	
        return  campusRepository.findByNomC(nomC);
    }
    
   
    public List<Campus> findByVille(String ville) {
    	
        return (List<Campus>) campusRepository.findByVille(ville);
    }
    
    
    public List<Campus> findByNomCAndVille(String nomC, String ville) {
	    return campusRepository.findByNomCAndVille(nomC, ville);
	}
    
    
    public List<Campus> findByNomCOrVille(String nomC, String ville) {
    	return campusRepository.findByNomCOrVille(nomC, ville);
	}
    
    
    /*****		UPDATE		*****/
    //MAJ
    public Campus updateCampus(String nomC, String newVille) {
        Campus campus = campusRepository.findById(nomC)
                                        .orElseThrow(() -> new IllegalArgumentException("Campus non trouvé avec nomC : " + nomC));
        campus.setVille(newVille);
        return campusRepository.save(campus);
    }
    
    //SAUVEGARDE
    public void saveCampus(Campus campus) {
        campusRepository.save(campus); 
    }
    
    
    //recherche par id
    public Campus getCampusById(String nomC) {
        return campusRepository.findById(nomC).orElseThrow(() -> new RuntimeException("Campus introuvable"));
    }
    
    
    //Utiliser BatimentService pour récupérer les composantes par campus
    public List<Composante> getComposantesByCampus(String nomC) {
       // return batimentService.getComposantesByCampus(nomC);
        List<Composante> composantes = batimentService.getComposantesByCampus(nomC);
        return new ArrayList<>(new HashSet<>(composantes));
    }
}

