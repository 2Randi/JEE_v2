package fr.umontpellier.jeeS.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;

import fr.umontpellier.jeeS.model.Batiment;
import fr.umontpellier.jeeS.model.Campus;
import fr.umontpellier.jeeS.model.Composante;
import fr.umontpellier.jeeS.service.BatimentService;
import fr.umontpellier.jeeS.service.CampusService;
import fr.umontpellier.jeeS.service.ComposanteService;


@Controller
public class CampusController {
	
	@Autowired
    private CampusService campusService;
	
	@Autowired
    private ComposanteService composanteService;
	
	
    /***********			**********/
	
	/*****		READ		*****/
    //AFFICHER TOUS LES CAMPUS
	
    @GetMapping("/campus")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String showAllCampus(Model model) {
        List<Campus> campusList = campusService.getAllCampus();  
        model.addAttribute("campusList", campusList);  
        return "campus";  
    }
    
    //Afficher un campus par nomC ou ville
    @GetMapping("/searchCampus")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String findCampus(@RequestParam("nomC") String nomC,
                             @RequestParam("ville") String ville, 
                             Model model) {
    	List<Campus> campuses;

        if (nomC != null && !nomC.isEmpty() && ville != null && !ville.isEmpty()) {
            campuses = campusService.findByNomCAndVille(nomC, ville);
        }
        
        else if (nomC != null && !nomC.isEmpty()) {
            campuses = campusService.findByNomC(nomC);
        }
        
        else if (ville != null && !ville.isEmpty()) {
            campuses = campusService.findByVille(ville);
        }
        
        else {
            campuses = campusService.getAllCampus();
        }

        model.addAttribute("campuses", campuses);
        return "campus";
   
    }
    

	
	/*******	ADD		******/
	//FORMULAIRE pour l ajout de Campus
	@GetMapping("/addCampus")
	@PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String addCampusForm(Model model) {
        model.addAttribute("campus", new Campus());  
        return "ajoutCampus";  
    }
	
	
	// Ajouter un campus via un formulaire (en POST)
    @PostMapping("/campus")
	@PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String addCampus(Campus campus, Model model) {
        campusService.addCampus(campus);
        model.addAttribute("message", "Campus ajouté avec succès !");
        return "redirect:/campus";  
    }
    
    
    
    
    /*****		DELETE		*****/
 	//Supprimer un campus
    @PostMapping("/deleteCampus")
	@PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String deleteCampus(@RequestParam("nomC") String nomC, Model model) {
        campusService.deleteCampus(nomC);
        model.addAttribute("campusList", campusService.getAllCampus());
        model.addAttribute("page", "accueil");
        return "redirect:/campus"; 
    }
	
    
    /**********		UPDATE		****************/
    //MAJ VILLE
    
    @GetMapping("/updateCampusForm")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE')")
    public String updateCampusForm(@RequestParam("nomC") String nomC, Model model) {
        model.addAttribute("nomC", nomC); 
        return "modifierCampus";         
    }
    
    
    @PostMapping("/updateCampus")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE')")
    public String updateCampus(@RequestParam("nomC") String nomC,
                                @RequestParam("newVille") String newVille,
                                Model model) {
        List<Campus> campusList = campusService.findByNomC(nomC); 
        if (!campusList.isEmpty()) {
            Campus campus = campusList.get(0); 
            campus.setVille(newVille); 
            campusService.saveCampus(campus); 
            model.addAttribute("message", "Campus modifié avec succès !");
        } else {
            model.addAttribute("error", "Campus introuvable");
        }
        return "redirect:/campus"; 
    }
    
    
    //rechreche par id
    @GetMapping("/campus/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String getCampus(@PathVariable("id") String nomC, Model model) {
        Campus campus = campusService.getCampusById(nomC); 
        model.addAttribute("campus", campus);
        return "campusDetails"; 
    }

    
    
}
