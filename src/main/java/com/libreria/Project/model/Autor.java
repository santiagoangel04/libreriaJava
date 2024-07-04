package com.libreria.Project.model;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//creacion de la entidad en la base de datos y nombre
@Entity
@Table(name = "autores")
public class Autor {
    //llave primaria presente en la dba
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    // un solo autor en la tabla, sin duplicados
    @Column(unique = true)
    private String autor;
    private Integer nacimiento;
    private Integer fallecimiento;

    //relacion; foreign key
    @OneToMany(mappedBy = "autor",fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosLibro datosLibro){
        //autores nulos
        if (datosLibro.autor().isEmpty()) {
            this.autor = null;
            this.nacimiento = null;
            this.fallecimiento = null;
        }else{
            this.autor = datosLibro.autor().get(0).nombre();
            this.nacimiento = datosLibro.autor().get(0).nacimiento();
            this.fallecimiento = datosLibro.autor().get(0).fallecimiento();
        }
    }

    // Getter and Setter methods
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Libro libros) {
        libros.setAutor(this);
    }

    @Override
    public String toString() {
        String nombreAutor = autor == null ? "Autor: Dato no registrado" : "Autor: " + autor;
        String fechaInicial = nacimiento == null ? "Fecha de nacimiento: Dato no registrado" : "Fecha de nacimiento: " + nacimiento;
        String fechaFinal = fallecimiento == null ? "Fecha de fallecimiento: Dato no registrado" : "Fecha de fallecimiento: " + fallecimiento;
        return "--------------------------------" +
                "\n"+ nombreAutor  +
                ", \n nacimiento=" + fechaInicial +
                ", \n fallecimiento=" + fechaFinal +
                ", \n libros=" + libros.stream().map(l->l.getTitulo()).collect(Collectors.toList()) + "\n-----------------------------";
    }
}
