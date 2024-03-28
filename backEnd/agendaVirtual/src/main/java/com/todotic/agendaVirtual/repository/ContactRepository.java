package com.todotic.agendaVirtual.repository;

import com.todotic.agendaVirtual.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {}
