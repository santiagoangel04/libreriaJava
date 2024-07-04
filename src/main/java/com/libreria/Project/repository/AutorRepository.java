package com.libreria.Project.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreria.Project.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long>{
    Optional<Autor> findByAutor(String autor);

    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :year AND a.fallecimiento >= :year")
    List<Autor> listarAutoresRegistradosVivosPorAno(int year);
} 
