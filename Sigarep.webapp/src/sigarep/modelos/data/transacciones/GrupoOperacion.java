package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.Grupo;
import sigarep.modelos.data.maestros.Operacion;


/**
 * The persistent class for the grupo_operacion database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="grupo_operacion")
public class GrupoOperacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GrupoOperacionPK id;

	@Column(nullable=false)
	private Boolean estatus;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="id_grupo", nullable=false, insertable=false, updatable=false)
	private Grupo grupo;

	//bi-directional many-to-one association to Operacion
	@ManyToOne
	@JoinColumn(name="id_operacion", nullable=false, insertable=false, updatable=false)
	private Operacion operacion;

	public GrupoOperacion() {
	}

	public GrupoOperacionPK getId() {
		return this.id;
	}

	public void setId(GrupoOperacionPK id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Operacion getOperacion() {
		return this.operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

}