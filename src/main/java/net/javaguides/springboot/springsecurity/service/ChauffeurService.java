package net.javaguides.springboot.springsecurity.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.javaguides.springboot.springsecurity.exception.RecordNotFoundException;
import net.javaguides.springboot.springsecurity.model.Chauffeur;
import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.repository.ChauffeurRepository;
import net.javaguides.springboot.springsecurity.web.dto.ChauffeurRegistrationDto;

@Service
public class ChauffeurService {
	@Autowired
	private ChauffeurRepository chauffeurrepositry;

	public Page<ChauffeurRegistrationDto> getAllChauffeur(Pageable pageable) {
		List<ChauffeurRegistrationDto> result = chauffeurrepositry.affichageChauff();
		// convertir une liste a une page
		Page<ChauffeurRegistrationDto> page = new PageImpl<ChauffeurRegistrationDto>(result, pageable, result.size());

		return page;
	}

	public Chauffeur getChauffeurById(Long id) throws RecordNotFoundException {
		Optional<Chauffeur> c = chauffeurrepositry.findById(id);

		if (c.isPresent()) {
			return c.get();
		} else {
			throw new RecordNotFoundException("il n'ya pas de chauffeur avec cet id");
		}

	}

	public Optional<Chauffeur> findById(Long id) throws RecordNotFoundException {
		return chauffeurrepositry.findById(id);
	}

	public void deleteChauffeur(Chauffeur v) // throws RecordNotFoundException
	{
		chauffeurrepositry.delete(v);

	}

	public void deleteChauffeurById(Long id) {

		chauffeurrepositry.deleteById(id);

	}

	public Chauffeur addChauffeur(Chauffeur c) {
		// c'est pour faire l'ajout
		if (c.getId() == null) {
			//c.setRoles(Arrays.asList(new Role("ROLE_CHAUFFEUR")));
			c = chauffeurrepositry.save(c);

			return c;

		} else {
			// c'est pour le cas de modification récupération des valeurs de l'objet
			// chauffeur à partir de son id
			Optional<Chauffeur> chauffeur = chauffeurrepositry.findById(c.getId());

			if (chauffeur.isPresent() && (c.getEtatDispo().getId() != null)) {
				Chauffeur newEntity = chauffeur.get();
				newEntity.setNom((c.getNom()));
				newEntity.setPrenom((c.getPrenom()));
				newEntity.setDateNai(c.getDateNai());
				newEntity.setEmail((c.getEmail()));
				newEntity.setEtatDispo(c.getEtatDispo());
				newEntity.setCin(c.getCin());
				newEntity.setNumPermis(c.getNumPermis());
				newEntity.setNumTel(c.getNumTel());
				//newEntity.setRoles(Arrays.asList(new Role("ROLE_CHAUFFEUR")));
				newEntity = chauffeurrepositry.save(newEntity);

				return newEntity;
			} else {
				c = chauffeurrepositry.save(c);
				System.out.print("save1");

				return c;
			}

		}

	}

	// faire la recherche par nom
	public Page<Chauffeur> findByNom(String nom, Pageable pageable) {
		return chauffeurrepositry.findByNomContainingIgnoreCase(nom, pageable);

	}

//afficher la liste des chauffeurs disponibles sur le garnd tunis
	public List<ChauffeurRegistrationDto> chauffDispoTunis() {
		return chauffeurrepositry.affichageChauffDispoGrnTun();

	}

	// afficher la liste des chauffeurs disponibles sur tous les gouvernerat
	public List<ChauffeurRegistrationDto> chauffDispoGouver() {
		return chauffeurrepositry.affichageChauffDispoHorGrnTun();

	}

	// afficher la liste des chauffeurs disponibles
	public List<ChauffeurRegistrationDto> chauffDipo() {
		return chauffeurrepositry.affichageChauffDispo();

	}
	public int nbreTotalC() {
		int nbre=0;
		nbre=chauffeurrepositry.findNumberChauff();
		return nbre;
	}
}
