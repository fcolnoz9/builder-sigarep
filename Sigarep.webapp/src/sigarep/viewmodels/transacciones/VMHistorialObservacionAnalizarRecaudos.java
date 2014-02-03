package sigarep.viewmodels.transacciones;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistorialObservacionAnalizarRecaudos {
	private String cedula;
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	private List<RecaudoEntregado> listaRecaudo;
	private List<EstudianteSancionado> listaEstudiantes = new LinkedList<EstudianteSancionado>();
	private String rutaModal; 
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	private SolicitudApelacion sancionadoSeleccionado;
	private EstudianteSancionado estudianteseleccionado;
	private List<SolicitudApelacion> lista = new LinkedList<SolicitudApelacion>();
	private Integer idInstancia;
	
	
	public List<SolicitudApelacion> getLista() {
		return lista;
	}

	public void setLista(List<SolicitudApelacion> lista) {
		this.lista = lista;
	}

	public List<RecaudoEntregado> getListaRecaudo() {
		return listaRecaudo;
	}

	public void setListaRecaudo(List<RecaudoEntregado> listaRecaudo) {
		this.listaRecaudo = listaRecaudo;
	}
	
	
	public SolicitudApelacion getSancionadoSeleccionado() {
		return sancionadoSeleccionado;
	}

	public void setSancionadoSeleccionado(SolicitudApelacion sancionadoSeleccionado) {
		this.sancionadoSeleccionado = sancionadoSeleccionado;
	}

	public EstudianteSancionado getEstudianteseleccionado() {
		return estudianteseleccionado;
	}

	public void setEstudianteseleccionado(
			EstudianteSancionado estudianteseleccionado) {
		this.estudianteseleccionado = estudianteseleccionado;
	}

	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	@Init
    public void init(
    		@ContextParam(ContextType.VIEW) Component view, 
    		@ExecutionArgParam("cedula") String cedula,
			@ExecutionArgParam("idInstancia") Integer idInstancia){
		this.cedula = cedula;
		this.idInstancia = idInstancia;
		System.out.println("CEDULA"+cedula);
		buscarRecaudosEntregados(cedula); 
    }

	
	/** buscarRecaudosEntregados
	 * @param cedula 
	 * @return Lista de recaudos 
	 */
	@Command
	@NotifyChange({ "listaRecaudo" })
	public void buscarRecaudosEntregados(String cedula) {
		listaRecaudo = serviciorecaudoentregado.buscarRecaudosEntregadosAnalizarValidezI(cedula);
	}
	
}
