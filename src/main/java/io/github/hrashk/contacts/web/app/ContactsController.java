package io.github.hrashk.contacts.web.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ContactsController {
    private final ContactsService service;

    @GetMapping("/")
    public String show(Model model) {
        model.addAttribute("contacts", service.findAllContacts());

        return "index";
    }
}
