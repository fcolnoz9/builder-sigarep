package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pregunta_basica database table.
 * 
 */
@Entity
@Table(name="pregunta_basica")
public class PreguntaBasica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pregunta_basica", unique=true, nullable=false)
	private Integer idPreguntaBasica;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(nullable=false, length=255)
	private String pregunta;

	@Column(nullable=false, length=255)
	private String respuesta;

	public PreguntaBasica() {
	}

	public Integer getIdPreguntaBasica() {
		return this.idPreguntaBasica;
	}

	public void setIdPreguntaBasica(Integer idPreguntaBasica) {
		this.idPreguntaBasica = idPreguntaBasica;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}