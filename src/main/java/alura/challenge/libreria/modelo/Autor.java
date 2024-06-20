package alura.challenge.libreria.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @JsonAlias("name")private String nombre;
    @JsonAlias("birth_year")private double nacimiento;
    @JsonAlias("death_year")private double muerte;
    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Libros> libro;

    public Autor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(double nacimiento) {
        this.nacimiento = nacimiento;
    }

    public double getMuerte() {
        return muerte;
    }

    public void setMuerte(double muerte) {
        this.muerte = muerte;
    }

    public List<Libros> getLibro() {
        return libro;
    }

    public void setLibro(List<Libros> libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", muerte=" + muerte +
                ", libro=" + libro ;
    }
}
