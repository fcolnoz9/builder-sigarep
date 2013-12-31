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
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
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
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.*;
import sigarep.modelos.data.transacciones.ApelacionMomento;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.*;
import sigarep.modelos.servicio.transacciones.ListaApelacionMomento;
import sigarep.modelos.servicio.transacciones.ServicioApelacion;
//import sigarep.modelos.servicio.transacciones.ServicioApelacionMomento;
//import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
//import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
//import sigarep.modelos.servicio.transacciones.ServicioApelacionMomento;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewModelListaApelaciones  {
	
	@WireVariable
	private Estudiante estudiante = new Estudiante();
	@WireVariable
	private EstudianteSancionado estudiantesancionado;
	@WireVariable
	private ListaApelacionMomento listaapelacionmomento;
	@WireVariable
	private SolicitudApelacion solicitudapelacion;
	@WireVariable
	private Momento momento;
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String nombreTipoMotivo;
	@WireVariable
	private ApelacionMomento apelacionmomento;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
//	@WireVariable
//	private ServicioApelacionMomento servicioapelacionmomento;
//	@WireVariable
//	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioApelacion serviciolista;
//	@WireVariable
//	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private List<EstudianteSancionado> listaSancionados =  new LinkedList<EstudianteSancionado>();
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<ApelacionMomento> listadoApelaciones;
	private List<ListaApelacionMomento> lista = new LinkedList<ListaApelacionMomento>();
	private String sancion;
	private String programa;
	private String telefono;
	private String email;
	private String apellido;
	private String nombre;
	private String cedula;
	private String lapso;
	private Integer instancia;
	
	
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
	public List<ApelacionMomento> getListadoApelaciones() {
		return listadoApelaciones;
	}

	public void setListadoApelaciones(List<ApelacionMomento> listadoApelaciones) {
		this.listadoApelaciones = listadoApelaciones;
	}

	public void setListaTipoMotivo(List<TipoMotivo> ListaTipoMotivo) {
		this.listaTipoMotivo = ListaTipoMotivo;
	}
	
	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}
   
	
	 public List<ListaApelacionMomento> getLista() {
		return lista;
	}

	public void setLista(List<ListaApelacionMomento> lista) {
		this.lista = lista;
	}
	

	public ListaApelacionMomento getListaapelacionmomento() {
		return listaapelacionmomento;
	}

	public void setListaapelacionmomento(ListaApelacionMomento listaapelacionmomento) {
		this.listaapelacionmomento = listaapelacionmomento;
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
		@NotifyChange({"cedula", "nombre", "apellido","email", "telefono", "programa", "sancion", "lapso"})
		public void showModal (){
	  		cedula = getListaapelacionmomento().getCedulaEstudiante();
	  		nombre = getListaapelacionmomento().getPrimerNombre();
	  		apellido = getListaapelacionmomento().getPrimerApellido();
	  		email = getListaapelacionmomento().getEmail();
	  		telefono = getListaapelacionmomento().getTelefono();
	  		programa = getListaapelacionmomento().getPrograma();
	  		sancion = getListaapelacionmomento().getNombreSancion();
	  		lapso = getListaapelacionmomento().getLapso();
	  		instancia = getListaapelacionmomento().getInstancia();
	  		
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
	       
	        
	        final Window window = (Window) Executions.createComponents(
	        		"/WEB-INF/sigarep/vistas/transacciones/RegistrarReconsideracion.zul", null, map);
			window.setMaximizable(true);
			window.doModal();	
	  	}
	  }