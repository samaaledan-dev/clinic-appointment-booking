package ca.sheridancollege.halaayoub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.halaayoub.beans.Appointment;
import ca.sheridancollege.halaayoub.beans.AppointmentStatus;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    List<Appointment>
    findByPatientFirstNameContainingIgnoreCase(String firstName);

    List<Appointment>
    findByStatus(AppointmentStatus status);
}