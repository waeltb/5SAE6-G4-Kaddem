package tn.esprit.spring.kaddem;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMockitou {
    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Test
    @Order(4)
    public void testRetrieveAllDepartements() {

        List<Departement> departements = Arrays.asList(
                new Departement(1, "Test1", null),
                new Departement(2, "Test2", null)
        );
        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> result = departementService.retrieveAllDepartements();

        assertEquals(departements, result);
    }

    @Test
    @Order(0)
    public void testAddDepartement() {

        Departement departementToAdd = new Departement(1, "Test", null);
        when(departementRepository.save(departementToAdd)).thenReturn(departementToAdd);

        Departement result = departementService.addDepartement(departementToAdd);

        // Assert
        assertEquals(departementToAdd, result);
    }

    @Test
    @Order(1)
    public void testUpdateDepartement() {
        Departement departementToUpdate = new Departement(1, "Test", null);
        when(departementRepository.save(departementToUpdate)).thenReturn(departementToUpdate);

        Departement result = departementService.updateDepartement(departementToUpdate);

        assertEquals(departementToUpdate, result);
    }

    @Test
    @Order(2)
    public void testRetrieveDepartement() {
        int id = 1;
        Departement departement = new Departement(id, "Test", null);
        when(departementRepository.findById(id)).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveDepartement(id);

        assertEquals(departement, result);
    }

    @Test
    @Order(3)
    public void testDeleteDepartement() {
        int id = 1;
        Departement departementToDelete = new Departement(id, "Test", null);
        when(departementRepository.findById(id)).thenReturn(Optional.of(departementToDelete));

        departementService.deleteDepartement(id);
        verify(departementRepository, times(1)).delete(departementToDelete);
    }

}