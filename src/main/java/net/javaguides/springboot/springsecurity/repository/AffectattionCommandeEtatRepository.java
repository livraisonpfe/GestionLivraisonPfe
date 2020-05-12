package net.javaguides.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.javaguides.springboot.springsecurity.model.AffectationCommandeEtat;
import net.javaguides.springboot.springsecurity.model.AffectationCommandeEtatId;
@Repository
public interface AffectattionCommandeEtatRepository extends JpaRepository<AffectationCommandeEtat,AffectationCommandeEtatId>{

}
