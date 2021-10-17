package ar.edu.unlam.pb2.videoclub.src;


public class Pelicula extends Media{

    private String nacionalidad;
    
    public Pelicula(String nombre, String codPel, String genero, String actores, String direc, Integer dur, Integer anioDebut, String nacionalidad) {
        super(nombre, codPel, genero, actores, direc, dur, anioDebut);
        this.nacionalidad = nacionalidad;
    }



}
