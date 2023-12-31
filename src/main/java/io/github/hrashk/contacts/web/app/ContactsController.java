package io.github.hrashk.contacts.web.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactsController {
    private final ContactsService service;

    @GetMapping("/")
    public String show(Model model) {
        model.addAttribute("contacts", service.findAllContacts());

        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteContact(id);

        return "redirect:/";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("isEdit", false);
        model.addAttribute("contact", Contact.builder().id(0).build());

        return "create";
    }

    @PostMapping("/create")
    public String create(Contact contact) {
        service.createContact(contact);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("isEdit", true);
        model.addAttribute("contact", service.findById(id));

        return "create";
    }

    @PostMapping("/edit")
    public String edit(Contact contact) {
        service.editContact(contact);

        return "redirect:/";
    }
}
