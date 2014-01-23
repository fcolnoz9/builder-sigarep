package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;


import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.InstanciaMiembro;
import sigarep.modelos.data.transacciones.SolicitudApelacion;

import java.util.LinkedList;
import java.util.List;
/**Instancia Apelada
* UCLA DCYT Sistemas de Informacion.
* @author Equipo: Builder-SIGAREP 
* @version 1.0
* @since 20/12/13
*/
@Entity
@Access(AccessType.FIELD)
@Table(name="instancia_apelada")
public class InstanciaApelada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_instancia_apelada", unique=true, nullable=false)
	private Integer idInstanciaApelada;

	@Column(name="descripcion", length=255)
	private String descripcion;

	@Column(name="estatus", nullable=false)
	private Boolean estatus;

	@Column(name="instancia_apelada", nullable=false, length=100)
	private String instanciaApelada;

	@Column(name="nombre_recurso_apelacion", nullable=false, length=60)
	private String nombreRecursoApelacion;
	
	// bi-directional many-to-one association to EstadoApelacion
	@OneToMany(mappedBy = "instanciaApelada", cascade = { CascadeType.ALL })
	private List<EstadoApelacion> estadosApelacion = new LinkedList<EstadoApelacion>();
	
	// bi-directional many-to-one association to Cronograma
	@OneToMany(mappedBy = "responsable", cascade = { CascadeType.ALL })
	private List<Cronograma> cronograma = new LinkedList<Cronograma>();

	//bi-directional many-to-one association to SolicitudApelacion
	@OneToMany(mappedBy="instanciaApelada")
	private List<SolicitudApelacion> solicitudApelacions;
	
	//bi-directional many-to-one association to InstanciaMiembro
	@OneToMany(mappedBy="instanciaApelada")
	private List<InstanciaMiembro> instanciaMiembros;

	public InstanciaApelada(Integer idInstanciaApelada, String descripcion, Boolean estatus, String instanciaApelada, String nombreRecursoApelacion){
		this.idInstanciaApelada = idInstanciaApelada;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.instanciaApelada = instanciaApelada;
		this.nombreRecursoApelacion = nombreRecursoApelacion;
	}
	
	public InstanciaApelada() {
	}

	public Integer getIdInstanciaApelada() {
		return this.idInstanciaApelada;
	}

	public void setIdInstanciaApelada(Integer idInstanciaApelada) {
		this.idInstanciaApelada = idInstanciaApelada;
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

	public String getInstanciaApelada() {
		return this.instanciaApelada;
	}

	public void setInstanciaApelada(String instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}

	public String getNombreRecursoApelacion() {
		return this.nombreRecursoApelacion;
	}

	public void setNombreRecursoApelacion(String nombreRecursoApelacion) {
		this.nombreRecursoApelacion = nombreRecursoApelacion;
	}

	public List<SolicitudApelacion> getSolicitudApelacions() {
		return this.solicitudApelacions;
	}

	public void setSolicitudApelacions(List<SolicitudApelacion> solicitudApelacions) {
		this.solicitudApelacions = solicitudApelacions;
	}

	public SolicitudApelacion addSolicitudApelacion(SolicitudApelacion solicitudApelacion) {
		getSolicitudApelacions().add(solicitudApelacion);
		solicitudApelacion.setInstanciaApelada(this);

		return solicitudApelacion;
	}

	public SolicitudApelacion removeSolicitudApelacion(SolicitudApelacion solicitudApelacion) {
		getSolicitudApelacions().remove(solicitudApelacion);
		solicitudApelacion.setInstanciaApelada(null);

		return solicitudApelacion;
	}

	public List<EstadoApelacion> getEstadosApelacion() {
		return estadosApelacion;
	}

	public void setEstadosApelacion(List<EstadoApelacion> estadosApelacion) {
		this.estadosApelacion = estadosApelacion;
	}

	public List<Cronograma> getCronograma() {
		return cronograma;
	}

	public void setCronograma(List<Cronograma> cronograma) {
		this.cronograma = cronograma;
	}

	public List<InstanciaMiembro> getInstanciaMiembros() {
		return instanciaMiembros;
	}

	public void setInstanciaMiembros(List<InstanciaMiembro> instanciaMiembros) {
		this.instanciaMiembros = instanciaMiembros;
	}
}