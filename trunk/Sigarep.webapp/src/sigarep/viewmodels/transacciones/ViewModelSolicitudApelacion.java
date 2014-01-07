package sigarep.viewmodels.transacciones;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.jfree.text.TextBox;
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
import sigarep.modelos.data.transacciones.ApelacionMomento;
import sigarep.modelos.data.transacciones.ApelacionMomentoPK;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ListaApelacionMomento;
import sigarep.modelos.servicio.transacciones.ServicioApelacionMomento;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewModelSolicitudApelacion {
	@Wire("#modalDialog")
    private Window window;
	
	 @Wire Textbox txtAsignatura;
	 @Wire Window winRegistrarReconsideracion;
	
	private String sancion;
	private String programa;
	private String telefono;
	private String email;
	private String apellido;
	private String nombre;
	private String lapso;
	private int instancia;
	private String motivo;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private String asignatura;
	private String caso;


	public String getCaso() {
		return caso;
	}

	public void setCaso(String caso) {
		this.caso = caso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


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
	private ServicioApelacionMomento servicioapelacionmomento;
	@WireVariable
	private Integer instanciaApelada;
	@WireVariable
	private Date fechaSolicitud;
	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo
	SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
	SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
	ApelacionMomentoPK apelacionMomentoPK = new ApelacionMomentoPK();
	ApelacionMomento apelacionMomento = new ApelacionMomento(); 


	public String getRecaudo() {
		return recaudo;
	}

	public void setRecaudo(String recaudo) {
		this.recaudo = recaudo;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

		public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

		
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
		
		public void concatenacionNombres () {		

		 	String nombre1 = nombre;
			String nombre2 = segundoNombre;
			nombres = nombre1 + " " + nombre2;
			System.out.println(nombres);
		}
		
		public void  concatenacionApellidos () {
			
			String apellido1 = apellido;
			String apellido2 = segundoApellido;
			apellidos = apellido1 + " " + apellido2;
			
		}
		
		
//		public void inhabilitar () {
//		String materia = asignatura;
//			if (materia  == null){
//			txtAsignatura.setTextBlock(null);
//		}
//		}

		@Init
		public void init(
				
				@ContextParam(ContextType.VIEW) Component view,
	            @ExecutionArgParam("cedula") String v1 ,
	            @ExecutionArgParam("nombre") String v2,
	            @ExecutionArgParam("apellido") String v3,
	            @ExecutionArgParam("email") String v4,
	            @ExecutionArgParam("telefono") String v5,
	            @ExecutionArgParam("programa") String v6,
	            @ExecutionArgParam("sancion") String v7,
	            @ExecutionArgParam("lapso") String v8,
	            @ExecutionArgParam("instancia") Integer v9,
	            @ExecutionArgParam("motivo") String v10,
	            @ExecutionArgParam("recaudo") String v11,
	            @ExecutionArgParam("segundoNombre") String v12,
	            @ExecutionArgParam("segundoApellido") String v13,
	            @ExecutionArgParam("asignatura") String v14,
	            @ExecutionArgParam("caso") String v15)
	           
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
		        this.motivo = v10;
		        this.recaudo = v11;
		        this.segundoNombre = v12;
		        this.segundoApellido = v13;
		        this.asignatura = v14;
		        this.caso = v15;
		        concatenacionNombres ();
		        concatenacionApellidos ();
		 

		   } 
		    @Command
		    public void closeThis() {
		        window.detach();
		    }
		

		@Command
		public void registrarSolicitudApelacion() {
			
				Date fecha= new Date();
				Time hora= new Time(0);
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
				
				apelacionMomentoPK.setCedulaEstudiante(cedula);
				apelacionMomentoPK.setCodigoLapso(lapso);
				apelacionMomentoPK.setIdInstanciaApelada(2);
				apelacionMomentoPK.setIdMomento(4);
				apelacionMomento.setId(apelacionMomentoPK);
				apelacionMomento.setFechaMomento(hora);
				
				try {
			
					serviciosolicitudapelacion.guardar(solicitudApelacion);
					servicioapelacionmomento.guardar(apelacionMomento);
	
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				msjs.informacionRegistroCorrecto();
			
			}
		
		@Command
		@NotifyChange({"txtAsignatura", "winRegistrarReconsideracion"})
		public void inhabilitar () {
			String sanciontipo;
			sanciontipo = "RP";
			System.out.println("...." +sanciontipo);
			System.out.println("´´´´´´" +sancion);
			System.out.println("pasoooooooo1");
			if (sanciontipo.equals(sancion)){
				txtAsignatura.setVisible(false);
			System.out.println("pasooooo2");
			}
		}
	}

