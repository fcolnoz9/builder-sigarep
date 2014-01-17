package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the instancia_miembro database table.
 * 
 */
@Embeddable
public class InstanciaMiembroPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_instancia_apelada")
	private Integer idInstanciaApelada;

	@Column(name="cedula_persona")
	private Integer cedulaPersona;

	public InstanciaMiembroPK() {
	}
	public Integer getIdInstanciaApelada() {
		return this.idInstanciaApelada;
	}
	public void setIdInstanciaApelada(Integer idInstanciaApelada) {
		this.idInstanciaApelada = idInstanciaApelada;
	}
	public Integer getCedulaPersona() {
		return this.cedulaPersona;
	}
	public void setCedulaPersona(Integer cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InstanciaMiembroPK)) {
			return false;
		}
		InstanciaMiembroPK castOther = (InstanciaMiembroPK)other;
		return 
			this.idInstanciaApelada.equals(castOther.idInstanciaApelada)
			&& this.cedulaPersona.equals(castOther.cedulaPersona);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idInstanciaApelada.hashCode();
		hash = hash * prime + this.cedulaPersona.hashCode();
		
		return hash;
	}
}