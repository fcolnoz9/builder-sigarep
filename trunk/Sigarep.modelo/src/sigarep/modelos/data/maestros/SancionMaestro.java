package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase SancionMaestro
 * 
 * @author BUILDER
 * @version 1.0
 * @since 15/12/2013
 * @last 08/05/2014
 */
@Entity
@Access(AccessType.FIELD)
//anotación indica que el JavaBean es una entidad persistente
@Table(name = "sancion_maestro")
public class SancionMaestro implements Serializable {
	private static final long serialVersionUID = 1L;
     
	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@Column(name = "id_sancion", unique = true, nullable = false)
	private Integer idSancion;

	@Column(length = 255)
	private String descripcion;

	@Column(nullable = false)
	private Boolean estatus;

	@Column(name = "nombre_sancion", nullable = false, length = 60)
	private String nombreSancion;

	//  Relación bidireccional de muchos a uno, asociada a la clase EstudianteSancionado
	@OneToMany(mappedBy = "sancionMaestro")
	private List<EstudianteSancionado> estudianteSancionados = new LinkedList<EstudianteSancionado>();

	// Constructor por defecto
	public SancionMaestro() {
	}

	/**
	 * Constructor SancionMaestro
	 * 
	 * @param idSancion, descripcion, estatus, nombreSancion
	 */
	public SancionMaestro(Integer idSancion, String descripcion,
			Boolean estatus, String nombreSancion) {
		super();
		this.idSancion = idSancion;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.nombreSancion = nombreSancion;
	}

	//Métodos Set y Get
	public Integer getIdSancion() {
		return this.idSancion;
	}

	public void setIdSancion(Integer idSancion) {
		this.idSancion = idSancion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getNombreSancion() {
		return this.nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}

	public List<EstudianteSancionado> getEstudianteSancionados() {
		return this.estudianteSancionados;
	}

	public void setEstudianteSancionados(
			List<EstudianteSancionado> estudianteSancionados) {
		this.estudianteSancionados = estudianteSancionados;
	}
	//Fin Métodos Set y Get
	/**
	 * Relación de la clase SancionMaestro  con la clase EstudianteSancionado, Agregar
	 * EstudianteSancionado
	 * 
	 * @see EstudianteSancionado
	 * @param EstudianteSancionado
	 * @return EstudianteSancionado
	 */
	public EstudianteSancionado addEstudianteSancionado(
			EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().add(estudianteSancionado);
		estudianteSancionado.setSancionMaestro(this);
		return estudianteSancionado;
	}

	/**
	 * Relación de la clase SancionMaestro con la clase EstudianteSancionado, Quitar
	 * EstudianteSancionado
	 * 
	 * @see EstudianteSancionado
	 * @param EstudianteSancionado
	 * @return EstudianteSancionado
	 */
	public EstudianteSancionado removeEstudianteSancionado(
			EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().remove(estudianteSancionado);
		estudianteSancionado.setSancionMaestro(null);
		return estudianteSancionado;
	}

}//Fin Clase SancionMaestro