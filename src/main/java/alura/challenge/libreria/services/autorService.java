package alura.challenge.libreria.services;

import alura.challenge.libreria.modelo.Autor;
import alura.challenge.libreria.repositorio.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class autorService {
    @Autowired
    private AutorRepository repository;

    public List<Autor> obtenerTodosLosAutores(String nombre) {
        List<Autor> autor = repository.findBynombre(nombre);
        return autor;
    }

}
