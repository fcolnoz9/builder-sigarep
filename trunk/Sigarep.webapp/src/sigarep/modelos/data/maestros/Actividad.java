package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.herramientas.Archivo;
import sigarep.modelos.data.transacciones.Cronograma;

import java.util.List;

/**
 * The persistent class for the actividad database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "actividad")
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_actividad", unique = true, nullable = false)
	private Integer idActividad;

	@Column(length = 255)
	private String descripcion;

	@Column(nullable = false)
	private Boolean estatus;

	@Column(nullable = false, length = 60)
	private String nombre;

	// bi-directional many-to-one association to Cronograma
	@OneToMany(mappedBy = "actividad")
	private List<Cronograma> cronogramas;

	public Actividad() {
	}

	public Actividad(Integer id_actividad, String nombre, String descripcion,
			Boolean estatus) {
		super();
		this.idActividad = id_actividad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public Integer getIdActividad() {
		return this.idActividad;
	}

	public void setIdActividad(Integer idActividad) {
		this.idActividad = idActividad;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cronograma> getCronogramas() {
		return this.cronogramas;
	}

	public void setCronogramas(List<Cronograma> cronogramas) {
		this.cronogramas = cronogramas;
	}

	public Cronograma addCronograma(Cronograma cronograma) {
		getCronogramas().add(cronograma);
		cronograma.setActividad(this);

		return cronograma;
	}

	public Cronograma removeCronograma(Cronograma cronograma) {
		getCronogramas().remove(cronograma);
		cronograma.setActividad(null);
		return cronograma;
	}

}