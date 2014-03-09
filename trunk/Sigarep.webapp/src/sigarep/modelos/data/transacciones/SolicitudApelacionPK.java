package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Solicitud apelacion relaciona las tablas estudiante sancionado, lapso
 * academico e instancia
 * 
 * @author Equipo: Builder-SIGAREP
 * @version 1.0
 * @since 07/01/14
 */
@Embeddable
@Access(AccessType.FIELD)
public class SolicitudApelacionPK implements Serializable {

	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "codigo_lapso", unique = false, nullable = false, length = 6)
	private String codigoLapso;

	@Column(name = "cedula_estudiante", unique = false, nullable = false, length = 8)
	private String cedulaEstudiante;

	@Column(name = "id_instancia_apelada", unique = false, nullable = false)
	private Integer idInstanciaApelada;

	public SolicitudApelacionPK() {
	}

	/**
	 * Constructor Solitud Apelacion
	 * 
	 * @param codigoLapso
	 *            , cedulaEstudiante, idInstanciaApelada
	 * @return Constructor lleno
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public SolicitudApelacionPK(String codigoLapso, String cedulaEstudiante,
			Integer idInstanciaApelada) {
		super();
		this.codigoLapso = codigoLapso;
		this.cedulaEstudiante = cedulaEstudiante;
		this.idInstanciaApelada = idInstanciaApelada;
	}

	// metodos set y get
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
		SolicitudApelacionPK castOther = (SolicitudApelacionPK) other;
		return this.codigoLapso.equals(castOther.codigoLapso)
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