package alura.challenge.libreria.repositorio;

import alura.challenge.libreria.modelo.Autor;
import alura.challenge.libreria.modelo.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libros,Long>  {
    List<Libros> findBylenguaje(String lenguaje);
}
