package ca.sheridancollege.halaayoub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.halaayoub.repositories.PatientRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public String patients(Model model) {

        model.addAttribute(
                "patientList",
                patientRepository.findAll()
        );

        return "patients";
    }
}