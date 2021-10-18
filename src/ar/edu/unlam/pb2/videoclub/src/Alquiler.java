package ar.edu.unlam.pb2.videoclub.src;

public class Alquiler {
    private Media peliAlquilar;
    private Integer dni;
    private String fechaDeAlquiler;
    private Integer cantDiasDeAlquiler;
    private Boolean estadoAlquiler;

    public Alquiler(Media peliAlquilar, Integer dni, String fechaDeAlquiler, Integer cantDiasDeAlquiler) {
        this.peliAlquilar = peliAlquilar;
        this.dni = dni;
        this.fechaDeAlquiler = fechaDeAlquiler;
        this.cantDiasDeAlquiler = cantDiasDeAlquiler;
        this.estadoAlquiler = true;
    }

    public Media getPeliAlquilar() {
        return peliAlquilar;
    }

    public Integer getDni() {
        return dni;
    }

    public String getFechaDeAlquiler() {
        return fechaDeAlquiler;
    }

    public Integer getCantDiasDeAlquiler() {
        return cantDiasDeAlquiler;
    }

    public Boolean getEstadoAlquiler() {
        return estadoAlquiler;
    }

    public void setEstadoAlquiler(Boolean est){
        this.estadoAlquiler = est;
    }
}
