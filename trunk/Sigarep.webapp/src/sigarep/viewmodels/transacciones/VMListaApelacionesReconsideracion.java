package sigarep.viewmodels.transacciones;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;



import sigarep.modelos.data.maestros.*;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.*;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacionFiltros;
import sigarep.modelos.servicio.transacciones.ListaRecaudosMotivoEstudiante;
import sigarep.modelos.servicio.transacciones.ServicioApelacion;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMListaApelacionesReconsideracion  {
	
	@WireVariable
	private Textbox txtAsignatura;
	@WireVariable
	private EstudianteSancionado estudiantesancionado;
	@WireVariable
	private ListaApelacionEstadoApelacion listaapelacionestadoapelacion;
	@WireVariable
	private SolicitudApelacion solicitudapelacion;
	@WireVariable
	private EstadoApelacion estadoapelacion;
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String nombreTipoMotivo;
	@WireVariable
	private ApelacionEstadoApelacion apelacionestadoapelacion;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioApelacion serviciolista;
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<ApelacionEstadoApelacion> listadoApelaciones;
	private List<ListaApelacionEstadoApelacion> lista = new LinkedList<ListaApelacionEstadoApelacion>();
	private String sancion;
	private String programa;
	private String telefono;
	private String email;
	private String apellido;
	private String nombre;
	private String cedula;
	private String lapso;
	private Integer instancia;
	private String motivo;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String asignatura;
	private Integer caso;
	private Integer idMotivo;
	
	@Wire("#winActualizarEstadoEstudiante")
	private Window win;
	
	

	public Integer getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

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
	private ListaApelacionEstadoApelacionFiltros filtros = new ListaApelacionEstadoApelacionFiltros();
	private String programaFiltro;
	
	public String getProgramaFiltro() {
		return programaFiltro;
	}

	public void setProgramaFiltro(String programaFiltro) {
		this.programaFiltro = programaFiltro;
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

	public List<TipoMotivo> getListaTipoMotivo() {
			return listaTipoMotivo;
		}
	  
	public List<ProgramaAcademico> getListaPrograma() {
			return listaPrograma;
		}
	public List<ApelacionEstadoApelacion> getListadoApelaciones() {
		return listadoApelaciones;
	}

	public void setListadoApelaciones(List<ApelacionEstadoApelacion> listadoApelaciones) {
		this.listadoApelaciones = listadoApelaciones;
	}

	public void setListaTipoMotivo(List<TipoMotivo> ListaTipoMotivo) {
		this.listaTipoMotivo = ListaTipoMotivo;
	}
	
	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}
   
	
	 public List<ListaApelacionEstadoApelacion> getLista() {
		return lista;
	}

	public void setLista(List<ListaApelacionEstadoApelacion> lista) {
		this.lista = lista;
	}
	

	public ListaApelacionEstadoApelacion getListaapelacionestadoapelacion() {
		return listaapelacionestadoapelacion;
	}

	public void setListaapelacionestadoapelacion(ListaApelacionEstadoApelacion listaapelacionestadoapelacion) {
		this.listaapelacionestadoapelacion = listaapelacionestadoapelacion;
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	

	@Init
	    public void init(){
	    	 //initialization code
	    	buscarTipoMotivo();
	    	buscarProgramaA ();
	    	buscarApelacionesR ();

	    }
	    //Metodo que busca un motivo partiendo por su titulo
	  	@Command
	  	@NotifyChange({"listaTipoMotivo"})
	  	public void buscarTipoMotivo(){
	  		listaTipoMotivo = serviciotipomotivo.buscarP(nombreTipoMotivo);
	  	}
	  	
	  	@Command
		@NotifyChange({ "listaPrograma" })
		public void buscarProgramaA() {
			listaPrograma = servicioprogramaacademico.buscarPr(nombrePrograma);
		}
	  	@Command
		@NotifyChange({"lista"})
		public void buscarApelacionesR(){ 
		  			lista = serviciolista.buscarApelaciones();
		}
	  	
	  	@Command
		@NotifyChange({"cedula", "nombre", "apellido","email", "telefono", "programa", 
			"sancion", "lapso",  "segundoNombre", "segundoApellido", "asignatura", "caso"})
		public void showModal (){
	  		cedula = getListaapelacionestadoapelacion().getCedulaEstudiante();
	  		nombre = getListaapelacionestadoapelacion().getPrimerNombre();
	  		apellido = getListaapelacionestadoapelacion().getPrimerApellido();
	  		email = getListaapelacionestadoapelacion().getEmail();
	  		telefono = getListaapelacionestadoapelacion().getTelefono();
	  		programa = getListaapelacionestadoapelacion().getPrograma();
	  		sancion = getListaapelacionestadoapelacion().getNombreSancion();
	  		lapso = getListaapelacionestadoapelacion().getLapso();
	  		instancia = getListaapelacionestadoapelacion().getInstancia();
	  		segundoNombre = getListaapelacionestadoapelacion().getSegundoNombre();
	  		segundoApellido = getListaapelacionestadoapelacion().getSegundoApellido();
	  		asignatura = getListaapelacionestadoapelacion().getAsignatura();
	  		caso  = getListaapelacionestadoapelacion().getCaso();
	  
	  		
	  		
	  		final HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("cedula", this.cedula );
	        map.put("nombre", this.nombre);
	        map.put("apellido", this.apellido);
	        map.put("email", this.email);
	        map.put("telefono", this.telefono);
	        map.put("programa", this.programa);
	        map.put("sancion", this.sancion);
	        map.put("lapso", this.lapso);
	        map.put("instancia", this.instancia);
	       
	       
	        map.put("segundoNombre", this.segundoNombre);
	        map.put("segundoApellido", this.segundoApellido);
	        map.put("asignatura", this.asignatura);
	        map.put("caso", this.caso); 
	        
	     
	        
	        final Window window = (Window) Executions.createComponents(
	        		"/WEB-INF/sigarep/vistas/transacciones/RegistrarReconsideracion.zul", null, map);
			window.setMaximizable(true);
			window.doModal();
			
		
			
	  	}
	  
		@Command
		@NotifyChange({"lista"})
		public void filtros(){
			lista = serviciolista.buscarPorFiltros(filtros);
		}
		@NotifyChange({"filtros"})
		public ListaApelacionEstadoApelacionFiltros getFiltros() {
			return filtros;
		}
		public void setFiltros(ListaApelacionEstadoApelacionFiltros filtros) {
			this.filtros = filtros;
		
	  }

}