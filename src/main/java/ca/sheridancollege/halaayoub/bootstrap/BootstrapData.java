package ca.sheridancollege.halaayoub.bootstrap;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.halaayoub.beans.Appointment;
import ca.sheridancollege.halaayoub.beans.AppointmentStatus;
import ca.sheridancollege.halaayoub.beans.Doctor;
import ca.sheridancollege.halaayoub.beans.Patient;
import ca.sheridancollege.halaayoub.repositories.AppointmentRepository;
import ca.sheridancollege.halaayoub.repositories.DoctorRepository;
import ca.sheridancollege.halaayoub.repositories.PatientRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;

    @Override
    public void run(String... args) throws Exception {

        if (appointmentRepository.count() > 0) {
            return;
        }

        Doctor doctor1 = new Doctor(
                null,
                "Sarah",
                "Smith",
                "Family Medicine",
                "905-555-1111",
                "sarah.smith@clinic.ca"
        );

        Doctor doctor2 = new Doctor(
                null,
                "David",
                "Brown",
                "Dermatology",
                "905-555-2222",
                "david.brown@clinic.ca"
        );

        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        Patient patient1 = new Patient(
                null,
                "Hala",
                "Ayoub",
                "hala@email.com",
                "647-555-3333"
        );

        Patient patient2 = new Patient(
                null,
                "Hoang",
                "Nguyen",
                "hoang@email.com",
                "647-555-4444"
        );

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        Appointment appointment1 = new Appointment(
                null,
                LocalDateTime.now().plusDays(1),
                "General checkup",
                AppointmentStatus.BOOKED,
                doctor1,
                patient1
        );

        Appointment appointment2 = new Appointment(
                null,
                LocalDateTime.now().plusDays(2),
                "Skin consultation",
                AppointmentStatus.BOOKED,
                doctor2,
                patient2
        );

        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
    }
}