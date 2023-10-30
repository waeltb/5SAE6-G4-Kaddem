package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EtudiantMockitoTest {

    @Mock
    EtudiantRepository etudiantRepository;

    @InjectMocks
    EtudiantServiceImpl etudiantService;

    Etudiant etudiant  = Etudiant.builder().nomE("Saidani").prenomE("Hedil").op(Option.SE).build();

    List<Etudiant> list= new ArrayList<Etudiant>() {
        {
            add(Etudiant.builder().nomE("Ben Said").prenomE("Ahmed").op(Option.SIM).build());
            add(Etudiant.builder().nomE("Ben foulen").prenomE("Mohamed").op(Option.GAMIX).build());
        }
    };

    @Test
    public void addEtudiant() {
        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).then(invocation -> {
            Etudiant model = invocation.getArgument(0, Etudiant.class);
            model.setIdEtudiant(1);
            return model;
        });
        log.info("In method addEtudiant" + etudiant.toString());
        Etudiant e = etudiantService.addEtudiant(etudiant);
        assertSame(etudiant, e);
        log.info("Out of method addEtudiant" + e.toString());
    }

    @Test
    public void retreiveAllEtudiants() {
        Mockito.when(etudiantRepository.findAll()).thenReturn(list);
        log.info("In method retrieveAllEtudiants");
        List<Etudiant> etudiants= etudiantService.retrieveAllEtudiants();
        assertNotNull(etudiants);
        log.info("out of method retrieveAllEtudiants");
    }


    @Test
    public void retreiveEtudiantTest() {
        Mockito.when(etudiantRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(etudiant));
        Etudiant e = etudiantService.retrieveEtudiant(2005);
        assertNotNull(etudiant);
        log.info("Etudiant :" + etudiant.toString());
        verify(etudiantRepository).findById(Mockito.anyInt());
    }


    @Test
    public void updateEtudiantTest() {
        Mockito.when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        log.info("In method updateEtudiant");
        Etudiant e = etudiantService.updateEtudiant(etudiant);
        etudiant.setPrenomE("Hedil2");
        assertEquals(etudiant,e);
        log.info("Out of method updateEtudiant" + e.toString());
    }

}