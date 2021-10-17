package ar.edu.unlam.pb2.videoclub.src;


public class Serie extends Media{

    private String distr; //distribuidora


    public Serie(String nombre, String codPel, String genero, String actores, String direc, Integer dur, Integer anioDebut, String distr) {
        super(nombre, codPel, genero, actores, direc, dur, anioDebut);
        this.distr = distr;

    }
}
