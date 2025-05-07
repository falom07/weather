package org.example.demo.controllers;

import jakarta.validation.Valid;
import org.example.demo.DTO.UserSignUpDTO;
import org.example.demo.Service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final SignService signService;


    @Autowired
    public RegistrationController(SignService signService) {
        this.signService = signService;
    }

    @GetMapping()
    public String showForm(Model model) {
        model.addAttribute("user", new UserSignUpDTO());
        return "Registration";
    }

    @PostMapping()
    public String handlerForm(@Valid @ModelAttribute("user") UserSignUpDTO user, BindingResult bindingResult) {


        if(signService.isSignUpFormCorrect(bindingResult, user)) {
            Long loginId = signService.saveUser(user);

            return "redirect:/session/create/" + loginId;
        }else {
            return "Registration";
        }
    }

}
