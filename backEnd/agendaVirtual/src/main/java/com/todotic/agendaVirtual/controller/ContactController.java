package com.todotic.agendaVirtual.controller;

import com.todotic.agendaVirtual.dto.ContactDto;
import com.todotic.agendaVirtual.entity.Contact;
import com.todotic.agendaVirtual.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/contacts")
@RestController
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public Iterable<Contact> list() {
        return contactService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Contact get(@PathVariable Integer id) {
        return contactService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contact create(@Validated @RequestBody ContactDto cDto) {
        return contactService.createContact(cDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable("id") Integer id, @Validated @RequestBody ContactDto cDto) {
        return contactService.updateContact(id, cDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteContact(@PathVariable("id") Integer id) {
        contactService.deleteContact(id);
    }
}
