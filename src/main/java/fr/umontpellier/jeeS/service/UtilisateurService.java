package fr.umontpellier.jeeS.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.umontpellier.jeeS.config.*;
import fr.umontpellier.jeeS.model.Role;
import fr.umontpellier.jeeS.model.Utilisateur;
import fr.umontpellier.jeeS.repository.UtilisateurRepository;

@Service
public class UtilisateurService implements UserDetailsService {

	 @Autowired
	 private UtilisateurRepository utilisateurRepository;
	 
	 @Autowired
	 private BCryptPasswordEncoder  passwordEncoder;  

	 
	 @Override
	 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	     
	     Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

	     if (utilisateur == null) {
	         throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email);
	     }

	     
	     String role = utilisateur.getRole().name(); 
	     System.out.println("Utilisateur : " + email + ", Rôle : " + role);
	     
	     return User.builder()
	             .username(utilisateur.getEmail())
	             .password(utilisateur.getMdp()) 
	             .authorities(new SimpleGrantedAuthority(role)) 
	             .build();
	 }

	 //sauvegarde utilisateur
	 public Utilisateur saveUser(Utilisateur utilisateur) {
	        utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));
	        return utilisateurRepository.save(utilisateur);
	    }

	 
	 //Service vaovao
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
	        throw new IllegalArgumentException("L'utilisateur avec cet email existe déjà.");
	    }
	    utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));
	    return utilisateurRepository.save(utilisateur);
	}
	 
}
