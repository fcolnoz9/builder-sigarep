package sigarep.modelos.data.reportes;

public class Sancionados {
	String cedula;
	String nombre;
	String apellido;
	String sancion;
	String veredicto;
	String observacion;
	public Sancionados(String cedula, String nombre, String apellido,
			String sancion, String veredicto, String observacion) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sancion = sancion;
		this.veredicto = veredicto;
		this.observacion = observacion;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
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
	public String getSancion() {
		return sancion;
	}
	public void setSancion(String sancion) {
		this.sancion = sancion;
	}
	public String getVeredicto() {
		return veredicto;
	}
	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
