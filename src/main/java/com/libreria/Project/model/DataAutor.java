package com.libreria.Project.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignorar los items no puestos en este record
@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAutor(
    @JsonAlias("name") String nombre,
    @JsonAlias("birth_year") Integer nacimiento,
    @JsonAlias("death_year") Integer fallecimiento
) {
}
