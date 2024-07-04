package com.libreria.Project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreria.Project.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
}
