package sigarep.viewmodels.lista;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.lista.ListaGenericaSancionados;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioVeredicto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMListaGenericaSancionados {


	private ListaGenericaSancionados sancionadoSeleccionado;
	
	
	//Servicios para llenar los combos
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	
	//Servicios para buscar apelaciones segun su transaccion (AGREGA TU SERVICIO AQUI)
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioVeredicto servicioveredicto;
	
	
	//Lista que se llena segun la transaccion
	private List<ListaGenericaSancionados> lista = new LinkedList<ListaGenericaSancionados>();
	
	//Variables para el filtrado por combos o textbox
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private String nombrePrograma;
	private String nombreTipoMotivo;
	private String rutaModal="";
	private String sancion="";
	private String programa="";
	private String apellido="";
	private String nombre="";
	private String cedula="";

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}

	public String getSancion() {
		return sancion;
	}

	public String getPrograma() {
		return programa;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public ServicioRecaudoEntregado getServiciorecaudoentregado() {
		return serviciorecaudoentregado;
	}

	public ListaGenericaSancionados getSancionadoSeleccionado() {
		return sancionadoSeleccionado;
	}

	public void setSancionadoSeleccionado(
			ListaGenericaSancionados sancionadoSeleccionado) {
		this.sancionadoSeleccionado = sancionadoSeleccionado;
	}

	public List<ListaGenericaSancionados> getLista() {
		return lista;
	}

	public void setLista(List<ListaGenericaSancionados> lista) {
		this.lista = lista;
	}

	/**
	 * Otros Metodos
	 */
	
	@Init
    public void init(@ExecutionArgParam("rutaModal") String rutaModal){
		this.rutaModal=rutaModal;
		buscarTipoMotivo();
		buscarProgramaA ();
		buscarSancionados();
    }
	
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
	
	
	//Metodo donde se decide cuales sancionados se deben buscar segun la transaccion
	@Command
	@NotifyChange({"lista"})
	public void buscarSancionados(){
		if (rutaModal.equalsIgnoreCase("transacciones/CargarRecaudoEntregado.zul"))
			lista = serviciorecaudoentregado.buscarApelacionesCargarRecaudo();
		else if (rutaModal.equalsIgnoreCase("transacciones/Veredicto.zul"))
			lista = servicioveredicto.buscarApelacionesVeredicto1();
	}
	
	@Command
	public void showModal (){
  		
  		final HashMap<String, Object> map = new HashMap<String, Object>();
	 	map.put("cedula", sancionadoSeleccionado.getCedulaEstudiante());
        map.put("primerNombre", sancionadoSeleccionado.getPrimerNombre());
        map.put("primerApellido", sancionadoSeleccionado.getPrimerApellido());
        map.put("segundoNombre", sancionadoSeleccionado.getSegundoNombre());
        map.put("segundoApellido", sancionadoSeleccionado.getSegundoApellido());
        map.put("email", sancionadoSeleccionado.getEmail());
        map.put("programa", sancionadoSeleccionado.getProgramaAcademico());
        map.put("indice", sancionadoSeleccionado.getIndiceAcademico());
        map.put("sancion", sancionadoSeleccionado.getNombreSancion());
        map.put("lapsosConsecutivos", sancionadoSeleccionado.getLapsosConsecutivos());
        map.put("lapsoAcademico", sancionadoSeleccionado.getLapsoAcademico());
        map.put("numeroCaso", sancionadoSeleccionado.getNumeroCaso());
        map.put("idInstancia", sancionadoSeleccionado.getIdInstancia());
 
        final Window window = (Window) Executions.createComponents(
        		"/WEB-INF/sigarep/vistas/"+rutaModal, null, map);
		window.setMaximizable(true);
		window.doModal();
  	}
	
	@Command
	@NotifyChange({"lista","programa","cedula","nombre","apellido","sancion"})
	public void filtros(){
		if (rutaModal.equalsIgnoreCase("transacciones/CargarRecaudoEntregado.zul"))
			lista = serviciorecaudoentregado.filtrarApelacionesCargarRecaudo(programa,cedula,nombre,apellido,sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/Veredicto.zul"))
			lista = servicioveredicto.filtrarApelacionesVeredicto1(cedula, nombre, apellido, programa, sancion);
	}

}

