package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.EstudianteSancionado;

import java.util.List;


/**
 * The persistent class for the sancion_maestro database table.
 * 
 */
@Entity
@Table(name="sancion_maestro")
public class SancionMaestro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sancion", unique=true, nullable=false)
	private Integer idSancion;

	@Column(name="descripcion", length=255)
	private String descripcion;

	@Column(name="estatus", nullable=false)
	private Boolean estatus;

	@Column(name="nombre_sancion", nullable=false, length=60)
	private String nombreSancion;

	//bi-directional many-to-one association to EstudianteSancionado
	@OneToMany(mappedBy="sancionMaestro")
	private List<EstudianteSancionado> estudianteSancionados;

	public SancionMaestro() {
	}
	
	public SancionMaestro(Integer idSancion, String nombreSancion, String descripcion,Boolean estatus) {
		super();
		this.idSancion = idSancion;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.nombreSancion = nombreSancion;
	}



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

	public void setEstudianteSancionados(List<EstudianteSancionado> estudianteSancionados) {
		this.estudianteSancionados = estudianteSancionados;
	}

	public EstudianteSancionado addEstudianteSancionado(EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().add(estudianteSancionado);
		estudianteSancionado.setSancionMaestro(this);

		return estudianteSancionado;
	}

	public EstudianteSancionado removeEstudianteSancionado(EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().remove(estudianteSancionado);
		estudianteSancionado.setSancionMaestro(null);

		return estudianteSancionado;
	}

}