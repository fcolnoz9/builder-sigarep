package sigarep.viewmodels.transacciones;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.servicio.maestros.ServicioReglamento;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMAyuda {

	private AMedia contenidoManual;
	private Reglamento reglamento;
	@WireVariable
	private ServicioReglamento servicioreglamento;
//Set y Get 	
	public AMedia getContenidoManual() {
		return contenidoManual;
	}

	public void setContenidoManual(AMedia contenidoManual) {
		this.contenidoManual = contenidoManual;
	}

		
	@Init
	public void init(@ExecutionArgParam("rutaModal") String rutaModal) throws IOException{
        //initialization code
		if (rutaModal.equalsIgnoreCase("ManualUsuario"))
			buscarManualUsuario();
		else
			buscarManualSistema();
		mostrarManual();
	}
//Métodos de búsqueda de los manuales
	private void buscarManualSistema() {
		reglamento = servicioreglamento.buscarManualSistema().get(0);
	}

	private void buscarManualUsuario() {
		reglamento = servicioreglamento.buscarManualUsuario().get(0);
	}
//Método que permite mostrar los manuales	
	@Command
    @NotifyChange("contenidoManual")
    public void mostrarManual() throws IOException {
     contenidoManual = new AMedia(reglamento.getDocumento().getNombreDocumento(), "pdf", reglamento.getDocumento().getTipoDocumento(), reglamento.getDocumento().getContenidoDocumento());
     System.out.println(contenidoManual);
   }
}