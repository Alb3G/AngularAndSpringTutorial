package com.todotic.agendaVirtual.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {
    @NotBlank(message = "Name must not be null or empty")
    @NotNull(message = "Name must not be null or empty")
    private String name;

    @Email(message = "Email not valid")
    @NotBlank(message = "Email must not be null or empty")
    @NotNull(message = "Email must not be null or empty")
    private String email;
}
