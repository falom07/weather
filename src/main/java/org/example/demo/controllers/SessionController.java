package org.example.demo.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.demo.Repository.SessionRepository;
import org.example.demo.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/session")
@Controller
public class SessionController {
    private final SessionService sessionService;


    @Autowired
    public SessionController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @GetMapping("/create/{loginID}")
    public String createSession(HttpSession session, @PathVariable("loginID") Long loginID) {

        UUID uuid = sessionService.saveSession(loginID);

        session.setAttribute("loginID", loginID);
        session.setAttribute("SessionID", uuid);


        return "redirect:/cookie/create/" + uuid;
    }

}
