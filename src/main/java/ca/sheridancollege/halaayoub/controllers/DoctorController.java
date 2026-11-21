package ca.sheridancollege.halaayoub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.halaayoub.repositories.DoctorRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class DoctorController {

    private DoctorRepository doctorRepository;

    @GetMapping("/doctors")
    public String doctors(Model model) {

        model.addAttribute(
                "doctorList",
                doctorRepository.findAll()
        );

        return "doctors";
    }
}