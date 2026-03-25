package fr.umontpellier.jeeS.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import fr.umontpellier.jeeS.model.Utilisateur;
import fr.umontpellier.jeeS.repository.UtilisateurRepository;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @ModelAttribute
    public void addUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Utilisateur utilisateur = utilisateurRepository.findByEmail(userDetails.getUsername());
   	     String role = utilisateur.getRole().name(); 

            if (utilisateur != null) {
                model.addAttribute("nom", utilisateur.getNom());
                model.addAttribute("email", utilisateur.getEmail());
                model.addAttribute("role", new SimpleGrantedAuthority(role));
            }
        }
    }
}
