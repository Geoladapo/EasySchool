package com.oladapo.EasySchool.controller;

import com.oladapo.EasySchool.model.Person;
import com.oladapo.EasySchool.services.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping("/createUser")
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors){
        if(errors.hasErrors()){
            return "register";
        }
        boolean isSaved = personService.createNewPerson(person);
        if(isSaved){
            return "redirect:/login?register=true";
        }else {
            return "register";
        }
    }
}
