package tn.esprit.spring.kaddem.service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.kaddem.entities.Universite;

import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class mockito {
    @Mock
    UniversiteRepository universiteRepository;
    @InjectMocks
    UniversiteServiceImpl universiteService;
    Universite u = Universite.builder().nomUniv("tekup").build();
    List<Universite> list= new ArrayList<Universite>() {
        {
            add(Universite.builder().nomUniv("fst").build());
            add(Universite.builder().nomUniv("enic").build());
        }
    };
    @Test
    public void add(){
        Mockito.when(universiteRepository.save(Mockito.any(Universite.class))).then(invocation -> {
            Universite model = invocation.getArgument(0, Universite.class);
            model.setIdUniv(1);
            return model;
        });
        log.info("Avant ==> " + u.toString());
        Universite universite = universiteService.addUniversite(u);
        assertSame(universite, u);
        log.info("Après ==> " + u.toString());

    }
    @Test
    public void retreiveUniversiteTest() {
        Mockito.when(universiteRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(u));
        Universite universite = universiteService.retrieveUniversite(1);
        assertNotNull(universite);
        log.info("get ==> " + universite.toString());
        verify(universiteRepository).findById(Mockito.anyInt());

    }

    @Test
    public void retreiveAllUniversiteTest() {
        Mockito.when(universiteRepository.findAll()).thenReturn(list);
        List<Universite> universites = universiteService.retrieveAllUniversites();
        assertNotNull(universites);
    }
}
