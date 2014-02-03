package sigarep.viewmodels.transacciones;


import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
/**
 * DetalleHistorialEstudiante 
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since 23/01/14
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDetalleHistorialEstudiante {
	@Wire("#modalDialog")
	private Window window;
	private String codigoLapso;
	private String cedula;
	private Integer instancia;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	private List<ApelacionEstadoApelacion> apelacionestudiante  = new LinkedList<ApelacionEstadoApelacion>(); 
	private List<ApelacionEstadoApelacion> apelacionestudianteinstancia2  = new LinkedList<ApelacionEstadoApelacion>(); 
	private List<ApelacionEstadoApelacion> apelacionestudianteinstancia3  = new LinkedList<ApelacionEstadoApelacion>(); 
	private SolicitudApelacion apelacionseleccionada;
	private List<SolicitudApelacion> apelacion = new LinkedList<SolicitudApelacion>();

	
	// Metodos get y set

	public Integer getInstancia() {
		return instancia;
	}

	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudianteinstancia2() {
		return apelacionestudianteinstancia2;
	}

	public void setApelacionestudianteinstancia2(
			List<ApelacionEstadoApelacion> apelacionestudianteinstancia2) {
		this.apelacionestudianteinstancia2 = apelacionestudianteinstancia2;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudianteinstancia3() {
		return apelacionestudianteinstancia3;
	}

	public void setApelacionestudianteinstancia3(
			List<ApelacionEstadoApelacion> apelacionestudianteinstancia3) {
		this.apelacionestudianteinstancia3 = apelacionestudianteinstancia3;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudiante() {
		return apelacionestudiante;
	}

	public void setApelacionestudiante(
			List<ApelacionEstadoApelacion> apelacionestudiante) {
		this.apelacionestudiante = apelacionestudiante;
	}

	public SolicitudApelacion getApelacionseleccionada() {
		return apelacionseleccionada;
	}

	public void setApelacionseleccionada(SolicitudApelacion apelacionseleccionada) {
		this.apelacionseleccionada = apelacionseleccionada;
	}

	public List<SolicitudApelacion> getApelacion() {
		return apelacion;
	}

	public void setApelacion(List<SolicitudApelacion> apelacion) {
		this.apelacion = apelacion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	
 // fin de metodos get y set
	
	@Command
	@NotifyChange({ "apelacionestudiante" })
	public void buscarSolicitud(String cedula, String codigoLapso, Integer instancia) {
		instancia = 1;
		apelacionestudiante = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, instancia);	
	}
	@Command
	@NotifyChange({ "apelacionestudianteinstancia2" })
	public void buscarSolicitudInstancia2(String cedula, String codigoLapso, Integer instancia) {
		instancia = 2;
		apelacionestudianteinstancia2 = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, instancia);
	}
	@Command
	@NotifyChange({ "apelacionestudianteinstancia3" })
	public void buscarSolicitudInstancia3(String cedula, String codigoLapso, Integer instancia) {
		instancia = 3;
		apelacionestudianteinstancia3 = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, instancia);	
	}
	
	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cedula") String v1,
			@ExecutionArgParam("codigoLapso") String v2)

	// initialization code
	{
		Selectors.wireComponents(view, this, false);
		this.cedula = v1;
		this.codigoLapso = v2;
		System.out.println("recibe"+cedula);
		System.out.println("recibe"+codigoLapso);
		System.out.println(""+instancia);
		buscarSolicitud(cedula, codigoLapso, instancia);
		buscarSolicitudInstancia2(cedula, codigoLapso, instancia);
		buscarSolicitudInstancia3(cedula, codigoLapso, instancia);
	}

	@Command
	public void closeThis() {
		window.detach();
	}
}
