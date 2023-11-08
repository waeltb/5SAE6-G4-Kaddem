package tn.esprit.spring.kaddem.service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.IUniversiteService;
import java.util.List;


@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class unitaire {
    @Autowired
    IUniversiteService iUniversiteService ;
    @Autowired
    UniversiteRepository universiteRepository ;

    Universite u = Universite.builder().nomUniv("tekup").build();
    @Test
    @Order(0)
    public void ajouterContrat(){
        u= universiteRepository.save(u);
        log.info(u.toString());
        Assertions.assertNotNull(u.getIdUniv());
    }
    @Test
    @Order(1)
    public void update(){
        u.setNomUniv("esprit");
        u= universiteRepository.save(u);
        log.info(u.toString());
        Assertions.assertNotEquals(u.getNomUniv(), "supcom");
    }
    @Test
    @Order(3)
    public void getAll(){
        List<Universite> universites = (List<Universite>) universiteRepository.findAll();
        log.info(universites.size()+"size list");
        Assertions.assertTrue(universites.size()>0);
    }
}
