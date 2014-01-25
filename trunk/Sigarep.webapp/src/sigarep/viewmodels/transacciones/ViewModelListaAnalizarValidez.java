package sigarep.viewmodels.transacciones;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.FiltroListaAnalizarValidez;
import sigarep.modelos.servicio.transacciones.ListaAnalizarValidez;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacionFiltros;
import sigarep.modelos.servicio.transacciones.ServicioAnalizarValidez;
import sigarep.modelos.servicio.transacciones.ServicioApelacion;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewModelListaAnalizarValidez {
	@WireVariable
	private Estudiante estudiante = new Estudiante();
	@WireVariable
	private EstudianteSancionado estudiantesancionado  = new EstudianteSancionado();
	@WireVariable
	private ListaAnalizarValidez listaanalizarvalidez;
	@WireVariable
	private SolicitudApelacion solicitudapelacion;
	@WireVariable
	private EstadoApelacion estadoapelacion;
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String nombreTipoMotivo;
//	@WireVariable
//	private ApelacionEstadoApelacion apelacioestadoapelacion;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	//@WireVariable
	//private ServicioAnalizarValidez serviciolista;
	@WireVariable
	private ServicioAnalizarValidez serviciolistaanalizarvalidez;
//	@WireVariable
//	private ServicioApelacion serviciolistaapelacion;
// para los filtros
	private String cedulafiltro, nombrefiltro, apellidofiltro, sancionfiltro;
	private FiltroListaAnalizarValidez filtros = new FiltroListaAnalizarValidez();
	private String programaFiltro;
	private List<ListaAnalizarValidez> listafiltro = new LinkedList<ListaAnalizarValidez>();

	@Wire("#winActualizarValidez")
	private Window window;
	
//	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private List<EstudianteSancionado> listaSancionados =  new LinkedList<EstudianteSancionado>();
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	

	private List<SancionMaestro> listasancion;
	//private List<ListaApelacionEstadoApelacion> listadoApelaciones;
	private List<ListaAnalizarValidez> lista = new LinkedList<ListaAnalizarValidez>();
	
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

	// ojo falta agregar numero de caso	
		public List<TipoMotivo> getListaTipoMotivo() {
				return listaTipoMotivo;
			}
		  
		public List<ProgramaAcademico> getListaPrograma() {
				return listaPrograma;
			}
		
//		public List<ListaApelacionEstadoApelacion> getListadoApelaciones() {
//			return listadoApelaciones;
//		}
//
//		public void setListadoApelaciones(
//				List<ListaApelacionEstadoApelacion> listadoApelaciones) {
//			this.listadoApelaciones = listadoApelaciones;
//		}

		public String getNombrePrograma() {
			return nombrePrograma;
		}

		public void setNombrePrograma(String nombrePrograma) {
			this.nombrePrograma = nombrePrograma;
		}

		public String getNombreTipoMotivo() {
			return nombreTipoMotivo;
		}

		public void setNombreTipoMotivo(String nombreTipoMotivo) {
			this.nombreTipoMotivo = nombreTipoMotivo;
		}

		public Window getWindow() {
			return window;
		}

		public void setWindow(Window window) {
			this.window = window;
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

		public String getLapso() {
			return lapso;
		}

		public void setLapso(String lapso) {
			this.lapso = lapso;
		}

		public Integer getInstancia() {
			return instancia;
		}

		public void setInstancia(Integer instancia) {
			this.instancia = instancia;
		}

		public String getMotivo() {
			return motivo;
		}

		public void setMotivo(String motivo) {
			this.motivo = motivo;
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

		public String getAsignatura() {
			return asignatura;
		}

		public void setAsignatura(String asignatura) {
			this.asignatura = asignatura;
		}

		public Integer getCaso() {
			return caso;
		}

		public void setCaso(Integer caso) {
			this.caso = caso;
		}

		public Integer getIdMotivo() {
			return idMotivo;
		}

		public void setIdMotivo(Integer idMotivo) {
			this.idMotivo = idMotivo;
		}

		public void setListaTipoMotivo(List<TipoMotivo> ListaTipoMotivo) {
			this.listaTipoMotivo = ListaTipoMotivo;
		}
		
		public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
			this.listaPrograma = listaPrograma;
		}
	   
		public ListaAnalizarValidez getListaanalizarvalidez() {
			return listaanalizarvalidez;
		}

		public void setListaanalizarvalidez(ListaAnalizarValidez listaanalizarvalidez) {
			this.listaanalizarvalidez = listaanalizarvalidez;
		}

		 public List<ListaAnalizarValidez> getLista() {
			return lista;
		}

		public void setLista(List<ListaAnalizarValidez> lista) {
			this.lista = lista;
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
		public List<ListaAnalizarValidez> getListafiltro() {
			return listafiltro;
		}

		public void setListafiltro(List<ListaAnalizarValidez> listafiltro) {
			this.listafiltro = listafiltro;
		}
		// De los filtros
		
		public String getCedulafiltro() {
			return cedulafiltro;
		}
		public void setCedulafiltro(String cedulafiltro) {
			this.cedulafiltro = cedulafiltro;
		}
		
		
		public String getProgramaFiltro() {
			return programaFiltro;
		}

		public void setProgramaFiltro(String programaFiltro) {
			this.programaFiltro = programaFiltro;
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
		public List<SancionMaestro> getListasancion() {
			return listasancion;
		}

		public void setListasancion(List<SancionMaestro> listasancion) {
			this.listasancion = listasancion;
		}
		// de los filtros
			
		@Init
	    public void init(){
	    	 //initialization code
	    	buscarTipoMotivo();
	    	buscarProgramaA ();
	    	buscarAnalizarValidez();
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
			listaPrograma = servicioprogramaacademico.buscarPrograma(nombrePrograma);
		}
//	  	@Command
//		@NotifyChange({"lista"})
//		public void buscarApelacionesR(){ 
//	  		listadoApelaciones = serviciolistaapelacion.buscarApelaciones();
//		}
		
		
		@Command
		@NotifyChange({"lista"})
		public void buscarAnalizarValidez(){ 
//			System.out.println(""+listadoApelaciones);
		  			lista = serviciolistaanalizarvalidez.buscarAnalizarValidez();
		  			System.out.println(""+lista);
		}
		
		@Command
		@NotifyChange({"cedula", "nombre", "apellido","email", "telefono", "programa", 
			"sancion", "lapso",  "segundoNombre", "segundoApellido", "asignatura", "caso"})
		public void showModal (){
	  		cedula = getListaanalizarvalidez().getCedulaEstudiante();
	  		nombre = getListaanalizarvalidez().getPrimerNombre();
	  		apellido = getListaanalizarvalidez().getPrimerApellido();
	  		email = getListaanalizarvalidez().getEmail();
	  		telefono = getListaanalizarvalidez().getTelefono();
	  		programa = getListaanalizarvalidez().getPrograma();
	  		sancion = getListaanalizarvalidez().getNombreSancion();
	  		lapso = getListaanalizarvalidez().getLapso();
	  		instancia = getListaanalizarvalidez().getInstancia();
	  		segundoNombre =getListaanalizarvalidez().getSegundoNombre();
	  		segundoApellido = getListaanalizarvalidez().getSegundoApellido();
	  		asignatura = getListaanalizarvalidez().getAsignatura();
	  		caso  = getListaanalizarvalidez().getCaso();
	  
	  		
	  		
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
	        System.out.println(cedula);
	        System.out.println(nombre);
	        System.out.println(apellido);
	        System.out.println(email);
	        System.out.println(telefono);
	        System.out.println(programa);
	        System.out.println(sancion);
	        System.out.println(lapso);
	        System.out.println(instancia);
	        System.out.println(segundoNombre);
	        System.out.println(segundoApellido);
	        System.out.println(asignatura);
	        System.out.println(caso);
	     
	        
	        final Window window = (Window) Executions.createComponents("/WEB-INF/sigarep/vistas/transacciones/AnalizarValidez.zul", null, map);
			window.setMaximizable(true);
			window.doModal();
		
		
	  	}

			
			@Command
			@NotifyChange({"listafiltro"})
			public void filtros(){
				listafiltro = serviciolistaanalizarvalidez.buscarPorfiltros(filtros);
			}
			@NotifyChange({"filtros"})
			public FiltroListaAnalizarValidez getFiltros() {
				return filtros;
			}
			public void setFiltros(FiltroListaAnalizarValidez filtros) {
				this.filtros = filtros;
			}
			
//	@Command
//	@NotifyChange({ "listasancion" })
//	public void buscarsancion() {
//		listasancion = serviciosancionmaestro.listadoSanciones();
//	}
//
//	@Command
//	@NotifyChange({ "lista" })
//	public void buscarAnalizarvalidez() {
//		lista = serviciolistaanalizarvalidez.buscarAnalizarValidez();
//	}
			  	

			 
			  
			  
			
}
