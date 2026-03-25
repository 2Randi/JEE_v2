package fr.umontpellier.jeeS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.umontpellier.jeeS.model.Campus;
import fr.umontpellier.jeeS.model.Role;
import fr.umontpellier.jeeS.model.Utilisateur;
import fr.umontpellier.jeeS.service.UtilisateurService;

@Controller
public class UtilisateurController {
	
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	//Page de garde
	@GetMapping("/")
	@PreAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'GESTIONNAIRE', 'ETUDIANT')")
    public String home(Model model) {
        model.addAttribute("message", "Bienvenue sur la plateforme !");
        return "home";
    }

	
    /************Ajout nouveau utilisateur****************/
    //Formulaire 
    @GetMapping("/ajoutUser")
    public String ajoutUtilisateurForm(Model model) {
    	model.addAttribute("utilisateur", new Utilisateur());  
        return "ajoutUser"; 
    }
    
    @PostMapping("/ajoutUtilisateur")
    public String ajouterUtilisateur(Utilisateur utilisateur,Model model) {
        
    	utilisateurService.ajouterUtilisateur(utilisateur);
    	model.addAttribute("message", "Utilisateur ajouté avec succès !");
    	
       
        return "redirect:/login"; 
    }
    
    /*
    //tsy haiko
    @GetMapping("/somePage")
    public ResponseEntity<String> somePage() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok().headers(headers).body("Some page content");
    }
    */

}
