package fr.umontpellier.jeeS.service;

import fr.umontpellier.jeeS.model.Batiment;
import fr.umontpellier.jeeS.model.Composante;
import fr.umontpellier.jeeS.repository.BatimentRepository;
import fr.umontpellier.jeeS.repository.ComposanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
@Service
public class BatimentComposanteService {

    @Autowired
    private BatimentRepository batimentRepository;

    @Autowired
    private ComposanteRepository composanteRepository;

    @Transactional
    public Batiment createBatiment(Batiment batiment) {
        return batimentRepository.save(batiment);
    }

    @Transactional
    public Batiment updateBatiment(String codeB, Batiment updatedBatiment) {
        Batiment batiment = batimentRepository.findById(codeB).orElseThrow();
        batiment.setAnneeC(updatedBatiment.getAnneeC());
        batiment.setCampus(updatedBatiment.getCampus());
        return batimentRepository.save(batiment);
    }

    @Transactional
    public void deleteBatiment(String codeB) {
        batimentRepository.deleteById(codeB);
    }

    @Transactional
    public Composante createComposante(Composante composante) {
        return composanteRepository.save(composante);
    }

    @Transactional
    public Composante updateComposante(String acronyme, Composante updatedComposante) {
        Composante composante = composanteRepository.findById(acronyme).orElseThrow();
        composante.setNom(updatedComposante.getNom());
        composante.setResponsable(updatedComposante.getResponsable());
        return composanteRepository.save(composante);
    }

    @Transactional
    public void deleteComposante(String acronyme) {
        composanteRepository.deleteById(acronyme);
    }

    @Transactional
    public void addComposanteToBatiment(String codeB, String acronyme) {
        Batiment batiment = batimentRepository.findById(codeB).orElseThrow();
        Composante composante = composanteRepository.findById(acronyme).orElseThrow();
        batiment.addComposante(composante);
        composante.getBatiments().add(batiment);
        batimentRepository.save(batiment);
        composanteRepository.save(composante);
    }

    @Transactional
    public void removeComposanteFromBatiment(String codeB, String acronyme) {
        Batiment batiment = batimentRepository.findById(codeB).orElseThrow();
        Composante composante = composanteRepository.findById(acronyme).orElseThrow();
        batiment.removeComposante(composante);
        composante.getBatiments().remove(batiment);
        batimentRepository.save(batiment);
        composanteRepository.save(composante);
    }
}
*/
