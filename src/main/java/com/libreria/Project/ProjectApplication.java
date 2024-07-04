package com.libreria.Project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.libreria.Project.principal.Principal;
import com.libreria.Project.repository.AutorRepository;
import com.libreria.Project.repository.LibroRepository;


@SpringBootApplication
public class ProjectApplication implements CommandLineRunner{

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository;


	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.menuP();
	}
}
