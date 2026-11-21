package ca.sheridancollege.halaayoub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.halaayoub.beans.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}