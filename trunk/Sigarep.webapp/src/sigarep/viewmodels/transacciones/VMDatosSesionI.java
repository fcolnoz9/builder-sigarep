package sigarep.viewmodels.transacciones;

import java.util.ArrayList;
import java.util.Date;
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
import org.zkoss.zk.ui.select.annotation.WireVariable;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDatosSesionI {
	
	//Datos de la sesion
	private String numeroSesion;
	private String tipoSesion;
	private Date fechaSesion = new Date();
	private List<SolicitudApelacion> listaSancionados = new ArrayList<SolicitudApelacion>();
	
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	
	private MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();
	
	public VMDatosSesionI() {
		
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
	public void init(
		@ContextParam(ContextType.VIEW) Component view,
		@ExecutionArgParam("listaSancionados") List<SolicitudApelacion> listaSancionados)
	{
		Selectors.wireComponents(view, this, false);
		this.listaSancionados = listaSancionados;
	}
	
	@Command
	public void guardarDatosSesion(){
		if (fechaSesion == null || tipoSesion == null || numeroSesion == null)
			mensajesAlUsuario.advertenciaLlenarCampos();
		else{
			int contador = 0;
			for (int i = 0; i < listaSancionados.size(); i++) {
				if (listaSancionados.get(i).getVeredicto() != null && !listaSancionados.get(i).getVeredicto().equals("")){
					contador++;
					listaSancionados.get(i).setFechaSesion(fechaSesion);
					listaSancionados.get(i).setNumeroSesion(numeroSesion);
					listaSancionados.get(i).setTipoSesion(tipoSesion);
					serviciosolicitudapelacion.guardar(listaSancionados.get(i));
				}
			}
			mensajesAlUsuario.informacionGuardarDatosSesion(contador);
		}
	}
	
	@Command
	@NotifyChange ({"fechaSesion", "tipoSesion", "numeroSesion"})
	public void limpiar(){
		fechaSesion = new Date();
		tipoSesion = null;
		numeroSesion = null;
	}

}
