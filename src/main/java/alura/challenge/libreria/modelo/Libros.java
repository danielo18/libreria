package alura.challenge.libreria.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="Libros")
public class Libros {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @JsonAlias("title")private String titulo;
    @JsonAlias("languages")private String lenguaje;
    @JsonAlias("download_count")private double descargas;
    @ManyToOne
    private Autor autor;

    public Libros() {
    }

    public Libros(String titulo, double descargas, ArrayList lenguaje) {
        this.titulo=titulo;
        this.descargas=descargas;
        this.lenguaje= String.join(",",lenguaje);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(List<String> lenguaje) {
        this.lenguaje = String.join(",",lenguaje);
    }

    public double getDescargas() {
        return descargas;
    }

    public void setDescargas(double descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", lenguaje='" + lenguaje + '\'' +
                ", descargas=" + descargas ;
    }
}
