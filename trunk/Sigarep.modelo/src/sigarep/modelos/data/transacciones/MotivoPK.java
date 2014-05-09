package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

/**
 * MotivoPK 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/13
 * @last 08/05/2014
 */

@Embeddable
@Access(AccessType.FIELD)
public class MotivoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	// Atributos de la clase
	@Column(name = "id_tipo_motivo", unique = false, nullable = false)
	private Integer idTipoMotivo;

	@Column(name = "codigo_lapso", unique = false, nullable = false, length = 6)
	private String codigoLapso;

	@Column(name = "cedula_estudiante", unique = false, nullable = false, length = 8)
	private String cedulaEstudiante;

	@Column(name = "id_instancia_apelada", unique = false, nullable = false)
	private Integer idInstanciaApelada;

	// constructor por defecto
	public MotivoPK() {
	}
	
	// Métodos Set y Get
	public Integer getIdTipoMotivo() {
		return this.idTipoMotivo;
	}

	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
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
	//Fin  Métodos Set y Get
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MotivoPK)) {
			return false;
		}
		MotivoPK castOther = (MotivoPK) other;
		return this.idTipoMotivo.equals(castOther.idTipoMotivo)
				&& this.codigoLapso.equals(castOther.codigoLapso)
				&& this.cedulaEstudiante.equals(castOther.cedulaEstudiante)
				&& this.idInstanciaApelada.equals(castOther.idInstanciaApelada);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idTipoMotivo.hashCode();
		hash = hash * prime + this.codigoLapso.hashCode();
		hash = hash * prime + this.cedulaEstudiante.hashCode();
		hash = hash * prime + this.idInstanciaApelada.hashCode();

		return hash;
	}
}