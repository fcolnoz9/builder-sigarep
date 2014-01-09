package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

/*
 * @ (#) EstadoApelacion.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 */
/*
 ** Esta clase es del registro del maestro "EstadoApelacion"
 * @ Author Lilibeth Achji 
 * @ Version 1.0, 16/12/13
 */

@Entity
@Access(AccessType.FIELD)
// anotación indica que el JavaBean es una entidad persistente
@Table(name = "estado_apelacion")
public class EstadoApelacion implements Serializable {
	private static final long serialVersionUID = 1L;

	public EstadoApelacion(Integer idEstadoApelacion, String nombreEstado, String descripcion,
			Boolean estatus) {
		super();
		this.idEstadoApelacion = idEstadoApelacion;
		this.nombreEstado = nombreEstado;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Genera el ID del Momento
	@Column(name = "id_estado_apelacion", unique = true, nullable = false)
	private Integer idEstadoApelacion;

	@Column(name = "estatus", nullable = false)
	private Boolean estatus;

	@Column(name = "nombre_estado", length = 100)
	private String nombreEstado;

	@Column(name = "descripcion", length = 255)
	private String descripcion;
	
	//bi-directional many-to-one association to InstanciaApelada
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="id_instancia_apelada", nullable=false)
	private InstanciaApelada instanciaApelada;

	public EstadoApelacion() {
	}

	// Métodos GET y SET

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getIdEstadoApelacion() {
		return idEstadoApelacion;
	}

	public void setIdEstadoApelacion(Integer idEstadoApelacion) {
		this.idEstadoApelacion = idEstadoApelacion;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public InstanciaApelada getInstanciaApelada() {
		return instanciaApelada;
	}

	public void setInstanciaApelada(InstanciaApelada instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}

}