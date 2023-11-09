package tn.esprit.spring.kaddem.services;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class EquipeServiceTestsUnitaires {

    @Autowired
    IEquipeService iEquipeService;
    @Autowired
    EquipeRepository equipeRepository;

    Equipe equipe = Equipe.builder().nomEquipe("equipe1").niveau(Niveau.SENIOR).build();


    @Test
    @Order(0)
    public void ajouterequipe(){
        equipe= equipeRepository.save(equipe);
        log.info(equipe.toString());
        Assertions.assertNotNull(equipe.getIdEquipe());
    }
    @Test
    @Order(1)
    public void update(){
        equipe.setNomEquipe("equipeB");
        equipe= equipeRepository.save(equipe);
        log.info(equipe.toString());
        Assertions.assertNotEquals(equipe.getNomEquipe(), "equipe1");
    }
    @Test
    @Order(3)
    public void getAll(){
        List<Equipe> equipeList = (List<Equipe>) equipeRepository.findAll();
        log.info(equipeList.size()+"size list");
        Assertions.assertTrue(equipeList.size()>0);
    }

}

