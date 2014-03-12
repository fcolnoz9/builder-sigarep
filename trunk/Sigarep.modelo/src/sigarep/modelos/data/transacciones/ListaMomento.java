package sigarep.modelos.data.transacciones;

import java.util.Date;

/**
 * Lista momento
 * 
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.1
 * @since 10/02/14
 */
public class ListaMomento {

	private Date fecha;
	private String nombreEstado;
	private String observacion;
	private String estatus;

	/**
	 * Constructor Momento.
	 * 
	 * @param fecha
	 *            , nombreEstado, observacion
	 * @return Constructor lleno
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public ListaMomento(Date fecha, String nombreEstado, String observacion) {
		super();
		this.fecha = fecha;
		this.nombreEstado = nombreEstado;
		this.observacion = observacion;
	}

	public ListaMomento() {

	}

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
}