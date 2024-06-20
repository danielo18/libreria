package alura.challenge.libreria.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title")String titulo,
        @JsonAlias("languages")ArrayList lenguaje,
        @JsonAlias("download_count")double descargas,
        @JsonAlias("authors")List<Autor> autor
        ) {
}
