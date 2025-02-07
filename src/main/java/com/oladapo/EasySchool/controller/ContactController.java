package com.oladapo.EasySchool.controller;

import com.oladapo.EasySchool.model.Contact;
import com.oladapo.EasySchool.services.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String displayContactPage() {
        return "contact";
    }

    @PostMapping("/saveMsg")
    public ModelAndView saveMessage(Contact contact) {
        contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }
}
