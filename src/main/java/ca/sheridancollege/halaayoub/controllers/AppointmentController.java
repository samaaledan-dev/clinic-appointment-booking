package ca.sheridancollege.halaayoub.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.sheridancollege.halaayoub.beans.Appointment;
import ca.sheridancollege.halaayoub.beans.AppointmentStatus;
import ca.sheridancollege.halaayoub.repositories.AppointmentRepository;
import ca.sheridancollege.halaayoub.repositories.DoctorRepository;
import ca.sheridancollege.halaayoub.repositories.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AppointmentController {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    @GetMapping("/")
    public String index(
            Model model,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) AppointmentStatus status) {

        List<Appointment> appointmentList;

        if (search != null && !search.isBlank()) {

            appointmentList =
                    appointmentRepository
                    .findByPatientFirstNameContainingIgnoreCase(search);

        } else if (status != null) {

            appointmentList =
                    appointmentRepository.findByStatus(status);

        } else {

            appointmentList =
                    appointmentRepository.findAll();
        }

        model.addAttribute(
                "appointmentList",
                appointmentList);

        model.addAttribute(
                "doctorCount",
                doctorRepository.count());

        model.addAttribute(
                "patientCount",
                patientRepository.count());

        model.addAttribute(
                "appointmentCount",
                appointmentRepository.count());

        model.addAttribute(
                "search",
                search);

        model.addAttribute(
                "selectedStatus",
                status);

        model.addAttribute(
                "statusList",
                AppointmentStatus.values());

        return "index";
    }

    @GetMapping("/addAppointment")
    public String addAppointment(Model model) {

        model.addAttribute(
                "appointment",
                new Appointment());

        model.addAttribute(
                "doctorList",
                doctorRepository.findAll());

        model.addAttribute(
                "patientList",
                patientRepository.findAll());

        model.addAttribute(
                "statusList",
                AppointmentStatus.values());

        return "appointmentForm";
    }

    @PostMapping("/saveAppointment")
    public String saveAppointment(
            @Valid @ModelAttribute Appointment appointment,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "doctorList",
                    doctorRepository.findAll());

            model.addAttribute(
                    "patientList",
                    patientRepository.findAll());

            model.addAttribute(
                    "statusList",
                    AppointmentStatus.values());

            return "appointmentForm";
        }

        boolean isNewAppointment =
                appointment.getId() == null;

        appointmentRepository.save(appointment);

        if (isNewAppointment) {

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Appointment booked successfully!");

        } else {

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Appointment updated successfully!");
        }

        return "redirect:/";
    }

    @GetMapping("/editAppointment/{id}")
    public String editAppointment(
            @PathVariable Long id,
            Model model) {

        Appointment appointment =
                appointmentRepository
                .findById(id)
                .orElseThrow();

        model.addAttribute(
                "appointment",
                appointment);

        model.addAttribute(
                "doctorList",
                doctorRepository.findAll());

        model.addAttribute(
                "patientList",
                patientRepository.findAll());

        model.addAttribute(
                "statusList",
                AppointmentStatus.values());

        return "appointmentForm";
    }

    @GetMapping("/deleteAppointment/{id}")
    public String deleteAppointment(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        appointmentRepository.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Appointment deleted successfully!");

        return "redirect:/";
    }
}