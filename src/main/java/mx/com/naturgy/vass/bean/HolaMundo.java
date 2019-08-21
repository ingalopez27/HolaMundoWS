package mx.com.naturgy.vass.bean;

public class HolaMundo {
	String nombre;
	String apellido;
	String mensaje;

	

	public HolaMundo(String nombre, String apellido, String mensaje) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mensaje = mensaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
