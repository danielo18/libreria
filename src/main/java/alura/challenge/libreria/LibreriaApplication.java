package alura.challenge.libreria;

import alura.challenge.libreria.principal.Principal;
import alura.challenge.libreria.repositorio.AutorRepository;
import alura.challenge.libreria.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class LibreriaApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repositoryLibro;
	@Autowired
	private AutorRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(LibreriaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositoryLibro,repository);//repository
		principal.muestraElMenu();

	}
}