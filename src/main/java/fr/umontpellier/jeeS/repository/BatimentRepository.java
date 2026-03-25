package fr.umontpellier.jeeS.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.umontpellier.jeeS.model.Batiment;
import fr.umontpellier.jeeS.model.Campus;

@Repository
public interface BatimentRepository extends CrudRepository<Batiment,String>{
	
	List<Batiment> findByCodeBAndAnneeC(String codeB, int anneeC);

	List<Batiment> findByAnneeC(int anneeC);

	List<Batiment> findByCodeB(String codeB);

	//List<Batiment> findByCodeBAndAnneeCAndCampus(String codeB, Integer anneeC, Campus campus);

	List<Batiment> findByCampus(Campus campus);

	//List<Batiment> findByCodeBAndCampus(String codeB, Campus campus);

	List<Batiment> findByCampus_nomC(String nomC);

	List<Batiment> findByComposantes_acronyme(String acronyme);
	
}
