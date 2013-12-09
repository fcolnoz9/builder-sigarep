
package sigarep.modelos.data.maestros;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "pregunta_basica")
public class PreguntaBasica {
	
	private Integer id_pregunta_basica;
    private String pregunta;
    private String respuesta;
    private boolean estatus;
 
	public PreguntaBasica(Integer id_pregunta_basica, String pregunta, String respuesta, Boolean estatus) {
		super();
		this.id_pregunta_basica = id_pregunta_basica;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.estatus = estatus;
	}
	public PreguntaBasica() {
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_pregunta_basica")
     public Integer getId_pregunta_basica() {
		return id_pregunta_basica;
	}

	public void setId_pregunta_basica(Integer id_pregunta_basica) {
		this.id_pregunta_basica = id_pregunta_basica;
	}
	@Column(name = "pregunta")
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	@Column(name = "respuesta")
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	@Column(name = "estatus")
	public Boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(Boolean estatus){
		this.estatus = estatus;
	}
}
