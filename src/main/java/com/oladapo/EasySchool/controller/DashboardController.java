package com.oladapo.EasySchool.controller;

import com.oladapo.EasySchool.model.Person;
import com.oladapo.EasySchool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model,Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(null != person.getEasyClass() && null != person.getEasyClass().getName()){
            model.addAttribute("enrolledClass", person.getEasyClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }

}