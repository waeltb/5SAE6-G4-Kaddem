package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EquipeServiceTestsMokito {


    @InjectMocks
    IEquipeService iEquipeService;
    @Mock
    EquipeRepository equipeRepository;

    Equipe equipe = Equipe.builder().nomEquipe("equipe1").niveau(Niveau.SENIOR).build();

    List<Equipe> list= new ArrayList<Equipe>() {
        {
            add(Equipe.builder().nomEquipe("equipe1").niveau(Niveau.SENIOR).build());
            add(Equipe.builder().nomEquipe("equipe2").niveau(Niveau.JUNIOR).build());
        }
    };
    @Test
    public void add(){
        Mockito.when(equipeRepository.save(Mockito.any(Equipe.class))).then(invocation -> {
            Equipe model = invocation.getArgument(0, Equipe.class);
            model.setIdEquipe(1);
            return model;
        });
        log.info("Avant ==> " + equipe.toString());
        Equipe equipe1 = iEquipeService.addEquipe(equipe);
        assertSame(equipe1, equipe);
        log.info("Après ==> " + equipe.toString());

    }
    @Test
    public void afficheEquipe() {
        Mockito.when(equipeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(equipe));
        Equipe equipe1 = iEquipeService.retrieveEquipe(1);
        assertNotNull(equipe1);
        log.info("get ==> " + equipe1.toString());
        verify(equipeRepository).findById(Mockito.anyInt());

    }

    @Test
    public void afficheAllequipe() {
        Mockito.when(equipeRepository.findAll()).thenReturn(list);
        List<Equipe> equipes = iEquipeService.retrieveAllEquipes();
        assertNotNull(equipes);
    }

}
