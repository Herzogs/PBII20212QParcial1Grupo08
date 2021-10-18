package ar.edu.unlam.pb2.videoclub.src;
import java.util.ArrayList;



public class Videoclub {

    private ArrayList<Cliente> listaCliente;
    private ArrayList<Alquiler> listaAlquileres;
    private ArrayList<Alquiler> listaDevoluciones;
    private ArrayList<Media> catalogo;
    private final String nombre;
    private final Integer MAX_CANT_ALQUILERES_PERMITIDOS_POR_CLIENTE = 2;
    private static final Boolean DESBLOQUEAR_USUARIO = false;

    public Videoclub(String nombre) {
        this.nombre = nombre;
        this.listaCliente = new ArrayList<Cliente>();
        this.listaAlquileres = new ArrayList<Alquiler>();
        this.listaDevoluciones = new ArrayList<Alquiler>();
        this.catalogo = new ArrayList<Media>();
    }

    public Boolean aniadirCliente(Persona pers){
        Boolean est = false;
        Boolean enc = false;
        for (int i = 0; i < this.listaCliente.size() && !enc; i++) {
            if(this.listaCliente.get(i).getDni().equals(pers.getDni()))
                enc = true;
        }
        if(!enc){
            this.listaCliente.add(new Cliente(pers.getNombre(), pers.getApellido(), pers.getDni(), pers.getDir()));
            est = true;
        }
        return est;
    }

    /******************************* METODOS PRIVADOS ********************************************************/
   /* private Integer getCantidadDePeliculasAlquiladasXDNI (Cliente cli){
        Integer cant = 0;
        for (Cliente aux: this.listaCliente) {
            if(cli.getDni().equals(aux.getDni()))
                cant++;
        }
        return cant;
    }*/

    private Integer buscarClienteXDNI( Integer dni){
        Boolean enc = false;
        Integer i=0;
        for (; i < this.listaCliente.size() && !enc; i++) {
            if(this.listaCliente.get(i).getDni().equals(dni))
                enc = true;
        }
        return enc? i-1 : -1;
    }

    private Integer buscarAlquiler(String cod, Integer dni){
        Boolean enc = false;
        Integer i = 0;
        for (; i < this.listaAlquileres.size() && !enc; i++) {
            if(this.listaAlquileres.get(i).getPeliAlquilar().getCodPel().equals(cod) && this.listaAlquileres.get(i).getDni().equals(dni))
                enc = true;
        }
        return enc? i : -1;
    }

    private Integer buscarMediaXCod (String cod){
        Boolean enc = false;
        Integer i = 0;
        for (; i < this.catalogo.size() && !enc; i++) {
            if(this.catalogo.get(i).getCodPel().equals(cod))
                enc = true;
        }
        return enc? i-1 : -1;
    }

    /**********************************************************************************************************************/

    public Cliente getClienteByDNI(Integer dni){
        Boolean enc = false;
        Integer i=0;
        for (; i < this.listaCliente.size() && !enc; i++) {
            if(this.listaCliente.get(i).getDni().equals(dni))
                enc = true;
        }
        return enc? this.listaCliente.get(i-1) : null;
    }

    public Boolean agregarCatalogo (Media nuevo){
        Boolean est = false;
        Boolean enc = false;
        for (int i = 0; i < this.catalogo.size() && !enc; i++) {
            if(this.catalogo.get(i).getCodPel().equals(nuevo.getCodPel()))
                enc = true;
        }
        if(!enc){
            this.catalogo.add(nuevo);
            est = true;
        }
        return est;
    }

    public Boolean agregarCopiaAlCatalogo (Media peli, Integer cant){
        Boolean est = false;
        for (int i = 0; i < this.catalogo.size() && !est; i++) {
            if(peli.getCodPel().equals(this.catalogo.get(i).getCodPel())){
                est = true;
                this.catalogo.get(i).agregarCopia(cant);
            }
        }
        return est;
    }

   public Boolean alquilar(Media media, Cliente per,String fech,Integer diasAlquiler) {
        Boolean est = false;
        Integer ind = this.buscarClienteXDNI(per.getDni());
        if (ind != -1 && this.listaCliente.get(ind).getNroEjemplaresAlquilados() < this.MAX_CANT_ALQUILERES_PERMITIDOS_POR_CLIENTE && this.listaCliente.get(ind).getEst().equals(DESBLOQUEAR_USUARIO)){
            Integer indMedia = this.buscarMediaXCod(media.getCodPel());
            if ( indMedia != -1) {
                if(this.catalogo.get(indMedia).getCantEjemplares() > 0){
                    this.listaAlquileres.add(new Alquiler(media,per.getDni(),fech,diasAlquiler));
                    this.listaCliente.get(ind).incrementarEjemplarAlquilado();
                    this.catalogo.get(indMedia).decremtentarEjemplar();
                    est = true;
                    if (this.listaCliente.get(ind).getNroEjemplaresAlquilados() == this.MAX_CANT_ALQUILERES_PERMITIDOS_POR_CLIENTE) {
                        this.listaCliente.get(ind).bloquearCliente();
                    }
                }
            }
        }
        return est;
    }

    public Boolean devolver(Media media, Cliente cli) {
        Boolean est = false;
        Integer indCli = this.buscarClienteXDNI(cli.getDni()), indAlq = this.buscarAlquiler(media.getCodPel(), cli.getDni());
        if (indCli != -1 && indAlq != -1) {
            Alquiler aux = this.listaAlquileres.remove(indAlq-1);
            this.listaCliente.get(this.buscarClienteXDNI(cli.getDni())).decrementarEjemplarAlquilado();
            aux.setEstadoAlquiler(false);
            this.catalogo.get(this.buscarMediaXCod(media.getCodPel())).agregarCopia(1);
            this.listaDevoluciones.add(aux);
            est = true;
            if (this.listaCliente.get(indCli).getNroEjemplaresAlquilados() < this.MAX_CANT_ALQUILERES_PERMITIDOS_POR_CLIENTE) {
                this.listaCliente.get(indCli).desbloquearCliente();
            }
        }
        return est;
    }

    public ArrayList<Cliente> getListaCliente() {
        return listaCliente;
    }

    public ArrayList<Alquiler> getListaAlquileres() {
        return listaAlquileres;
    }

    public ArrayList<Alquiler> getListaDevoluciones() {
        return listaDevoluciones;
    }

    public ArrayList<Media> getCatalogo() {
        return catalogo;
    }

    public String getNombre() {
        return nombre;
    }
}
