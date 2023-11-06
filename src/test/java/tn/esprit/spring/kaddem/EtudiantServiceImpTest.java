package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j

public class EtudiantServiceImpTest {


    @Autowired
    IEtudiantService iEtudiantService;


    @Test
    @Order(1)
    public void addEtudiant(){
        Etudiant etudiant= iEtudiantService.addEtudiant(Etudiant.builder().nomE("Saidani").prenomE("Hedil").op(Option.SE).build());
        Assertions.assertNotNull(etudiant);
        log.info("Etudiant : "+etudiant.toString());
    }


    @Test
    @Order(2)
    public void retrieveEtudiant() {
        final Etudiant etudiant = this.iEtudiantService.retrieveEtudiant(1);
        assertEquals("Saidani", etudiant.getNomE());
        log.info("Etudiant :" + etudiant.toString());
    }


    /*
    @Test
    @Order(3)
    public void retrieveAllEtudiant() {
        final List<Etudiant> allEtudiants = this.iEtudiantService.retrieveAllEtudiants();
        if (!CollectionUtils.isEmpty(allEtudiants)) {
            assertEquals(allEtudiants.size(), 4);
        }
        log.info("Nombre des étudiants " + allEtudiants.size() + " \n");
        for (int i = 0; i < allEtudiants.size(); i++) {
         log.info("==>" + allEtudiants.get(i).getPrenomE());
      }
    }
*/


    @Test
    @Order(4)
    public void updateEtudiant () {
        final Etudiant etudiant = this.iEtudiantService.retrieveEtudiant(1);
        log.info("In method updateEtudiant");
        etudiant.setPrenomE("hedil2");
        assertEquals("hedil2",iEtudiantService.updateEtudiant(etudiant).getPrenomE());
        log.info("Out of method updateEtudiant" + etudiant.toString());
    }


 /*
    @Test
    @Order(5)
    public void deleteEtudiant () {
        final Etudiant etudiant = this.iEtudiantService.retrieveEtudiant(6);
        iEtudiantService.removeEtudiant(etudiant.getIdEtudiant());
        log.info("Etudiant supprimé avec succès !");
    }
*/


}
