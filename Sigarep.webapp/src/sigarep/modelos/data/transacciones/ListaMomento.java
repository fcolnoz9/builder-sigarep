package sigarep.modelos.data.transacciones;

import java.util.Date;

public class ListaMomento {

	private Date fecha;
	private String nombreEstado;
	private String observacion;
	private String estatus;

	public ListaMomento(Date fecha, String nombreEstado,
			String observacion, String estatus) {
		super();
		this.fecha = fecha;
		this.nombreEstado = nombreEstado;
		this.observacion = observacion;
		this.estatus = estatus;
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