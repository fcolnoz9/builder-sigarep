package sigarep.viewmodels.transacciones;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
 * Descargas 
 * Maneja la descaga de archivos que requieran los estudiantes en el portal
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 * @since 22/01/14
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDescargas {

	Window win = null;
	@WireVariable
	private ServicioReglamento servicioreglamento;
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private List<Reglamento> listaReglamento = new LinkedList<Reglamento>();

	@Command
	public void modalReglamento() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("categoria", "REGLAMENTO");
		if (win != null) {
			win.detach();
		}
		win = (Window) Executions
				.createComponents(
						"WEB-INF/sigarep/vistas/portal/externo/modales/DescargarArchivo.zul",
						null, map);
		win.setMaximizable(true);
		win.doModal();
	}

	@Command
	public void modalRecaudosPorMotivo() {
		if (win != null) {
			win.detach();
		}
		win = (Window) Executions
				.createComponents(
						"WEB-INF/sigarep/vistas/portal/externo/modales/RecaudosPorMotivo.zul",
						null, null);
		win.setMaximizable(true);
		win.doModal();
	}

	@Command
	public void modalFormato() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("categoria", "FORMATO");
		if (win != null) {
			win.detach();
		}
		win = (Window) Executions
				.createComponents(
						"WEB-INF/sigarep/vistas/portal/externo/modales/DescargarArchivo.zul",
						null, map);
		win.setMaximizable(true);
		win.doModal();
	}

	@Command
	public void descargarCalendario() {
		listaReglamento = servicioreglamento.buscarCalendario();
		if (listaReglamento.size() > 0) {
			Reglamento calendario = servicioreglamento.buscarCalendario()
					.get(0);
			if (calendario != null) {
				Filedownload.save(calendario.getDocumento()
						.getContenidoDocumento(), calendario.getDocumento()
						.getTipoDocumento(), calendario.getDocumento()
						.getNombreDocumento());
			} else {
				mensajeAlUsuario.advertenciaCargarDocumento();
			}
		} else {
			mensajeAlUsuario.advertenciaCargarDocumento();
		}
	}
}//fin VMDescargas
