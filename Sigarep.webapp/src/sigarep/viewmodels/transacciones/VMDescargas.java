package sigarep.viewmodels.transacciones;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Window;

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

	@Command
	public void modalReglamento() {
		final Window window = (Window) Executions
				.createComponents(
						"WEB-INF/sigarep/vistas/portal/externo/modales/Reglamentos.zul",
						null, null);
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
}
