package sigarep.viewmodels.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zkplus.databind.BindingListModelArray;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Window;

import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewModelSolicitudApelacion {
	@Wire("#modalDialog")
    private Window window;
    
	
	private String sancion;
	private String programa;
	private String telefono;
	private String email;
	private String apellido;
	private String nombre;
	private String lapso;
	private int instancia;
		@WireVariable
		private String cedula;
		@WireVariable
		private SolicitudApelacion solicitudapelacion;
		@WireVariable
		private LapsoAcademico lapsoAcademico = new LapsoAcademico();
		@WireVariable
		private ServicioLapsoAcademico serviciolapsoacademico;
		@WireVariable
		private ServicioSolicitudApelacion serviciosolicitudapelacion;
		@WireVariable
		private Integer instanciaApelada;
		@WireVariable
		private Date fechaSolicitud;
		mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo
		SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
		SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
		 

		
		public Integer getInstancia() {
			return instancia;
		}

		public void setInstancia(Integer instancia) {
			this.instancia = instancia;
		}

		public String getLapso() {
			return lapso;
		}

		public void setLapso(String lapso) {
			this.lapso = lapso;
		}

		public String getCedula() {
			return cedula;
		}

		public void setCedula(String cedula) {
			this.cedula = cedula;
		}

		public SolicitudApelacion getSolicitudapelacion() {
			return solicitudapelacion;
		}

		public void setSolicitudapelacion(SolicitudApelacion solicitudapelacion) {
			this.solicitudapelacion = solicitudapelacion;
		}

		public LapsoAcademico getLapsoAcademico() {
			return lapsoAcademico;
		}

		public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
			this.lapsoAcademico = lapsoAcademico;
		}

		public ServicioLapsoAcademico getServiciolapsoacademico() {
			return serviciolapsoacademico;
		}

		public void setServiciolapsoacademico(
				ServicioLapsoAcademico serviciolapsoacademico) {
			this.serviciolapsoacademico = serviciolapsoacademico;
		}

		public ServicioSolicitudApelacion getServiciosolicitudapelacion() {
			return serviciosolicitudapelacion;
		}

		public void setServiciosolicitudapelacion(
				ServicioSolicitudApelacion serviciosolicitudapelacion) {
			this.serviciosolicitudapelacion = serviciosolicitudapelacion;
		}

		public Integer getInstanciaApelada() {
			return instanciaApelada;
		}

		public void setInstanciaApelada(Integer instanciaApelada) {
			this.instanciaApelada = instanciaApelada;
		}

		public Date getFechaSolicitud() {
			return fechaSolicitud;
		}

		public void setFechaSolicitud(Date fechaSolicitud) {
			this.fechaSolicitud = fechaSolicitud;
		}
		
		public String getSancion() {
			return sancion;
		}

		public void setSancion(String sancion) {
			this.sancion = sancion;
		}

		public String getPrograma() {
			return programa;
		}

		public void setPrograma(String programa) {
			this.programa = programa;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		@Init
		public void init(@ContextParam(ContextType.VIEW) Component view,
	            @ExecutionArgParam("cedula") String v1 ,
	            @ExecutionArgParam("nombre") String v2,
	            @ExecutionArgParam("apellido") String v3,
	            @ExecutionArgParam("email") String v4,
	            @ExecutionArgParam("telefono") String v5,
	            @ExecutionArgParam("programa") String v6,
	            @ExecutionArgParam("sancion") String v7,
	            @ExecutionArgParam("lapso") String v8,
	            @ExecutionArgParam("instancia") Integer v9)
	            
			// initialization code
		
		{
		        Selectors.wireComponents(view, this, false);
		        this.cedula = v1;
		        this.nombre = v2;
		        this.apellido = v3;
		        this.email = v4;
		        this.telefono = v5;
		        this.programa = v6;
		        this.sancion = v7;
		        this.lapso = v8;
		        this.instancia = v9;
		        
		   } 
		    @Command
		    public void closeThis() {
		        window.detach();
		    }
		

		@Command
		public void registrarSolicitudApelacion() {
				Date fecha= new Date();
				System.out.println("cedula "+cedula);
				solicitudApelacionPK.setCedulaEstudiante(cedula);
				System.out.println("lapso "+lapso);
				solicitudApelacionPK.setCodigoLapso(lapso);
				solicitudApelacionPK.setIdInstanciaApelada(2);
				System.out.println("instancia "+instancia);
				solicitudApelacion.setId(solicitudApelacionPK);
				System.out.println("fecha "+fecha);
				solicitudApelacion.setFechaSolicitud(fecha);
				solicitudApelacion.setEstatus(true);
				
				try {
			
					serviciosolicitudapelacion.guardar(solicitudApelacion);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				msjs.informacionRegistroCorrecto();
			
			}
		}

