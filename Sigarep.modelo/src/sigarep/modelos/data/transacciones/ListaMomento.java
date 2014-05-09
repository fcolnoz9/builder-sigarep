package sigarep.modelos.data.transacciones;

import java.util.Date;

/**
 * Lista momento
 * 
 * @author Equipo Builder
 * @version 1.1
 * @since 10/02/14
 * @last 08/05/2014
 */
public class ListaMomento {
	// Atributos de la clase
	private Date fecha;
	private String nombreEstado;
	private String observacion;
	private String estatus;

	// constructor por defecto
	public ListaMomento() {

	}

	/**
	 * Constructor Momento.
	 * 
	 * @param fecha , nombreEstado, observacion
	 */
	public ListaMomento(Date fecha, String nombreEstado, String observacion) {
		super();
		this.fecha = fecha;
		this.nombreEstado = nombreEstado;
		this.observacion = observacion;
	}
	
	//Métodos Set y Get
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	//Fin Métodos Set y Get
}