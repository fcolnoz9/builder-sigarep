package sigarep.modelos.data.maestros;
/*
 * @ (#) MomentoFiltros.java 
 *
 * Copyright 2014 Builder. Todos los derechos reservados.
 * CONFIDENCIAL.
 */
/*
** Esta clase es para los filtrosde busqueda del maestro "Momento"
 * @ Author Lilibeth Achji 
 * @ Version 1.0, 04/01/14
*/
public class MomentoFiltros {
	private String nombreMomento = "", descripcion = "";

	public String getNombreMomento() {
		return nombreMomento;
	}
	public void setNombreMomento(String nombreMomento) {
		this.nombreMomento = nombreMomento == null ? "" : nombreMomento.trim();
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null ? "" : descripcion.trim();
	}
	
}
