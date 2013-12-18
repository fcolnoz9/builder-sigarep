package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.ApelacionMomento;

import java.util.List;


/**
 * The persistent class for the momento database table.
 * 
 */
@Entity
@Table(name="momento")
public class Momento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_momento", unique=true, nullable=false)
	private Integer idMomento;

	@Column(length=255)
	private String descripcion;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(name="nombre_momento", nullable=false, length=255)
	private String nombreMomento;

	//bi-directional many-to-one association to ApelacionMomento
	@OneToMany(mappedBy="momento")
	private List<ApelacionMomento> apelacionMomentos;

	public Momento() {
	}

	public Integer getIdMomento() {
		return this.idMomento;
	}

	public void setIdMomento(Integer idMomento) {
		this.idMomento = idMomento;
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

	public String getNombreMomento() {
		return this.nombreMomento;
	}

	public void setNombreMomento(String nombreMomento) {
		this.nombreMomento = nombreMomento;
	}

	public List<ApelacionMomento> getApelacionMomentos() {
		return this.apelacionMomentos;
	}

	public void setApelacionMomentos(List<ApelacionMomento> apelacionMomentos) {
		this.apelacionMomentos = apelacionMomentos;
	}

	public ApelacionMomento addApelacionMomento(ApelacionMomento apelacionMomento) {
		getApelacionMomentos().add(apelacionMomento);
		apelacionMomento.setMomento(this);

		return apelacionMomento;
	}

	public ApelacionMomento removeApelacionMomento(ApelacionMomento apelacionMomento) {
		getApelacionMomentos().remove(apelacionMomento);
		apelacionMomento.setMomento(null);

		return apelacionMomento;
	}

}