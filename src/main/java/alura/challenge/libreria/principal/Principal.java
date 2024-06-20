package alura.challenge.libreria.principal;

import alura.challenge.libreria.modelo.Autor;
import alura.challenge.libreria.modelo.Datos;
import alura.challenge.libreria.modelo.DatosLibro;
import alura.challenge.libreria.modelo.Libros;
import alura.challenge.libreria.repositorio.AutorRepository;
import alura.challenge.libreria.repositorio.LibroRepository;
import alura.challenge.libreria.services.ConvierteDatos;
import alura.challenge.libreria.services.consumoAPI;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private consumoAPI consumoApi = new consumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositoryLibro;
    private AutorRepository repository;
    private Scanner teclado = new Scanner(System.in);
    private String URLlibros = "https://gutendex.com/books/";
    private String URLlib =URLlibros;
    private List<Libros> libreria;
    private List<Autor> libAutores;
    private int opcional;


    public Principal(LibroRepository repositoryLibro, AutorRepository repository) {
        this.repository = repository;
        this.repositoryLibro = repositoryLibro;
    }


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    LIBRERIA ALURA DANIEL
                    ********************************
                    1 - Buscar libro 
                    2 - Mostrar Libros
                    3 - Mostrar Autores     
                    4 - Mostrar Autores vivos en determinado año 
                    5 - Listado de libros por Idioma                
                    0 - Salir
                    ********************************
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibros();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    mostrarAutoresVivosporAnio();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        System.out.println("ADIOS");
        System.exit(0);
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("""
                ******************************
                Que idioma quieres buscar:
                en->Ingles
                es->Español
                pt->Portugues
                de->Aleman
                ******************************
                """);
        String idioma=teclado.nextLine();
        if(idioma.length()==2) {
            List<Libros> libroLenguaje = repositoryLibro.findBylenguaje(idioma);
            libroLenguaje.forEach(System.out::println);
        }else {
            System.out.println("No escogiste una de las opciones ");
        }

    }

    private void mostrarAutoresVivosporAnio() {
        System.out.println("Proporciona el año de busqueda");
        try {
            double anio = teclado.nextDouble();
            teclado.nextLine();
            if(anio<=2024) {
                libAutores = repository.findAll();

                libAutores.stream().filter(au -> anio < au.getMuerte() && anio > au.getNacimiento())
                        .forEach(System.out::println);
            }else{
                System.out.println("Ese año aun no llega");
            }
        }catch(InputMismatchException e){
            System.out.println("No proporcionaste un valor correcto");
            teclado.nextLine();
        }


    }

    private void mostrarAutores() {
        libAutores=repository.findAll();
        libAutores.stream().forEach(System.out::println);

    }

    public void buscarLibroWeb() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        int i = validarLibroExistente(nombreLibro);
        URLlib=URLlibros;
        for (; i < 10; i++) {

            var json = consumoApi.obtenerDatos(URLlib);
            Datos datos = conversor.obtenerDatos(json, Datos.class);
            List<DatosLibro> lib = datos.libros();
            Optional<DatosLibro> libroEncontrado = lib.stream()
                    .filter(l -> l.titulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                    .findFirst();
            if (libroEncontrado.isPresent()) {
                Autor autor = libroEncontrado.get().autor().get(0);
                List<Libros> l = libroEncontrado.stream()
                        .map(libr -> new Libros(libr.titulo(), libr.descargas(), libr.lenguaje()))
                        .collect(Collectors.toList());

               List<Autor> au=repository.findBynombre(autor.getNombre());
               if (au.isEmpty()){
                   repository.save(autor);
               }else{
                   autor=au.get(0);
               }
                Libros libro = new Libros(libroEncontrado.get().titulo(), libroEncontrado.get().descargas(), libroEncontrado.get().lenguaje());
                libro.setAutor(autor);
                repositoryLibro.save(libro);
                System.out.println(libro);
                i=10;
            } else {
                URLlib = datos.urlSiguiente();
            }
        }
        if(i==10){
            System.out.println("***Libro no encontrado***");
        }
        if(i==12){
            System.out.println("********Libro ya esta en la base de datos********");
        }
    }

    public void mostrarLibros() {
        libreria=repositoryLibro.findAll();
        libreria.stream().forEach(System.out::println);
    }

    public int validarLibroExistente(String tituloLibro){
        opcional=0;
        libreria=repositoryLibro.findAll();
        libreria.stream().forEach(l->{
            if(l.getTitulo().toLowerCase().contains(tituloLibro.toLowerCase())){
                opcional=12;
            }
        });
        return opcional;
    }
}

