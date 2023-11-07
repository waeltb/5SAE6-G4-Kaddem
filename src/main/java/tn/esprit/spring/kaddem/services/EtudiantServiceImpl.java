package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService{
	@Autowired
	EtudiantRepository etudiantRepository ;
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EquipeRepository equipeRepository;
    @Autowired
    DepartementRepository departementRepository;
	public List<Etudiant> retrieveAllEtudiants(){
		log.info("In method retrieveAllEtudiants");
	List<Etudiant> etudiants = (List<Etudiant>)  etudiantRepository.findAll();
		for (Etudiant etudiant : etudiants) {
			log.info(" Etudiant : " + etudiant);
		}
		log.info("out of method retrieveAllEtudiants");
		return etudiants;
	}


	public Etudiant addEtudiant (Etudiant e){
		Etudiant savedEtudiant = etudiantRepository.save(e);
		log.info("L'étudiant a été enregistré : " + savedEtudiant.toString());
		return savedEtudiant;
	}


	public Etudiant updateEtudiant (Etudiant e){
		Etudiant savedEtudiant = etudiantRepository.save(e);
		log.info("L'étudiant a été modifié : " + savedEtudiant.toString());
		return savedEtudiant;
	}

	public Etudiant retrieveEtudiant(Integer  idEtudiant){
		long start = System.currentTimeMillis();
		log.info("In method retrieveEtudiant");
		Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);
		log.info("out of method retrieveEtudiant");
		long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");
		return etudiant;
	}


	public boolean removeEtudiant(Integer idEtudiant){
		log.info("In method deleteEtudiant");
	Etudiant e=retrieveEtudiant(idEtudiant);
	etudiantRepository.delete(e);
		return false;
	}

	public void assignEtudiantToDepartement (Integer etudiantId, Integer departementId){
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        Departement departement = departementRepository.findById(departementId).orElse(null);
		if (etudiant == null || departement == null) {
			log.error("Impossible d'assigner l'étudiant au département. Etudiant ou département introuvable.");
			return;
		}
        etudiant.setDepartement(departement);
        etudiantRepository.save(etudiant);
	}
	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe){
		Contrat c=contratRepository.findById(idContrat).orElse(null);
		Equipe eq=equipeRepository.findById(idEquipe).orElse(null);
		if (c == null || eq == null) {
			log.error("Impossible d'ajouter et d'assigner l'étudiant au contrat et à l'équipe. Contrat ou équipe introuvable.");
			return null;
		}
		c.setEtudiant(e);
		eq.getEtudiants().add(e);
        return e;
	}

	public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement){
		return  etudiantRepository.findEtudiantsByDepartement_IdDepart((idDepartement));
	}
}
