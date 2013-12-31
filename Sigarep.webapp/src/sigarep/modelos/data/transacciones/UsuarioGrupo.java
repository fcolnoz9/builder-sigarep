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
@Access(AccessType.FIELD)
@Table(name="miembro_grupo")
@AssociationOverrides({
	@AssociationOverride(name = "id.usuario", 
		joinColumns = @JoinColumn(name = "nombre_usuario")),
	@AssociationOverride(name = "id.grupo", 
		joinColumns = @JoinColumn(name = "id_grupo")) })
public class UsuarioGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioGrupoPK id;

	@Column(nullable=false)
	private Boolean estatus;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="id_grupo", nullable=false, insertable=false, updatable=false)
	private Grupo grupo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="nombre_usuario", nullable=false, insertable=false, updatable=false)
	private Usuario usuario;

	public UsuarioGrupo() {
	}

	public UsuarioGrupoPK getId() {
		return this.id;
	}

	public void setId(UsuarioGrupoPK id) {
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
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
 
		UsuarioGrupo that = (UsuarioGrupo) o;
 
		if (getId() != null ? !getId().equals(that.getId())
				: that.getId() != null)
			return false;
 
		return true;
	}

}