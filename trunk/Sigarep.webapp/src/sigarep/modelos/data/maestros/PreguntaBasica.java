package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the instancia_apelada database table.
 * 
 */
@Entity // anotación indica que el JavaBean es una entidad persistente
@Access(AccessType.FIELD)
@Table(name="pregunta_basica") //
public class PreguntaBasica implements Serializable {
       private static final long serialVersionUID = 1L;

    
    
   	public PreguntaBasica(Integer idPreguntaBasica, String pregunta, String respuesta, Boolean estatus) {
   		super();
   		this.idPreguntaBasica = idPreguntaBasica;
   		this.pregunta = pregunta;
   		this.respuesta = respuesta;
   		this.estatus = estatus;
   	}
  
  // Atributos de la clase
       @Id // Clave principal de la clase
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

  // Métodos GET y SET 
      
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