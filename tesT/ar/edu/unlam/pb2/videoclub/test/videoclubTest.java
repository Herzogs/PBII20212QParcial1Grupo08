package ar.edu.unlam.pb2.videoclub.test;
import org.junit.Test;
import static org.junit.Assert.*;

import ar.edu.unlam.pb2.videoclub.src.Cliente;
import ar.edu.unlam.pb2.videoclub.src.Pelicula;
import ar.edu.unlam.pb2.videoclub.src.Persona;
import ar.edu.unlam.pb2.videoclub.src.Serie;
import ar.edu.unlam.pb2.videoclub.src.Videoclub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;




public class videoclubTest {


    private Videoclub video;

    @Before
    public void seInicializaElVideoClub(){
        this.video = new Videoclub("UNLaM");
    }

    @Test
    public void queSeIntentaRegistrarUnClienteEnElSistemaYNoHayInconvenientes() {
        Boolean valorEsperado = true;
        Boolean valorObtenido= this.video.aniadirCliente(new Persona("Cristian", "Feldman",33557055,"peribebuy 3667"));
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void queSeIntentaRegistrarUnClienteEnElSistemaConUnDNIRepetidoYHayError() {
        Boolean valorEsperado = false;
        this.video.aniadirCliente(new Persona("Cristian", "Feldman",33557055,"peribebuy 3667"));
        Boolean valorObtenido= this.video.aniadirCliente(new Persona("Cristian", "Feldman",33557055,"peribebuy 3667"));
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void queSeIntenteBloquearUnClienteQueSiExistaEnLaListaDeClientes(){
        this.video.aniadirCliente(new Cliente("Cristian","Feldman",33557055,"Pepe 123"));
        this.video.aniadirCliente(new Cliente("Carmen","López",12345678,"Pepe 123"));
        this.video.aniadirCliente(new Cliente("Ana María","López",23456789,"Pepe 123"));
        this.video.aniadirCliente(new Cliente("Pedro","Jimenez",345678123,"Pepe 123"));
        Boolean valorEsperado = true;
        Cliente aux = this.video.getClienteByDNI(33557055);
        aux.bloquearCliente();
        Boolean valorObtenido = aux.getEst();
        assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queSeIntenteBloquearUnClienteQueNoExistaEnLaListaDeClientesYDaError(){
        this.video.aniadirCliente(new Cliente("Cristian","Feldman",33557055,"Pepe 123"));
        this.video.aniadirCliente(new Cliente("Carmen","López",12345678,"Pepe 123"));
        this.video.aniadirCliente(new Cliente("Ana María","López",23456789,"Pepe 123"));
        this.video.aniadirCliente(new Cliente("Pedro","Jimenez",345678123,"Pepe 123"));
        assertNull(this.video.getClienteByDNI(33557054));
    }

    @Test
    public void queSeIntentaIngresarUnNuevoClientePeroYaHabiaSidoIngresadoAntesPorEsoDaError(){
        Boolean valorEsperado = false;
        this.video.aniadirCliente(new Cliente("Cristian","Feldman",33557055,"Pepe 123"));
        Boolean valorObtenido = this.video.aniadirCliente(new Cliente("Cristian","Feldman",33557055,"pepe 123"));
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queSeIntentaAgregarUnaCopiaAUnaPeliculaPeroDichaPeliculaNoExiste(){
        Boolean valorEsperado = false;
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990,"USA");
        Pelicula copia = new Pelicula("Destino Final II","00002","HORROR","none","none",120,1990,"USA");
        this.video.agregarCatalogo(nueva);

        Boolean valorObtenido = this.video.agregarCopiaAlCatalogo(copia,5);
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queSeIntentaAgregarUnaCopiaAUnaPeliculaPeroDichaPeliculaSiExiste(){
        Boolean valorEsperado = true;
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990,"USA");
        Pelicula copia = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990,"USA");
        this.video.agregarCatalogo(nueva);

        Boolean valorObtenido = this.video.agregarCopiaAlCatalogo(copia,5);
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queUnUsuarioValidoIntenteAlquilarUnaPeliculaPeroDichaPeliculaNoExiste(){
        Boolean valorEsperado = false;
        Cliente valido = new Cliente("Cristian", "Feldman",33557055,"Peribebuy 3667");
        this.video.aniadirCliente(valido);
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990,"USA");
        Pelicula error = new Pelicula("Destino Final I","00002","HORROR","none","none",120,1990, "USA");
        this.video.agregarCatalogo(nueva);
        Boolean valorObtenido = this.video.alquilar(error,valido);
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queUnUsuarioValidoIntenteAlquilarUnaPeliculaPeroDichaPeliculaSiExiste(){
        Boolean valorEsperado = true;
        Cliente valido = new Cliente("Cristian", "Feldman",33557055,"Peribebuy 3667");
        this.video.aniadirCliente(valido);
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        Pelicula ok = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        this.video.agregarCatalogo(nueva);
        this.video.agregarCopiaAlCatalogo(nueva,1);
        Boolean valorObtenido = this.video.alquilar(ok,valido);
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queUnUsuarioInValidoIntenteAlquilarUnaPeliculaPeroDichaPeliculaSiExisteYPorEsoDaError(){
        Boolean valorEsperado = false;
        Cliente valido = new Cliente("Cristian", "Feldman",33557055,"Peribebuy 3667");
        Cliente invalido = new Cliente("Cristian", "Feldman",33557054,"Peribebuy 3667");
        this.video.aniadirCliente(valido);
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        Pelicula ok = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        this.video.agregarCatalogo(nueva);
        this.video.agregarCopiaAlCatalogo(nueva,1);
        Boolean valorObtenido = this.video.alquilar(ok,invalido);
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queUnUsuarioValidoIntentaAlquilarUnaCopiaDeUnaPeliculaPeroDichaPeliculaSiExistePeroNoHaySuficientesCopiasParaAlquilar(){
        Cliente valido = new Cliente("Cristian", "Feldman",33557055,"Peribebuy 3667");
        this.video.aniadirCliente(valido);
        Boolean valorEsperado = false;
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        Pelicula copia = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        this.video.agregarCatalogo(nueva);

        Boolean valorObtenido = this.video.alquilar(copia, valido);
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queUnUsuarioInValidoIntentaAlquilarUnaCopiaDeUnaPeliculaPeroDichaPeliculaSiExistePeroNoHaySuficientesCopiasParaAlquilar(){
        Cliente valido = new Cliente("Cristian", "Feldman",33557055,"Peribebuy 3667");
        Cliente invalido = new Cliente("Cristian", "Feldman",33557054,"Peribebuy 3667");
        this.video.aniadirCliente(valido);
        Boolean valorEsperado = false;
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        Pelicula copia = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        this.video.agregarCatalogo(nueva);
        this.video.agregarCopiaAlCatalogo(nueva,1);
        Boolean valorObtenido = this.video.alquilar(copia, invalido);
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queUnUsuarioValidoSeBloqueePorPasarseDeLaCantidadMaximaPermitidaDeAlquileres(){
        Cliente valido = new Cliente("Cristian", "Feldman",33557055,"Peribebuy 3667");
        this.video.aniadirCliente(valido);
        Boolean valorEsperado = true;
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        Pelicula nueva1 = new Pelicula("Destino Final II","00002","HORROR","none","none",120,1990, "USA");
        Pelicula nueva2 = new Pelicula("Destino Final III","00003","HORROR","none","none",120,1990, "USA");
        this.video.agregarCatalogo(nueva);
        this.video.agregarCatalogo(nueva1);
        this.video.agregarCatalogo(nueva2);
        this.video.agregarCopiaAlCatalogo(nueva,1);
        this.video.agregarCopiaAlCatalogo(nueva1,1);
        this.video.agregarCopiaAlCatalogo(nueva2,1);
        this.video.alquilar(nueva, valido);
        this.video.alquilar(nueva1, valido);
        this.video.alquilar(nueva2, valido);
        Boolean valorObtenido = this.video.getClienteByDNI(valido.getDni()).getEst();
        assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queUnUsuarioValidoDevuelvaUnaPeliculaQueYaHabiaAlquilado(){
        Cliente valido = new Cliente("Cristian", "Feldman",33557055,"Peribebuy 3667");
        this.video.aniadirCliente(valido);
        Boolean valorEsperado = true;
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        this.video.agregarCatalogo(nueva);
        this.video.agregarCopiaAlCatalogo(nueva,1);
        this.video.alquilar(nueva, valido);
        Boolean valorObtenido = this.video.devolver(nueva,valido);
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queUnUsuarioValidoDevuelvaUnaPeliculaQueNoHabiaAlquilado(){
        Cliente valido = new Cliente("Cristian", "Feldman",33557055,"Peribebuy 3667");
        this.video.aniadirCliente(valido);
        Boolean valorEsperado = false;
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990, "USA");
        Pelicula error = new Pelicula("Destino Final II","00002","HORROR","none","none",120,1990, "USA");
        this.video.agregarCatalogo(nueva);
        this.video.agregarCopiaAlCatalogo(nueva,1);
        this.video.alquilar(nueva, valido);
        Boolean valorObtenido = this.video.devolver(error,valido);
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queUnUsuarioValidoSeDesbloqueeDespuesDeDeolverUnaPeliculaAlquilada(){
        Cliente valido = new Cliente("Cristian", "Feldman",33557055,"Peribebuy 3667");
        this.video.aniadirCliente(valido);
        Boolean valorEsperado = false;
        Pelicula nueva = new Pelicula("Destino Final I","00001","HORROR","none","none",120,1990,"USA");
        Pelicula nueva1 = new Pelicula("Destino Final II","00002","HORROR","none","none",120,1990,"USA");
        Pelicula nueva2 = new Pelicula("Destino Final III","00003","HORROR","none","none",120,1990,"USA");
        this.video.agregarCatalogo(nueva);
        this.video.agregarCatalogo(nueva1);
        this.video.agregarCatalogo(nueva2);
        this.video.agregarCopiaAlCatalogo(nueva,1);
        this.video.agregarCopiaAlCatalogo(nueva1,1);
        this.video.agregarCopiaAlCatalogo(nueva2,1);
        this.video.alquilar(nueva, valido);
        this.video.alquilar(nueva1, valido);
        this.video.alquilar(nueva2, valido);
        this.video.devolver(nueva,valido);
        Boolean valorObtenido = this.video.getClienteByDNI(valido.getDni()).getEst();
        assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queSeIntentaAgregarUnaSerieAlCatalogoYNoSeRepite(){
        Boolean valorEsperado = true;
        Serie nueva = new Serie("Casa De Papel", "S0001","accion","none","none",220,2015,"Netflix");
        Boolean valorObtenido = this.video.agregarCatalogo(nueva);
        Assert.assertEquals(valorEsperado,valorObtenido);
    }
}
