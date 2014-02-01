package sigarep.viewmodels.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Messagebox;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDatosSesionVeredicto {
	
	//Datos de la sesion
	private String numeroSesion;
	private String tipoSesion;
	private Date fechaSesion = new Date();
	private String titulo;
	private List<SolicitudApelacion> listaSancionados = new ArrayList<SolicitudApelacion>();
	
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	
	private MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();
	private String rutaModal;
	
	public VMDatosSesionVeredicto() {
		
	}
	
	public String getNumeroSesion() {
		return numeroSesion;
	}

	public void setNumeroSesion(String numeroSesion) {
		this.numeroSesion = numeroSesion;
	}

	public String getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	public Date getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}
	
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
					 @ExecutionArgParam("rutaModal") String rutaModal){
		
		Selectors.wireComponents(view, this, false);
		this.rutaModal=rutaModal;
		buscarDatosSesion();
	}
	
	@NotifyChange ({"fechaSesion", "tipoSesion", "numeroSesion","titulo"})
	public void buscarDatosSesion(){
		SolicitudApelacion solicitudApelacion = serviciosolicitudapelacion.buscarSolicitudParaDatosSesion();
		if (solicitudApelacion != null){
			fechaSesion = solicitudApelacion.getFechaSesion();
			tipoSesion = solicitudApelacion.getTipoSesion();
			numeroSesion = solicitudApelacion.getNumeroSesion();
			titulo = "Puede continuar con la siguiente sesión o proporcionar los datos de una nueva.";
			mensajesAlUsuario.informacionDatosDeSesionEncontrados();
		}else{
			titulo = "Proporcione los datos de una nueva sesión.";
		}
	}
	
	@Command
	public void guardarDatosSesion(){
		if (fechaSesion == null || tipoSesion == null || numeroSesion == null)
			mensajesAlUsuario.advertenciaLlenarCampos();
		else{
			mostrarListaSancionados();
		}
	}
	
	@Command
	@NotifyChange ({"fechaSesion", "tipoSesion", "numeroSesion"})
	public void limpiar(){
		fechaSesion = new Date();
		tipoSesion = null;
		numeroSesion = null;
	}
	
	public void mostrarListaSancionados(){
		final HashMap<String, Object> map = new HashMap<String, Object>();
	 	map.put("rutaModal", rutaModal);
	 	map.put("numeroSesion", numeroSesion);
	 	map.put("tipoSesion", tipoSesion);
	 	map.put("fechaSesion", fechaSesion);
	 	
	 	try {
			Borderlayout bl = (Borderlayout) Path.getComponent("/mainBorderLayout");
			Center center = bl.getCenter();
			center.getChildren().clear();
			Executions.createComponents("/WEB-INF/sigarep/vistas/transacciones/ListaGenericaSancionados.zul", center, map);
		} catch (Exception e) {
			Messagebox.show(e.toString());
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
