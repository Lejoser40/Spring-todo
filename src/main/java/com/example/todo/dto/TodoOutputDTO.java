package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TodoOutputDTO {

    private long id;
    private String title;
    private String description;
    private boolean completed;

}
