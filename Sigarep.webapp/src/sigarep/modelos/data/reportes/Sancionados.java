package sigarep.modelos.data.reportes;

/** Clase Sancionados
 * @author Equipo Builder  
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public class Sancionados {
	// Atributos de la clase
	String cedula;
	String nombre;
	String apellido;
	String veredicto;
	String programa;

	/**
	 * Constructor Sancionados
	 * @param cedula, nombre, apellido, veredicto, procedentes, noprocedentes
	 */
	public Sancionados(String cedula, String nombre, String apellido,
			String veredicto, String programa) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.veredicto = veredicto;
		this.programa = programa;
	}

	// Métodos GET y SET
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

	public String getVeredicto() {
		return veredicto;
	}

	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}
	
	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}
	// Fin Métodos Set y Get

}
