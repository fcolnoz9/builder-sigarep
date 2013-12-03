package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the grupo_operacion database table.
 * 
 */
@Embeddable
public class GrupoOperacionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_grupo", unique=true, nullable=false)
	private Integer idGrupo;

	@Column(name="id_operacion", unique=true, nullable=false)
	private Integer idOperacion;

	public GrupoOperacionPK() {
	}
	public Integer getIdGrupo() {
		return this.idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public Integer getIdOperacion() {
		return this.idOperacion;
	}
	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GrupoOperacionPK)) {
			return false;
		}
		GrupoOperacionPK castOther = (GrupoOperacionPK)other;
		return 
			this.idGrupo.equals(castOther.idGrupo)
			&& this.idOperacion.equals(castOther.idOperacion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGrupo.hashCode();
		hash = hash * prime + this.idOperacion.hashCode();
		
		return hash;
	}
}