package fr.umontpellier.jeeS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.umontpellier.jeeS.model.Batiment;
import fr.umontpellier.jeeS.model.Campus;
import fr.umontpellier.jeeS.model.Composante;
import fr.umontpellier.jeeS.repository.BatimentRepository;
import fr.umontpellier.jeeS.repository.ComposanteRepository;

@Service
public class BatimentService {
	
	@Autowired
    private BatimentRepository batimentRepository;
	
	
	/*
	//Afficher tous les batiments
    public List<Batiment> getAllBatiment() {
        return (List<Batiment>) batimentRepository.findAll();
    }

   //ajout batiment
    public Batiment addBatiment(Batiment batiment) {
        if (batimentRepository.existsById(batiment.getCodeB())) {
            throw new IllegalArgumentException("Le batiment avec ce nom existe déjà.");
        }
        return batimentRepository.save(batiment);
    }
    */
	
    /*****		READ		*****/
	public List<Batiment> getAllBatiments() {
        return (List<Batiment>) batimentRepository.findAll();
    }

    /*****		ADD		*****/
    // AJOUT BATIMENT
    public Batiment addBatiment(Batiment batiment) {
        if (batimentRepository.existsById(batiment.getCodeB())) {
            throw new IllegalArgumentException("Le bâtiment avec ce code existe déjà.");
        }
        return batimentRepository.save(batiment);
    }
    
    /*****		REMOVE		*****/
    
    public void dissocier(Batiment batiment) {
        
        for (Composante composante : batiment.getComposantes()) {
            composante.getBatiments().remove(batiment);
        }
    }
    
    //SUPPR BAT
    public void deleteBatiment(String codeB) {
        if (!batimentRepository.existsById(codeB)) {
            throw new IllegalArgumentException("Batiment non trouvé avec codeB : " + codeB);
        }

        Batiment batiment = batimentRepository.findById(codeB)
            .orElseThrow(() -> new IllegalArgumentException("Batiment introuvable avec codeB : " + codeB));

        dissocier(batiment);

        batimentRepository.deleteById(codeB);
    }
    
    

    /*****		UPDATE		*****/
    // MAJ
    public Batiment updateBatiment(String codeB, int newAnneeC, Campus newCampus) {
        Batiment batiment = batimentRepository.findById(codeB)
            .orElseThrow(() -> new IllegalArgumentException("Bâtiment non trouvé avec codeB : " + codeB));
        batiment.setAnneeC(newAnneeC);
        batiment.setCampus(newCampus);
        return batimentRepository.save(batiment);
    }

    /*****		READ		*****/
    // FIND
    
    public List<Batiment> findByCodeB(String codeB) {
        return batimentRepository.findByCodeB(codeB);
    }

    public List<Batiment> findByAnneeC(int anneeC) {
        return batimentRepository.findByAnneeC(anneeC);
    }


    public List<Batiment> findByCodeBAndAnneeC(String codeB, int anneeC) {
        return batimentRepository.findByCodeBAndAnneeC(codeB, anneeC);
    }

    /*
	public List<Batiment> findByCodeBAndAnneeCAndCampus(String codeB, Integer anneeC, Campus campus) {
		// TODO Auto-generated method stub
		return batimentRepository.findByCodeBAndAnneeCAndCampus(codeB, anneeC, campus);
	}
	*/

	public List<Batiment> findByCampus(Campus campus) {
		// TODO Auto-generated method stub
		return batimentRepository.findByCampus(campus);
	}

	/*
	public List<Batiment> findByCodeBAndCampus(String codeB, Campus campus) {
		// TODO Auto-generated method stub
		return batimentRepository.findByCodeBAndCampus(codeB, campus);
	}
	*/

	public List<Batiment> findByCampusNomC(String nomC) {
		// TODO Auto-generated method stub
		return batimentRepository.findByCampus_nomC(nomC);
	}

	// Sauvegarde d'un bâtiment
    public void saveBatiment(Batiment batiment) {
        batimentRepository.save(batiment);
    }

	//batiment par composante
	public List<Batiment> findByComposantesAcronyme(String acronyme) {
		// TODO Auto-generated method stub
		return batimentRepository.findByComposantes_acronyme(acronyme);
	}

	
	//recherche par id
	public Batiment getBatimentByCodeB(String codeB) {
		// TODO Auto-generated method stub
        return batimentRepository.findById(codeB).orElseThrow(() -> new RuntimeException("Campus introuvable"));
    }
		
	
	//Compsante par campus
	public List<Composante> getComposantesByCampus(String nomC) {
        List<Batiment> batiments = batimentRepository.findByCampus_nomC(nomC);
        
        List<Composante> composantes = new ArrayList<>();
        for (Batiment b : batiments) {
            composantes.addAll(b.getComposantes());
        }
        
        return composantes;
    }

	
	//campus par composante
	public List<Campus> getCampusByComposante(String acronyme) {
		List<Batiment> batiments = batimentRepository.findByComposantes_acronyme(acronyme);
		
		List<Campus> campus = new ArrayList<>();
		for (Batiment b : batiments) {
			campus.add(b.getCampus());
		}
		return campus;
	}
    

}

