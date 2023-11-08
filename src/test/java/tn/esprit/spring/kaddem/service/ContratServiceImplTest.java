package tn.esprit.spring.kaddem.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.IContratService;

import java.util.Date;
import java.util.List;


@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class ContratServiceImplTest {
    @Autowired
    IContratService service;
    @Autowired
    ContratRepository contratRepository;

    Contrat c = Contrat.builder().montantContrat(1).dateDebutContrat(new Date(2023, 05, 25))
            .dateFinContrat(new Date(2023, 06, 25)).specialite(Specialite.RESEAUX).archive(false).build();
    @Test
    @Order(0)
public void ajouterContrat(){
    c= contratRepository.save(c);
    log.info(c.toString());
    Assertions.assertNotNull(c.getIdContrat());
}
    @Test
    @Order(1)
public void update(){
        c.setMontantContrat(3);
        c= contratRepository.save(c);
    log.info(c.toString());
    Assertions.assertNotEquals(c.getMontantContrat(), 1);
}
    @Test
    @Order(3)
    public void getAll(){
        List<Contrat> contratList = contratRepository.findAll();
        log.info(contratList.size()+"size list");
        Assertions.assertTrue(contratList.size()>0);
}

}