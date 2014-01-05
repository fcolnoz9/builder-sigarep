package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

/*
 * @ (#) Momento.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 */
/*
 ** Esta clase es del registro del maestro "Momento"
 * @ Author Lilibeth Achji 
 * @ Version 1.0, 16/12/13
 */

@Entity
// anotación indica que el JavaBean es una entidad persistente
@Access(AccessType.FIELD)
@Table(name = "momento")
public class Momento implements Serializable {
	private static final long serialVersionUID = 1L;

	public Momento(Integer idMomento, String nombreMomento, String descripcion,
			Boolean estatus) {
		super();
		this.idMomento = idMomento;
		this.nombreMomento = nombreMomento;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Genera el ID del Momento
	@Column(name = "id_momento", unique = true, nullable = false)
	private Integer idMomento;

	@Column(name = "estatus", nullable = false)
	private Boolean estatus;

	@Column(name = "nombremomento", length = 255)
	private String nombreMomento;

	@Column(name = "descripcion", length = 255)
	private String descripcion;

	public Momento() {
	}

	// Métodos GET y SET

	public Integer getIdMomento() {
		return this.idMomento;
	}

	public void setIdMomento(Integer idMomento) {
		this.idMomento = idMomento;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getNombreMomento() {
		return this.nombreMomento;
	}

	public void setNombreMomento(String nombreMomento) {
		this.nombreMomento = nombreMomento;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}