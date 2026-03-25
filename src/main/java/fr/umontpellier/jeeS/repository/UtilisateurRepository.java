package fr.umontpellier.jeeS.repository;

import org.springframework.data.repository.CrudRepository;

import fr.umontpellier.jeeS.model.*;

public interface UtilisateurRepository extends CrudRepository<Utilisateur,Long>{
	Utilisateur findByEmail(String email);

	boolean existsByEmail(String email);
	

}
