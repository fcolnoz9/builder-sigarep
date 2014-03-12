package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

/**
 * UsuarioGrupoPK relaciona a un usuario con un grupo determinado
 * 
 * @author Equipo: Builder-SIGAREP
 * @version 1.0
 * @since 07/01/14
 */
@Embeddable
@Access(AccessType.FIELD)
public class UsuarioGrupoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_grupo", unique=false, nullable=false)
	private Integer idGrupo;

	@Column(name="nombre_usuario", unique=false, nullable=false, length=30)
	private String nombreUsuario;

	public UsuarioGrupoPK() {
	}
	public Integer getIdGrupo() {
		return this.idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsuarioGrupoPK)) {
			return false;
		}
		UsuarioGrupoPK castOther = (UsuarioGrupoPK)other;
		return 
			this.idGrupo.equals(castOther.idGrupo)
			&& this.nombreUsuario.equals(castOther.nombreUsuario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGrupo.hashCode();
		hash = hash * prime + this.nombreUsuario.hashCode();
		
		return hash;
	}
}