package fr.umontpellier.jeeS.repository;


import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.umontpellier.jeeS.model.Campus;


@Repository
public interface CampusRepository extends CrudRepository<Campus,String>{
	List<Campus> findByNomC(String nomC);

	List<Campus> findByVille(String ville);

	List<Campus> findByNomCOrVille(String nomC, String ville);

	List<Campus> findByNomCAndVille(String nomC, String ville);

}