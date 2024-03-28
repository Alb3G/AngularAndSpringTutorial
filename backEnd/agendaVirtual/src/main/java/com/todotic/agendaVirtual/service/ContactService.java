package com.todotic.agendaVirtual.service;

import com.todotic.agendaVirtual.dto.ContactDto;
import com.todotic.agendaVirtual.entity.Contact;
import com.todotic.agendaVirtual.exception.ResourceNotFoundException;
import com.todotic.agendaVirtual.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper mapper;

    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact findById(Integer id) {
        return contactRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Contact createContact(@RequestBody ContactDto cDto) {
        Contact c = mapper.map(cDto, Contact.class);
        // Setteamos la fecha de creacion nosotros.
        c.setCreatedAt(LocalDateTime.now());
        return contactRepository.save(c);
    }

    public Contact updateContact(@PathVariable("id") Integer id, @RequestBody ContactDto cDto) {
        Contact contacFromDb = findById(id);
        // Aquí como el objeto de tipo contact ya existe se lo pasamos para actualizarlo.
        mapper.map(cDto, contacFromDb);
        return contactRepository.save(contacFromDb);
    }

    public void deleteContact(@PathVariable("id") Integer id) {
        Contact contactFromDb = findById(id);
        contactRepository.delete(contactFromDb);
    }
}
