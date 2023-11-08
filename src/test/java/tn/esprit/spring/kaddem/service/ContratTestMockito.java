package tn.esprit.spring.kaddem.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ContratTestMockito {
    @Mock
    ContratRepository contratRepository;
    @InjectMocks
    ContratServiceImpl contratService;
    Contrat c = Contrat.builder().montantContrat(1).dateDebutContrat(new Date(2023, 05, 25))
            .dateFinContrat(new Date(2023, 06, 25)).specialite(Specialite.RESEAUX).archive(false).build();
    List<Contrat> list= new ArrayList<Contrat>() {
        {
            add(Contrat.builder().montantContrat(256).dateDebutContrat(new Date(2023, 05, 25))
                    .dateFinContrat(new Date(2023, 07, 25)).specialite(Specialite.CLOUD).archive(true).build());
            add(Contrat.builder().montantContrat(257).dateDebutContrat(new Date(2023, 05, 25))
                    .dateFinContrat(new Date(2023, 05, 25)).specialite(Specialite.IA).archive(false).build());
        }
    };
    @Test
    public void add(){
        Mockito.when(contratRepository.save(Mockito.any(Contrat.class))).then(invocation -> {
            Contrat model = invocation.getArgument(0, Contrat.class);
            model.setIdContrat(1);
            return model;
        });
        log.info("Avant ==> " + c.toString());
        Contrat contrat = contratService.addContrat(c);
        assertSame(contrat, c);
        log.info("Après ==> " + c.toString());

    }
    @Test
    public void retreiveMagasinTest() {
        Mockito.when(contratRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(c));
        Contrat contrat = contratService.retrieveContrat(1);
        assertNotNull(contrat);
        log.info("get ==> " + contrat.toString());
        verify(contratRepository).findById(Mockito.anyInt());

    }

    @Test
    public void retreiveAllMagasinTest() {
        Mockito.when(contratRepository.findAll()).thenReturn(list);
        List<Contrat> magasins = contratService.retrieveAllContrats();
        assertNotNull(magasins);
    }

}
