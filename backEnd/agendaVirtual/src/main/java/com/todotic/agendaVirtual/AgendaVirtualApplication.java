package com.todotic.agendaVirtual;

import com.todotic.agendaVirtual.entity.Contact;
import com.todotic.agendaVirtual.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AgendaVirtualApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgendaVirtualApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ContactRepository contactRepository) {
		return (String...args) -> {
				List<Contact> contactList = Arrays.asList(
						new Contact("Carlos", "carlos@gmail.com", LocalDateTime.now()),
						new Contact("Juan", "Juan@gmail.com", LocalDateTime.now()),
						new Contact("Ana", "Ana@gmail.com", LocalDateTime.now()),
						new Contact("Maria", "Maria@gmail.com", LocalDateTime.now()),
						new Contact("Jose", "Jose@gmail.com", LocalDateTime.now()),
						new Contact("Lucia", "Lucia@gmail.com", LocalDateTime.now())
				);
				contactRepository.saveAll(contactList);
		};
	}

	@Bean
	ModelMapper modelMapper() {
		return  new ModelMapper();
	}
}