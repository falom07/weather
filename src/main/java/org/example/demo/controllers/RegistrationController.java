package org.example.demo.controllers;

import org.example.demo.DTO.UserRegistrationDTO;
import org.example.demo.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "Registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@ModelAttribute("user") UserRegistrationDTO user) {
        //todo check and save and validate
        System.out.println(user);
        return "redirect:/registration";
    }
}
