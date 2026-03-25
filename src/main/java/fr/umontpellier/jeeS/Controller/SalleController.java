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
import fr.umontpellier.jeeS.model.Salle;
import fr.umontpellier.jeeS.service.BatimentService;
import fr.umontpellier.jeeS.service.SalleService;

@Controller
public class SalleController {
	
	@Autowired
    private SalleService salleService;
	
	@Autowired
	private BatimentService batimentService;
	
	
    /*****		READ		*****/
	//Affichage salle par batiment
	@GetMapping("/sallesParBatiment")
	@PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String getSallesByBatiment(@RequestParam("codeB") String codeB, Model model) {
        
		List<Salle> salleList;

	    if (codeB != null && !codeB.isEmpty()) {
	        salleList = salleService.findByBatimentCodeB(codeB);
	        if (salleList.isEmpty()) {
	            model.addAttribute("message", "Aucune salle trouvée pour le bâtiment : " + codeB);
	        }
	    } else {
	        salleList = salleService.getAllSalle(); 
	    }

	    model.addAttribute("salleList", salleList);
	    return "listeSalle"; 
	}
	
	
	/*********	READ	******/
	//Afficher tous les salle (codeB, anneeC, nom du campus)
	@GetMapping("/salle")
	@PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String showAllSalle(Model model) {
        List<Salle> salleList =salleService.getAllSalle();  
        model.addAttribute("salleList", salleList);  
        return "salle"; 
	}
	
	/*******	ADD		******/
	//AJOUT SALLE formulaire
	@GetMapping("/addSalle")
	@PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String addSalleForm(Model model) {
        model.addAttribute("salle", new Salle());
        List<Batiment> batimentList = batimentService.getAllBatiments(); 
        model.addAttribute("batimentList", batimentList);
        return "ajoutSalle";  
    }
	
	// Ajouter un salle via un formulaire
    @PostMapping("/salle")
    @PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String addSalle(Salle salle, Model model) {
        salleService.addSalle(salle);
        model.addAttribute("message", "Salle ajouté avec succès !");
        return "redirect:/salle";  
    }
    
    
    /*****		DELETE		*****/
 	//Supprimer un salle
    @PostMapping("/deleteSalle")
    @PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String deleteCampus(@RequestParam("numS") String numS, Model model) {
        salleService.deleteSalle(numS);
        model.addAttribute("salleList", salleService.getAllSalle());
        model.addAttribute("page", "accueil");
        return "redirect:/salle"; 
    }
	
    /**********		UPDATE		****************/
    //MAJ VILLE
    
    @GetMapping("/updateSalleForm")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE')")
    public String updateSalleForm(@RequestParam("numS") String numS, Model model) {
        model.addAttribute("numS", numS);
        List<Batiment> batimentList = batimentService.getAllBatiments(); 
        model.addAttribute("batimentList", batimentList);
        return "modifierSalle";         
    }
    
    @PostMapping("/updateSalle")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE')")
    public String updateCampus(@RequestParam("numS") String numS,
                                @RequestParam("newCapacite") int newCapacite,
                                @RequestParam("newTypeS") String newTypeS,
                                @RequestParam("newAcces") String newAcces,
                                @RequestParam("newEtage") String newEtage,
                                @RequestParam("newBatiment") Batiment newBatiment,
                                Model model) {
        List<Salle> salleList = salleService.findByNumS(numS); 
        if (!salleList.isEmpty()) {
            Salle salle = salleList.get(0); 
            salle.setCapacite(newCapacite) ;
            salle.setTypeS(newTypeS);
            salle.setAcces(newAcces);
            salle.setEtage(newEtage);
            salle.setBatiment(newBatiment);
            salleService.saveSalle(salle); 
            model.addAttribute("message", "Salle modifié avec succès !");
        } else {
            model.addAttribute("error", "Salle introuvable");
        }
        return "redirect:/salle"; 
    }
    
    /****** FIND******/
    @GetMapping("/searchSalle")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String findSalle(@RequestParam(value = "numS", required = false) String numS,
                              @RequestParam(value = "capacite", required = false) Integer capacite,
                              @RequestParam(value = "typeS", required = false) String typeS,
                              @RequestParam(value = "acces", required = false) String acces,
                              @RequestParam(value = "etage", required = false) String etage,
                              Model model) {
        
        List<Salle> salles;

        if (numS != null && !numS.isEmpty() && capacite != null && typeS != null && !typeS.isEmpty()) {
            salles = salleService.findByNumSAndCapaciteAndTypeS(numS, capacite, typeS);

        } else if (numS != null && !numS.isEmpty() && capacite != null) {
            salles = salleService.findByNumSAndCapacite(numS, capacite);

        } else if (numS != null && !numS.isEmpty() && typeS != null && !typeS.isEmpty()) {
            salles = salleService.findByNumSAndTypeS(numS, typeS);

        } else if (numS != null && !numS.isEmpty()) {
            salles = salleService.findByNumS(numS);

        } else if (capacite != null) {
            salles = salleService.findByCapacite(capacite);

        } else if (typeS != null && !typeS.isEmpty()) {
            salles = salleService.findByTypeS(typeS);

        } else if (etage != null && !etage.isEmpty()) {
            salles = salleService.findByEtage(etage);

        } else if (acces != null && !acces.isEmpty()) {
            salles = salleService.findByAcces(acces);

        } else {
            salles = salleService.getAllSalle(); 
        }

        model.addAttribute("salles", salles);
        return "salle"; 
    }
    
    //recherche par id
    @GetMapping("/salle/{numS}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String getDSalle(@PathVariable("numS") String numS, Model model) {
        Salle salle = salleService.getSalleByNumS(numS);
        model.addAttribute("salle", salle);
        return "salleDetails";
    }


}
