package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoInputDTO {
    @NotBlank(message = "El titulo no puede estar vacio")
    @Size(max = 100, message = "El titulo no puede tener mas de 100 caracteres")
    private String title;

    private String description;
    private boolean completed;
}