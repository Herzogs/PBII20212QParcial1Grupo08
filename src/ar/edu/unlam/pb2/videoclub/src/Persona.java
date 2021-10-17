package ar.edu.unlam.pb2.videoclub.src;

public class Persona {
	private String nombre;
	private String apellido;
	private Integer dni;
	private String dir;
	
	public Persona(String nombre, String apellido, Integer dni, String dir) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.dir = dir;
	}

	public void actualizarDatos(Persona nuevo){
		this.nombre = nuevo.getNombre();
		this.apellido = nuevo.getApellido();
		this.dir = nuevo.getDir();
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Integer getDni() {
		return dni;
	}

	public String getDir() {
		return dir;
	}
	
}