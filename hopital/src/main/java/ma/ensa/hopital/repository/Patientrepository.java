package ma.ensa.hopital.repository;

import ma.ensa.hopital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Patientrepository extends JpaRepository< Patient , Long> {


	//recherche
	Page<Patient> findByNomContains(String keyword , Pageable pageable);






}
