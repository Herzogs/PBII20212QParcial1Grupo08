package ar.edu.unlam.pb2.videoclub.src;

public abstract class Media {

    private String nombre; // nombre de la pelicula o serie xej: Casa de Papel - Temp 1
    private String codPel; // codigo de pelicula o serie
    private String genero; 
    private String actores; 
    private String direc; //director
    private Integer dur; //duracion
    private Integer anioDebut;
    private Integer cantEjemplares;

    public Media(String nombre, String codPel, String genero, String actores, String direc, Integer dur, Integer anioDebut) {
        this.nombre = nombre;
        this.codPel = codPel;
        this.genero = genero;
        this.actores = actores;
        this.direc = direc;
        this.dur = dur;
        this.anioDebut = anioDebut;
        this.cantEjemplares = 0;
    }


    public String getNombre() {
        return nombre;
    }

    public String getCodPel() {
        return codPel;
    }

    public String getGenero() {
        return genero;
    }

    public String getActores() {
        return actores;
    }

    public String getDirec() {
        return direc;
    }

    public Integer getDur() {
        return dur;
    }

    public Integer getAnioDebut() {
        return anioDebut;
    }

    public void agregarCopia (Integer cant){

        this.cantEjemplares += cant;
    }

    public Integer getCantEjemplares() {
        return cantEjemplares;
    }

    public void decremtentarEjemplar (){
        this.cantEjemplares--;
    }
}