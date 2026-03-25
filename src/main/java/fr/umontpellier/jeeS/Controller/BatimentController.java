package fr.umontpellier.jeeS.Controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.umontpellier.jeeS.model.Batiment;
import fr.umontpellier.jeeS.model.Campus;
import fr.umontpellier.jeeS.model.Composante;
import fr.umontpellier.jeeS.repository.BatimentRepository;
import fr.umontpellier.jeeS.service.BatimentService;
import fr.umontpellier.jeeS.service.CampusService;
import fr.umontpellier.jeeS.service.ComposanteService;

@Controller
public class BatimentController {
	
	@Autowired
    private BatimentService batimentService;
	
	@Autowired
    private CampusService campusService;
	
	@Autowired
    private ComposanteService composanteService;
	
	
	// TEST CRUD//
	
	
	//batiment par campus
    @GetMapping("/batimentsParCampus")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String getBatimentsByCampus(@RequestParam("nomC") String nomC, Model model) {
        
        if (nomC != null && !nomC.isEmpty()) {
            List<Batiment> batimentList = batimentService.findByCampusNomC(nomC);
            model.addAttribute("batimentList", batimentList);
        } else {
            model.addAttribute("batimentList", new ArrayList<>());
        }

        
        List<Campus> campusList = campusService.getAllCampus();
        model.addAttribute("campusList", campusList);

        return "listeBatiment"; 
    }
    
    //batiment par composante
    @GetMapping("/batimentsParComposante")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String getBatimentByComposantes(@RequestParam("acronyme") String acronyme, Model model) {
        
        if (acronyme != null && !acronyme.isEmpty()) {
            List<Batiment> batimentList = batimentService.findByComposantesAcronyme(acronyme);
            model.addAttribute("batimentList", batimentList);
        } else {
            model.addAttribute("batimentList", new ArrayList<>());
        }

        
        List<Composante> composanteList = composanteService.getAllComposante();
        model.addAttribute("composanteList", composanteList);

        return "listeBatiment"; 
    }
    
    
	
    /***********			**********/
    
    /*****		READ		*****/
    //AFFICHER TOUS LES BÂTIMENTS
    @GetMapping("/batiments")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String showAllBatiments(Model model) {
        List<Batiment> batimentList = batimentService.getAllBatiments();
        model.addAttribute("batimentList", batimentList);  
        return "batiment";  
    }
    
    //Afficher un bâtiment par codeB ou campus
    @GetMapping("/searchBatiment")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String findBatiment(@RequestParam(value = "codeB", required = false) String codeB,
    	    					@RequestParam(value = "anneeC", required = false) Integer anneeC,
    	    					//@RequestParam(value = "campus", required = false) String nomC,
                               Model model) {
	
		List<Batiment> batiments;
		/*
		Campus campus = null;
	    if (nomC != null && !nomC.isEmpty()) {
	        campus = (Campus) campusService.findByNomC(nomC);
	    }
	        
	    } else if (codeB != null && !codeB.isEmpty() && campus != null) {
	        batiments = batimentService.findByCodeBAndCampus(codeB, campus);
	        
	    } else if (campus != null ) {
	        batiments = batimentService.findByCampus(campus);
	    }
	    */
		if (codeB != null && !codeB.isEmpty() && anneeC != null ) {
	        batiments = batimentService.findByCodeBAndAnneeC(codeB, anneeC);
	      
	        
	    } else if (codeB != null && !codeB.isEmpty()) {
	        batiments = batimentService.findByCodeB(codeB);
	        
	    
	    } else if (anneeC != null) {
	        batiments = batimentService.findByAnneeC(anneeC);
	        
	    } else {
	        batiments = batimentService.getAllBatiments();
	    }

	    model.addAttribute("batiments", batiments);
	    return "batiment";
    }
    
    
    
    /*******	ADD		******/
    //FORMULAIRE pour l'ajout de Batiment
    @GetMapping("/addBatiment")
    @PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String addBatimentForm(Model model) {
        model.addAttribute("batiment", new Batiment());
        List<Campus> campusList = campusService.getAllCampus(); 
        model.addAttribute("campusList", campusList);
        return "ajoutBatiment";  
    }
    
   
    //Ajouter un bâtiment via un formulaire (en POST)
    @PostMapping("/batiment")
    @PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String addBatiment(Batiment batiment, Model model) {
        batimentService.addBatiment(batiment);
        model.addAttribute("message", "Bâtiment ajouté avec succès !");
        return "redirect:/batiments";  
    }
    
