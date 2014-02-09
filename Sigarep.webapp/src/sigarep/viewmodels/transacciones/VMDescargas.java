package sigarep.viewmodels.transacciones;

import java.util.HashMap;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.servicio.maestros.ServicioReglamento;

/**
 * Cronograma UCLA DCYT Sistemas de Informacion.
 * 
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 * @since 22/01/14
 */
@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDescargas {

	@WireVariable
	private ServicioReglamento servicioreglamento;
	private MensajesAlUsuario msj = new MensajesAlUsuario();

	@Command
	public void modalReglamento() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("categoria", "REGLAMENTO");
		final Window window = (Window) Executions
				.createComponents(
						"WEB-INF/sigarep/vistas/portal/externo/modales/descargarArchivo.zul",
						null, map);
		window.setMaximizable(true);
		window.doModal();
	}

	@Command
	public void modalRecaudosPorMotivo() {
		final Window window = (Window) Executions
				.createComponents(
						"WEB-INF/sigarep/vistas/portal/externo/modales/RecaudosPorMotivo.zul",
						null, null);
		window.setMaximizable(true);
		window.doModal();
	}

	@Command
	public void modalFormato() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("categoria", "FORMATO");
		final Window window = (Window) Executions
				.createComponents(
						"WEB-INF/sigarep/vistas/portal/externo/modales/descargarArchivo.zul",
						null, map);
		window.setMaximizable(true);
		window.doModal();
	}

	@Command
	public void descargarCalendario() {
		Reglamento calendario = servicioreglamento.buscarCalendario();
		if (calendario != null) {
			Filedownload.save(
					calendario.getDocumento().getContenidoDocumento(),
					calendario.getDocumento().getTipoDocumento(), calendario
							.getDocumento().getNombreDocumento());
		} else {
			msj.advertenciaCargarDocumento();
		}
	}

}
