package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the solicitud_apelacion database table.
 * 
 */
@Embeddable
@Access(AccessType.FIELD)
public class SolicitudApelacionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_lapso", unique=true, nullable=false, length=6)
	private String codigoLapso;

	@Column(name="cedula_estudiante", unique=true, nullable=false, length=8)
	private String cedulaEstudiante;

	@Column(name="id_instancia_apelada", unique=true, nullable=false)
	private Integer idInstanciaApelada;

	public SolicitudApelacionPK() {
	}
	public String getCodigoLapso() {
		return this.codigoLapso;
	}
	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}
	public String getCedulaEstudiante() {
		return this.cedulaEstudiante;
	}
	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}
	public Integer getIdInstanciaApelada() {
		return this.idInstanciaApelada;
	}
	public void setIdInstanciaApelada(Integer idInstanciaApelada) {
		this.idInstanciaApelada = idInstanciaApelada;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SolicitudApelacionPK)) {
			return false;
		}
		SolicitudApelacionPK castOther = (SolicitudApelacionPK)other;
		return 
			this.codigoLapso.equals(castOther.codigoLapso)
			&& this.cedulaEstudiante.equals(castOther.cedulaEstudiante)
			&& this.idInstanciaApelada.equals(castOther.idInstanciaApelada);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoLapso.hashCode();
		hash = hash * prime + this.cedulaEstudiante.hashCode();
		hash = hash * prime + this.idInstanciaApelada.hashCode();
		
		return hash;
	}
}