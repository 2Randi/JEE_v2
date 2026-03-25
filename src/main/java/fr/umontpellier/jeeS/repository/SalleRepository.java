package fr.umontpellier.jeeS.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.umontpellier.jeeS.model.Salle;

@Repository
public interface SalleRepository extends CrudRepository<Salle,String>{

	List<Salle> findByBatiment_CodeB(String codeB);
	
	List<Salle> findByNumS(String numS);

	List<Salle> findByCapacite(Integer capacite);

	List<Salle> findByTypeS(String typeS);

	List<Salle> findByEtage(String etage);

	List<Salle> findByAcces(String acces);

	List<Salle> findByNumSAndCapacite(String numS, Integer capacite);

	List<Salle> findByNumSAndTypeS(String numS, String typeS);

	List<Salle> findByNumSAndCapaciteAndTypeS(String numS, Integer capacite, String typeS);

}
