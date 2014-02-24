package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.Cronograma;

import java.util.List;

/**
 * Clase Actividad Registra y Modifica las Actividad del cronograma
 * 
 * @author BUILDER
 * @version 1
 * @since 15/12/2013
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "actividad")
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos de la clase
	@Id
	// Clave principal de la clase
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

	// bi-directional many-to-one association to InstanciaApelada
	@ManyToOne
	@JoinColumn(name = "id_instancia_apelada", nullable = true)
	private InstanciaApelada instanciaApelada;

	public Actividad() {
	}

	/**
	 * Constructor Actividad
	 * 
	 * @param id_actividad
	 *            , nombre, descripcion, instanciaApelada, estatus
	 * @return Constructor lleno
	 */
	public Actividad(Integer id_actividad, String nombre, String descripcion,
			InstanciaApelada instanciaApelada, Boolean estatus) {
		super();
		this.idActividad = id_actividad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.instanciaApelada = instanciaApelada;
		this.estatus = estatus;
	}

	// Métodos GET y SET
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

	public InstanciaApelada getInstanciaApelada() {
		return instanciaApelada;
	}

	public void setInstanciaApelada(InstanciaApelada instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}

}