package sigarep.modelos.data.transacciones;

import java.sql.Time;
import java.util.Date;

/**
 * Lista cronograma
 * 
 * @author Equipo Builder
 * @version 1.1
 * @since 10/02/14
 * @last 08/05/2014
 */
public class ListaCronograma {
	// Atributos de la clase
	private String nombre;
	private String descripcion;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Time hora_inicio;
	private String observacion;
	private String lugar;

	/**
	 * Constructor ListaCronograma.
	 * 
	 * @param nombre, nombre,descripcion,fecha_inicio,fecha_fin,hora_inicio
	 *        observacion, lugar
	 */
	public ListaCronograma(String nombre, String descripcion,
			Date fecha_inicio, Date fecha_fin, Time hora_inicio,
			String observacion, String lugar) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.setFecha_inicio(fecha_inicio);
		this.setFecha_fin(fecha_fin);
		this.setHora_inicio(hora_inicio);
		this.observacion = observacion;
		this.lugar = lugar;
	}

	//Métodos Set y Get
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	//Fin Métodos Set y Get
}