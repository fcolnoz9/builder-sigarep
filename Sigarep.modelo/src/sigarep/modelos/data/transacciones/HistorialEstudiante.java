package sigarep.modelos.data.transacciones;

import java.util.Date;

/**
 * Historial estudiante , trae los objetos de esta clase compuesta por varias
 * claves principales y atributos propios
 * 
 * @author BUILDER
 * @version 1
 * @since 13/03/2014
 * @last 08/05/2014
 */
public class HistorialEstudiante {
	// Atributos de la clase
	private String lapsoSancion = "";
	private String tipoSancion = "";
	private float indiceGrado =0 ;
	private String unidadesAprobadas = ""; 
	private String unidadesCursadas = "";
	private String asignaturas = ""; 
	private String cedula = "";
	

	/**
	 * Constructor HistorialEstudiante
	 * 
	 * @param lapsoSancion, tipoSancion, indiceGrado, unidadesAprobadas, unidadesCursadas,
	 *        asignaturas, cedula
	 */
	public HistorialEstudiante(String lapsoSancion,String tipoSancion,float indiceGrado,String unidadesAprobadas,String unidadesCursadas,String asignaturas,String cedula ) {
		super();
		this.lapsoSancion = lapsoSancion ;
		this.tipoSancion =tipoSancion;
		this.indiceGrado = indiceGrado; 
		this.unidadesAprobadas = unidadesAprobadas; 
		this.unidadesCursadas = unidadesCursadas;
		this.asignaturas = asignaturas ;
		this.cedula = cedula;
	}

	// constructor por defecto
	public HistorialEstudiante() {

	}

	// metodos set y get
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getLapsoSancion() {
		return lapsoSancion;
	}

	public void setLapsoSancion(String lapsoSancion) {
		this.lapsoSancion = lapsoSancion;
	}

	public String getTipoSancion() {
		return tipoSancion;
	}

	public void setTipoSancion(String tipoSancion) {
		this.tipoSancion = tipoSancion;
	}

	public float getIndiceGrado() {
		return indiceGrado;
	}

	public void setIndiceGrado(float indiceGrado) {
		this.indiceGrado = indiceGrado;
	}

	public String getUnidadesAprobadas() {
		return unidadesAprobadas;
	}

	public void setUnidadesAprobadas(String unidadesAprobadas) {
		this.unidadesAprobadas = unidadesAprobadas;
	}

	public String getUnidadesCursadas() {
		return unidadesCursadas;
	}

	public void setUnidadesCursadas(String unidadesCursadas) {
		this.unidadesCursadas = unidadesCursadas;
	}

	public String getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(String asignaturas) {
		this.asignaturas = asignaturas;
	}
	//Fin  metodos set y get
}