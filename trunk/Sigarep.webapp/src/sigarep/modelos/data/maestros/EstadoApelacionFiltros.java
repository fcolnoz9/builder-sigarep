package sigarep.modelos.data.maestros;
/*
 * @ (#) MomentoFiltros.java 
 *
 * Copyright 2014 Builder. Todos los derechos reservados.
 * CONFIDENCIAL.
 */
/*
** Esta clase es para los filtrosde busqueda del maestro "EstadoApelacion"
 * @ Author Lilibeth Achji 
 * @ Version 1.0, 04/01/14
*/
public class EstadoApelacionFiltros {
	private String nombreEstado = "", descripcion = "";

	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado == null ? "" : nombreEstado.trim();
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null ? "" : descripcion.trim();
	}
	
}
