package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

/**Cronograma de Actividades - Planificar
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.1
 * @since 10/02/14
 */

/**
 * The primary key class for the cronograma database table.
 * 
 */
@Embeddable
@Access(AccessType.FIELD)
public class CronogramaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_lapso", unique=false, nullable=false, length=6)
	private String codigoLapso;

	@Column(name="id_actividad", unique=false, nullable=false)
	private Integer idActividad;

	public CronogramaPK() {
	}
	public String getCodigoLapso() {
		return this.codigoLapso;
	}
	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}
	public Integer getIdActividad() {
		return this.idActividad;
	}
	public void setIdActividad(Integer idActividad) {
		this.idActividad = idActividad;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CronogramaPK)) {
			return false;
		}
		CronogramaPK castOther = (CronogramaPK)other;
		return 
			this.codigoLapso.equals(castOther.codigoLapso)
			&& this.idActividad.equals(castOther.idActividad);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoLapso.hashCode();
		hash = hash * prime + this.idActividad.hashCode();
		
		return hash;
	}
}