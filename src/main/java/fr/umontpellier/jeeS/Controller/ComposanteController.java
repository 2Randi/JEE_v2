package fr.umontpellier.jeeS.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.umontpellier.jeeS.model.Batiment;
import fr.umontpellier.jeeS.model.Campus;
import fr.umontpellier.jeeS.model.Composante;
import fr.umontpellier.jeeS.model.Salle;
import fr.umontpellier.jeeS.service.BatimentService;
import fr.umontpellier.jeeS.service.ComposanteService;

@Controller
public class ComposanteController {
	
	@Autowired
    private ComposanteService composanteService;
	
	@Autowired
    private BatimentService batimentService;
	
	
	/*****		READ		*****/
    //AFFICHER TOUS LES COMPOSANTE
	
	//Afficher tous les composante)
	@GetMapping("/composante")
	@PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String showAllComposante(Model model) {
        List<Composante> composanteList =composanteService.getAllComposante();  
        model.addAttribute("composanteList", composanteList);  
        return "composante"; 
	}
		
    
    //Afficher un campus par nomC ou ville
    @GetMapping("/searchComposante")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String findComposante(@RequestParam("acronyme") String acronyme,
                             @RequestParam("nom") String nom,
                             @RequestParam("responsable") String responsable,
                             Model model) {
    	List<Composante> composantes;

        if (acronyme != null && !acronyme.isEmpty() && nom != null && !nom.isEmpty() && responsable != null && !responsable.isEmpty()) {
        	composantes = composanteService.findByAcronymeAndNomAndResponsable(acronyme, nom, responsable);
        }
        
        else if (acronyme != null && !acronyme.isEmpty() && nom != null && !nom.isEmpty() ) {
        	composantes = composanteService.findByAcronymeAndNom(acronyme, nom);
        }
        
        else if (acronyme != null && !acronyme.isEmpty() && responsable != null && !responsable.isEmpty()) {
        	composantes = composanteService.findByAcronymeAndResponsable(acronyme, responsable);
        }
        
        else if (nom != null && !nom.isEmpty() && responsable != null && !responsable.isEmpty()) {
        	composantes = composanteService.findByNomAndResponsable( nom, responsable);
        }
        
        else if (acronyme != null && !acronyme.isEmpty() ) {
        	composantes = composanteService.findByAcronyme(acronyme);
        }
        
        else if (responsable != null && !responsable.isEmpty() ) {
        	composantes = composanteService.findByResponsable(responsable);
        }
        
        else if (nom != null && !nom.isEmpty()) {
        	composantes = composanteService.findByNom(nom);
        }
        
        else {
        	composantes = composanteService.getAllComposante();
        }

        model.addAttribute("composantes", composantes);
        return "composante";
   
    }
    

	
	/*******	ADD		******/
	//FORMULAIRE pour l ajout de composante
    
	@GetMapping("/addComposante")
	@PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String addComposanteForm(Model model) {
        model.addAttribute("composante", new Composante());  
        return "ajoutComposante";  
    }
	
	
	// Ajouter un campus via un formulaire (en POST)
    @PostMapping("/composante")
	@PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String addComposante(Composante composante, Model model) {
    	composanteService.addComposante(composante);
        model.addAttribute("message", "Composante ajouté avec succès !");
        return "redirect:/composante";  
    }
    
    
    
    /*****		DELETE		*****/
	
 	//Supprimer un campus
    @PostMapping("/deleteComposante")
	@PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public String deleteComposante(@RequestParam("acronyme") String acronyme, Model model) {
        composanteService.deleteComposante(acronyme);
        model.addAttribute("composanteList", composanteService.getAllComposante());
        model.addAttribute("page", "accueil");
        return "redirect:/composante"; 
    }
	
    
    /**********		UPDATE		****************/
    //MAJ VILLE
    
    @GetMapping("/updateComposanteForm")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE')")
    public String updateComposanteForm(@RequestParam("acronyme") String acronyme, Model model) {
        model.addAttribute("acronyme", acronyme);
        List<Composante> composanteList = composanteService.getAllComposante(); 
        model.addAttribute("composanteList", composanteList);
        return "modifierComposante";         
    }
    
    
    @PostMapping("/updateComposante")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE')")
    public String updateComposante(@RequestParam("acronyme") String acronyme,
    							@RequestParam("newNom") String newNom,
                                @RequestParam("newResponsable") String newResponsable,
                                Model model) {
        List<Composante> composanteList = composanteService.findByAcronyme(acronyme); 

        if (!composanteList.isEmpty()) {
        	Composante composante = composanteList.get(0); 
        	composante.setNom(newNom); 
        	composante.setResponsable(newResponsable);
            composanteService.saveComposante(composante); 
            model.addAttribute("message", "Composante modifié avec succès !");
        } else {
            model.addAttribute("error", "Composante introuvable");
        }
        return "redirect:/composante"; 
    }
    
    
    
    //Composante par batiment
    @GetMapping("/composantesParBatiment")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String getComposantesByBatiment(@RequestParam("codeB") String codeB, Model model) {
        
        if (codeB != null && !codeB.isEmpty()) {
            List<Composante> composanteList = composanteService.findByBatimentsCodeB(codeB);
            model.addAttribute("composanteList", composanteList);
        } else {
            model.addAttribute("composanteList", new ArrayList<>());
        }

        
        List<Batiment> batimentList = batimentService.getAllBatiments();
        model.addAttribute("batimentList", batimentList);

        return "listeComposante"; 
    }
}
