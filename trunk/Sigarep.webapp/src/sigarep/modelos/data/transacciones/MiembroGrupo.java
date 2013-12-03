package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.Grupo;
import sigarep.modelos.data.maestros.Usuario;


/**
 * The persistent class for the miembro_grupo database table.
 * 
 */
@Entity
@Table(name="miembro_grupo")
public class MiembroGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MiembroGrupoPK id;

	@Column(nullable=false)
	private Boolean estatus;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="id_grupo", nullable=false, insertable=false, updatable=false)
	private Grupo grupo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="nombre_usuario", nullable=false, insertable=false, updatable=false)
	private Usuario usuario;

	public MiembroGrupo() {
	}

	public MiembroGrupoPK getId() {
		return this.id;
	}

	public void setId(MiembroGrupoPK id) {
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}