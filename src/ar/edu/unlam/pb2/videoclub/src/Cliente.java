package ar.edu.unlam.pb2.videoclub.src;

public class Cliente extends Persona {
	private Boolean est;
	private Integer nroEjemplaresAlquilados;

	public Cliente(String nombre, String apellido, Integer dni, String dir) {
		super(nombre, apellido, dni, dir);
		this.est = false;
		this.nroEjemplaresAlquilados = 0;
	}

	public void incrementarEjemplarAlquilado(){
		this.nroEjemplaresAlquilados++;
	}

	public void decrementarEjemplarAlquilado(){
		if(this.nroEjemplaresAlquilados >0)
			this.nroEjemplaresAlquilados--;
	}

	public void bloquearCliente (){
		this.est = true;
	}

	public void desbloquearCliente (){
		this.est = false;
	}

	public Boolean getEst() {
		return est;
	}

	public Integer getNroEjemplaresAlquilados() {
		return nroEjemplaresAlquilados;
	}
}
