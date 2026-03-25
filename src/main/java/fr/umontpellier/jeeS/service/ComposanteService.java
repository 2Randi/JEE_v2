package fr.umontpellier.jeeS.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.umontpellier.jeeS.model.Campus;
import fr.umontpellier.jeeS.model.Composante;
import fr.umontpellier.jeeS.repository.ComposanteRepository;

@Service
public class ComposanteService {
	
	@Autowired
	private ComposanteRepository composanteRepository;
	
	@Autowired
	private BatimentService batimentService;
	
	
	//Afficher ts les composantes
	public List<Composante> getAllComposante() {
        return (List<Composante>) composanteRepository.findAll();
    }


	//find
	public List<Composante> findByAcronymeAndNomAndResponsable(String acronyme, String nom, String responsable) {
		// TODO Auto-generated method stub
		return composanteRepository.findByAcronymeAndNomAndResponsable(acronyme, nom, responsable);
	}

	public List<Composante> findByAcronymeAndNom(String acronyme, String nom) {
		// TODO Auto-generated method stub
		return composanteRepository.findByAcronymeAndNom(acronyme,nom);
	}

	public List<Composante> findByAcronymeAndResponsable(String acronyme, String responsable) {
		// TODO Auto-generated method stub
		return composanteRepository.findByAcronymeAndResponsable(acronyme, responsable);
	}

	public List<Composante> findByNomAndResponsable(String nom, String responsable) {
		// TODO Auto-generated method stub
		return composanteRepository.findByNomAndResponsable(nom,responsable);
	}


	public List<Composante> findByAcronyme(String acronyme) {
		// TODO Auto-generated method stub
		return composanteRepository.findByAcronyme(acronyme) ;
	}

	public List<Composante> findByNom(String nom) {
		// TODO Auto-generated method stub
		return composanteRepository.findByNom(nom);
	}
	
	public List<Composante> findByResponsable(String responsable) {
		// TODO Auto-generated method stub
		return composanteRepository.findByResponsable(responsable);
	}


	/**** ADD *****/
	//add
	public Composante addComposante(Composante composante) {
		// TODO Auto-generated method stub
		if (composanteRepository.existsById(composante.getAcronyme())) {
            throw new IllegalArgumentException("Le campus avec ce nom existe déjà.");
        }
        return composanteRepository.save(composante);
	}
	
	/**** delete *****/
	//delete
	public void deleteComposante(String acronyme) {
		// TODO Auto-generated method stub
		if (!composanteRepository.existsById(acronyme)) {
            throw new IllegalArgumentException("Composante non trouvé avec nomC : " + acronyme);
        }
		composanteRepository.deleteById(acronyme);
	}
	
	
	/**** UPDATE *****/
	//Maj
	public Composante updateComposante(String acronyme, String newNom, String newResponsable) {
        Composante composante = composanteRepository.findById(acronyme)
                                        .orElseThrow(() -> new IllegalArgumentException("composante non trouvé avec composante : " + acronyme));
        composante.setNom(newNom);
        composante.setResponsable(newResponsable);
        //composante.setBatiments(newBatiment);
        return composanteRepository.save(composante);
    }

	/**** SAVE *****/
	//SAUVEGARDE
    public void saveComposante(Composante composante) {
        composanteRepository.save(composante); 
    }

    
    //composante par batiment (ManyToMany)
    public List<Composante> findByBatimentsCodeB(String codeB) {
        return composanteRepository.findByBatiments_codeB(codeB);
    }
    
    
    /**** jointure  ****/
    //campus du composante
    public Optional<Composante> findByBatimentCampusNomC(String nomC) {
        return composanteRepository.findById(nomC);
        
    }

    //Utiliser BatimentService
	public Optional<Composante> findByBatimentComposanteAcronyme(String acronyme) {
        return composanteRepository.findById(acronyme);
    }


	public List<Campus> getCampusByComposante(String acronyme) {
		// TODO Auto-generated method stub
		//return batimentService.getCampusByComposante(acronyme);
		List<Campus> campus = batimentService.getCampusByComposante(acronyme);
		return new ArrayList<>(new HashSet<>(campus));
	}
	
	
}



