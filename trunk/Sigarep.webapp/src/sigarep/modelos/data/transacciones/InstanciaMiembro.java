package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.Persona;

import java.util.Date;


/**
 * The persistent class for the instancia_miembro database table.
 * 
 */
@Entity
@Table(name="instancia_miembro")
@AssociationOverrides({
	@AssociationOverride(name = "id.instancia_apelada", 
		joinColumns = @JoinColumn(name = "id_instancia_apelada")),
	@AssociationOverride(name = "id.cedula_persona", 
		joinColumns = @JoinColumn(name = "cedula_persona"))})
public class InstanciaMiembro implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InstanciaMiembroPK id;

	private String cargo;

	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_entrada")
	private Date fechaEntrada;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_salida")
	private Date fechaSalida;

	//bi-directional many-to-one association to InstanciaApelada
	@ManyToOne
	@JoinColumn(name="id_instancia_apelada",insertable=false, updatable=false)
	private InstanciaApelada instanciaApelada;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="cedula_persona",insertable=false, updatable=false)
	private Persona persona;

	public InstanciaMiembro() {
	}

	public InstanciaMiembroPK getId() {
		return this.id;
	}

	public void setId(InstanciaMiembroPK id) {
		this.id = id;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaEntrada() {
		return this.fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public InstanciaApelada getInstanciaApelada() {
		return this.instanciaApelada;
	}

	public void setInstanciaApelada(InstanciaApelada instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}