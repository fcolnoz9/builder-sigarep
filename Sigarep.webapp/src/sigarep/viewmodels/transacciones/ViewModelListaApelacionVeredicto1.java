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

import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.FiltroListaApelacionVeredicto1;
import sigarep.modelos.servicio.transacciones.ListaApelacionVeredicto1;
import sigarep.modelos.servicio.transacciones.ServicioVeredicto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewModelListaApelacionVeredicto1 {
	
	@WireVariable
	private Estudiante estudiante = new Estudiante();
	@WireVariable
	private EstudianteSancionado estudiantesancionado  = new EstudianteSancionado();
	@WireVariable
	private ListaApelacionVeredicto1 listaapelacionveredicto1;
	@WireVariable
	private SolicitudApelacion solicitudapelacion;
	@WireVariable
	private EstadoApelacion estadoapelacion;
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String nombreTipoMotivo;
	@WireVariable
	private ApelacionEstadoApelacion apelacionmomento;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioVeredicto serviciolistaveredicto;
//	@WireVariable
//	private ServicioApelacionMomento servicioapelacionmomento;
//	@WireVariable
//	private ServicioEstudianteSancionado servicioestudiantesancionado;

// para los filtros
	private String cedulafiltro, nombrefiltro, apellidofiltro, sancionfiltro;
	private FiltroListaApelacionVeredicto1 filtros = new FiltroListaApelacionVeredicto1();
	private List<ListaApelacionVeredicto1> listafiltro = new LinkedList<ListaApelacionVeredicto1>();

//	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private List<EstudianteSancionado> listaSancionados =  new LinkedList<EstudianteSancionado>();
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<ApelacionEstadoApelacion> listadoApelaciones = new LinkedList<ApelacionEstadoApelacion>();
	private List<ListaApelacionVeredicto1> lista = new LinkedList<ListaApelacionVeredicto1>();
	private String sancion;
	private String programa;
	private float indice;
//	private String periodosancion;
	private String apellido;
	private String nombre;
	private String cedula;
	private String codigolapso;
// ojo falta agregar numero de caso	
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
   
	
	 public List<ListaApelacionVeredicto1> getLista() {
		return lista;
	}

	public void setLista(List<ListaApelacionVeredicto1> lista) {
		this.lista = lista;
	}
	

	public ListaApelacionVeredicto1 getListaapelacionveredicto1() {
		return listaapelacionveredicto1;
	}

	public void setListaapelacionveredicto1(ListaApelacionVeredicto1 listaapelacionveredicto1) {
		this.listaapelacionveredicto1 = listaapelacionveredicto1;
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

	public float getIndice() {
		return indice;
	}

	public void setIndice(float Indice) {
		this.indice = indice;
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
	
	public String getCodigoLapso() {
		return codigolapso;
	}

	public void setCodigoLapso(String codigolapso) {
		this.codigolapso = codigolapso;
	}
	public List<ListaApelacionVeredicto1> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<ListaApelacionVeredicto1> listafiltro) {
		this.listafiltro = listafiltro;
	}

// De los filtros
	
	public String getCedulafiltro() {
		return cedulafiltro;
	}
	public void setCedulafiltro(String cedulafiltro) {
		this.cedulafiltro = cedulafiltro;
	}
	public String getNombrefiltro() {
		return nombrefiltro;
	}
	public void setNombrefiltro(String nombrefiltro) {
		this.nombrefiltro = nombrefiltro;
	}	
	public String getApellidofiltro() {
		return apellidofiltro;
	}
	public void setApellidofiltro(String apellidofiltro) {
		this.apellidofiltro = apellidofiltro;
	}
	public String getSancionfiltro() {
		return sancionfiltro;
	}
	public void setSancionfiltro(String sancionfiltro) {
		this.sancionfiltro = sancionfiltro;
	}
	
// de los filtros
	
	@Command
	@NotifyChange({"cedula", "nombre", "apellido", "sancion"})
	public void limpiar(){
		cedula=""; nombre=""; apellido=""; sancion="";
		filtros();
	}
	@Command
	@NotifyChange({"listafiltro"})
	public void filtros(){
		listafiltro = serviciolistaveredicto.buscarP(filtros);
	}
	@NotifyChange({"filtros"})
	public FiltroListaApelacionVeredicto1 getFiltros() {
		return filtros;
	}
	public void setFiltros(FiltroListaApelacionVeredicto1 filtros) {
		this.filtros = filtros;
	}
	
	
	@Init
	    public void init(){
	    	 //initialization code
	    	buscarTipoMotivo();
	    	buscarProgramaA ();
	    	buscarApelacionesveredicto ();
		
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
		public void buscarApelacionesveredicto(){
	  				System.out.print(serviciolistaveredicto);
		  			//lista = serviciolistaveredicto.buscarApelacionesVeredicto();
		}
	  	
  	@Command
		@NotifyChange({"cedula", "nombre", "apellido","sancion", "programa", "indice"})
		public void showModal (){
	  		cedula = getListaapelacionveredicto1().getCedula();
	  		nombre = getListaapelacionveredicto1().getNombre();
	  		apellido = getListaapelacionveredicto1().getApellido();
	  		sancion = getListaapelacionveredicto1().getSancion();
	  		programa = getListaapelacionveredicto1().getPrograma();
	  		indice = getListaapelacionveredicto1().getIndice();
	  			  		
	  		final HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("cedula", this.cedula );
	        map.put("nombre", this.nombre);
	        map.put("apellido", this.apellido);
	        map.put("sancion", this.sancion);
	        map.put("programa", this.programa);
	        map.put("indice", this.indice);
	        	        
	        final Window window = (Window) Executions.createComponents(
	        		"/WEB-INF/sigarep/vistas/transacciones/Veredicto.zul", null, map);
			window.setMaximizable(true);
			window.doModal();	
	  	}
}
