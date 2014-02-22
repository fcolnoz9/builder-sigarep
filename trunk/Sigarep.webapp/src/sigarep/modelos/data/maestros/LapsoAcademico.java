package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.EstudianteSancionado;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**Clase Lapso Académico
 * Registra y Modifica un lapso académico
* UCLA DCYT Sistemas de Informacion.
* @author Equipo: Builder-SIGAREP 
* @version 1.0
* @since 20/12/13
*/
@Entity
@Access(AccessType.FIELD)
//anotación indica que el JavaBean es una entidad persistente
@Table(name="lapso_academico")
public class LapsoAcademico implements Serializable {
        private static final long serialVersionUID = 1L;

    	// Atributos de la clase
    	@Id
    	// Clave principal de la clase
        @Column(name="codigo_lapso", unique=true, nullable=false, length=6)
        private String codigoLapso;

        @Column(nullable=false)
        private Boolean estatus;

        @Temporal(TemporalType.DATE)
        @Column(name="fecha_cierre", nullable=false)
        private Date fechaCierre;

        @Temporal(TemporalType.DATE)
        @Column(name="fecha_inicio", nullable=false)
        private Date fechaInicio;

        //bi-directional many-to-one association to Cronograma
        @OneToMany(mappedBy="lapsoAcademico")
        private List<Cronograma> cronogramas = new LinkedList<Cronograma>();

        //bi-directional many-to-one association to EstudianteSancionado
        @OneToMany(mappedBy="lapsoAcademico")
        private List<EstudianteSancionado> estudianteSancionados = new LinkedList<EstudianteSancionado>();

        public LapsoAcademico() {
        }
        
        public LapsoAcademico(String codigoLapso,
				 Date fechaInicio, Date fechaCierre,Boolean estatus) {
			super();
			this.codigoLapso = codigoLapso;
			this.estatus = estatus;
			this.fechaInicio = fechaInicio;
			this.fechaCierre = fechaCierre;
			
		}

    	// Métodos GET y SET

		public String getCodigoLapso() {
                return this.codigoLapso;
        }

        public void setCodigoLapso(String codigoLapso) {
                this.codigoLapso = codigoLapso;
        }

        public Boolean getEstatus() {
                return this.estatus;
        }

        public void setEstatus(Boolean estatus) {
                this.estatus = estatus;
        }

        public Date getFechaCierre() {
                return this.fechaCierre;
        }

        public void setFechaCierre(Date fechaCierre) {
                this.fechaCierre = fechaCierre;
        }

        public Date getFechaInicio() {
                return this.fechaInicio;
        }

        public void setFechaInicio(Date fechaInicio) {
                this.fechaInicio = fechaInicio;
        }

        public List<Cronograma> getCronogramas() {
                return this.cronogramas;
        }

        public void setCronogramas(List<Cronograma> cronogramas) {
                this.cronogramas = cronogramas;
        }

        public Cronograma addCronograma(Cronograma cronograma) {
                getCronogramas().add(cronograma);
                cronograma.setLapsoAcademico(this);

                return cronograma;
        }

        public Cronograma removeCronograma(Cronograma cronograma) {
                getCronogramas().remove(cronograma);
                cronograma.setLapsoAcademico(null);

                return cronograma;
        }

        public List<EstudianteSancionado> getEstudianteSancionados() {
                return this.estudianteSancionados;
        }

        public void setEstudianteSancionados(List<EstudianteSancionado> estudianteSancionados) {
                this.estudianteSancionados = estudianteSancionados;
        }

        public EstudianteSancionado addEstudianteSancionado(EstudianteSancionado estudianteSancionado) {
                getEstudianteSancionados().add(estudianteSancionado);
                estudianteSancionado.setLapsoAcademico(this);

                return estudianteSancionado;
        }

        public EstudianteSancionado removeEstudianteSancionado(EstudianteSancionado estudianteSancionado) {
                getEstudianteSancionados().remove(estudianteSancionado);
                estudianteSancionado.setLapsoAcademico(null);

                return estudianteSancionado;
        }

}