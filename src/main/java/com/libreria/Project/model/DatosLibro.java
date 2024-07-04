package com.libreria.Project.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") List<DataAutor> autor,
    @JsonAlias("languages") List<String> idiomas,
    @JsonAlias("download_count") Long descargas
) {
    
}