    /*
    // Ajouter une composante à un bâtiment via un formulaire (en POST)
    //@PostMapping("/addComposanteToBatiment")
    @PostMapping("/batiment")
    @PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String addComposanteToBatiment(@RequestParam("codeB") String codeB,
                                           @RequestParam("acronyme") String acronyme,
                                           Model model) {
        try {
            batimentService.addComposanteToBatiment(codeB, acronyme);
            model.addAttribute("message", "Composante ajoutée au bâtiment avec succès !");
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
        }
        return "redirect:/batiment";  
    }
    */
    
    
    /*****		DELETE		*****/
    
    
    //Supprimer un bâtiment
    @PostMapping("/deleteBatiment")
    @PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String deleteBatiment(@RequestParam("codeB") String codeB, Model model) {
        batimentService.deleteBatiment(codeB);
        model.addAttribute("batimentList", batimentService.getAllBatiments());
        model.addAttribute("page", "accueil");
        return "redirect:/batiments"; 
    }
    
    /*
    //@PostMapping("/removeComposanteFromBatiment")
    @PostMapping("/deleteBatiment")
    @PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String removeComposanteFromBatiment(@RequestParam("codeB") String codeB,
                                               @RequestParam("acronyme") String acronyme,
                                               Model model) {
        try {
            batimentService.removeComposanteFromBatiment(codeB, acronyme);
            model.addAttribute("message", "Composante supprimée du bâtiment avec succès !");
        } catch (Exception e) {
            model.addAttribute("message", "Erreur : " + e.getMessage());
        }
        return "redirect:/batiment";  
    }
    */
    
    /**********		UPDATE		****************/
    //MAJ ANNEE et CAMPUS

    @GetMapping("/updateBatimentForm")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE')")
    public String updateBatimentForm(@RequestParam("codeB") String codeB, Model model) {
        model.addAttribute("codeB", codeB);
        List<Campus> campusList = campusService.getAllCampus(); 
        model.addAttribute("campusList", campusList);
        return "modifierBatiment";         
    }

    @PostMapping("/updateBatiment")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE')")
    public String updateBatiment(@RequestParam("codeB") String codeB,
                                 @RequestParam("newAnneeC") int newAnneeC,
                                 @RequestParam("newCampus") Campus newCampus,
                                 Model model) {
        List<Batiment> batimentList = batimentService.findByCodeB(codeB); 
        if (!batimentList.isEmpty()) {
            Batiment batiment = batimentList.get(0); 
            batiment.setAnneeC(newAnneeC); 
            batiment.setCampus(newCampus);
            batimentService.saveBatiment(batiment); 
            model.addAttribute("message", "Bâtiment modifié avec succès !");
        } else {
            model.addAttribute("error", "Bâtiment introuvable");
        }
        return "redirect:/batiments"; 
    }

    
    //recherche par id
    @GetMapping("/batiments/{codeB}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String getBatiment(@PathVariable("codeB") String codeB, Model model) {
        Batiment batiment = batimentService.getBatimentByCodeB(codeB);
        model.addAttribute("batiment", batiment);
        return "batimentDetails";
    }
    
    
    
    //Liste des composante d'un campus
    @GetMapping("/composantesParCampus")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String getComposantesParCampus(@RequestParam("nomC") String nomC, Model model) {
        List<Composante> composantes = campusService.getComposantesByCampus(nomC);
        model.addAttribute("composanteList", composantes);
        model.addAttribute("nomC", nomC);
        return "composantesParCampus";
    }
    
    
    //Campus d'un composant
    @GetMapping("/campusParComposante")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String getCampusParComposante(@RequestParam("acronyme") String acronyme, Model model) {
        
        List<Campus> campusList = composanteService.getCampusByComposante(acronyme);

        model.addAttribute("campusList", campusList);
        model.addAttribute("acronyme", acronyme);
        return "campusParComposante";
    }
    
    
 


}
