package com.libreria.Project.principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;



import com.libreria.Project.model.Autor;
import com.libreria.Project.model.Datos;
import com.libreria.Project.model.DatosLibro;
import com.libreria.Project.model.Libro;
import com.libreria.Project.repository.AutorRepository;
import com.libreria.Project.repository.LibroRepository;
import com.libreria.Project.service.ConversionDatos;
import com.libreria.Project.service.apiConsumo;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private String URL_BASE = "https://gutendex.com/books/";
    private String OTHER_URL = "?search=";
    private apiConsumo api = new apiConsumo();
    private ConversionDatos conversor = new ConversionDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void menuP(){
        var opcion = -1;
        while (opcion != 0) {
            System.out.println(
                """
                1 - Buscar libro por titulo
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos en un determinado año
                5 - Listar libros por idioma
                0 - Salir        
                """
            );
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    List<Autor> listaAutores = autorRepository.findAll();
                    listaAutores.forEach(System.out::println);
                    break;
                case 4:
                case 5:
                
                default:
                    break;
            }
        }
    }

    public void buscarLibroPorTitulo() {
        System.out.println("Ingrese el título del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = api.obtenerDatos(URL_BASE + OTHER_URL + tituloLibro.replace(" ", "+"));
        Datos consultaDatosGenerales = conversor.obtenerDatos(json, Datos.class);
        if (!consultaDatosGenerales.resultados().isEmpty()) {
            DatosLibro consultaDatosLibro = consultaDatosGenerales.resultados().get(0);
            Autor consultaAutor = new Autor(consultaDatosLibro);
            Libro consultaLibro = new Libro(consultaDatosLibro);
            //Basta con usar setLibros() porque en ese mismo método se usa setAuthor
            consultaAutor.setLibros(consultaLibro);
            Optional<Libro> libroRegistrado = libroRepository.findByTituloContainsIgnoreCase(consultaLibro.getTitulo());
            if (libroRegistrado.isPresent()) {
                System.out.println("Libro ya registrado");
            } else {
                Optional<Autor> autorRegistrado = autorRepository.findByAutor(consultaAutor.getAutor());
                if (autorRegistrado.isPresent()) {
                    consultaLibro.setAutor(autorRegistrado.get());
                    //Se guarda solo el Libro relacionandolo a un Autor existente
                    libroRepository.save(consultaLibro);
                } else {
                    //Se guarda primero el Autor y luego el Libro
                    autorRepository.save(consultaAutor);
                    libroRepository.save(consultaLibro);
                }
                System.out.println(consultaLibro);
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }


    public void listarLibros(){
        libroRepository.findAll().forEach(System.out::println);
    }

    
}
