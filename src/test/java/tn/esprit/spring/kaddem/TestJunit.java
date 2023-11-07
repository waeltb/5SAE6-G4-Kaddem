package tn.esprit.spring.kaddem;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class TestJunit {

    @Autowired
    private IDepartementService departementService;

    @Test
    @Order(0)
    public void testAddDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("testDepartement");
        Departement addedDepartement = departementService.addDepartement(departement);
        assertNotNull(addedDepartement);
        log.info("Added departement: {}", addedDepartement);
    }

    @Test
    @Order(1)
    public void testUpdateDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("testDepartement");
        Departement addedDepartement = departementService.addDepartement(departement);
        addedDepartement.setNomDepart("updatedDepartement");
        Departement updatedDepartement = departementService.updateDepartement(addedDepartement);
        assertEquals("updatedDepartement", updatedDepartement.getNomDepart());
        log.info("Updated departement: {}", updatedDepartement);
    }

    @Test
    @Order(2)
    public void testRetrieveDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("testDepartement");
        Departement addedDepartement = departementService.addDepartement(departement);
        Departement retrievedDepartement = departementService.retrieveDepartement(addedDepartement.getIdDepart());
        assertEquals(addedDepartement.getNomDepart(), retrievedDepartement.getNomDepart());
        log.info("Retrieved departement: {}", retrievedDepartement);
    }

    @Test
    @Order(3)
    public void testDeleteDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("testDepartement");
        Departement addedDepartement = departementService.addDepartement(departement);
        departementService.deleteDepartement(addedDepartement.getIdDepart());
        List<Departement> departements = departementService.retrieveAllDepartements();
        assertTrue(departements.stream().noneMatch(d -> d.getIdDepart().equals(addedDepartement.getIdDepart())));
        log.info("Departement successfully deleted.");
    }

    @Test
    @Order(4)
    public void testRetrieveAllDepartements() {
        List<Departement> departements = departementService.retrieveAllDepartements();
        assertTrue(departements.size() > 0);
        log.info("Departement count: {}", departements.size());
    }
}

