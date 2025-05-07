package org.example.demo.controllers;

import org.example.demo.DTO.UserSignInDTO;
import org.example.demo.Service.SignService;
import org.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
@Controller
public class LogInController {

    private final SignService signService;

    @Autowired
    public LogInController(SignService signService) {
        this.signService = signService;
    }

    @GetMapping()
    public String openLoginPage(Model model) {
        model.addAttribute("user",new UserSignInDTO());
        return "login";
    }

    @PostMapping
    public String handleLogin(@ModelAttribute("user") UserSignInDTO user,  BindingResult bindingResult) {
        if(signService.isSignInFormCorrect(bindingResult,user)){
            Long loginId = signService.getUserByLogin(user.getLogin());
            return "redirect:/session/create/" + loginId;
        }else {
            return "login";
        }


    }
}
