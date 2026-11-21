package ca.sheridancollege.halaayoub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.halaayoub.beans.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}